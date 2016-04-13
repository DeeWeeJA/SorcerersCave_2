/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sorcererscave;


import java.util.ArrayList;

class Creature extends Cave {
	private int partyLevel;
	private int empathyValue;
	private int fearValue;
	private double capacity;
	private double age;
	private double weight;
	private double height;
	private ArrayList<Treasure> treasures;
	private ArrayList<Artifact> artifacts;

	/***
	 * 
	 * @param index
	 * @param type
	 * @param name
	 * @param partyLevel
	 * @param empathyValue
	 * @param fearValue
	 * @param capacity
	 */
	public Creature(int index, String type, String name, int partyLevel,
			int empathyValue, int fearValue, double capacity) {
		super(index, type, name);
		this.setPartyLevel(partyLevel);
		this.setEmpathyValue(empathyValue);
		this.setFearValue(fearValue);
		this.setCapacity(capacity);
		treasures = new ArrayList<Treasure>();
		artifacts = new ArrayList<Artifact>();
	}

	/***
	 * 
	 * @param index
	 * @param type
	 * @param name
	 * @param partyLevel
	 * @param empathyValue
	 * @param fearValue
	 * @param capacity
	 * @param age
	 * @param weight
	 * @param height
	 */
	public Creature(int index, String type, String name, int partyLevel,
			int empathyValue, int fearValue, double capacity, int age,
			int weight, double height) {
		super(index, type, name);
		this.setPartyLevel(partyLevel);
		this.setEmpathyValue(empathyValue);
		this.setFearValue(fearValue);
		this.setCapacity(capacity);
		this.setAge(age);
		this.setWeight(weight);
		this.setHeight(height);
		treasures = new ArrayList<Treasure>();
		artifacts = new ArrayList<Artifact>();

	}

	/***
	 * 
	 * @param t
	 */
	public void addTreature(Treasure treasure) {
		if (treasure != null && (treasure instanceof Treasure)
				&& treasure.getTreasureIndex() != 0) {
			this.treasures.add(treasure);
		}
	}

	public void addArtifact(Artifact artifact) {
		if (artifact != null && artifact instanceof Artifact
				&& artifact.getCreatureIndex() != 0) {
			this.artifacts.add(artifact);
		}
	}

	@Override
	public String toString() {
		String output = "";
		output += "[Creature]: \n\tType = " + this.getType() + " \n\tName = "
				+ this.getName() + " \n\tParty Index = "
				+ Integer.toString(this.getPartyLevel())
				+ " \n\tEmpathy Value = "
				+ Integer.toString(this.getEmpathyValue())
				+ " \n\tFear Value = " + Integer.toString(this.getFearValue())
				+ " \n\tCarrying Capacity = "
				+ Double.toString(this.getCapacity());
		output += "\n\t------Owned Items------\n";
		for (Treasure t : this.treasures)
			output += "\n\t" + t.toString();
		for (Artifact a : this.artifacts)
			output += "\n\t" + a.toString();
		output += "\n\t-----------------------\n";
		return output;
	}

	/**
	 * @return the age
	 */
	public double getAge() {
		return age;
	}

	/**
	 * @param age
	 *            the age to set
	 */
	public void setAge(double age) {
		this.age = age;
	}

	/**
	 * @return the weight
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * @param weight
	 *            the weight to set
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}

	/**
	 * @return the height
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * @param height
	 *            the height to set
	 */
	public void setHeight(double height) {
		this.height = height;
	}

	/**
	 * @return the partyLevel
	 */
	public int getPartyLevel() {
		return partyLevel;
	}

	/**
	 * @param partyLevel
	 *            the partyLevel to set
	 */
	public void setPartyLevel(int partyLevel) {
		this.partyLevel = partyLevel;
	}

	/**
	 * @return the fearValue
	 */
	public int getFearValue() {
		return fearValue;
	}

	/**
	 * @param fearValue
	 *            the fearValue to set
	 */
	public void setFearValue(int fearValue) {
		this.fearValue = fearValue;
	}

	/**
	 * @return the capacity
	 */
	public double getCapacity() {
		return capacity;
	}

	/**
	 * @param capacity
	 *            the capacity to set
	 */
	public void setCapacity(double capacity) {
		this.capacity = capacity;
	}

	/**
	 * @return the empathyValue
	 */
	public int getEmpathyValue() {
		return empathyValue;
	}

	/**
	 * @param empathyValue
	 *            the empathyValue to set
	 */
	public void setEmpathyValue(int empathyValue) {
		this.empathyValue = empathyValue;
	}
}
