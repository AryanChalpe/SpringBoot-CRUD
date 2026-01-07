package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

public class UserControllerTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserController userController;

    private User testUser;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        testUser = new User("Test User", 30);
        testUser.setId("1");
    }

    @Test
    public void testCreateUser() {
        User newUser = new User("New User", 25);
        when(userRepository.save(newUser)).thenReturn(newUser);

        User result = userController.create(newUser);

        assertNotNull(result);
        assertEquals("New User", result.getName());
        verify(userRepository, times(1)).save(newUser);
    }

    @Test
    public void testGetAllUsers() {
        User user1 = new User("User 1", 20);
        User user2 = new User("User 2", 30);
        List<User> users = Arrays.asList(user1, user2);

        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userController.getAll();

        assertEquals(2, result.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void testGetAllUsersEmpty() {
        List<User> emptyList = Arrays.asList();
        when(userRepository.findAll()).thenReturn(emptyList);

        List<User> result = userController.getAll();

        assertEquals(0, result.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteUser() {
        doNothing().when(userRepository).deleteById("1");

        String result = userController.delete("1");

        assertEquals("Deleted", result);
        verify(userRepository, times(1)).deleteById("1");
    }

    @Test
    public void testDeleteUserMultipleTimes() {
        doNothing().when(userRepository).deleteById("1");

        userController.delete("1");
        userController.delete("1");

        verify(userRepository, times(2)).deleteById("1");
    }
}
