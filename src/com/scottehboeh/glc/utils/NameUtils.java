package com.scottehboeh.glc.utils;

import java.util.Random;

/**
 * Created by 1503257 on 20/12/2017.
 * <p>
 * Used to create random names for Stock Items
 */
public class NameUtils {

    private static String[] Beginning = {"Kr", "Ca", "Ra", "Mrok", "Cru",
            "Ray", "Bre", "Zed", "Drak", "Mor", "Jag", "Mer", "Jar", "Mjol",
            "Zork", "Mad", "Cry", "Zur", "Creo", "Azak", "Azur", "Rei", "Cro",
            "Mar", "Luk"};
    private static String[] Middle = {"air", "ir", "mi", "sor", "mee", "clo",
            "red", "cra", "ark", "arc", "miri", "lori", "cres", "mur", "zer",
            "marac", "zoir", "slamar", "salmar", "urak"};
    private static String[] End = {"d", "ed", "ark", "arc", "es", "er", "der",
            "tron", "med", "ure", "zur", "cred", "mur"};

    /**
     * Static Random Instance
     */
    private static Random rand = new Random();

    /**
     * Get Random Name - Get a Randomly Generated
     *
     * @return - Returns a randomly generated Name (String)
     */
    public static String getRandomName() {

        return Beginning[rand.nextInt(Beginning.length)] +
                Middle[rand.nextInt(Middle.length)] +
                End[rand.nextInt(End.length)];

    }

}
