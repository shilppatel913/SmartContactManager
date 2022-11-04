package smartcontactmanager.controlllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import smartcontactmanager.dao.UserRepo;
import smartcontactmanager.entities.User;


@Controller
@RequestMapping("/user")
public class UserController {
		@Autowired
		private UserRepo userRepo;
		@RequestMapping("/dashboard")
		public String dashboard(Model model,Principal principal) {
			String username=principal.getName(); //this is the username we used to login
			System.out.println(username);
			//let us query this user from the database
			User user=this.userRepo.getUserbyuserName(username);
			System.out.println(user);
			//let us show this data on the webpage user-dashboard and apply a logout button
			model.addAttribute("user",user);
			return "normal/user-dashboard";
		}
}
