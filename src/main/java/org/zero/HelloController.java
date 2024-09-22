package org.zero;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.time.Duration;
import java.time.Instant;

public class HelloController {

    @FXML
    public ComboBox<String> executionStrategySelect;
    @FXML
    public ComboBox<String> numberOfThreadsSelection;
    @FXML
    public TextField textInput3;
    @FXML
    public TextField textInput2;
    @FXML
    public TextField textInput1;
    @FXML
    public ListView<String> resultListView;

    public void calculate(ActionEvent actionEvent) {
        var a = Double.parseDouble(textInput1.getText());
        var b = Double.parseDouble(textInput2.getText());
        var n = Integer.parseInt(textInput3.getText());
        var threads = getCountOfThreads();

        var manager = switch (getExecutionStrategy()) {
            case 1 -> new FutureThreadManager(threads);
            default -> new StandardThreadManager(threads);
        };

        var calculator = new IntegralCalculator(manager);
        var function = new MyFunction();
        try {
            var start = Instant.now();
            var result = calculator.calculate(a, b, n, function);
            var end = Instant.now();
            resultListView.getItems().add("Method: " + executionStrategySelect.getValue()
                    + ",\nNumber of threads: " + threads + ", Iterations: " + n
                    + ",\nExecution time: " + Duration.between(start, end).toMillis() + "ms"
                    + ", Result: " + result);
        } catch (Exception e) {
            System.out.println("Threads error");
        }
    }

    private Integer getCountOfThreads() {
        String numberOfThreads = numberOfThreadsSelection.getValue();
        String parsedValue = numberOfThreads.replaceAll("\\D", "");
        return Integer.parseInt(parsedValue);
    }

    private Integer getExecutionStrategy() {
        String selectedValue = executionStrategySelect.getValue();
        String s = selectedValue.split("\\.")[0];
        return Integer.parseInt(s);
    }

    public void clear(ActionEvent actionEvent) {
       resultListView.getItems().clear();
    }
}