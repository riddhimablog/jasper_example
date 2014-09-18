package com.riddima.jasper;

public class User {
	private Long id;
	private String firstName = null;
	private String lastName = null;
	private String fatherName = null;
	private String address = null;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getListDescription() {

		return lastName + "," + firstName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User(String firstName, String lastName, String fatherName,
			String address) {
		// TODO Auto-generated constructor stub
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.fatherName = fatherName;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("User Information \n");
		sb.append("First Name : " + firstName + "\n");
		sb.append("Last Name : " + lastName + "\n");
		sb.append("Father Name : " + fatherName + "\n");
		sb.append("Address : " + address + "\n");
		return sb.toString();
	}

}
