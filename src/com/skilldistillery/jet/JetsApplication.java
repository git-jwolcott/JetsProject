package com.skilldistillery.jet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
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
					jets.add(new StandardJet(fields[1], fields[2], Double.parseDouble(fields[3]),
							Integer.parseInt(fields[4]), Long.parseLong(fields[5])));
				} else if (fields[0].contains("CargoPlane")) {
					jets.add(new CargoPlane(fields[1], fields[2], Double.parseDouble(fields[3]),
							Integer.parseInt(fields[4]), Long.parseLong(fields[5])));
				} else if (fields[0].contains("FighterJet")) {
					jets.add(new FighterJet(fields[1], fields[2], Double.parseDouble(fields[3]),
							Integer.parseInt(fields[4]), Long.parseLong(fields[5])));
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
		String menuSelection = userInput.next();
		boolean keepGoing = true;
		while (keepGoing) {
			switch (menuSelection) {
			case "1":
				// get all jets
				userInput.nextLine();
				aField.getjList();
				showMenu(userInput, jets);
				break;
			case "2":
				userInput.nextLine();
				for (Jet jet : jets) {
					jet.fly(jet);
				}
				showMenu(userInput, jets);
				break;
			case "3":
				userInput.nextLine();
				getFastestJet(userInput, jets);
				break;
			case "4":
				userInput.nextLine();
				getLongestRangeJet(userInput, jets);
				break;
			case "5":
				userInput.nextLine();
				for (Jet jet : jets) {
					if (jet.getClass().getSimpleName().equals("CargoPlane")) {
						((CargoPlane) jet).loadCargo();
					}
				}
				showMenu(userInput, jets);
				break;
			case "6":
				userInput.nextLine();
				for (Jet jet : jets) {
					if (jet.getClass().getSimpleName().equals("FighterJet")) {
						((FighterJet) jet).fight();
					}
				}
				showMenu(userInput, jets);
				break;
			case "7":
				userInput.nextLine();
				addJet(userInput, jets);
				break;
			case "8":
				userInput.nextLine();
				removeJet(userInput, jets);
				break;
			case "9":
				userInput.nextLine();
				System.out.println("You opted to quit. Have a nice day! Goodbye!");
				keepGoing = false;
				System.exit(0);
				break;
			default:
				System.out.println("Invalid selection. Please enter a number 1 - 4.");
				break;
			}
		}
	}

	private void getFastestJet(Scanner userInput, List<Jet> jets) {
		double fastestSpeed = 0;
		String jDetail = null;
			for (Jet jet : jets) {
				if (jet.getSpeed() > fastestSpeed) {
					fastestSpeed = jet.getSpeed();
					jDetail = jet.toString();
				}
			}
		System.out.println(jDetail + " is the fastest jet.");
		showMenu(userInput, jets);
	}

	private void getLongestRangeJet(Scanner userInput, List<Jet> jets) {
		int farthestRange = 0;
		String jDetail = null;
			for (Jet jet : jets) {
				if (jet.getRange() > farthestRange) {
					farthestRange = jet.getRange();
					jDetail = jet.toString();
				}
			}
		System.out.println(jDetail + " is the jet with the longest flight range.");
		showMenu(userInput, jets);
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

	private void jetTypeMenuAction(Scanner userInput, int jetType, String make, String model, double speed, int range, long price, List<Jet> jets ) {
			switch (jetType) {
			case 1:
				// create a standard jet
				Jet j = new StandardJet(make, model, speed, range, price);
				aField.addJets(j);
				showMenu(userInput, jets);
				break;
			case 2:
				Jet j1 =new CargoPlane(make, model, speed, range, price);
				aField.addJets(j1);
				showMenu(userInput, jets);
				break;
			case 3:
				Jet j2 = new FighterJet(make, model, speed, range, price);
				aField.addJets(j2);
				showMenu(userInput, jets);
				break;
			default:
				break;
			}
	}
	
	private void getNewJetDetails(Scanner userInput, List<Jet> jets) {
		int jetType = userInput.nextInt();
		if (jetType == 1 || jetType == 2 || jetType == 3) {
		String make;
		String model;
		double speed;
		int range;
		long price;
		
		System.out.println("Enter the jet's make: ");
		make = userInput.nextLine();
		userInput.nextLine();
		System.out.println("Enter the jet's model: ");
		model = userInput.nextLine();
		System.out.println("Enter the jet's speed: ");
		speed = userInput.nextDouble();
		System.out.println("Enter the jet's range: ");
		range = userInput.nextInt();
		System.out.println("Enter the jet's price: ");
		price = userInput.nextLong();
		
		jetTypeMenuAction(userInput, jetType, make, model, speed, range, price, jets);
		}
		else {
			System.out.println("Invalid selection. Please enter a number 1 - 3.");
			showJetTypeMenu(userInput ,jets);
		}
	}

	private void removeJet(Scanner userInput, List<Jet> jets) {
		System.out.println("You selected to remove a jet from the fleet. Here's the fleet: ");
		System.out.println();
		System.out.println("======== Fleet ========");
		System.out.println("|                    ");
		for (Jet jet : jets) {
			System.out.println("|  " + jet + "   ");
			System.out.println();
		}
		System.out.println("|                    ");
		System.out.println("======================");
		System.out.println();
		System.out.print("Which jet would you like to remove from the fleet? Enter the appropriate jetID: ");
		int jetID = userInput.nextInt();
		
//		for (Jet jet : jets) {
//			if(jet.getJetID() == jetID) {
//				jets.remove(jet);
//				aField.removeJet(jet);
//			}
//		}
//		Jet[] jArr = (Jet[]) jets.toArray();
//		for (Jet jet : jArr) {
//			if(((Jet) jet).getJetID() == jetID) {
////				Jet j = new Jet(jet.getJetID(), jet.getMake(), jet.getModel(), jet.getSpeed(), jet.getRange(), jet.getPrice());
//				jArr.remove(jet);
//			}
//			
//		}{
//		}
//		
		Iterator<Jet> it = jets.iterator();
		while(it.hasNext()) {
			Jet j = it.next();
			if(j.getJetID() == jetID) {
				it.remove();
				aField.removeJet(j);
			}
		}
		showMenu(userInput, jets);		
	}
	
}
