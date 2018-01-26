package com.scottehboeh.glc.objects;

import com.scottehboeh.glc.enums.EnumUserType;

import java.util.UUID;

/**
 * Created by 1503257 on 29/11/2017.
 */
public class ObjectUserStaff extends ObjectUser {

    /**
     * Object Variables
     */
    private UUID staffID; //Staff ID
    private String staffEmail; //Staff Email Address
    private String telNo; //Staff Telephone Extention

    /**
     * Default Constructor with Attributes with Staff User Object Extras
     */
    public ObjectUserStaff(String givenUsername, String givenFirstName, String givenSecondName, String givenEmailAddress, String givenHomeAddress, String givenPassword, String givenStaffEmail, String givenStaffPhoneNo) {
        super(givenUsername, givenFirstName, givenSecondName, givenEmailAddress, givenHomeAddress, givenPassword, EnumUserType.STAFF);
        this.setStaffEmaiL(givenStaffEmail);
        this.setStaffPhoneNumber(givenStaffPhoneNo);
        this.staffID = UUID.randomUUID();
        this.setUserType(EnumUserType.STAFF);
    }

    /**
     * Get Staff ID - Get the Staff ID of the Staff User
     *
     * @return - Given Staff ID (String)
     */
    public UUID getStaffID() {
        return this.staffID;
    }

    /**
     * Get Staff Email - Get the Staff Users Staff Email
     *
     * @return - Given Staff Email (String)
     */
    public String getStaffEmail() {
        return this.staffEmail;
    }

    /**
     * Get Staff Phone Number - Get the Phone Number of the Staff Member
     *
     * @return - Given Staff Member Phone Number
     */
    public String getStaffPhoneNumber() {
        return this.telNo;
    }

    /**
     * Set Staff Phone Number - Set the Staff Members Telephone Extension
     *
     * @param givenTelNo - Given Telephone Number (String)
     */
    public void setStaffPhoneNumber(String givenTelNo) {
        this.telNo = givenTelNo;
    }

    /**
     * Set Staff Email - Set the Email Address of the Staff User
     *
     * @param givenEmail - Given EmaiL Address (String)
     */
    public void setStaffEmaiL(String givenEmail) {
        this.staffEmail = givenEmail;
    }

}
