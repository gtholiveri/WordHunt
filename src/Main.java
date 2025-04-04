/*
Emeric Chang, Gabriel Oliveri, Kyle Chen
AP CS
Final Project: Word Hunt
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Dictionary dictionary = new Dictionary();
        try {
            dictionary.read("dictionary.txt");
            //will need to put dictionary file in here
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        }

        String searchWord = "hello";
        //once we make a method that gets user input as a word, we can use this
        if (dictionary.searchWord(searchWord)) {
            //points += 400 or something
            return;
        }
        new GUI(500, 500);
    }
}

class GUI {
    private JFrame frame;
    private JButton[][] buttons;
    private Random random = new Random();
    public GUI(int width, int height){
        // Creates frame with specified name and dimensions
        frame = new JFrame("Word Hunt");
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
                buttons[i][j] = new JButton(generateRandomLetter());
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 40));
                // ButtonClickListener is a nested class of Homework18
                buttons[i][j].addActionListener(new ButtonClickListener(i, j, buttons, random));
                panel.add(buttons[i][j]);
            }
        }
        // Adds panel and status to frame
        // Sets the frame to be visible
        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }
    private String generateRandomLetter() {
        char letter = (char) ('A' + random.nextInt(26));
        return String.valueOf(letter);
    }
    private class ButtonClickListener implements ActionListener {
        private int row, col;
        private JButton[][] buttons;
        private Random random;

        public ButtonClickListener(int row, int col, JButton[][] buttons, Random random) {
            this.row = row;
            this.col = col;
            this.buttons = buttons;
            this.random = random;
        }

        public void actionPerformed(ActionEvent e) {

        }
    }
}
class Dictionary {
    ArrayList<String> words = new ArrayList<>();
    public void read(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("src/" + filename));
        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            words.add(line);
        }
        scanner.close();
    }
    public int binarySearchWord(String target, int lo, int hi) {
        if (lo > hi) {
            return -1;
        }
        int mid = (lo + hi) / 2;
        int comparison = words.get(mid).compareTo(target);
        if (comparison == 0) {
            return mid;
        } else if (comparison < 0) {
            return binarySearchWord(target, mid + 1, hi);
        } else {
            return binarySearchWord(target, lo, mid - 1);
        }
    }
    public boolean searchWord(String target) {
        return (binarySearchWord(target, 0, words.size() - 1) != -1);
    }
}