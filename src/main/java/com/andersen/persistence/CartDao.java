package com.andersen.persistence;

import java.util.List;

import com.andersen.domain.Cart;

public interface CartDao {

	public void persist(Cart entity);

	public Cart find(int id);

	public List<Cart> findAll();

	public void update(Cart entity);

	public void deleteById(int id);

	public void delete(Cart entity);

	public void deleteAll();

}
