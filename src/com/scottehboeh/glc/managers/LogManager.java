package com.scottehboeh.glc.managers;

import com.scottehboeh.glc.referrals.ReferralDirectories;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

/**
 * Created by 1503257 on 29/11/2017.
 * <p>
 * Log Manager
 * <p>
 * This class is used to log various different means of activity, such as
 * logging in, logging out, editing personal details, etc.
 */
public class LogManager {

    /**
     * Audit - Create new Log Entry to Audit Log
     *
     * @param givenLog - Given Log
     * @throws IOException - Handled Exception (IOException)
     */
    public static void audit(String givenLog, UUID givenUserID, String logExtension) throws IOException {

        /** Get new Date Instance */
        Date date = new Date();
        /** Get Local Date Instance from Date Instance */
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        /** Get Year, Month and Day for Audit Log File Name */
        int year = localDate.getYear();
        int month = localDate.getMonthValue();
        int day = localDate.getDayOfMonth();

        /** Get Time and Date Formatted */
        String formattedTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        String formattedDate = day + "-" + month + "-" + year;

        /** If Log Directory doesn't exis, create it */
        if (!ReferralDirectories.logsDirectory.exists()) {
            ReferralDirectories.logsDirectory.mkdir();
        }

        /** Cast Audit File to new File Instance */
        File auditFile = new File(ReferralDirectories.logsDirectory + "/" + givenUserID.toString() + "-LOG-" + formattedDate + "." + logExtension);

        /** If Audit File doesn't exist, create it */
        if (!auditFile.exists()) {
            auditFile.createNewFile();
        }

        /** Get Network and Hardware Instances/Information */
        InetAddress ip = InetAddress.getLocalHost();
        NetworkInterface network = NetworkInterface.getByInetAddress(ip);
        byte[] hwAddress = network.getHardwareAddress();

        /** Concat new Log Entry as String with given Address/Details/Description */
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < hwAddress.length; i++) {
            sb.append(String.format("%02X%s", hwAddress[i], (i < hwAddress.length - 1) ? "-" : ""));
        }

        /** Open File Writer Instance */
        FileWriter fw = new FileWriter(auditFile.getAbsolutePath(), true);
        /** Write Audit Entry to Audit Log */
        fw.write("PC ID: " + sb + " | " + formattedDate + " " + formattedTime + " | " + givenLog + "\n");
        /** Close File Writer Instance */
        fw.close();
    }

}
