package com.kinsa.rest.data;

import com.kinsa.rest.model.CoffeeShop;
import com.kinsa.rest.util.Helper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *  CoffeeShop dataset is stored as singleton list.
 */
public class CoffeeShopList {

    private static CopyOnWriteArrayList<CoffeeShop> csList = new CopyOnWriteArrayList<>();

    private static Properties prop = new Properties();
    private static String dataFile =  ProjectProperty.dataFile ; // "locations.csv" ; // Default data file in JSON format.

    private CoffeeShopList() {}

    static {
        List<CoffeeShop> csList = CoffeeShopList.getInstance();
        Helper.readCoffeeShopLocation(csList, dataFile);
    }

    public static CopyOnWriteArrayList<CoffeeShop> getInstance(){
        return csList;
    }


}
