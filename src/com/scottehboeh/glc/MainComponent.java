package com.scottehboeh.glc;

import com.scottehboeh.glc.forms.Form_Main;
import com.scottehboeh.glc.managers.StockManager;
import com.scottehboeh.glc.managers.UserManager;
import com.scottehboeh.glc.objects.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by 1503257 on 29/11/2017.
 */
public class MainComponent {

    /**
     * Initialize Main Components/Variables
     */
    public static JFrame mainframe = new JFrame("Glencaldy Learning Center - Welcome"); //Main JFrame Window
    public static Form_Main mainForm;
    public static ArrayList<ObjectStock> stockList;
    /**
     * Main Component Instance
     */
    private static ObjectCurrentSession currentSession;

    /**
     * Should the Program Populate the Program with Pre-made Users/Stock (Only use once!)
     */
    public static final boolean prePopulateProgram = false;

    /**
     * WindowMain Component - Program Initialization
     *
     * @param args - Given Launch Arguments (String[])
     */
    public static void main(String[] args) {

        currentSession = new ObjectCurrentSession();

        stockList = StockManager.getAllStock();

        /** If Create Fake Stock = True, create Fake Stock Items for Examples */
        if (prePopulateProgram) {

            ObjectUserStaff newStaffUser1 = new ObjectUserStaff(
                    "Cazzummeister24",
                    "Callum",
                    "Hall",
                    "chall@gmail.com",
                    "A1 - Upper Wellheads",
                    "cazzum123",
                    "chall@glencaldy.com",
                    "07139359493"
            );
            ObjectUserStaff newStaffUser2 = new ObjectUserStaff(
                    "Chazzum",
                    "Charlie",
                    "Marsh",
                    "chazumfps@gmail.com",
                    "4 Farmroad House",
                    "chazzy010",
                    "chazz@glencaldy.com",
                    "93504694205"
            );
            ObjectUserStaff newStaffUser3 = new ObjectUserStaff(
                    "FarmerHoggit",
                    "Sean",
                    "Robertson",
                    "farmerhoggit@btinternet.com",
                    "14 Mainstreet, Limekilns",
                    "unrool",
                    "seanr@glencaldy.com",
                    "01383872903"
            );

            ObjectUser newUser1 = new ObjectUserStaff(
                    "EDale",
                    "Euan",
                    "McDale",
                    "euanmcdale@gmail.com",
                    "Abbyview",
                    "12345",
                    "euan@glencaldy.com",
                    "93584758293"
            );

            ObjectUser newUser2 = new ObjectUserStaff(
                    "AlecB",
                    "Alec",
                    "Blythe",
                    "ablythe@gmail.com",
                    "Kirkcaldy House",
                    "alecman123",
                    "alec@glencaldy.com",
                    "04938495834"
            );

            ObjectUser newUser3 = new ObjectUserStaff(
                    "RossyCam",
                    "Ross",
                    "Cameron",
                    "rcameron@hotmail.co.uk",
                    "123 Evergreen Terrace",
                    "simpsons",
                    "rcameron@glencaldy.com",
                    "03948596361"
            );

            try {
                UserManager.saveUserObjectToFile(newStaffUser1, true);
                UserManager.saveUserObjectToFile(newStaffUser2, true);
                UserManager.saveUserObjectToFile(newStaffUser3, true);
                UserManager.saveUserObjectToFile(newUser1, true);
                UserManager.saveUserObjectToFile(newUser2, true);
                UserManager.saveUserObjectToFile(newUser3, true);
            } catch (IOException e) {
                e.printStackTrace();
            }

            /** Create Hardcoded Administrator Account (if doesn't exist) */
            try {
                if (UserManager.getUserIDFromUsername("root") == null || UserManager.getUserFromID(UserManager.getUserIDFromUsername("root")) == null) {
                    ObjectUserStaff testUser = new ObjectUserStaff("root",
                            "root",
                            "root",
                            "root@gmail.com",
                            "Not Required",
                            "root",
                            "root@gmail.com",
                            "00000000000");

                    try {
                        UserManager.saveUserObjectToFile(testUser, true);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            ObjectStockBook bookObject = new ObjectStockBook();
            bookObject.setBookAuthor("D. Seuss");
            bookObject.setBookNumberOfPages(200);
            bookObject.setBookPublisher("Dr. Seuss Publishing");
            bookObject.setBookSubjectArea("Adventure");
            bookObject.setBookTitle("How the Santa Stole Christmas");
            bookObject.setStockCost(56);

            try {
                StockManager.saveStockObjectToFile(bookObject, true);
            } catch (IOException e) {
                e.printStackTrace();
            }

            ObjectStockVideo videoObject = new ObjectStockVideo();
            videoObject.setRunningTime(5);
            videoObject.setVideoFormat("HD 1080");
            videoObject.setVideoGenre("Adventure");
            videoObject.setVideoPublisher("Jeff Goldblum");
            videoObject.setVideoStorageCaseType("DVD Case");
            videoObject.setVideoTitle("Jurassic Park");
            videoObject.setStockCost(40);

            try {
                StockManager.saveStockObjectToFile(videoObject, true);
            } catch (IOException e) {
                e.printStackTrace();
            }

            ObjectStockCompactDisc discObject = new ObjectStockCompactDisc();
            discObject.setRunningTime(200);
            discObject.setDiscArtist("Mozart");
            discObject.setDiscPublisher("Mozart Publishing Co.");
            discObject.setDiscTitle("Classical Music Compilation");
            discObject.setDiscNumberOfTracks(13);
            discObject.setStockCost(120);

            try {
                StockManager.saveStockObjectToFile(discObject, true);
            } catch (IOException e) {
                e.printStackTrace();
            }

            ObjectStockJournal journalObject = new ObjectStockJournal();
            journalObject.setISSN(20);
            journalObject.setJournalDate(Calendar.getInstance().getTime());
            journalObject.setJournalNumberOfPages(40);
            journalObject.setJournalIssueNumber(1);
            journalObject.setJournalSubjectArea("Wildlife");
            journalObject.setJournalTitle("Visiting the great Wildlife w/ Scotteh");
            journalObject.setStockCost(20);

            try {
                StockManager.saveStockObjectToFile(journalObject, true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        mainForm = new Form_Main();

        /** Initialize JFrame Instance */
        mainframe.setSize(new Dimension(650, 600));
        mainframe.setContentPane(mainForm.getPanel());
        mainframe.setVisible(true);
        mainframe.setResizable(false);

        mainForm.refreshGUI();

    }

    /**
     * Get Instance - Get Instance of Main Component
     *
     * @return - Given Instance (MainComponent)
     */
    public static ObjectCurrentSession getCurrentSession() {
        return currentSession;
    }

}
