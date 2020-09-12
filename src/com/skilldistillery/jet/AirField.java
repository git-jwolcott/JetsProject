package com.skilldistillery.jet;

import java.util.*;

public class AirField {
	//create local variables
	//max number of jets allowed in the airfield
//	private static final int MAX_NUMBER_OF_JETS = 20;
	//list to hold which jets are parked in the Air Field
	List<Jet> jList = new ArrayList<>();
	
	public AirField(List<Jet> jets) {
		jList = jets;
	}

	public void addJets(List<Jet> jets) {
		for (Jet jet : jets) {
			jList.add(jet);
		}
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
	
}
