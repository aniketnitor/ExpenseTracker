package com.example.expensetracker.repository;

import com.example.expensetracker.model.Expense;
import com.example.expensetracker.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ExpenseRepositoryTest {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void testFindByUser_Username() {
        User user = new User();
        user.setUsername("user");
        user.setPassword("123");
        userRepository.save(user);

        Expense expense = new Expense();
        expense.setExpenseName("Expense 1");
        expense.setCategory("Category 1");
        expense.setAmount(100.0);
        expense.setDate(java.time.LocalDate.now());
        expense.setUser(user);

        expenseRepository.save(expense);

        java.util.List<Expense> expenses = expenseRepository.findByUser_Username("user");

        assertEquals(1, expenses.size());
    }
}
