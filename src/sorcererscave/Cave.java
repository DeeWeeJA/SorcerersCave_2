/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sorcererscave;


public class Cave {
	private int level;
	private String type;
	private String name;

	
	/***
	 * 
	 * @param level
	 */
	public Cave(int level) {
		this.setIndex(level);
	}

	/***
	 * 
	 * @param level
	 * @param type
	 * @param name
	 */
	public Cave(int level, String type, String name) {
		this.setIndex(level);
		this.setType(type);
		this.setName(name);
	}

	/***
	 * 
	 * @param level
	 * @param name
	 */
	public Cave(int level, String name) {
		this.setIndex(level);
		this.setName(name);
	}

	/***
	 * 
	 * @param type
	 * @param level
	 */
	public Cave(String type, int level) {
		this.setIndex(level);
		this.setType(type);
	}

	/**
	 * @return the level
	 */
	public int getIndex() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setIndex(int level) {
		this.level = level;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}

