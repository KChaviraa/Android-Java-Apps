package com.example.cardvalidator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etCardNumber;
    private Button btnValidate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etCardNumber = findViewById(R.id.etCardNumber);
        btnValidate = findViewById(R.id.btnValidate);

        btnValidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cardNumber = etCardNumber.getText().toString().trim();

                // Check if card number is 15 digits long and starts with 37 or 34
                if (cardNumber.length() != 15 || (!cardNumber.startsWith("37") && !cardNumber.startsWith("34"))) {
                    Toast.makeText(MainActivity.this, "Invalid input! Number must be 15 digits long and start with 37 or 34.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Reverse the digits and exclude the first digit
                StringBuilder reversedCardNumber = new StringBuilder(cardNumber.substring(0, cardNumber.length() - 1)).reverse();

                int sum = 0;
                for (int i = 0; i < reversedCardNumber.length(); i++) {
                    int digit = Character.getNumericValue(reversedCardNumber.charAt(i));

                    // Double the value of odd position numbers and subtract 9 if greater than 9
                    if (i % 2 == 0) {
                        digit *= 2;
                        if (digit > 9) {
                            digit -= 9;
                        }
                    }

                    sum += digit;
                }

                // Add the last digit of the original number to the sum
                sum += Character.getNumericValue(cardNumber.charAt(cardNumber.length() - 1));

                // Check if sum is divisible by 10
                if (sum % 10 == 0) {
                    Toast.makeText(MainActivity.this, "Valid credit card number!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Invalid credit card number, the sum is not a multiple of 10!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}