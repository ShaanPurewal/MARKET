/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package market;

/**
 *
 * @author Purew
 */
public class Order {
    String TKR;
    int SHARES;
    double PRICE;
    String DATE;
    public Order(String tkr, int shares, double price, String date){
        TKR = tkr;
        SHARES = shares;
        PRICE = price;
        DATE = date;
    }
}
