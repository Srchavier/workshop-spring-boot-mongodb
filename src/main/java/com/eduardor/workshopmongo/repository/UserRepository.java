package com.eduardor.workshopmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.eduardor.workshopmongo.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
