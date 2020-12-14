package com.example.kaffeina;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class YelpRestaurant {
    String name;
    Double rating;
    String price;
    @SerializedName("review_count") int numReviews;
    @SerializedName("distance") Double distanceInMeters;
    @SerializedName("image_url") String imageUrl;
    @SerializedName("categories") List<Category> categories;
    YelpLocation location;
    String displayDistance(){
        Double milesPerMeter = 0.000621371;
        String distanceInMiles = "%.2f".format(String.valueOf(distanceInMeters * milesPerMeter));
        return distanceInMiles;
    }
}
