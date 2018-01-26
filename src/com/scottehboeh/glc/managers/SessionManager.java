package com.scottehboeh.glc.managers;

import com.scottehboeh.glc.objects.ObjectUser;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by 1503257 on 06/12/2017.
 * <p>
 * Session Manager
 * <p>
 * The Session Manager is a useful class that is used to allow users
 * to log in/out of the program. This handy class also lets the program
 * check if the current logged in user is logged into a valid account
 */
public class SessionManager {

    public static boolean isValidSession(String givenUsername, String givenPassword) {

        if (givenUsername != null && givenPassword != null) {
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
                            String givenPasswordHash = givenPassword.hashCode() + "";

                            if (realUsername.equals(givenUsername) && realPasswordHash.equals(givenPasswordHash)) {
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

}
