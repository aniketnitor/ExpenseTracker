package com.example.expensetracker.controller;

import com.example.expensetracker.model.Expense;
import com.example.expensetracker.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping("/expenses")
    public List<Expense> getExpenses(@RequestParam String username) {
        return expenseService.getExpensesByUser(username);
    }

    @PostMapping("/expenses")
    public ResponseEntity<Expense> addExpense(@RequestBody Expense expense, @RequestParam String username) {
        Expense addedExpense = expenseService.addExpense(expense, username);
        if (addedExpense != null) {
            return ResponseEntity.ok(addedExpense);
        }
        return ResponseEntity.status(400).body(null);
    }

    @PutMapping("/expenses/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable Long id, @RequestBody Expense expense) {
        Optional<Expense> updatedExpense = expenseService.updateExpense(id, expense);
        return updatedExpense.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(404).body(null));
    }

    @DeleteMapping("/expenses/{id}")
    public ResponseEntity<String> deleteExpense(@PathVariable Long id) {
        if (expenseService.deleteExpense(id)) {
            return ResponseEntity.ok("Expense deleted successfully");
        }
        return ResponseEntity.status(404).body("Expense not found");
    }
}