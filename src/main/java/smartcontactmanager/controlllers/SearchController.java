package smartcontactmanager.controlllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import smartcontactmanager.dao.ContactRepo;
import smartcontactmanager.dao.UserRepo;
import smartcontactmanager.entities.Contact;
import smartcontactmanager.entities.User;

@RestController //so that we can send json as a response
public class SearchController {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ContactRepo contactRepo;
	
	//this is the backend for handling the search functionality
	@GetMapping("/search/{query}")
	public ResponseEntity<List<Contact>> search(@PathVariable("query") String query,
			Principal principal) {
		
		User user=this.userRepo.getUserbyuserName(principal.getName());
		List<Contact> contacts=this.contactRepo.findByCnameContainingAndUser(query, user);
		return ResponseEntity.ok(contacts);
	}

}
