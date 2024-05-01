package com.example.quizapp;

public class Quiz {
    private String arr[] = {"Mexico City is the capital of Mexico",
            "Montreal is the capital of Canada",
            "Jerusalem is the capital of Israel",
            "Capital of New York state is New York",
            "There are 3 state names in the US that begin with the letter T"};
    private boolean arrVal[] = {true, false, true,false,false};

    String getThePrevious(int i){return arr[i]; }
    String getTheNext(int i){return arr[i]; }

    boolean getNextAns(int i){
        return arrVal[i];
    }
}