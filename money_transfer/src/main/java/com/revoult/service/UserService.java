package com.revoult.service;

import java.util.Collection;

import com.revoult.entity.User;

public interface UserService {

    public User addUser (User user);

    public Collection<User> getUsers ();
    public User getUser (String id);

    public User editUser (User user);

    public void deleteUser (String id);

    public boolean userExist (String id);
}