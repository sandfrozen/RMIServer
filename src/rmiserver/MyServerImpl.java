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
        System.out.println("Calculating: " + text);
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
        System.out.println("Calculated:  " + result);
        return result;
    }

    @Override
    public double sum(double a, double b) throws RemoteException {
        System.out.println("Calculating operation: " + a + " + " + b + " = " + a+b);
        return a+b;
    }
    @Override
    public double subtract(double a, double b) throws RemoteException {
        System.out.println("Calculating operation: " + a + " - " + b + " = " + (a-b));
        return a-b;
    }
    @Override
    public double multiply(double a, double b) throws RemoteException {
        System.out.println("Calculating operation: " + a + " * " + b + " = " + a*b);
        return a*b;
    }
    @Override
    public double divide(double a, double b) throws RemoteException {
        System.out.println("Calculating operation: " + a + " : " + b + " = " + (b == 0.0 ? 0.0 : a/b));
        return b == 0 ? 0.0 : a/b;
    }

}
