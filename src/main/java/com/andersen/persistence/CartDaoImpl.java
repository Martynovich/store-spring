package com.andersen.persistence;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.andersen.domain.Cart;

@Component
public class CartDaoImpl implements CartDao {

	private static final Logger logger = Logger.getLogger(CartDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public CartDaoImpl(){
	}
	
	private Session getcurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Transactional
	public void persist(Cart entity) {
		getcurrentSession().save(entity);
	}

	@Transactional
	public Cart find(int id) {
		return getcurrentSession().get(Cart.class, id);
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public List<Cart> findAll() {
		return getcurrentSession().createCriteria(Cart.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	}

	@Transactional
	public void update(Cart entity) {
		getcurrentSession().update(entity);
	}

	@Transactional
	public void delete(Cart entity) {
		getcurrentSession().delete(entity);
	}

	@Transactional
	public void deleteById(int id) {
		getcurrentSession().delete(this.find(id));
	}

	@Transactional
	public void deleteAll() {
		List<Cart> entityList = findAll();
		for (Cart entity : entityList) {
			delete(entity);
		}
	}
}
