package lesson6_7;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame implements ActionListener {

    private JLabel display;
    private StringBuilder expression;
    private double firstNumber = 0;
    private String operator = "";

    public Calculator() {

        setTitle("Calculator");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        display = new JLabel("0", SwingConstants.RIGHT);
        display.setFont(new Font("Arial", Font.BOLD, 30));
        display.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        expression = new StringBuilder();

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
            button.setFont(new Font("Arial", Font.PLAIN, 40));
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        setLayout(new BorderLayout());
        add(display, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.matches("\\d")) {  // 处理数字输入
            expression.append(command);
            display.setText(expression.toString());
        } else if (command.equals("C")) {  // 处理清除按钮
            expression.setLength(0);
            display.setText("0");
            firstNumber = 0;
            operator = "";
        } else if (command.equals("=")) {  // 处理等号按钮
            if (operator.isEmpty() || expression.length() == 0) return;

            // 解析第二个数字并计算
            double secondNumber = Double.parseDouble(expression.toString());
            double result = calculate(firstNumber, secondNumber, operator);

            // 显示完整表达式和结果
            display.setText(firstNumber + " " + operator + " " + secondNumber + " = " + result);

            // 重置状态
            expression.setLength(0);
            operator = "";
        } else {  // 处理操作符按钮
            if (expression.length() == 0) return;

            // 保存第一个数字和操作符
            firstNumber = Double.parseDouble(expression.toString());
            operator = command;

            // 更新表达式以显示操作符
            expression.append(" ").append(operator).append(" ");
            display.setText(expression.toString());

            // 准备接受下一个数字
            expression.setLength(0);
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
                return num2 != 0 ? num1 / num2 : 0;
            default:
                return 0;
        }
    }

}


