import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * 
 * @author Young id=12330280
 */

class Calculator extends JFrame {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    // keys
    private final String[] buttons = { "7", "8", "9", "/", "4", "5", "6",
            "*", "1", "2", "3", "-", "0", ".", "=", "+" };
    // keys button array
    private JButton keys[] = new JButton[buttons.length];
    private JTextField console;
    private JPanel keysPanel;
    private JButton ackey;

    Calculator() {
        super("Calculator");
    }

    void start() {
        // init layout
        init();

        // frame conf
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(100, 50, 250, 350);
        this.setVisible(true);
        this.setDefaultCloseOperation(3);
        this.setResizable(false);

        // Add Listener
        addListener();
    }

    void init() {
        // console conf
        console = new JTextField("0", 13);
        console.setHorizontalAlignment(JTextField.RIGHT);
        console.setFont(new Font("微软雅黑", Font.BOLD, 25));
        console.setBackground(Color.white);

        // put key in one panel
        keysPanel = new JPanel();
        // gridlayout 4 Lines 5 Columns
        keysPanel.setLayout(new GridLayout(4, 4, 3, 3));
        for (int i = 0; i < buttons.length; i++) {
            keys[i] = new JButton(buttons[i]);
            keysPanel.add(keys[i]);
            keys[i].setForeground(Color.blue);
        }
        // red color for the operation & BLACk for equ
        keys[3].setForeground(Color.red);
        keys[7].setForeground(Color.red);
        keys[11].setForeground(Color.red);
        keys[14].setForeground(Color.BLACK);
        keys[15].setForeground(Color.red);

        // AC Panel
        JPanel acPanel = new JPanel();
        acPanel.setLayout(new GridLayout(1, 1, 3, 3));
        ackey = new JButton("AC");
        ackey.setForeground(Color.red);
        acPanel.add(ackey);

        // whole layout

        // control panel
        JPanel controlpanel = new JPanel();
        controlpanel.setLayout(new BorderLayout(3, 3));
        controlpanel.add("North", acPanel);
        controlpanel.add("Center", keysPanel);

        // display panel
        JPanel displaypanel = new JPanel();
        displaypanel.setLayout(new BorderLayout());
        displaypanel.add("Center", console);

        // combine
        getContentPane().setLayout(new BorderLayout(5, 5));
        getContentPane().add("North", displaypanel);
        getContentPane().add("Center", controlpanel);
    }

    void addListener() {
        Handler handler = new Handler();
        for (int i = 0; i < buttons.length; i++) {
            keys[i].addActionListener(handler);
        }
        ackey.addActionListener(handler);
    }

    // Handler
    class Handler implements ActionListener {

        private boolean isFirstDigit = true;
        private double number = 0.0;
        private String operator = "=";

        @Override
        public void actionPerformed(ActionEvent arg0) {
            // TODO Auto-generated method stub
            String command = arg0.getActionCommand();
            if (command.equals("AC")) {
                // AC
                handleAC();
            } else if ("0123456789.".indexOf(command) >= 0) {
                // 0-9 && .
                handleNumber(command);
            } else {
                // operation
                handleOperator(command);
            }
        }

        // reset
        void handleAC() {
            isFirstDigit = true;
            console.setText("0");
        }

        // handler number
        void handleNumber(String number) {
            if (isFirstDigit) {
                console.setText(number);
            } else if ((number.equals("."))
                    && (console.getText().indexOf(".") < 0)) {
                console.setText(console.getText() + ".");
            } else if (!number.equals(".")) {
                console.setText(console.getText() + number);
            }
            isFirstDigit = false;
        }

        void handleOperator(String key) {
            try {
                Double.valueOf(console.getText());
                if (operator.equals("+")) {
                    number += Double.valueOf(console.getText());
                } else if (operator.equals("-")) {
                    number -= Double.valueOf(console.getText());
                } else if (operator.equals("*")) {
                    number *= Double.valueOf(console.getText());
                } else if (operator.equals("/")) {
                    number /= Double.valueOf(console.getText());
                } else if (operator.equals("=")) {
                    number = Double.valueOf(console.getText());
                }
                console.setText(String.valueOf(number));
                operator = key;
                isFirstDigit = true;
            } catch (Exception e) {
                console.setText("Error,Try Again");
            }

        }
    }

    // MAIN
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.start();
    }
}
