package com.guilong.service.userServiceImpl;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.loader.custom.Return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guilong.dao.PrimaryAccountDao;
import com.guilong.dao.PrimaryTransactionDao;
import com.guilong.dao.RecipientDao;
import com.guilong.dao.SavingsAccountDao;
import com.guilong.dao.SavingsTransactionDao;
import com.guilong.domain.PrimaryAccount;
import com.guilong.domain.PrimaryTransaction;
import com.guilong.domain.Recipient;
import com.guilong.domain.SavingsAccount;
import com.guilong.domain.SavingsTransaction;
import com.guilong.domain.User;
import com.guilong.service.TransactionService;
import com.guilong.service.UserService;

@Service
public class TransactionServiceImpl implements TransactionService {
	@Autowired
	private UserService userService;
	@Autowired
	private PrimaryTransactionDao primaryTransactionDao;
	@Autowired
	private SavingsTransactionDao savingsTransactionDao;
	@Autowired
	private PrimaryAccountDao primaryAccountDao;
	@Autowired
	private SavingsAccountDao savingsAccountDao;
	@Autowired
	private RecipientDao recipientDao;
	
	

	@Override
	public List<PrimaryTransaction> findPrimaryTransactionList(String username) {
		User user=userService.findByUserName(username);
		List<PrimaryTransaction> primaryTransactionList=user.getPrimaryAccount().getPrimaryTransactionList();
		return primaryTransactionList;
	}

	@Override
	public List<SavingsTransaction> findSavingsTransactionList(String username) {
		User user=userService.findByUserName(username);
		List<SavingsTransaction> savingsTransactionList=user.getSavingsAccount().getSavingsTransactionList();
		return savingsTransactionList;
	}

	@Override
	public void savePrimaryDepositTransaction(PrimaryTransaction primaryTransaction) {
		primaryTransactionDao.save(primaryTransaction);
		
	}

	@Override
	public void saveSavingsDepositTransaction(SavingsTransaction savingsTransaction) {
		savingsTransactionDao.save(savingsTransaction);
		
	}

	@Override
	public void savePrimaryWithdrawTransaction(PrimaryTransaction primaryTransaction) {
	primaryTransactionDao.save(primaryTransaction);
		
	}

	@Override
	public void saveSavingsWithdrawTransaction(SavingsTransaction savingsTransaction) {
		savingsTransactionDao.save(savingsTransaction);
		
	}

	@Override
	public void betweenAccountsTransfer(String transferFrom, String transferTo, String amount,
			PrimaryAccount primaryAccount, SavingsAccount savingsAccount) throws Exception {
		 if (transferFrom.equalsIgnoreCase("Primary") && transferTo.equalsIgnoreCase("Savings")) {
	            primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().subtract(new BigDecimal(amount)));
	            savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().add(new BigDecimal(amount)));
	            primaryAccountDao.save(primaryAccount);
	            savingsAccountDao.save(savingsAccount);

	            Date date = new Date();

	            PrimaryTransaction primaryTransaction = new PrimaryTransaction(date, "Between account transfer from "+transferFrom+" to "+transferTo, "Account", "Finished", Double.parseDouble(amount), primaryAccount.getAccountBalance(), primaryAccount);
	            primaryTransactionDao.save(primaryTransaction);
	        } else if (transferFrom.equalsIgnoreCase("Savings") && transferTo.equalsIgnoreCase("Primary")) {
	            primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().add(new BigDecimal(amount)));
	            savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().subtract(new BigDecimal(amount)));
	            primaryAccountDao.save(primaryAccount);
	            savingsAccountDao.save(savingsAccount);

	            Date date = new Date();

	            SavingsTransaction savingsTransaction = new SavingsTransaction(date, "Between account transfer from "+transferFrom+" to "+transferTo, "Transfer", "Finished", Double.parseDouble(amount), savingsAccount.getAccountBalance(), savingsAccount);
	            savingsTransactionDao.save(savingsTransaction);
	        } else {
	            throw new Exception("Invalid Transfer");
	        }
		
	}

	@Override
	public List<Recipient> findRecipientList(Principal principal) {
		String username=principal.getName();
		List<Recipient> recipientList=recipientDao.findAll().stream().filter(recipient->username.equals(recipient.getUser().getUsername())).collect(Collectors.toList());
		return recipientList;
		
	}

	@Override
	public Recipient saveRecipient(Recipient recipient) {
		// TODO Auto-generated method stub
		return recipientDao.save(recipient);
	}

	@Override
	public Recipient findRecipientByName(String recipientName) {
		// TODO Auto-generated method stub
		return recipientDao.findByName(recipientName);
	}

	@Override
	public void deleteRecipientByName(String recipientName) {
		recipientDao.deleteByName(recipientName);
		
	}

	@Override
	public void toSomeoneElseTransfer(Recipient recipient, String accountType, String amount,
			PrimaryAccount primaryAccount, SavingsAccount savingsAccount) {
		// TODO Auto-generated method stub
		  if (accountType.equalsIgnoreCase("Primary")) {
	            primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().subtract(new BigDecimal(amount)));
	            primaryAccountDao.save(primaryAccount);

	            Date date = new Date();

	            PrimaryTransaction primaryTransaction = new PrimaryTransaction(date, "Transfer to recipient "+recipient.getName(), "Transfer", "Finished", Double.parseDouble(amount), primaryAccount.getAccountBalance(), primaryAccount);
	            primaryTransactionDao.save(primaryTransaction);
	        } else if (accountType.equalsIgnoreCase("Savings")) {
	            savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().subtract(new BigDecimal(amount)));
	            savingsAccountDao.save(savingsAccount);

	            Date date = new Date();

	            SavingsTransaction savingsTransaction = new SavingsTransaction(date, "Transfer to recipient "+recipient.getName(), "Transfer", "Finished", Double.parseDouble(amount), savingsAccount.getAccountBalance(), savingsAccount);
	            savingsTransactionDao.save(savingsTransaction);
	        }
	    
	}

}
