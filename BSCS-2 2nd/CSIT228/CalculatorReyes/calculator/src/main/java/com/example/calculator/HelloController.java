package com.example.calculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.math.RoundingMode;
import java.nio.DoubleBuffer;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Objects;

public class HelloController {
    public TextField tfInput;

    static private class numberPair {
        public double number;
        public char opBind = '0';

        numberPair(double num) {
            number = num;
        }
    }

    @FXML
    protected void onButtonClick(ActionEvent e) {
        Button b = (Button) e.getSource();
        String text = tfInput.getText();

        if (Objects.equals(text, "Error!")) text = "";
        Double res;

        switch (b.getText()) {
            case "=":
                if (text.isEmpty()) return;
                try {
                    text = text.replaceAll(" ", "");
                    ArrayList<numberPair> numbers =  new ArrayList<>();

                    int curIdx = 0;
                    boolean isPositive = true;
                    for (int i = 0; i < text.length(); i++) {
                        if (text.charAt(i) == '+' || text.charAt(i) == '-') {
                            if (i == 0 || text.charAt(i - 1) == '+' || text.charAt(i - 1) == '-') {
                                if (text.charAt(i) == '-') {
                                    isPositive = !isPositive;
                                }
                                curIdx++;
                            } else {
                                double rawNum = Double.parseDouble(text.substring(curIdx, i));
                                if (!isPositive) {
                                    rawNum *= -1;
                                }
                                numbers.add(new numberPair(rawNum));
                                curIdx = i + 1;
                                isPositive = text.charAt(i) == '+';
                            }
                        } else if (text.charAt(i) == 'x' || text.charAt(i) == '/') {
                            if (curIdx == i) throw new NumberFormatException();
                            double rawNum = Double.parseDouble(text.substring(curIdx, i));
                            if (!isPositive) {
                                rawNum *= -1;
                            }
                            numberPair numb = new numberPair(rawNum);
                            numb.opBind = text.charAt(i);
                            numbers.add(numb);
                            curIdx = i + 1;
                            isPositive = true;
                        }
                    }

                    if (curIdx == text.length()) throw new NumberFormatException();
                    double rawNum = Double.parseDouble(text.substring(curIdx));
                    if (!isPositive) {
                        rawNum *= -1;
                    }
                    numbers.add(new numberPair(rawNum));

                    for (int i = 0; i < numbers.size(); i++) {
                        numberPair n = numbers.get(i);

                        while (n.opBind != '0') {
                            numberPair n2 = numbers.remove(i + 1);
                            if (n.opBind == 'x') {
                                n.number *= n2.number;
                            } else {
                                n.number /= n2.number;
                            }
                            n.opBind = n2.opBind;
                        }
                    }

                    double result = 0;
                    while (!numbers.isEmpty()) {
                        result += numbers.removeFirst().number;
                    }

                    text = String.valueOf(result);
                    if (text.endsWith(".0")) {
                        text = text.substring(0, text.length() - 2);
                    }

                    tfInput.setText(text);
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
                    tfInput.setText("Error!");
                    return;
                }

                break;
            case "X":
                tfInput.setText(text + " x ");
                break;
            case "CE":
                if (text.isEmpty()) return;
                tfInput.setText(text.substring(0, text.length() - 1));
                break;
            case "C":
                tfInput.setText("");
                break;
            default:
                tfInput.setText(text + b.getText());
        }
    }
}