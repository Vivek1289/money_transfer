package com.revoult.controllers;

import static spark.Spark.get;
import static spark.Spark.post;

import com.revoult.service.TransactionService;
import com.revoult.service.impl.TransactionServiceImpl;
import com.google.gson.Gson;
import com.revoult.entity.StandardResponse;
import com.revoult.entity.StatusResponse;

/**
 * @author Vivek Grewal
 */
public class TransactionEndpoints implements EndPoints {

    public static void init() {
        TransactionService transactionService = TransactionServiceImpl.INSTANCE;

        get("/getAllTransactions", (request, response) -> {
            response.type(RESPONSE_TYPE_APPLCIATION_JSON);
            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS));
        });


        get("/transactions/:id", (request, response) -> {
            response.type(RESPONSE_TYPE_APPLCIATION_JSON);

            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, 
            		new Gson().toJsonTree(transactionService.getTransaction(request.params(":id")))));
        });



    }
}
