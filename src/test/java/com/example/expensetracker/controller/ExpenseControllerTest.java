package com.example.expensetracker.controller;

import com.example.expensetracker.model.Expense;
import com.example.expensetracker.model.User;
import com.example.expensetracker.service.ExpenseService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExpenseControllerTest {

    @Mock
    private ExpenseService expenseService;

    @InjectMocks
    private ExpenseController expenseController;

    @Test
    void testGetExpenses() {
        User user1 = new User();
        user1.setUsername("user");
        user1.setPassword("123");

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

        when(expenseService.getExpensesByUser("user")).thenReturn(List.of(expense1, expense2));

        List<Expense> response = expenseController.getExpenses("user");

        assertEquals(2, response.size());
    }

    @Test
    void testAddExpense() {
        User user = new User();
        user.setUsername("user");
        user.setPassword("123");

        Expense expense = new Expense();
        expense.setExpenseName("Expense 1");
        expense.setCategory("Category 1");
        expense.setAmount(100.0);
        expense.setDate(java.time.LocalDate.now());
        expense.setUser(user);

        when(expenseService.addExpense(expense, "user")).thenReturn(expense);

        ResponseEntity<Expense> response = expenseController.addExpense(expense, "user");

        assertEquals(expense, response.getBody());
    }
}