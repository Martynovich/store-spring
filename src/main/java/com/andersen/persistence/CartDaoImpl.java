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
	
	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Transactional
	public void persist(Cart entity) {
		getCurrentSession().save(entity);
	}

	@Transactional
	public Cart find(int id) {
		return getCurrentSession().get(Cart.class, id);
	}

	@Transactional
	public List<Cart> findAll() {
		return getCurrentSession().createCriteria(Cart.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	}

	@Transactional
	public void update(Cart entity) {
		getCurrentSession().update(entity);
	}

	@Transactional
	public void delete(Cart entity) {
		getCurrentSession().delete(entity);
	}

	@Transactional
	public void deleteById(int id) {
		getCurrentSession().delete(this.find(id));
	}

	@Transactional
	public void deleteAll() {
		List<Cart> entityList = findAll();
		for (Cart entity : entityList) {
			delete(entity);
		}
	}
}
