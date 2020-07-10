package com.agileproject.expense_tracker.models;

public class User {
    private String _id, firstName, lastName, email, password;
    private double totalIncome,totalExpense;

    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public String get_id() {
        return _id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public double getTotalIncome() {
        return totalIncome;
    }

    public double getTotalExpense() {
        return totalExpense;
    }

}