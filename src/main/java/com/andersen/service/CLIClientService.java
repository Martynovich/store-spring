package com.andersen.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.andersen.domain.Client;

@Component
public class CLIClientService implements CLICrudService {
	private static final Logger logger = Logger.getLogger(CLIClientService.class);

	@Autowired
	private ClientService clientServise;
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private String userInput;

	public CLIClientService() {
	}

	public void create() {
		logger.info("Start creating client.");
		logger.info("Enter clientname.");
		logger.info("For exit enter - exit.");
		try {
			userInput = reader.readLine();
			StoreUtil.isExit(userInput);
			clientServise.create(new Client(userInput));
		} catch (Exception e) {
			logger.error("Login is already exist.");
			logger.info("This login is already exist. Try again");
			this.create();
		}
		logger.info("Client is added.");
		StoreUtil.contOrExit();
	}

	public void findById() {
		logger.info("Start finding by id client.");
		Client client = clientIdInput();
		logger.info("Client id - " + client.getId() + " client login - " + client.getLogin());
		StoreUtil.contOrExit();
	}

	public void findAll() {
		logger.info("Start finding all clients.");
		List<Client> clients = clientServise.findAll();
		if (clients.isEmpty()) {
			logger.info("No clients.");
		}
		for (Client client : clients) {
			logger.info("Client id - " + client.getId() + " client login - " + client.getLogin());
		}
		StoreUtil.contOrExit();
	}

	public void update() {
		logger.info("Start updating client.");
		Client client = clientIdInput();
		while (true) {
			logger.info("Enter new login");
			logger.info("For exit enter - exit.");
			try {
				userInput = reader.readLine();
				StoreUtil.isExit(userInput);
				client.setLogin(userInput);
				clientServise.update(client);
			} catch (Exception e) {
				logger.error("Login is already exist.");
				logger.info("This login is already exist. Try again");
				continue;
			}
			break;
		}
		logger.info("Client updated");
		StoreUtil.contOrExit();
	}

	public void deleteById() {
		logger.info("Start deleting by id client.");
		Client client = clientIdInput();
		clientServise.delete(client);
		logger.info("Client deleted.");
		StoreUtil.contOrExit();
	}

	public void deleteAll() {
		logger.info("Start deleting all clients.");
		List<Client> clientList = clientServise.findAll();
		for (Client client : clientList) {
			try {
				clientServise.delete(client);
			} catch (Exception e) {
				logger.error("Can't delete client.");
			}
		}
		logger.error("Clients deleted.");
		StoreUtil.contOrExit();
	}

	Client clientIdInput() {
		Client client = null;
		int id;
		while (true) {
			logger.info("Enter client id.\nFor exit enter - exit.");
			try {
				userInput = reader.readLine();
				StoreUtil.isExit(userInput);
				id = Integer.parseInt(userInput);
				client = clientServise.findById(id);
				if (client == null) {
					logger.info("Client with this id does not exist");
					continue;
				}
			} catch (NumberFormatException e) {
				logger.error("Incorect input. Need to enter number.");
				logger.info("Incorrect input. Please enter number");
				continue;
			} catch (IOException e) {
				logger.error(e);
			}
			break;
		}
		return client;
	}

	public ClientService getClientServise() {
		return clientServise;
	}

	public void setClientServise(ClientService clientServise) {
		this.clientServise = clientServise;
	}

}
