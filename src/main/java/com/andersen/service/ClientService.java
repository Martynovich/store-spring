package com.andersen.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.andersen.domain.Client;
import com.andersen.persistence.ClientDao;
import com.andersen.persistence.ClientDaoImpl;

@Component
public class ClientService implements CrudServise<Client> {

	private static final Logger logger = Logger.getLogger(ClientService.class);

	@Autowired
	private ClientDao clientDao;

	public ClientService() {
	}

	public void create(Client client) {
		logger.debug("ClientServise create");
		clientDao.persist(client);
	}

	public Client findById(int id) {
		logger.debug("ClientServise findById");
		return clientDao.find(id);
	}

	public List<Client> findAll() {
		logger.debug("ClientServise findAll");
		return clientDao.findAll();
	}

	public void update(Client client) {
		logger.debug("ClientServise update");
		clientDao.update(client);
	}
	
	public void delete(Client client) {
		logger.debug("ClientServise delete");
		clientDao.delete(client);
	}

	public void deleteById(int id) {
		logger.debug("ClientServise deleteById");
		clientDao.deleteById(id);
	}

	public void deleteAll() {
		logger.debug("ClientServise deleteAll");
		clientDao.deleteAll();
	}
}
