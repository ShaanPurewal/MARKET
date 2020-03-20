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
    public double BUY_VALUE = 0;
    
    public Stocks(String name, int stocks, String tkr, double buyValue){
        NAME = name;
        STOCKS = stocks;
        TKR = tkr;
        BUY_VALUE = buyValue;
    }
}
