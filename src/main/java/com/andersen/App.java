package com.andersen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.andersen.service.CLICartService;
import com.andersen.service.CLIClientService;
import com.andersen.service.CLICrudService;
import com.andersen.service.CLIProductService;

@Component
public class App {

	private static final Logger logger = Logger.getLogger(App.class);

	private CLICrudService tableServise;
	private String userInput;
	private String tableName;
	private String currentSelecting = "table";

	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	@Autowired
	private CLIClientService cliClientService;

	@Autowired
	private CLIProductService cliProductService;

	@Autowired
	private CLICartService cliCartService;

	public static final String EXIT = "exit";
	public static final String CONTINUE = "cont";

	public static void main(String[] args) throws IOException {
		ApplicationContext context = new ClassPathXmlApplicationContext("store-spring.xml");
		App app = (App)context.getBean("app");
		app.start();
		
	}

	public void start() {
		logger.info("Hello.");
		tableSelecting();
		currentSelecting = "action";
		commandSelecting();
	}

	private void tableSelecting() {
		int rawNumber = 0;
		logger.info("Please select the table.");
		logger.info("Enter the table number for select.");
		logger.info("1 - Client");
		logger.info("2 - Product.");
		logger.info("3 - Cart.");
		try {
			rawNumber = inputHandler(new String[] { "1", "2", "3" });
		} catch (IOException e) {
			logger.error(e);
		}
		switch (rawNumber) {
		case 1:
			tableServise = cliClientService;
			tableName = "client";
			break;
		case 2:
			tableServise = cliProductService;
			tableName = "product";
			break;
		case 3:
			tableServise = cliCartService ;
			tableName = "cart";
			break;
		default:
			System.exit(0);
		}
	}

	private void commandSelecting() {
		int rawNumber = 0;
		logger.info("Please select an action.");
		logger.info("Enter the action number for select.");
		logger.info("1 - Create new " + tableName + ".");
		logger.info("2 - Find " + tableName + " by id.");
		logger.info("3 - Find all " + tableName + "s.");
		logger.info("4 - Update " + tableName + ".");
		logger.info("5 - Delete " + tableName + " by id.");
		logger.info("6 - Delete all " + tableName + "s.");
		try {
			rawNumber = inputHandler(new String[] { "1", "2", "3", "4", "5", "6" });
		} catch (IOException e) {
			logger.error(e);
		}
		switch (rawNumber) {
		case 1:
			tableServise.create();
			break;
		case 2:
			tableServise.findById();
			break;
		case 3:
			tableServise.findAll();
			break;
		case 4:
			tableServise.update();
			break;
		case 5:
			tableServise.deleteById();
			break;
		case 6:
			tableServise.deleteAll();
			break;
		default:
			System.exit(0);
		}
	}

	private Integer inputHandler(String[] raws) throws IOException {
		ArrayList<String> rawsArray = new ArrayList<String>(Arrays.asList(raws));
		userInput = reader.readLine();
		while (!rawsArray.contains(userInput)) {
			logger.info("Incorrect input. Please select the " + currentSelecting + " number.");
			logger.info("For exit enter - exit.");
			userInput = reader.readLine();
			if (userInput.equals(EXIT)) {
				System.exit(0);
			}
		}
		return Integer.parseInt(userInput);
	}

	public CLICrudService getCliClientService() {
		return cliClientService;
	}

	public void setCliClientService(CLIClientService cliClientService) {
		this.cliClientService = cliClientService;
	}

	public CLICrudService getCliProductService() {
		return cliProductService;
	}

	public void setCliProductService(CLIProductService cliProductService) {
		this.cliProductService = cliProductService;
	}

	public CLICrudService getCliCartService() {
		return cliCartService;
	}

	public void setCliCartService(CLICartService cliCartService) {
		this.cliCartService = cliCartService;
	}
	
	
}
