package com.example.expensetracker.repository;

import com.example.expensetracker.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import com.example.expensetracker.ExpensetrackerApplication;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ContextConfiguration(classes = ExpensetrackerApplication.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Test
    void testFindByUsername() {
        User user = new User();
        user.setUsername("user");
        user.setPassword("123");
        userRepository.save(user);

        java.util.Optional<User> foundUser = userRepository.findByUsername("user");

        assertEquals(java.util.Optional.of(user), foundUser);
    }
}
