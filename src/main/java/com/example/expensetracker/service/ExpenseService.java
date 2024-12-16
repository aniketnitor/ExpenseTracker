package com.example.expensetracker.service;

import com.example.expensetracker.model.Expense;
import com.example.expensetracker.model.User;
import com.example.expensetracker.repository.ExpenseRepository;
import com.example.expensetracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExpenseRepository expenseRepository;

    public Optional<User> authenticateUser(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return user;
        }
        return Optional.empty();
    }

    public List<Expense> getExpensesByUser(String username) {
        return expenseRepository.findByUser_Username(username);
    }

    public Expense addExpense(Expense expense, String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            expense.setUser(user.get());
            return expenseRepository.save(expense);
        }
        return null;
    }

    public Optional<Expense> updateExpense(Long id, Expense expense) {
        Optional<Expense> existingExpense = expenseRepository.findById(id);
        if (existingExpense.isPresent()) {
            Expense updatedExpense = existingExpense.get();
            updatedExpense.setExpenseName(expense.getExpenseName());
            updatedExpense.setCategory(expense.getCategory());
            updatedExpense.setAmount(expense.getAmount());
            updatedExpense.setDate(expense.getDate());
            return Optional.of(expenseRepository.save(updatedExpense));
        }
        return Optional.empty();
    }

    public boolean deleteExpense(Long id) {
        if (expenseRepository.existsById(id)) {
            expenseRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
