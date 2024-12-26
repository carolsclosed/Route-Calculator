/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package APPS;

/**
 *
 * @author josel
 */
public class City implements Comparable<City> {

    private String name;
    private String latitude;
    private String longitude;
    private String country;

    // Constructor
    public City(String name, String latitude, String longitude, String country) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getCountry() {
        return country;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public int compareTo(City other) {
        return this.name.compareTo(other.name); 
    }

    @Override
    public String toString() {
        return "City{"
                + "name='" + name + '\''
                + ", latitude='" + latitude + '\''
                + ", longitude='" + longitude + '\''
                + ", country='" + country + '\''
                + '}';
    }
}
