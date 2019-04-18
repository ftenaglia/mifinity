package com.mifinity.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mifinity.entity.Role;
import com.mifinity.entity.User;
import com.mifinity.repository.UserRepository;

@Transactional
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserRepository userRepository;

  public UserDetailsServiceImpl(final UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user =
        Optional.ofNullable(userRepository.findByUsername(username))
            .orElseThrow(() -> new UsernameNotFoundException(""));

    Set<GrantedAuthority> authorities = new HashSet<>();
    for (Role role : user.getRoles()) {
      authorities.add(new SimpleGrantedAuthority(role.getName()));
    }

    return new org.springframework.security.core.userdetails.User(
        user.getUsername(), user.getPassword(), authorities);
  }
}
