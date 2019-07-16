package com.revoult.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;

import com.revoult.entity.Account;
import com.revoult.service.AccountService;

/**
 * @author Vivek Grewal
 */
public enum AccountServiceImpl implements AccountService {
	
	INSTANCE;
    private Map<String, Account> accountStore = new ConcurrentHashMap<>();

    @Override
    public Account addAccount(Account account) {
    	accountStore.put(account.getAccountNumber(), account);
        return account;
    }

    @Override
    public void addAll(List<Account> accounts) {
        accounts.forEach(account -> accountStore.put(account.getAccountNumber(), account));
    }

    @Override
    public Collection<Account> getAccounts() {
        return accountStore.values();
    }

    @Override
    public Account getAccount(String accountNumber) {
        return accountStore.get(accountNumber);
    }
    
   
    @Override
    public Account editAccount(Account account) throws IllegalArgumentException {
        
    	if (StringUtils.isBlank(account.getAccountNumber())) {
    		throw new IllegalArgumentException("Account Number cannot be blank");
        }  
        Account accountToBeEdited = accountStore.get(account.getAccountNumber());
        if (accountToBeEdited == null) {
        	throw new IllegalArgumentException("Account not found");
        }
           
        synchronized(accountToBeEdited) {
        	 
            if (StringUtils.isNotBlank(account.getName())) {
            	accountToBeEdited.setName(account.getName());
            }
            if (account.getCurrency() != null) {
            	accountToBeEdited.setCurrency(account.getCurrency());
            }
            if (account.getBalance() != -1) {
            	accountToBeEdited.setBalance(account.getBalance());
            }

        }
        
        return accountToBeEdited;
    }

    @Override
    public void deleteAccount(String accountNumber) {
        accountStore.remove(accountNumber);
    }

    @Override
    public boolean isAccountExist(String id) {
        return accountStore.containsKey(id);
    }

}
