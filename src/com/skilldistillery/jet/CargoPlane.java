package com.skilldistillery.jet;

public class CargoPlane extends Jet implements CargoCarrier{

	public CargoPlane(String make, String model, double speed, int range, long price) {
		super(make, model, speed, range, price);
	}

	public void loadCargo() {
		System.out.println("Loading cargo.");
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CargoPlane  = ");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}
	

	
	
	
}
