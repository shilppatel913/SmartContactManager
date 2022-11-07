package smartcontactmanager.controlllers;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import smartcontactmanager.dao.ContactRepo;
import smartcontactmanager.dao.UserRepo;
import smartcontactmanager.entities.Contact;
import smartcontactmanager.entities.User;
import smartcontactmanager.helper.Message;


@Controller
@RequestMapping("/user")
public class UserController {
		@Autowired
		private UserRepo userRepo;
		
		@Autowired
		private ContactRepo contactRepo;
		
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
		public String handleForm(@ModelAttribute Contact contact,
				@RequestParam("profileImage") MultipartFile file,Principal principal,HttpSession session) {
			System.out.println("Form has been submitted"+contact);
			//now let us try and add contact into the database
			//Instead of making the Contact repo and adding from there
			//we can use the userRepo to add this contact and it will automatically be added to the contact
			try {
				String username=principal.getName();
				//get the user from the database
				User user=this.userRepo.getUserbyuserName(username);
				//let us first save the image in static/images and store the url in our database
				System.out.println(file.getName());
				contact.setCimgURL(file.getOriginalFilename());
				File fileobj=	new ClassPathResource("static/images").getFile();
				Path path=Paths.get(fileobj.getAbsolutePath()+File.separator+file.getOriginalFilename());
				
				Files.copy(file.getInputStream(), path,StandardCopyOption.REPLACE_EXISTING);
				System.out.println("Your file has been saved successfully");
				//we have done the bidirectional mapping
				contact.setUser(user);
				user.getContact().add(contact);
				this.userRepo.save(user);
				System.out.println("The contact has been saved successfully");
				session.setAttribute("message", new Message("Contact added successfully","alert-success"));
			}catch(Exception e) {
				System.out.println("Something went wrong");
				e.printStackTrace();
				session.setAttribute("message", new Message("Something went wrong","alert-danger"));
			}
			return "normal/add-contact";
		}
		
		//handler to view all the contacts
		@RequestMapping("/show-contacts/{page}")
		public String viewContact(@PathVariable("page") int currpage,Model model,Principal principal) {
			//how can we fetch all the  contacts of the user who is logged in
			//this is one way we can get all the contacts
			//but since we have to use pagination we will be making a seperate rep for contact
			
			/**String username=principal.getName();
			User user=this.userRepo.getUserbyuserName(username);
			List<Contact> contacts=user.getContact(); **/
			
			String username=principal.getName();
			User user=this.userRepo.getUserbyuserName(username);
		Pageable pageable=PageRequest.of(currpage, 3);
			Page<Contact> contacts=this.contactRepo.getContactsByUser(user.getId(),pageable);
			model.addAttribute("contacts",contacts);
			model.addAttribute("currPage",currpage);
			model.addAttribute("totalPages",contacts.getTotalPages());
			return "normal/contactview";
		}
}
