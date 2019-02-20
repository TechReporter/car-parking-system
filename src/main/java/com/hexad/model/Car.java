/**
 * 
 */
package com.hexad.model;

/**
 * @author Tanmoy Dasgupta
 */
public class Car {
	
	private String registrationNumber;
	private String color;
	
	public Car(String registrationNumber, String color) {
		super();
		this.registrationNumber = registrationNumber;
		this.color = color;
	}
	
	public String getRegistrationNumber() {
		return registrationNumber;
	}
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "Car [registrationNumber=" + registrationNumber + ", color=" + color + "]";
	}
	
}
