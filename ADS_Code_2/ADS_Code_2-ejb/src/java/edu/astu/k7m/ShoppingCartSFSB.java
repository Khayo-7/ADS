/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.astu.k7m;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Stateful;

/**
 *
 * @author Ever
 */
@Stateful
public class ShoppingCartSFSB implements ShoppingCartSFSBLocal {
    
    private List<String> itemStorage = new ArrayList<String>();
    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

      
    
    public void addItem(String item){
        if(!itemStorage.contains(item)){
            itemStorage.add(item);
        }
    }
    public void removeItem(String item){
        if(itemStorage.contains(item)){
            itemStorage.remove(item);
        }
        
    }
    public List display(){
        return itemStorage;
    }
    public int numberOfItems(){
        return itemStorage.size();
    } 
    
    public boolean check(String item) {        
        if(itemStorage.contains(item))
            return true;
        else 
            return false;
    }
}
