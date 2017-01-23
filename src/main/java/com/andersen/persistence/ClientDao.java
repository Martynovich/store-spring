package com.andersen.persistence;

import java.util.List;

import com.andersen.domain.Client;

public interface ClientDao {

	public void persist(Client entity);

	public Client find(int id);

	public List<Client> findAll();

	public void update(Client entity);

	public void deleteById(int id);

	public void delete(Client entity);

	public void deleteAll();

}
