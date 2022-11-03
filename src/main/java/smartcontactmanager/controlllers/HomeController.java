package smartcontactmanager.controlllers;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import smartcontactmanager.dao.UserRepo;
import smartcontactmanager.entities.User;
import smartcontactmanager.helper.Message;

@Controller
public class HomeController {
	
	@Autowired
	private UserRepo userRepo;
	
	@RequestMapping("/home")
	public String home() {
		return "home";
	}
	

	@RequestMapping("/about")
	public String about() {
		return "about";
	}
	

	@RequestMapping("/signup")
	public String signup(Model model) {
		return "signup";
	}
	
	//handling the post request for the signup form
	@RequestMapping(path = "/do_signup",method = RequestMethod.POST)
	public String handleSignup(@ModelAttribute User user,@RequestParam("uagreed") boolean agreed,HttpSession session,Model model) {
		try {
			if(!agreed) {
				throw new Exception("Please agree to the terms and conditions");
			}
			user.setUrole("ROLE_USER");
			user.setUenabled(true);
			user.setImageURL("default");
			User result=this.userRepo.save(user);
			System.out.println(result);
			session.setAttribute("message",new Message("Successfull registered","alert-success"));
			return "signup";
		}catch(Exception e) {
			model.addAttribute("user",user);
			session.setAttribute("message", new Message("Something went wrong","alert-warning"));
			return "signup";
		}
		
	}

	@RequestMapping("/login")
	public String login() {
		return "login";
	}
}
