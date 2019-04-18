package com.mifinity.service;

import java.util.List;

import com.mifinity.entity.Card;

public interface CardService {

  Card findByNumber(String number);

  Card saveCard(Card card);

  List<Card> findAllCards();

  List<Card> findAllCardsForUser(String user);

  List<Card> searchCards(String pattern);

  List<Card> searchCardsForUser(String pattern, String user);
}
