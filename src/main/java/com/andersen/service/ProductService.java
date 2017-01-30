
package com.andersen.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.andersen.domain.Product;
import com.andersen.persistence.ProductDao;
import com.andersen.persistence.ProductDaoImpl;

@Component
public class ProductService implements CrudServise<Product> {

	private final static Logger logger = Logger.getLogger(ProductService.class);

	@Autowired
	private ProductDao productDao;

	public ProductService() {
	}

	public void create(Product product) {
		logger.debug("ProductServise create");
		productDao.persist(product);

	}

	public Product findById(int id) {
		return productDao.find(id);
	}

	public List<Product> findAll() {
		return productDao.findAll();
	}

	public void update(Product product) {
		productDao.update(product);
	}

	public void delete(Product product) {
		productDao.delete(product);

	}

	public void deleteById(int id) {
		productDao.deleteById(id);

	}

	public void deleteAll() {
		productDao.deleteAll();
	}
}
