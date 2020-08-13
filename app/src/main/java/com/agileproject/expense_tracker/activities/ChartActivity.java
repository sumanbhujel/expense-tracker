package com.agileproject.expense_tracker.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.agileproject.expense_tracker.R;
import com.agileproject.expense_tracker.bll.TransactionBLL;
import com.agileproject.expense_tracker.helper.Helper;
import com.agileproject.expense_tracker.helper.UserSession;
import com.agileproject.expense_tracker.models.TransactionR;
import com.agileproject.expense_tracker.response.TransactionResponse;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChartActivity extends AppCompatActivity {

    private PieChart pieChart;
    private UserSession userSession;
    private TransactionBLL transactionBLL;
    private HashMap<String, Double> myTransactions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        userSession = new UserSession(this);
        transactionBLL = new TransactionBLL();
        myTransactions = new HashMap<>();

        pieChart = findViewById(R.id.transactions_piechart);
    }

    public void drawChart() {

        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5, 10, 5, 5);
        pieChart.setDragDecelerationFrictionCoef(0.95f);

        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleColor(Color.WHITE);
        pieChart.setTransparentCircleAlpha(110);

        pieChart.setHoleRadius(58f);
        pieChart.setTransparentCircleRadius(61f);
        pieChart.setDrawCenterText(true);
        pieChart.setRotationAngle(0);
        pieChart.setRotationEnabled(true);
        pieChart.setHighlightPerTapEnabled(true);

        pieChart.animateY(1200, Easing.EaseInOutQuad);

        Legend l = pieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setTextSize(14f);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        pieChart.setEntryLabelColor(Color.WHITE);
        pieChart.setEntryLabelTextSize(12f);

        ArrayList<PieEntry> pieChartArrayList = new ArrayList<>();
        for (Map.Entry<String, Double> entry : myTransactions.entrySet()) {
            pieChartArrayList.add(new PieEntry(entry.getValue().floatValue(), entry.getKey()+""));
        }

        PieDataSet pieDataSet = new PieDataSet(pieChartArrayList, "My Transactions");
        pieDataSet.setSliceSpace(3f);
        pieDataSet.setSelectionShift(5f);
        pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS);

        PieData pieData = new PieData(pieDataSet);
        pieData.setValueFormatter(new PercentFormatter(pieChart));
        pieData.setValueTextSize(15f);
        pieData.setValueTypeface(Typeface.DEFAULT_BOLD);
        pieData.setValueTextColor(Color.WHITE);

        pieChart.setData(pieData);
        pieChart.invalidate();
    }

    private void getIncomeTransactions() {
        myTransactions.clear();
        Helper.StrictMode();
        TransactionResponse incomes = transactionBLL.getIncomeTransactions(userSession.getUser().get_id());
        if (incomes != null) {
            for (TransactionR transaction : incomes.getMyTransactions()) {
                String key = transaction.getCategory().getName();
                if (myTransactions.containsKey(key)) {
                    myTransactions.put(key, myTransactions.get(key) + transaction.getAmount());
                } else {
                    myTransactions.put(key, transaction.getAmount());
                }
            }

            drawChart();
        }
    }

    private void getExpenseTransactions() {
        myTransactions.clear();
        Helper.StrictMode();
        TransactionResponse expenses = transactionBLL.getExpenseTransactions(userSession.getUser().get_id());
        if (expenses != null) {
            for (TransactionR transaction : expenses.getMyTransactions()) {
                String key = transaction.getCategory().getName();
                if (myTransactions.containsKey(key)) {
                    myTransactions.put(key, myTransactions.get(key) + transaction.getAmount());
                } else {
                    myTransactions.put(key, transaction.getAmount());
                }
            }

            drawChart();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_chart,menu);

        MenuItem menuItem = menu.findItem(R.id.sp_trans_type);
        Spinner spinner = (Spinner) menuItem.getActionView();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.transactions_type, R.layout.spinner_trans_type);
        adapter.setDropDownViewResource(R.layout.spinner_trans_type);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String transType = String.valueOf(parent.getItemAtPosition(position));
                if (transType.equals("Expense")) {
                    getExpenseTransactions();
                } else if (transType.equals("Income")) {
                    getIncomeTransactions();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(ChartActivity.this, "Nothing selected!!!", Toast.LENGTH_SHORT).show();
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}