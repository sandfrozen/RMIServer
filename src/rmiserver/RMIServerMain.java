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
import java.net.MalformedURLException;

import java.rmi.Naming;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class RMIServerMain {

    public static void main(String[] args) {

        try {
            System.setProperty("java.security.policy", "security.policy");
            System.setSecurityManager(new SecurityManager());
            
            System.setProperty("java.rmi.server.hostname", "192.168.1.103");
            System.out.println("Hostname: " + System.getProperty("java.rmi.server.hostname"));

            LocateRegistry.createRegistry(1099);
            
            MyServerImpl object = new MyServerImpl();
            Naming.rebind("//localhost/ABC", object);
            System.out.println("Serwer oczekuje ...");

        } catch (RemoteException | MalformedURLException e) { 
            e.printStackTrace();
        }
    }
}
