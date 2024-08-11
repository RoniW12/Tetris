package screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class GameScreen extends JFrame {
    private static final int GRID_ROWS = 20;
    private static final int GRID_COLS = 10;
    private static final int HORIZONTAL_PADDING = 20;
    private static final int VERTICAL_PADDING = 50;
    private int score = 0;

    private JPanel gamePanel;
    private JLabel scoreLabel;
    private BufferedImage backgroundImage;
    private JPanel[][] gridSquares;  // 2D array to store grid squares
    
    Color gridColor = Color.WHITE;
    Color emptyColor = Color.LIGHT_GRAY;
    Color pieceColor = Color.BLUE;
    Color lockedColor = Color.GRAY;

    public GameScreen() {
        setTitle("Tetris");
        setSize(GRID_COLS * 20 + 200 + 2 * HORIZONTAL_PADDING, GRID_ROWS * 20 + 2 * VERTICAL_PADDING);  // Added width for next piece display
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Load background image
        String backgroundImagePath = "Tetris\\src\\score_board_image1.jpg";
        try {
            backgroundImage = ImageIO.read(new File(backgroundImagePath));
            System.out.println("Background image loaded successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create game panel (grid) with background image
        gamePanel = new GameScreenBackgroundPanel();
        gamePanel.setLayout(null);  // Use null layout to manually position components
        gamePanel.setBorder(BorderFactory.createEmptyBorder(VERTICAL_PADDING, HORIZONTAL_PADDING, VERTICAL_PADDING, HORIZONTAL_PADDING));

        // Create and position the grid
        JPanel gridPanel = new JPanel(new GridLayout(GRID_ROWS, GRID_COLS));
        gridPanel.setBounds(150, VERTICAL_PADDING, GRID_COLS * 20, GRID_ROWS * 20);  // Positioned grid to the right
        gridPanel.setOpaque(false);  // Make the grid panel transparent

        gridSquares = new JPanel[GRID_ROWS][GRID_COLS];
        for (int row = 0; row < GRID_ROWS; row++) {
            for (int col = 0; col < GRID_COLS; col++) {
                JPanel square = new JPanel();
                square.setOpaque(true);
                square.setBackground(Color.LIGHT_GRAY);  // Default color
                square.setBorder(BorderFactory.createLineBorder(gridColor));  // Grid lines in light gray
                gridSquares[row][col] = square;
                gridPanel.add(square);
            }
        }

        gamePanel.add(gridPanel);

        // Create score label
        scoreLabel = new JLabel("Score: 0", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 24));  // Font size adjusted
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setBounds(HORIZONTAL_PADDING, 0, GRID_COLS * 20 + 150, VERTICAL_PADDING);  // Adjusted bounds to center the text

        gamePanel.add(scoreLabel);

        // Add space for next piece display
        JPanel nextPiecePanel = new JPanel();
        nextPiecePanel.setBounds(HORIZONTAL_PADDING, VERTICAL_PADDING + GRID_ROWS * 20 + 10, 120, 120);  // Positioned next piece display area
        nextPiecePanel.setBackground(new Color(0, 0, 0, 0));  // Transparent background

        gamePanel.add(nextPiecePanel);

        // Add game panel to the frame
        add(gamePanel, BorderLayout.CENTER);

        // Ensure the gamePanel is repainted and validated
        gamePanel.revalidate();
        gamePanel.repaint();

        setVisible(true);
    }

    // Method to set the color of a grid square
    public void setGridSquareColor( int row, int col, int value) {
        //if (row >= 0 && row < GRID_ROWS && col >= 0 && col < GRID_COLS) {
            JPanel square = gridSquares[row][col];
            switch (value) {
                case 0:
                    square.setBackground(emptyColor);
                    break;
                case -2:
                    square.setBackground(gridColor);
                    break;
                case 1:
                    square.setBackground(pieceColor);
                    break;
                case -1:
                    square.setBackground(pieceColor);
                    break;
                case 2:
                    square.setBackground(lockedColor);
                    break;
                default:
                    square.setBackground(emptyColor);  // Default color
                    break;
            }
            square.repaint();
        //}
    }

    public void updateScore(int score) {
        scoreLabel.setText("Score: " + score); 
    }

    // Custom JPanel to draw the background image
    private class GameScreenBackgroundPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            } else {
                System.out.println("Background image is null.");
            }
        }
    }
}
