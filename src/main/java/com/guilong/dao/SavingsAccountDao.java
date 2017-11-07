package com.guilong.dao;

import org.springframework.data.repository.CrudRepository;

import com.guilong.domain.SavingsAccount;

public interface SavingsAccountDao extends CrudRepository<SavingsAccount, Long>{
   SavingsAccount findByAccountNumber (int accountNumber);
   
   
}
