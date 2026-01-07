package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.TestPropertySource;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

@DataMongoTest
@TestPropertySource(properties = "spring.mongodb.embedded.version=4.4.13")
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User testUser;

    @BeforeEach
    public void setUp() {
        userRepository.deleteAll();
        testUser = new User("Test User", 25);
    }

    @Test
    public void testSaveUser() {
        User savedUser = userRepository.save(testUser);
        assertNotNull(savedUser.getId());
        assertEquals("Test User", savedUser.getName());
    }

    @Test
    public void testFindUserById() {
        User savedUser = userRepository.save(testUser);
        Optional<User> foundUser = userRepository.findById(savedUser.getId());
        
        assertTrue(foundUser.isPresent());
        assertEquals("Test User", foundUser.get().getName());
    }

    @Test
    public void testFindAllUsers() {
        userRepository.save(new User("User 1", 20));
        userRepository.save(new User("User 2", 30));
        
        List<User> users = userRepository.findAll();
        assertEquals(2, users.size());
    }

    @Test
    public void testUpdateUser() {
        User savedUser = userRepository.save(testUser);
        savedUser.setName("Updated User");
        savedUser.setAge(40);
        
        User updatedUser = userRepository.save(savedUser);
        assertEquals("Updated User", updatedUser.getName());
        assertEquals(40, updatedUser.getAge());
    }

    @Test
    public void testDeleteUser() {
        User savedUser = userRepository.save(testUser);
        String userId = savedUser.getId();
        
        userRepository.deleteById(userId);
        Optional<User> deletedUser = userRepository.findById(userId);
        
        assertFalse(deletedUser.isPresent());
    }

    @Test
    public void testRepositoryEmpty() {
        List<User> users = userRepository.findAll();
        assertEquals(0, users.size());
    }
}
