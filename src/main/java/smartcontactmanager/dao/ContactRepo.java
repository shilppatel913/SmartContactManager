package smartcontactmanager.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import smartcontactmanager.entities.Contact;

public interface ContactRepo extends JpaRepository<Contact, Integer> {
		
	//custom method to get all the contacts of a particular user
	@Query("from Contact as c where c.user.id=:userId")
	public List<Contact> getContactsByUser(@Param("userId") int userId);
	
	
	
	
	//we will implement pagination here
}
