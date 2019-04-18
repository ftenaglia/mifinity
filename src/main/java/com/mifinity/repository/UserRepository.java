package com.mifinity.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mifinity.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

  User findByUsername(String username);
}
