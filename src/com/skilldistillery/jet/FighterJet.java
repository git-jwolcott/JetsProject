package com.skilldistillery.jet;

public class FighterJet extends Jet implements CombatReady{

	public FighterJet(String make, String model, double speed, int range, long price) {
		super(make, model, speed, range, price);
	}

	@Override
	public void fight() {
		System.out.println("Fighting....");
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FighterJet [toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

	
}
