package com.example.kaffeina;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface YelpFunctions {
    @GET("businesses/search")
    Call<YelpSearchResult> searchRestaurants(
            @Header("Authorization") String api_key,
            @Query("searchTerm") String searchTerm,
            @Query("location") String location
    );
}
