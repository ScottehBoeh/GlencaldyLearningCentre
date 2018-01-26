package com.scottehboeh.glc.forms;

import com.scottehboeh.glc.MainComponent;
import com.scottehboeh.glc.enums.EnumUserType;
import com.scottehboeh.glc.managers.LogManager;
import com.scottehboeh.glc.managers.StockManager;
import com.scottehboeh.glc.managers.UserManager;
import com.scottehboeh.glc.objects.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * Created by 1503257 on 29/11/2017.
 */
public class Form_Main extends JFrame {

    private JPanel panel1;
    private JLabel lblProgramTitle;
    private JLabel lblSessionStatus;
    private JToolBar.Separator toolbarSplitter;
    private JToolBar toolbarMain;
    private JTabbedPane tabMain;
    private JLabel lblTab_MainMenu_Message;
    private JLabel lblTab_Profile_Title;
    private JLabel lblTab_MainMenu_Title;
    private JLabel lblTab_Profile_Message;
    private JButton btnSession;
    private JToolBar.Separator toolbarSplitter2;
    private JLabel lblProfileUsername;
    private JLabel lblProfileEmail;
    private JLabel lblTab_Catalogue_Title;
    private JTabbedPane tabCatalogue;
    private JLabel lblTab_Catalogue_Message;
    private JPanel panelImage;
    private JPanel panelStaffUtilities;
    private JLabel lblStaffUtilities_Title;
    private JLabel lblStaffOption_CreateUser;
    private JButton btnStaffOption_CreateUser;
    private JLabel lblStaffOption_ManageUsers;
    private JButton btnStaffOption_ManageUsers;
    private JLabel lblVideo_ID;
    private JLabel lblVideo_Format;
    private JLabel lblVideo_StorageType;
    private JLabel lblVideo_Publisher;
    private JLabel lblVideo_Genre;
    private JButton btnVideo_Previous;
    private JButton btnVideo_Next;
    private JPanel tabcatalogueVideo;
    private JPanel tabcatalogueCompactDisk;
    private JPanel tabcatalogueJournals;
    private JPanel tabcatalogueBooks;
    private JSeparator sepTabProfile1;
    private int catalogueIndex = 0;
    private UUID catalogueIndexUUID = null;
    private JButton btnBorrowStock;
    private JLabel lblStockStatus;
    private JButton btnProfile_MyBorrowedStock;
    private JLabel lblTab_Profile_Rank;
    private JLabel lblDisc_Name;
    private JLabel lblDisc_Artist;
    private JLabel lblDisc_Publisher;
    private JLabel lblDisc_Tracks;
    private JLabel lblDisc_CaseType;
    private JLabel lblDisc_ID;
    private JLabel lblVideo_Title;
    private JLabel lblIndexHelp;
    private JLabel lblJournal_ID;
    private JLabel lblJournal_Name;
    private JLabel lblJournal_Issue;
    private JLabel lblJournal_Date;
    private JLabel lblJournal_ISSN;
    private JLabel lblJournal_SubArea;
    private JLabel lblJournal_Publisher;
    private JLabel lblJournal_NumberOfPages;
    private JLabel lblCost;
    private JLabel lblBook_ID;
    private JLabel lblBook_Title;
    private JLabel lblBook_Author;
    private JLabel lblBook_SubjectArea;
    private JLabel lblBook_Publisher;
    private JLabel lblBook_PageCount;
    private JLabel lblSearch;
    private JTextField txtSearch;
    private JButton btnRefresh;
    private JButton btnProfile_EditMyUser;
    private JLabel lblCreateNewStock;
    private JButton btnCreateNewStock;
    private JButton btnCreateNewVideoStock;
    private JButton btnCreateNewJournalStock;
    private JButton btnCreateNewCDStock;
    private JLabel lblStaffOption_ManageLoans;
    private JButton btnStaffOption_ManageLoans;

    private ArrayList<ObjectStockVideo> videoStock = StockManager.getAllVideoStock();
    private ArrayList<ObjectStockJournal> journalStock = StockManager.getAllJournalStock();
    private ArrayList<ObjectStockBook> bookStock = StockManager.getAllBookStock();
    private ArrayList<ObjectStockCompactDisc> compactDiscStock = StockManager.getAllCompactDiscStock();

