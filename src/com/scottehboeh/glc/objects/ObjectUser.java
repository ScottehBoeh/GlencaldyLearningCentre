package com.scottehboeh.glc.objects;

import com.scottehboeh.glc.enums.EnumUserStatus;
import com.scottehboeh.glc.enums.EnumUserType;

import java.util.UUID;

/**
 * Created by 1503257 on 29/11/2017.
 */
public class ObjectUser {

    /**
     * Object Variables
     */
    private UUID userID; //User ID (Unique Identifier)
    private String userName; //User Name
    private String firstName; //First Name
    private String secondName; //Second Name
    private String emailAddress; //User Email Address
    private String homeAddress; //User Home Address
    private int password; //User Password
    private EnumUserStatus userStatus; //User Status
    private EnumUserType userType = EnumUserType.STAFF; //User Type
    private double dueLoanFine; //User Loan Fine

    /**
     * Default Constructor for User Object
     */
    public ObjectUser() {
        this.userID = UUID.randomUUID();
    }

    /**
     * Default Constructor with Attributes
     */
    public ObjectUser(String givenUsername, String givenFirstName, String givenSecondName, String givenEmailAddress, String givenHomeAddress, String givenPassword, EnumUserType givenUserType) {
        this.userID = UUID.randomUUID();
        this.setUsername(givenUsername);
        this.setFirstName(givenFirstName);
        this.setSecondName(givenSecondName);
        this.setEmailAddress(givenEmailAddress);
        this.setHomeAddress(givenHomeAddress);
        this.setPassword(givenPassword);
        this.dueLoanFine = 0;
        this.userType = givenUserType;
    }

    /**
     * Get Due Loan Fine - Get the Due amount of Money Fined
     *
     * @return - Given Fine Amount (double)
     */
    public double getDueLoanFine() {
        return dueLoanFine;
    }

    /**
     * Set Due Loan Fine - Set the Due amount of Money Fined
     *
     * @param dueLoanFine - Given Money to set Due Loan Fine as (double)
     */
    public void setDueLoanFine(double dueLoanFine) {
        this.dueLoanFine = dueLoanFine;
    }

    /**
     * Add To Fine - Add a specific amount of Money to Fine
     *
     * @param givenLoanFineCost - Given Loan Fine Cost (double)
     */
    public void addToFine(double givenLoanFineCost) {
        this.dueLoanFine += givenLoanFineCost;
    }

    /**
     * Get User Type - Get the Type of User
     *
     * @return - Given User Type (EnumUserType)
     */
    public EnumUserType getUserType() {
        return this.userType;
    }

    /**
     * Set User Type - Set the Type of User
     *
     * @param userType - Given User Type (EnumUserType)
     */
    public void setUserType(EnumUserType userType) {
        this.userType = userType;
    }

    /**
     * Get First Name - Get the First Name of the User
     *
     * @return - Given First Name (String)
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set First Name - Set the First Name of the User
     *
     * @param firstName - Given First Name (String)
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get Second Name - Get the Second Name of the User
     *
     * @return - Given Second Name (String)
     */
    public String getSecondName() {
        return secondName;
    }

    /**
     * Set Second Name - Set the Second Name of the User
     *
     * @param secondName - Given Second Name (String)
     */
    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    /**
     * Get Email Address - Get the Email Address of the User
     *
     * @return - Given Email Address (String)
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Set Email Address - Set the Email Address of the User
     *
     * @param emailAddress - Given Email Address (String)
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * Get Home Address - Get the Home Address of the User
     *
     * @return - Given Home Address (String)
     */
    public String getHomeAddress() {
        return homeAddress;
    }

    /**
     * Set Home Address - Set the Home Address of the User
     *
     * @param homeAddress - Given Home Address (String)
     */
    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    /**
     * Get Password - Get the User Password
     *
     * @return - Given Password (int)
     */
    public int getPasswordHashCode() {
        return password;
    }

    /**
     * Set Password - Set the Password of the User
     *
     * @param password - Given Password (String)
     */
    public void setPassword(String password) {
        /** Convert Password into Hash Code */
        this.password = password.hashCode();
    }

    /**
     * Get User Status - Get the Status of the User as an Enum
     *
     * @return - Given Enum (EnumUserStatus)
     */
    public EnumUserStatus getUserStatus() {
        return this.userStatus;
    }

    /**
     * Get User Status - Set the Status of the User
     *
     * @param givenStatus - Given User Status (EnumUserStatus)
     */
    public void setUserStatus(EnumUserStatus givenStatus) {
        this.userStatus = givenStatus;
    }

    /**
     * Get Username - Get the Username of the User
     *
     * @return - Given Username (String)
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * Get User ID - Get the User ID of the User
     *
     * @return - Given User ID (UUID)
     */
    public UUID getUserID() {
        return this.userID;
    }

    /**
     * Set User ID - Set the ID of the User (from String)
     *
     * @param givenUUID - Given User ID (String)
     */
    public void setUserID(String givenUUID) {
        this.userID = UUID.fromString(givenUUID);
    }

    /**
     * Set User ID - Set the ID of the User
     *
     * @param givenUUID - Given User ID (UUID)
     */
    public void setUserID(UUID givenUUID) {
        this.userID = givenUUID;
    }

    /**
     * Set Username - Set the Username of the User
     *
     * @param givenUsername - Given Username (String)
     */
    public void setUsername(String givenUsername) {
        this.userName = givenUsername;
    }
}
