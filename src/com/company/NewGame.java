package com.company;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class NewGame extends JFrame {
    SoundManager soundManager;
    String namePlayer;
    Colors color;
    Levels difficulty;
    public NewGame(String name,Colors color,Levels difficulty) {
        this.namePlayer = name;
        this.color = color;
        this.difficulty = difficulty;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((screen.getWidth() - getWidth()) /2);
        int y = (int) ((screen.getHeight() -getHeight()) /2);
        JFrame frame = new JFrame();
        Database database = new Database();
        Gameplay gamePlay = new Gameplay(name,color,difficulty);
        frame.setBounds(x/2,y/4,700,600);
        //getContentPane().setLayout(new BoxLayout (this.getContentPane(), BoxLayout.Y_AXIS));
        frame.setVisible(true);
        frame.setTitle("Breakout Ball");
        frame.setResizable(false);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        if (gamePlay.ended)
        {
            frame = new JFrame();
            frame.dispose();
            frame.remove(gamePlay);
            frame.removeAll();
            JButton newGame = new JButton("New Game");
            System.out.println("jkwefwfk");
            newGame.setBounds(400,500,100,100);
            JButton exit = new JButton("Exit");
            newGame.setBounds(200,500,100,100);
            this.add(newGame);
            this.add(exit);

        }
        if (!gamePlay.ended)
        {
            System.out.println("kwefh");
            frame.add(gamePlay);
        }
        /*Button buttonPlay = new Button ("play Game");
        Button buttonInfo = new Button("info");
        Button buttonExit = new Button("exit");
        add(buttonPlay);
        add(buttonInfo);
        add(buttonExit);
*/
        //setLayout (new BoxLayout (this, BoxLayout.X_AXIS));  //sets the layout by mentioning the axis
        //setSize(400,400);
        //sets the width and height of the frame





       // run();
    }

}
