package com.web_project.hoodies.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.web_project.hoodies.model.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
