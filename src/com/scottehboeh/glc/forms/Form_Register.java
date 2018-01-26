package com.scottehboeh.glc.forms;

import com.scottehboeh.glc.enums.EnumUserType;
import com.scottehboeh.glc.managers.UserManager;
import com.scottehboeh.glc.objects.ObjectUser;
import com.scottehboeh.glc.objects.ObjectUserStaff;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;

/**
 * Created by 1503257 on 08/12/2017.
 */
public class Form_Register extends JFrame {
    private JTextField txtUsername;
    private JTextField txtPassword;
    private JPanel panel1;
    private JLabel lblRegister_Title;
    private JLabel lblRegister_Message;
    private JTextField txtFirstName;
    private JTextField txtLastName;
    private JLabel lblFirstName;
    private JLabel lblLastName;
    private JLabel lblUsername;
    private JTextField txtEmail;
    private JLabel lblEmail;
    private JTextField txtAddressLine1;
    private JLabel lblAddress;
    private JTextField txtAddressLine2;
    private JTextField txtAddressLine3;
    private JLabel lblPassword;
    private JCheckBox checkIsStaff;
    private JLabel lblStaffEmail;
    private JTextField txtStaffEmail;
    private JLabel lblStaffPhone;
    private JTextField txtStaffPhone;
    private JButton btnRegister;
    private JCheckBox checkIsFullMember;
    private String errorMessage;
    private Boolean isStaff;
    private Boolean isFullMember;

    public Form_Register() {

        isStaff = false;
        isFullMember = false;
        lblStaffEmail.setVisible(false);
        lblStaffPhone.setVisible(false);
        txtStaffEmail.setVisible(false);
        txtStaffPhone.setVisible(false);

        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                attemptRegistration();


            }
        });

        checkIsStaff.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                System.out.println(e.getStateChange() == ItemEvent.SELECTED
                        ? "SELECTED" : "DESELECTED");

                if (e.getStateChange() == ItemEvent.SELECTED) {
                    isStaff = true;
                    isFullMember = false;
                    lblStaffEmail.setVisible(true);
                    lblStaffPhone.setVisible(true);
                    txtStaffEmail.setVisible(true);
                    txtStaffPhone.setVisible(true);
                    checkIsFullMember.setVisible(false);
                    checkIsFullMember.setSelected(false);
                } else {
                    isStaff = false;
                    lblStaffEmail.setVisible(false);
                    lblStaffPhone.setVisible(false);
                    txtStaffEmail.setVisible(false);
                    txtStaffPhone.setVisible(false);
                    checkIsFullMember.setVisible(true);
                    checkIsFullMember.setSelected(false);
                }
            }
        });

        checkIsFullMember.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

                if (e.getStateChange() == ItemEvent.SELECTED) {
                    isStaff = false;
                    isFullMember = true;
                    checkIsStaff.setSelected(false);
                    checkIsStaff.setVisible(false);
                } else {
                    isFullMember = false;
                    checkIsStaff.setSelected(false);
                    checkIsStaff.setVisible(true);
                }

            }

        });

    }

    public JPanel getPanel() {
        return this.panel1;
    }

    public void attemptRegistration() {

        errorMessage = null;

        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        String userName = txtUsername.getText();
        String email = txtEmail.getText();
        String address = txtAddressLine1.getText();
        String passWord = txtPassword.getText();

        String staffEmail = "";
        String staffTelNo = "";

        if (txtAddressLine2.getText().length() > 0) {
            address += "\n" + txtAddressLine2.getText();
        }
        if (txtAddressLine3.getText().length() > 0) {
            address += "\n" + txtAddressLine3.getText();
        }

        if (firstName == null || firstName.length() < 1) {
            errorMessage = "Please put a valid First Name!";
        }
        if (lastName == null || lastName.length() < 1) {
            errorMessage = "Please put a valid Last Name!";
        }
        if (userName == null || userName.length() < 1) {
            errorMessage = "Please put a valid Username!";
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
        if (passWord == null || passWord.length() < 1) {
            errorMessage = "Please put a valid Password!";
        }

        if (isStaff) {
            staffEmail = txtStaffEmail.getText();
            staffTelNo = txtStaffPhone.getText();

            if (staffEmail == null || staffEmail.length() < 1) {
                errorMessage = "Please put a valid Staff Email!";
            }
            if (staffTelNo == null || staffTelNo.length() < 1) {
                errorMessage = "Please put a valid Staff Telephone Number!";
            }

        }

        if (errorMessage != null) {
            JOptionPane.showMessageDialog(panel1, errorMessage, "Registration Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        /** If Staff Member, set User Type as STAFF */
        if (isStaff) {

            ObjectUserStaff newStaffUser = new ObjectUserStaff(
                    userName,
                    firstName,
                    lastName,
                    email,
                    address,
                    passWord,
                    staffEmail,
                    staffTelNo
            );
            try {
                UserManager.saveUserObjectToFile(newStaffUser, true);
                JOptionPane.showMessageDialog(panel1, "You have Successfully registered the Staff User " + userName, "User Registered", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        } else {

            ObjectUser newUser = new ObjectUser(
                    userName,
                    firstName,
                    lastName,
                    email,
                    address,
                    passWord,
                    EnumUserType.NORMAL
            );

            /** If Full Member, set User Type as FULL */
            if (isFullMember) {
                newUser.setUserType(EnumUserType.FULL);
            }

            try {
                UserManager.saveUserObjectToFile(newUser, true);
                JOptionPane.showMessageDialog(panel1, "You have Successfully registered the User " + userName, "User Registered", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }

    }

}
