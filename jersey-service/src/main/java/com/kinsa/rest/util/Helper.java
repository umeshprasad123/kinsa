package com.kinsa.rest.util;

import com.kinsa.rest.model.CoffeeShop;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;


public class Helper {

    public static void readCoffeeShopLocation ( List<CoffeeShop> csList , String fileName ) {

        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {
            System.out.println( fileName);
            br = new BufferedReader(new FileReader(fileName));

            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] location = line.split(cvsSplitBy);
                csList.add(new CoffeeShop(Long.parseLong(location[0]),location[1],location[2],Double.parseDouble(location[3]),Double.parseDouble(location[4])));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static CoffeeShop findById( List<CoffeeShop> cList, long id) {
        for ( CoffeeShop cs : cList) {
            if (cs.getId() == id) {
                return cs;
            }
        }
        return null;
    }

    public  static CoffeeShop findNearestCoffeeShopbyLngLat(List<CoffeeShop> cList, double lat, double lng )  { // 37.76712520,-122.42451350
        CoffeeShop closestCoffeeShop = null ;

        double distanceBtwTwo = Double.MAX_VALUE;
        double tmpDistanceBtw;

        for ( CoffeeShop cs : cList) {
            tmpDistanceBtw = distFrom(cs.getLatitude(),cs.getLongitude(),lat,lng);
            if (tmpDistanceBtw  < distanceBtwTwo ){
                closestCoffeeShop = cs;
                distanceBtwTwo = tmpDistanceBtw;
            }
        }
        return closestCoffeeShop;
    }




    private static double distFrom(double lat1, double lng1, double lat2, double lng2) {
        double earthRadius = 6371000; //meters
        double dLat = Math.toRadians(lat2-lat1);
        double dLng = Math.toRadians(lng2-lng1);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLng/2) * Math.sin(dLng/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double dist = (earthRadius * c);

        return dist;
    }

}
