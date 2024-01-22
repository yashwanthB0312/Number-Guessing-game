package Random_number_finding;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App extends JFrame {
    private int randomNumber;
    private JTextField guessField;
    private JLabel resultLabel;

    public App() {
        initializeUI();
        startNewGame();
    }

    private void initializeUI() {
        setTitle("Random Number Guessing Game");
        setSize(500, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        randomNumber = generateRandomNumber();

        JLabel instructionsLabel = new JLabel("Guess the number between 1 and 100:");
        guessField = new JTextField(10);
        JButton submitButton = new JButton("Submit Guess");
        submitButton.setFocusable(false);
        resultLabel = new JLabel("");

        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.add(instructionsLabel);
        inputPanel.add(guessField);
        inputPanel.add(submitButton);

        add(inputPanel, BorderLayout.NORTH);
        add(resultLabel, BorderLayout.CENTER);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });
    }

    private int generateRandomNumber() {
        return (int) (Math.random() * 100) + 1;
    }

    private void checkGuess() {
        try {
            int userGuess = Integer.parseInt(guessField.getText());
            if (userGuess < 1 || userGuess > 100) {
                resultLabel.setText("Please enter a number between 1 and 100.");
            } else {
                if (userGuess == randomNumber) {
                    resultLabel.setText("Congratulations! You guessed the correct number.");
                    JOptionPane.showMessageDialog(this,"Congratulations! You guessed the correct number.");
                    startNewGame();
                } else if (userGuess < randomNumber) {
                    resultLabel.setText("Try a higher number.");
                } else {
                    resultLabel.setText("Try a lower number.");
                }
            }
        } catch (NumberFormatException ex) {
            resultLabel.setText("Invalid input. Please enter a valid number.");
        } finally {
            guessField.setText("");
            guessField.requestFocus();
        }
    }
    private void startNewGame() {
        randomNumber = generateRandomNumber();
        resultLabel.setText("New game started. Guess the number!");
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new App().setVisible(true));
    }
}

