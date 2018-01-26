package com.scottehboeh.glc.forms;

import com.scottehboeh.glc.MainComponent;
import com.scottehboeh.glc.managers.LogManager;
import com.scottehboeh.glc.managers.StockManager;
import com.scottehboeh.glc.managers.UserManager;
import com.scottehboeh.glc.objects.ObjectStock;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by 1503257 on 15/12/2017.
 */
public class Form_MyBorrowedStock extends JFrame {
    private JButton btnPrevious;
    private JPanel panel1;
    private JButton btnNext;
    private JLabel lblStockID;
    private JLabel lblStockName;
    private JLabel lblStockType;
    private JButton btnReturn;
    private JLabel lblStockDate;
    private ArrayList<ObjectStock> myStocks = new ArrayList<>();

    private int selectedIndex;

    public Form_MyBorrowedStock() {

        refreshUI();

        /** Return Item Button */
        btnReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (myStocks == null || myStocks.size() == 0) {
                    return;
                }

                /** Prompt the user if they wish to return the booked item */
                int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure that you wish to return this Stock Item?", "Return this Item?", dialogButton);

                /** If yes, begin removal process */
                if (dialogResult == JOptionPane.YES_OPTION) {

                    ObjectStock stockItem = myStocks.get(selectedIndex);
                    try {

                        int days = daysBetween(stockItem.getBorrowingCustomerDate(), Calendar.getInstance().getTime());

                        if (days > 7) {
                            JOptionPane.showMessageDialog(panel1, "The item that you have returned was loaned for more than 7 days! A fine has been added to your Account!", "Account Fined!", JOptionPane.ERROR_MESSAGE);

                            MainComponent.getCurrentSession().getSessionUser().addToFine(250);
                            UserManager.saveUserObjectToFile(MainComponent.getCurrentSession().getSessionUser(), true);

                            /** Log Activity */
                            LogManager.audit("User " + MainComponent.getCurrentSession().getSessionUser().getUserID() + " to be fined £250 for late loan return", MainComponent.getCurrentSession().getSessionUser().getUserID(), "userlog");

                        }

                        /** Return the Item */
                        StockManager.returnItem(stockItem.getStockID());

                        /** Log Activity */
                        LogManager.audit("Removed Stock Item Loan " + stockItem.getStockID() + " (" + stockItem.getMediaType() + ") from User ID: " + MainComponent.getCurrentSession().getSessionUser().getUserID(), stockItem.getStockID(), "stocklog");

                        /** Reset Selection Index */
                        selectedIndex = 0;

                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                }

                refreshUI();

            }
        });
        /** Previous */
        btnPrevious.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedIndex--;
                refreshUI();
            }
        });
        /** Next */
        btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedIndex++;
                refreshUI();
            }
        });
    }

    public void refreshUI() {

        if (selectedIndex > myStocks.size() - 1) {
            selectedIndex = 0;
        } else if (selectedIndex < 0) {
            selectedIndex = myStocks.size() - 1;
        }

        myStocks = StockManager.getAllBorrowedStockFromCustomerID(MainComponent.getCurrentSession().getSessionUser().getUserID());

        if (myStocks != null && myStocks.size() > 0 && myStocks.get(selectedIndex) != null) {

            lblStockID.setText("Stock ID: " + myStocks.get(selectedIndex).getStockID().toString());

            lblStockName.setText("Stock Name: " + myStocks.get(selectedIndex).getUnderstandableName());

            lblStockType.setText("Stock Type: " + myStocks.get(selectedIndex).getMediaType().toString());

            lblStockDate.setText("Borrowed Date: " + myStocks.get(selectedIndex).getBorrowingCustomerDateFormatted());

        } else {
            JOptionPane.showMessageDialog(panel1, "You have no borrowed items!", "Feature Unavailable!", JOptionPane.ERROR_MESSAGE);
        }

    }

    public JPanel getPanel() {
        return this.panel1;
    }

    /**
     * Days Between - Calculate the Time between two given Dates
     *
     * @param d1 - Given Date 1 (Date)
     * @param d2 - Given Date 2 (Date)
     * @return - Returned amount of Days (int)
     */
    public static int daysBetween(Date d1, Date d2) {
        return (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }

}
