package com.kinsa.rest.service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.xml.ws.Response;

import com.kinsa.rest.Main;
import com.kinsa.rest.model.CoffeeShop;
import org.glassfish.grizzly.http.server.HttpServer;

import org.glassfish.jersey.server.internal.inject.MultivaluedParameterExtractor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EndPointTest {

    private HttpServer server;
    private WebTarget target;

    @Before
    public void setUp() throws Exception {
        // start the server
        server = Main.startServer();
        // create the client
        Client c = ClientBuilder.newClient();

        // uncomment the following line if you want to enable
        // support for JSON in the client (you also have to uncomment
        // dependency on jersey-media-json module in pom.xml and Main.startServer())
        // --
        // c.configuration().enable(new org.glassfish.jersey.media.json.JsonJaxbFeature());

        target = c.target(Main.BASE_URI);
    }

    @After
    public void tearDown() throws Exception {
        server.stop();
    }

    /**
     * Test to see that the message "Got it!" is sent in the response.
     */
    @Test
    public void testGetIt() {
        String responseMsg = target.path("endpoint").request().get(String.class);
        assertEquals("Got it!", responseMsg);
    }

    /**
     * Test to see all Coffee shops in the response.
     */
    @Test
    public void testGetAll() {

        String responseMsg = target.path("coffeeshops/all").request().get(String.class);
        assertTrue(responseMsg.contains("Equator Coffees & Teas"));
    }

    /**
     * Test to see Coffee shop # 2 in the response.
     */
    @Test
    public void testGetCoffeeShop() {

        String responseMsg = target.path("coffeeshops/2").request().get(String.class);
        assertEquals("{\"id\":2,\"name\":\" Snowbird Coffee\",\"address\":\" 1352A 9th Ave\",\"latitude\":37.76309350825185,\"longitude\":-122.46623159512482}", responseMsg);
    }


    /**
     * Test to update to CoffeeShop 56 .
     */
    @Test
    public void testUpdateCoffeeShop() {
        CoffeeShop cs = new CoffeeShop();
        cs.setId(56);
        cs.setName("Umesh Prasd's : Chapel Hill Coffee Co"); // Update just name.
        cs.setAddress("670 Commercial St");
        cs.setLatitude(37.794096040757196);
        cs.setLongitude(-122.40423200906335);

        CoffeeShop responseMsg = target.path("coffeeshops/update").request(MediaType.APPLICATION_JSON).put(Entity.entity(cs,MediaType.APPLICATION_JSON),CoffeeShop.class); // Entity.json(postBody)) ; // put(new CoffeeShop()); // String.class);


        // Do a query to see if the update has happened
        String responseAllMsg = target.path("coffeeshops/all").request().get(String.class);
        assertTrue(responseAllMsg.contains("Umesh Prasd's : Chapel Hill Coffee Co"));
    }

    /**
     * Test to delete coffee shop # 55
     */
    @Test
    public void testDeleteCoffeeShop() {
        String responseMsg = target.path("coffeeshops/delete/55").request().delete(String.class);
        assertEquals(0, responseMsg.length());
    }

    /**
     * Test to add a new CoffeeShop location .
     */
    @Test
    public void testAddCoffeeShop() {

        Form form = new Form();
        form.param("name", "Google Coffee Shop");
        form.param("address", "1600 Amphitheatre Parkway Mountain View, CA 94043, USA");
        form.param("latitude", "37.794096040757196");
        form.param("longitude", "-122.40423200906335");

        CoffeeShop responseMsg = target.path("coffeeshops/add").request(MediaType.APPLICATION_JSON).post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED), CoffeeShop.class);

        assertEquals("Google Coffee Shop",responseMsg.getName());

    }
}
