package com.andersen.persistence;

import java.util.List;

import com.andersen.domain.Product;

public interface ProductDao {

	void persist(Product entity);

	Product find(int id);

	List<Product> findAll();

	void update(Product entity);

	void deleteById(int id);

	void delete(Product entity);

	void deleteAll();

}
