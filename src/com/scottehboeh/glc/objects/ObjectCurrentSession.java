package com.scottehboeh.glc.objects;

import com.scottehboeh.glc.managers.LogManager;
import com.scottehboeh.glc.managers.SessionManager;
import com.scottehboeh.glc.managers.UserManager;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by 1503257 on 01/12/2017.
 * <p>
 * Object Current Session
 * <p>
 * This object holds the information of the currently logged in User. This allows
 * more flexibility for customers using the program, as it temporarily caches the
 * customers username and password for unlimited use until they log out. This means
 * that they can use various parts of the program without having to input a user-
 * name and password every time.
 */
public class ObjectCurrentSession {

    private String sessionUsername;
    private String sessionPassword;
    private UUID sessionUserID;

    /**
     * Get Session User - Get the User of the current Session
     *
     * @return - Given User Object (ObjectUser)
     */
    public ObjectUser getSessionUser() {
        try {
            return UserManager.getUserFromID(UserManager.getUserIDFromUsername(sessionUsername));
        } catch (IOException e) {
            System.out.println("Unable to retrieve user " + sessionUsername);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get Session Username - Get the Username of the Current Session
     *
     * @return - Given Username (String)
     */
    public String getSessionUsername() {
        return sessionUsername;
    }

    /**
     * Set Session Username - Set the Username of the Current Session
     *
     * @param sessionUsername - Given Session Username (String)
     */
    public void setSessionUsername(String sessionUsername) {
        this.sessionUsername = sessionUsername;
    }

    /**
     * Get Session Password - Get the Password of the Current Session
     *
     * @return - The Password of the Current Session (String)
     */
    public String getSessionPassword() {
        return sessionPassword;
    }

    /**
     * Set Session Password - Set the Password of the Current Session
     *
     * @param sessionPassword - Given Password (String)
     */
    public void setSessionPassword(String sessionPassword) {
        this.sessionPassword = sessionPassword;
    }

    /**
     * Is Valid - Is the Current Session Valid
     *
     * @return - True/False - Session is Valid/Invalid
     */
    public boolean isValid() {

        if (sessionUsername != null && sessionPassword != null) {
            File folder = new File("users/");
            File[] listOfFiles = folder.listFiles();

            for (File file : listOfFiles) {
                if (file.isFile() && !file.getName().contains("log")) {

                    String fileName = file.getName().replace(".user", "");

                    if (UserManager.doesUserExistWithID(UUID.fromString(fileName))) {

                        try {
                            ObjectUser theUser = UserManager.getUserFromID(UUID.fromString(fileName));

                            String realUsername = theUser.getUserName();
                            String realPasswordHash = theUser.getPasswordHashCode() + "";
                            String givenPasswordHash = sessionPassword.hashCode() + "";

                            if (realUsername.equals(sessionUsername) && realPasswordHash.equals(givenPasswordHash)) {
                                return true;
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }

                }
            }
        }

        return false;

    }

    /**
     * Login - Attempt to Login to an Account using given Username and Password
     *
     * @param givenUsername - Given Username (String)
     * @param givenPassword - Given Password (String)
     */
    public void login(String givenUsername, String givenPassword) {

        /** Log Activity */
        try {
            LogManager.audit("Attempted Log-in to user with password " + givenPassword, UserManager.getUserIDFromUsername(givenUsername), "userlog");
        } catch (IOException e) {
            System.out.println("Failed to save Audit Log!");
            e.printStackTrace();
        }

        /** If the Session is Valid, attempt to Login */
        if (SessionManager.isValidSession(givenUsername, givenPassword)) {
            this.setSessionUsername(givenUsername);
            this.setSessionPassword(givenPassword);
        }

    }

    /**
     * Logout - Attempt to Logout of the Current Session
     */
    public void logOut() {

        /** Log Activity */
        try {
            LogManager.audit("Attempted Log-out of user " + this.getSessionUsername(), UserManager.getUserIDFromUsername(this.getSessionUsername()), "userlog");
        } catch (IOException e) {
            System.out.println("Failed to save Audit Log!");
            e.printStackTrace();
        }

        this.setSessionPassword(null);
        this.setSessionUsername(null);
    }

    /**
     * Get Status - Get the Status of the Current Session
     *
     * @return - The Session Status (String)
     */
    public String getStatus() {

        if (isValid()) {
            return "Logged in as " + getSessionUsername();
        } else {
            return "Not Logged In";
        }

    }

}
