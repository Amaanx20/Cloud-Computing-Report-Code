import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Simple Swing client that sends requests to the calculator web service
 * and displays the result in a small GUI.
 */
public class CalculatorClient extends JFrame {

    private final JTextField firstNumber;
    private final JTextField secondNumber;
    private final JTextField result;
    private final JComboBox<String> operation;
    private final JButton calculate;
    private String baseUrl = "https://calculator-webservice-c1234567.azurewebsites.net";


    public CalculatorClient() {
        setTitle("Calculator Client...");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 350);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 2, 20, 20));

        // Input fields for the two numbers
        firstNumber = new JTextField();
        secondNumber = new JTextField();

        // Dropdown for selecting the operation
        operation = new JComboBox<>(new String[]{"Add", "Subtract", "Multiply", "Divide"});

        // Result field (read‑only)
        result = new JTextField();
        result.setEditable(false);

        // Button that triggers the calculation
        calculate = new JButton("Calculate");
        calculate.addActionListener(this::calculate);

        // Build the UI layout
        add(new JLabel("Enter First Number:"));
        add(firstNumber);
        add(new JLabel("Enter Second Number:"));
        add(secondNumber);
        add(new JLabel("Select Operation:"));
        add(operation);
        add(new JLabel("Result:"));
        add(result);
        add(new JLabel()); // spacer
        add(calculate);
    }

    /**
     * Reads the inputs, builds the URL, calls the API, and shows the result.
     */
    private void calculate(ActionEvent e) {
        try {
            double number1 = Double.parseDouble(firstNumber.getText());
            double number2 = Double.parseDouble(secondNumber.getText());
            String op = ((String) operation.getSelectedItem()).toLowerCase();

            // Build the request URL for the selected operation
            String urlStr = String.format(
                    "%s/calculator/%s?number1=%s&number2=%s",
                    baseUrl, op, number1, number2
            );

            // Call the service and show the response
            String response = sendGet(urlStr);
            result.setText(response.trim());

        } catch (NumberFormatException ex) {
            // Handles invalid number input
            JOptionPane.showMessageDialog(this,
                    "Please enter valid numeric values...",
                    "Input error...",
                    JOptionPane.ERROR_MESSAGE);

        } catch (Exception ex) {
            // Handles any errors from the service call
            JOptionPane.showMessageDialog(this,
                    "Error calling service: " + ex.getMessage(),
                    "Service error...",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Sends a simple GET request to the calculator API and returns the response as a string.
     */
    private String sendGet(String urlStr) throws Exception {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        // Choose the correct stream based on the HTTP status
        int status = conn.getResponseCode();
        InputStream stream = (status >= 200 && status < 300)
                ? conn.getInputStream()
                : conn.getErrorStream();

        // Read the response body
        BufferedReader in = new BufferedReader(new InputStreamReader(stream));
        StringBuilder response = new StringBuilder();
        String line;

        while ((line = in.readLine()) != null) {
            response.append(line);
        }

        in.close();
        conn.disconnect();

        // If the service returned an error, throw it
        if (status >= 400) {
            throw new RuntimeException("HTTP error " + status + ": " + response);
        }

        return response.toString();
    }

    /**
     * Starts the Swing application.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculatorClient().setVisible(true));
    }
}
