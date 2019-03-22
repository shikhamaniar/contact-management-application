package com.emxcel.contactmanagementapplication.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.emxcel.contactmanagementapplication.entity.Contact;
import com.emxcel.contactmanagementapplication.exception.ContactNotFoundException;
import com.emxcel.contactmanagementapplication.repository.ContactRepository;

@Service
public class ContactService {
	@Autowired
	private ContactRepository contactRepository;

	// view
	public List<Contact> getAllContacts() {
		List<Contact> contacts = new ArrayList<>();
		contactRepository.findAll().forEach(contacts::add);
		return contacts;
	}

	// add
	public Contact addContact(Contact contact) {
		contactRepository.save(contact);
		return contact;
	}

	// update
	public void updateContact(long id, Contact contact) {
		contact.setId(id);
		contactRepository.save(contact);
	}

	// delete
	public void deleteContactById(long id) {
		contactRepository.deleteById(id);
	}

	// Search Id
	public Contact getById(long id) {
	Contact contact = contactRepository.findById(id).orElseThrow(()-> new ContactNotFoundException("Id-" + id));

		return contact;
	}

	// view-page
	public List<Contact> getAllContactPaginated(Pageable pageable) {
		return contactRepository.findAll(pageable);
	}

	// first name-view All
	public List<Contact> getByFirstName(String firstName) {
		List<Contact> contact = contactRepository.findByFirstNameIgnoreCase(firstName);
		if (contact == null)
			throw new ContactNotFoundException("FirstName-" + firstName);
		return contact;
	}

	// first name-view PAGE
	public List<Contact> getByFirstName(String firstName, Pageable pageable) {
		List<Contact> contact = contactRepository.findByFirstNameIgnoreCase(firstName, pageable);
		if (contact == null)
			throw new ContactNotFoundException("FirstName-" + firstName);
		return contact;
	}

	// last name-view All
	public List<Contact> getByLastName(String lastName) {
		List<Contact> contact = contactRepository.findByLastNameIgnoreCase(lastName);
		if (contact == null)
			throw new ContactNotFoundException("LastName-" + lastName);
		return contact;
	}

	// last name-view PAGE
	public List<Contact> getByLastName(String lastName, Pageable pageable) {
		List<Contact> contact = contactRepository.findByLastNameIgnoreCase(lastName, pageable);
		if (contact == null)
			throw new ContactNotFoundException("LastName-" + lastName);
		return contact;
	}

}
