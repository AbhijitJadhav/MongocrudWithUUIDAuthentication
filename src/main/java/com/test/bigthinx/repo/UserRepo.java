package com.test.bigthinx.repo;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.test.bigthinx.pojo.User;

@Repository
public interface UserRepo extends MongoRepository<User, String>{

	public User findByName(String name);

	public User findByUsername(String username);

	public User findByToken(String token);

	public void deleteById(ObjectId id);

}
