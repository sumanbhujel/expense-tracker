package com.agileproject.expense_tracker.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.agileproject.expense_tracker.R;
import com.agileproject.expense_tracker.bll.TransactionBLL;
import com.agileproject.expense_tracker.fragments.DatePickerFragment;
import com.agileproject.expense_tracker.fragments.ExpCategoriesBottomSheet;
import com.agileproject.expense_tracker.helper.EditTextValidation;
import com.agileproject.expense_tracker.helper.Helper;
import com.agileproject.expense_tracker.helper.UserSession;
import com.agileproject.expense_tracker.models.Category;
import com.agileproject.expense_tracker.models.Transaction;
import com.agileproject.expense_tracker.models.TransactionR;
import com.agileproject.expense_tracker.response.TransactionResponse;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class AddExpenseActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener,
        View.OnClickListener, ExpCategoriesBottomSheet.ExpBottomSheetListener {



    private TextInputLayout etTransanctionNote, etTransactionAmount, etTransactionDate, etTransactionCategory;
    private TransactionBLL transactionBLL;
    private String transactionType = "", creator;
    private Calendar calendar;
    private CircleImageView categoryIcon;
    private ExpCategoriesBottomSheet expCategoriesBS;
    private String category = "";
    private String categoryName = "";
    private TransactionR transaction = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);
        getSupportActionBar().setTitle("Expense Transaction");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initComponents();

        transaction = (TransactionR) getIntent().getSerializableExtra("UPDATE_TRANSACTION1");
        if (transaction != null) {
            populateTransactionDetails();
        }

    }

    private void initComponents() {

        UserSession userSession = new UserSession(this);
        creator = userSession.getUser().get_id();
        transactionBLL = new TransactionBLL();

        // categoryIcon = findViewById(R.id.category_icon);
        etTransactionCategory = findViewById(R.id.et_exp_category);
        etTransanctionNote = findViewById(R.id.et_note);
        etTransactionAmount = findViewById(R.id.et_amount);
        etTransactionDate = findViewById(R.id.et_date);

        Button btnAddTransaction = findViewById(R.id.btn_save_exp);
        calendar = Calendar.getInstance();

        setToday();


        etTransactionCategory.getEditText().setOnClickListener(this);
        etTransactionDate.getEditText().setOnClickListener(this);
        btnAddTransaction.setOnClickListener(this);


    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        calendar.set(year, month, dayOfMonth);
        setDate(calendar.getTime());
    }

    private void setDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd", Locale.getDefault());
        String selectedDate = dateFormat.format(date);
        etTransactionDate.getEditText().setText(selectedDate);
    }

    private void setToday() {
        calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        setDate(calendar.getTime());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.et_exp_category_value:
                transactionType = "Expense";
                expCategoriesBS = new ExpCategoriesBottomSheet();
                expCategoriesBS.show(getSupportFragmentManager(), "EXPENSE CATEGORIES");
                break;

            case R.id.et_date_value:
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "TRANSACTION DATE");
                break;

            case R.id.btn_save_exp:
                if (transaction == null) {
                    addNewTransaction();
                } else {
                    // updateTransaction();
                }
                break;
        }
    }

    @Override
    public void onExpCatSelected(Category expCategory) {
        category = expCategory.get_id();
        categoryName = expCategory.getName();
        Helper.setIcon(expCategory.getIcon(), categoryIcon);
        etTransactionCategory.getEditText().setText(categoryName);
        expCategoriesBS.dismiss();
    }

    private void addNewTransaction() {
        if (transaction == null && transactionType.equals("")) {
            Toast.makeText(this, "Please select a transaction type!", Toast.LENGTH_LONG).show();
        } else if (!EditTextValidation.isEmpty(etTransanctionNote) & !EditTextValidation.isEmpty(etTransactionAmount)) {
            Helper.StrictMode();
            String note = etTransanctionNote.getEditText().getText().toString().trim();
            double amount = Double.parseDouble(etTransactionAmount.getEditText().getText().toString().trim());
            String transactionDate = etTransactionDate.getEditText().getText().toString().trim();
            Transaction transaction = new Transaction(note, transactionType, creator, transactionDate, category, amount);
            TransactionResponse transactionResponse = transactionBLL.addNewTransaction(transaction);
            if (transactionResponse != null) {
                Toast.makeText(this, transactionResponse.getMessage(), Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    private void populateTransactionDetails() {
        category = transaction.getCategory().get_id();
        Toast.makeText(this, "1st " + category, Toast.LENGTH_SHORT).show();
        Helper.setIcon(transaction.getCategory().getIcon(), categoryIcon);
        etTransanctionNote.getEditText().setText(transaction.getNote());
        etTransactionAmount.getEditText().setText(String.valueOf(transaction.getAmount()));
        etTransactionDate.getEditText().setText(Helper.formatDate("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", "YYYY-MM-dd", transaction.getDate()));
        String transType = transaction.getCategory().getType();


    }

}