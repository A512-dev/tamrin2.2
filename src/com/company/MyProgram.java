package com.company;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MyProgram extends JFrame {
    public MyProgram() {
        new FirstPage();
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((screen.getWidth() - getWidth()) /2);
        int y = (int) ((screen.getHeight() -getHeight()) /2);
        /*JFrame frame = new JFrame();
        Gameplay gamePlay = new Gameplay();
        frame.setBounds(x/2,y/4,700,600);
        getContentPane().setLayout(new BoxLayout (this.getContentPane(), BoxLayout.Y_AXIS));
        frame.setVisible(true);
        frame.setTitle("Breakout Ball");
        frame.setResizable(false);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Button buttons[];  //Button reference variable
        buttons = new Button [5];
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




        //frame.add(gamePlay);
       // run();
    }

    public void run(){
//        JFrame frame = new JFrame();
////        frame.setBounds(10, 10, 700, 600);
////        frame.setTitle("Breakout Ball");
////        frame.setResizable(false);
//
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//







        //





    }
}