/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sorcererscave;


import java.util.ArrayList;

public class Party extends Cave {
	private String location;
	private ArrayList<Creature> creatures;

	/***
	 * 
	 * @param index
	 * @param name
	 */
	public Party(int index, String name) {
		super(index, name);
		creatures = new ArrayList<Creature>();

	}

	/***
	 * 
	 * @param creature
	 */
	public void addCreature(Creature creature) {
		if (creature != null && creature instanceof Creature) {
			this.getCreatures().add(creature);
		}
	}

	@Override
	public String toString() {
		String output = "";
		output += "Party: \nName = " + this.getName() + " \nIndex = "
				+ Integer.toString(this.getIndex());
		output += "\n---------Member Creatures---------\n";
		for (Creature c : this.getCreatures())
			output += "\n\t" + c.toString();
		output += "\n----------------------------------\n";
		return output;
	}

	/**
	 * @return the creatures
	 */
	public ArrayList<Creature> getCreatures() {
		return creatures;
	}

	/**
	 * @param creatures
	 *            the creatures to set
	 */
	public void setCreatures(ArrayList<Creature> creatures) {
		this.creatures = creatures;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}
}