    public Form_Main() {

        /** Listener: if Tab has changed */
        tabMain.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {

                /** Switch Statement for each Tab */
                switch (tabMain.getSelectedIndex()) {
                    case 0:
                        /** Refresh Main Menu Tab */
                        initMainMenuTab();
                        break;
                    case 1:
                        /** Refresh Profile Tab */
                        initProfileTab();
                        break;
                    case 2:
                        /** Refresh Catalogue Tab */
                        initCatalogueTab();
                        break;
                    default:

                        break;
                }

            }
        });

        /** Listener: if Catalogue Tab has changed */
        tabCatalogue.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {

                /** Switch Statement for each Tab */
                switch (tabCatalogue.getSelectedIndex()) {
                    case 0:
                        /** Refresh Video Tab */
                        initCatalogueVideo();
                        break;
                    case 1:

                        /** Refresh Compact Disc Tab */
                        initCatalogueCompactDisc();
                        break;
                    case 2:

                        /** Refresh Journal Tab */
                        initCatalogueJournal();
                        break;
                    case 3:

                        /** Refresh Book Tab */
                        initCatalogueBook();
                        break;
                    default:

                        break;
                }

            }
        });

        /** Listener: if Login/Logout button is pressed */
        btnSession.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                /** If user is logged in, log out */
                if (MainComponent.getCurrentSession().isValid()) {

                    /** Ask the user if they wish to Log out */
                    int dialogButton = JOptionPane.YES_NO_OPTION;
                    int dialogResult = JOptionPane.showConfirmDialog(null, "Are you Sure that you wish to Log Out?", "Log Out?", dialogButton);

                    /** If user entered Yes, log out of session */
                    if (dialogResult == JOptionPane.YES_OPTION) {

                        /** Inform the Customer tha they have successfully logged out */
                        JOptionPane.showMessageDialog(panel1, "You have Successfully logged out of user " + MainComponent.getCurrentSession().getSessionUsername() + "!", "Logged Out", JOptionPane.INFORMATION_MESSAGE);
                        MainComponent.getCurrentSession().logOut();

                    }

                    /** Refresh the GUI */
                    refreshGUI();

                    /** Else, get user to log in */
                } else {

                    /** Create new Login Form instance */
                    Form_Login loginFrame = new Form_Login();
                    loginFrame.setTitle("Login");

                    /** Initialize Login Form JFrame Instance */
                    loginFrame.setSize(new Dimension(300, 150));
                    loginFrame.setContentPane(loginFrame.getPanel());
                    loginFrame.setVisible(true);
                    loginFrame.setResizable(false);

                }

            }
        });

        refreshGUI();

        /** Listener: if Create New User button is pressed */
        btnStaffOption_CreateUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /** Create new Login Form instance */
                Form_Register registerFrame = new Form_Register();
                registerFrame.setTitle("Login");

                /** Initialize Login Form JFrame Instance */
                registerFrame.setSize(new Dimension(300, 430));
                registerFrame.setContentPane(registerFrame.getPanel());
                registerFrame.setVisible(true);
                registerFrame.setResizable(false);
            }
        });
        btnStaffOption_ManageUsers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /** Create new Login Form instance */
                Form_ManageUsers manageUsersFrame = new Form_ManageUsers();
                manageUsersFrame.setTitle("Manage Users");

                /** Initialize Login Form JFrame Instance */
                manageUsersFrame.setSize(new Dimension(600, 350));
                manageUsersFrame.setContentPane(manageUsersFrame.getPanel());
                manageUsersFrame.setVisible(true);
                manageUsersFrame.setResizable(false);
            }
        });

        /** Catalogue Video PREVIOUS */
        btnVideo_Previous.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                catalogueIndex--;
                refreshGUI();
                refreshCurrentCatalogueTab();
            }
        });
        /** Catalogue Video NEXT */
        btnVideo_Next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                catalogueIndex++;
                refreshGUI();
                refreshCurrentCatalogueTab();
            }
        });
        /** Catalogue Borrow Item Button */
        btnBorrowStock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                borrowItem();

            }
        });
        /** Profile Tab - View Borrowed Stock */
        btnProfile_MyBorrowedStock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (MainComponent.getCurrentSession().getSessionUser().getUserType() != EnumUserType.NORMAL) {

                    /** Create new Login Form instance */
                    Form_MyBorrowedStock editFrame = new Form_MyBorrowedStock();
                    editFrame.setTitle("My Loaned Stocks");

                    /** Initialize Login Form JFrame Instance */
                    editFrame.setSize(new Dimension(300, 430));
                    editFrame.setContentPane(editFrame.getPanel());
                    editFrame.setVisible(true);
                    editFrame.setResizable(false);

                } else {
                    JOptionPane.showMessageDialog(panel1, "You have sufficient privileges to view loan items!", "Feature Unavailable!", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        btnRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshGUI();
                refreshCurrentCatalogueTab();
            }
        });
        btnProfile_EditMyUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                /** Create new Login Form instance */
                Form_EditUser editFrame = new Form_EditUser(MainComponent.getCurrentSession().getSessionUser().getUserID());
                editFrame.setTitle("Edit my Profile");

                /** Initialize Login Form JFrame Instance */
                editFrame.setSize(new Dimension(300, 430));
                editFrame.setContentPane(editFrame.getPanel());
                editFrame.setVisible(true);
                editFrame.setResizable(false);

            }
        });
        /** CREATE BOOK ITEM */
        btnCreateNewStock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String bookTitle = "";
                String bookAuthor = "";
                String bookPublisher = "";
                String bookGenre = "";
                int bookPageCount = 0;
                double bookCost = 0;

                bookTitle = JOptionPane.showInputDialog("Enter the Book Title");
                bookAuthor = JOptionPane.showInputDialog("Enter the Book Author");
                bookPublisher = JOptionPane.showInputDialog("Enter the Book Publisher");
                bookGenre = JOptionPane.showInputDialog("Enter the Book Genre");
                bookPageCount = Integer.parseInt(JOptionPane.showInputDialog("Enter the Book Page Count"));
                bookCost = Double.parseDouble(JOptionPane.showInputDialog("Enter the Book Cost"));

                ObjectStockBook newBook = new ObjectStockBook();
                newBook.setBookTitle(bookTitle);
                newBook.setBookAuthor(bookAuthor);
                newBook.setBookPublisher(bookPublisher);
                newBook.setBookSubjectArea(bookGenre);
                newBook.setBookNumberOfPages(bookPageCount);
                newBook.setStockCost(bookCost);

                try {
                    StockManager.saveStockObjectToFile(newBook, true);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                JOptionPane.showMessageDialog(panel1, "Successfully Created new Book Item: " + bookTitle + " by " + bookAuthor, "Creation Successful", JOptionPane.INFORMATION_MESSAGE);

            }
        });
        btnCreateNewVideoStock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String videoTitle = "";
                String videoFormat = "";
                String videoStorageType = "";
                String videoPublisher = "";
                String videoGenre = "";
                int videoRunningTime = 0;
                double videoCost = 0;

                videoTitle = JOptionPane.showInputDialog("Enter the Video Title");
                videoFormat = JOptionPane.showInputDialog("Enter the Video Format (DVD/HD/BLU-RAY)");
                videoStorageType = JOptionPane.showInputDialog("Enter the Video Storage Type");
                videoPublisher = JOptionPane.showInputDialog("Enter the Video Publisher");
                videoGenre = JOptionPane.showInputDialog("Enter the Genre of the Video");
                videoRunningTime = Integer.parseInt(JOptionPane.showInputDialog("Enter the Running Time of the Video"));
                videoCost = Double.parseDouble(JOptionPane.showInputDialog("Enter the Cost of the Video"));

                ObjectStockVideo newVideo = new ObjectStockVideo();
                newVideo.setVideoTitle(videoTitle);
                newVideo.setVideoFormat(videoFormat);
                newVideo.setVideoStorageCaseType(videoStorageType);
                newVideo.setVideoPublisher(videoPublisher);
                newVideo.setVideoGenre(videoGenre);
                newVideo.setRunningTime(videoRunningTime);
                newVideo.setStockCost(videoCost);

                try {
                    StockManager.saveStockObjectToFile(newVideo, true);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                JOptionPane.showMessageDialog(panel1, "Successfully Created new Video Item: " + videoTitle + " by " + videoPublisher, "Creation Successful", JOptionPane.INFORMATION_MESSAGE);

            }
        });
        btnCreateNewJournalStock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String journalName = "";
                int journalIssueNumber = 0;
                Date journalPublishDate = Calendar.getInstance().getTime();
                int journalISSN = 0;
                String journalSubject = "";
                String journalPublisher = "";
                int journalPageCount = 0;
                double journalCost = 0;

                journalName = JOptionPane.showInputDialog("Enter the Journal Title");
                journalIssueNumber = Integer.parseInt(JOptionPane.showInputDialog("Enter the Issue Number of the Journal"));
                journalISSN = Integer.parseInt(JOptionPane.showInputDialog("Enter the Journal ISSN"));
                journalSubject = JOptionPane.showInputDialog("Enter the Journal Genre/Subject");
                journalPublisher = JOptionPane.showInputDialog("Enter the Publisher of the Journal");
                journalPageCount = Integer.parseInt(JOptionPane.showInputDialog("Enter the Page Count of the Journal"));
                journalCost = Double.parseDouble(JOptionPane.showInputDialog("Enter the Cost of the Journal"));

                System.out.println("Enter the Date ");

                String date = JOptionPane.showInputDialog("Enter the Journal Publishing Date (dd-mmm-yyy)");

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");

                try {
                    //Parsing the String
                    journalPublishDate = dateFormat.parse(date);
                } catch (ParseException e2) {
                    // TODO Auto-generated catch block
                    e2.printStackTrace();
                }

                ObjectStockJournal newJournal = new ObjectStockJournal();
                newJournal.setJournalTitle(journalName);
                newJournal.setJournalIssueNumber(journalIssueNumber);
                newJournal.setJournalDate(journalPublishDate);
                newJournal.setISSN(journalISSN);
                newJournal.setJournalSubjectArea(journalSubject);
                newJournal.setJournalIssueNumber(journalPageCount);
                newJournal.setJournalPublisher(journalPublisher);
                newJournal.setStockCost(journalCost);

                try {
                    StockManager.saveStockObjectToFile(newJournal, true);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                JOptionPane.showMessageDialog(panel1, "Successfully Created new Journal Item: " + journalName + " by " + journalPublisher, "Creation Successful", JOptionPane.INFORMATION_MESSAGE);

            }
        });
        btnCreateNewCDStock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String cdTitle = "";
                String cdArtist = "";
                String cdPublisher = "";
                int tracksCount = 0;
                String caseType = "";
                double cdCost = 0;

                cdTitle = JOptionPane.showInputDialog("Enter the CD Title");
                cdArtist = JOptionPane.showInputDialog("Enter the CD Artist");
                cdPublisher = JOptionPane.showInputDialog("Enter the CD Publisher Name");
                tracksCount = Integer.parseInt(JOptionPane.showInputDialog("Enter the Track Count of the CD"));
                caseType = JOptionPane.showInputDialog("Enter the type of CD Case");
                cdCost = Double.parseDouble(JOptionPane.showInputDialog("Enter the Cost of the CD"));

                ObjectStockCompactDisc newDisc = new ObjectStockCompactDisc();
                newDisc.setDiscTitle(cdTitle);
                newDisc.setDiscArtist(cdArtist);
                newDisc.setDiscPublisher(cdPublisher);
                newDisc.setDiscNumberOfTracks(tracksCount);
                newDisc.setDiscStorageCaseType(caseType);
                newDisc.setStockCost(cdCost);

                try {
                    StockManager.saveStockObjectToFile(newDisc, true);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                JOptionPane.showMessageDialog(panel1, "Successfully Created new Disc Item: " + cdTitle + " by " + cdArtist, "Creation Successful", JOptionPane.INFORMATION_MESSAGE);

            }
        });

        btnStaffOption_ManageLoans.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

    }

    /**
     * Refresh the current tab depending on the current Media Type
     */
    private void refreshCurrentCatalogueTab() {

        /** Switch Statement for each Tab */
        switch (tabCatalogue.getSelectedIndex()) {
            case 0:
                /** Refresh Video Tab */
                initCatalogueVideo();
                break;
            case 1:

                /** Refresh Compact Disc Tab */
                initCatalogueCompactDisc();
                break;
            case 2:

                /** Refresh Journal Tab */
                initCatalogueJournal();
                break;
            case 3:

                /** Refresh Book Tab */
                initCatalogueBook();
                break;
            default:

                break;
        }

    }

    /**
     * Get Panel of Main form
     *
     * @return - Givne Container/Panel (Container)
     */
    public Container getPanel() {
        return this.panel1;
    }

    /**
     * Refresh GUI - Refresh the overall Interface
     */
    public void refreshGUI() {

        /** Update/Refresh Stocks */
        if (txtSearch.getText() != null && txtSearch.getText().length() > 0) {

            String searchCriteria = txtSearch.getText();

            if (StockManager.getAllVideoStockThatContains(searchCriteria) != null) {
                videoStock = StockManager.getAllVideoStockThatContains(searchCriteria);
            }
            if (StockManager.getAllJournalStockThatContains(searchCriteria) != null) {
                journalStock = StockManager.getAllJournalStockThatContains(searchCriteria);
            }
            if (StockManager.getAllBookStockThatContains(searchCriteria) != null) {
                bookStock = StockManager.getAllBookStockThatContains(searchCriteria);
            }
            if (StockManager.getAllCompactDiscStockThatContains(searchCriteria) != null) {
                compactDiscStock = StockManager.getAllCompactDiscStockThatContains(searchCriteria);
            }
        } else {
            videoStock = StockManager.getAllVideoStock();
            journalStock = StockManager.getAllJournalStock();
            bookStock = StockManager.getAllBookStock();
            compactDiscStock = StockManager.getAllCompactDiscStock();
        }

        tabCatalogue.setTitleAt(0, "Videos (" + videoStock.size() + ")");
        tabCatalogue.setTitleAt(1, "Compact Discs (" + compactDiscStock.size() + ")");
        tabCatalogue.setTitleAt(2, "Journals (" + journalStock.size() + ")");
        tabCatalogue.setTitleAt(3, "Books (" + bookStock.size() + ")");

        /** If the Current ObjectCurrentSession is valid, print "Logged In" status and hide appropriate buttons */
        if (MainComponent.getCurrentSession().isValid()) {
            btnProfile_MyBorrowedStock.setVisible(true);
            btnProfile_MyBorrowedStock.setEnabled(true);
            btnProfile_EditMyUser.setVisible(true);
            btnProfile_EditMyUser.setEnabled(true);
            lblSessionStatus.setText("Online! - Logged in as " + MainComponent.getCurrentSession().getSessionUsername() + " (" + MainComponent.getCurrentSession().getSessionUser().getUserType().getEnglish() + ")");
            lblSessionStatus.setForeground(Color.BLACK);
            btnSession.setText("Log out");
            /** Else, inform the Customer that nobody is logged in and show login/register buttons */
        } else {
            btnProfile_MyBorrowedStock.setVisible(false);
            btnProfile_MyBorrowedStock.setEnabled(false);
            btnProfile_EditMyUser.setVisible(false);
            btnProfile_EditMyUser.setEnabled(false);
            lblSessionStatus.setText("Offline! - Not logged in");
            lblSessionStatus.setForeground(Color.RED);
            btnSession.setText("Log in");
        }

        initMainMenuTab();
        initProfileTab();

        initCatalogueTab();

        refreshCurrentCatalogueTab();

    }

    /**
     * Initialize Profile Tab - Set the Information of the Profiles Tab
     */
    public void initProfileTab() {

        /** If the Current ObjectCurrentSession is Invalid, inform the user to Login */
        if (!MainComponent.getCurrentSession().isValid()) {
            lblTab_Profile_Message.setText("Please log in to view your Profile Details!");
            lblTab_Profile_Message.setForeground(Color.red);

            lblProfileUsername.setVisible(false);
            lblProfileEmail.setVisible(false);

            panelStaffUtilities.setEnabled(false);
            panelStaffUtilities.setVisible(false);

            btnBorrowStock.setEnabled(false);
            btnBorrowStock.setVisible(false);

            lblTab_Profile_Rank.setEnabled(false);
            lblTab_Profile_Rank.setVisible(false);

            btnRefresh.setEnabled(false);
            txtSearch.setEnabled(false);

        } else {

            btnBorrowStock.setEnabled(true);
            btnBorrowStock.setVisible(true);

            lblTab_Profile_Rank.setEnabled(true);
            lblTab_Profile_Rank.setVisible(true);

            btnRefresh.setEnabled(true);
            txtSearch.setEnabled(true);

            lblTab_Profile_Message.setText("Your Profile Details: \nTest");
            lblTab_Profile_Message.setForeground(Color.BLACK);

            /** Cast the User to a new User Object using the users Username */
            ObjectUser theUser = MainComponent.getCurrentSession().getSessionUser();

            /** If the User exists, print the user information */
            if (theUser != null) {
                lblProfileUsername.setVisible(true);
                lblProfileEmail.setVisible(true);

                lblProfileUsername.setText("Username: " + theUser.getUserName());
                lblProfileEmail.setText("Email Address: " + theUser.getEmailAddress());

                lblTab_Profile_Rank.setText("User Rank: " + theUser.getUserType().getEnglish());
            }

            /** If the user is a Staff Member, show Staff Options */
            if (theUser.getUserType() == EnumUserType.STAFF) {
                panelStaffUtilities.setEnabled(true);
                panelStaffUtilities.setVisible(true);
            } else {
                /** Else, disable them */
                panelStaffUtilities.setEnabled(false);
                panelStaffUtilities.setVisible(false);
            }

        }

    }

    /**
     * Initialize Main Menu Tab - Set the Information of the Main Menu Tab
     */
    public void initMainMenuTab() {

        /** Awkward fix for Image Panel */
        panelImage.setLayout(new BoxLayout(panelImage, BoxLayout.PAGE_AXIS));

        /** Get image icon from Resources and add it to new JLabel */
        ImageIcon theImage = new ImageIcon(this.getClass().getResource("/library.jpg"));
        JLabel imageLabel = new JLabel(new ImageIcon(theImage.getImage().getScaledInstance(629, 275, Image.SCALE_DEFAULT)));

        /** Remove all components and re-add image */
        panelImage.removeAll();
        panelImage.add(imageLabel);

    }

    /**
     * Initialize Catalogue Tab - Set the Information of the Catalogue Tab
     */
    public void initCatalogueTab() {

        if (MainComponent.getCurrentSession().isValid()) {
            lblTab_Catalogue_Message.setText("Official Glencaldy Catalogue");
            lblTab_Catalogue_Message.setForeground(Color.BLACK);

            btnVideo_Previous.setVisible(true);
            btnVideo_Next.setVisible(true);
            btnBorrowStock.setVisible(true);
            lblStockStatus.setVisible(true);

            enableCatalogueElements();

            tabCatalogue.setEnabled(true);
        } else {
            lblTab_Catalogue_Message.setText("Please log in to view the Catalogue!");
            lblTab_Catalogue_Message.setForeground(Color.RED);

            btnVideo_Previous.setVisible(false);
            btnVideo_Next.setVisible(false);
            btnBorrowStock.setVisible(false);
            lblStockStatus.setVisible(false);

            disableCatalogueElements();

            tabCatalogue.setEnabled(false);
        }

    }

    /**
     * Disable Catalogue Elements - Disable all Catalogue-based Elements
     */
    private void disableCatalogueElements() {

        lblVideo_ID.setVisible(false);
        lblVideo_Title.setVisible(false);
        lblVideo_Genre.setVisible(false);
        lblVideo_Format.setVisible(false);
        lblVideo_Publisher.setVisible(false);
        lblVideo_StorageType.setVisible(false);

    }

    /**
     * Enable Catalogue Elements - Enable all Catalogue-based Elements
     */
    private void enableCatalogueElements() {

        lblVideo_ID.setVisible(true);
        lblVideo_Title.setVisible(true);
        lblVideo_Genre.setVisible(true);
        lblVideo_Format.setVisible(true);
        lblVideo_Publisher.setVisible(true);
        lblVideo_StorageType.setVisible(true);

    }

    /**
     * Initialize Catalogue Video - Initialize the Video Catalogue
     */
    public void initCatalogueVideo() {

        if (catalogueIndex < 0) {
            catalogueIndex = (videoStock.size() - 1);
        } else if (catalogueIndex > (videoStock.size() - 1)) {
            catalogueIndex = 0;
        }

        if (videoStock.size() > 0) {
            ObjectStockVideo videoObject = videoStock.get(catalogueIndex);

            catalogueIndexUUID = videoObject.getStockID();

            lblVideo_ID.setText("Video ID: " + videoObject.getStockID());
            lblVideo_Title.setText("Video Title: " + videoObject.getVideoTitle());
            lblVideo_Publisher.setText("Video publisher: " + videoObject.getVideoPublisher());
            lblVideo_Format.setText("Video format: " + videoObject.getVideoFormat());
            lblVideo_StorageType.setText("Video storage type: " + videoObject.getVideoStorageCaseType());
            lblVideo_Genre.setText("Video genre: " + videoObject.getVideoGenre());

            lblCost.setText("£" + videoObject.getStockCost());

            try {
                if (StockManager.doesStockExistWithID(catalogueIndexUUID)) {
                    /** If the Stock has/hasn't been Borrowed, change Status Label */
                    if (MainComponent.getCurrentSession().isValid() && catalogueIndexUUID != null && StockManager.getStockFromID(catalogueIndexUUID).getBorrowingCustomerID() != null && StockManager.hasItemBeenBorrowed(catalogueIndexUUID)) {
                        if (StockManager.getStockFromID(catalogueIndexUUID).getBorrowingCustomerID().toString().equals(MainComponent.getCurrentSession().getSessionUser().getUserID().toString())) {
                            lblStockStatus.setText("Item Unavailable (Loaned) - By You");
                            lblStockStatus.setForeground(Color.ORANGE);
                        } else {
                            lblStockStatus.setText("Item Unavailable (Loaned)");
                            lblStockStatus.setForeground(Color.RED);
                        }
                    } else {
                        lblStockStatus.setText("Item Available (Loan-able)");
                        lblStockStatus.setForeground(Color.GREEN);
                    }
                } else {
                    lblStockStatus.setText("Unknown Item");
                    lblStockStatus.setForeground(Color.RED);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            lblStockStatus.setText("No Stock Found!");
            lblStockStatus.setForeground(Color.RED);
        }

        lblIndexHelp.setText("Index: " + (catalogueIndex + 1) + "/" + videoStock.size());

    }

    /**
     * Initialize Catalogue Compact Disc - Initialize the Compact Disc Catalogue
     */
    public void initCatalogueCompactDisc() {

        if (catalogueIndex < 0) {
            catalogueIndex = (compactDiscStock.size() - 1);
        } else if (catalogueIndex > (compactDiscStock.size() - 1)) {
            catalogueIndex = 0;
        }

        if (compactDiscStock.size() > 0) {
            ObjectStockCompactDisc discObject = compactDiscStock.get(catalogueIndex);

            catalogueIndexUUID = discObject.getStockID();

            lblDisc_Artist.setText("Artist: " + discObject.getDiscArtist());
            lblDisc_Name.setText("Title: " + discObject.getDiscTitle());
            lblDisc_Publisher.setText("Publisher: " + discObject.getDiscPublisher());
            lblDisc_Tracks.setText("Tracks: " + discObject.getDiscNumberOfTracks());
            lblDisc_CaseType.setText("Case Type: " + discObject.getDiscStorageCaseType());
            lblDisc_ID.setText("Disc ID: " + discObject.getStockID());

            lblCost.setText("£" + discObject.getStockCost());

            try {
                if (StockManager.doesStockExistWithID(catalogueIndexUUID)) {
                    /** If the Stock has/hasn't been Borrowed, change Status Label */
                    if (MainComponent.getCurrentSession().isValid() && catalogueIndexUUID != null && StockManager.getStockFromID(catalogueIndexUUID).getBorrowingCustomerID() != null && StockManager.hasItemBeenBorrowed(catalogueIndexUUID)) {
                        if (StockManager.getStockFromID(catalogueIndexUUID).getBorrowingCustomerID().toString().equals(MainComponent.getCurrentSession().getSessionUser().getUserID().toString())) {
                            lblStockStatus.setText("Item Unavailable (Loaned) - By You");
                            lblStockStatus.setForeground(Color.ORANGE);
                        } else {
                            lblStockStatus.setText("Item Unavailable (Loaned)");
                            lblStockStatus.setForeground(Color.RED);
                        }
                    } else {
                        lblStockStatus.setText("Item Available (Loan-able)");
                        lblStockStatus.setForeground(Color.GREEN);
                    }
                } else {
                    lblStockStatus.setText("Unknown Item");
                    lblStockStatus.setForeground(Color.RED);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            lblStockStatus.setText("No Stock Found!");
            lblStockStatus.setForeground(Color.RED);
        }

        lblIndexHelp.setText("Index: " + (catalogueIndex + 1) + "/" + compactDiscStock.size());

    }

    /**
     * Initialize Catalogue Book - Initialize the Book Catalogue
     */
    public void initCatalogueBook() {

        if (catalogueIndex < 0) {
            catalogueIndex = (bookStock.size() - 1);
        } else if (catalogueIndex > (bookStock.size() - 1)) {
            catalogueIndex = 0;
        }

        if (bookStock.size() > 0) {
            ObjectStockBook bookObject = bookStock.get(catalogueIndex);

            catalogueIndexUUID = bookObject.getStockID();

            lblBook_Author.setText("Book Author: " + bookObject.getBookAuthor());
            lblBook_ID.setText("Book ID: " + bookObject.getStockID());
            lblBook_PageCount.setText("Book Page Count: " + bookObject.getBookNumberOfPages());
            lblBook_Publisher.setText("Book Publisher: " + bookObject.getBookPublisher());
            lblBook_SubjectArea.setText("Case Type: " + bookObject.getBookSubjectArea());
            lblBook_Title.setText("Book Title: " + bookObject.getBookTitle());

            lblCost.setText("£" + bookObject.getStockCost());

            try {
                if (StockManager.doesStockExistWithID(catalogueIndexUUID)) {
                    /** If the Stock has/hasn't been Borrowed, change Status Label */
                    if (MainComponent.getCurrentSession().isValid() && catalogueIndexUUID != null && StockManager.getStockFromID(catalogueIndexUUID).getBorrowingCustomerID() != null && StockManager.hasItemBeenBorrowed(catalogueIndexUUID)) {
                        if (StockManager.getStockFromID(catalogueIndexUUID).getBorrowingCustomerID().toString().equals(MainComponent.getCurrentSession().getSessionUser().getUserID().toString())) {
                            lblStockStatus.setText("Item Unavailable (Loaned) - By You");
                            lblStockStatus.setForeground(Color.ORANGE);
                        } else {
                            lblStockStatus.setText("Item Unavailable (Loaned)");
                            lblStockStatus.setForeground(Color.RED);
                        }
                    } else {
                        lblStockStatus.setText("Item Available (Loan-able)");
                        lblStockStatus.setForeground(Color.GREEN);
                    }
                } else {
                    lblStockStatus.setText("Unknown Item");
                    lblStockStatus.setForeground(Color.RED);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            lblStockStatus.setText("No Stock Found!");
            lblStockStatus.setForeground(Color.RED);
        }

        lblIndexHelp.setText("Index: " + (catalogueIndex + 1) + "/" + bookStock.size());
    }

    /**
     * Initialize Catalogue Journal - Initialize the Journal Catalogue
     */
    public void initCatalogueJournal() {

        if (catalogueIndex < 0) {
            catalogueIndex = (journalStock.size() - 1);
        } else if (catalogueIndex > (journalStock.size() - 1)) {
            catalogueIndex = 0;
        }

        if (journalStock.size() > 0) {
            ObjectStockJournal journalObject = journalStock.get(catalogueIndex);

            catalogueIndexUUID = journalObject.getStockID();

            lblJournal_ID.setText("Journal ID: " + journalObject.getStockID());
            lblJournal_Name.setText("Journal Name: " + journalObject.getUnderstandableName());
            lblJournal_Issue.setText("Journal Issue: " + journalObject.getJournalIssueNumber());
            lblJournal_Date.setText("Journal Publish Date: " + journalObject.getJournalDate());
            lblJournal_ISSN.setText("Journal ISSN: " + journalObject.getISSN());
            lblJournal_SubArea.setText("Journal Subject Area: " + journalObject.getJournalSubjectArea());
            lblJournal_Publisher.setText("Journal Publisher: " + journalObject.getJournalPublisher());
            lblJournal_NumberOfPages.setText("Journal Page Count: " + journalObject.getJournalNumberOfPages());

            lblCost.setText("£" + journalObject.getStockCost());

            try {
                if (StockManager.doesStockExistWithID(catalogueIndexUUID)) {
                    /** If the Stock has/hasn't been Borrowed, change Status Label */
                    if (MainComponent.getCurrentSession().isValid() && catalogueIndexUUID != null && StockManager.getStockFromID(catalogueIndexUUID).getBorrowingCustomerID() != null && StockManager.hasItemBeenBorrowed(catalogueIndexUUID)) {
                        if (StockManager.getStockFromID(catalogueIndexUUID).getBorrowingCustomerID().toString().equals(MainComponent.getCurrentSession().getSessionUser().getUserID().toString())) {
                            lblStockStatus.setText("Item Unavailable (Loaned) - By You");
                            lblStockStatus.setForeground(Color.ORANGE);
                        } else {
                            lblStockStatus.setText("Item Unavailable (Loaned)");
                            lblStockStatus.setForeground(Color.RED);
                        }
                    } else {
                        lblStockStatus.setText("Item Available (Loan-able)");
                        lblStockStatus.setForeground(Color.GREEN);
                    }
                } else {
                    lblStockStatus.setText("Unknown Item");
                    lblStockStatus.setForeground(Color.RED);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            lblStockStatus.setText("No Stock Found!");
            lblStockStatus.setForeground(Color.RED);
        }

        lblIndexHelp.setText("Index: " + (catalogueIndex + 1) + "/" + journalStock.size());

    }

    /**
     * Borrow Item - Attempt to borrow the Item/Stock that is currently selected
     */
    private void borrowItem() {

        /** Prompt the User to confirm that they wish to borrow this Item */
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure that you wish to loan this Stock Item?", "Loan this Item?", dialogButton);
        if (dialogResult == JOptionPane.YES_OPTION) {

            /** If the current logged in session is valid and the current selected stock is not null */
            if (catalogueIndexUUID != null && MainComponent.getCurrentSession().isValid()) {

                /** If the User has not reached the borrow limit */
                try {
                    if (!UserManager.hasReachedBorrowLimit(MainComponent.getCurrentSession().getSessionUser().getUserID())) {

                        /** If the Item is not currently being borrowed */
                        if (!StockManager.hasItemBeenBorrowed(catalogueIndexUUID)) {

                            /** If the User is not Staff or Full Member, prevent them from borrowing item */
                            if (MainComponent.getCurrentSession().getSessionUser().getUserType() == EnumUserType.NORMAL) {
                                JOptionPane.showMessageDialog(panel1, "You have sufficient privileges to loan this item!", "Item Unavailable!", JOptionPane.ERROR_MESSAGE);
                            } else {
                                /** Borrow the Item */
                                StockManager.borrowItem(MainComponent.getCurrentSession().getSessionUser().getUserID(), catalogueIndexUUID);
                                LogManager.audit("Borrowed Item " + catalogueIndexUUID, MainComponent.getCurrentSession().getSessionUser().getUserID(), "userlog");
                            }

                        } else {
                            /** Else, inform user that item has already been borrowed */
                            JOptionPane.showMessageDialog(panel1, "This item has already been loaned!", "Item Unavailable!", JOptionPane.ERROR_MESSAGE);
                        }

                    } else {
                        /** Else, inform user that they have reached the maximum borrow limit */
                        JOptionPane.showMessageDialog(panel1, "You can only loan up to 4 items! Please return an item in order to loan another", "Item Unavailable!", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }

        }

        refreshGUI();

    }

}
