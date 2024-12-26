/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package APPS;

/**
 *
 * @author josel
 */
public class HaversineFormula {
    
    

    double Distance(double long1, double lat1, double long2, double lat2) {
    // Convert to radians
    long1 = Math.toRadians(long1);
    lat1 = Math.toRadians(lat1);
    long2 = Math.toRadians(long2);
    lat2 = Math.toRadians(lat2);

    // Haversine formula
    double dLat = lat2 - lat1;
    double dLon = long2 - long1;

    double sinDLat = Math.sin(dLat / 2);
    double sinDLon = Math.sin(dLon / 2);
    double Form1 = sinDLat * sinDLat + Math.cos(lat1) * Math.cos(lat2) * sinDLon * sinDLon;

    double radius_of_earth = 6378.1; // Earth's radius in kilometers
    double distance = 2 * radius_of_earth * Math.asin(Math.sqrt(Form1));
    return distance;
    }
    

}
