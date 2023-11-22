package com.sp.desired.java.entities;

public class Employee {

	private int id;

	private String fName;

	private String lName;

	private double salary;

	public Employee(int id, String fName, String lName, double salary) {
		super();
		this.id = id;
		this.fName = fName;
		this.lName = lName;
		this.salary = salary;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

}
