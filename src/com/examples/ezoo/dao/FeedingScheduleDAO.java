package com.examples.ezoo.dao;

import java.util.List;

import com.examples.ezoo.model.FeedingSchedule;

public interface FeedingScheduleDAO<T, K> {

	void addFeeding(FeedingSchedule feedingSchedule);

	void deleteFeeding(long id);

	List<T> getAllFeeding();

	T getFeeding(long id);

	void assignAnimalFeeding(T t, K k);

	void unassignAnimalFeeding(T t, K k);

}