package com.revoult.service.impl;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import com.revoult.currency.Currency;
import com.revoult.currency.convertor.CurrencyConvertorFactory;
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
		 to.addMoney(getAmountToBeAdded(from.getCurrency(), to.getCurrency(), amount));
		 transaction.setStatus(Status.SUCCESS);
		 from.addTransactionId(transaction.getId());
		 to.addTransactionId(transaction.getId());
	     return transaction;
	}
	
	private boolean isSameCurrency(Currency fromCurrency, Currency toCurrency) {
		return fromCurrency.equals(toCurrency);
	}
	
	private double getAmountToBeAdded(Currency fromCurrency, Currency toCurrency, double amount) {
		return isSameCurrency(fromCurrency, toCurrency) ? amount : 
			 CurrencyConvertorFactory.getCurrencyConvertor(IS_COMMISSION_FLAG_ON).
			 getConvertedAmount(fromCurrency, toCurrency, amount);
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
