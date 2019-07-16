package com.revoult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.revoult.controllers.AccountServiceEndpoints;
import com.revoult.controllers.TransactionEndpoints;
import com.revoult.controllers.UserEndpoints;
import com.revoult.currency.Currency;
import com.revoult.entity.Account;
import com.revoult.entity.User;
import com.revoult.service.AccountService;
import com.revoult.service.UserService;
import com.revoult.service.impl.AccountServiceImpl;
import com.revoult.service.impl.UserServiceImpl;

/**
 * @author Vivek Grewal
 */
public class Main {

    public static void main(String[] args) {

        UserEndpoints.init();
        AccountServiceEndpoints.init();
        TransactionEndpoints.init();
        initData();
    }

    public static void initData() {
        UserService userService = UserServiceImpl.INSTANCE;
        AccountService accountService = AccountServiceImpl.INSTANCE;
        List<Account> accounts = new ArrayList<>(Arrays.asList(
                new Account("Vivek Savings Account", 1000, Currency.INR),
                new Account("Shreya OD Account", 500, Currency.EUR)
        ));
        accountService.addAll(accounts);
        User user = new User("Vivek", "Grewal", "vivek.g@gmail.com", accounts);
        userService.addUser(user);
        user = new User("Shreya", "S", "s.shreya@gmail.com");
        userService.addUser(user);
        user = new User("Aakash", "Grewal", "aakash.g@yahoo.in");
        userService.addUser(user);



    }
}
