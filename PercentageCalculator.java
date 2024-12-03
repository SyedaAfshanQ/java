
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

public class PercentageCalculator {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PercentageCalculator::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Percentage Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(6, 2, 10, 10));

        // Labels and Input Fields
        JLabel lblFirstValue = new JLabel("First Value:");
        JTextField txtFirstValue = new JTextField();

        JLabel lblSecondValue = new JLabel("Second Value (Percentage):");
        JTextField txtSecondValue = new JTextField();

        JLabel lblResult = new JLabel("Result:");
        JTextField txtResult = new JTextField();
        txtResult.setEditable(false);

        // Dropdown for Calculation Type
        JLabel lblCalculationType = new JLabel("Calculation Type:");
        String[] calculationTypes = {"Calculate Percentage", "Percentage Increase", "Percentage Decrease", "Find Whole"};
        JComboBox<String> cbCalculationType = new JComboBox<>(calculationTypes);

        // Buttons
        JButton btnCalculate = new JButton("Calculate");
        JButton btnClear = new JButton("Clear");

        // Adding Components to the Frame
        frame.add(lblFirstValue);
        frame.add(txtFirstValue);

        frame.add(lblSecondValue);
        frame.add(txtSecondValue);

        frame.add(lblCalculationType);
        frame.add(cbCalculationType);

        frame.add(lblResult);
        frame.add(txtResult);

        frame.add(btnCalculate);
        frame.add(btnClear);

        frame.setVisible(true);

        // Action Listeners
        btnCalculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Parse inputs
                    BigDecimal firstValue = new BigDecimal(txtFirstValue.getText());
                    BigDecimal secondValue = new BigDecimal(txtSecondValue.getText());

                    // Calculation type
                    String calculationType = (String) cbCalculationType.getSelectedItem();
                    BigDecimal result = BigDecimal.ZERO;

                    switch (calculationType) {
                        case "Calculate Percentage":
                            result = firstValue.multiply(secondValue).divide(BigDecimal.valueOf(100));
                            break;
                        case "Percentage Increase":
                            result = firstValue.add(firstValue.multiply(secondValue).divide(BigDecimal.valueOf(100)));
                            break;
                        case "Percentage Decrease":
                            result = firstValue.subtract(firstValue.multiply(secondValue).divide(BigDecimal.valueOf(100)));
                            break;
                        case "Find Whole":
                            result = firstValue.multiply(BigDecimal.valueOf(100)).divide(secondValue);
                            break;
                    }

                    // Display result
                    txtResult.setText(result.toString());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid input. Please enter numeric values.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Clear all fields
                txtFirstValue.setText("");
                txtSecondValue.setText("");
                txtResult.setText("");
                cbCalculationType.setSelectedIndex(0);
            }
        });
    }
}
