package com.revoult;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

import com.revoult.entity.Account;
import com.revoult.entity.Currency;
import com.revoult.entity.Transaction;
import com.revoult.entity.Transaction.Status;
import com.revoult.service.AccountService;
import com.revoult.service.TransactionService;
import com.revoult.service.impl.AccountServiceImpl;
import com.revoult.service.impl.TransactionServiceImpl;


public class TransactionTests {
    private static AccountService accountService = AccountServiceImpl.INSTANCE;
    private static TransactionService transactionService = TransactionServiceImpl.INSTANCE;
    private static Account account1;
    private static Account account2;
    private static Account account3;

    public TransactionTests() {
        account1 = new Account("1 account", 500, Currency.CAD);
        accountService.addAccount(account1);

        account2 = new Account("2 account", 100, Currency.CAD);
        accountService.addAccount(account2);

        account3 = new Account("3 account", 100, Currency.EUR);
        accountService.addAccount(account3);
    }

	@Test
    public void transactTest() {
        account1.setBalance(500);
        account2.setBalance(100);
        Transaction transaction = transactionService.performTransaction(account1, account2, 100);
        assertEquals(transaction.getStatus(), Transaction.Status.SUCCESS);
        assertEquals(account1.getBalance(), 400, 0.01);
        assertEquals(account2.getBalance(), 200, 0.01);
        
    }

    @Test
    public void transferWhenNotEnoughFunds() {
        double payment = 1000000;
        account1.setBalance(500);
        account2.setBalance(100);
        Transaction transaction = transactionService.performTransaction(account1, account2, payment);
        assertEquals(transaction.getStatus(), Status.FAILED);
        assertEquals(transaction.getFailureReason(), "Insufficient Balance...");
    }

	@Test
    public void transfersInSameTime() throws InterruptedException {
        account1.setBalance(200);
        account2.setBalance(1000);
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        CountDownLatch latch = new CountDownLatch(3);
        final Collection<Transaction> transactions = Collections.synchronizedCollection(new ArrayList<>());
        Runnable runnable = ()->{
        	transactions.add(transactionService.performTransaction(account2, account1, 100));
        	latch.countDown();
        };
        executorService.submit(runnable);
        executorService.submit(runnable);
        executorService.submit(runnable);
        latch.await();
        assertEquals(transactions.stream().
            		filter(transaction -> transaction.getStatus().equals(Status.SUCCESS)).count(),3);
        assertEquals(account1.getBalance(), 500, 0.01);
        assertEquals(account2.getBalance(), 700, 0.01);

    }

    @Test
    public void transactWithDifferentCurrencies() {
    	account1.setBalance(1000);
    	account1.setCurrency(Currency.CAD);
    	account3.setBalance(200);
    	account3.setCurrency(Currency.EUR);
    	transactionService.performTransaction(account1, account3, 500);
    	assertEquals(account1.getBalance(), 500, 0.01);
        assertEquals(account3.getBalance(), 542.105, 0.01);

    }

   
}
