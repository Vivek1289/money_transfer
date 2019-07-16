package com.revoult.controllers;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.options;
import static spark.Spark.post;
import static spark.Spark.put;

import com.google.gson.Gson;
import com.revoult.entity.StandardResponse;
import com.revoult.entity.StatusResponse;
import com.revoult.entity.User;
import com.revoult.service.UserService;
import com.revoult.service.impl.UserServiceImpl;

/**
 * @author Vivek Grewal
 */
public class UserEndpoints implements EndPoints {

    public static void init() {
        final UserService userService = UserServiceImpl.INSTANCE;
        post("/addUser", (request, response) -> {
            response.type(RESPONSE_TYPE_APPLCIATION_JSON);

            User user = new Gson().fromJson(request.body(), User.class);
            userService.addUser(user);

            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS));
        });

        get("/getAllUsers", (request, response) -> {
            response.type(RESPONSE_TYPE_APPLCIATION_JSON);
            return new Gson().toJson(new StandardResponse(
            		StatusResponse.SUCCESS, new Gson().toJsonTree(userService.getUsers())));
        });

        get("/user/:id", (request, response) -> {
            response.type(RESPONSE_TYPE_APPLCIATION_JSON);
            return new Gson().toJsonTree(
            		new StandardResponse(StatusResponse.SUCCESS, 
            				new Gson().toJsonTree(userService.getUser(request.params(":id")))));
        });

        get("/user/:id/accounts", (request, response) -> {
            response.type(RESPONSE_TYPE_APPLCIATION_JSON);

            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, 
            		new Gson().toJsonTree(userService.getUser(request.params(":id")).getAccounts())));
        });

        put("/editUser/:id", (request, response) -> {
            response.type(RESPONSE_TYPE_APPLCIATION_JSON);

            User editedUser = userService.editUser(new Gson().fromJson(request.body(), User.class));

            if (editedUser != null) {
                return new Gson().toJson(new StandardResponse(
                		StatusResponse.SUCCESS, new Gson().toJsonTree(editedUser)));
            } else {
                return new Gson().toJson(new StandardResponse(
                		StatusResponse.ERROR, new Gson().toJson("User not found or error in edit")));
            }
        });

        delete("/deleteUser/:id", (request, response) -> {
            response.type(RESPONSE_TYPE_APPLCIATION_JSON);

            userService.deleteUser(request.params(":id"));
            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, request.params(":id") + 
            		" user deleted"));
        });

        options("/isUserExist/:id", (request, response) -> {
            response.type(RESPONSE_TYPE_APPLCIATION_JSON);

            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, 
            		(userService.userExist(request.params(":id"))) ? "User exist" : "User does not exist"));
        });

    }

}