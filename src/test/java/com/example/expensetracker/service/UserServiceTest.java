package com.example.expensetracker.service;

import com.example.expensetracker.model.User;
import com.example.expensetracker.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testAuthenticateUser() {
        User user = new User();
        user.setUsername("user");
        user.setPassword("123");

        when(userRepository.findByUsername("user")).thenReturn(java.util.Optional.of(user));

        java.util.Optional<User> authenticatedUser = userService.authenticateUser("user", "123");

        assertEquals(java.util.Optional.of(user), authenticatedUser);
    }
}