/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package APPS;

import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class AVLTree extends BinarySearchTree {

    private Nodo source;

    @Override
    public boolean isEmpty() {
        return source == null;
    }

    public void add(City city) {
        Nodo novo = new Nodo(city);
        novo.data = city;
        novo.left = null;
        novo.right = null;
        novo.height = Integer.MAX_VALUE;
        if (source == null) {
            source = novo;
        } else {
            source = add(source, novo);
        }
    }

    private Nodo add(Nodo actual, Nodo novo) {
        if (novo.data.compareTo(actual.data) < 0) {
            if (actual.left == null) {
                actual.left = novo;
            } else {
                actual.left = add(actual.left, novo);
            }
        } else if (novo.data.compareTo(actual.data) > 0) {
            if (actual.right == null) {
                actual.right = novo;
            } else {
                actual.right = add(actual.right, novo);
            }
        }
        return balance(actual);
    }

    private int fator(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }
        int leftHeight = (nodo.left != null) ? nodo.left.height : 0;
        int rightHeight = (nodo.right != null) ? nodo.right.height : 0;
        return leftHeight - rightHeight;
    }

    private Nodo rightRotation(Nodo k2) {
        Nodo k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = Math.max(heigth(k2.left), heigth(k2.right)) + 1;
        k1.height = Math.max(heigth(k1.left), k2.height) + 1;
        return k1;
    }

    private Nodo leftRotation(Nodo k1) {
        Nodo k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        k1.height = Math.max(heigth(k1.left), heigth(k1.right)) + 1;
        k2.height = Math.max(heigth(k2.right), k1.height) + 1;
        return k2;
    }

    private Nodo doubleRigthRotation(Nodo k3) {
        k3.left = leftRotation(k3.left);
        return rightRotation(k3);
    }

    private Nodo doubleLeftRotation(Nodo k3) {
        k3.right = rightRotation(k3.right);
        return leftRotation(k3);
    }

    private Nodo balance(Nodo nodo) {
        nodo.height = Math.max(heigth(nodo.left), heigth(nodo.right)) + 1;
        int balanceFactor = fator(nodo);

        if (balanceFactor > 1) {
            if (fator(nodo.left) >= 0) {
                nodo = rightRotation(nodo);
            } else {
                nodo = doubleRigthRotation(nodo);
            }
        } else if (balanceFactor < -1) {
            if (fator(nodo.right) <= 0) {
                nodo = leftRotation(nodo);
            } else {
                nodo = doubleLeftRotation(nodo);
            }
        }

        return nodo;
    }

    private int heigth(Nodo nodo) {
        return (nodo == null) ? 0 : nodo.height;
    }

    @Override
    public boolean contains(Comparable o) {
        return contains(source, o);
    }

    private boolean contains(Nodo actual, Comparable o) {
        if (actual == null) {
            return false;
        } else if (actual.data.equals(o)) {
            return true;
        } else if (o.compareTo(actual.data) < 0) {
            return contains(actual.left, o);
        } else {
            return contains(actual.right, o);
        }
    }

    @Override
    public boolean remove(Comparable o) {
        if (!contains(o)) {
            return false;
        }
        source = remove(source, o);
        return true;
    }

    private Nodo remove(Nodo actual, Comparable o) {
        if (actual == null) {
            return null;
        }
        if (o.compareTo(actual.data) < 0) {
            actual.left = remove(actual.left, o);
        } else if (o.compareTo(actual.data) > 0) {
            actual.right = remove(actual.right, o);
        } else {
            if (actual.left == null || actual.right == null) {
                Nodo temp = (actual.left != null) ? actual.left : actual.right;
                actual = temp;
            } else {
                Nodo temp = findMinNode(actual.right);
                actual.data = temp.data;
                actual.right = remove(actual.right, temp.data);
            }
        }
        return (actual != null) ? balance(actual) : null;
    }

    private Nodo findMinNode(Nodo nodo) {
        while (nodo.left != null) {
            nodo = nodo.left;
        }
        return nodo;
    }

    //Goes through all AVLTree vertices 
    public int countVertices() {
        return countVertices(source);
    }

    private int countVertices(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }
        return 1 + countVertices(nodo.left) + countVertices(nodo.right);
    }

    public ArrayList<City> getCities() {
        ArrayList<City> cities = new ArrayList<>();
        AlphabeticOrder(source, cities);
        return cities;
    }

    public int findCityIndex(String City) {
        ArrayList<City> cities = getCities();
        for (int i = 0; i < cities.size(); i++) {
            if (cities.get(i).getName().equals(City)) {
                return i;
            }
        }
        return -1;
    }

    public ArrayList<City> getCitiesByCountry(String Country) {
        ArrayList<City> citiesByCountry = new ArrayList<>();

        ArrayList<City> cities = getCities();
        for (City city : cities) {
            if (city.getCountry().equalsIgnoreCase(Country)) {
                citiesByCountry.add(city);
            }
        }

        return citiesByCountry;
    }
    
    private void AlphabeticOrder(Nodo node, ArrayList<City> cities) {
        if (node != null) {
            AlphabeticOrder(node.left, cities);
            cities.add(node.data);
            AlphabeticOrder(node.right, cities);
        }
    }

    @Override
    public Object findMin() {
        if (source == null) {
            return null;
        }
        return findMinNode(source).data;
    }

    @Override
    public Object findMax() {
        if (source == null) {
            return null;
        }
        return findMaxNode(source).data;
    }

    private Nodo findMaxNode(Nodo nodo) {
        while (nodo.right != null) {
            nodo = nodo.right;
        }
        return nodo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toString(source, sb);
        return sb.toString().trim();
    }

    private void toString(Nodo actual, StringBuilder sb) {
        if (actual != null) {
            toString(actual.left, sb);
            sb.append(actual.data).append(" ");
            toString(actual.right, sb);
        }
    }

    @Override
    public void mostrar() {
        mostrar(source, 0, 0);
    }

    private void mostrar(Nodo actual, int nivel, int aux) {
        if (actual != null) {
            mostrar(actual.right, nivel + 1, 1);
            for (int i = 0; i < nivel; i++) {
                System.out.print("    ");
            }
            if (aux > 0) {
                System.out.print("/");
            }
            if (aux < 0) {
                System.out.print("\\");
            }
            System.out.println(actual.data);
            mostrar(actual.left, nivel + 1, -1);
        }
    }

    public class Nodo {

        City data;
        Nodo left;
        Nodo right;
        int height;

        public Nodo(City data) {
            this.data = data;
            this.height = 1;
        }
    }

    public Nodo getSource() {
        return source;
    }

    public void setSource(Nodo source) {
        this.source = source;
    }
}
