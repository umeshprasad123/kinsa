package com.kinsa.rest.data;


import com.kinsa.rest.model.CoffeeShop;

import java.util.concurrent.CopyOnWriteArrayList;

public class MockCoffeeShopList {

        private static final CopyOnWriteArrayList<CoffeeShop> cList = new CopyOnWriteArrayList<>();

        static {
            // Create list of coffee shops
            cList.add(
                    new CoffeeShop( "Jane",
                                    "2123 Fillmore St",
                                    37.78930232286027,
                                    -122.43397951126099)

            );

            cList.add(
                    new CoffeeShop( "Stable Cafe",
                                    "2128 Folsom St",
                                    37.776317491980427,
                                    -122.41525769233704)
            );

            cList.add(
                    new CoffeeShop( "Ritual Coffee Roasters",
                                    "432B Octavia St",
                            37.77649268335422,
                            -122.4242315985391)
            );

        }
        private MockCoffeeShopList(){}

        public static CopyOnWriteArrayList<CoffeeShop> getInstance(){
            return cList;
        }
}
