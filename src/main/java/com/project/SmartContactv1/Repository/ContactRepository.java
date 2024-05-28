package com.project.SmartContactv1.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.SmartContactv1.Entity.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {

	@Query("from Contact as c where c.user.id =:userid")
	public List<Contact> findByUserId(@Param("userid") int userId);

}
