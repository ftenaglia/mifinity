package com.mifinity.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mifinity.entity.Card;
import com.mifinity.entity.User;
import com.mifinity.service.CardService;

@Controller
public class CardController {

  private final CardService cardService;

  public CardController(final CardService cardService) {
    this.cardService = cardService;
  }

  @GetMapping("/card/add")
  public String appCard(Model model) {
    model.addAttribute("card", new Card());
    return "card/card";
  }

  @GetMapping("/card/search")
  public String appSearch(Model model) {
    model.addAttribute("cards", new ArrayList<Card>());
    return "card/search";
  }

  @PostMapping("/card/add")
  public String addCard(
      @Valid Card card, BindingResult bindingResult, Authentication authentication) {

    if (bindingResult.hasErrors()) {
      return "card/card";
    }

    Card existingCard = cardService.findByNumber(card.getNumber());

    if (existingCard != null) {

      if (existingCard.getOwner().getUsername().equals(authentication.getName())) {
        existingCard.setExpiryDate(card.getExpiryDate());
        cardService.saveCard(existingCard);
      } else {
        bindingResult.rejectValue("number", "number", "You can't change this card");
        return "card/card";
      }
    } else {
      card.setOwner(new User(authentication.getName()));
      cardService.saveCard(card);
    }

    return "redirect:/card?cardAdded";
  }

  @PostMapping("/card/search")
  public String addCard(
      @RequestParam("pattern") String pattern,
      HttpServletRequest request,
      Model model,
      Authentication authentication) {

    List<Card> cards = new ArrayList<>();
    if (request.isUserInRole("ADMIN")) {
      cards = cardService.searchCards(pattern);
    } else {
      cards = cardService.searchCardsForUser(pattern, request.getUserPrincipal().getName());
    }

    model.addAttribute("cards", cards);

    return "card/search";
  }
}
