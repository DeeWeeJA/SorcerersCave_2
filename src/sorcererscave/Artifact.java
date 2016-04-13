/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sorcererscave;


class Artifact extends Cave {
	private int level;

	/***
	 * 
	 * @param index
	 * @param type
	 * @param level
	 */
	public Artifact(int index, String type, int level) {
		super(type, index);
		this.setCreatureIndex(level);
	}

	@Override
	public String toString() {
		return "[Artifact]: \n\tOwner Index = "
				+ Integer.toString(this.getCreatureIndex())
				+ " \n\tArtifact Type = " + this.getType();
	}

	/**
	 * @return the level
	 */
	public int getCreatureIndex() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setCreatureIndex(int level) {
		this.level = level;
	}
}

