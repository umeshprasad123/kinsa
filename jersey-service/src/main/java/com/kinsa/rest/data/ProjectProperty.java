package com.kinsa.rest.data;

// TODO : Read project properties from a file.
public class ProjectProperty {
    public static String geocodingKey   = "AIzaSyBYen5eFqxsGAG274HtJzD0DgLwE0IFfFo";
    public static String dataFile       = "locations.csv" ;

    // Fall back if dataFile is not found in the classpath
    public static String absPathDtaFile = "C:/ucp/misc/examples/jsontest/jersey-service/src/main/resources/locations.csv" ;

}
