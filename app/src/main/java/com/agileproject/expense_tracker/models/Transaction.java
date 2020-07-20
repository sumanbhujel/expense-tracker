package com.agileproject.expense_tracker.models;

public class Transaction {

    private String note, type, creator, date, category;
    private double amount;

    public Transaction(String note, String type, String creator, String date, String category, double amount) {
        this.note = note;
        this.type = type;
        this.creator = creator;
        this.date = date;
        this.category = category;
        this.amount = amount;
    }

    public Transaction(String note, String type, String creator, String date, String category) {
        this.note = note;
        this.type = type;
        this.creator = creator;
        this.date = date;
        this.category = category;
    }

    public Transaction(String note, String type, String date, double amount) {
        this.note = note;
        this.type = type;
        this.date = date;
        this.amount = amount;
    }
}
