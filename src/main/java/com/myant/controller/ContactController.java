package com.myant.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myant.model.Contact;
import com.myant.model.Group;
import com.myant.repository.ContactRepository;
import com.myant.repository.GroupRepository;

@RestController
@RequestMapping("/")
public class ContactController {
	@Autowired
	ContactRepository contactRepository;

	@Autowired
	GroupRepository groupRepository;

	@GetMapping("/myant")
	public String hello() {
		return "MYANT Assignment";
	}

	@GetMapping("/contacts")
	public ResponseEntity<List<Contact>> getAllContacts() {
		List<Contact> contacts = new ArrayList<Contact>();
		contactRepository.findAll().forEach(contacts::add);
		return new ResponseEntity<>(contacts, HttpStatus.OK);

	}

	@GetMapping("/contactbyFirstName/{firstName}")
	public ResponseEntity<Contact> getContactByFirstName(@PathVariable("firstName") String firstName) {
		Optional<Contact> contactData = contactRepository.findByFirstName(firstName);

		if (contactData.isPresent()) {
			return new ResponseEntity<>(contactData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/contactbyLastName/{lastName}")
	public ResponseEntity<Contact> getContactByLastName(@PathVariable("lastName") String lastName) {
		Optional<Contact> contactData = contactRepository.findByLastName(lastName);
		if (contactData.isPresent()) {
			return new ResponseEntity<>(contactData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/contactbyAddress/{address}")
	public ResponseEntity<Contact> getContactByAddress(@PathVariable("address") String address) {
		List<Contact> contactData = contactRepository.findByAddress(address);
		if (contactData.size() == 0) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(contactData.get(0), HttpStatus.OK);
		}

	}

	@GetMapping("/contactbyHomeTel/{homeTel}")
	public ResponseEntity<Contact> getContactByHomeTel(@PathVariable("homeTel") String homeTel) {
		Optional<Contact> contactData = contactRepository.findByHomeTel(homeTel);

		if (contactData.isPresent()) {
			return new ResponseEntity<>(contactData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/contactbyWorkTel/{workTel}")
	public ResponseEntity<Contact> getContactByWorkTel(@PathVariable("workTel") String workTel) {
		Optional<Contact> contactData = contactRepository.findByWorkTel(workTel);

		if (contactData.isPresent()) {
			return new ResponseEntity<>(contactData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/contactbyMobileTel/{mobileTel}")
	public ResponseEntity<Contact> getContactByMobileTel(@PathVariable("mobileTel") String mobileTel) {
		Optional<Contact> contactData = contactRepository.findByMobileTel(mobileTel);

		if (contactData.isPresent()) {
			return new ResponseEntity<>(contactData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/contacts")
	public ResponseEntity<Contact> createContact(@RequestBody Contact contact) {
		try {
			if(Objects.isNull(contact.getFirstName()) || Objects.isNull(contact.getLastName()) || Objects.isNull(contact.getAddress())) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			Contact _contact = contactRepository.save(new Contact(contact.getFirstName(), contact.getLastName(),
					contact.getAddress(), contact.getHomeTel(), contact.getMobileTel(), contact.getWorkTel()));
			return new ResponseEntity<>(_contact, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@DeleteMapping("/contacts/{id}")
	public ResponseEntity<Contact> deleteContact(@PathVariable("id") String id) {
		try {
			contactRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/group")
	public ResponseEntity<Group> createGroup(@RequestBody Group group) {
		try {
			List<Contact> _contacts = group.getContacts();
			List<Contact> contactsFromDB = new ArrayList<Contact>();
			for (Contact c : _contacts) {
				Optional<Contact> cDB = contactRepository.findByFirstName(c.getFirstName());
				if (!cDB.isPresent()) {
					contactsFromDB.add(contactRepository.save(c));
				} else {
					contactsFromDB.add(cDB.get());
				}
			}
			Group _group = groupRepository.save(new Group(group.getName(), contactsFromDB));
			return new ResponseEntity<>(_group, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/group")
	public ResponseEntity<List<Group>> getAllGroups() {
		List<Group> groups = new ArrayList<Group>();
		groupRepository.findAll().forEach(groups::add);
		return new ResponseEntity<>(groups, HttpStatus.OK);

	}

	@DeleteMapping("/group/{id}")
	public ResponseEntity<Group> deleteGroup(@PathVariable("id") String id) {
		try {
			groupRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
