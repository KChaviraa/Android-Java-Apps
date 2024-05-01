package com.example.djiaapp;

/*
This program will take the djia from 30 companies and will take the average. While it is computing
a progress bar will show its progress. At the end the average and the time elapsed are elapsed.
@author: Kate Chavira
@data: 04/08/2024
 */

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    TextView djia, time;
    Button compute;
    final String[] companies = {"AMZN", "AXP", "AMGN", "AAPL", "BA", "CAT", "CSCO",
            "CVX", "GS", "HD", "HON", "IBM", "INTC", "JNJ", "KO", "JPM", "MCD", "MMM", "MRK",
            "MSFT", "NKE", "PG", "TRV", "UNH", "CRM", "VZ", "V", "WMT", "DIS", "DOW"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressBar);
        djia = findViewById(R.id.djia);
        time = findViewById(R.id.time);
        compute = findViewById(R.id.compute);

        djia.setText("DJIA Average");

        compute.setOnClickListener(v -> {
            progressBar.setVisibility(View.VISIBLE);
            time.setVisibility(View.INVISIBLE);
            new DjiaAverage().execute();
        });
    }

    private class DjiaAverage extends AsyncTask<Void, Integer, String> {
        long start, end;

        @Override
        protected String doInBackground(Void... voids) {
            Double sum = 0.0;
            int tick = 1;
            int count = 0;
            int check = 3;
            String st;
            String[] ar;
            String[] s1;

            for (String acro : companies) {
                try {
                    URL url = new URL("https://query1.finance.yahoo.com/v8/finance/chart/" + acro + "?interval=2m");
                    URLConnection urlConnection = url.openConnection();
                    urlConnection.setRequestProperty("User-Agent",
                            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
                    InputStreamReader inputStreamReader = new InputStreamReader(urlConnection.getInputStream());
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    String line = bufferedReader.readLine();
                    ar = line.split("\"previousClose\":");
                    s1 = ar[1].split(",");
                    st = s1[0];
                    sum += Double.parseDouble(st);
                    Log.i("I am here", "sum is: " + sum);
                    bufferedReader.close();
                } catch (MalformedURLException mle) {
                    mle.printStackTrace();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                }
                count++;
                if (count == check) {
                    publishProgress(tick);
                    tick++;
                    check = check + 3;
                }
            }
            Double average = sum / 0.153; // Ensure sum is not zero before performing division
            String avg = average.toString();
            publishProgress(count);
            return avg;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            start = new Date().getTime();
            djia.setText("DJIA computation in progress ...");
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressBar.setVisibility(View.INVISIBLE);
            djia.setText("DJIA for the previous day close is \n" + String.format("%,.2f", Double.parseDouble(s)));
            end = new Date().getTime();
            double timeElapsed = ((double) end - start) / 1000;
            time.setText(String.format("Time taken for the computation is %.2f.", timeElapsed));
            time.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
        }
    }
}