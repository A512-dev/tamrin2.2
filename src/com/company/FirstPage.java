package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FirstPage extends JFrame {
    public FirstPage(){
            /*JFrame frame = new JFrame();
            Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
            int x = (int) ((screen.getWidth() - getWidth()) /2);
            int y = (int) ((screen.getHeight() -getHeight()) /2);
            frame.setBounds(x/2,y/4,700,600);
        getContentPane().setLayout(new BoxLayout (this.getContentPane(), BoxLayout.Y_AXIS));

            frame.setTitle("Breakout Ball");
            frame.setResizable(true);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Button buttonPlay = new Button ("play Game");
            Button buttonInfo = new Button("info");
            Button buttonExit = new Button("exit");
            frame.add(buttonPlay);
            frame.add(buttonInfo);
            frame.add(buttonExit);

        frame.setVisible(true);*/
        JFrame frame = new JFrame();
        frame.setTitle("Breakout Ball");
        frame.setResizable(false);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((screen.getWidth() - getWidth()) /2);
        int y = (int) ((screen.getHeight() -getHeight()) /2);
        frame.setBounds(x/2,y/4,700,600);
        frame.setSize(700, 600); // Set an appropriate size
        frame.getContentPane().setBackground(Color.black);

        // Create five buttons
        JButton buttonNewGame = new JButton("New Game");
        JButton buttonHistory = new JButton("History");
        JButton buttonExit = new JButton("Exit");
        buttonNewGame.setPreferredSize(new Dimension(500, 180));
        buttonHistory.setPreferredSize(new Dimension(500, 180));
        buttonExit.setPreferredSize(new Dimension(500, 180));
        buttonNewGame.setBackground(Color.cyan);
        buttonHistory.setBackground(Color.cyan);
        buttonExit.setBackground(Color.cyan);

        // Set layout manager (FlowLayout or GridLayout)
        frame.setLayout(new FlowLayout()); // Alternatively, use GridLayout(1, 5)

        // Add buttons to the frame
        frame.add(buttonNewGame);
        buttonNewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        frame.add(buttonHistory);
        frame.add(buttonExit);
        frame.setVisible(true);
    }
}
