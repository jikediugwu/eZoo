package com.examples.ezoo.model;

public class FeedingSchedule {

	private long scheduleID;
	private String feedingTime;
	private String recurrence;
	private String food;
	private String notes;

	public FeedingSchedule() {
		super();
	}

	public FeedingSchedule(long scheduleID, String feedingTime, String recurrence, String food, String notes) {
		super();
		this.scheduleID = scheduleID;
		this.feedingTime = feedingTime;
		this.recurrence = recurrence;
		this.food = food;
		this.notes = notes;
	}

	public long getScheduleID() {
		return scheduleID;
	}

	public void setScheduleID(long scheduleID) {
		this.scheduleID = scheduleID;
	}

	public String getFeedingTime() {
		return feedingTime;
	}

	public void setFeedingTime(String feedingTime) {
		this.feedingTime = feedingTime;
	}

	public String getRecurrence() {
		return recurrence;
	}

	public void setRecurrence(String recurrence) {
		this.recurrence = recurrence;
	}

	public String getFood() {
		return food;
	}

	public void setFood(String food) {
		this.food = food;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
	public String toString() {
		return "FeedingSchedule [scheduleID=" + scheduleID + ", feedingTime=" + feedingTime + ", recurrence="
				+ recurrence + ", food=" + food + ", notes=" + notes + "]";
	}

}
