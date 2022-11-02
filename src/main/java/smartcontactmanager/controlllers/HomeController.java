package smartcontactmanager.controlllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@RequestMapping("/home")
	public String home() {
		return "home";
	}
	

	@RequestMapping("/about")
	public String about() {
		return "about";
	}
	

	@RequestMapping("/signup")
	public String signup() {
		return "signup";
	}
	

	@RequestMapping("/login")
	public String login() {
		return "login";
	}
}
