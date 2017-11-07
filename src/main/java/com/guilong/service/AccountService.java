package com.guilong.service;

import java.security.Principal;

import com.guilong.domain.PrimaryAccount;
import com.guilong.domain.SavingsAccount;

public interface AccountService {
   PrimaryAccount createPrimaryAccount();
   SavingsAccount createSavingsAccount();
   
   void deposit(String accountType, double amount, Principal principal);
   void withdraw(String accountType, double amount, Principal principal);
}
