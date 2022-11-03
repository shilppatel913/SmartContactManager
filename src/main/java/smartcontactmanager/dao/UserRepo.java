package smartcontactmanager.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import smartcontactmanager.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	
	@Query("select u from User u where u.uemail=:email")
	public User getUserbyuserName(@Param("email") String email);

}
