package com.agileproject.expense_tracker.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;

import com.agileproject.expense_tracker.R;
import com.agileproject.expense_tracker.adapters.TransactionAdapter;
import com.agileproject.expense_tracker.bll.TransactionBLL;
import com.agileproject.expense_tracker.helper.Helper;
import com.agileproject.expense_tracker.helper.UserSession;
import com.agileproject.expense_tracker.models.TransactionR;
import com.agileproject.expense_tracker.response.TransactionResponse;

import java.util.ArrayList;
import java.util.List;

public class TransactionListActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout swipeRefreshLayout;
    private TransactionAdapter transactionAdapter;
    private List<TransactionR> userTransactions;
    private TransactionBLL transactionBLL;
    private UserSession userSession;
    private String category = "";
    private String transactionId = "";
    private TransactionR transaction = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_list);
        getSupportActionBar().setTitle("Transaction List");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initComponents();


        if (transaction != null) {
            // populateTransactionDetails();
        }
    }

    private void initComponents() {


        userSession = new UserSession(this);
        transactionBLL = new TransactionBLL();
        userTransactions = new ArrayList<>();

        RecyclerView transactionsContainer = findViewById(R.id.main_recyclerview);
        transactionsContainer.setLayoutManager(new LinearLayoutManager(this));

        transactionAdapter = new TransactionAdapter(this, userTransactions);
        transactionsContainer.setAdapter(transactionAdapter);

        swipeRefreshLayout = findViewById(R.id.home_refresh);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent));
        swipeRefreshLayout.setOnRefreshListener(this);

    }

    public void showTransactions() {
        userTransactions.clear();
        Helper.StrictMode();
        swipeRefreshLayout.setRefreshing(true);
        TransactionResponse transactionResponse = transactionBLL.getTransactions(userSession.getUser().get_id());
        if (transactionResponse == null) {
            transactionAdapter.updateTransactionsList(userTransactions);
            swipeRefreshLayout.setRefreshing(false);

        } else {
            swipeRefreshLayout.setRefreshing(false);

            userTransactions.addAll(transactionResponse.getMyTransactions());
            transactionAdapter.updateTransactionsList(userTransactions);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        showTransactions();
    }

    @Override
    public void onRefresh() {
        showTransactions();
    }
}