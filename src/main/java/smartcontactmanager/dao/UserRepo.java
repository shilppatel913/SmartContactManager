package smartcontactmanager.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import smartcontactmanager.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {

}
