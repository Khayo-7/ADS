/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.astu.k7m;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ever
 */
@Local
public interface ShoppingCartSFSBLocal {
    public void addItem(String item);
    public void removeItem(String item);
    public List display();
    public int numberOfItems();  
    public boolean check(String item);
}
