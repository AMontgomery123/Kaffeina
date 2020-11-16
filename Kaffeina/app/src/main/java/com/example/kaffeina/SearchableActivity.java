package com.example.kaffeina;

import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ListAdapter;

public class SearchableActivity extends ListActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        // Get the intent, verify the action and get the query
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            ListAdapter queryAdapter = doMySearch(query); //implement this
            setListAdapter(queryAdapter);
        }
    }
    ListAdapter doMySearch(String query){
        //makes the API call?
        //returns fields and returns them as an Adapter?
        //see android.net
        //see "Creating a Progress Dialog" to learn how to display a progress wheel
        return null;
    }
}