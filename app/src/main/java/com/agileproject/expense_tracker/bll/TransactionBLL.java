package com.agileproject.expense_tracker.bll;

import com.agileproject.expense_tracker.api.TransactionAPI;
import com.agileproject.expense_tracker.helper.ApiError;
import com.agileproject.expense_tracker.helper.Retrofit;
import com.agileproject.expense_tracker.models.Errors;
import com.agileproject.expense_tracker.models.Transaction;
import com.agileproject.expense_tracker.response.TransactionResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class TransactionBLL {

    private Gson gson;
    private ApiError apiError;
    private TransactionAPI transactionAPI;
    private TransactionListener transactionListener;

    public TransactionBLL() {
        transactionAPI = Retrofit.getInstance().create(TransactionAPI.class);
        gson = new GsonBuilder().create();
        apiError = new ApiError();
    }

    public TransactionResponse addNewTransaction(Transaction transaction) {
        TransactionResponse transactionResponse = null;
        Call<TransactionResponse> addTransactionCall = transactionAPI.addNewTransaction(transaction);
        try {
            Response<TransactionResponse> addTransactionResponse = addTransactionCall.execute();
            if (!addTransactionResponse.isSuccessful()) {
//                apiError = gson.fromJson(addTransactionResponse.errorBody().string(), ApiError.class);
//                transactionListener.onError(apiError.getErrors());
                return transactionResponse;
            } else if (addTransactionResponse.body().getTransaction() != null) {
                transactionResponse = addTransactionResponse.body();
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
        return transactionResponse;
    }

    public interface TransactionListener {
        void onError(Errors error);
    }

    public void setTransactionListener(TransactionListener transactionListener) {
        this.transactionListener = transactionListener;
    }
}
