package com.kinsa.rest.service;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.kinsa.rest.*;
import com.kinsa.rest.data.CoffeeShopList;
import com.kinsa.rest.data.ProjectProperty;
import com.kinsa.rest.model.CoffeeShop;
import com.kinsa.rest.util.Helper;

import java.util.concurrent.CopyOnWriteArrayList;
import javax.ws.rs.*;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

@Path("/coffeeshops")
public class CoffeeShopService {

    private final CopyOnWriteArrayList<CoffeeShop> cList = CoffeeShopList.getInstance();

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public CoffeeShop[] getAllCoffeeShops() {
        CoffeeShop[] coffeeShopLocations = new CoffeeShop[cList.size()];
        return cList.toArray(coffeeShopLocations);
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public CoffeeShop getCoffeeShop(@PathParam("id") long id) {
        CoffeeShop coffeeShop = Helper.findById(cList, id);
        if (coffeeShop != null) {
            return coffeeShop;

        } else {
            throw new com.kinsa.rest.NotFoundException(new JsonError("Error", "CoffeeShop [" + id + "] not found"));
        }

    }

    @PUT
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCoffeeShop(CoffeeShop coffeeShop) {

        if (coffeeShop != null) {
            // Instead of updating inline.
            // Taken the approach of deleting found coffee shop and adding the copy that came in request.
            CoffeeShop cs = Helper.findById(cList, coffeeShop.getId());
            if (cs != null) {
                cList.remove(cs);
                cList.add(coffeeShop);
                return Response.status(Response.Status.OK).build();
            }
        }

        return Response.status(Response.Status.NOT_FOUND).build();

    }

    @DELETE
    @Path("/delete/{id}")
    public Response deleteCoffeeShop(@PathParam("id") long id) {
        CoffeeShop coffeeShop = Helper.findById(cList, id);
        if (coffeeShop != null) {
            cList.remove(coffeeShop);
            return Response.status(Response.Status.OK).build();

        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("/add")
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.APPLICATION_JSON)
    public CoffeeShop newCoffeeShop(MultivaluedMap<String, String> formParams) {
        CoffeeShop coffeeShop = new CoffeeShop(formParams.getFirst("name"),
                formParams.getFirst("address"),
                Double.valueOf(formParams.getFirst("latitude")),
                Double.valueOf(formParams.getFirst("longitude")));
        if (coffeeShop != null && coffeeShop.getId() > 0) {
            cList.add(coffeeShop);
        } else {
            throw new com.kinsa.rest.NotFoundException(new JsonError("Error", " Invalid input address. "));
        }
        return coffeeShop;
    }

    @GET
    @Path(("/closest"))
    @QueryParam("address")
    @Produces(MediaType.APPLICATION_JSON)
    public CoffeeShop findClosestCoffeeShop(@QueryParam("address") String addr) { // CoffeeShop presentLocation) {
        CoffeeShop coffeeShop = new CoffeeShop();
        LatLng inputAddrLatLng = findGeoLatLng(addr);

        if (inputAddrLatLng != null) {
            coffeeShop = Helper.findNearestCoffeeShopbyLngLat(cList, inputAddrLatLng.lat, inputAddrLatLng.lng);
        }
        return coffeeShop;
    }

    protected LatLng findGeoLatLng(String inputAddress) {
        LatLng latLng = null;
        GeoApiContext context = new GeoApiContext().setApiKey(ProjectProperty.geocodingKey); // "AIzaSyBYen5eFqxsGAG274HtJzD0DgLwE0IFfFo");// Key
        GeocodingResult[] results = null;
        try {
            if (inputAddress != null && !inputAddress.isEmpty()) {
                results = GeocodingApi.geocode(context, inputAddress).await();
                if (results != null && (results.length > 0) && results[0] != null) {
                    latLng = results[0].geometry.location;
                }
            }

        } catch (Exception googleAPI_exception) {
            System.err.println(" Google GeoCoding API issue : " + googleAPI_exception.getMessage());
            googleAPI_exception.printStackTrace();
        }
        return latLng;
    }

}



//    @POST
//    @Path("/nearest")
//    @Produces(MediaType.APPLICATION_JSON)
//    public CoffeeShop findNearestCoffeeShop(MultivaluedMap<String, String> formParams )  {
//        CoffeeShop coffeeShop = new CoffeeShop();
//        LatLng inputAddrLatLng = findGeoLatLng(formParams.getFirst("address"));
//
//        if ( inputAddrLatLng != null ) {
//            coffeeShop = Helper.findNearestCoffeeShopbyLngLat(cList,inputAddrLatLng.lat,inputAddrLatLng.lng );
//        }
////        else {
////            throw new NotFoundException(new JsonError("Error", " Invalid input address. "));
////        }
//        return coffeeShop;
//    }

//
//    @POST
//    @Path("/add")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response addCoffeeShop(CoffeeShop coffeeShop){
//        // First find out if 'new' coffee shop already exists.  If so then simply update otherwise add a new coffeeshop data.
//        if ( coffeeShop == null || coffeeShop.getId() == 0 ) {
//            return Response.status(Response.Status.NOT_FOUND).build();
//
//
//        }else {
//            CoffeeShop cs = Helper.findById(cList,coffeeShop.getId());
//            if ( cs != null ) {
//                // It's an update instead of a simple add.
//                cList.remove(cs);
//            }
//            cList.add(coffeeShop);
//            return Response.status(201).build();
//        }
//    }

