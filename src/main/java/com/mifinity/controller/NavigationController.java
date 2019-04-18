package com.mifinity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavigationController {

  @GetMapping({"/", "/index", "/login"})
  public String root() {
    return "index";
  }

  @GetMapping("/card")
  public String app() {
    return "card/index";
  }
}
