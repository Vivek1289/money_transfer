package com.revoult.service;

import java.util.Collection;
import java.util.List;

import com.revoult.entity.Account;

/**
 * @author Vivek Grewal
 */
public interface AccountService {
    public Account addAccount(Account account);

    public void addAll(List<Account> accounts);

    public Collection<Account> getAccounts();

    public Account getAccount(String id);

    public Account editAccount(Account user);

    public void deleteAccount(String id);

	public boolean isAccountExist(String id);
}
