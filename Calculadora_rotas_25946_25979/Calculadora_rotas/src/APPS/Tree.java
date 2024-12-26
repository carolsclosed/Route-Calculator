/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package APPS;

/**
 *
 * @author IPT
 */
public interface Tree {
    public boolean isEmpty();
    public void add(Comparable o);
    public boolean contains(Comparable o);
    public boolean remove(Comparable o);
    public Object findMin();
    public Object findMax(); 
    public void mostrar();
}
