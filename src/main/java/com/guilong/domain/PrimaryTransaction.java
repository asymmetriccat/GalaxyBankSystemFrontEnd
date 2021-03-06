package com.guilong.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.context.annotation.Primary;

@Entity
public class PrimaryTransaction {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;
  private Date date;
  private String descprition;
  private String type;
  private String status;
  private double amount;
  private BigDecimal availableBalance;
  
  @ManyToOne
  @JoinColumn(name="primary_account_id")
  private PrimaryAccount primaryAccount;
  
  public PrimaryTransaction(){}

public PrimaryTransaction(Date date, String descprition, String type, String status, double amount,
		BigDecimal availableBalance, PrimaryAccount primaryAccount) {
	super();
	this.date = date;
	this.descprition = descprition;
	this.type = type;
	this.status = status;
	this.amount = amount;
	this.availableBalance = availableBalance;
	this.primaryAccount = primaryAccount;
}

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public Date getDate() {
	return date;
}

public void setDate(Date date) {
	this.date = date;
}

public String getDescprition() {
	return descprition;
}

public void setDescprition(String descprition) {
	this.descprition = descprition;
}

public String getType() {
	return type;
}

public void setType(String type) {
	this.type = type;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

public double getAmount() {
	return amount;
}

public void setAmount(double amount) {
	this.amount = amount;
}

public BigDecimal getAvailableBalance() {
	return availableBalance;
}

public void setAvailableBalance(BigDecimal availableBalance) {
	this.availableBalance = availableBalance;
}

public PrimaryAccount getPrimaryAccount() {
	return primaryAccount;
}

public void setPrimaryAccount(PrimaryAccount primaryAccount) {
	this.primaryAccount = primaryAccount;
}
  
  
}
