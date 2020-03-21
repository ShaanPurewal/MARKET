/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package market;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author Purew
 */
public class FileHandler {

    public double BALANCE_LIQUID;
    public double BALANCE_STOCK;
    public int AMOUNT_OF_STOCKS;
    public Stocks[] stocks;

    public void loadAccount() throws FileNotFoundException, IOException {
        File file = new File("config.txt");
        FileReader fileReader = new FileReader(file);
        BufferedReader read = new BufferedReader(fileReader);
        BALANCE_LIQUID = Double.parseDouble(read.readLine().substring(16));
        BALANCE_STOCK = Double.parseDouble(read.readLine().substring(15));
        AMOUNT_OF_STOCKS = Integer.parseInt(read.readLine().substring(13));
        stocks = new Stocks[AMOUNT_OF_STOCKS];
        
        for (int i = 0; i < AMOUNT_OF_STOCKS; i++) {
            String name = read.readLine().substring(6);
            int numOfOrders = Integer.parseInt(read.readLine().substring(10));
            int stocksOwned = Integer.parseInt(read.readLine().substring(10));
            String tickerSymbol = read.readLine().substring(10);
            stocks[i] = new Stocks(name, stocksOwned, tickerSymbol);
            for (int j = 0; j < numOfOrders; j++) {
                String tkr = read.readLine();
                String date = read.readLine().substring(8);
                int shares = Integer.parseInt(read.readLine().substring(10));
                double price = Double.parseDouble(read.readLine().substring(9));
                stocks[i].addOrder(tkr, shares, price, date);
            }
        }
        
        
    }

    public String readAccount(String TKR, String EX) throws MalformedURLException, IOException {
        URL url = new URL("https://ca.finance.yahoo.com/quote/" + TKR + EX + "?p=" + TKR + EX + "&tsrc=fin-srch");
        URLConnection urlCon = url.openConnection();
        InputStreamReader inStream = new InputStreamReader(urlCon.getInputStream());
        BufferedReader buff = new BufferedReader(inStream);

        String line = buff.readLine();
        while (line != null) {
            if (line.contains("currentPrice")) {
                String price = line.substring(line.indexOf("currentPrice") + 21, line.indexOf(",", line.indexOf("currentPrice")));
                return price;
            }

            line = buff.readLine();
        }
        return "No account found!";
    }
    
    public void buyAccount(String TKR, String EX, int numOfShares) throws MalformedURLException, IOException {
        URL url = new URL("https://ca.finance.yahoo.com/quote/" + TKR + EX + "?p=" + TKR + EX + "&tsrc=fin-srch");
        URLConnection urlCon = url.openConnection();
        InputStreamReader inStream = new InputStreamReader(urlCon.getInputStream());
        BufferedReader buff = new BufferedReader(inStream);

        String line = buff.readLine();
        String price = "0";
        while (line != null) {
            if (line.contains("currentPrice")) {
                price = line.substring(line.indexOf("currentPrice") + 21, line.indexOf(",", line.indexOf("currentPrice")));
            }

            line = buff.readLine();
        }
        
        boolean found = false;
        
        for (int i = 0; i < AMOUNT_OF_STOCKS; i++) {
            if(stocks[i].NAME.equals(TKR + EX + "")){
                ammendAcc(i, numOfShares, TKR, Double.parseDouble(price));
                found = true;
            }
        }
        
        if(found == false){
            AMOUNT_OF_STOCKS++;
            Stocks[] temp = stocks;
            stocks = new Stocks[AMOUNT_OF_STOCKS];
            for (int i = 0; i < temp.length; i++) {
                stocks[i] = temp[i];
            }
            addAcc(AMOUNT_OF_STOCKS - 1, TKR + EX + "", numOfShares, TKR, Double.parseDouble(price));
        }
        
        BALANCE_LIQUID -= numOfShares * Double.parseDouble(price);
    }

    public void addAcc(int index, String name, int stocksOwned, String tickerSymbol, double buyValue) {
        stocks[index] = new Stocks(name, stocksOwned, tickerSymbol);
        stocks[index].addOrder(tickerSymbol, stocksOwned, buyValue, Window.getTime());
    }
    
    public void saveFile() throws IOException{
        File file = new File("config.txt");
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter buff = new BufferedWriter(fileWriter);
        calcStockValue();
        String text = "";
        
        text += "BALANCE_LIQUID: " + BALANCE_LIQUID + "\n";
        text += "BALANCE_STOCK: " + BALANCE_STOCK + "\n";
        text += "INVESTMENTS: " + AMOUNT_OF_STOCKS;
        for (int i = 0; i < AMOUNT_OF_STOCKS; i++) {
            text += "\nNAME: " + stocks[i].NAME;
            text += "\n  ORDERS: " + stocks[i].NUM_OF_ORDERS;
            text += "\n  SHARES: " + stocks[i].STOCKS;
            text += "\n  TICKER: " + stocks[i].TKR;
            for (int j = 0; j < stocks[i].NUM_OF_ORDERS; j++) {
                text += "\n" + stocks[i].orders[j].TKR;
                text += "\n  DATE: " + stocks[i].orders[j].DATE;
                text += "\n  SHARES: " + stocks[i].orders[j].SHARES;
                text += "\n  PRICE: " + stocks[i].orders[j].PRICE;
            }
        }
        
        buff.write(text);
        buff.flush();
    }

    public void ammendAcc(int i, int numOfShares, String tkr, double price) {
        stocks[i].STOCKS += numOfShares;
        stocks[i].addOrder(tkr, numOfShares, price, Window.getTime());
    }

    private void calcStockValue() {
        BALANCE_STOCK = 0;
        for (int i = 0; i < stocks.length; i++) {
            BALANCE_STOCK += stocks[i].totalValue();
        }
    }
}
