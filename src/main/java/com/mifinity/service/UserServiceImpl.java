package com.mifinity.service;

import java.util.Arrays;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mifinity.entity.User;
import com.mifinity.repository.RoleRepository;
import com.mifinity.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final PasswordEncoder passwordEncoder;

  public UserServiceImpl(
      final UserRepository userRepository,
      final PasswordEncoder passwordEncoder,
      final RoleRepository roleRepository) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.roleRepository = roleRepository;
  }

  @Override
  public User findByEmail(String username) {
    return userRepository.findByUsername(username);
  }

  @Override
  public User createUser(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setRoles(Arrays.asList(roleRepository.findByName("USER")));
    return userRepository.save(user);
  }
}
