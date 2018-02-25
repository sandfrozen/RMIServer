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
            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new SecurityManager());
            }

            //System.setProperty("java.rmi.server.codebase","file:/C:/Users/Jacek/workspace/RMIServer/bin/");
            //System.setProperty("java.rmi.server.codebase","file:/C:/Users/Jacek/NetBeansProjects/RMIServer/build/classes/");
            System.setProperty("java.rmi.server.codebase","file:/C:/Users/tomek.buslowski/NetBeansProjects/RMIServer/build/classes/");
            //System.setProperty("java.rmi.server.codebase", "http://192.168.1.102/jaco/");
            System.out.println("Codebase: " + System.getProperty("java.rmi.server.codebase"));
            //LocateRegistry.createRegistry(1099);
            // MAC in terminal: rmiregistry &
            // % fg - checkout is running rmiregistry, ctrl + c for stop
            MyServerImpl obj1 = new MyServerImpl();
            Naming.rebind("//localhost/ABC", obj1);
            System.out.println("Serwer oczekuje ...");

        } catch (RemoteException | MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
