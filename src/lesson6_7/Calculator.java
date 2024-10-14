package lesson6_7;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame implements ActionListener {

    private JLabel display;  // 用于显示输入和结果
    private StringBuilder input;  // 存储当前输入内容
    private double firstNumber = 0;
    private String operator = "";

    public Calculator() {

        setTitle("Calculator");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        display = new JLabel("0", SwingConstants.RIGHT);
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        input = new StringBuilder();


        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4, 5, 5));

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", "C", "=", "+"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.PLAIN, 20));
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        // 布局窗体
        setLayout(new BorderLayout());
        add(display, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.matches("\\d")) {
            input.append(command);
            display.setText(input.toString());
        } else if (command.equals("C")) {
            input.setLength(0);
            display.setText("0");
            firstNumber = 0;
            operator = "";
        } else if (command.equals("=")) {
            if (operator.isEmpty() || input.length() == 0) return;
            double secondNumber = Double.parseDouble(input.toString());
            double result = calculate(firstNumber, secondNumber, operator);
            display.setText(String.valueOf(result));
            input.setLength(0);
            operator = "";
        } else {
            if (input.length() == 0) return;
            firstNumber = Double.parseDouble(input.toString());
            operator = command;
            input.setLength(0);
        }
    }

    private double calculate(double num1, double num2, String op) {
        switch (op) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                return num2 != 0 ? num1 / num2 : 0;  // 防止除以0
            default:
                return 0;
        }
    }
}
