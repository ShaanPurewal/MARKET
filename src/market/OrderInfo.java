/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package market;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Purew
 */
public class OrderInfo extends javax.swing.JFrame {

    FileHandler fileHandler = new FileHandler();

    /**
     * Creates new form InvestmentsInfo
     */
    public OrderInfo(Stocks stock, int i) throws IOException {
        initComponents();

        if (i != -1) {
            String Ordername = stock.orders[i].TKR;
            String name = stock.NAME;
            fileHandler.loadAccount();
            String currentPrice = fileHandler.readAccount(name.substring(0, name.indexOf(".")), name.substring(name.indexOf(".")));
            double calculation = (stock.orders[i].SHARES * Double.parseDouble(currentPrice)) - (stock.orders[i].PRICE * stock.orders[i].SHARES);

            text.setText("<html><body>" + Ordername + "<br> <br>"
                    + "You have bought " + stock.orders[i].SHARES + " shares" + "<br> <br>"
                    + "You bought these shares at a price of $" + stock.orders[i].PRICE + " CAD per share<br> <br>"
                    + "The current price per share is $" + currentPrice + " CAD<br> <br>"
                    + "Your net income from this trade is " + calculation + "</body></html>");
        }else{
            fileHandler.loadAccount();
            String name = stock.NAME;
            double averagePrice = stock.totalValue() / stock.STOCKS;
            String currentPrice = fileHandler.readAccount(name.substring(0, name.indexOf(".")), name.substring(name.indexOf(".")));
            double calculation = (stock.STOCKS * Double.parseDouble(currentPrice)) - (stock.totalValue());
            
            text.setText("<html><body>" + "SUMMARY: " + name + "<br> <br>"
                    + "You have made " + stock.NUM_OF_ORDERS + " orders" + "<br> <br>"
                    + "You own " + stock.STOCKS + " shares" + "<br> <br>"
                    + "With a total value of $" + stock.totalValue() + " CAD" + "<br> <br>"
                    + "At an average price of $" + averagePrice + " CAD per share<br> <br>"
                    + "The current price per share is $" + currentPrice + " CAD<br> <br>"
                    + "Your net income from this trade is " + calculation + "</body></html>");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        text = new javax.swing.JLabel();

        text.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        text.setText("jLabel1");
        text.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(text, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(text, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(OrderInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OrderInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OrderInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OrderInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new OrderInfo(null, 0).setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(OrderInfo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel text;
    // End of variables declaration//GEN-END:variables
}
