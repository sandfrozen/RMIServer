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

public interface MyServerInt extends Remote{
    String getDescription(String text) throws RemoteException;
    String calculator(String text) throws RemoteException;
    
    double sum(double a, double b) throws RemoteException;
    double subtract(double a, double b) throws RemoteException;
    double multiply(double a, double b) throws RemoteException;
    double divide(double a, double b) throws RemoteException;
}