package com.kinsa.rest.data;

import com.kinsa.rest.model.CoffeeShop;
import com.kinsa.rest.util.Helper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *  CoffeeShop dataset is stored as singleton list.
 */
public class CoffeeShopList {

    private static CopyOnWriteArrayList<CoffeeShop> csList = new CopyOnWriteArrayList<>();

    private static Properties prop          = new Properties();
    private static String dataFilePath      =  ProjectProperty.dataFile ; // "locations.csv" ; // Default data file in JSON format.
    private static String dataFileAbsPath   = ProjectProperty.absPathDtaFile;

    private CoffeeShopList() {}

    static {
        List<CoffeeShop> csList = CoffeeShopList.getInstance();
        // System.out.println( dataFilePath);

        File dataFile ;
        URL path = ClassLoader.getSystemResource(dataFilePath);
        if ( path == null ) {
            dataFile = new File(dataFileAbsPath);
            System.out.println(" URI Path is null ... ");
        }else {
            try {
                dataFile = new File(path.toURI());
            }catch(URISyntaxException uris) {
                // Fall back to absolute file.
                dataFile = new File(dataFileAbsPath);
            }
        }

        Helper.readCoffeeShopLocation(csList, dataFile);
    }

    public static CopyOnWriteArrayList<CoffeeShop> getInstance(){
        return csList;
    }


}
