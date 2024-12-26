/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package APPS;

import java.util.ArrayList;

/**
 *
 * @author josel
 */
public class Calculadora {

    public Calculadora() {

    }
    
    private String[] cities;

    public String GUI_Response(String cidade_partida, String cidade_destino, AVLTree avlTree) {


        int source = avlTree.findCityIndex(cidade_partida);
        int destination = avlTree.findCityIndex(cidade_destino);
        
        ArrayList<City> city = avlTree.getCities();

        DijkstraAlgorithm dj = new DijkstraAlgorithm(city.size());
        String result = dj.dijkstra(source, destination);
        
        this.cities = dj.getCities();
        return result;
    }

    public String[] getCities() {
        return this.cities;
    }

}
