package com.example.expensetracker.service;

import com.example.expensetracker.model.Expense;
import com.example.expensetracker.model.User;
import com.example.expensetracker.repository.ExpenseRepository;
import com.example.expensetracker.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExpenseServiceTest {

    @Mock
    private ExpenseRepository expenseRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private ExpenseService expenseService;

    @Test
    void testGetExpensesByUser() {
        User user1 = new User();
        user1.setUsername("user");
        user1.setPassword("123");
        userRepository.save(user1);

        Expense expense1 = new Expense();
        expense1.setExpenseName("Expense 1");
        expense1.setCategory("Category 1");
        expense1.setAmount(100.0);
        expense1.setDate(java.time.LocalDate.now());
        expense1.setUser(user1);

        Expense expense2 = new Expense();
        expense2.setExpenseName("Expense 2");
        expense2.setCategory("Category 2");
        expense2.setAmount(200.0);
        expense2.setDate(java.time.LocalDate.now());
        expense2.setUser(user1);

        when(expenseRepository.findByUser_Username("user")).thenReturn(java.util.List.of(expense1, expense2));

        java.util.List<Expense> expenses = expenseService.getExpensesByUser("user");

        assertEquals(2, expenses.size());
    }

    @Test
    void testAddExpense() {
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

        when(userService.authenticateUser("user", "123")).thenReturn(Optional.of(user));
        when(expenseRepository.save(expense)).thenReturn(expense);

        Expense addedExpense = expenseService.addExpense(expense, "user");

        assertEquals(expense, addedExpense);
    }


    @Test
    void testUpdateExpense() {
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

        when(expenseRepository.findById(1L)).thenReturn(java.util.Optional.of(expense));
        when(expenseRepository.save(expense)).thenReturn(expense);

        java.util.Optional<Expense> updatedExpense = expenseService.updateExpense(1L, expense);

        assertEquals(java.util.Optional.of(expense), updatedExpense);
    }

    @Test
    void testDeleteExpense() {
        when(expenseRepository.existsById(1L)).thenReturn(true);

        boolean deleted = expenseService.deleteExpense(1L);

        assertTrue(deleted);
    }
}