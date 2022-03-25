package ui;

import model.Student;
import model.TutorOrganization;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
/**
Class level comment:

 This class creates a graphical user interface allowing the user to have an interactable menu to perform operations on
 */
public class GUI2 implements ActionListener {

    private static final String JSON_STORE = "./data/tutororganization.json";
    private TutorOrganization tutorOrganization;
    private Student student;
    //private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // main frame fields
    private JFrame mainFrame;
    private JLabel title;
    private JButton saveButton;
    private JButton loadButton;
    private JButton quitButton;
    private JButton viewBookings;
    private JButton makeBookingButton;
    private JButton emptySlotsButton;
    private JButton fullSlotsButton;

    // booking fields
    private JFrame bookingFrame;
    private JLabel nameLabel;
    private JTextField nameInput;
    private JLabel bookingLabel;
    private JTextField bookingInput;
    private JButton confirmAppointment;
    private JLabel success;
    private String user;

    //booking is a success
    private JFrame successfullBooking;
    private JLabel successfullLabel;
    private JLabel successfullImage;
    private JFrame insuccessfullBooking;
    private JLabel insuccessfullLabel;
    private JLabel insuccessfullImage;

    // saving fields
    private JFrame savingFrame;
    private JLabel savingImage;

    // loading table
    private JFrame loadingFrame;
    private JTable loadingTable;

    //empty slots
    private JFrame emptySlotsFrame;
    private JLabel emptySlotsTitle;
    private JPanel emptyListPanel;
    private DefaultListModel stringDefaultListModelEmpty;
    private JList emptySlotsList;
    private JScrollPane emptyListScroller;

    //full slots
    private JFrame fullSlotsFrame;
    private JLabel fullSlotsTitle;
    private JPanel fullSlotsPanel;
    private DefaultListModel stringDefaultListModelfull;
    private JList fullSlotsList;
    private JScrollPane fullSlotScroller;


