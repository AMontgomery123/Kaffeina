package com.example.kaffeina;

import android.Manifest;
import android.app.Activity;
import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Response;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchableActivity extends Activity implements LocationListener {
    final String api_key = "8qAmQjja1BpFCrjRjK07GiQaHwrpQUGMZRMrHmLpbYIW0_JZiwD8iLNNzPgLrpGw4Yqe035LyzxkyKbZ0-OQCKkJ5lFPjbgWyl-ajdiCY_lS_fnRQGsE7BKJTY_ZX3Yx";
    final String BASE_URL = "https://api.yelp.com/v3/";
    double userLat;
    double userLong;
    RecyclerView rvRestaurants;
    EditText search_bar;
    Button go_button;
    List restaurantList;
    ArrayAdapter adapter;
    ListView resultsList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        go_button = (Button)findViewById(R.id.goButton);
        search_bar = findViewById(R.id.searchQuery);
        resultsList = (ListView)findViewById(R.id.searchList);

        go_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query =  search_bar.getText().toString();

                final ArrayList<YelpRestaurant> restaurants = new ArrayList<YelpRestaurant>();

                    Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    YelpFunctions yelpClient = retrofit.create(YelpFunctions.class);
                    yelpClient.searchRestaurants("Bearer "+api_key,query,"San Francisco").enqueue(new Callback<YelpSearchResult>(){
                        @Override
                        public void onResponse(Call<YelpSearchResult> call, retrofit2.Response<YelpSearchResult> response) {
                            Log.i("SearchableActivity","onResponse" + response);
                            YelpSearchResult body = response.body();
                            if(body==null){
                                Log.w("SearchableActivity", "Response body not received");
                                return;
                            }
                            restaurants.addAll(body.restaurants);
//                            restaurantList = new ArrayList<String>();
//                            int num = 0;
//                            if(restaurants.size() <10){
//                                num = restaurants.size();
//                            }
//
//                            for (int i = 0; i < num; i++){
//                                restaurantList.add(restaurants.get(i).name);
//                            }

                            adapter = new ArrayAdapter(SearchableActivity.this, android.R.layout.simple_list_item_1, restaurants);
                            resultsList.setAdapter(adapter);
                        }
                        @Override
                        public void onFailure(Call<YelpSearchResult> call, Throwable t) {
                            Log.i("Searchable","onFailure" + t);
                        }
                    });
                    //setListAdapter(queryAdapter);
            }
        });

    }

    //LocationListener
    @Override
    public void onLocationChanged(@NonNull Location location) {
        userLat = location.getLatitude();
        userLong = location.getLongitude();
        FusedLocationProviderClient client = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        client.getLastLocation()
                .addOnCompleteListener(this, new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        // ...
                    }
                });
    }
}