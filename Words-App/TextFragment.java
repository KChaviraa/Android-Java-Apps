package com.example.wordsapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

public class TextFragment extends Fragment {

    final static String ARG_POSITION = "position";
    int mCurrentPosition = -1;


    public void updateTextView(int position){
        TextView tv = getActivity().findViewById(R.id.text);
        tv.setText(WordInfo.WORDS[position]);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.text_view, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        Bundle args = getArguments();
        if (args != null) {
            updateTextView(args.getInt(ARG_POSITION));
        } else if (mCurrentPosition != -1) {
            updateTextView(mCurrentPosition);
        } else {
            TextView tv = getActivity().findViewById(R.id.text);
            tv.setText("Select an item from the list");
        }
    }

    @Override
    public void onSaveInstanceState( Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(ARG_POSITION, mCurrentPosition);
    }
}