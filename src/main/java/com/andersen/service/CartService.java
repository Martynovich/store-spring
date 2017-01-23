package com.andersen.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.andersen.domain.Cart;
import com.andersen.persistence.CartDao;
import com.andersen.persistence.CartDaoImpl;

@Component
public class CartService implements CrudServise<Cart> {

	private static final Logger logger = Logger.getLogger(CartService.class);

	@Autowired
	private CartDao cartDao;

	public CartService() {
	}

	public void create(Cart cart) {
		logger.debug("CartService create");
		cartDao.persist(cart);
	}

	public Cart findById(int id) {
		return cartDao.find(id);
	}

	public List<Cart> findAll() {
		return cartDao.findAll();
	}

	public void update(Cart cart) {
		cartDao.update(cart);

	}

	public void delete(Cart cart) {
		cartDao.delete(cart);
	}

	public void deleteById(int id) {
		cartDao.deleteById(id);
	}

	public void deleteAll() {
		cartDao.deleteAll();
	}

	public CartDao getCartDao() {
		return cartDao;
	}

	public void setCartDao(CartDaoImpl cartDao) {
		this.cartDao = cartDao;
	}
}
