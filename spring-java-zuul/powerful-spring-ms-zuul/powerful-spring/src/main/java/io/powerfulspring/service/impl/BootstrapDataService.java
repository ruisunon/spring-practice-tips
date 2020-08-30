package io.powerfulspring.service.impl;

import io.powerfulspring.model.Role;
import io.powerfulspring.model.User;
import io.powerfulspring.repository.RoleRepository;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import io.powerfulspring.repository.UserRepository;

@Service
public class BootstrapDataService implements ApplicationListener<ContextRefreshedEvent> {
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserRepository userRepository;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		insertRoles();
		insertUser();
	}

	private void insertRoles() {
		if(roleRepository.findAll().size() == 0) {
			List<Role> roles = new ArrayList<Role>();
			Role roleAdmin = new Role();
			roleAdmin.setName("admin");
			roles.add(roleAdmin);
			
			Role roleUser = new Role();
			roleUser.setName("user");
			roles.add(roleUser);
			
			roleRepository.saveAll(roles);
		}
	}
	
	@Value("${spring.profiles.active}")
	private String profile;
	
	private void insertUser() {
		if(userRepository.userList().size() == 0) {
			User user = new User();
			if(profile.equalsIgnoreCase("dev")) {
				user.setEmail("powerfulspring@gmail.com");
				user.setFullName("powerful spring");
				user.setUserId("MJ001");
				user.setUserName("powerfulspring");
			} else {
				user.setEmail("powerfulspring@gmail.com");
				user.setFullName("powerfulspring");
				user.setUserId("AMJ001");
				user.setUserName("powerfulspring");
			}
			user.setMobile("9876543210");
			user.setPassword(new BCryptPasswordEncoder().encode("password"));
			user.setRole(roleRepository.findById(1L).get());
			
			userRepository.save(user);
		}
	}
}