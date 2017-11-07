package com.guilong.dao;

import org.springframework.data.repository.CrudRepository;

import com.guilong.domain.PrimaryAccount;

public interface PrimaryAccountDao extends CrudRepository<PrimaryAccount, Long> {
 PrimaryAccount findByAccountNumber(int accountNumber);
}
