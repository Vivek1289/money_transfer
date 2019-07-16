package com.revoult.entity;

import java.util.ArrayList;
import java.util.List;

import com.revoult.exception.TransactionException;

/**
 * @author Vivek Grewal
 */
public class Account {

    private String name;
    private String accountNumber;
    private double balance = -1;
    private Currency currency;
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	

	public List<String> getTransactionIds() {
		return transactionIds;
	}

	public void setTransactionIds(List<String> transactionIds) {
		this.transactionIds = transactionIds;
	}
	
	public void addTransactionId(String transactionId) {
		this.transactionIds.add(transactionId);
	}

	private List<String> transactionIds;

    private static int accountNumberSequence = 1000000;

    public Account(String name) {
        this(name, Currency.INR);
    }

    public Account(String name, Currency currency) {
        this(name, 1000, currency);

    }

    public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public Account(String name, double balance, Currency currency) {
        if (balance < 0)
            throw new IllegalArgumentException("amount should be >=0");
        if (name.isEmpty()) {
            throw new IllegalArgumentException("name can't be empty");
        }
        this.accountNumber = generateAccountNumber();
        this.name = name;
        this.currency = currency;
        this.balance = balance;
        this.transactionIds = new ArrayList<>();
    }


  
    private synchronized String generateAccountNumber() {
        return String.valueOf(++accountNumberSequence);
    }

    public synchronized double withdrawMoney(double amount) throws TransactionException {
    	if(this.balance < amount) {
    		throw new TransactionException("Insufficient Balance...");
    	}
        this.balance -= amount;
        return this.balance;
    }

    public synchronized double addMoney(double amount) {
    	this.balance += amount;
    	return this.balance;
    }

}
