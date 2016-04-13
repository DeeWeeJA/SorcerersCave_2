/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sorcererscave;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Game extends JFrame implements ActionListener {
	private JTextArea output;
	private JFileChooser fileChooser;
	private JButton loadButton;
	private JButton displayData;
	private JComboBox<String> searchCriteria;
	private JScrollPane completePanel;
	private JPanel gamePanel;
	private JButton searchButton;
	private JTextField searchItem;
	private String fileName;
	private HashMap<Integer, Cave> caves;
	private ArrayList<Cave> loners;
	private ArrayList<Party> parties;
	private Party party;
	private Creature creature;
	private Treasure treasure;
	private Artifact artifact;
	private String[] searching = { "index", "type", "name" };
	private boolean fileSelected = false;

	/***
	 * Default Constructor
	 */
	public Game() {
		caves = new HashMap<Integer, Cave>();
		loners = new ArrayList<Cave>();
		parties = new ArrayList<Party>();
		gamePanel = new JPanel();
		searchCriteria = new JComboBox<>(searching);
		fileChooser = new JFileChooser(".");

		output = new JTextArea(
				"You can perform following operation:\n" + "1. Search \n" + "2.Show data\n" + "3.Load data\n");
		displayData = new JButton("Display");
		loadButton = new JButton("Read File");
		searchButton = new JButton("Search");
		searchItem = new JTextField(10);

		this.loadButton.addActionListener(this);
		this.displayData.addActionListener(this);
		this.searchButton.addActionListener(this);
		this.searchCriteria.addActionListener(this);
		completePanel = new JScrollPane(output);
		add(completePanel, BorderLayout.CENTER);

		gamePanel.add(loadButton);
		gamePanel.add(displayData);
		gamePanel.add(searchCriteria);
		gamePanel.add(searchButton);
		gamePanel.add(searchItem);
		add(gamePanel, BorderLayout.PAGE_END);
		output.setBackground(Color.CYAN);

		setTitle("Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(600, 500);
		setVisible(true);
	}

	/***
	 * 
	 * @param fileName
	 * @throws IOException
	 */
	public Game(String fileName) throws IOException {
		this.fileName = fileName;
		this.loatData(fileName);
	}

	/***
	 * 
	 * @param fileName
	 * @throws IOException
	 */
	public void setGameElements(String fileName) throws IOException {
		this.loatData(fileName);
	}

	/***
	 * 
	 * @param fileName
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/***
	 * 
	 * @return
	 */
	public String getFileName() {
		return this.fileName;
	}

	/***
	 * 
	 * @param fileName
	 * @throws IOException
	 */
	private void loatData(String fileName) throws IOException {
		Path path = Paths.get(fileName);
		List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
		for (String line : lines) {
			line = line.replaceAll("\\s", "");
			if (!line.startsWith("//") && line.length() > 0) {
				String[] extractedData = line.split(":");
				switch (extractedData[0].charAt(0)) {
				case 'p':
					loadParty(extractedData);
					break;
				case 'c':
					loadCreatures(extractedData);
					break;
				case 't':
					loadTreasure(extractedData);

					break;
				case 'a':
					loadArtifact(extractedData);
					break;

				default:
					break;
				}
			}
		}
	}

	/***
	 * 
	 * @param extractedData
	 */
	private void loadTreasure(String[] extractedData) {
		treasure = new Treasure(Integer.parseInt(extractedData[1]), extractedData[2],
				Integer.parseInt(extractedData[3]), Double.parseDouble(extractedData[4]),
				Double.parseDouble(extractedData[5]));
		Creature treasureCreature = (Creature) caves.get(treasure.getTreasureIndex());
		if (treasureCreature == null || treasure.getTreasureIndex() == 0) {
			this.loners.add(treasure);
		} else {
			treasureCreature.addTreature(treasure);
		}
		this.caves.put(treasure.getIndex(), treasure);

	}

	/***
	 * Laod Artifact
	 * 
	 * @param extractedData
	 */
	private void loadArtifact(String[] extractedData) {
		artifact = new Artifact(Integer.parseInt(extractedData[1]), extractedData[2],
				Integer.parseInt(extractedData[3]));
		Creature artifactCreature = (Creature) caves.get(artifact.getCreatureIndex());
		if (artifactCreature == null || artifact.getCreatureIndex() == 0) {
			this.loners.add(artifact);
		} else if (artifact.getCreatureIndex() != 0) {
			artifactCreature.addArtifact(artifact);
		}
		this.caves.put(artifact.getIndex(), artifact);

	}

	/***
	 * Load Party
	 * 
	 * @param extractedData
	 */
	private void loadParty(String[] extractedData) {
		party = new Party(Integer.parseInt(extractedData[1]), extractedData[2]);
		this.caves.put(party.getIndex(), party);
		parties.add(party);

	}

	/***
	 * Load Creatures
	 * 
	 * @param extractedData
	 */
	private void loadCreatures(String[] extractedData) {
		creature = new Creature(Integer.parseInt(extractedData[1]), extractedData[2], extractedData[3],
				Integer.parseInt(extractedData[4]), Integer.parseInt(extractedData[5]),
				Integer.parseInt(extractedData[6]), Double.parseDouble(extractedData[7]));

		if (extractedData.length > 8 && extractedData[8] != null) {
			creature.setAge(Double.parseDouble(extractedData[8]));
		}
		if (extractedData.length > 9 && extractedData[9] != null) {
			creature.setHeight(Double.parseDouble(extractedData[9]));
		}
		if (extractedData.length > 10 && extractedData[10] != null) {
			creature.setWeight(Double.parseDouble(extractedData[10]));
		}
		Party creatureParty = (Party) caves.get(creature.getPartyLevel());
		if (creatureParty == null || creature.getPartyLevel() == 0) {
			this.loners.add(creature);
		} else {
			creatureParty.addCreature(creature);
		}
		this.caves.put(creature.getIndex(), creature);

	}

	/***
	 * 
	 * @return
	 */
	public String toString() {
		String output = "";
		ArrayList<Cave> printStatement = new ArrayList<Cave>();
		printStatement.addAll(caves.values());
		for (Cave cave : printStatement) {
			if (cave instanceof Party) {
				Party p = (Party) cave;
				output += p.toString();
			}
		}
		output += "\n-----------------\nLoners\n-----------------\n";
		for (Cave ge : loners) {
			output += "\n" + ge.toString();
		}
		return output;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == loadButton) {
			int returnVal = fileChooser.showOpenDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				try {
					if (file.getName().equals("dataZ.txt")) {
						fileSelected = true;
						this.setGameElements(file.getAbsolutePath());
						JOptionPane.showMessageDialog(null, "Data Successfully Loaded!");
					} else {
						JOptionPane.showMessageDialog(null, "Wrong file Selected!(Kindly load 'dataZ.txt')");

					}

				} catch (IOException ex) {
					Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}

		// Searching Data
		else if (e.getSource() == displayData) {
			if (fileSelected) {
				output.setText(this.toString());
			} else {
				JOptionPane.showMessageDialog(null, "No file selected!(Kindly load 'dataZ.txt')");
			}
		} else if (e.getSource() == searchButton) {
			if (searchCriteria.getSelectedItem().equals("index")) {
				String resultText = "";
				String searchInput = searchItem.getText();

				if (searchInput.equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter an index into search box");

				} else {

					resultText = "Search Results: \n------------\n";
					resultText += searchGame(searchInput, 0);
				}
				output.setText("");
				output.setText(resultText);
			} else if (searchCriteria.getSelectedItem().equals("type")) {
				String resultText = "";
				String searchInput = searchItem.getText();
				if (searchInput.equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter a type into search box");

				} else {
					resultText = "Search Results: \n------------\n";
					resultText += searchGame(searchInput, 1);
				}
				output.setText("");
				output.setText(resultText);
			} else if (searchCriteria.getSelectedItem().equals("name")) {
				String resultText = "";
				String searchInput = searchItem.getText();
				if (searchInput.equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter a name into search box");

				} else {
					resultText = "Search Results: \n------------\n";
					resultText += searchGame(searchInput, 2);
				}

				output.setText("");
				output.setText(resultText);

			}
		}

	}

	/***
	 * 
	 * @param searchInput
	 * @param searchTpe
	 * @return
	 */
	public String searchGame(String searchInput, int searchTpe) {
		String result = "";
		ArrayList<Cave> printElements = new ArrayList<Cave>();
		printElements.addAll(caves.values());
		printElements.addAll(loners);
		for (Cave ge : printElements) {
			if (searchTpe == 0) {
				if (ge.getIndex() != 0 && searchInput != null && Integer.toString(ge.getIndex()).equals(searchInput)) {
					result += ge.toString() + "\n";
				}
			} else if (searchTpe == 1) {
				if (ge.getType() != null

						&& searchInput != null && ge.getType().toLowerCase().equals(searchInput.toLowerCase())) {
					result += ge.toString() + "\n";
				}
			} else if (searchTpe == 2) {
				if (ge.getName() != null

						&& searchInput != null && ge.getName().toLowerCase().equals(searchInput.toLowerCase())) {
					result += ge.toString() + "\n";
				}
			}
		}

		if (result.equals("")) {
			JOptionPane.showMessageDialog(null, "No result found!");
			result = "\tNo result found";
		}
		return result;
	}

	/***
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		new Game();
	}

}
