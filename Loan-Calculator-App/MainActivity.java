package com.example.loancalculator;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;



public class MainActivity extends AppCompatActivity {


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            // Initialize UI components
            EditText loanAmount = findViewById(R.id.loan_amount);
            EditText APR = findViewById(R.id.APR);
            EditText Years = findViewById(R.id.LoanYears);
            TextView monthlyPayment = findViewById(R.id.monthly_payment);
            monthlyPayment.setVisibility(View.GONE);
            Button calculateButton = findViewById(R.id.Calculate_button);
            Button showTable = findViewById(R.id.Show_button);
            showTable.setVisibility(View.GONE);

            // Set OnClickListener for the Calculate button
            calculateButton.setOnClickListener(view -> {
                // Get input values from EditText fields
                String loanAmountStr = loanAmount.getText().toString();
                String aprStr = APR.getText().toString();
                String loanYearsStr = Years.getText().toString();

                if (TextUtils.isEmpty(loanAmountStr) || TextUtils.isEmpty(aprStr) || TextUtils.isEmpty(loanYearsStr)) {
                    // Display a toast message if any field is empty
                    Toast.makeText(MainActivity.this, "Please enter all values", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Convert input values to appropriate data types
                double p = Double.parseDouble(loanAmountStr);
                double apr = Double.parseDouble(aprStr);
                int years = Integer.parseInt(loanYearsStr);

                // Calculate monthly payment using the formula
                double r = apr / (100 * 12);
                // Convert loan years to total number of payments
                int n = years * 12;
                // Calculate monthly payment using the formula
                double monthlyPaymentValue = p * (r * Math.pow(1 + r, n)) / (Math.pow(1 + r, n) - 1);
                // Display the monthly payment
                monthlyPayment.setVisibility(View.VISIBLE);
                monthlyPayment.setText(String.format("Your Monthly Payment: $%.2f", monthlyPaymentValue));

                // Enable the "Show Table" button
                showTable.setVisibility(View.VISIBLE);

            });

            Button resetButton = findViewById(R.id.Reset_button);
            resetButton.setOnClickListener(view -> {
                loanAmount.setText("");
                APR.setText("");
                Years.setText("");
                monthlyPayment.setVisibility(View.GONE);
                showTable.setVisibility(View.GONE);
            });

            showTable.setOnClickListener(view -> {
                // Launch AmortizationTable activity
                Intent intent = new Intent(MainActivity.this, AmortizationTable.class);
                intent.putExtra("LOAN_AMOUNT", Double.parseDouble(loanAmount.getText().toString()));
                intent.putExtra("APR", Double.parseDouble(APR.getText().toString()));
                intent.putExtra("YEARS", Integer.parseInt(Years.getText().toString()));
                startActivity(intent);
                showTable.setVisibility(View.GONE);
            });
        }

// Create an ActivityResultLauncher instance using registerForActivityResult
ActivityResultLauncher<Intent> activityResultLaunch = registerForActivityResult(
        // Define the ActivityResultContract for starting the activity
        new ActivityResultContracts.StartActivityForResult(),
        new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    // Get data from the result Intent
                    Intent data = result.getData();
                    double totalInterestPaid = data.getDoubleExtra("TOTAL_INTEREST_PAID", 0.0);
                    // Update the UI with the total interest paid
                    Toast.makeText(MainActivity.this, "Over the period of the loan interest paid $" + totalInterestPaid, Toast.LENGTH_SHORT).show();
                }
            }
        });


}