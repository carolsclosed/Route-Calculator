/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package APPS;
import APPS.City;
import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import java.util.Random;

public class MapaOverlay {

    private int mapWidth = 800;
    private int mapHeight = 748;
    private int xOffset = 647;
    private List<City> cities;

    // Constructor to initialize map dimensions and cities
       public MapaOverlay() {
        this.cities = cities;
    }

    public MapaOverlay(List<City> cities) {
        this.cities = cities;
    }

    public static int[] latLonToPixels(String latStr, String lonStr, int mapWidth, int mapHeight) {
        try {
            // Convert input strings to double
            double latitude = Double.parseDouble(latStr);
            double longitude = Double.parseDouble(lonStr);

            // Validate latitude and longitude
            if (latitude > 85.0511 || latitude < -85.0511) {
                throw new IllegalArgumentException("Latitude must be between -85.0511 and 85.0511 degrees.");
            }
            if (longitude > 180 || longitude < -180) {
                throw new IllegalArgumentException("Longitude must be between -180 and 180 degrees.");
            }

            // Longitude to X (linear mapping)
            int x = (int) (((longitude + 180) / 360) * mapWidth);

            // Latitude to Y (using Mercator formula)
            double latRad = Math.toRadians(latitude); // Convert latitude to radians
            double mercatorY = Math.log(Math.tan(Math.PI / 4 + latRad / 2));
            int y = (int) ((1 - mercatorY / Math.PI) * (mapHeight / 2)) + 17;

            return new int[]{x, y};
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid latitude or longitude format. Must be a valid number.", e);
        }
    }

    public void draw(Graphics g) {
        

        for (int i = 0; i < cities.size(); i++) {
            City city = cities.get(i);
            int[] cityPixels = latLonToPixels(city.getLatitude(), city.getLongitude(), mapWidth, mapHeight);

            // Draw city as a red circle
            g.setColor(Color.ORANGE);  
            g.fillOval(cityPixels[0] + xOffset , cityPixels[1],7, 7);

            // Draw connections between consecutive cities
            
        }
    }
}