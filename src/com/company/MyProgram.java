package com.company;

import com.sun.security.auth.module.JndiLoginModule;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

enum Levels {
    Easy,Medium,Hard
}
enum Colors {
    Blue,Red,Green
}

public class MyProgram implements Runnable {
    JPanel mainPanel;
    SoundManager soundManager;
    Levels level = Levels.Easy;
    Colors ballColor = Colors.Red;
    String namePlayer = "aa";
    JFrame frame;
    JPanel getPanel() {
        return mainPanel;
    }

    public MyProgram(){
        frame = new JFrame();
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(400,100,700,600);
        frame.setTitle("Breakout Ball");
        mainPanel = (JPanel)frame.getContentPane();

        //frame.setSize(700, 600); // Set an appropriate size
        //frame.getContentPane().setBackground(Color.yellow);


        // Set layout manager (FlowLayout or GridLayout)

    }

    private void initMenu() {
        System.out.println("efhafkafj");
        mainPanel.removeAll();
        //mainPanel.setBackground(Color.blue);
        mainPanel = (JPanel) frame.getContentPane();
        //mainPanel.setBounds(400,100,700,600);
        mainPanel.setVisible(true);
        // Create five buttons
        JButton buttonNewGame = new JButton("New Game");
        JButton buttonSetting = new JButton("Settings");
        JButton buttonHistory = new JButton("History");
        JButton buttonExit = new JButton("Exit");
        buttonNewGame.setPreferredSize(new Dimension(400, 120));
        buttonSetting.setPreferredSize(new Dimension(400,120));
        buttonHistory.setPreferredSize(new Dimension(400, 120));
        buttonExit.setPreferredSize(new Dimension(400, 120));
        buttonNewGame.setBackground(Color.cyan);
        buttonSetting.setBackground(Color.cyan);
        buttonHistory.setBackground(Color.cyan);
        buttonExit.setBackground(Color.cyan);

        mainPanel.setLayout(new FlowLayout()); // Alternatively, use GridLayout(1, 5)

        // Add buttons to the frame
        mainPanel.add(buttonNewGame);
        buttonNewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.removeAll();
                goToChoosingInfoPanel();
                mainPanel.revalidate();
                mainPanel.repaint();
                //new NewGame();
            }
        });
        mainPanel.add(buttonSetting);
        buttonSetting.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.removeAll();
                goToSettingPanel();
                mainPanel.revalidate();
                mainPanel.repaint();
            }
        });
        mainPanel.add(buttonHistory);
        buttonHistory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.removeAll();
                goToHistoryPanel();
                mainPanel.revalidate();
                mainPanel.repaint();
            }
        });
        mainPanel.add(buttonExit);
        buttonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        mainPanel.setVisible(true);
    }

    private void goToChoosingInfoPanel() {
        mainPanel.setLayout(null);
        //mainPanel.removeAll();
//        MyPanels infoPanel = new MyPanels();
//        infoPanel.setVisible(true);
//        infoPanel.setLayout(null);
//        JLabel difficultyT = new JLabel("Difficulty");
//        difficultyT.setBounds(320,20,520,520);
//        infoPanel.add(difficultyT);
//        JPanel newGamePanel = new JPanel();
//        newGamePanel.setLayout(null);
//        newGamePanel.setVisible(true);
//        newGamePanel.setBackground(Color.blue);
//        mainPanel.add(newGamePanel);
        //difficulty
        addLevel();
        addColor();
        addName();
        JButton back = new JButton("Back");
        back.setBackground(Color.pink);
        back.setBounds(150, 430, 90, 90);
        mainPanel.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.removeAll();
                initMenu();
                mainPanel.revalidate();
                mainPanel.repaint();
            }
        });
        JButton start = new JButton("Start");
        start.setBackground(Color.pink);
        start.setBounds(350, 430, 90, 90);
        mainPanel.add(start);
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.removeAll();
                frame.dispose();
                NewGame newGame = new NewGame(namePlayer,ballColor,level);
            }
        });
        System.out.println("1");
    }

    private void addName() {
        JPanel namePanel = new JPanel();
        namePanel.setLayout(null);
        //mainPanel.add(namePanel);
        JLabel name = new JLabel("Name : ");
        name.setBounds(50, 230, 100, 90);
        name.setBackground(Color.green);
        //namePanel.add(name);
        mainPanel.add(name);

        TextArea nameTextArea = new TextArea("enter your name");
        nameTextArea.setBounds(150, 260, 300, 40);
        //namePanel.add(nameTextArea);
        mainPanel.add(nameTextArea);


        JButton submit = new JButton("Submit");
        submit.setBackground(Color.pink);
        submit.setBounds(500, 230, 90, 90);
        //namePanel.add(submit);
        mainPanel.add(submit);
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                namePlayer = nameTextArea.getText();
                System.out.println(namePlayer);
            }
        });
        //namePanel.setBackground(Color.red);


    }

    private void addColor() {
        JLabel color = new JLabel("Color : ");
        color.setBounds(50,130,100,90);
        color.setBackground(Color.green);
        mainPanel.add(color);
        //
        JButton blue = new JButton("Blue");
        blue.setBackground(Color.pink);
        blue.setBounds(150,130,90,90);
        mainPanel.add(blue);
        blue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ballColor = Colors.Blue;
            }
        });
        JButton red = new JButton("Red");
        red.setBackground(Color.pink);
        red.setBounds(250,130,90,90);
        mainPanel.add(red);
        red.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ballColor = Colors.Red;
            }
        });
        JButton green = new JButton("Green");
        green.setBackground(Color.pink);
        green.setBounds(350,30,90,90);
        mainPanel.add(green);
        green.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ballColor = Colors.Green;
            }
        });
    }

    private void addLevel() {
        JLabel levelLabel = new JLabel("Difficulty : ");
        levelLabel.setBounds(50,30,100,90);
        levelLabel.setBackground(Color.green);
        mainPanel.add(levelLabel);
        //
        JButton easy = new JButton("Easy");
        easy.setBackground(Color.pink);
        easy.setBounds(150,30,90,90);
        mainPanel.add(easy);
        easy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                level = Levels.Easy;
            }
        });
        JButton medium = new JButton("Medium");
        medium.setBackground(Color.pink);
        medium.setBounds(250,30,90,90);
        mainPanel.add(medium);
        medium.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                level = Levels.Medium;
            }
        });
        JButton hard = new JButton("Hard");
        hard.setBackground(Color.pink);
        hard.setBounds(350,30,90,90);
        mainPanel.add(hard);
        hard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                level = Levels.Hard;
            }
        });
    }

    private void goToHistoryPanel() {
    }



    private void goToSettingPanel() {


    }

    @Override
    public void run() {
        initMenu();
        soundManager = new SoundManager();
        soundManager.play();
    }

//    @Override
//    public void run() {
//
//    }
}
