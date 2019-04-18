package com.mifinity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mifinity.entity.Card;

@Repository
public interface CardRepository extends CrudRepository<Card, String> {

  List<Card> findAll();

  List<Card> findAllByOwnerUsername(String ownerUsername);

  @Query("SELECT c FROM Card c WHERE c.number like ?1")
  List<Card> findCards(String pattern);

  @Query("SELECT c FROM Card c WHERE c.number like ?1 and c.owner.username = ?2")
  List<Card> findCards(String pattern, String ownerUsername);

  Card findAllByNumber(String number);
}
