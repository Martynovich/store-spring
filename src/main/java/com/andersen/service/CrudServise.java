package com.andersen.service;

import java.util.List;

import com.andersen.domain.Client;

public interface CrudServise<T> {
	
	void create(T t);
	
	T findById(int id);
	
	List<T> findAll();
	
	void update(T t);
	
	void delete(T t);
	
	void deleteById(int id);
	
	void deleteAll();
}
