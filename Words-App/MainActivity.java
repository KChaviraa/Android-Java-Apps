package com.example.wordsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends FragmentActivity implements ItemFragment.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // if using large layout, use the SupportFragmentManager
        if(findViewById(R.id.fragment_container) != null){
            if(savedInstanceState != null){
                return;
            }
            ItemFragment firstFragment = new ItemFragment();
            firstFragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, firstFragment).commit();
        }
    }

    public void onItemSelected(int position){
        TextFragment textFrag = (TextFragment) getSupportFragmentManager().findFragmentById(R.id.text_fragment);
        if(textFrag != null){
            textFrag.updateTextView(position);
        }
        else{
            TextFragment newFragment = new TextFragment();
            Bundle args = new Bundle();
            args.putInt(TextFragment.ARG_POSITION, position);
            newFragment.setArguments(args);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}