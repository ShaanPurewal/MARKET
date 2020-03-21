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
public class Stocks {
    public String NAME = "";
    public int STOCKS = 0;
    public String TKR = "";
    public Order[] orders = new Order[0];
    public int NUM_OF_ORDERS = 0;
    
    public Stocks(String name, int stocks, String tkr){
        NAME = name;
        STOCKS = stocks;
        TKR = tkr;
    }
    
    public void addOrder(String tkr, int shares, double price, String date){
        Order[] tempOrders = orders;
        NUM_OF_ORDERS++;
        orders = new Order[NUM_OF_ORDERS];
        for (int i = 0; i < tempOrders.length; i++) {
            orders[i] = tempOrders[i];
        }
        orders[orders.length - 1] = new Order(tkr, shares, price, date);
    }
    
    public double totalValue(){
        double value = 0;
        for (int i = 0; i < orders.length; i++) {
            value += (orders[i].PRICE * orders[i].SHARES);
        }
        
        return value;
    }

    
}
