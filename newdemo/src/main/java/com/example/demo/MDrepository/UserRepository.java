package com.example.demo.MDrepository;

import com.example.demo.MDentity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String> {
    User findByUsername(String username);
}
