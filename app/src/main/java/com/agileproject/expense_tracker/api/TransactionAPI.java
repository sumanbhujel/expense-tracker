package com.agileproject.expense_tracker.api;

import com.agileproject.expense_tracker.models.Transaction;
import com.agileproject.expense_tracker.response.TransactionResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface TransactionAPI {

    //add a new Transactions
    @POST("transactions")
    Call<TransactionResponse> addNewTransaction(@Body Transaction transaction);

    //    get user transactions
    @GET("transactions/users/{creator}")
    Call<TransactionResponse> getMyTransactions(@Path("creator") String creator);

    // get single transaction
    @GET("transactions/{id}")
    Call<TransactionResponse> fetchSingleTransaction(@Path("id") String transactionId);

    // delete a transaction
    @DELETE("transactions/{id}")
    Call<TransactionResponse> deleteTransaction(@Path("id") String transactionId);
}
