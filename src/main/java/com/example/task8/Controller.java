package com.example.task8;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.TextField;
import java.awt.*;

public class Controller extends Component {
    private Alert alert = new Alert(Alert.AlertType.WARNING);

    private double first;
    private double second;
    private boolean start = true;
    private String selection = null;

    @FXML
    private TextField textField = new TextField();

    public void key(KeyEvent keyEvent) {
        String key = keyEvent.getCode().getName();
        switch (key) {
            case "1" -> oneAction();
            case "2" -> twoAction();
            case "3" -> threeAction();
            case "4" -> fourAction();
            case "5" -> fiveAction();
            case "6" -> sixAction();
            case "7" -> sevenAction();
            case "8" -> eightAction();
            case "9" -> nineAction();
            case "0" -> zeroAction();
            case "Slash" -> operationAction("dot");
            case "Equals" -> operationAction("equal");
            case "Quote" -> operationAction("plus");
            case "Minus" -> operationAction("minus");
            case "Open Bracket" -> operationAction("multiply");
            case "Period" -> operationAction("pow");
            case "Back Slash" -> operationAction("div");
            case "Backspace" -> operationAction("clear");
            case "Comma" -> operationAction("sqrt");
        }
    }

    public void digitAction(String digit){
        String value = textField.getText();
        if (start) {
            textField.setText(digit);
            start = false;
        }
        else
            textField.setText(value + digit);
    }

    public void operationAction(String operation){
        String value = textField.getText();
        switch (operation){
            case "dot":
                if (!textField.getText().contains("."))
                    if (textField.getText().length() > 0)
                        textField.setText(value + ".");
                    else
                        textField.setText("0.");
                break;
            case "equal":
                if (isNumeric(value)){
                    second = Double.parseDouble(value);
                    solve();
                }
                start = true;
                break;
            case "plus":
                operation("plus");
                break;
            case "minus":
                if (start){
                    textField.setText("-");
                    start = false;
                }
                else
                    operation("minus");
                break;
            case "multiply":
                operation("multiply");
                break;
            case "pow":
                operation("pow");
                break;
            case "div":
                operation("div");
                break;
            case "clear":
                textField.setText("");
                start = true;
                first = 0;
                second = 0;
                selection = null;
                break;
            case "sqrt":
                operationSqrt();
                break;
        }
    }

    public void operation(String operation){
        String value = textField.getText();
        if (selection != null){
            second = Double.parseDouble(value);
            solve();
            selection = operation;
        }
        else {
            selection = operation;
            if (isNumeric(value))
                first = Double.parseDouble(value);
        }
        start = true;
    }

    public void operationSqrt(){
        String value = textField.getText();
        if(selection != null) {
            second = Double.parseDouble(value);
            solve();
            second = first;
        }
        else {
            if (isNumeric(value))
                second = Double.parseDouble(value);
        }
        selection = "sqrt";
        solve();
        selection = null;
        start = true;
    }

    public void solve(){
        String result = null;
        switch (selection){
            case "plus":
                first += second;
                result = String.valueOf(first);
                break;
            case "minus":
                first -= second;
                result = String.valueOf(first);
                break;
            case "multiply":
                first *= second;
                result = String.valueOf(first);
                break;
            case "pow":
                if (first < 0 && second < 1 && second > 0 && second % 0.5 == 0){
                    first = 0;
                    alert.setTitle("Ошибка!");
                    alert.setHeaderText("Корень из отрицательного числа!");
                    alert.showAndWait();
                }
                else{
                    first = Math.pow(first, second);
                    result = String.valueOf(first);
                }
                break;
            case "div":
                if (second != 0){
                    first /= second;
                    result = String.valueOf(first);
                }
                else{
                    first = 0;
                    alert.setTitle("Ошибка!");
                    alert.setHeaderText("Деление на ноль!");
                    alert.showAndWait();
                }
                break;
            case "sqrt":
                if (second >= 0){
                    first = Math.sqrt(second);
                    result = String.valueOf(first);
                }
                else{
                    first = 0;
                    alert.setTitle("Ошибка!");
                    alert.setHeaderText("Корень из отрицательного числа!");
                    alert.showAndWait();
                }
                break;
        }
        selection = null;
        textField.setText(result);
    }

    public boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public void delAction() {
        if (textField.getLength() > 0) textField.setText(textField.getText().substring(0, textField.getLength() - 1));
    }
    public void clearAction() {
        operationAction("clear");
    }
    public void equalAction() {
        operationAction("equal");
    }
    public void dotAction() {
        operationAction("dot");
    }
    public void plusAction() {
        operationAction("plus");
    }
    public void minAction() {
        operationAction("minus");
    }
    public void multiplyAction() {
        operationAction("multiply");
    }
    public void divAction() {
        operationAction("div");
    }
    public void powAction() {
        operationAction("pow");
    }
    public void sqrtAction() {
        operationAction("sqrt");
    }

    public void zeroAction() {
        digitAction("0");
    }
    public void oneAction() {
        digitAction("1");
    }
    public void twoAction() {
        digitAction("2");
    }
    public void threeAction() {
        digitAction("3");
    }
    public void fourAction() {
        digitAction("4");
    }
    public void fiveAction() {
        digitAction("5");
    }
    public void sixAction() {
        digitAction("6");
    }
    public void sevenAction() {
        digitAction("7");
    }
    public void eightAction() {
        digitAction("8");
    }
    public void nineAction() {
        digitAction("9");
    }
}