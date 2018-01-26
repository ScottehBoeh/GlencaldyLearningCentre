package com.scottehboeh.glc.managers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.scottehboeh.glc.objects.ObjectStock;
import com.scottehboeh.glc.objects.ObjectUser;
import com.scottehboeh.glc.objects.ObjectUserStaff;
import com.scottehboeh.glc.referrals.ReferralDirectories;

import java.io.*;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by 1503257 on 29/11/2017.
 * <p>
 * User Manager
 * <p>
 * The User Manager Class is used to create, delete, and edit information for
 * users on the system. This class is also used to gain various information
 * about a user, aswell as retrieve their UUID, Username or Password with a
 * various use of methods from this class.
 */
public class UserManager {

    /**
     * Get User As File From ID - Get the User File from their ID
     *
     * @param givenUUID - Given User ID (UUID)
     * @return - The User File (File)
     */
    public static File getUserAsFileFromID(UUID givenUUID) {

        /** If the Direcotry does not exist, create it */
        if (!ReferralDirectories.userDirectory.exists()) {
            ReferralDirectories.userDirectory.mkdir();
        }

        /** Cast to new File Instance and return */
        return new File(ReferralDirectories.userDirectory + "/" + givenUUID + ".user");

    }

    /**
     * Does User Exist with ID - Does a User Exist with the given UUID
     *
     * @param givenUUID - Given User ID (UUID)
     * @return - Yes/No - Does/Doesn't Exist
     */
    public static boolean doesUserExistWithID(UUID givenUUID) {

        /** Returns exists() value of User File */
        return getUserAsFileFromID(givenUUID).exists();

    }

    /**
     * Does User Exist With Username - Does a User exist with the given Username
     *
     * @param givenUsername - Given Username (String)
     * @return - Yes/No - Does/Doesn't Exist
     */
    public static boolean doesUserExistWithUsername(String givenUsername) {

        /** Returns exists() value of User File */
        return getUserAsFileFromID(getUserIDFromUsername(givenUsername)).exists();

    }

    /**
     * Get User From ID - Get the Staff User from given UUID
     *
     * @param givenUUID - Given Staff User ID (UUID)
     * @return - Returns the Staff User Object of the User (ObjectUser)
     */
    public static ObjectUserStaff getStaffUserFromID(UUID givenUUID) throws IOException {

        /** Cast Staff User ID to File */
        File userFile = getUserAsFileFromID(givenUUID);

        /** If the Staff User File Exists, Cast it */
        if (userFile.exists()) {
            /** Create new buff reader instance */
            BufferedReader br = new BufferedReader(new FileReader(userFile));
            /** Cast Staff User Object from Gson Instance */
            ObjectUserStaff userObject = new Gson().fromJson(br, ObjectUserStaff.class);
            /** Close buff reader instance */
            br.close();
            return userObject;
        } else {
            return null;
        }

    }

    /**
     * Get User From ID - Get the User from given UUID
     *
     * @param givenUUID - Given User ID (UUID)
     * @return - Returns the User Object of the User (ObjectUser)
     */
    public static ObjectUser getUserFromID(UUID givenUUID) throws IOException {

        /** Cast User ID to File */
        File userFile = getUserAsFileFromID(givenUUID);

        /** If the User File Exists, Cast it */
        if (userFile.exists()) {
            /** Create new buff reader instance */
            BufferedReader br = new BufferedReader(new FileReader(userFile));
            /** Cast User Object from Gson Instance */
            ObjectUser userObject = new Gson().fromJson(br, ObjectUser.class);
            /** Close buff reader instance */
            br.close();
            return userObject;
        } else {
            return null;
        }

    }

