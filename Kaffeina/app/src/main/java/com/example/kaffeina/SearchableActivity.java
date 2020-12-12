package com.example.kaffeina;

import android.Manifest;
import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.volley.Response;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchableActivity extends ListActivity implements LocationListener {
    final String api_key = "y4H3bZlkpQY_teceOJpBAeLjPnTrV-FHCcDT3CMObQf_AiPOqzStf5PmfkV60ch7URJz7JWXWlnUDuoWVc7xA08bLxe7fIQESu23V4yEAi8QUxQ-h1pjqFvy2dy1X3Yx";
    final String BASE_URL = "https://api.yelp.com/v3/";
    double userLat;
    double userLong;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        final ArrayList<YelpRestaurant> restaurants = new ArrayList<YelpRestaurant>();
        //final RestaurantsAdapter adapter = new RestaurantsAdapter(this, restaurants);

        // rvRestaurants.adapter = adapter;
        // rvRestaurants.layoutManager = new LinearLayoutManager(this);
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            ListAdapter queryAdapter = null;
            Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            YelpFunctions yelpClient = retrofit.create(YelpFunctions.class);
            yelpClient.searchRestaurants("Bearer "+api_key,query,"San Jose").enqueue(new Callback<YelpSearchResult>(){
                @Override
                public void onResponse(Call<YelpSearchResult> call, retrofit2.Response<YelpSearchResult> response) {
                    Log.i("SearchableActivity","onResponse" + response);
                    YelpSearchResult body = response.body();
                    if(body==null){
                        Log.w("SearchableActivity", "Response body not received");
                        return;
                    }
                    restaurants.addAll(body.restaurants);
                    //adapter.notifyAll();
                }
                @Override
                public void onFailure(Call<YelpSearchResult> call, Throwable t) {
                    Log.i("Searchable","onFailure" + t);
                }
            });
            setListAdapter(queryAdapter);
        }
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