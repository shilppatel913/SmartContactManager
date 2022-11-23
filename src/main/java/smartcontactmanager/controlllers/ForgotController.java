package smartcontactmanager.controlllers;

import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import smartcontactmanager.dao.UserRepo;
import smartcontactmanager.entities.User;
import smartcontactmanager.helper.Message;
import smartcontactmanager.service.EmailService;

@Controller
public class ForgotController {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private EmailService emailService;
	

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@GetMapping("/fp")
	public String otpform() {
		return "otp_form";
	}
	
	//to verify if the email is registered and generate an OTP
	@PostMapping("/send-otp")
	public String sendOtp(@RequestParam("email") String email,
			HttpSession session) {
		
		User user=this.userRepo.getUserbyuserName(email);
		if(user==null) {
			session.setAttribute("message", new Message("Email Id not registered","alert-danger"));
			return "redirect:/fp";
		}else {
//			generate OTp
			Random random = new Random(1000);
			int otp=random.nextInt(999999);
			//send otp on the email
			String subject="OTP FROM SCM";
			String message="OTP : "+otp;
			String from="shilpnirajbhai.patel2019@vitstudent.ac.in";
			boolean flag=this.emailService.sendEmail(message, subject, email,from);
			
			
			if(flag) {
				session.setAttribute("message",new Message("OTP sent successfully","alert-success"));
				session.setAttribute("myotp", otp);
				session.setAttribute("user",user);
				return "redirect:/verify-otp";
			}else {
				session.setAttribute("message",new Message("OTP could not be sent try again","alert-danger"));
				return "redirect:/fp";
			}
			
		}

	}
	
	//page to verify the otp
	@GetMapping("/verify-otp")
	public String verifyotp() {
		return "verify_otp";
	}
	
	//handling check otp
	@PostMapping("/check-otp")
	public String checkOtp(@RequestParam("otp") int otp,HttpSession session) {
		
		int myotp=(int)session.getAttribute("myotp");
		User user=(User)session.getAttribute("user");
		if(myotp==otp) {
			return "change_password";
		}else {
			session.setAttribute("message",new Message("Incorrect OTP entered","alert-danger"));
			return "redirect:/verify-otp";
		}
		
	}
	
	//to change the password and save it in the database
	@PostMapping("/change-pass")
	public String changePass(@RequestParam("password") String password,HttpSession session) {
		
		User user=(User) session.getAttribute("user");
		user.setUpassword(this.bCryptPasswordEncoder.encode(password));
		this.userRepo.save(user);
		session.setAttribute("message",new Message("Password changed successfully","alert-success"));
		return "redirect:/signin";
	}
}
