package com.skilldistillery.jet;

public class FighterJet extends Jet implements CombatReady {

	public FighterJet(String name, String make, String model, double speed, int range, long price) {
		super(name, make, model, speed, range, price);
	}

	@Override
	public void fight() {
		System.out.println("I feel the need -- the need for speed!");
		System.out.println("Fighting....");
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FighterJet  = ");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

}
