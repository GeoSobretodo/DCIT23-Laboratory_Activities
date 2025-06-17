import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class LabExamCalculatorGUI extends JFrame implements ActionListener {

    // --- UI Components ---
    private JTextField display;
    private JLabel historyDisplay;
    private JButton[] numberButtons;
    private JButton[] functionButtons;

    // --- Calculator Logic Variables ---
    private double currentNumber = 0;
    private double operand2 = 0;
    private String currentOperator = "";
    private boolean startNewNumber = true;
    private boolean decimalEntered = false;
    private double memoryValue = 0;

    // Used for formatting the output to avoid excessive decimal places for integers
    private DecimalFormat decimalFormat;

    public LabExamCalculatorGUI() {
        setTitle("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false); // Make the calculator window not resizable
        // Apply UI manager setting for slightly rounded buttons
        UIManager.put("Button.arc", 15); // Adjust for more rounding

        // Initialize decimal format to show up to 10 decimal places, but remove trailing zeros
        decimalFormat = new DecimalFormat("#.##########");

        // --- Setup Main Frame Layout ---
        setLayout(new BorderLayout(5, 5));

        // --- History Display ---
        historyDisplay = new JLabel("");
        historyDisplay.setHorizontalAlignment(SwingConstants.RIGHT);
        historyDisplay.setFont(new Font("Inter", Font.PLAIN, 18));
        historyDisplay.setForeground(new Color(100, 100, 100)); // Dark gray for subtle history
        historyDisplay.setBorder(BorderFactory.createEmptyBorder(5, 10, 0, 10)); // Padding
        add(historyDisplay, BorderLayout.NORTH);

        // --- Main Display Field ---
        display = new JTextField("0");
        display.setEditable(false);
        display.setFont(new Font("Inter", Font.BOLD, 50));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setBackground(new Color(240, 248, 255));
        display.setForeground(Color.BLACK);
        // Add a subtle border to the display
        display.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 190, 200), 2),
                BorderFactory.createEmptyBorder(5, 10, 5, 10) // padding
        ));
        add(display, BorderLayout.CENTER);

        // --- Button Panel ---
        JPanel buttonPanel = new JPanel();
        // Use GridLayout for the button grid: 6 rows, 4 columns, with gaps
        buttonPanel.setLayout(new GridLayout(6, 4, 10, 10)); // Gaps between buttons
        buttonPanel.setBackground(new Color(230, 235, 240)); // A soft blue-grey background for the panel
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding around the grid

        // Initialize button arrays
        numberButtons = new JButton[10]; // 0-9
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
        }

        // Define labels for function buttons
        String[] funcLabels = {
                "AC", "DEL", "%", "/",
                "M+", "M-", "MR", "MC",
                "7", "8", "9", "*",
                "4", "5", "6", "-",
                "1", "2", "3", "+",
                "+/-", "0", ".", "="
        };

        // All button labels in the order they will appear in the grid
        String[] allButtonLabels = {
                "AC", "DEL", "√", "^",
                "M+", "M-", "MR", "MC",
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "+/-", "0", ".", "+",
                "="
        };

        // let's make '=' a regular button at the end of the 6th row.
        buttonPanel.setLayout(new GridLayout(7, 4, 10, 10)); // Adjusted to 7 rows if '=' is alone or spans.

        String[] calculatorButtons = {
                "AC", "DEL", "√", "%",
                "M+", "M-", "MR", "MC",
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "+/-", "0", ".", "+",
                "=", " ", " ", " "
        };

        String[] buttonLabelsOrdered = {
                "AC", "DEL", "√", "%",
                "M+", "M-", "MR", "MC",
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "+/-", "0", ".", "+",
                "="
        };

        String[] visualLayoutButtons = {
                "AC", "DEL", "√", "%",
                "M+", "M-", "MR", "MC", // Memory row
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "+/-", "0", ".", "+", // Last row, but '+' and '=' are large.
                "=", "^", " ", " "    // Separate row for '=' to potentially span and Exponent
        };

        // For simplicity of GridLayout, let's just make them normal size buttons and keep the 6x4 grid.
        buttonPanel.setLayout(new GridLayout(6, 4, 10, 10)); // Back to 6x4 for simplicity

        String[] finalButtonLabels = {
                "AC", "DEL", "√", "^",
                "M+", "M-", "MR", "MC", // Row 2: Memory functions
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "+/-", "0", ".", "="
        };

        // Corrected final button labels for a 6x4 grid to include all operations
        String[] gridButtons = {
                "AC", "DEL", "√", "%",
                "M+", "M-", "MR", "MC",
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "+/-", "0", ".", "+",
                "^", "=" , " ", " "
        };

        // This is a common calculator layout:
        String[] commonLayoutButtons = {
                "AC", "DEL", "√", "^", // Row 1 (Functions/Clear)
                "7", "8", "9", "/",   // Row 2
                "4", "5", "6", "*",   // Row 3
                "1", "2", "3", "-",   // Row 4
                "+/-", "0", ".", "+", // Row 5
                "%", "=", "M+", "M-"  // Row 6 (Remaining ops, Memory, etc.)
        };

        // Final decision on layout for a clean code: 6x4 grid
        buttonPanel.setLayout(new GridLayout(6, 4, 10, 10)); // 6 rows, 4 columns

        String[] buttonLabels = {
                "AC", "DEL", "√", "^", // Row 1: Clear All, Clear Entry, Square Root, Exponent
                "7", "8", "9", "/",   // Row 2
                "4", "5", "6", "*",   // Row 3
                "1", "2", "3", "-",   // Row 4
                "+/-", "0", ".", "+", // Row 5: Change Sign, Zero, Decimal, Add
                "M+", "M-", "MR", "=" // Row 6: Memory Plus, Memory Minus, Memory Recall, Equals
        };

        // Add buttons to the panel
        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setFont(new Font("Inter", Font.BOLD, 22)); // Larger font for buttons
            button.addActionListener(this); // Register ActionListener
            customizeButton(button, label); // Apply custom styling
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.SOUTH); // Place button panel at the bottom

        pack(); // Adjusts frame size to fit all components
        setLocationRelativeTo(null); // Centers the frame on the screen
        setVisible(true); // frame visible
    }
    private void customizeButton(JButton button, String label) {
        button.setFocusPainted(false); // Remove focus border

        // Set default colors for number and common operation buttons
        button.setBackground(new Color(200, 220, 240)); // Light blue/grey
        button.setForeground(Color.BLACK); // Black text

        // Apply specific styles based on button label
        switch (label) {
            case "AC":
                button.setBackground(new Color(255, 100, 100)); // Black for Clear All
                button.setForeground(Color.WHITE);
                break;
            case "DEL":
                button.setBackground(new Color(255, 150, 150)); // Lighter red for Clear Entry
                button.setForeground(Color.BLACK);
                break;
            case "+": case "-": case "*": case "/":
            case "^": case "√": case "%":
                button.setBackground(new Color(120, 170, 220)); // Medium blue for operators
                button.setForeground(Color.WHITE);
                break;
            case "+/-": case ".":
                button.setBackground(new Color(180, 200, 220)); // Slightly darker for sign/decimal
                button.setForeground(Color.BLACK);
                break;
            case "M+": case "M-": case "MR": case "MC":
                button.setBackground(new Color(150, 180, 210)); // Memory buttons
                button.setForeground(Color.BLACK);
                break;
            case "=": // Make equals button distinct
                button.setBackground(new Color(50, 150, 220)); // Darker blue for equals
                button.setForeground(Color.WHITE);
                break;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand(); // Get the text of the clicked button

        // --- Number Input (0-9) ---
        if (command.matches("[0-9]")) {
            if (startNewNumber || display.getText().equals("0") || display.getText().equals("Error")) {
                display.setText(command); // Start new number or replace "0" or "Error"
                startNewNumber = false;
            } else {
                display.setText(display.getText() + command); // Append digit
            }
        }
        // --- Decimal Point (.) ---
        else if (command.equals(".")) {
            if (startNewNumber || display.getText().equals("Error")) {
                display.setText("0."); // Start with "0." if new number or error
                startNewNumber = false;
                decimalEntered = true;
            } else if (!decimalEntered) {
                display.setText(display.getText() + "."); // Add decimal if not already present
                decimalEntered = true;
            }
        }
        // --- Clear All (AC) ---
        else if (command.equals("AC")) {
            display.setText("0");
            historyDisplay.setText("");
            currentNumber = 0;
            operand2 = 0;
            currentOperator = "";
            startNewNumber = true;
            decimalEntered = false;
        }
        // --- Clear Entry (CE) ---
        else if (command.equals("CE")) {
            String currentText = display.getText();
            if (currentText.equals("Error")) { // If display shows error, clear completely
                display.setText("0");
                startNewNumber = true;
                decimalEntered = false;
            } else if (currentText.length() > 1) {
                if (currentText.endsWith(".")) {
                    decimalEntered = false; // Reset decimal flag if decimal is removed
                }
                display.setText(currentText.substring(0, currentText.length() - 1)); // Remove last char
            } else {
                display.setText("0"); // If only one char left, set to "0"
                startNewNumber = true;
                decimalEntered = false;
            }
        }
        // --- Change Sign (+/-) ---
        else if (command.equals("+/-")) {
            try {
                double value = Double.parseDouble(display.getText());
                display.setText(formatResult(-value));
                // If it's the start of a new number, update currentNumber as well
                if (startNewNumber) {
                    currentNumber = -value;
                }
            } catch (NumberFormatException ex) {
                // Ignore if display is "Error" or non-numeric
            }
        }
        // --- Basic Operators (+, -, *, /, %) or Exponent (^) ---
        else if (command.matches("[+\\-*/%^]")) {
            try {
                if (!currentOperator.isEmpty() && !startNewNumber) {
                    // If an operator is already set and a second number has been entered,
                    // calculate the intermediate result before setting a new operator.
                    operand2 = Double.parseDouble(display.getText());
                    calculateResult();
                    display.setText(formatResult(currentNumber)); // Display intermediate result
                    historyDisplay.setText(historyDisplay.getText() + " " + operand2 + " " + command);
                } else {
                    currentNumber = Double.parseDouble(display.getText());
                    historyDisplay.setText(formatResult(currentNumber) + " " + command);
                }
                currentOperator = command;
                startNewNumber = true; // Next digit starts a new number
                decimalEntered = false;
            } catch (NumberFormatException ex) {
                display.setText("Error");
                currentNumber = 0;
                currentOperator = "";
            }
        }
        // --- Square Root (√) ---
        else if (command.equals("√")) {
            try {
                double value = Double.parseDouble(display.getText());
                if (value >= 0) {
                    double result = Math.sqrt(value);
                    display.setText(formatResult(result));
                    historyDisplay.setText("√(" + formatResult(value) + ") = " + formatResult(result));
                    currentNumber = result;
                    startNewNumber = true;
                    decimalEntered = false;
                } else {
                    display.setText("Error: Invalid input");
                    historyDisplay.setText("");
                    currentNumber = 0;
                }
            } catch (NumberFormatException ex) {
                display.setText("Error");
                historyDisplay.setText("");
                currentNumber = 0;
            }
        }
        // --- Equals (=) ---
        else if (command.equals("=")) {
            if (!currentOperator.isEmpty() && !startNewNumber) {
                try {
                    operand2 = Double.parseDouble(display.getText());
                    historyDisplay.setText(formatResult(currentNumber) + " " + currentOperator + " " + formatResult(operand2) + " = ");
                    calculateResult();
                    display.setText(formatResult(currentNumber)); // currentNumber now holds the result
                    currentOperator = ""; // Reset operator
                    startNewNumber = true; // Ready for new operation
                    decimalEntered = false;
                } catch (NumberFormatException ex) {
                    display.setText("Error");
                    currentNumber = 0;
                    currentOperator = "";
                }
            } else if (currentOperator.isEmpty() && !startNewNumber) {
                // If '=' pressed without an operator, just update history with current display
                historyDisplay.setText(display.getText() + " =");
            }
        }
        // --- Memory Functions ---
        else if (command.equals("M+")) {
            try {
                memoryValue += Double.parseDouble(display.getText());
                historyDisplay.setText("Memory: " + formatResult(memoryValue));
            } catch (NumberFormatException ex) {
                // Ignore if display is not a valid number
            }
            startNewNumber = true; // Ready for next input
        }
        else if (command.equals("M-")) {
            try {
                memoryValue -= Double.parseDouble(display.getText());
                historyDisplay.setText("Memory: " + formatResult(memoryValue));
            } catch (NumberFormatException ex) {
                // Ignore if display is not a valid number
            }
            startNewNumber = true; // Ready for next input
        }
        else if (command.equals("MR")) {
            display.setText(formatResult(memoryValue));
            historyDisplay.setText("Memory Recall: " + formatResult(memoryValue));
            startNewNumber = true; // Displayed memory, so next input starts new number
            decimalEntered = formatResult(memoryValue).contains("."); // Set decimal flag if recalled value has decimal
        }
        else if (command.equals("MC")) {
            memoryValue = 0;
            historyDisplay.setText("Memory Cleared");
            startNewNumber = true;
        }
    }

    private void calculateResult() {
        try {
            switch (currentOperator) {
                case "+":
                    currentNumber += operand2;
                    break;
                case "-":
                    currentNumber -= operand2;
                    break;
                case "*":
                    currentNumber *= operand2;
                    break;
                case "/":
                    if (operand2 == 0) {
                        display.setText("Error: Div by 0");
                        currentNumber = 0; // Reset for safety
                        currentOperator = ""; // Clear operator
                        return; // Exit without updating currentNumber
                    }
                    currentNumber /= operand2;
                    break;
                case "%":
                    currentNumber %= operand2;
                    break;
                case "^": // Exponent operation
                    currentNumber = Math.pow(currentNumber, operand2);
                    break;
            }
        } catch (NumberFormatException ex) {
            display.setText("Error");
            currentNumber = 0;
            currentOperator = "";
        }
    }

    private String formatResult(double value) {
        // Use DecimalFormat to avoid scientific notation for large numbers and remove trailing .0
        // for integer values, while keeping precision for decimals.
        return decimalFormat.format(value);
    }

    //Main method to run the calculator application. Uses SwingUtilities.invokeLater to ensure GUI updates are on the Event Dispatch Thread.
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LabExamCalculatorGUI());
    }
}
