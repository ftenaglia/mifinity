package com.mifinity.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.mifinity.entity.User;
import com.mifinity.service.UserService;

@Controller
public class UserController {

  private final UserService userService;

  public UserController(final UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/signup")
  public String signup(Model model) {
    model.addAttribute("user", new User());
    return "signup";
  }

  @PostMapping(path = "/signup")
  public String createUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {

    User existingUser = userService.findByEmail(user.getUsername());
    if (existingUser != null) {
      bindingResult.reject("username", "There is a user already registered with this email");
    }

    if (bindingResult.hasErrors()) {
      return "signup";
    }

    userService.createUser(user);

    return "redirect:/index?signupSuccess";
  }
}
