package com.scottehboeh.glc.objects;

import com.scottehboeh.glc.enums.EnumMediaType;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by 1503257 on 13/12/2017.
 */
public class ObjectStock {

    private UUID stockID = UUID.randomUUID();
    private UUID borrowingCustomerID = null;
    private EnumMediaType mediaType = EnumMediaType.UNKNOWN;
    private double stockCost;

    private Date borrowingCustomerDate;

    public ObjectStock() {
    }

    /**
     * Get Stock ID - Get the ID of the Stock Item
     *
     * @return - The Stock Item ID (UUID)
     */
    public UUID getStockID() {
        return stockID;
    }

    /**
     * Set Stock ID - Set the ID of the Stock Item
     *
     * @param stockID - Given Stock ID (UUID)
     */
    public void setStockID(UUID stockID) {
        this.stockID = stockID;
    }

    /**
     * Get Stock Cost - Get the Cost of the Stock
     *
     * @return - Given Stock Cost (double)
     */
    public double getStockCost() {
        return stockCost;
    }

    /**
     * Set Stock Cost - Set the Cost of the Stock
     *
     * @param stockCost - Given Cost (double)
     */
    public void setStockCost(double stockCost) {
        this.stockCost = stockCost;
    }

    /**
     * Get Media Type - Get the Type of Media being Stored
     *
     * @return - Given Media Type (EnumMediaType)
     */
    public EnumMediaType getMediaType() {
        return mediaType;
    }

    /**
     * Set Media Type - Set the Type of Media beng Stored
     *
     * @param mediaType - Given Media Type (EnumMediaType)
     */
    public void setMediaType(EnumMediaType mediaType) {
        this.mediaType = mediaType;
    }

    /**
     * Get Borrowing Customer ID - Get the ID of the Customer borrowing the Item
     *
     * @return - Given Customer ID (UUID)
     */
    public UUID getBorrowingCustomerID() {
        return borrowingCustomerID;
    }

    /**
     * Set Borring Customer ID - Set the ID of the Borring Customer
     *
     * @param borrowingCustomerID - Given Customer ID (UUID)
     */
    public void setBorrowingCustomerID(UUID borrowingCustomerID) {
        this.borrowingCustomerID = borrowingCustomerID;
    }

    /**
     * Get Understandable Name - Get the proper Name of the Stock (such as Video Title, Book Name, etc.)
     *
     * @return - Given Stock Understandable Name (String)
     */
    public String getUnderstandableName() {
        return "Unknown Name";
    }

    /**
     * Get Borrowing Customer Date - Get the Date in which the Stock was last borrowed
     *
     * @return - Given Borrow Date (Date)
     */
    public Date getBorrowingCustomerDate() {
        return borrowingCustomerDate;
    }

    /**
     * Set Borrowing Customer Date - Set the Date in which the Stock was last borrowed
     *
     * @param borrowingCustomerDate - Given Date (Date)
     */
    public void setBorrowingCustomerDate(Date borrowingCustomerDate) {
        this.borrowingCustomerDate = borrowingCustomerDate;
    }

    /**
     * Get Borrowing Customer Date Formatted - Get the Date in which the Stock was last
     * borrowed formatted
     *
     * @return - Given Borrow Date Formatted (String)
     */
    public String getBorrowingCustomerDateFormatted() {

        if (this.getBorrowingCustomerDate() != null) {
            return new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(this.getBorrowingCustomerDate());
        } else {
            return "Unknown";
        }

    }

}
