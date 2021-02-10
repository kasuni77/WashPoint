package com.washpoint.model;

public class Employee extends User{
	private String position;

	//Costructor
	public Employee() {
		super();
	}
	
	public Employee(String userId, String password, String fName, String lName, String email, String mobile,
			String gender, String nic, String profilePic, String registeredDate, String position) {
		super(userId, password, fName, lName, email, mobile, gender, nic, profilePic, registeredDate);
		this.position = position;
	}

	//Setters and Getters
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
}
