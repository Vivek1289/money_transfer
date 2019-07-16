package com.revoult.service;

import java.util.List;

import com.revoult.entity.Account;
import com.revoult.entity.Transaction;

/**
 * @author Vivek Grewal
 */
public interface TransactionService {
    Transaction getTransaction(String id);


    void deleteTransaction(String id);

	Transaction performTransaction(Account from, Account to, double amount);


	List<Transaction> getTransactions(List<String> ids);
}
