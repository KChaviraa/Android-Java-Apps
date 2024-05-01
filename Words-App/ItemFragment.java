package com.example.wordsapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.ListFragment;

public class ItemFragment extends ListFragment {
    OnItemSelectedListener mCallback;

    public interface OnItemSelectedListener {
        public void onItemSelected(int position);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        mCallback.onItemSelected(position);
        getListView().setItemChecked(position, true);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity a;
        if (context instanceof Activity){
            a=(Activity) context;
            try{
                mCallback = (OnItemSelectedListener) a;
            }
            catch (ClassCastException e){
                throw new ClassCastException(a.toString() + " must implement OnItemSelectedListener");
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int layout = android.R.layout.simple_list_item_1;
        setListAdapter(new ArrayAdapter(getActivity(),layout,WordInfo.LETTERS));
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        FragmentManager manager = this.getParentFragmentManager();
        if (manager.findFragmentById(R.id.item_fragment) != null){
            getListView().setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        }
    }
}