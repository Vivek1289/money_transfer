package com.revoult;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;

import org.junit.Test;

import com.revoult.entity.User;
import com.revoult.service.UserService;
import com.revoult.service.impl.UserServiceImpl;

public class UserTests {

    private UserService userService = UserServiceImpl.INSTANCE;


    @Test
    public void testCreateUser() {
        User user = userService.addUser(new User("Vivek", "Grewal", "vivek.grewal@gmail.com", 
        		new ArrayList<>()));
        assertEquals(userService.getUser(user.getId()), user);
        userService.deleteUser(user.getId());
    }

    @Test
    public void testUpdateUser() {
        User user = userService.addUser(new User("Vivek", "Grewal", "vivek.grewal@gmail.com", 
        		new ArrayList<>()));
        user = userService.getUser(user.getId());
        user.setFirstName("Vicky");
        user.setLastName("G");
        user.setEmail("vicky.g@gmail.com");
        userService.editUser(user);
        user = userService.getUser(user.getId());
        assertEquals(user.getFirstName(), "Vicky");
        assertEquals(user.getLastName(), "G");
        assertEquals(user.getEmail(), "vicky.g@gmail.com");
        userService.deleteUser(user.getId());
    }

    @Test
    public void testDeleteUser() {
        User user = userService.addUser(new User("Vivek", "Grewal", "vivek.grewal@gmail.com", 
        		new ArrayList<>()));
        userService.deleteUser(user.getId());
        assertNull(userService.getUser(user.getId()));
    }
}
