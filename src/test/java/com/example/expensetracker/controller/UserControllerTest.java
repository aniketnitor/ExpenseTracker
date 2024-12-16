package com.example.expensetracker.controller;

import com.example.expensetracker.model.User;
import com.example.expensetracker.repository.UserRepository;
import com.example.expensetracker.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserController userController;

    @Test
    void testLoginSuccess() {
        User user = new User();
        user.setUsername("user");
        user.setPassword("123");
        userRepository.save(user);

        when(userService.authenticateUser("user", "123")).thenReturn(java.util.Optional.of(user));

        ResponseEntity<String> response = userController.login("user", "123");

        assertEquals("Login successful", response.getBody());
    }

    @Test
    void testLoginFailure() {
        when(userService.authenticateUser("user", "wrong_password")).thenReturn(java.util.Optional.empty());

        ResponseEntity<String> response = userController.login("user", "wrong_password");

        assertEquals("Invalid credentials", response.getBody());
    }
}