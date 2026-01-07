package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User("John Doe", 30);
    }

    @Test
    public void testUserCreation() {
        assertNotNull(user);
        assertEquals("John Doe", user.getName());
        assertEquals(30, user.getAge());
    }

    @Test
    public void testUserWithId() {
        user.setId("123");
        assertEquals("123", user.getId());
    }

    @Test
    public void testUserDefaultConstructor() {
        User newUser = new User();
        assertNotNull(newUser);
    }

    @Test
    public void testUserSetterGetters() {
        user.setName("Jane Doe");
        user.setAge(25);
        
        assertEquals("Jane Doe", user.getName());
        assertEquals(25, user.getAge());
    }

    @Test
    public void testUserEquality() {
        User user2 = new User("John Doe", 30);
        user2.setId("123");
        user.setId("123");
        
        assertEquals(user.getId(), user2.getId());
    }
}
