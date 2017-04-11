package com.kinsa.rest.model;


import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.kinsa.rest.data.CoffeeShopList;
import com.kinsa.rest.data.ProjectProperty;
import com.kinsa.rest.util.Helper;

import java.util.List;

public class CoffeeShopTest {

    public static void main(String[] args) {

        String dataFile             = ProjectProperty.dataFile; //  "locations.csv" ; // Default data file in JSON format.

        // Test to see if reading locations from a file works
        List<CoffeeShop> csList = CoffeeShopList.getInstance();
        Helper.readCoffeeShopLocation(csList,dataFile);

        System.out.println(" (after reading from file) Number of location in memory" + csList.size());
        System.out.println( "##################################3");

        // Invoke Geocoding to find out Latitude and Longitude information from
        GeoApiContext context = new GeoApiContext().setApiKey(ProjectProperty.geocodingKey);
        GeocodingResult[] results =  null ;
        String inputAddress = "520 Logue Avenue, Mountain View, CA 94043, USA"; // // "252 Guerrero St, San Francisco, CA 94103, USA";  // 37.76712520,-122.42451350
        try {
            results = GeocodingApi.geocode(context, inputAddress).await();

            System.out.println("Input Address : "+inputAddress);
            System.out.println(results[0].formattedAddress);
            System.out.printf(results[0].geometry.location.toString());
        }catch (Exception googleAPI_exception) {
            System.out.println(" Google GeoCoding API issue : "+googleAPI_exception.getMessage());
            googleAPI_exception.printStackTrace();
        }

    }

}
