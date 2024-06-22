import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleCalculatorGUI extends JFrame implements ActionListener {
    private JTextField display;
    private JButton[] numberButtons;
    private JButton[] functionButtons;
    private JButton addButton, subButton, mulButton, divButton;
    private JButton modButton, powButton, decButton, equButton, clrButton, delButton;
    private JPanel panel;

    private Font myFont = new Font("Ink Free", Font.BOLD, 30);

    private double num1 = 0, num2 = 0, result = 0;
    private char operator = '\0'; // Initialize operator to null

    public SimpleCalculatorGUI() {
        createWindow();
        createUIComponents();
    }

    private void createWindow() {
        setTitle("Calculator");
        setSize(420, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
    }

    private void createUIComponents() {
        display = new JTextField();
        display.setBounds(50, 25, 300, 50);
        display.setFont(myFont);
        display.setEditable(false);
        add(display);

        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        modButton = new JButton("%");
        powButton = new JButton("^");
        decButton = new JButton(".");
        equButton = new JButton("=");
        clrButton = new JButton("C");
        delButton = new JButton("DEL");

        functionButtons = new JButton[]{
                addButton, subButton, mulButton, divButton, modButton, powButton, decButton, equButton, clrButton, delButton
        };

        for (JButton button : functionButtons) {
            button.addActionListener(this);
            button.setFont(myFont);
            button.setFocusable(false);
        }

        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(myFont);
            numberButtons[i].setFocusable(false);
        }

        delButton.setBounds(50, 430, 145, 50);
        clrButton.setBounds(205, 430, 145, 50);

        panel = new JPanel();
        panel.setBounds(50, 100, 300, 300);
        panel.setLayout(new GridLayout(5, 4, 10, 10)); // Updated to 5x4 layout to include additional buttons

        // Adding number buttons and function buttons to the panel
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
        panel.add(modButton);
        panel.add(powButton);

        add(panel);
        add(delButton);
        add(clrButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String input = display.getText();

        if (e.getSource() == equButton) {
            if (!input.isEmpty() && operator != '\0') {
                num2 = Double.parseDouble(input);
                performOperation();
            }
        } else if (e.getSource() == clrButton) {
            display.setText("");
            num1 = 0;
            num2 = 0;
            result = 0;
            operator = '\0';
        } else if (e.getSource() == delButton) {
            if (!input.isEmpty()) {
                String newInput = input.substring(0, input.length() - 1);
                display.setText(newInput);
            }
        } else if (e.getSource() == decButton) {
            if (!input.contains(".")) {
                display.setText(input + ".");
            }
        } else if (e.getSource() == addButton || e.getSource() == subButton ||
                e.getSource() == mulButton || e.getSource() == divButton ||
                e.getSource() == modButton || e.getSource() == powButton) {
            setOperator(e.getActionCommand().charAt(0));
        } else {
            JButton button = (JButton) e.getSource();
            String buttonText = button.getText();
            display.setText(input + buttonText);
        }
    }

    private void setOperator(char op) {
        if (!display.getText().isEmpty()) {
            num1 = Double.parseDouble(display.getText());
            operator = op;
            display.setText("");
        }
    }

    private void performOperation() {
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
                    result = num1 / num2;
                } else {
                    display.setText("Error: Division by zero");
                    return;
                }
                break;
            case '%':
                result = num1 % num2;
                break;
            case '^':
                result = Math.pow(num1, num2);
                break;
        }
        display.setText(String.valueOf(result));
        num1 = result;
        num2 = 0;
        operator = '\0';
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SimpleCalculatorGUI calculator = new SimpleCalculatorGUI();
            calculator.setVisible(true);
        });
    }
}

