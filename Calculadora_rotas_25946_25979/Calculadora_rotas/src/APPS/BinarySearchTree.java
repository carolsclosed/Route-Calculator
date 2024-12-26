/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package APPS;

/**
 *
 * @author IPT
 */
public class BinarySearchTree implements Tree{
    
    private Nodo root;
    
    public BinarySearchTree() {
        root = null;
    }

    @Override
    public boolean isEmpty() {
        return root==null;
    }

    @Override
    public void add(Comparable o) {
        Nodo novo = new Nodo();
        novo.data = o;
        novo.left = null;
        novo.rigth = null;
        if (root == null)
            root = novo;
        else
            add(root, novo);
    }
    
    private void add(Nodo actual, Nodo novo) {
        if (novo.data.compareTo(actual.data)<0)
            if (actual.left == null)
                actual.left = novo;
            else
                add(actual.left, novo);
        else 
            if (actual.rigth == null)
                actual.rigth = novo;
            else
                add(actual.rigth, novo);
    }

    @Override
    public boolean contains(Comparable o) {
        return contains(root, o);
    }
    
    private boolean contains(Nodo actual, Comparable o) {
        if (actual == null)
            return false;
        else if (actual.data.equals(o))
            return true;
        else if (o.compareTo(actual.data)<0)
            return contains(actual.left, o);
        else
            return contains(actual.rigth, o);
    }
    
    private Nodo find(Comparable o) {
        return find(root, o);
    }
    
    private Nodo find(Nodo actual, Comparable o) {
        if (actual == null)
            return null;
        else if (actual.data.equals(o))
            return actual;
        else if (o.compareTo(actual.data)<0)
            return find(actual.left, o);
        else
            return find(actual.rigth, o);
    }
    
    private Nodo findParent(Comparable o) {
        return findParent(root, o);
    }

    private Nodo findParent(Nodo actual, Comparable o) {
        if (o.equals(actual.data))
            return null;
        else
            if (o.compareTo(actual.data)<0)
                if (actual.left == null)
                    return null;
                else if (actual.left.data.equals(o))
                    return actual;
                else
                    return findParent(actual.left, o);
            else 
                if (actual.rigth == null)
                    return null;
                else if (actual.rigth.data.equals(o))
                    return actual;
                else
                    return findParent(actual.rigth, o);
    }


    @Override
    public boolean remove(Comparable o) {
        Nodo nodoARemover = find(o);
        if (nodoARemover == null)
            return false;
        else {
            Nodo parent = findParent(o);
            if (root.left == null && root.rigth == null)
                root = null;
            else if (root.left == null && root.rigth != null && root.data.equals(o))
                root = root.rigth;
            else if (root.left != null && root.rigth == null && root.data.equals(o))
                root = root.left;
            else if (nodoARemover.left == null && nodoARemover.rigth == null) // caso 1
                if (nodoARemover.data.compareTo(parent.data)<0)
                    parent.left = null;
                else
                    parent.rigth = null;
            else if (nodoARemover.left == null && nodoARemover.rigth != null) // caso 2.1
                if (nodoARemover.data.compareTo(parent.data)<0)
                    parent.left = nodoARemover.rigth;
                else
                    parent.rigth = nodoARemover.rigth;
            else if (nodoARemover.left != null && nodoARemover.rigth == null) // caso 2.2
                if (nodoARemover.data.compareTo(parent.data)<0)
                    parent.left = nodoARemover.left;
                else
                    parent.rigth = nodoARemover.left;
            else { // caso 3
                Nodo nodoMaior = nodoARemover.left;
                while (nodoMaior.rigth != null)
                    nodoMaior = nodoMaior.rigth;
                Nodo parentNodoMaior = findParent(nodoMaior.data);
                if (parentNodoMaior == nodoARemover)
                    parentNodoMaior.left = nodoMaior.left;
                else
                    parentNodoMaior.rigth = nodoMaior.left;
                nodoARemover.data = nodoMaior.data;
            }
            return true;
        }
    }

    @Override
    public Object findMin() {
        if (root == null)
            return null;
        else
            return findMin(root);
    }
    
    private Object findMin(Nodo actual) {
        if (actual.left == null)
            return actual.data;
        else
            return findMin(actual.left);
    }

    @Override
    public Object findMax() {
        if (root == null)
            return null;
        else
            return findMax(root);
    }
    
    private Object findMax(Nodo actual) {
        if (actual.left == null)
            return actual.data;
        else
            return findMax(actual.rigth);
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        toString(root, sb);
        return sb.toString().trim();
    }
    
    private void toString(Nodo actual, StringBuffer sb) {
        if (actual != null) {
            toString(actual.left, sb);
            sb.append(actual.data);
            sb.append(" ");
            toString(actual.rigth, sb);
        }
    }
    
    public void mostrar() {
        mostrar(root, 0, 0);
    }
    
    private void mostrar(Nodo actual, int nivel, int aux) {
        if (actual!=null) {
           mostrar(actual.rigth, nivel+1, 1);
           for(int i=0; i < nivel; i++)
                System.out.print(" ");
           if (aux > 0) System.out.print("/");
           if (aux < 0) System.out.print("\\");
           System.out.println(actual.data);
           mostrar(actual.left, nivel+1, -1);
        }
    }
   
    private class Nodo {
        Comparable data;
        Nodo left;
        Nodo rigth;

        @Override
        public String toString() {
            return data.toString();
        }
        
        
    }
    
}
