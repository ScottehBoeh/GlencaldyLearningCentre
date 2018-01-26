package com.scottehboeh.glc.managers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.scottehboeh.glc.enums.EnumMediaType;
import com.scottehboeh.glc.objects.*;
import com.scottehboeh.glc.referrals.ReferralDirectories;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * Created by 1503257 on 13/12/2017.
 * <p>
 * Stock Manager
 * <p>
 * The Stock Manager Creates, Edits, Removes and overall Manages Stock
 * using the program. It also makes use of the stocks folder to save
 * and load stock.
 */
public class StockManager {

    /**
     * Get Stock As File From ID - Get the Stock File from their ID
     *
     * @param givenUUID - Given Stock ID (UUID)
     * @return - The Stock File (File)
     */
    public static File getStockAsFileFromID(UUID givenUUID) {

        /** If the Direcotry does not exist, create it */
        if (!ReferralDirectories.stockDirectory.exists()) {
            ReferralDirectories.stockDirectory.mkdir();
        }

        /** Cast to new File Instance and return */
        return new File(ReferralDirectories.stockDirectory + "/" + givenUUID + ".stock");

    }

    /**
     * Get Stock From ID - Get the Stock from given UUID
     *
     * @param givenUUID - Given Stock ID (UUID)
     * @return - Returns the Stock Object of the Stock (ObjectStock)
     */
    public static ObjectStock getStockFromID(UUID givenUUID) throws IOException {

        /** Cast Stock ID to File */
        File stockFile = getStockAsFileFromID(givenUUID);

        /** If the Stock File Exists, Cast it */
        if (stockFile.exists()) {
            /** Create new buff reader instance */
            BufferedReader br = new BufferedReader(new FileReader(stockFile));
            /** Cast Stock Object from Gson Instance */
            ObjectStock stockObject = new Gson().fromJson(br, ObjectStock.class);
            /** Close buff reader instance */
            br.close();
            return stockObject;
        } else {
            return null;
        }

    }

    public static ObjectStockBook getStockFromIDAsBook(UUID givenUUID) throws IOException {

        /** Cast Stock ID to File */
        File stockFile = getStockAsFileFromID(givenUUID);

        /** If the Stock File Exists, Cast it */
        if (stockFile.exists()) {
            /** Create new buff reader instance */
            BufferedReader br = new BufferedReader(new FileReader(stockFile));
            /** Cast Stock Object from Gson Instance */
            ObjectStockBook stockObject = new Gson().fromJson(br, ObjectStockBook.class);
            /** Close buff reader instance */
            br.close();
            return stockObject;
        } else {
            return null;
        }

    }

    public static ObjectStockVideo getStockFromIDAsVideo(UUID givenUUID) throws IOException {

        /** Cast Stock ID to File */
        File stockFile = getStockAsFileFromID(givenUUID);

        /** If the Stock File Exists, Cast it */
        if (stockFile.exists()) {
            /** Create new buff reader instance */
            BufferedReader br = new BufferedReader(new FileReader(stockFile));
            /** Cast Stock Object from Gson Instance */
            ObjectStockVideo stockObject = new Gson().fromJson(br, ObjectStockVideo.class);
            /** Close buff reader instance */
            br.close();
            return stockObject;
        } else {
            return null;
        }

    }

    public static ObjectStockJournal getStockFromIDAsJournal(UUID givenUUID) throws IOException {

        /** Cast Stock ID to File */
        File stockFile = getStockAsFileFromID(givenUUID);

        /** If the Stock File Exists, Cast it */
        if (stockFile.exists()) {
            /** Create new buff reader instance */
            BufferedReader br = new BufferedReader(new FileReader(stockFile));
            /** Cast Stock Object from Gson Instance */
            ObjectStockJournal stockObject = new Gson().fromJson(br, ObjectStockJournal.class);
            /** Close buff reader instance */
            br.close();
            return stockObject;
        } else {
            return null;
        }

    }

    public static ObjectStockCompactDisc getStockFromIDAsCompactDisc(UUID givenUUID) throws IOException {

        /** Cast Stock ID to File */
        File stockFile = getStockAsFileFromID(givenUUID);

        /** If the Stock File Exists, Cast it */
        if (stockFile.exists()) {
            /** Create new buff reader instance */
            BufferedReader br = new BufferedReader(new FileReader(stockFile));
            /** Cast Stock Object from Gson Instance */
            ObjectStockCompactDisc stockObject = new Gson().fromJson(br, ObjectStockCompactDisc.class);
            /** Close buff reader instance */
            br.close();
            return stockObject;
        } else {
            return null;
        }

    }

