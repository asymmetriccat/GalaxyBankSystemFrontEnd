package com.guilong.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.guilong.domain.SavingsTransaction;

public interface SavingsTransactionDao extends CrudRepository<SavingsTransaction, Long>{
  List<SavingsTransaction> findAll();
}
