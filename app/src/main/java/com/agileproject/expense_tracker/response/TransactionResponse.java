package com.agileproject.expense_tracker.response;

import com.agileproject.expense_tracker.models.TransactionR;

import java.util.List;

public class TransactionResponse {

    private TransactionR transaction;
    private String message;
    private List<TransactionR> myTransactions;

    public TransactionR getTransaction() {
        return transaction;
    }

    public String getMessage() {
        return message;
    }

    public List<TransactionR> getMyTransactions() {
        return myTransactions;
    }
}
