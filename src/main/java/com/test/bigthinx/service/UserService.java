package com.test.bigthinx.service;

import java.util.List;
import java.util.UUID;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.bigthinx.pojo.User;
import com.test.bigthinx.repo.UserRepo;

@Service
public class UserService {

	@Autowired
	public UserRepo userRepo;

	public void addUser(User user) {
		userRepo.save(user);		
	}

	public String login(User user) {
		String token = null;
		User user2 = userRepo.findByUsername(user.getUsername());

		if(user2.getPassword().equals(user.getPassword())) {
			token = UUID.randomUUID().toString();
			user2.setToken(token);
			userRepo.save(user2);
		}
		return token;
	}

	public void deleteById(ObjectId id) {
		userRepo.deleteById(id);
	}

	public List<User> getAllusers() {
		return userRepo.findAll();
	}

	public User updateUserById(User user) {
		return userRepo.save(user);
	}

}
