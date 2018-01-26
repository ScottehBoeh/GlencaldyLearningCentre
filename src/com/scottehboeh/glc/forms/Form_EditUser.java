package com.scottehboeh.glc.forms;

import com.scottehboeh.glc.enums.EnumUserType;
import com.scottehboeh.glc.managers.UserManager;
import com.scottehboeh.glc.objects.ObjectUser;
import com.scottehboeh.glc.objects.ObjectUserStaff;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by 1503257 on 13/12/2017.
 */
public class Form_EditUser extends JFrame {
    private JPanel panel1;
    private JTextField txtUsername;
    private JLabel lblUsername;
    private JLabel lblPassword;
    private JPasswordField txtPassword;
    private JLabel lblEmail;
    private JTextField txtEmail;
    private JLabel lblAddress;
    private JTextField txtAddress;
    private JButton btnSubmit;
    private JButton btnCancel;
    private JTextField txtFirstName;
    private JLabel lblFirstName;
    private JLabel lblSecondName;
    private JTextField txtSecondName;
    private UUID userUUID;
    private String errorMessage;

    public Form_EditUser(UUID givenUserUUID) {

        this.userUUID = givenUserUUID;

        try {
            ObjectUser theUser = UserManager.getUserFromID(userUUID);

            txtUsername.setText(theUser.getUserName());
            txtPassword.setText("");
            txtEmail.setText(theUser.getEmailAddress());
            txtAddress.setText(theUser.getHomeAddress());
            txtFirstName.setText(theUser.getFirstName());
            txtSecondName.setText(theUser.getSecondName());

        } catch (IOException e) {
            e.printStackTrace();
        }

        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure that you wish to save this users changes?", "Warning", dialogButton);
                if (dialogResult == JOptionPane.YES_OPTION) {

                    try {
                        attemptEdit();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }

                }

            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public JPanel getPanel() {
        return this.panel1;
    }

    public void attemptEdit() throws IOException {

        errorMessage = null;

        String firstName = txtFirstName.getText();
        String lastName = txtSecondName.getText();
        String userName = txtUsername.getText();
        String email = txtEmail.getText();
        String address = txtAddress.getText();
        String passWord = txtPassword.getText();

        String staffEmail = "";
        String staffTelNo = "";

        if (firstName == null || firstName.length() < 1) {
            errorMessage = "Please put a valid First Name!";
        }
        if (lastName == null || lastName.length() < 1) {
            errorMessage = "Please put a valid Last Name!";
        }
        if (userName == null || userName.length() < 1) {
            errorMessage = "Please put a valid Username! (Username may already exist)";
        }
        if (UserManager.doesUserExistWithUsername(userName) && UserManager.getUserFromID(UserManager.getUserIDFromUsername(userName)).getUserID() != this.userUUID) {
            errorMessage = "A user with that Username already exists!";
        }
        if (UserManager.doesUserExistWithUsername(userName)) {
            errorMessage = "A user already exists with the Username " + userName + "!";
        }
        if (email == null || email.length() < 1) {
            errorMessage = "Please put a valid Email!";
        }
        if (address == null) {
            errorMessage = "Please put a valid Address! (At least 1 Line)";
        }

        if (errorMessage != null) {
            JOptionPane.showMessageDialog(panel1, errorMessage, "Edit Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        ObjectUser theUser = UserManager.getUserFromID(this.userUUID);

        if (theUser.getUserType() == EnumUserType.STAFF) {

            ObjectUserStaff staffUser = (ObjectUserStaff) UserManager.getStaffUserFromID(this.userUUID);

            staffUser.setUsername(txtUsername.getText());
            staffUser.setFirstName(txtFirstName.getText());
            staffUser.setSecondName(txtSecondName.getText());
            staffUser.setEmailAddress(txtEmail.getText());
            staffUser.setHomeAddress(txtAddress.getText());

            if (passWord != null || passWord.length() > 0) {
                staffUser.setPassword(txtPassword.getText());
            }

            UserManager.saveUserObjectToFile(staffUser, true);
            JOptionPane.showMessageDialog(panel1, "Successfully Saved Staff User", "Edit Error", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();

        } else {

            ObjectUser staffUser = UserManager.getUserFromID(this.userUUID);

            staffUser.setUsername(txtUsername.getText());
            staffUser.setFirstName(txtFirstName.getText());
            staffUser.setSecondName(txtSecondName.getText());
            staffUser.setEmailAddress(txtEmail.getText());
            staffUser.setHomeAddress(txtAddress.getText());

            if (passWord != null || passWord.length() > 0) {
                staffUser.setPassword(txtPassword.getText());
            }

            UserManager.saveUserObjectToFile(staffUser, true);
            JOptionPane.showMessageDialog(panel1, "Successfully Saved User", "Edit Error", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();

        }

    }

}
