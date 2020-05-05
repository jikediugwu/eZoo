package com.examples.ezoo.dao;

import java.util.List;

import com.examples.ezoo.model.FeedingSchedule;

public interface FeedingScheduleDAO<T, K> {

	void addFeeding(FeedingSchedule feedingSchedule) throws Exception;

	void deleteFeeding(long id);

	List<T> getAllFeeding();

	T getFeeding(long id) throws Exception;

	void assignAnimalFeeding(T t, K k) throws Exception;

	void unassignAnimalFeeding(T t, K k) throws Exception;

	void updateFeeding(long id, T t) throws Exception;
}