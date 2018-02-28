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
import java.rmi.RemoteException;

import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;



public class MyServerImpl extends UnicastRemoteObject implements MyServerInt {

    int i = 0;

    protected MyServerImpl() throws RemoteException {
        super();
    }

    @Override
    public String getDescription(String text) throws RemoteException {
        i++;
        System.out.println("MyServerImpl.getDescription: " + text + " " + i);
        return "getDescription: " + text + " " + i;
    }
    
    @Override
    public String calculator(String text) throws RemoteException {
        System.out.println("Recieved from client: " + text);
        String result = "error";
        if( text.matches("^([\\d]+([\\.]{1}[\\d]+)?)[ ]*[+-\\/\\*]{1}[ ]*([\\d]+([\\.]{1}[\\d]+)?)[ ]*$") ){
            String as = ""; // a as string
            String op = ""; // operation
            String bs = ""; // b as string
            int i=0;
            for(;i<text.length(); i++) {
                if( Character.isDigit(text.charAt(i)) || text.charAt(i) == '.' ) {
                    as += text.charAt(i);
                } else {
                    break;
                }
            }
            for(;i<text.length(); i++) {
                if( text.charAt(i) == '+' || text.charAt(i) == '-' || text.charAt(i) == '*' || text.charAt(i) == '/') {
                    op += text.charAt(i);
                } else if( Character.isDigit(text.charAt(i)) ) {
                    break;
                }
            }
            for(;i<text.length(); i++) {
                if( Character.isDigit(text.charAt(i)) || text.charAt(i) == '.' ) {
                    bs += text.charAt(i);
                } else {
                    break;
                }
            }
            //System.out.println("Value in a: " + as);
            //System.out.println("Value in b: " + bs);
            //System.out.println("Value in op: " + op);
            double a = Double.parseDouble(as);
            double b = Double.parseDouble(bs);
            switch (op) {
                case "+":
                    result = String.valueOf(a + b);
                    break;
                case "-":
                    result = String.valueOf(a - b);
                    break;
                case "*":
                    result = String.valueOf(a * b);
                    break;
                case "/":
                    result = String.valueOf(b == 0 ? 0 : a/b);
                    break;
                default:
                    break;
            }
        } else {
            result = "invalid format";
        }
        System.out.println("Sended to client:  " + result);
        return result;
    }

    @Override
    public List<Product> productsList() throws RemoteException, SQLException {
        List<Product> products = new ArrayList<>();
        try {
            String url = "jdbc:postgresql://localhost/shop";
            Properties props = new Properties();
            props.setProperty("user","tomek.buslowski");
            props.setProperty("password","tomek");
            //props.setProperty("ssl","false");
            Connection conn = DriverManager.getConnection(url, props);
                
            System.out.println("Połączono do bazy: " + conn.getCatalog());
            
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM PRODUCTS;" );
            
            while ( rs.next() ) {
                Product p = new Product();
                p.id = rs.getInt("id");
                p.name = rs.getString("name");
                p.price =  rs.getDouble("price");
                products.add(p);
            }
            
            rs.close();
            stmt.close();
            conn.close();
                
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return products;
    }

    @Override
    public Product productByName(String name) throws RemoteException, SQLException {
        Product p = null;
        try {
            String url = "jdbc:postgresql://localhost/shop";
            Properties props = new Properties();
            props.setProperty("user","tomek.buslowski");
            props.setProperty("password","tomek");
            //props.setProperty("ssl","false");
            Connection conn = DriverManager.getConnection(url, props);
                
            System.out.println("Połączono do bazy: " + conn.getCatalog());
            
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM PRODUCTS WHERE name LIKE '%" + name + "%' LIMIT 1"; 
            ResultSet rs = stmt.executeQuery(query);
            
            while ( rs.next() ) {
                p = new Product();
                p.id = rs.getInt("id");
                p.name = rs.getString("name");
                p.price =  rs.getDouble("price");
            }
            
            rs.close();
            stmt.close();
            conn.close();
                
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return p;
    }
}
