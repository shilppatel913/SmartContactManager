package smartcontactmanager.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import smartcontactmanager.dao.UserRepo;
import smartcontactmanager.entities.User;

public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user=this.userRepo.getUserbyuserName(username);
		if(user==null) {
			throw new UsernameNotFoundException("Could not find username in the database");
		}
		CustomUserDetails customDetails=new CustomUserDetails(user);
		return customDetails;
	}

}
