package com.andersen.persistence;

import java.util.List;

import com.andersen.domain.Client;

public interface ClientDao {

	void persist(Client entity);

	Client find(int id);

	List<Client> findAll();

	void update(Client entity);

	void deleteById(int id);

	void delete(Client entity);

	void deleteAll();

}
