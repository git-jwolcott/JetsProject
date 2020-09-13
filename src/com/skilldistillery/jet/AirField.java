package com.skilldistillery.jet;

import java.util.*;

public class AirField {
	// create local variables
	// a list to hold which jets are parked in the Air Field
	List<Jet> jList = new ArrayList<>();

	public AirField(List<Jet> jets) {
		jList = jets;
	}

	public List<Jet> getjList() {
		for (Jet jet : jList) {
			System.out.println(jet);
		}
		return jList;
	}

	public void setjList(List<Jet> jList) {
		this.jList = jList;
	}

	public void flyJets() {
		for (Jet jet : jList) {
			jet.fly(jet);
		}
	}

	public void getFastestJet(List<Jet> jets) {
		double fastestSpeed = 0;
		String jDetail = null;
		for (Jet jet : jets) {
			if (jet.getSpeed() > fastestSpeed) {
				fastestSpeed = jet.getSpeed();
				jDetail = jet.toString();
			}
		}
		System.out.println(jDetail + " is the fastest jet.");
	}

	public void getLongestRangeJet(List<Jet> jets) {
		int farthestRange = 0;
		String jDetail = null;
		for (Jet jet : jets) {
			if (jet.getRange() > farthestRange) {
				farthestRange = jet.getRange();
				jDetail = jet.toString();
			}
		}
		System.out.println(jDetail + " is the jet with the longest flight range.");
	}

	public void loadCargoPlanes(List<Jet> jets) {
		for (Jet jet : jets) {
			if (jet.getClass().getSimpleName().equals("CargoPlane")) {
				((CargoPlane) jet).loadCargo();
			}
		}
	}

	public void sendFighterJetsIntoAction(List<Jet> jets) {
		for (Jet jet : jets) {
			if (jet.getClass().getSimpleName().equals("FighterJet")) {
				((FighterJet) jet).fight();
			}
		}
	}

	public void addNewJet(Jet j) {
		jList.add(j);
	}

	public void removeJet(Scanner userInput, List<Jet> jets) {
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

		Iterator<Jet> it = jets.iterator();
		while (it.hasNext()) {
			Jet j = it.next();
			if (j.getJetID() == jetID) {
				it.remove();
				jList.remove(j);
			}
		}
		userInput.nextLine();
	}

}
