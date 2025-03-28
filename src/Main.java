/*
Emeric Chang, Gabriel Oliveri, Kyle Chen
AP CS
Final Project: Word Hunt
 */

import java.awt.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        System.out.print("Hello world");
        new GUI(500, 500);
    }
}

class GUI {
    private JFrame frame;
    private JButton[][] buttons;

    public GUI(int width, int height){
        // Creates frame with specified name and dimensions
        frame = new JFrame("Tic-Tac-Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width,height);
        frame.setMinimumSize(new Dimension(200,200));
        // Creates a panel with a grid format
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4,4));
        buttons = new JButton[4][4];
        // Creates a button for each section of the panel with a specific action
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 40));
                // ButtonClickListener is a nested class of Homework18
                buttons[i][j].addActionListener(new ButtonClickListener(i, j));
                panel.add(buttons[i][j]);
            }
        }
        // Adds panel and status to frame
        // Sets the frame to be visible
        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        private int row, col;
        // Constructor that takes in an integer number of rows and columns
        public ButtonClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }
        public void actionPerformed(ActionEvent e) {
            if (!buttons[row][col].getText().equals("")) {
                return; // Prevents overwriting a square after it's already been clicked
            }

        }
    }
}