package com.skilldistillery.jet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JetsApplication {
	private AirField aField;

	public static void main(String[] args) {

		// create Scanner for user input
		Scanner userInput = new Scanner(System.in);
		// create an instance of this app
		JetsApplication jetApp = new JetsApplication();
		// call run method to kick of the project
		jetApp.run(userInput);
	}

	// create run method
	private void run(Scanner userInput) {
		List<Jet> jets = new ArrayList<>();
//		jets.addAll(
		parseFile(jets);
		showMenu(userInput, jets);
	}

	// create menu method

	// create parseFile method
	private void parseFile(List<Jet> jets) {
		// from a file populate the AirField with 5 Jet of different kinds
		try (BufferedReader bufIn = new BufferedReader(new FileReader("jets.txt"))) {
			String line;
			while ((line = bufIn.readLine()) != null) {
				String[] fields = line.split(",");
				if (fields[0].contains("StandardJet")) {
					jets.add(new StandardJet(fields[1], fields[2], fields[3], Double.parseDouble(fields[4]),
							Integer.parseInt(fields[5]), Long.parseLong(fields[6])));
				} else if (fields[0].contains("CargoPlane")) {
					jets.add(new CargoPlane(fields[1], fields[2], fields[3], Double.parseDouble(fields[4]),
							Integer.parseInt(fields[5]), Long.parseLong(fields[6])));
				} else if (fields[0].contains("FighterJet")) {
					jets.add(new FighterJet(fields[1], fields[2], fields[3], Double.parseDouble(fields[4]),
							Integer.parseInt(fields[5]), Long.parseLong(fields[6])));
				}
			}
		} catch (IOException e) {
			System.err.println(e);
		}
		aField = new AirField(jets);

	}

	// show menu: list all jets, fly all jets, view fasted jet, view jet with
	// longest range, load cargo planes, fight with FighterJet, Add to Jet fleet,
	// decommission a jet from fleet, and quit program
	// keep showing menu until quit
	private void showMenu(Scanner userInput, List<Jet> jets) {
		System.out.println();
		System.out.println("==================== MENU ====================");
		System.out.println("|                                            |");
		System.out.println("|  1. List jet fleet.                        |");
		System.out.println("|  2. Fly jet fleet.                         |");
		System.out.println("|  3. Display fastest jet in fleet.          |");
		System.out.println("|  4. Display longest range jet in fleet.    |");
		System.out.println("|  5. Load cargo into cargo jets.            |");
		System.out.println("|  6. Send fighter jets into action.         |");
		System.out.println("|  7. Add jet to fleet.                      |");
		System.out.println("|  8. Decommission a jet from fleet.         |");
		System.out.println("|  9. Quit the program.                      |");
		System.out.println("|                                            |");
		System.out.println("==============================================");
		System.out.println();
		menuAction(userInput, jets);
	}

	// menu selection action
	private void menuAction(Scanner userInput, List<Jet> jets) {
		int menuSelection = Integer.parseInt(userInput.nextLine());
		boolean keepGoing = true;
		while (keepGoing) {
			switch (menuSelection) {
			// show all jets in fleet
			case 1:
				// get all jets
				aField.getjList();
				showMenu(userInput, jets);
				break;
			// fly all jets
			case 2:
				aField.flyJets();
				showMenu(userInput, jets);
				break;
			// find fastest jet in fleet
			case 3:
				aField.getFastestJet(jets);
				showMenu(userInput, jets);
				break;
			// find jet with longest range
			case 4:
				aField.getLongestRangeJet(jets);
				showMenu(userInput, jets);
				break;
			// load all cargo planes
			case 5:
				// find the cargo planes
				aField.loadCargoPlanes(jets);
				showMenu(userInput, jets);
				break;
			// send all fighter jets into action
			case 6:
				// find the fighter jets
				aField.sendFighterJetsIntoAction(jets);
				showMenu(userInput, jets);
				break;
			// add a jet to the fleet and air field
			case 7:
				addJet(userInput, jets);
				break;
			// remove a jet from the fleet and the air field
			case 8:
				aField.removeJet(userInput, jets);
				showMenu(userInput, jets);
				break;
			// quit the application
			case 9:
				System.out.println("You opted to quit. Have a nice day! Goodbye!");
				keepGoing = false;
				System.exit(0);
				break;
			default:
				System.out.println("Invalid selection. Please enter a number 1 - 9.");
				break;
			}
		}
	}

	private void addJet(Scanner userInput, List<Jet> jets) {
		System.out.println("What type of Jet will we be adding to the fleet?");
		System.out.println("Enter the appropriate number.");
		showJetTypeMenu(userInput, jets);
	}

	private void showJetTypeMenu(Scanner userInput, List<Jet> jets) {
		System.out.println();
		System.out.println("======== MENU ========");
		System.out.println("|                    |");
		System.out.println("|  1. Standard Jet   |");
		System.out.println("|  2. Cargo Plane    |");
		System.out.println("|  3. Fighter Jet    |");
		System.out.println("|                    |");
		System.out.println("======================");
		System.out.println();

		getNewJetDetails(userInput, jets);
	}

	private void getNewJetDetails(Scanner userInput, List<Jet> jets) {
		int jetType = Integer.parseInt(userInput.nextLine());
		if (jetType == 1 || jetType == 2 || jetType == 3) {

			System.out.println("Enter the jet's name: ");
			String name = userInput.nextLine();
			System.out.println("Enter the jet's make: ");
			String make = userInput.nextLine();
			System.out.println("Enter the jet's model: ");
			String model = userInput.nextLine();
			System.out.println("Enter the jet's speed: ");
			double speed = userInput.nextDouble();
			System.out.println("Enter the jet's range: ");
			int range = userInput.nextInt();
			System.out.println("Enter the jet's price: ");
			long price = userInput.nextLong();

			jetTypeMenuAction(userInput, jetType, name, make, model, speed, range, price, jets);
		} else {
			System.out.println("Invalid selection. Please enter a number 1 - 3.");
			showJetTypeMenu(userInput, jets);
		}
	}

	private void jetTypeMenuAction(Scanner userInput, int jetType, String name, String make, String model, double speed,
			int range, long price, List<Jet> jets) {
		switch (jetType) {
		case 1:
			// create a standard jet
			Jet j = new StandardJet(name, make, model, speed, range, price);
			aField.addNewJet(j);
			userInput.nextLine();
			showMenu(userInput, jets);
			break;
		case 2:
			Jet j1 = new CargoPlane(name, make, model, speed, range, price);
			aField.addNewJet(j1);
			userInput.nextLine();
			showMenu(userInput, jets);
			break;
		case 3:
			Jet j2 = new FighterJet(name, make, model, speed, range, price);
			aField.addNewJet(j2);
			userInput.nextLine();
			showMenu(userInput, jets);
			break;
		default:
			break;
		}
	}

}
