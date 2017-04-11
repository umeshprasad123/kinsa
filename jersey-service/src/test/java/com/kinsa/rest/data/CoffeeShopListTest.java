package com.kinsa.rest.data;


import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.kinsa.rest.model.CoffeeShop;
import com.kinsa.rest.util.Helper;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.List;

public class CoffeeShopListTest {

    private List<CoffeeShop> csList ;

    /**
     * Test to see all Coffee shops is in memory.
     */
    @Test
    public void testGetAll() {

        csList = MockCoffeeShopList.getInstance();
        CoffeeShop cs = Helper.findById(csList,102);
        assertEquals(3, csList.size());
    }


    /**
     * Test to see a Coffee shop is in memory.
     */
    @Test
    public void testGetACoffeeShop() {

        csList = MockCoffeeShopList.getInstance();
        CoffeeShop cs = Helper.findById(csList,102);
        assertEquals("Ritual Coffee Roasters", cs.getName());
    }

    /**
     * Test to find nearest Coffee shop.
     */
    @Test
    public void testFindNearestCoffeeShop() {
        csList = MockCoffeeShopList.getInstance();
        CoffeeShop cs = Helper.findNearestCoffeeShopbyLngLat(csList, 37.76317491980500, -122.41525769233670);
        assertEquals("Stable Cafe", cs.getName());
    }

}
