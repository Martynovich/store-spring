package com.andersen.persistence;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.andersen.domain.Product;

@Component
public class ProductDaoImpl implements ProductDao {

	private static final Logger logger = Logger.getLogger(ProductDaoImpl.class);
	
	public  ProductDaoImpl() {
	}
	
	@Autowired
	SessionFactory sessionFactory;
	
	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Transactional
	public void persist(Product entity) {
		getCurrentSession().save(entity);
	}
	
	@Transactional
	public Product find(int id) {
		return getCurrentSession().get(Product.class, id);
	}
	
	@Transactional
	@SuppressWarnings("unchecked")
	public List<Product> findAll() {
		return (List<Product>)getCurrentSession().createCriteria(Product.class).list();
	}

	@Transactional
	public void update(Product entity) {
		getCurrentSession().update(entity);
	}

	@Transactional
	public void delete(Product entity) {
		getCurrentSession().delete(entity);
	}
	
	@Transactional
	public void deleteById(int id) {
		getCurrentSession().delete(this.find(id));
	}

	@Transactional
	public void deleteAll() {
		List<Product> entityList = findAll();
        for (Product entity : entityList) {
            delete(entity);
        }
	}
}
