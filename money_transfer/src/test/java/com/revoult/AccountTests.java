package com.revoult;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.revoult.currency.Currency;
import com.revoult.entity.Account;
import com.revoult.service.AccountService;
import com.revoult.service.impl.AccountServiceImpl;


public class AccountTests {
    private AccountService accountService = AccountServiceImpl.INSTANCE;

    @Test
    public void testCreateAccount() {
        Account account = accountService.addAccount(new Account("Vivek Grewal Saving's Account", 
        		Currency.INR));
        assertEquals(accountService.getAccount(account.getAccountNumber()), account);
        accountService.deleteAccount(account.getAccountNumber());
    }

    @Test
    public void testUpdateAccount() {
        Account account = accountService.addAccount(new Account("Vivek Grewal OD Account", Currency.EUR));
        account.setName("Vivek Grewal Current Account");
        account.setCurrency(Currency.USD);
        accountService.editAccount(account);
        account = accountService.getAccount(account.getAccountNumber());
        assertEquals(account.getName(), "Vivek Grewal Current Account");
        assertEquals(account.getCurrency(), Currency.USD);
        accountService.deleteAccount(account.getAccountNumber());
    }

    @Test
    public void testDeleteAccount() {
        Account account = accountService.addAccount(new Account("TEST account", Currency.CAD));
        accountService.deleteAccount(account.getAccountNumber());
        assertNull(accountService.getAccount(account.getAccountNumber()));
    }

}
