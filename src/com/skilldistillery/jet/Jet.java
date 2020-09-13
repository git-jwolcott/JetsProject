package com.skilldistillery.jet;

import java.util.List;
import java.util.Scanner;

public abstract class Jet {
	private int jetID = 0;
	private String name;
	private String make;
	private String model;
	private double speed;
	private int range;
	private long price;

	private static int jetIDTracker = 0;

	public Jet(String name, String make, String model, double speed, int range, long price) {
		this.name = name;
		this.make = make;
		this.model = model;
		this.speed = speed;
		this.range = range;
		this.price = price;
		this.jetID = jetIDTracker;

		// increment jetIDTracker
		jetIDTracker++;
	}

	public void fly(Jet j) {
		System.out.println(
				"This jet " + j + " can fly for " + String.format("%.2f", range / speed) + " hours until empty.");
	}

	public double getSpeedInMach(double mph) {
		return mph * 0.006517;
	}

	public int getJetID() {
		return jetID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Jet [jetID=");
		builder.append(jetID);
		builder.append(", name=");
		builder.append(name);
		builder.append(", make=");
		builder.append(make);
		builder.append(", model=");
		builder.append(model);
		builder.append(", speed=");
		builder.append(speed);
		builder.append(", range=");
		builder.append(range);
		builder.append(", price=");
		builder.append(price);
		builder.append("]");
		return builder.toString();
	}

}
