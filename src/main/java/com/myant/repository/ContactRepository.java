package com.myant.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.myant.model.Contact;

public interface ContactRepository extends MongoRepository<Contact, String> {
	Optional<Contact> findByFirstName(String firstName);

	Optional<Contact> findByLastName(String lastName);

	List<Contact> findByAddress(String address);

	Optional<Contact> findByHomeTel(String homeTel);

	Optional<Contact> findByMobileTel(String mobileTel);

	Optional<Contact> findByWorkTel(String workTel);
}
