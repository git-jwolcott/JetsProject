package com.skilldistillery.jet;

public class StandardJet extends Jet {

	public StandardJet(String name, String make, String model, double speed, int range, long price) {
		super(name, make, model, speed, range, price);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("StandardJet = ");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

}
