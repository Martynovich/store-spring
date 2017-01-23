package com.andersen.service;

import java.util.List;

import com.andersen.domain.Client;

public interface CrudServise<T> {
	
	public void create(T t);
	
	public T findById(int id);
	
	public List<T> findAll();
	
	public void update(T t);
	
	public void delete(T t);
	
	public void deleteById(int id);
	
	public void deleteAll();
}