    // EFFECTS: Instantiates initial fields and sets up the main frame
    public GUI2() {
        init();

        mainFrame.setSize(700, 380);
        mainFrame.setLayout(null);

        title.setFont(new Font(Font.DIALOG, Font.PLAIN, 38));
        title.setBounds(200, 10, 400, 50);
        mainFrame.add(title);

        buttonVisuals();

        saveButton.addActionListener(this);
        loadButton.addActionListener(this);
        quitButton.addActionListener(this);
        viewBookings.addActionListener(this);
        makeBookingButton.addActionListener(this);
        emptySlotsButton.addActionListener(this);
        fullSlotsButton.addActionListener(this);

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);

    }

    // EFFECTS: place buttons onto mainFrame at certain position
    private void buttonVisuals() {
        saveButton.setBounds(20, 70, 195, 50);
        saveButton.setFont(new Font(Font.DIALOG, Font.PLAIN, 25));
        loadButton.setBounds(245, 70, 195, 50);
        loadButton.setFont(new Font(Font.DIALOG, Font.PLAIN, 25));
        quitButton.setBounds(470, 70, 195, 50);
        quitButton.setFont(new Font(Font.DIALOG, Font.PLAIN, 25));
        viewBookings.setBounds(20, 130, 315, 50);
        viewBookings.setFont(new Font(Font.DIALOG, Font.PLAIN, 25));
        makeBookingButton.setBounds(350, 130, 315, 50);
        makeBookingButton.setFont(new Font(Font.DIALOG, Font.PLAIN, 25));
        emptySlotsButton.setBounds(20, 190, 645, 50);
        emptySlotsButton.setFont(new Font(Font.DIALOG, Font.PLAIN, 25));
        fullSlotsButton.setBounds(20, 250, 645, 50);
        fullSlotsButton.setFont(new Font(Font.DIALOG, Font.PLAIN, 25));
        mainFrame.add(saveButton);
        mainFrame.add(loadButton);
        mainFrame.add(quitButton);
        mainFrame.add(viewBookings);
        mainFrame.add(makeBookingButton);
        mainFrame.add(emptySlotsButton);
        mainFrame.add(fullSlotsButton);
    }

    // EFFECTS: Initializes all mainFrame fields and buttons, student, tutorOrganization,
    //          and JSON read and write objects
    private void init() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        tutorOrganization = new TutorOrganization();
        student = new Student(null, 0);

        mainFrame = new JFrame("TutorOrganization");
        title = new JLabel("Tutor Organization");

        saveButton = new JButton("Save");
        loadButton = new JButton("Load");
        quitButton = new JButton("Quit");
        viewBookings = new JButton("View Bookings");
        makeBookingButton = new JButton("Make Booking");
        emptySlotsButton = new JButton("See Empty Slots");
        fullSlotsButton = new JButton("View Taken Slots");
    }

    // EFFECTS: Performs action depending on button click
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveButton) {
            saveTutorSession();
            savingWindow();
        } else if (e.getSource() == loadButton) {
            loadTutorSession();
            makeLoadingTable();
        } else if (e.getSource() == quitButton) {
            mainFrame.dispose();
        } else if (e.getSource() == viewBookings) {
            makeLoadingTable();
        } else if (e.getSource() == makeBookingButton) {
            bookingWindow();
        } else if (e.getSource() == confirmAppointment) {
            tryBooking();
        } else if (e.getSource() == emptySlotsButton) {
            findEmptySlots();
        } else if (e.getSource() == fullSlotsButton) {
            findFullSlots();
        }
    }

    // EFFECTS: Displays a list of times slots along with the name that are already taken
    public void findFullSlots() {
        fullSlotSetup();

        for (int i = 0; i < tutorOrganization.getStudentList().size(); i++) {
            if (tutorOrganization.getStudentList().get(i) != null) {
                String timeTaken = String.valueOf(i);
                String name = tutorOrganization.getStudentList().get(i).getStudentName();
                stringDefaultListModelfull.addElement(timeTaken + " " + name);
            }
        }

        fullSlotsList = new JList(stringDefaultListModelfull);
        fullSlotsList.setFont(new Font(Font.DIALOG, Font.PLAIN, 25));
        fullSlotsList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        fullSlotsList.setLayoutOrientation(JList.VERTICAL);

        fullSlotScroller = new JScrollPane(fullSlotsList);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        fullSlotScroller.setBorder(border);
        fullSlotScroller.setPreferredSize(new Dimension(250, 250));

        fullSlotsPanel.add(fullSlotScroller);
        fullSlotsFrame.add(fullSlotsPanel);

    }

    // EFFECTS: Initializes fields and sets up frame for findFullSlots()
    private void fullSlotSetup() {
        fullSlotsFrame = new JFrame();
        fullSlotsFrame.setSize(350, 450);
        fullSlotsFrame.setLayout(null);
        fullSlotsFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        fullSlotsFrame.setLocationRelativeTo(null);
        fullSlotsFrame.setVisible(true);

        fullSlotsTitle = new JLabel("Slots that are booked:");
        fullSlotsTitle.setFont(new Font(Font.DIALOG, Font.PLAIN, 25));
        fullSlotsTitle.setBounds(0, 0, 450, 50);
        fullSlotsFrame.add(fullSlotsTitle);

        fullSlotsPanel = new JPanel();
        fullSlotsPanel.setBounds(0, 50, 250, 350);

        stringDefaultListModelfull = new DefaultListModel();
    }

    // EFFECTS: Displays a list of times that are empty and can be booked
    public void findEmptySlots() {
        emptySlotSetup();

        for (int i = 0; i < tutorOrganization.getStudentList().size(); i++) {
            if (tutorOrganization.getStudentList().get(i) == null) {
                String emptyTime = String.valueOf(i);
                stringDefaultListModelEmpty.addElement(emptyTime);
            }
        }

        emptySlotsList = new JList(stringDefaultListModelEmpty);
        emptySlotsList.setFont(new Font(Font.DIALOG, Font.PLAIN, 25));
        emptySlotsList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        emptySlotsList.setLayoutOrientation(JList.VERTICAL);

        emptyListScroller = new JScrollPane(emptySlotsList);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        emptyListScroller.setBorder(border);
        emptyListScroller.setPreferredSize(new Dimension(250, 250));

        emptyListPanel.add(emptyListScroller);
        emptySlotsFrame.add(emptyListPanel);
    }

    // EFFECTS: Initializes fields and sets up frame for findEmptySlots()
    private void emptySlotSetup() {
        emptySlotsFrame = new JFrame();
        emptySlotsFrame.setSize(350, 450);
        emptySlotsFrame.setLayout(null);
        emptySlotsFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        emptySlotsFrame.setLocationRelativeTo(null);
        emptySlotsFrame.setVisible(true);

        emptySlotsTitle = new JLabel("Slots that are not booked:");
        emptySlotsTitle.setFont(new Font(Font.DIALOG, Font.PLAIN, 25));
        emptySlotsTitle.setBounds(0, 0, 450, 50);
        emptySlotsFrame.add(emptySlotsTitle);

        emptyListPanel = new JPanel();
        emptyListPanel.setBounds(0, 50, 250, 350);

        stringDefaultListModelEmpty = new DefaultListModel();
    }

    // EFFECTS: Constructs a JTABLE with each row representing a time slot and the name of the student if it is full
    //          False if slot is empty
    @SuppressWarnings("methodlength")
    public void makeLoadingTable() {

        loadingFrame = new JFrame();
        String[][] data = {
                {"0", getStudentName(0)},
                {"1", getStudentName(1)},
                {"2", getStudentName(2)},
                {"3", getStudentName(3)},
                {"4", getStudentName(4)},
                {"5", getStudentName(5)},
                {"6", getStudentName(6)},
                {"7", getStudentName(7)},
                {"8", getStudentName(8)},
                {"9", getStudentName(9)},
                {"10", getStudentName(10)},
                {"11", getStudentName(11)},
                {"12", getStudentName(12)},
                {"13", getStudentName(13)},
                {"14", getStudentName(14)},
                {"15", getStudentName(15)},
                {"16", getStudentName(16)},
                {"17", getStudentName(17)},
                {"18", getStudentName(18)},
                {"19", getStudentName(19)}};

        String[] column = {"Time", "Booked by:"};


        loadingTable = new JTable(data, column);
        loadingTable.setBounds(30, 70, 200, 600);
        loadingTable.setFont(new Font(Font.DIALOG, Font.PLAIN, 25));
        loadingTable.setRowHeight(35);
        loadingTable.setGridColor(Color.DARK_GRAY);
        JScrollPane scrollPane = new JScrollPane(loadingTable);
        scrollPane.setFont(new Font(Font.DIALOG, Font.PLAIN, 25));
        loadingFrame.add(scrollPane);
        loadingFrame.setSize(400, 800);
        loadingFrame.setVisible(true);
    }

    // REQUIRES: A time slot (integer) [0, 19]
    // EFFECTS: Gets the name of the student booked at index
    //          else return "empty"
    private String getStudentName(int index) {

        if (tutorOrganization.getStudentList().get(index) == null) {
            return "empty";
        } else {
            return tutorOrganization.getStudentList().get(index).getStudentName();
        }
    }

    // EFFECTS: Constructs new frame with an image indicating this TutorOrganization is saved
    public void savingWindow() {
        savingFrame = new JFrame("Saving Window");
        savingFrame.setSize(820, 600);
        savingFrame.setLayout(null);
        savingFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        savingFrame.setLocationRelativeTo(null);
        savingFrame.setVisible(true);

        savingImage = new JLabel(new ImageIcon("./data/saving_image.PNG"));
        savingImage.setBounds(10, 0, 770, 500);
        savingImage.setVisible(true);
        savingFrame.add(savingImage);
    }


    // MODIFIES: this
    // EFFECTS: Assigns a field to each text bar when user is attempting to make a booking and creates booking
    //          if slot is free
    public void tryBooking() {
        user = nameInput.getText();
        int bookingTime = Integer.parseInt(bookingInput.getText());

        enterInfo(user);
        makeBooking(bookingTime);
    }

    // REQUIRES: Non-empty userName
    // MODIFIES: student
    // EFFECTS: set student name to userName
    private void enterInfo(String userName) {
        student.setStudentName(userName);
    }

    // REQUIRES: a time [0, 19]
    // MODIFIES: this
    // EFFECTS: if time slot is empty, assign student to this time
    //          else ask student to book for a different time
    private void makeBooking(int time) {

        if (!tutorOrganization.checkIfTaken(time)) {
            tutorOrganization.makeNewTutorSession(student, time);
            bookingIsSuccessful(time);

        } else {
            bookingIsUnsuccessful(time);
        }
    }


    // REQUIRES: time[0, 19] and student to make an unsuccessful booking in makeBooking(int time)
    // EFFECTS: Creates a frame confirming unsuccessful booking
    private void bookingIsUnsuccessful(int time) {
        insuccessfullBooking = new JFrame();
        insuccessfullBooking.setSize(1100, 590);
        insuccessfullBooking.setLayout(null);
        insuccessfullBooking.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        insuccessfullBooking.setLocationRelativeTo(null);
        insuccessfullBooking.setVisible(true);

        insuccessfullLabel = new JLabel(user + " your requested session at " + time
                + " is taken please select another time.");
        insuccessfullLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 25));
        insuccessfullLabel.setBounds(10,10, 800, 50);
        insuccessfullBooking.add(insuccessfullLabel);

        insuccessfullImage = new JLabel(new ImageIcon("./data/unsuccessful_image.PNG"));
        insuccessfullImage.setBounds(50, 60, 1000, 450);
        insuccessfullImage.setVisible(true);
        insuccessfullBooking.add(insuccessfullImage);
    }

    // REQUIRES: time [0, 19] and student to make a successful booking in makeBooking(int time)
    // EFFECTS: Creates frame confirming successful booking
    private void bookingIsSuccessful(int time) {
        successfullBooking = new JFrame();
        successfullBooking.setSize(1000, 590);
        successfullBooking.setLayout(null);
        successfullBooking.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        successfullBooking.setLocationRelativeTo(null);
        successfullBooking.setVisible(true);

        successfullLabel = new JLabel(user + " your session is available, appointment is made for " + time + ".");
        successfullLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 25));
        successfullLabel.setBounds(10, 10, 700, 50);
        successfullBooking.add(successfullLabel);

        successfullImage = new JLabel(new ImageIcon("./data/successful_image.PNG"));

        successfullImage.setBounds(50, 60, 900, 450);
        successfullImage.setVisible(true);
        successfullBooking.add(successfullImage);
    }

    // EFFECTS: Constructs a window where student can make booking
    public void bookingWindow() {
        // make the frame
        bookingFrame = new JFrame("Make Booking");
        bookingFrame.setSize(550, 300);
        bookingFrame.setLayout(null);

        bookingFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        bookingFrame.setLocationRelativeTo(null);
        bookingFrame.setVisible(true);

        textBars();

        // enter button
        confirmAppointment = new JButton("Book Session");
        confirmAppointment.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
        confirmAppointment.setBounds(300, 100, 180, 60);
        bookingFrame.add(confirmAppointment);
        confirmAppointment.addActionListener(this);

        /*
        // success label
        success = new JLabel("");
        success.setBounds(10, 110, 300, 25);
        bookingFrame.add(success);
        //success.setText();

         */
    }

    // EFFECTS: Constructs the textbars where student will enter info
    private void textBars() {
        // name label
        nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 25));
        nameLabel.setBounds(10, 10, 80, 35);
        bookingFrame.add(nameLabel);

        // name text bar
        nameInput = new JTextField();
        nameInput.setFont(new Font(Font.DIALOG, Font.PLAIN, 25));
        nameInput.setBounds(300, 10, 165, 35);
        bookingFrame.add(nameInput);

        // booking label
        bookingLabel = new JLabel("Booking time (0 - 19):");
        bookingLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 25));
        bookingLabel.setBounds(10, 50, 250, 35);
        bookingFrame.add(bookingLabel);

        // booking text bar
        bookingInput = new JTextField();
        bookingInput.setFont(new Font(Font.DIALOG, Font.PLAIN, 25));
        bookingInput.setBounds(300, 50, 165, 35);
        bookingFrame.add(bookingInput);
    }

    // EFFECTS: saves the tutorOrganization to file
    private void saveTutorSession() {
        try {
            jsonWriter.open();
            jsonWriter.write(tutorOrganization);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            JFrame exceptionFrame = new JFrame();
            exceptionFrame.setSize(200, 200);
            exceptionFrame.setLayout(null);
            exceptionFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            exceptionFrame.setLocationRelativeTo(null);
            exceptionFrame.setVisible(true);

            JLabel exceptionLabel = new JLabel("Unable to write file: " + JSON_STORE);
            exceptionLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 25));
            exceptionLabel.setBounds(0, 0, 200, 50);

            exceptionFrame.add(exceptionLabel);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads tutorsession from file
    private void loadTutorSession() {
        try {
            tutorOrganization = jsonReader.read();
        } catch (IOException e) {
            JFrame exceptionFrame = new JFrame();
            exceptionFrame.setSize(200, 200);
            exceptionFrame.setLayout(null);
            exceptionFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            exceptionFrame.setLocationRelativeTo(null);
            exceptionFrame.setVisible(true);

            JLabel exceptionLabel = new JLabel("Unable to read from file: " + JSON_STORE);
            exceptionLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 25));
            exceptionLabel.setBounds(0, 0, 200, 50);

            exceptionFrame.add(exceptionLabel);
        }
    }
}
