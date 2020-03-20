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
        BALANCE_LIQUID = Double.parseDouble(read.readLine());
        BALANCE_STOCK = Double.parseDouble(read.readLine());
        AMOUNT_OF_STOCKS = Integer.parseInt(read.readLine());
        stocks = new Stocks[AMOUNT_OF_STOCKS];
        
        for (int i = 0; i < AMOUNT_OF_STOCKS; i++) {
            String name = read.readLine();
            int stocksOwned = Integer.parseInt(read.readLine().substring(2));
            String tickerSymbol = read.readLine().substring(2);
            double buyValue = Double.parseDouble(read.readLine().substring(2));
            stocks[i] = new Stocks(name, stocksOwned, tickerSymbol, buyValue);
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
                ammendAcc(i, numOfShares);
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
        BALANCE_STOCK += numOfShares * Double.parseDouble(price);
    }

    public void addAcc(int index, String name, int stocksOwned, String tickerSymbol, double buyValue) {
        stocks[index] = new Stocks(name, stocksOwned, tickerSymbol, buyValue);
    }
    
    public void saveFile() throws IOException{
        File file = new File("config.txt");
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter buff = new BufferedWriter(fileWriter);
        
        String text = "";
        
        text += BALANCE_LIQUID + "\n";
        text += BALANCE_STOCK + "\n";
        text += AMOUNT_OF_STOCKS;
        for (int i = 0; i < AMOUNT_OF_STOCKS; i++) {
            text += "\n" + stocks[i].NAME;
            text += "\n  " + stocks[i].STOCKS;
            text += "\n  " + stocks[i].TKR;
            text += "\n  " + stocks[i].BUY_VALUE;
        }
        
        buff.write(text);
        buff.flush();
    }

    public void ammendAcc(int i, int numOfShares) {
        stocks[i].STOCKS += numOfShares;
    }
}
