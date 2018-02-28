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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class RMIServerMain {

    public static void main(String[] args) {
        
        try {
            String url = "jdbc:postgresql://localhost/iit_db_dev";
            Properties props = new Properties();
            props.setProperty("user","tomek.buslowski");
            props.setProperty("password","tomek");
            //props.setProperty("ssl","false");
            Connection conn = DriverManager.getConnection(url, props);
                
            //url = "jdbc:postgresql://localhost/iit_db_dev?user=tomek.buslowski&password=tomek&ssl=false";
            //conn = DriverManager.getConnection(url);
                
            System.out.println("Połączono do bazy: " + conn.getCatalog());
                
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            System.setProperty("java.security.policy", "security.policy");
            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new SecurityManager());
            }
            System.setProperty("java.rmi.server.hostname", "82.139.138.91");
            System.out.println("Hostname: " + System.getProperty("java.rmi.server.hostname"));
            System.setProperty("java.rmi.server.codebase", "http://82.139.138.91/tomek/");
            System.out.println("Codebase: " + System.getProperty("java.rmi.server.codebase"));
            //LocateRegistry.createRegistry(1099);
            
            MyServerImpl obj1 = new MyServerImpl();
            Naming.rebind("//82.139.138.91/ABC", obj1);
            System.out.println("Serwer oczekuje ...");

        } catch (RemoteException | MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
