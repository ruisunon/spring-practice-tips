package io.powerfulspring.service;

import java.util.List;

import io.powerfulspring.model.Role;
import io.powerfulspring.model.User;

public interface UserService extends HelperService<User> {
	
	User findByUsername(String username);
	
	List<Role> roleList();
	
}
