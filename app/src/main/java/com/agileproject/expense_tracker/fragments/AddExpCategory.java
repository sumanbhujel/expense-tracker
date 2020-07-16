package com.agileproject.expense_tracker.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.agileproject.expense_tracker.R;


public class AddExpCategory extends Fragment {

    public AddExpCategory() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_add_exp_category, container, false);
        View expCategoryView = inflater.inflate(R.layout.fragment_add_exp_category, container, false);

        return expCategoryView;
    }
}