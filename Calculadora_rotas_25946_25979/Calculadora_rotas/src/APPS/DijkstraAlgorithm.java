/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package APPS;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class DijkstraAlgorithm {

    private int V; 
    private ArrayList<String> cities;
    private double[][] graph; 
    ReadCSV csv = new ReadCSV();

    private AVLTree avlTree;  
    HaversineFormula haversine = new HaversineFormula();

    public DijkstraAlgorithm(int vertices) {
        csv.ReadCountryFile("EUROPE");
        this.avlTree = csv.getAvlTree();
        V = vertices;
        graph = new double[V][V]; 

        
//          debug error of index out of bounds
//        for (int i = 0; i < V; i++) {
//            System.out.println("Checking index: " + i);  // Debug print
//            City city = avlTree.getCities().get(i);  // Check for invalid access
//        }
    }

    private int minimumDistance(double[] distances, boolean[] visited) {
        double minDistance = Integer.MAX_VALUE;
        int minIndex = -1;
        for (int v = 0; v < V; v++) {
            if (!visited[v] && distances[v] <= minDistance) {
                minDistance = distances[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    private String printPath(double[] distances, int[] parent,
                             int source, int destination) {
        int crawl = destination;
        double totalDistance = distances[destination];

        ArrayList<String> cidades_de_passagem = new ArrayList<>();
        ArrayList<Integer> distancias = new ArrayList<>();

        while (parent[crawl] != -1) {
            int parentCity = parent[crawl];
            City cidadePartida = avlTree.getCities().get(parentCity);  // Get the City object

            // Get the latitude and longitude for both cities from the AVL tree
            double latFrom = Double.parseDouble(cidadePartida.getLatitude());
            double lonFrom = Double.parseDouble(cidadePartida.getLongitude());
            City cidadeChegada = avlTree.getCities().get(crawl);  // Get the destination City
            double latTo = Double.parseDouble(cidadeChegada.getLatitude());
            double lonTo = Double.parseDouble(cidadeChegada.getLongitude());

            // Calculate the distance between the cities using the Haversine formula
            double distanceBetweenCities = haversine.Distance(lonFrom, latFrom, lonTo, latTo);

            cidades_de_passagem.add(cidadePartida.getName());  
            distancias.add((int) distanceBetweenCities);
            crawl = parent[crawl];
        }

        StringBuilder result = new StringBuilder();
        result.append("Caminho minimo entre ").append(avlTree.getCities().get(source).getName())
                .append(" e ").append(avlTree.getCities().get(destination).getName()).append(": \n");

        for (int i = distancias.size() - 1; i >= 0; i--) {
            result.append("➡").append(cidades_de_passagem.get(i)).append("(").append(distancias.get(i))
                    .append("km)").append(" \n");
        }

        result.append("➡").append(avlTree.getCities().get(destination).getName());
        result.append("\nCusto total: ").append((int) totalDistance).append(" km");

        String resultString = result.toString();
        System.out.println(resultString);
        this.cities = cidades_de_passagem;
        return resultString;
    }

    public String[] getCities(){
        return this.cities.toArray(new String[0]);
    }
    
    
    // Save the graph to a file
    public void saveGraph() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("graph.ser"))) {
            out.writeObject(graph);
            System.out.println("Graph saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load the graph from a file
    public void loadGraph() {
        File graphFile = new File("graph.ser");
        if (graphFile.exists()) {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(graphFile))) {
                graph = (double[][]) in.readObject();
                System.out.println("Graph loaded successfully.");
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Graph file not found. Calculating the graph.");
            buildGraph();
            saveGraph();  // Save the graph for future use
        }
    }

    // Build the graph (calculate distances between cities)
    public void buildGraph() {
        // Assuming the AVL tree has been populated with cities
        ArrayList<City> cities = avlTree.getCities(); // Get all cities from AVL tree
        if (cities.isEmpty()) {
            csv.ReadCountryFile("EUROPE");
            avlTree = csv.getAvlTree();
            cities = avlTree.getCities();
           
        }
       

        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (i != j) {
                    City city1 = cities.get(i);
                    City city2 = cities.get(j);

                    double lat1 = Double.parseDouble(city1.getLatitude());
                    double lon1 = Double.parseDouble(city1.getLongitude());
                    double lat2 = Double.parseDouble(city2.getLatitude());
                    double lon2 = Double.parseDouble(city2.getLongitude());

                    // Set the distance between two cities using Haversine
                    double distance = haversine.Distance(lon1, lat1, lon2, lat2);
                    if (distance <= 200 && distance != 0) {
                        graph[i][j] = distance;
                        graph[j][i] = distance;
                    }
                }
            }
        }
    }

    // Dijkstra's Algorithm implementation
    public String dijkstra(int source, int destination) {

        loadGraph();  // Load the precomputed graph

        double[] distances = new double[V];
        boolean[] visited = new boolean[V];
        int[] parent = new int[V];

        for (int i = 0; i < V; i++) {
            distances[i] = Integer.MAX_VALUE;
            visited[i] = false;
            parent[i] = -1;
        }
        distances[source] = 0;

        for (int count = 0; count < V - 1; count++) {
            int u = minimumDistance(distances, visited);
            visited[u] = true;

            for (int v = 0; v < V; v++) {
                if (!visited[v] && graph[u][v] != 0 && distances[u] != Integer.MAX_VALUE
                        && distances[u] + graph[u][v] < distances[v]) {
                    distances[v] = distances[u] + graph[u][v];
                    parent[v] = u;
                }
            }
        }
        return printPath(distances, parent, source, destination);
    }
}
