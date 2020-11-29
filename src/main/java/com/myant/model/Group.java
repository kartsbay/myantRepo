package com.myant.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Group")
public class Group {
	@Id
	String id;
	String name;
	@DBRef
	List<Contact> contacts;

	public Group() {

	}

	@Override
	public String toString() {
		return "Group [id=" + id + ", name=" + name + ", contacts=" + contacts + "]";
	}

	public Group(String name, List<Contact> contacts) {
		this.name = name;
		this.contacts = contacts;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

}
