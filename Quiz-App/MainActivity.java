MainActivity.java

package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button previousButton = findViewById(R.id.previousButton);
        Button nextButton = findViewById(R.id.nextButton);
        TextView textView = findViewById(R.id.textView);
        Button falseButton = findViewById(R.id.falseButton);
        Button trueButton = findViewById(R.id.trueButton);
        Quiz my_q_a = new Quiz();
        textView.setTextColor(Color.BLACK);
        textView.setTextSize(18f);
        textView.setText(my_q_a.getThePrevious(i).toString());
        textView.setText(my_q_a.getTheNext(i).toString());
        TextView tv = findViewById(R.id.answer);
        tv.setTextColor(Color.BLACK);
        tv.setTextSize(18f);

        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv.setText("");
                textView.setText(my_q_a.getThePrevious(--i % 5).toString());
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv.setText("");
                textView.setText(my_q_a.getTheNext(++i % 5).toString());
            }
        });
        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (my_q_a.getNextAns(i % 5)) {
                    tv.setText("Your choice is correct");
                } else {
                    tv.setText("Your choice is not correct");
                }
            }
        });
        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!my_q_a.getNextAns(i % 5)) {
                    tv.setText("Your choice is correct");
                } else {
                    tv.setText("Your choice is not correct");
                }
            }
        });
    }
}