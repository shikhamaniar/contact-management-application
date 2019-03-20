package com.emxcel.contactmanagementapplication.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.emxcel.contactmanagementapplication.entity.Contact;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Long> {
	List<Contact> findByFirstNameIgnoreCase(String firstName);

	List<Contact> findByFirstNameIgnoreCase(String firstName, Pageable pageable);

	List<Contact> findAll(Pageable pageable);

	List<Contact> findByLastNameIgnoreCase(String lastName, Pageable pageable);

	List<Contact> findByLastNameIgnoreCase(String lastName);

}
