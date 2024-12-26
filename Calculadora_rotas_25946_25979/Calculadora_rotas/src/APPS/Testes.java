/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package APPS;

import java.util.ArrayList;

/**
 *
 * @author josel
 */
public class Testes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        ReadCSV csv = new ReadCSV();
//        HaversineFormula haversin = new HaversineFormula();
//        csv.writeCitiesByCountry();
//        CountryData file = csv.ReadCountryFile("EUROPE");
//        AVLTree tree = new AVLTree();
//
//        String cidade_partida = "Tomar";
//        String cidade_destino = "Porto";
//        int source = file.findIndexCity(cidade_partida);
//        int destination = file.findIndexCity(cidade_destino);
//
//        // Check if the cities exist in the list
//        if (source == -1 || destination == -1) {
//            System.out.println("One or both of the cities do not exist.");
//            return;
//        }
//
//        for (int i = 0; i < file.getCities().size(); i++) {
//
//            tree.add(file.findIndexCity(file.getCities().get(i)));
//        }
//
//        System.out.println(file.findCountries());
//
//        // Run Dijkstra algorithm
//        DijkstraAlgorithm dj = new DijkstraAlgorithm(tree.countVertices());
//        dj.dijkstra(source, destination);
        ReadCSV csv = new ReadCSV();
        String cidade_partida = "Porto";
        String cidade_destino = "Tomar";
        csv.writeCitiesByCountry();
        csv.ReadCountryFile("EUROPE");
        AVLTree avlTree = csv.getAvlTree();
        ArrayList<City> cities =  avlTree.getCities();
        int source = avlTree.findCityIndex(cidade_partida);
        int destination = avlTree.findCityIndex(cidade_destino);


        

        // Step 2: Initialize the DijkstraAlgorithm
        // Assuming the number of cities is 4 (adjust this to match the actual number of cities in your AVL tree)
        DijkstraAlgorithm dijkstraAlgorithm = new DijkstraAlgorithm(avlTree.countVertices());
        dijkstraAlgorithm.dijkstra(source, destination);
    }
}
