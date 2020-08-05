package com.agileproject.expense_tracker.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.agileproject.expense_tracker.R;
import com.agileproject.expense_tracker.activities.TransactionListActivity;
import com.agileproject.expense_tracker.bll.TransactionBLL;
import com.agileproject.expense_tracker.helper.ConfirmationDialog;
import com.agileproject.expense_tracker.helper.Helper;
import com.agileproject.expense_tracker.helper.UserSession;
import com.agileproject.expense_tracker.models.TransactionR;
import com.agileproject.expense_tracker.response.TransactionResponse;
import com.google.android.material.textfield.TextInputLayout;

import de.hdodenhof.circleimageview.CircleImageView;

public class TransactionDetailDialog extends AppCompatDialogFragment implements ConfirmationDialog.ConfirmationDialogListener {

    private TextView tvCategory, tvType;
    private TextInputLayout tvAmount, tvDate, tvNote;
    private ImageView transactionImage;
    private String creator, transactionId = "";
    private String category = "";
    private CircleImageView categoryIcon;
    private ConfirmationDialog deleteTransDialog;
    private TransactionR transaction;
    private TransactionBLL transactionBLL;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder updateTran = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.transaction_detail_dialog, null);
        updateTran.setView(view)
                .setCancelable(false)
                .setTitle("Update Transaction!");

        UserSession userSession = new UserSession(getContext());
        creator = userSession.getUser().get_id();
        transactionBLL = new TransactionBLL();

        transactionImage = view.findViewById(R.id.iv_trans_icon);
        tvCategory = view.findViewById(R.id.trans_cat_name);
        tvType = view.findViewById(R.id.dt_type_value);
        tvAmount = view.findViewById(R.id.dt_tran_amount);
        tvDate = view.findViewById(R.id.dt_tran_date);
        tvNote = view.findViewById(R.id.dt_tran_note);


        if (transaction != null) {
            populateTransactionDetails();
        }

        ImageButton btnDeleteTransaction = view.findViewById(R.id.btn_delete_transaction);
        btnDeleteTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //((TransactionListActivity) getContext()).confirmCategoryDelete(transactionId);
                //dismiss();

            }
        });

        Button btnUpdate = view.findViewById(R.id.btn_tran_update);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //updateTransaction();
               // Toast.makeText(getActivity(), "amoount"+tvAmount.getEditText().getText().toString().trim(), Toast.LENGTH_SHORT).show();
            }
        });


        return updateTran.create();
    }

    private void getTransactionDetail() {
        Helper.StrictMode();
        if (!transactionId.equals("")) {
            TransactionResponse transactionResponse = transactionBLL.getSingleTransaction(transactionId);
            if (transactionResponse != null) {
                transaction = transactionResponse.getTransaction();
                populateDetails(transaction);
            }
        }
    }

    public void getTransaction(String tranId) {
        transactionId = tranId;
        Helper.StrictMode();

    }


    private void populateDetails(TransactionR transaction) {
        tvCategory.setText(transaction.getCategory().getName());
        tvType.setText(transaction.getType());
        tvAmount.getEditText().setText(String.valueOf(transaction.getAmount()));
        tvDate.getEditText().setText(Helper.formatDate("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", "MM/dd/YYYY", transaction.getDate()));
        tvNote.getEditText().setText(transaction.getNote());
        Helper.setIcon(transaction.getCategory().getIcon(), transactionImage);
    }

    private void populateTransactionDetails() {
        category = transaction.getCategory().get_id();
        Toast.makeText(getContext(), "1st " + category, Toast.LENGTH_SHORT).show();
        Helper.setIcon(transaction.getCategory().getIcon(), categoryIcon);
        tvNote.getEditText().setText(transaction.getNote());
        tvAmount.getEditText().setText(String.valueOf(transaction.getAmount()));
        tvDate.getEditText().setText(Helper.formatDate("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", "YYYY-MM-dd", transaction.getDate()));

    }

    @Override
    public void onSure() {

    }

    @Override
    public void onCancel() {

    }
}
