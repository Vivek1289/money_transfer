package com.revoult.service.impl;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import com.revoult.currency.CurrencyConvertor;
import com.revoult.entity.Account;
import com.revoult.entity.Transaction;
import com.revoult.entity.Transaction.Status;
import com.revoult.exception.TransactionException;
import com.revoult.service.TransactionService;

/**
 * @author Vivek Grewal
 */
public enum TransactionServiceImpl implements TransactionService {
	INSTANCE;
    private Map<String, Transaction> transactionStore = new ConcurrentHashMap<>();

	@Override
	public Transaction performTransaction(Account from, Account to, double amount) {
		 Transaction transaction = new Transaction(from, to, amount);
		 transactionStore.put(transaction.getId(), transaction);
		try {
			from.withdrawMoney(amount);
		} catch (TransactionException e) {
			transaction.setStatus(Status.FAILED);
			transaction.setFailureReason(e.getMessage());
			return transaction;
		}
		 to.addMoney(CurrencyConvertor.getConversionRate(from.getCurrency(), 
				 to.getCurrency()) * amount);
		 transaction.setStatus(Status.SUCCESS);
		 from.addTransactionId(transaction.getId());
		 to.addTransactionId(transaction.getId());
	     return transaction;
	}


	@Override
	public Transaction getTransaction(String id) {
		return transactionStore.get(id);
	}

	@Override
	public void deleteTransaction(String id) {
		transactionStore.remove(id);
	}
	@Override
	public List<Transaction> getTransactions(List<String> ids) {
		
		return transactionStore.entrySet().stream().filter(entry -> ids.contains(entry.getKey())).
		map(java.util.Map.Entry :: getValue).collect(Collectors.toList());
	}

}
