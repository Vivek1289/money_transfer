package com.revoult.service;

import java.util.List;

import com.revoult.entity.Account;
import com.revoult.entity.Transaction;

/**
 * @author Vivek Grewal
 */
public interface TransactionService {
	
	// this flag we can put in database to enable/disable the commission
	public static final boolean IS_COMMISSION_FLAG_ON = true;
	
    Transaction getTransaction(String id);

    void deleteTransaction(String id);

	Transaction performTransaction(Account from, Account to, double amount);


	List<Transaction> getTransactions(List<String> ids);
}
