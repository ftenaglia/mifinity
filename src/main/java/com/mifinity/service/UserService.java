package com.mifinity.service;

import com.mifinity.entity.User;

public interface UserService {

  User findByEmail(String email);

  User createUser(User user);
}
