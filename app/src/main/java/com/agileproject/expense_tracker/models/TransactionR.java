package com.agileproject.expense_tracker.models;

public class TransactionR {


    private String _id, note, type, creator, date;
    private double amount;
    private Category category;

    public String get_id() {
        return _id;
    }

    public String getNote() {
        return note;
    }

    public String getType() {
        return type;
    }

    public String getCreator() {
        return creator;
    }

    public String getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public Category getCategory() {
        return category;
    }
}
