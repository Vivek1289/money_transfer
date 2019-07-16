package com.revoult.service.impl;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;

import com.revoult.entity.User;
import com.revoult.service.UserService;

public enum UserServiceImpl implements UserService {
	INSTANCE;
    private Map<String, User> userStore = new ConcurrentHashMap<>();

    private volatile int id = 0;

    @Override
    public User addUser(User user) {
        user.setId(String.valueOf(++id));
        userStore.put(user.getId(), user);
        return user;
    }

    @Override
    public Collection<User> getUsers() {
        return userStore.values();
    }

    @Override
    public User getUser(String id) {
        return userStore.get(id);
    }
    
    @Override
    public User editUser(User user) throws IllegalArgumentException {
        
    	if (StringUtils.isBlank(user.getId())) {
    		throw new IllegalArgumentException("User id cannot be blank");
        }  
    	User userToBeEdited = userStore.get(user.getId());
        if (userToBeEdited == null) {
        	throw new IllegalArgumentException("User not found");
        }
           
        synchronized(userToBeEdited) {
        	 
        	 if (userToBeEdited.getEmail() != null) {
                 user.setEmail(userToBeEdited.getEmail());
             }
             if (userToBeEdited.getFirstName() != null) {
            	 user.setFirstName(userToBeEdited.getFirstName());
             }
             if (userToBeEdited.getLastName() != null) {
            	 user.setLastName(userToBeEdited.getLastName());
             }
             if (userToBeEdited.getId() != null) {
            	 user.setId(userToBeEdited.getId());
             }

        }
        
        return userToBeEdited;
    }


    @Override
    public void deleteUser(String id) {
    	userStore.remove(id);
    }

    @Override
    public boolean userExist(String id) {
        return userStore.containsKey(id);
    }

}