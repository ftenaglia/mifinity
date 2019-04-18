package com.mifinity.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mifinity.entity.Card;
import com.mifinity.repository.CardRepository;

@Service
public class CardServiceImpl implements CardService {

  private final CardRepository cardRepository;

  public CardServiceImpl(final CardRepository cardRepository) {
    this.cardRepository = cardRepository;
  }

  @Override
  public Card findByNumber(String number) {
    return cardRepository.findAllByNumber(number);
  }

  @Override
  public Card saveCard(Card card) {
    return cardRepository.save(card);
  }

  @Override
  public List<Card> findAllCards() {
    return cardRepository.findAll();
  }

  @Override
  public List<Card> findAllCardsForUser(String username) {
    return cardRepository.findAllByOwnerUsername(username);
  }

  @Override
  public List<Card> searchCards(String pattern) {
    return cardRepository.findCards("%" + pattern + "%");
  }

  @Override
  public List<Card> searchCardsForUser(String pattern, String user) {
    return cardRepository.findCards("%" + pattern + "%", user);
  }
}
