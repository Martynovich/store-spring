package com.andersen.persistence;

import java.util.List;

import com.andersen.domain.Product;

public interface ProductDao {

	public void persist(Product entity);

	public Product find(int id);

	public List<Product> findAll();

	public void update(Product entity);

	public void deleteById(int id);

	public void delete(Product entity);

	public void deleteAll();

}
