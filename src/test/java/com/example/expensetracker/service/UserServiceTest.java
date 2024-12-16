package com.example.expensetracker.service;

import com.example.expensetracker.model.User;
import com.example.expensetracker.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    void testAuthenticateUser() {
        User user = new User();
        user.setUsername("user");
        user.setPassword("123");

        when(userRepository.findByUsername("user")).thenReturn(java.util.Optional.of(user));
        Mockito.lenient().when(passwordEncoder.matches("123", user.getPassword())).thenReturn(true);

        java.util.Optional<User> authenticatedUser = userService.authenticateUser("user", "123");

        assertEquals(java.util.Optional.of(user), authenticatedUser);
    }
}