/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package APPS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ReadCSV {

    private AVLTree avlTree = new AVLTree();
    private static ArrayList<String> cities = new ArrayList<>();
    private static ArrayList<String> latitudes = new ArrayList<>();
    private static ArrayList<String> longitudes = new ArrayList<>();
    private static ArrayList<String> pais = new ArrayList<>();

    public ReadCSV() {
        if (cities.isEmpty()) {  // Only read the file once
            String filePath = "worldcities.csv";
            String line;

            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(",");
                    if (values.length >= 4) {
                        cities.add(values[1].replace("\"", "").trim());
                        latitudes.add(values[2].replace("\"", "").trim());
                        longitudes.add(values[3].replace("\"", "").trim());
                        pais.add(values[4].replace("\"", "").trim());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //Cidades de portugal espanha e frança num só ficheiro
    public void writeCitiesByCountry() {

        ArrayList<Integer> index_paises = new ArrayList<Integer>();

        int Austria = findIndexCountry("Austria");
        int Belgium = findIndexCountry("Belgium");
        int France = findIndexCountry("France");
        int Germany = findIndexCountry("Germany");
        int Liechtenstein = findIndexCountry("Liechtenstein");
        int Luxembourg = findIndexCountry("Luxembourg");
        int Monaco = findIndexCountry("Monaco");
        int Netherlands = findIndexCountry("Netherlands");
        int Switzerland = findIndexCountry("Switzerland");

        int Denmark = findIndexCountry("Denmark");
        int Estonia = findIndexCountry("Estonia");
        int Finland = findIndexCountry("Finland");
        int Latvia = findIndexCountry("Latvia");
        int Lithuania = findIndexCountry("Lithuania");
        int Norway = findIndexCountry("Norway");
        int Sweden = findIndexCountry("Sweden");

        int Albania = findIndexCountry("Albania");
        int Andorra = findIndexCountry("Andorra");
        int BosniaAndHerzegovina = findIndexCountry("Bosnia and Herzegovina");
        int Croatia = findIndexCountry("Croatia");
        int Greece = findIndexCountry("Greece");
        int Italy = findIndexCountry("Italy");
        int Kosovo = findIndexCountry("Kosovo");
        int Montenegro = findIndexCountry("Montenegro");
        int NorthMacedonia = findIndexCountry("North Macedonia");
        int Portugal = findIndexCountry("Portugal");
        int SanMarino = findIndexCountry("San Marino");
        int Serbia = findIndexCountry("Serbia");
        int Slovenia = findIndexCountry("Slovenia");
        int Spain = findIndexCountry("Spain");
        int VaticanCity = findIndexCountry("Vatican City");

        int Belarus = findIndexCountry("Belarus");
        int Bulgaria = findIndexCountry("Bulgaria");
        int Czechia = findIndexCountry("Czechia");
        int Hungary = findIndexCountry("Hungary");
        int Moldova = findIndexCountry("Moldova");
        int Poland = findIndexCountry("Poland");
        int Romania = findIndexCountry("Romania");
        int Slovakia = findIndexCountry("Slovakia");
        int Ukraine = findIndexCountry("Ukraine");

        int Armenia = findIndexCountry("Armenia");
        int Azerbaijan = findIndexCountry("Azerbaijan");
        int Georgia = findIndexCountry("Georgia");

        int Turkey = findIndexCountry("Turkey");

        int[] countriesArray = {
            Austria, Belgium, France, Germany, Liechtenstein, Luxembourg, Monaco, Netherlands, Switzerland,
            Denmark, Estonia, Finland, Latvia, Lithuania, Norway, Sweden,
            Albania, Andorra, BosniaAndHerzegovina, Croatia, Greece, Italy, Kosovo, Montenegro, NorthMacedonia,
            Portugal, SanMarino, Serbia, Slovenia, Spain, VaticanCity,
            Belarus, Bulgaria, Czechia, Hungary, Moldova, Poland, Romania, Slovakia, Ukraine,
            Armenia, Azerbaijan, Georgia, Turkey
        };

        for (int country : countriesArray) {
            index_paises.add(country);
        }

        String fileName = "EUROPE.txt";
        File file = new File(fileName);

        try {
            // Check if the file exists and skip writing if it does
            if (file.exists()) {
                System.out.println("File already exists. Skipping writing.");
                return; // Exit the method if the file exists
            } else {
                file.createNewFile(); // Create the file if it doesn't exist
            }

            // Write data to the file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) { // Without 'true' to overwrite
                for (int i = 0; i < index_paises.size(); i++) {
                    for (int j = 1; j < cities.size(); j++) {
                        if (pais.get(index_paises.get(i)).equals(pais.get(j))) {
                            writer.write("\"" + cities.get(j) + "\",");
                            writer.write("\"" + latitudes.get(j) + "\",");
                            writer.write("\"" + longitudes.get(j) + "\",");
                            writer.write("\"" + pais.get(j) + "\"");
                            writer.newLine();
                        }
                    }
                }

            } catch (IOException e) {
                System.err.println("Error writing file for countries");
                e.printStackTrace();
            }

        } catch (IOException e) {
            System.err.println("Error checking/creating file");
            e.printStackTrace();
        }
    }

    public void ReadCountryFile(String Country) {

        String fileName = Country + ".txt";
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            while ((line = br.readLine()) != null) {

                String[] values = line.split(",");

                if (values.length == 4) {
                    String name = values[0].replace("\"", "").trim();
                    String lat = values[1].replace("\"", "").trim();
                    String log = values[2].replace("\"", "").trim();
                    String country = values[3].replace("\"", "").trim();

                    City city = new City(name, lat, log, country);
                    avlTree.add(city);  // Insert city into the AVL Tree

                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    //WORKING
    public int findIndexCountry(String country) {
        int index = -1;
        for (int i = 0; i < pais.size(); i++) {
            if (pais.get(i).equals(country)) {
                index = i;
                break; // Once found, exit the loop
            }
        }
        return index; // Returns -1 if the country is not found
    }

    

    public AVLTree getAvlTree() {
        return avlTree;
    }

    public ArrayList<String> getPais() {
        return pais;
    }

    public void setPais(ArrayList<String> pais) {
        this.pais = pais;
    }

    public ArrayList<String> getCities() {
        return cities;
    }

    public void setCities(ArrayList<String> cities) {
        this.cities = cities;
    }

    public ArrayList<String> getLatitudes() {
        return latitudes;
    }

    public void setLatitudes(ArrayList<String> latitudes) {
        this.latitudes = latitudes;
    }

    public ArrayList<String> getLongitudes() {
        return longitudes;
    }

    public void setLongitudes(ArrayList<String> longitudes) {
        this.longitudes = longitudes;
    }

}
