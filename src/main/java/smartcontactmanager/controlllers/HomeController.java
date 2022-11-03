package smartcontactmanager.controlllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
		model.addAttribute("user",new User());
		return "signup";
	}
	
	//handling the post request for the signup form
	//let us apply server side validation also
	@RequestMapping(path = "/do_signup",method = RequestMethod.POST)
	public String handleSignup(@Valid @ModelAttribute("user") User user,BindingResult result,@RequestParam("uagreed") boolean agreed,HttpSession session,Model model) {
		try {
			if(!agreed) {
				throw new Exception("Please agree to the terms and conditions");
			}
			if(result.hasErrors()) {
				return "signup";
			}
			user.setUrole("ROLE_USER");
			user.setUenabled(true);
			user.setImageURL("default");
			this.userRepo.save(user);
			session.setAttribute("message",new Message("Successfully registered","alert-success"));
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
