package com.example.demo;

import com.example.demo.controller.UserController;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DemoApplicationTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void contextLoads() {
        assertNotNull(applicationContext);
    }

    @Test
    public void userRepositoryBeanExists() {
        assertNotNull(applicationContext.getBean(UserRepository.class));
    }

    @Test
    public void userControllerBeanExists() {
        assertNotNull(applicationContext.getBean(UserController.class));
    }

    @Test
    public void applicationContextIsNotNull() {
        assertNotNull(applicationContext);
        assertTrue(applicationContext.containsBean("userRepository"));
    }
}