    /**
     * Does Stock Exist with ID - Does a Stock Object Exist with the given UUID
     *
     * @param givenUUID - Given Stock ID (UUID)
     * @return - Yes/No - Does/Doesn't Exist
     */
    public static boolean doesStockExistWithID(UUID givenUUID) {

        /** Returns exists() value of Stock File */
        return getStockAsFileFromID(givenUUID).exists();

    }

    /**
     * Save Stock Object to File - Save a Stock Object Instance to File
     *
     * @param givenStockObject - Given Stock Object (ObjecStock)
     */
    public static void saveStockObjectToFile(ObjectStock givenStockObject, boolean shouldOverwrite) throws IOException {

        /** Log Activity */
        LogManager.audit("Saved Stock Item " + givenStockObject.getStockID() + "(" + givenStockObject.getMediaType() + ") to File", givenStockObject.getStockID(), "stocklog");

        /** If the Stock File doesn't exist or should Overwrite File */
        if (!doesStockExistWithID(givenStockObject.getStockID()) || shouldOverwrite) {

            /** Get new Gson Builder Instance */
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            /** If the Stock File does not exist, create it */
            if (!getStockAsFileFromID(givenStockObject.getStockID()).exists()) {
                getStockAsFileFromID(givenStockObject.getStockID()).createNewFile();
            }

            /** Create new Writer Instance */
            Writer writer = new FileWriter(getStockAsFileFromID(givenStockObject.getStockID()));

            /** Check for different instances of Stock Object */
            if (givenStockObject instanceof ObjectStockBook) {

                /** If Staff, Cast to new Stock Object instance */
                ObjectStockBook stockObject = (ObjectStockBook) givenStockObject;
                writer.write(gson.toJson(stockObject));

            } else if (givenStockObject instanceof ObjectStockJournal) {

                /** If Staff, Cast to new Stock Object instance */
                ObjectStockJournal stockObject = (ObjectStockJournal) givenStockObject;
                writer.write(gson.toJson(stockObject));

            } else if (givenStockObject instanceof ObjectStockVideo) {

                /** If Staff, Cast to new Stock Object instance */
                ObjectStockVideo stockObject = (ObjectStockVideo) givenStockObject;
                writer.write(gson.toJson(stockObject));

            } else if (givenStockObject instanceof ObjectStockCompactDisc) {

                /** If Staff, Cast to new Stock Object instance */
                ObjectStockCompactDisc stockObject = (ObjectStockCompactDisc) givenStockObject;
                writer.write(gson.toJson(stockObject));

            } else {

                /** Write Stock Object to Gson Json File */
                writer.write(gson.toJson(givenStockObject));

            }

            /** Close Writer Instance */
            writer.close();

        }

    }

