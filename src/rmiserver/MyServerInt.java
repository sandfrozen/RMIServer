/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmiserver;

/**
 *
 * @author tomek.buslowski
 */
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

public interface MyServerInt extends Remote{
    String getDescription(String text) throws RemoteException;
    String calculator(String text) throws RemoteException;
    
    List<Product> productsList() throws RemoteException, SQLException;
    Product productByName(String name) throws RemoteException, SQLException;
}