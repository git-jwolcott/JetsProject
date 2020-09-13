package com.skilldistillery.jet;

import java.util.*;

public class AirField {
	//create local variables
	//list to hold which jets are parked in the Air Field
	List<Jet> jList = new ArrayList<>();
	
	public AirField(List<Jet> jets) {
		jList = jets;
	}

	public void addJets(Jet j) {
			jList.add(j);
		}
	
	public void removeJet(Jet j) {
		jList.remove(j);
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
