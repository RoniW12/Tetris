package screens;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class EndScreen {
    public static void showEndScreen(String username, int score) {
        // Create and set up the window
        JFrame frame = new JFrame("Game Over");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null); // Center the frame

        // Create the background panel with the background image
        BackgroundPanel backgroundPanel = new BackgroundPanel("Tetris\\src\\game_over_image.jpg");
        backgroundPanel.setLayout(null); // Use null layout for absolute positioning

        // Create and add a label for the game over message
        JLabel gameOverLabel = new JLabel("Game Over!", SwingConstants.CENTER);
        gameOverLabel.setFont(new Font("Arial", Font.BOLD, 32));
        gameOverLabel.setForeground(Color.BLACK);
        gameOverLabel.setBounds(0, 30, 400, 50); // Centered horizontally
        backgroundPanel.add(gameOverLabel);

        // Create and add a label for the username and score
        JLabel scoreLabel = new JLabel(username + ", your score is " + score, SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 18));
        scoreLabel.setForeground(Color.BLACK);
        scoreLabel.setBounds(0, 80, 400, 30); // Centered horizontally
        backgroundPanel.add(scoreLabel);

        // Create and add a save score button with rounded corners
        CustomRoundedButton saveScoreButton = new CustomRoundedButton("Save Score");
        saveScoreButton.setBounds(145, 135, 110, 30);
        saveScoreButton.setBackground(Color.BLACK);
        saveScoreButton.setForeground(Color.WHITE);
        saveScoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code to save the score goes here
                ScoreBoard.saveScore(username, score);
                System.out.println("Score saved!");
            }
        });
        backgroundPanel.add(saveScoreButton);

        // Create and add a home page button with rounded corners
        CustomRoundedButton homeButton = new CustomRoundedButton("Home Page");
        homeButton.setBounds(145, 175, 110, 30);
        homeButton.setBackground(Color.BLACK);
        homeButton.setForeground(Color.WHITE);
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				// Show the home page
                TetrisOpeningScreen.openScreen();
                frame.dispose(); // Close the end screen
            }
        });
        backgroundPanel.add(homeButton);

        // Add the panel to the frame
        frame.getContentPane().add(backgroundPanel);

        // Display the window
        frame.setVisible(true);
    }
}

// Custom JPanel class for drawing background image
class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    public BackgroundPanel(String imagePath) {
        try {
            backgroundImage = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}

// Custom JButton class for rounded corners
class RoundedButton extends JButton {
    public RoundedButton(String label) {
        super(label);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorder(BorderFactory.createEmptyBorder());
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        g2.dispose();
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getForeground());
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
        g2.dispose();
    }

    @Override
    public boolean contains(int x, int y) {
        int width = getWidth();
        int height = getHeight();
        int arc = 15;
        return (x >= 0 && x <= width && y >= 0 && y <= height);
    }
}
