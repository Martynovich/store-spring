package com.andersen.persistence;

import java.util.List;

import com.andersen.domain.Cart;

public interface CartDao {

	void persist(Cart entity);

	Cart find(int id);

	List<Cart> findAll();

	void update(Cart entity);

	void deleteById(int id);

	void delete(Cart entity);

	void deleteAll();

}
