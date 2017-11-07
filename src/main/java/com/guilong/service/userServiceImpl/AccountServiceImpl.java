package com.guilong.service.userServiceImpl;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guilong.dao.PrimaryAccountDao;
import com.guilong.dao.SavingsAccountDao;
import com.guilong.domain.PrimaryAccount;
import com.guilong.domain.PrimaryTransaction;
import com.guilong.domain.SavingsAccount;
import com.guilong.domain.SavingsTransaction;
import com.guilong.domain.User;
import com.guilong.service.AccountService;
import com.guilong.service.TransactionService;
import com.guilong.service.UserService;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
	private static int nextAccountNumber=11223145;
	@Autowired
	private PrimaryAccountDao primaryAccountDao;
	@Autowired
	private SavingsAccountDao savingsAccountDao;
	@Autowired
	private UserService userService;
    @Autowired
	private TransactionService transactionService;
	
    
	private int accountGen(){
		return nextAccountNumber+1;
	}
	
	@Override
	public PrimaryAccount createPrimaryAccount() {
		PrimaryAccount primaryAccount=new PrimaryAccount();
		primaryAccount.setAccountBalance(new BigDecimal(0.0));
		primaryAccount.setAccountNumber(accountGen());
		
		primaryAccountDao.save(primaryAccount);
		return primaryAccountDao.findByAccountNumber(primaryAccount.getAccountNumber());
	}

	@Override
	public SavingsAccount createSavingsAccount() {
		SavingsAccount savingsAccount=new SavingsAccount();
		savingsAccount.setAccountBalance(new BigDecimal(0.0));
		savingsAccount.setAccountNumber(accountGen());
		
		savingsAccountDao.save(savingsAccount);
		
		return savingsAccountDao.findByAccountNumber(savingsAccount.getAccountNumber());
	}

	@Override
	public void deposit(String accountType, double amount, Principal principal) {
		User user=userService.findByUserName(principal.getName());
		
		if(accountType.equalsIgnoreCase("Primary")){
			PrimaryAccount primaryAccount=user.getPrimaryAccount();
			primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().add(new BigDecimal(amount)));
			primaryAccountDao.save(primaryAccount);
			
			Date date=new Date();
			PrimaryTransaction primaryTransaction = new PrimaryTransaction(date, "Deposit to Primary Account", "Account", "Finished", amount, primaryAccount.getAccountBalance(), primaryAccount);
            transactionService.savePrimaryDepositTransaction(primaryTransaction);
		}else if (accountType.equalsIgnoreCase("Savings")){
			SavingsAccount savingsAccount=user.getSavingsAccount();
			savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().add(new BigDecimal(amount)));
			savingsAccountDao.save(savingsAccount);
			
			Date date=new Date();
			SavingsTransaction savingsTransaction = new SavingsTransaction(date, "Deposit to Primary Account", "Account", "Finished", amount, savingsAccount.getAccountBalance(), savingsAccount);
            transactionService.saveSavingsDepositTransaction(savingsTransaction);
		}
		
	}

	@Override
	public void withdraw(String accountType, double amount, Principal principal) {
		User user=userService.findByUserName(principal.getName());
		if(accountType.equalsIgnoreCase("Primary")){
			PrimaryAccount primaryAccount=user.getPrimaryAccount();
			primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().subtract(new BigDecimal(amount)));
			primaryAccountDao.save(primaryAccount);
			
			Date date=new Date();
			PrimaryTransaction primaryTransaction = new PrimaryTransaction(date, "Withdraw from Primary Account", "Account", "Finished", amount, primaryAccount.getAccountBalance(), primaryAccount);
            transactionService.savePrimaryDepositTransaction(primaryTransaction);
		}else if (accountType.equalsIgnoreCase("Savings")){
			SavingsAccount savingsAccount=user.getSavingsAccount();
			savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().subtract(new BigDecimal(amount)));
			savingsAccountDao.save(savingsAccount);
			
			Date date=new Date();
			SavingsTransaction savingsTransaction = new SavingsTransaction(date, "Withdraw from Primary Account", "Account", "Finished", amount, savingsAccount.getAccountBalance(), savingsAccount);
            transactionService.saveSavingsDepositTransaction(savingsTransaction);
		
	}
  
}
	
}
