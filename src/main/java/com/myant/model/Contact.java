package com.myant.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Contact")
public class Contact {
	@Id
	private String id;
	private String firstName;
	private String lastName;
	private String address;
	private String homeTel;
	private String mobileTel;
	private String workTel;

	public Contact() {

	}

	public Contact(String firstName, String lastName, String address, String homeTel, String mobleTel, String workTel) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.homeTel = homeTel;
		this.mobileTel = mobleTel;
		this.workTel = workTel;
	}

	@Override
	public String toString() {
		return "Contact [firstName=" + firstName + ", lastName=" + lastName + ", address=" + address + ", homeTel="
				+ homeTel + ", mobleTel=" + mobileTel + ", workTel=" + workTel + "]";
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHomeTel() {
		return homeTel;
	}

	public void setHomeTel(String homeTel) {
		this.homeTel = homeTel;
	}

	public String getMobileTel() {
		return mobileTel;
	}

	public void setMobileTel(String mobleTel) {
		this.mobileTel = mobleTel;
	}

	public String getWorkTel() {
		return workTel;
	}

	public void setWorkTel(String workTel) {
		this.workTel = workTel;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