    /**
     * Get All Stocks - Get all Registered/Documented Stock
     *
     * @return - Given Array List of Stocks (ArrayList<ObjectStock>)
     */
    public static ArrayList<ObjectStock> getAllStock() {


        File folder = new File("stock/");
        File[] listOfFiles = folder.listFiles();
        ArrayList<ObjectStock> stockList = new ArrayList<ObjectStock>();

        if (listOfFiles != null && listOfFiles.length > 0) {
            for (File file : listOfFiles) {
                if (file.isFile() && !file.getName().contains("log")) {

                    String fileName = file.getName().toString().split("\\.")[0];

                    if (StockManager.doesStockExistWithID(UUID.fromString(fileName))) {

                        try {
                            ObjectStock theStock = getStockFromID(UUID.fromString(fileName));

                            stockList.add(theStock);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }

                }
            }
        }

        return stockList;

    }

    /**
     * Get All Journal Stock - Get a list of all Journal Stocks
     *
     * @return - Given List of Journal Stocks (ArrayList<ObjectJournal>)
     */
    public static ArrayList<ObjectStockJournal> getAllJournalStock() {

        ArrayList<ObjectStockJournal> books = new ArrayList<ObjectStockJournal>();

        for (ObjectStock stock : getAllStock()) {

            if (stock.getMediaType() == EnumMediaType.JOURNAL) {
                File mediaFile = getStockAsFileFromID(stock.getStockID());
                BufferedReader br = null;
                try {
                    br = new BufferedReader(new FileReader(mediaFile));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                /** Get quote object from file name */
                ObjectStockJournal theQuote = new Gson().fromJson(br, ObjectStockJournal.class);
                books.add(theQuote);
            }

        }

        return books;

    }

    public static ArrayList<ObjectStockVideo> getAllVideoStockThatContains(String givenContainingString) {

        ArrayList<ObjectStockVideo> videos = new ArrayList<ObjectStockVideo>();

        for (ObjectStockVideo videoObject : getAllVideoStock()) {

            if (videoObject.getUnderstandableName() != null && videoObject.getUnderstandableName().contains(givenContainingString)) {
                videos.add(videoObject);
            }

        }

        return videos;

    }

    public static ArrayList<ObjectStockJournal> getAllJournalStockThatContains(String givenContainingString) {

        ArrayList<ObjectStockJournal> journals = new ArrayList<ObjectStockJournal>();

        for (ObjectStockJournal videoObject : getAllJournalStock()) {

            if (videoObject.getUnderstandableName() != null && videoObject.getUnderstandableName().contains(givenContainingString)) {
                journals.add(videoObject);
            }

        }

        return journals;

    }

    public static ArrayList<ObjectStockCompactDisc> getAllCompactDiscStockThatContains(String givenContainingString) {

        ArrayList<ObjectStockCompactDisc> compactDiscs = new ArrayList<ObjectStockCompactDisc>();

        for (ObjectStockCompactDisc videoObject : getAllCompactDiscStock()) {

            if (videoObject.getUnderstandableName() != null && videoObject.getUnderstandableName().contains(givenContainingString)) {
                compactDiscs.add(videoObject);
            }

        }

        return compactDiscs;

    }

    public static ArrayList<ObjectStockBook> getAllBookStockThatContains(String givenContainingString) {

        ArrayList<ObjectStockBook> books = new ArrayList<ObjectStockBook>();

        for (ObjectStockBook videoObject : getAllBookStock()) {

            if (videoObject.getUnderstandableName() != null && videoObject.getUnderstandableName().contains(givenContainingString)) {
                books.add(videoObject);
            }

        }

        return books;

    }

    /**
     * Get All Video Stock - Get a list of all Video Stocks
     *
     * @return - Given List of Video Stocks (ArrayList<ObjectVideo>)
     */
    public static ArrayList<ObjectStockVideo> getAllVideoStock() {

        ArrayList<ObjectStockVideo> books = new ArrayList<ObjectStockVideo>();
        ArrayList<ObjectStock> stocks = getAllStock();

        for (ObjectStock stock : stocks) {
            if (stock.getMediaType() == EnumMediaType.VIDEO) {

                File mediaFile = getStockAsFileFromID(stock.getStockID());
                BufferedReader br = null;
                try {
                    br = new BufferedReader(new FileReader(mediaFile));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                /** Get quote object from file name */
                ObjectStockVideo theQuote = new Gson().fromJson(br, ObjectStockVideo.class);
                books.add(theQuote);

            }

        }

        return books;

    }

    /**
     * Get All Compact Disc Stock - Get a list of all Compact Disc Stocks
     *
     * @return - Given List of Compact Disc Stocks (ArrayList<ObjectStockCompactDisc>)
     */
    public static ArrayList<ObjectStockCompactDisc> getAllCompactDiscStock() {

        ArrayList<ObjectStockCompactDisc> books = new ArrayList<ObjectStockCompactDisc>();

        for (ObjectStock stock : getAllStock()) {

            if (stock.getMediaType() == EnumMediaType.COMPACTDISC) {
                File mediaFile = getStockAsFileFromID(stock.getStockID());
                BufferedReader br = null;
                try {
                    br = new BufferedReader(new FileReader(mediaFile));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                /** Get quote object from file name */
                ObjectStockCompactDisc theQuote = new Gson().fromJson(br, ObjectStockCompactDisc.class);
                books.add(theQuote);
            }

        }

        return books;

    }

    /**
     * Get All Book Stock - Get a list of all Book Stocks
     *
     * @return - Given List of Book Stocks (ArrayList<ObjectBook>)
     */
    public static ArrayList<ObjectStockBook> getAllBookStock() {

        ArrayList<ObjectStockBook> books = new ArrayList<ObjectStockBook>();

        for (ObjectStock stock : getAllStock()) {

            if (stock.getMediaType() == EnumMediaType.BOOK) {
                File mediaFile = getStockAsFileFromID(stock.getStockID());
                BufferedReader br = null;
                try {
                    br = new BufferedReader(new FileReader(mediaFile));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                /** Get quote object from file name */
                ObjectStockBook theQuote = new Gson().fromJson(br, ObjectStockBook.class);
                books.add(theQuote);
            }

        }

        return books;

    }

    /**
     * Get All Stocks - Get all Registered/Documented Stock
     *
     * @return - Given Array List of Stocks (ArrayList<ObjectStock>)
     */
    public static ArrayList<ObjectStock> getAllBorrowedStockFromCustomerID(UUID givenCustomerID) {

        ArrayList<ObjectStock> customerStock = new ArrayList<>();

        /** Loop through each item in all stock */
        for (ObjectStock stock : getAllStock()) {

            /** If Stock ID is not null and equals customer ID */
            if (stock.getBorrowingCustomerID() != null && stock.getBorrowingCustomerID().equals(givenCustomerID)) {
                /** Add stock to Customer Stock Array List */
                customerStock.add(stock);
            }

        }

        return customerStock;

    }

    /**
     * Has Item Been Borrowed - Check if a Specific Stock Item has been Borrowed
     *
     * @param givenStockID - Given Stock ID
     * @return - True/False - The Item Has/Hasn't been Borrowed
     * @throws IOException - Returned Exception
     */
    public static boolean hasItemBeenBorrowed(UUID givenStockID) throws IOException {

        return (getStockFromID(givenStockID).getBorrowingCustomerID() != null);

    }

    /**
     * Borrow Item - Borrow a Specific Item for a Specific Customer/User
     *
     * @param givenUserID  - Given User ID (UUID)
     * @param givenStockID - Given Stock ID (UUID)
     */
    public static void borrowItem(UUID givenUserID, UUID givenStockID) throws IOException {

        /** If a User and Stock exist with the given UUID's */
        if (doesStockExistWithID(givenStockID) && UserManager.doesUserExistWithID(givenUserID)) {

            /** Get the Stock Item and set a new user Customer ID */
            ObjectStock stockItem = getStockFromID(givenStockID);

            /** Switch Statement for Stock Media Type */
            switch (stockItem.getMediaType()) {

                case BOOK:
                    ObjectStockBook bookStock = getStockFromIDAsBook(givenStockID);
                    bookStock.setBorrowingCustomerID(givenUserID);
                    bookStock.setBorrowingCustomerDate(Calendar.getInstance().getTime());
                    /** Save the Stock Object */
                    saveStockObjectToFile(bookStock, true);
                    break;
                case VIDEO:
                    ObjectStockVideo videoStock = getStockFromIDAsVideo(givenStockID);
                    videoStock.setBorrowingCustomerID(givenUserID);
                    videoStock.setBorrowingCustomerDate(Calendar.getInstance().getTime());
                    /** Save the Stock Object */
                    saveStockObjectToFile(videoStock, true);
                    break;
                case COMPACTDISC:
                    ObjectStockCompactDisc compactDiscStock = getStockFromIDAsCompactDisc(givenStockID);
                    compactDiscStock.setBorrowingCustomerID(givenUserID);
                    compactDiscStock.setBorrowingCustomerDate(Calendar.getInstance().getTime());
                    /** Save the Stock Object */
                    saveStockObjectToFile(compactDiscStock, true);
                    break;
                case JOURNAL:
                    ObjectStockJournal journalStock = getStockFromIDAsJournal(givenStockID);
                    journalStock.setBorrowingCustomerID(givenUserID);
                    journalStock.setBorrowingCustomerDate(Calendar.getInstance().getTime());
                    /** Save the Stock Object */
                    saveStockObjectToFile(journalStock, true);
                    break;
                default:

                    break;

            }

        }

    }

    public static void returnItem(UUID givenStockID) throws IOException {

        /** If a Stock Item exists with the given UUID */
        if (doesStockExistWithID(givenStockID)) {

            /** Get the Stock Item */
            ObjectStock stockItem = getStockFromID(givenStockID);

            /** Switch Statement for Stock Media Type */
            switch (stockItem.getMediaType()) {

                case BOOK:
                    ObjectStockBook bookStock = getStockFromIDAsBook(givenStockID);
                    bookStock.setBorrowingCustomerID(null);
                    bookStock.setBorrowingCustomerDate(null);
                    /** Save the Stock Object */
                    saveStockObjectToFile(bookStock, true);
                    break;
                case VIDEO:
                    ObjectStockVideo videoStock = getStockFromIDAsVideo(givenStockID);
                    videoStock.setBorrowingCustomerID(null);
                    videoStock.setBorrowingCustomerDate(null);
                    /** Save the Stock Object */
                    saveStockObjectToFile(videoStock, true);
                    break;
                case COMPACTDISC:
                    ObjectStockCompactDisc compactDiscStock = getStockFromIDAsCompactDisc(givenStockID);
                    compactDiscStock.setBorrowingCustomerID(null);
                    compactDiscStock.setBorrowingCustomerDate(null);
                    /** Save the Stock Object */
                    saveStockObjectToFile(compactDiscStock, true);
                    break;
                case JOURNAL:
                    ObjectStockJournal journalStock = getStockFromIDAsJournal(givenStockID);
                    journalStock.setBorrowingCustomerID(null);
                    journalStock.setBorrowingCustomerDate(null);
                    /** Save the Stock Object */
                    saveStockObjectToFile(journalStock, true);
                    break;
                default:

                    break;

            }

        }

    }

}
