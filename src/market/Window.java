/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package market;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Purew
 */
public class Window extends javax.swing.JFrame implements ActionListener {

    static Timer timer = new Timer();
    static FileHandler fileHandler = new FileHandler();
    static String EXCHANGE = ".TO";
    static String currentPage = "Main";
    static String decision = "CLOSED";

    public static String getTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("E, dd-MMM-yyyy HH:mm:ss a");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    /**
     * Creates new form Window
     */
    public Window() throws IOException {
        initComponents();

        //time stuff
        openMain();

        //load the config file
        fileHandler.loadAccount();

        MARKET.addActionListener(this);
        SHARES.addActionListener(this);
        TKRSYM.addActionListener(this);
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
        SEARCH = new javax.swing.JButton();
        ACCOUNT = new javax.swing.JButton();
        CONFIG = new javax.swing.JButton();
        accountInfo = new javax.swing.JLabel();
        INVESTMENTS = new javax.swing.JButton();
        MAIN = new javax.swing.JButton();
        SHARES = new javax.swing.JTextField();
        TKRSYM = new javax.swing.JTextField();
        MARKET = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MARKET TIME! - Shaan Purewal");
        setMinimumSize(new java.awt.Dimension(1600, 1200));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(null);

        text.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        text.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        text.setText("MARKET");
        text.setToolTipText("");
        text.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        text.setAlignmentX(0.5F);
        getContentPane().add(text);
        text.setBounds(390, 40, 685, 310);

        SEARCH.setText("BANK");
        SEARCH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SEARCHActionPerformed(evt);
            }
        });
        getContentPane().add(SEARCH);
        SEARCH.setBounds(690, 710, 159, 92);

        ACCOUNT.setText("ACCOUNT");
        ACCOUNT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ACCOUNTActionPerformed(evt);
            }
        });
        getContentPane().add(ACCOUNT);
        ACCOUNT.setBounds(240, 710, 159, 92);

        CONFIG.setText("CONFIG");
        CONFIG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CONFIGActionPerformed(evt);
            }
        });
        getContentPane().add(CONFIG);
        CONFIG.setBounds(1150, 712, 159, 92);

        accountInfo.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        accountInfo.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        accountInfo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().add(accountInfo);
        accountInfo.setBounds(30, 190, 850, 900);

        INVESTMENTS.setText("INVESTMENTS");
        INVESTMENTS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                INVESTMENTSActionPerformed(evt);
            }
        });
        getContentPane().add(INVESTMENTS);
        INVESTMENTS.setBounds(1400, 1060, 159, 92);

        MAIN.setText("MAIN");
        MAIN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MAINActionPerformed(evt);
            }
        });
        getContentPane().add(MAIN);
        MAIN.setBounds(1400, 40, 159, 92);

        SHARES.setText("Please enter the amount of shares to be bought");
        getContentPane().add(SHARES);
        SHARES.setBounds(60, 460, 670, 50);

        TKRSYM.setText("Please enter the TKR Symbol of the requested stock");
        getContentPane().add(TKRSYM);
        TKRSYM.setBounds(60, 340, 670, 50);

        MARKET.setText("Please enter the appropriate market extension");
        getContentPane().add(MARKET);
        MARKET.setBounds(60, 400, 670, 50);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ACCOUNTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ACCOUNTActionPerformed
        openAccInfo();
    }//GEN-LAST:event_ACCOUNTActionPerformed

    private void SEARCHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SEARCHActionPerformed
        if(decision.equalsIgnoreCase("CLOSED")){
            JOptionPane.showMessageDialog(this, "The MARKET is currently closed!");
        }else{
            openBank();
        }
    }//GEN-LAST:event_SEARCHActionPerformed

    private void CONFIGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CONFIGActionPerformed
        Desktop desk = Desktop.getDesktop();
        try {
            desk.open(new File("config.txt"));
        } catch (IOException ex) {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_CONFIGActionPerformed

    private void INVESTMENTSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_INVESTMENTSActionPerformed
        new Investments(fileHandler.stocks).setVisible(true);
    }//GEN-LAST:event_INVESTMENTSActionPerformed

    private void MAINActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MAINActionPerformed
        openMain();
    }//GEN-LAST:event_MAINActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        try {
            fileHandler.saveFile();
        } catch (IOException ex) {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formWindowClosing

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws IOException {
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
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Window().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ACCOUNT;
    private javax.swing.JButton CONFIG;
    private javax.swing.JButton INVESTMENTS;
    private javax.swing.JButton MAIN;
    private javax.swing.JTextField MARKET;
    private javax.swing.JButton SEARCH;
    private javax.swing.JTextField SHARES;
    private javax.swing.JTextField TKRSYM;
    private javax.swing.JLabel accountInfo;
    private static javax.swing.JLabel text;
    // End of variables declaration//GEN-END:variables

    private static void runTime() {
        String MASTER = getTime();
        String DAY = MASTER.substring(0, MASTER.indexOf(" ") - 1);
        String TIME = MASTER.substring(MASTER.indexOf(":") - 2);
        int HOUR = Integer.parseInt(TIME.substring(0, 2));
        int MINUTE = Integer.parseInt(TIME.substring(3, 5));

        if (DAY.equalsIgnoreCase("Sat") || DAY.equalsIgnoreCase("Sun")) {
            decision = "CLOSED";
        }else{
            if (HOUR < 16 && HOUR >= 10) {
                decision = "OPEN";
            } else if (HOUR == 9 && MINUTE >= 30) {
                decision = "OPEN";
            } else {
                decision = "CLOSED";
            }
        }

        text.setText("<html><body><center>The current date is: " + MASTER + "<br>and the market is " + decision + "</center></body></html>");
    }

    private void openMain() {
        ACCOUNT.setVisible(true);
        CONFIG.setVisible(true);
        SEARCH.setVisible(true);

        accountInfo.setVisible(false);
        INVESTMENTS.setVisible(false);
        MAIN.setVisible(false);
        SHARES.setVisible(false);
        TKRSYM.setVisible(false);
        MARKET.setVisible(false);
        timer.cancel();
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                runTime();
            }
        }, 1000, 1000);

        currentPage = "Main";
    }

    private void openAccInfo() {
        ACCOUNT.setVisible(false);
        CONFIG.setVisible(false);
        SEARCH.setVisible(false);

        accountInfo.setVisible(true);
        INVESTMENTS.setVisible(true);
        MAIN.setVisible(true);
        SHARES.setVisible(false);
        TKRSYM.setVisible(false);
        MARKET.setVisible(false);

        accountInfo.setText("<html><body>Your current CASH BALANCE is: " + fileHandler.BALANCE_LIQUID + "<br>"
                + "Your current STOCK VALUE is: " + fileHandler.BALANCE_STOCK + "<br>"
                + "You have " + fileHandler.stocks.length + " INVESTMENTS"
                + "</body></html>");

        currentPage = "Account";
    }

    private void openBank() {
        timer.cancel();

        ACCOUNT.setVisible(false);
        CONFIG.setVisible(false);
        SEARCH.setVisible(false);
        accountInfo.setVisible(false);
        INVESTMENTS.setVisible(false);

        MAIN.setVisible(true);
        SHARES.setVisible(true);
        TKRSYM.setVisible(true);
        MARKET.setVisible(true);

        text.setText("Welcome To The INVESTMENT Bank");
        TKRSYM.setText("Please enter the TKR Symbol of the requested stock");
        MARKET.setText("Please enter the appropriate market extension");
        SHARES.setText("Please enter the amount of shares to be bought");

        currentPage = "Bank";
        TKRSYM.requestFocus();
        TKRSYM.selectAll();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (currentPage.equalsIgnoreCase("Bank")) {
            if (ae.getSource().equals(TKRSYM)) {
                MARKET.requestFocus();
                MARKET.selectAll();
            } else if (ae.getSource().equals(MARKET)) {
                SHARES.requestFocus();
                SHARES.selectAll();
            } else {
                try {
                    if (fileHandler.BALANCE_LIQUID >= (Double.parseDouble(SHARES.getText()) * Double.parseDouble(fileHandler.readAccount(TKRSYM.getText(), MARKET.getText())))) {
                        int answer = JOptionPane.showConfirmDialog(this, "Are you sure?", "Confirmation Window", JOptionPane.YES_NO_OPTION);
                        if (answer == JOptionPane.YES_OPTION) {
                            fileHandler.buyAccount(TKRSYM.getText(), MARKET.getText(), Integer.parseInt(SHARES.getText()));
                            fileHandler.saveFile();
                            text.setText("Purchase Complete");
                        } else {
                            text.setText("Order Cancelled");
                        }

                    } else {
                        text.setText("Insufficient Funds");
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

}
