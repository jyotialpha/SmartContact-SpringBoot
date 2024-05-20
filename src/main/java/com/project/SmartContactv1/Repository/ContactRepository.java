package com.project.SmartContactv1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.SmartContactv1.Entity.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact , Integer>{

}
