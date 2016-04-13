/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sorcererscave;


class Treasure extends Cave {
	private int treasureIndex;
	private double weight;
	private double value;

	/***
	 * Constructor
	 * 
	 * @param index
	 * @param type
	 * @param treasureIndex
	 * @param weight
	 * @param value
	 */
	public Treasure(int index, String type, int treasureIndex, double weight,
			double value) {
		super(type, index);
		this.setTreasureIndex(treasureIndex);
		this.setWeight(weight);
		this.setValue(value);
	}

	@Override
	public String toString() {
		return "[Treasure]: \n\tType = " + this.getType()
				+ " \n\tOwner Index = " + Integer.toString(this.getTreasureIndex())
				+ " \n\tWeight = " + Double.toString(this.getWeight())
				+ " \n\tValue = " + Double.toString(this.getValue());
	}

	/**
	 * @return the treasureIndex
	 */
	public int getTreasureIndex() {
		return treasureIndex;
	}

	/**
	 * @param treasureIndex the treasureIndex to set
	 */
	public void setTreasureIndex(int treasureIndex) {
		this.treasureIndex = treasureIndex;
	}

	/**
	 * @return the weight
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}

	/**
	 * @return the value
	 */
	public double getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(double value) {
		this.value = value;
	}
}
