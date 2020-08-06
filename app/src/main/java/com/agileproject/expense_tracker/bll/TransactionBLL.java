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

    //to get transaction list
    public TransactionResponse getTransactions(String creator) {
        TransactionResponse transactionResponse = null;
        Call<TransactionResponse> myTransactionsCall = transactionAPI.getMyTransactions(creator);
        try {
            Response<TransactionResponse> myTransactionsResponse = myTransactionsCall.execute();
            if (!myTransactionsResponse.isSuccessful()) {
                return transactionResponse;
            }
            transactionResponse = myTransactionsResponse.body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return transactionResponse;
    }

    // to get single transaction detail
    public TransactionResponse getSingleTransaction(String transactionId) {
        TransactionResponse transactionResponse = null;
        Call<TransactionResponse> singleTransactionCall = transactionAPI.fetchSingleTransaction(transactionId);
        try {
            Response<TransactionResponse> singleTransactionsResponse = singleTransactionCall.execute();
            if (!singleTransactionsResponse.isSuccessful()) {
                return transactionResponse;
            }
            transactionResponse = singleTransactionsResponse.body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return transactionResponse;
    }

    //to delete transaction
    public boolean deleteTransaction(String transactionId) {
        boolean transactionDeleted = false;
        Call<TransactionResponse> deleteTransactionCall = transactionAPI.deleteTransaction(transactionId);
        try {
            Response<TransactionResponse> deleteTransactionResponse = deleteTransactionCall.execute();
            if (!deleteTransactionResponse.isSuccessful()) {
                return transactionDeleted;
            }
            if (deleteTransactionResponse.body() != null) {
                transactionDeleted = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return transactionDeleted;
    }

    //to update transaction
    public TransactionResponse updateTransaction(String transactionId, Transaction transaction) {
        TransactionResponse transactionResponse = null;
        Call<TransactionResponse> updateTransactionCall = transactionAPI.updateTransaction(transactionId, transaction);
        try {
            Response<TransactionResponse> updateTransactionResponse = updateTransactionCall.execute();
            if (!updateTransactionResponse.isSuccessful()) {
//                apiError = gson.fromJson(updateTransactionResponse.errorBody().string(), APIError.class);
//                transactionListener.onError(apiError.getError());
                return transactionResponse;
            }
            if (updateTransactionResponse.body().getTransaction() != null) {
                transactionResponse = updateTransactionResponse.body();

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
