package com.scottehboeh.glc.forms;

import com.scottehboeh.glc.managers.UserManager;
import com.scottehboeh.glc.objects.ObjectUser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by 1503257 on 08/12/2017.
 */
public class Form_ManageUsers extends JFrame {

    private JLabel lblTitle;
    private JComboBox comboBox;
    private JPanel panel1;
    private JLabel lblCurrent_Username;
    private JLabel lblCurrent_Email;
    private JLabel lblCurrent_Address;
    private JLabel lblCurrent_Password;
    private JLabel lblCurrent_UUID;
    private JLabel lblCurrentText_Username;
    private JLabel lblCurrentText_Email;
    private JLabel lblCurrentText_Address;
    private JLabel lblCurrentText_Password;
    private JLabel lblCurrentText_UUID;
    private JButton btnDeleteUser;
    private JButton btnEditUser;
    private UUID currentUserUUID;
    private String currentUserName;
    private int currentIndex = 0;

    public Form_ManageUsers() {

        refreshUserList();

        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comboBox.getSelectedItem() != null) {
                    String[] split = ((String) comboBox.getSelectedItem()).split("/");
                    currentUserUUID = UUID.fromString(split[0]
                            .replace("[Staff User]", "")
                            .replace("[Full User]", "")
                            .replace("[Casual User]", "")
                            .replace(" ", ""));
                    currentUserName = split[1];
                    currentIndex = comboBox.getSelectedIndex();
                }
                refreshUserList();
            }
        });

        btnDeleteUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println(currentUserName);
                if (currentUserName.replace(" ", "").equals("root")) {
                    JOptionPane.showMessageDialog(null, "Cannot remove root user!", "Invalid User Deletion", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure that you wish to delete this user?", "Warning", dialogButton);
                if (dialogResult == JOptionPane.YES_OPTION) {

                    try {
                        UserManager.deleteUserObjectFileFromID(currentUserUUID);

                        try {
                            Thread.sleep(20);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }

                        refreshUserList();
                    } catch (IOException e1) {
                        System.out.println("Unable to delete user " + currentUserUUID);
                        e1.printStackTrace();
                    }

                }

            }
        });

        btnEditUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    if (currentUserUUID != null && UserManager.getUserFromID(currentUserUUID) != null) {

                        /** Create new Login Form instance */
                        Form_EditUser editFrame = new Form_EditUser(currentUserUUID);
                        editFrame.setTitle("Edit User " + currentUserUUID);

                        /** Initialize Login Form JFrame Instance */
                        editFrame.setSize(new Dimension(300, 430));
                        editFrame.setContentPane(editFrame.getPanel());
                        editFrame.setVisible(true);
                        editFrame.setResizable(false);

                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    public JPanel getPanel() {
        return this.panel1;
    }

    public void refreshUserList() {

        /**
         * Delete all Current User Objects from Combo Box
         */
        comboBox.removeAllItems();

        /** For each User, add them to the Combo Box */
        for (ObjectUser user : UserManager.getAllUsers()) {
            comboBox.addItem("[" + user.getUserType().getEnglish() + "] " + user.getUserID() + " / " + user.getUserName() + " / " + user.getEmailAddress());
        }

        if (currentIndex > comboBox.getItemCount()) {
            currentIndex = comboBox.getItemCount();
        }

        comboBox.setSelectedIndex(currentIndex);

        if (currentUserUUID != null && UserManager.doesUserExistWithID(currentUserUUID)) {
            try {
                ObjectUser selectedUser = UserManager.getUserFromID(this.currentUserUUID);

                lblCurrentText_Username.setText(selectedUser.getUserName());
                lblCurrentText_Email.setText(selectedUser.getEmailAddress());
                lblCurrentText_Address.setText(selectedUser.getHomeAddress());
                lblCurrentText_Password.setText(selectedUser.getPasswordHashCode() + "(Hash)");
                lblCurrentText_UUID.setText(selectedUser.getUserID().toString());

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            currentUserUUID = null;
        }
    }

}
