package com.examples.ezoo.model;

public class FeedingAnimal {

	private long animalID;
	private String animalName;
	private String food;
	private long scheduleID;

	public FeedingAnimal() {
		super();
	}

	public FeedingAnimal(long animalID, String animalName, String food, long scheduleID) {
		super();
		this.animalID = animalID;
		this.animalName = animalName;
		this.food = food;
		this.scheduleID = scheduleID;
	}

	public long getAnimalID() {
		return animalID;
	}

	public void setAnimalID(long animalID) {
		this.animalID = animalID;
	}

	public String getAnimalName() {
		return animalName;
	}

	public void setAnimalName(String animalName) {
		this.animalName = animalName;
	}

	public String getFood() {
		return food;
	}

	public void setFood(String food) {
		this.food = food;
	}

	public long getScheduleID() {
		return scheduleID;
	}

	public void setScheduleID(long scheduleID) {
		this.scheduleID = scheduleID;
	}

	@Override
	public String toString() {
		return "FeedingAnimal [animalID=" + animalID + ", animalName=" + animalName + ", food=" + food + ", scheduleID="
				+ scheduleID + "]";
	}

}