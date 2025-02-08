// Import necessary libraries for GUI components and event handling
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

// Main class implementing ActionListener for handling button actions
public class calculator implements ActionListener {

    // Declare the frame, text field, and buttons
    JFrame frame; // The main window (frame) of the calculator
    JTextField textfield; // The text field to display input/output
    JButton[] numberButtons = new JButton[10]; // Array to store number buttons (0-9)
    JButton[] functionButtons = new JButton[9]; // Array to store function buttons (e.g., +, -, *, /)
    
    // Declare buttons for basic functions and operations
    JButton addButton, subButton, mulButton, divButton, decButton, equButton, delButton, clrButton, negButton; 
    JPanel panel; // Panel to hold the buttons in grid format

    // Set font style and size
    Font myFont = new Font("Ink Free", Font.BOLD, 30);

    // Declare variables for calculations
    double num1 = 0, num2 = 0, result = 0;
    char operator; // Holds the operator (+, -, *, /)
    boolean operatorClicked = false; // To check if an operator button is clicked

    // Constructor to set up the calculator's layout and behavior
    calculator() {
        // Initialize the frame
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close the program when the window is closed
        frame.setSize(420, 550); // Set the size of the window
        frame.setLayout(null); // Use null layout for manual placement of components

        // Initialize the text field and set its properties
        textfield = new JTextField();
        textfield.setBounds(50, 25, 300, 50); // Set location and size of the text field
        textfield.setFont(myFont); // Apply the font style
        textfield.setEditable(false); // Make the text field read-only for user input

        // Initialize the buttons with their labels
        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        decButton = new JButton(".");
        equButton = new JButton("=");
        delButton = new JButton("Delete");
        clrButton = new JButton("Clear");
        negButton = new JButton("(-)");

        // Store function buttons in an array for easy handling
        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;
        functionButtons[4] = decButton;
        functionButtons[5] = equButton;
        functionButtons[6] = delButton;
        functionButtons[7] = clrButton;
        functionButtons[8] = negButton;

        // Add ActionListener to all function buttons and set their font and properties
        for (int i = 0; i < 9; i++) {
            functionButtons[i].addActionListener(this);
            functionButtons[i].setFont(myFont);
            functionButtons[i].setFocusable(false); // Remove focus outline
        }

        // Initialize number buttons and set their properties
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i)); // Create button with the number
            numberButtons[i].setFont(myFont); // Apply the font
            numberButtons[i].setFocusable(false); // Remove focus outline
            numberButtons[i].addActionListener(this); // Add ActionListener for each button
        }

        // Set positions and sizes for special buttons (negative, delete, clear)
        negButton.setBounds(50, 430, 75, 50);
        delButton.setBounds(125, 430, 125, 50);
        clrButton.setBounds(250, 430, 100, 50);

        // Create a panel to arrange the buttons in a grid layout
        panel = new JPanel();
        panel.setBounds(50, 100, 300, 300); // Set location and size of the panel
        panel.setLayout(new GridLayout(4, 4, 10, 10)); // 4x4 grid with gaps of 10 pixels

        // Add the number and operator buttons to the panel
        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);
        panel.add(decButton);
        panel.add(numberButtons[0]);
        panel.add(equButton);
        panel.add(divButton);

        // Add the panel and buttons to the frame
        frame.add(panel);
        frame.add(negButton);
        frame.add(delButton);
        frame.add(clrButton);
        frame.add(textfield); // Add the text field to the frame
        frame.setVisible(true); // Make the frame visible
    }

    // Main method to run the calculator
    public static void main(String[] args) {
        calculator calc = new calculator(); // Create a new calculator object
    }

    // Action handler for button clicks
    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle number button clicks (0-9)
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                if (operatorClicked) { // If an operator was clicked, clear the text field
                    textfield.setText("");
                    operatorClicked = false;
                }
                textfield.setText(textfield.getText().concat(String.valueOf(i))); // Append the clicked number
            }
        }

        // Handle decimal point button click
        if (e.getSource() == decButton) {
            if (!textfield.getText().contains(".")) { // Ensure only one decimal point can be added
                textfield.setText(textfield.getText().concat("."));
            }
        }

        // Handle addition button click
        if (e.getSource() == addButton) {
            num1 = Double.parseDouble(textfield.getText()); // Store the first number
            operator = '+'; // Set the operator to addition
            operatorClicked = true; // Mark that an operator was clicked
        }

        // Handle subtraction button click
        if (e.getSource() == subButton) {
            num1 = Double.parseDouble(textfield.getText()); // Store the first number
            operator = '-'; // Set the operator to subtraction
            operatorClicked = true;
        }

        // Handle multiplication button click
        if (e.getSource() == mulButton) {
            num1 = Double.parseDouble(textfield.getText()); // Store the first number
            operator = '*'; // Set the operator to multiplication
            operatorClicked = true;
        }

        // Handle division button click
        if (e.getSource() == divButton) {
            num1 = Double.parseDouble(textfield.getText()); // Store the first number
            operator = '/'; // Set the operator to division
            operatorClicked = true;
        }

        // Handle equals button click to perform the operation
        if (e.getSource() == equButton) {
            num2 = Double.parseDouble(textfield.getText()); // Store the second number

            // Perform the operation based on the selected operator
            switch (operator) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    if (num2 != 0) {
                        result = num1 / num2; // Ensure no division by zero
                    } else {
                        textfield.setText("Error"); // Display error for division by zero
                        return;
                    }
                    break;
            }
            textfield.setText(String.valueOf(result)); // Display the result
            num1 = result; // Store the result for further calculations
        }

        // Handle clear button click to reset the text field
        if (e.getSource() == clrButton) {
            textfield.setText("");
        }

        // Handle delete button click to remove the last character
        if (e.getSource() == delButton) {
            String string = textfield.getText();
            if (string.length() > 0) {
                textfield.setText(string.substring(0, string.length() - 1)); // Remove the last character
            }
        }

        // Handle negative button click to negate the current number
        if (e.getSource() == negButton) {
            double temp = Double.parseDouble(textfield.getText());
            temp *= -1; // Negate the number
            textfield.setText(String.valueOf(temp)); // Display the negated number
        }
    }
}
