package com.agileproject.expense_tracker.fragments;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.agileproject.expense_tracker.helper.ConfirmationDialog;
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
    //private TransactionR transaction;
    //private TransactionBLL transactionBLL;

    @Override
    public void onSure() {

    }

    @Override
    public void onCancel() {

    }
}
