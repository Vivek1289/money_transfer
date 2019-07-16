package com.revoult.controllers;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.options;
import static spark.Spark.post;
import static spark.Spark.put;

import com.google.gson.Gson;
import com.revoult.entity.Account;
import com.revoult.entity.StandardResponse;
import com.revoult.entity.StatusResponse;
import com.revoult.entity.Transaction;
import com.revoult.entity.dto.TransactionDTO;
import com.revoult.service.AccountService;
import com.revoult.service.TransactionService;
import com.revoult.service.impl.AccountServiceImpl;
import com.revoult.service.impl.TransactionServiceImpl;

/**
 * @author Vivek Grewal
 */
public class AccountServiceEndpoints implements EndPoints {

    public static void init() {
        final AccountService accountService = AccountServiceImpl.INSTANCE;
        final TransactionService transactionService = TransactionServiceImpl.INSTANCE;

        post("/addAccount", (request, response) -> {
            response.type(RESPONSE_TYPE_APPLCIATION_JSON);

            Account account = new Gson().fromJson(request.body(), Account.class);
            account = accountService.addAccount(account);

            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, 
            		new Gson().toJson(account)));
        });

        get("/getAllAccounts", (request, response) -> {
            response.type(RESPONSE_TYPE_APPLCIATION_JSON);

            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, 
            		new Gson().toJsonTree(accountService.getAccounts())));
        });

        get("/account/:accountNumber", (request, response) -> {
            response.type(RESPONSE_TYPE_APPLCIATION_JSON);

            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, 
            		new Gson().toJsonTree(accountService.getAccount(request.params(":accountNumber")))));
        });

        put("/editAccount/:accountNumber", (request, response) -> {
            response.type(RESPONSE_TYPE_APPLCIATION_JSON);

            Account editedAccount = accountService.editAccount(
            		new Gson().fromJson(request.body(), Account.class));

            if (editedAccount != null) {
                return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, 
                		new Gson().toJson(editedAccount)));
            } else {
                return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, 
                		new Gson().toJson("Account edit failed...")));
            }
        });


        get("/account/:accountNumber/getAllTransactions", (request, response) -> {
            response.type(RESPONSE_TYPE_APPLCIATION_JSON);
            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, new Gson().toJson(
                    transactionService.getTransactions(
                    		accountService.getAccount(
                    				request.params(":accountNumber")).getTransactionIds())
            )));
        });

        post("/account/:accountNumber/transact", (request, response) -> {
            response.type(RESPONSE_TYPE_APPLCIATION_JSON);
            TransactionDTO transactionDTO = new Gson().fromJson(request.body(), TransactionDTO.class);
            Account from = accountService.getAccount(request.params(":accountNumber"));
            Account to = accountService.getAccount(transactionDTO.getAccountNumberTo());
            if (from == null || to == null) {
                return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, 
                		new Gson().toJson("Wrong account number")));
            }
            
            Transaction transaction = null;
            try {
            	transaction = 
            			transactionService.performTransaction(from, to, 
            					Double.parseDouble(transactionDTO.getAmount()));
            } catch (IllegalArgumentException e) {
                return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, 
                		new Gson().toJson("Wrong amount")));
            }

            if (transaction!=null && 
            		transaction.getStatus().equals(Transaction.Status.SUCCESS))
                return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, 
                		new Gson().toJson(transaction)));
            else
                return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, 
                		new Gson().toJson(transaction.getFailureReason())));


        });

        delete("/deleteAccount/:accountNumber", (request, response) ->

        {
            response.type(RESPONSE_TYPE_APPLCIATION_JSON);

            accountService.deleteAccount(request.params(":accountNumber"));
            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, 
            		request.params(":accountNumber") + " account deleted"));
        });

        options("/isAccountExist/:accountNumber", (request, response) ->

        {
            response.type(RESPONSE_TYPE_APPLCIATION_JSON);

            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, 
            		(accountService.isAccountExist(request.params(":accountNumber"))) ? 
            				"Account exists" : "Account does not exist"));
        });

    }


}
