package smartcontactmanager.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import smartcontactmanager.entities.Contact;

public interface ContactRepo extends JpaRepository<Contact, Integer> {
		
	//custom method to get all the contacts of a particular user
	//let us use the Pageable class to use the pagination property
	@Query("from Contact as c where c.user.id=:userId")
	public Page<Contact> getContactsByUser(@Param("userId") int userId,Pageable pageable);
	
	
	
	
	
}
