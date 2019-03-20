package com.emxcel.contactmanagementapplication.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.emxcel.contactmanagementapplication.entity.Contact;
import com.emxcel.contactmanagementapplication.entity.ContactList;
import com.emxcel.contactmanagementapplication.service.ContactService;

@RestController
@RequestMapping("/ContactManagementApplication/contacts")
public class ContactController {

	@Autowired
	private ContactService contactService;

	@GetMapping
	public HttpEntity<ContactList> getAllContacts(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size,
			@RequestParam(value = "firstName", required = false) String firstName,
			@RequestParam(value = "lastName", required = false) String lastName) {
		ContactList contact = new ContactList();

		if (page != null && size != null && page >= 0 && size > 0) {
			if (firstName != null) {// result of firstName with pagination
				contact.setContacts(contactService.getByFirstName(firstName, PageRequest.of(page, size)));
				return new ResponseEntity<>(contact, HttpStatus.OK);
			} else if (lastName != null) {// result of lastName with pagination
				contact.setContacts(contactService.getByLastName(lastName, PageRequest.of(page, size)));
				return new ResponseEntity<>(contact, HttpStatus.OK);
			} else {// only pagination
				contact.setContacts(contactService.getAllContactPaginated(PageRequest.of(page, size)));
				return new ResponseEntity<>(contact, HttpStatus.OK);
			}
		}
		// first name search
		if (firstName != null) {
			contact.setContacts(contactService.getByFirstName(firstName));
			return new ResponseEntity<>(contact, HttpStatus.OK);
		}
		// last name search
		if (lastName != null) {
			contact.setContacts(contactService.getByLastName(lastName));
			return new ResponseEntity<>(contact, HttpStatus.OK);
		}
		// without pagination
		contact.setContacts(contactService.getAllContacts());
		return new ResponseEntity<>(contact, HttpStatus.OK);

	}

	@GetMapping("/{id}")
	public HttpEntity<Contact> getContactById(@PathVariable long id) {

		Contact contact = contactService.getById(id);
		return new ResponseEntity<>(contact, HttpStatus.OK);
	}

	@PostMapping()
	public HttpEntity<Contact> addContact(@RequestBody Contact contact) {
		Contact savedContact = contactService.addContact(contact);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedContact.getId()).toUri();

		return ResponseEntity.created(location).build();
	}

	@PutMapping("/{id}")
	public HttpEntity<Contact> updateName(@RequestBody Contact contact, @PathVariable long id) {
		if (contactService.getById(id) == null)
			return ResponseEntity.notFound().build();
		contactService.updateContact(id, contact);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	public HttpEntity<Contact> deleteName(@PathVariable long id) {
		if (contactService.getById(id) == null)
			return ResponseEntity.notFound().build();
		contactService.deleteContactById(id);
		return ResponseEntity.noContent().build();
	}
}
//jdbc:h2:mem:testdb
//{id": 1,"profilepicture":"C:\\Users\\Admin\\Desktop\\IMG_20170205_110803_840.jpg","firstName":"Shikha","middleName":"Ashish","lastName":"Maniar","number1":"8732434377","number2":"+918768375662","number3":"","email":"xya@yahoo.com","location":"ahmedabad"}