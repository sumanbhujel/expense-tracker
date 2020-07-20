package com.agileproject.expense_tracker.api;

import com.agileproject.expense_tracker.models.Transaction;
import com.agileproject.expense_tracker.response.TransactionResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface TransactionAPI {

    //add a new Transactions
    @POST("transactions")
    Call<TransactionResponse> addNewTransaction(@Body Transaction transaction);
}
