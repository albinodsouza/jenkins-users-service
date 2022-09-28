package com.sony.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sony.model.User;

@Service
public class UserManager {
	private Map<String, User> users;
	
	public UserManager() {
	users = new HashMap<>();
	User user;
	
	user = new User("Albino","Dsouza","some@gmail.com","Kaiga");
	users.put(user.getId(), user);
	
	user = new User("Great","doe","great@gmail.com","texas");
	users.put(user.getId(), user);
	
	user = new User("jack","you","you@gmail.com","NY");
	users.put(user.getId(), user);
}

public Iterable<User>getAllUsers(){
	return this.users.values();
}
public User getUserById(String id) {
	return this.users.get(id);
}
	
	

}
