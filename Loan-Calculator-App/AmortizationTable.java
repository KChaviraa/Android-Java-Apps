package com.example.loancalculator;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class AmortizationTable extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amortization_table);

        //Retrieve data passed from MainActivity
        double loanAmount = getIntent().getDoubleExtra("LOAN_AMOUNT", 0);
        double apr = getIntent().getDoubleExtra("APR", 0);
        int years = getIntent().getIntExtra("YEARS", 0);

        // calculate number of payments and monthly payment amount
        int n = years * 12;
        double r= apr / 100 / 12;
        double monthlyPayment = loanAmount * (r * Math.pow(1 + r, n)) / (Math.pow(1 + r, n) - 1);

        //Initialize views
        TextView loanAmountTextView = findViewById(R.id.loan_amount);
        TextView numberOfPaymentsTextView = findViewById(R.id.number_of_payments);
        TableLayout amortizationTableLayout = findViewById(R.id.amortization_table_layout);


        // set loan amount and number of payments
        loanAmountTextView.setText(String.format("Your loan amount: $%.2f", loanAmount));
        loanAmountTextView.setTextColor(Color.parseColor("#7FFFD4"));
        numberOfPaymentsTextView.setText(String.format("Your number of payments: %d", n));
        numberOfPaymentsTextView.setTextColor(Color.parseColor("#808000"));

        //calculate and display amortization table
        double balance = loanAmount;
        for (int i = 1; i <= n; i++) {
            double interest = balance * r;
            double principal = monthlyPayment - interest;
            balance -= principal;

            TableRow row = new TableRow(this);

            TextView paymentNumberTextView = new TextView(this);
            paymentNumberTextView.setText(String.valueOf(i));
            paymentNumberTextView.setPadding(20, 0, 10, 0);
            if (i % 2 == 0) {
                paymentNumberTextView.setTextColor(Color.GREEN); // Set text color to green for even rows
            } else {
                paymentNumberTextView.setTextColor(Color.BLUE); // Set text color to blue for odd rows
            }
            row.addView(paymentNumberTextView);

            TextView monthlyPaymentTextView = new TextView(this);
            monthlyPaymentTextView.setText(String.format(Locale.getDefault(), "$%.2f", monthlyPayment));
            monthlyPaymentTextView.setPadding(0, 0, 20, 0);
            if (i % 2 == 0) {
                monthlyPaymentTextView.setTextColor(Color.GREEN); // Set text color to green for even rows
            } else {
                monthlyPaymentTextView.setTextColor(Color.BLUE); // Set text color to blue for odd rows
            }
            row.addView(monthlyPaymentTextView);

            TextView interestTextView = new TextView(this);
            interestTextView.setText(String.format("%.2f", interest));
            interestTextView.setPadding(0, 0, 20, 0);
            if (i % 2 == 0) {
                interestTextView.setTextColor(Color.GREEN); // Set text color to green for even rows
            } else {
                interestTextView.setTextColor(Color.BLUE); // Set text color to blue for odd rows
            }
            row.addView(interestTextView);

            TextView principalTextView = new TextView(this);
            principalTextView.setText(String.format("%.2f", principal));
            principalTextView.setPadding(0, 0, 20, 0);
            if (i % 2 == 0) {
                principalTextView.setTextColor(Color.GREEN); // Set text color to green for even rows
            } else {
                principalTextView.setTextColor(Color.BLUE); // Set text color to blue for odd rows
            }
            row.addView(principalTextView);

            TextView balanceTextView = new TextView(this);
            balanceTextView.setText(String.format("%.2f", balance));
            balanceTextView.setPadding(0, 0, 20, 0);
            if (i % 2 == 0) {
                balanceTextView.setTextColor(Color.GREEN); // Set text color to green for even rows
            } else {
                balanceTextView.setTextColor(Color.BLUE); // Set text color to blue for odd rows
            }
            row.addView(balanceTextView);

            amortizationTableLayout.addView(row);
        }
    }

    public void finishAndReturnTotalInterest(double totalInterestPaid) {
        Intent intent = new Intent();
        intent.putExtra("TOTAL_INTEREST_PAID", totalInterestPaid);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}