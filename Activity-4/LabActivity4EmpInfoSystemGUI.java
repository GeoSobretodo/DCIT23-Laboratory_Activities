import java.awt.*;
import java.awt.event.*;

public class LabActivity4EmpInfoSystemGUI extends Frame implements ActionListener {
    TextField fnameInput, lnameInput, ageInput, hoursWorkedField, hourlyWageInput;
    TextArea outputArea;  // Text area for displaying output.
    Button computeButton; // Button to trigger the computation.

    public LabActivity4EmpInfoSystemGUI() { // Constructor for the GUI.
        setLayout(new GridLayout(7, 2, 10, 10)); // Set the layout manager to a grid layout with 7 rows, 2 columns, and spacing.

        add(new Label("First Name:")); // Label and text field for first name.
        fnameInput = new TextField();
        add(fnameInput);

        add(new Label("Last Name:"));// Label and text field for last name.
        lnameInput = new TextField();
        add(lnameInput);

        add(new Label("Age:")); // Label and text field for Age.
        ageInput = new TextField();
        add(ageInput);

        add(new Label("Hours Worked:")); // Label and text field for hours worked.
        hoursWorkedField = new TextField();
        add(hoursWorkedField);

        add(new Label("Hourly Wage:")); // Label and text field for hourly wage.
        hourlyWageInput = new TextField();
        add(hourlyWageInput);

        computeButton = new Button("Compute"); // Button to perform the computation.
        computeButton.addActionListener(this); // Register this frame as the action listener for the button.
        add(computeButton);

        outputArea = new TextArea("", 3, 40, TextArea.SCROLLBARS_VERTICAL_ONLY); // Text area for displaying the computed information.
        outputArea.setEditable(false); // Make the output area read-only.
        add(outputArea);


        setTitle("Employee Information System"); // Set the title of the frame.
        setSize(500, 350); // Set the initial size of the frame.
        setResizable(false); // Prevent the user from resizing the frame.
        setVisible(true); // Make the frame visible.

        addWindowListener(new WindowAdapter() { // Add a window listener to handle the closing of the window.
            public void windowClosing(WindowEvent e) {
                dispose(); // Release system resources used by the window.
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        String firstName = fnameInput.getText().trim();
        String lastName = lnameInput.getText().trim();
        String ageText = ageInput.getText().trim();
        String hoursText = hoursWorkedField.getText().trim();
        String wageText = hourlyWageInput.getText().trim();

        if (firstName.isEmpty() || lastName.isEmpty() || ageText.isEmpty() || hoursText.isEmpty() || wageText.isEmpty()) { // Check if any of the input fields are empty.
            outputArea.setText("All fields must be filled.");
            return;
        }

        int age;
        float hoursWorked;
        float hourlyWage;

        try {
            age = Integer.parseInt(ageText);
        } catch (NumberFormatException ex) {
            outputArea.setText("Error: Age must be a valid integer. Please enter a valid number for Age.");
            return;
        }

        try {
            hoursWorked = Float.parseFloat(hoursText);
        } catch (NumberFormatException ex) {
            outputArea.setText("Please enter a valid number for Hours Worked.");
            return;
        }

        try {
            hourlyWage = Float.parseFloat(wageText);
        } catch (NumberFormatException ex) {
            outputArea.setText("Please enter a valid number for Hourly Wage.");
            return;
        }

        String fullName = firstName + " " + lastName; // Concatenate the first and last names to get the full name.
        float dailyWage = hoursWorked * hourlyWage; // Calculate the daily wage.

        outputArea.setText("Full Name: " + fullName + "\nAge: " + age + "\nDaily Wage: PHP " + String.format("%.2f", dailyWage)); // Format the output string and display it in the output area.
    }

    public static void main(String[] args) { // Main method to create an instance of the GUI application.
        new LabActivity4EmpInfoSystemGUI;
    }
  }
