package smartcontactmanager.controlllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import smartcontactmanager.dao.UserRepo;
import smartcontactmanager.entities.Contact;
import smartcontactmanager.entities.User;


@Controller
@RequestMapping("/user")
public class UserController {
		@Autowired
		private UserRepo userRepo;
		
		//making a common method as we will need the user object for all the routes below
		@ModelAttribute
		public void addCommonData(Model model,Principal principal) {
			String username=principal.getName(); //this is the username we used to login
			System.out.println(username);
			//let us query this user from the database
			User user=this.userRepo.getUserbyuserName(username);
			System.out.println(user);
			//let us show this data on the webpage user-dashboard and apply a logout button
			model.addAttribute("user",user);
		}
		@RequestMapping("/dashboard")
		public String dashboard() {
			
			return "normal/user-dashboard";
		}
		
		//handler method to show contact form
		@RequestMapping("/add-contact")
		public String contactAdd() {
			return "normal/add-contact";
		}
		//handle the post request for the contact form
		@RequestMapping(path="/process_contact",method = RequestMethod.POST)
		public String handleForm(@ModelAttribute Contact contact,Principal principal) {
			System.out.println("Form has been submitted"+contact);
			//now let us try and add contact into the database
			//Instead of making the Contact repo and adding from there
			//we can use the userRepo to add this contact and it will automatically be added to the contact
			
			String username=principal.getName();
			//get the user from the database
			User user=this.userRepo.getUserbyuserName(username);
			//we have done the bidirectional mapping
			contact.setUser(user);
			user.getContact().add(contact);
			this.userRepo.save(user);
			System.out.println("The contact has been saved successfully");
			
			return "normal/add-contact";
		}
}
