package com.scottehboeh.glc.forms;

import com.scottehboeh.glc.MainComponent;
import com.scottehboeh.glc.managers.SessionManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by 1503257 on 08/12/2017.
 */
public class Form_Login extends JFrame {
    private JLabel lblLogin_Title;
    private JLabel lblLogin_Message;
    private JLabel lblLogin_Username;
    private JLabel lblLogin_Password;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JPanel panel1;

    public Form_Login() {
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                /** Get the Username and Password from the user as a Prompt */
                String userName = txtUsername.getText();
                String passWord = txtPassword.getText();

                /** If the Username and Password exist and can login */
                if (userName != null && passWord != null && SessionManager.isValidSession(userName, passWord)) {

                    /** If already logged in, inform user */
                    if (MainComponent.getCurrentSession().isValid()) {
                        JOptionPane.showMessageDialog(panel1, "You are already logged in as " + MainComponent.getCurrentSession().getSessionUsername() + "!", "Login Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        /** Else, inform the user that login was successful */
                        MainComponent.getCurrentSession().login(userName, passWord);
                        JOptionPane.showMessageDialog(panel1, "Logged in as " + userName + "!", "Login Success", JOptionPane.INFORMATION_MESSAGE);
                        /** Refresh the GUI */
                        MainComponent.mainForm.refreshGUI();
                        dispose();
                    }

                } else {
                    /** Else, inform the user that their given Inputs were invalid */
                    JOptionPane.showMessageDialog(panel1, "Invalid Username or Password! Try again!", "Login Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
    }

    public JPanel getPanel() {
        return this.panel1;
    }

}