    /**
     * Get User ID from Username - Get the UUID of a User from their Username
     *
     * @param givenUsername - Given Username (String)
     * @return - The User UUID (UUID)
     */
    public static UUID getUserIDFromUsername(String givenUsername) {

        File folder = new File("users/");

        if (!folder.exists()) {
            folder.mkdirs();
        }

        /** Cast list of files to new File List */
        File[] listOfFiles = folder.listFiles();

        /** For each file in Users Folder */
        for (File file : listOfFiles) {

            /** If the file is not a log */
            if (file.isFile() && !file.getName().contains("log")) {

                String fileName = file.getName().replace(".user", "");

                if (UserManager.doesUserExistWithID(UUID.fromString(fileName))) {

                    try {
                        ObjectUser theUser = UserManager.getUserFromID(UUID.fromString(fileName));

                        if (theUser.getUserName().equals(givenUsername)) {
                            return theUser.getUserID();
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

            }
        }

        return null;

    }

    /**
     * Save User Object to File - Save a User Object Instance to File
     *
     * @param givenUserObject - Given User Object (ObjectUser)
     */
    public static void saveUserObjectToFile(ObjectUser givenUserObject, boolean shouldOverwrite) throws IOException {

        /** Log Activity */
        LogManager.audit("Saved User " + givenUserObject.getUserID() + " to File", givenUserObject.getUserID(), "userlog");

        /** If the User File doesn't exist or should Overwrite File */
        if (!doesUserExistWithID(givenUserObject.getUserID()) || shouldOverwrite) {

            /** Get new Gson Builder Instance */
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            /** If the User File does not exist, create it */
            if (!getUserAsFileFromID(givenUserObject.getUserID()).exists()) {
                getUserAsFileFromID(givenUserObject.getUserID()).createNewFile();
            }

            /** Create new Writer Instance */
            Writer writer = new FileWriter(getUserAsFileFromID(givenUserObject.getUserID()));

            /** Check for different instances of User Object */
            if (givenUserObject instanceof ObjectUserStaff) {

                /** If Staff, Cast to new Staff User Object instance */
                ObjectUserStaff staffObject = (ObjectUserStaff) givenUserObject;
                writer.write(gson.toJson(staffObject));

            } else {

                /** Write User Object to Gson Json File */
                writer.write(gson.toJson(givenUserObject));

            }

            /** Close Writer Instance */
            writer.close();

        }

    }

    /**
     * Delete User Object File From ID - Delete a User File with Given ID
     *
     * @param givenUserID - Given User ID (UUID)
     */
    public static void deleteUserObjectFileFromID(UUID givenUserID) throws IOException {

        /** Log Activity */
        LogManager.audit("Deleted User with ID: " + givenUserID + " from File", givenUserID, "userlog");

        /** If the File exists, Delete it */
        if (getUserAsFileFromID(givenUserID).exists()) {
            getUserAsFileFromID(givenUserID).delete();
        }

    }

    /**
     * Get All Users - Get all Registered/Documented Users
     *
     * @return - Given Array List of Users (ArrayList<ObjectUser>)
     */
    public static ArrayList<ObjectUser> getAllUsers() {

        File folder = new File("users/");
        File[] listOfFiles = folder.listFiles();
        ArrayList<ObjectUser> userList = new ArrayList<ObjectUser>();

        for (File file : listOfFiles) {
            if (file.isFile() && !file.getName().contains("log")) {

                String fileName = file.getName().replace(".user", "");

                if (UserManager.doesUserExistWithID(UUID.fromString(fileName))) {

                    try {
                        ObjectUser theUser = UserManager.getUserFromID(UUID.fromString(fileName));

                        userList.add(theUser);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

            }
        }

        return userList;

    }

    /**
     * Has Reached Borrow Limit - Check if a User has reached the Stock-Borrow Limit
     *
     * @param customerID - Given Customer ID (UUID)
     * @return - True/False - Has/Hasn't reached Borrow Limit (boolean)
     */
    public static boolean hasReachedBorrowLimit(UUID customerID) {

        ArrayList<ObjectStock> customerStock = StockManager.getAllBorrowedStockFromCustomerID(customerID);

        if (customerStock == null || customerStock.size() < 4) {
            return false;
        }

        return true;

    }

}
