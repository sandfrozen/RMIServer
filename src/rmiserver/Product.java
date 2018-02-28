/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmiserver;

import java.io.Serializable;

/**
 *
 * @author tomek.buslowski
 */
public class Product implements Serializable {
   int id;
   String name;
   Double price;
   int c_id;
   
   @Override
   public String toString() {
       return id + ".   " + name + ",   $" + price;
   }
}
