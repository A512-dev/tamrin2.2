package com.company;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;

import static java.awt.geom.Point2D.distance;

public class Gameplay extends JPanel implements MouseListener, ActionListener{

    private boolean play = false;

    double brickdirDown;
    Color ballColor;
    private int score = 0;
    public int power = 0;
    String name;
    Colors color;
    Levels level;
    final Border blackBorder = BorderFactory.createLineBorder(Color.black);
    public final int ballDiameter = 20;
    ArrayList<Ball> balls;
    ArrayList<Bricks> rects;
    ArrayList<Item> items;
    //private int totalBricks = 21;

    private Timer timer;

    int ballposX = 120;
    int ballposY = 450;
     //int ballXdir = 0;
     //int ballYdir = 0;

    boolean shart1 = true;
    boolean shart2 = false;
    boolean ended = false;
    int numOfRound = 0;

    public MapGenerator map;
    public Ball ball;
    public ProjectoryLine projectoryLine;



    public Gameplay(String name,Colors color,Levels difficulty) {
        switch (difficulty) {
            case Easy -> brickdirDown = 0.14;
            case Medium -> brickdirDown = 0.20;
            case Hard -> brickdirDown = 0.22;
        }
        switch (color) {
            case Red -> ballColor = Color.red;
            case Blue -> ballColor = Color.blue;
            case Green -> ballColor = Color.green;
        }
        //Panel gamePanel = new JPanel();
        //gamePanel.setFocusable(true);

        map = new MapGenerator(3, 7);
        ball = new Ball(ballposX,ballposY,ballColor);
        updateDatabasePosition();
        power = 1;
//        addKeyListener(this);
        addMouseListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        int delay = 8;
        timer = new Timer(2, this);
        timer.start();
    }


    public void paint(Graphics g) {
        if (!ended)
        {
            //background
            g.setColor(Color.black);
            g.fillRect(1, 1, 692, 592);

            // drawing map
            map.draw((Graphics2D) g);

            // scores
            g.setColor(Color.white);
            g.setFont(new Font("serif", Font.BOLD, 25));
            g.drawString(""+score, 590, 30);

            //power
            g.setColor(Color.white);
            g.setFont(new Font("serif", Font.BOLD, 25));
            g.drawString(""+power, 10, 30);


            // borders
            g.setColor(Color.yellow);
            g.fillRect(0, 0, 3, 592);
            g.fillRect(0 , 0, 692, 3);
            g.fillRect(692, 0, 3, 592);

            // the ball
            ball.drawBalls((Graphics2D) g);
            //        g.setColor(Color.yellow);
//        g.fillOval(ballposX, ballposY, 20, 20);

            if (!shart2 || numOfRound==0)
            {
                projectoryLine = new ProjectoryLine();
                projectoryLine.drawLine((Graphics2D) g);

            }

            if (!shart1)
            {
                play = false;
                ended = true;
                ball.ballposY = 550;
                ball.ballposX = 0;
                ball.ballDirX = 0;
                ball.ballDirY = 0;

                updateDatabasePosition();
                this.removeAll();
                JButton newGame = new JButton("New Game");
                newGame.setBounds(400,500,100,100);
                JButton exit = new JButton("Exit");
                newGame.setBounds(200,500,100,100);
                this.add(newGame);
                this.add(exit);
                g.setColor(Color.red);
                g.setFont(new Font("serif", Font.BOLD, 30));
                g.drawString("Game Over, Scores: "+score, getWidth()/2, getHeight()/2);

                g.setFont(new Font("serif", Font.BOLD, 20));
                g.drawString("Press Enter to Restart ", 230, 350);
            }
            if (ball.ballposY > 561) {
                if (shart2 && !map.reposition())
                {
                    System.out.println("in change of levels");
                    gameOver(g);
                }
                //ballposX = 120;
                ball.ballposY = 551;
                ball.ballDirY = 0;
                ball.ballDirX = 0;
                power++;
                play = false;
                shart2 = false;
                updateDatabasePosition();
                ProjectoryLine projectoryLine = new ProjectoryLine();
                add(projectoryLine);
                //MouseListener.
                //MouseMotionEvent mouseMotionEvent = new MouseMotionEvent();
                //mouseMotionEvent.drawLine(g);
//            ballXdir = Database.getBallXdir();
//            ballYdir = Database.getBallYdir();
                //Scanner scanner = new Scanner(System.in);
                //ballXdir = scanner.nextInt();
                //ballYdir = -scanner.nextInt();
            }

            if (score >= 290) {
                ended = true;
                System.out.println("score>290");
                gameOver(g);
            }
            if (!play)
            {
                for (int i=0; i<map.mapBricks.size(); i++)
                {
                    for (int j=0; j<map.mapBricks.get(i).size(); j++)
                    {
                        map.mapBricks.get(i).get(j).posY += brickdirDown;
                        if (map.mapBricks.get(i).get(j).posY+ map.brickHeight>550 && map.mapBricks.get(i).get(j).posX<700)
                        {
                            ended = true;
                            map.mapBricks.get(i).get(j).printDetails();
                            //System.out.println("in scroll");
                            gameOver(g);
                        }

                    }
                }
            }
            repaint();

        }
        if (ended)
        {
            this.removeAll();
            g.setColor(Color.black);
            g.fillRect(0,0,700,600);
            g.drawRect(0,0,600,700);
            g.setColor(Color.white);
            g.fillRect(100,100,100,100);
            g.fillRect(300,100,100,100);
            g.setColor(Color.black);
            g.drawString("Back",130,150);
            g.drawString("New Game",330,150);
            ended = true;
            play = false;
            ball.ballDirX = 0;
            ball.ballDirY = 0;
            //this.repaint();
            JButton newGame = new JButton("New Game");
            newGame.setBackground(Color.red);
            System.out.println("new game");
            newGame.setBounds(400,500,100,100);
            JButton exit = new JButton("Exit");
            newGame.setBounds(200,500,100,100);
            this.add(newGame);
            this.add(exit);
        }
            //this.removeAll();
        repaint();
        g.dispose();

    }

    private void gameOver(Graphics g) {
        this.removeAll();
        ended = true;
        play = false;
        ball.ballDirX = 0;
        ball.ballDirY = 0;
        //this.repaint();
        JButton newGame = new JButton("New Game");
        newGame.setBackground(Color.red);
        newGame.setBounds(400,500,100,100);
        JButton exit = new JButton("Exit");
        newGame.setBounds(200,500,100,100);
        this.add(newGame);
        this.add(exit);
//        g.setColor(Color.red);
//        g.setFont(new Font("serif", Font.BOLD, 30));
//        g.drawString("You Lost, Scores: "+score, 190, 300);
//
//        g.drawString("jefhwefkwef",200,325);
//        g.setFont(new Font("serif", Font.BOLD, 20));
//        g.drawString("Press Enter to Restart ", 230, 350);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }
    @Override
    public void mouseExited(MouseEvent e) {

    }

    private void updateDatabasePosition() {
        Database.setBallXdir(ball.ballDirX);
        Database.setBallYdir(ball.ballDirY);
        Database.setBallPosX(ball.ballposX);
        Database.setBallPosY(ball.ballposY);
    }


    @Override
    public void actionPerformed (ActionEvent e) {
        if (!ended)
        {
//            if (ball.ballDirX<400 && ball.ballDirY<2)
//                ball.ballDirY = -4;
            boolean collision = false;
            //timer = new Timer(8,this);
            timer.start();
            A: for (int i = 0; i <map.mapBricks.size(); i++) {
                for (int j = 0; j < map.mapBricks.get(i).size(); j++) {
                    if (map.mapBricks.get(i).get(j).valueOf > 0) {
                        double brickX = map.mapBricks.get(i).get(j).posX;
                        double brickY = map.mapBricks.get(i).get(j).posY;
                        int brickWidth = map.brickWidth;
                        int brickHeight = map.brickHeight;

                        Rectangle2D.Double rect = new Rectangle2D.Double(brickX, brickY, brickWidth, brickHeight);
                        Ellipse2D ballRect = new Ellipse2D.Double(ball.ballposX, ball.ballposY, 20, 20);
                        Rectangle2D brickRect = rect;

                        if (ballRect.intersects(rect)) {
                            //System.out.println("aaaaaaaaa");
                            map.mapBricks.get(i).get(j).valueOf -= power;
                            score += power;

                            if (ball.ballposX + 19 <= brickRect.getX() || ball.ballposX + 1 >= brickRect.getX() + brickRect.getWidth()) {
                                ball.ballDirX = -ball.ballDirX;
                            } else {
                                ball.ballDirY = -ball.ballDirY;
                            }
                            break A;
                        }
                    }
                }
            }
            if (shart2) {
                if (numOfRound == 0)
                    numOfRound++;
                ball.ballLastX = ball.ballposX;
                ball.ballLastY = ball.ballposY;
                ball.ballposX += ball.ballDirX;
                ball.ballposY += ball.ballDirY;
                updateDatabasePosition();
                if (ball.ballposX <= 0) {
                    ball.ballDirX = -ball.ballDirX;
                    updateDatabasePosition();
                }
                if (ball.ballposY < 0) {
                    ball.ballDirY = -ball.ballDirY;
                    updateDatabasePosition();
                }
                if (ball.ballposX > 660) {
                    ball.ballDirX = -ball.ballDirX;
                    updateDatabasePosition();
                }
            }
            repaint();
        }
//        A:
//        for (int i = 0; i < map.numRowOfBlocks; i++) {
//            for (int j = 0; j < map.mapBricks.get(i).size(); j++) {
//                if (map.mapBricks.get(i).get(j).posX < 670 && map.mapBricks.get(i).get(j).valueOf > 0) {
//
//                    double brickX = map.mapBricks.get(i).get(j).posX;
//                    double brickY = map.mapBricks.get(i).get(j).posY;
//                    //System.out.println(brickX+"...."+brickY);
//                    int brickWidth = map.brickWidth;
//                    int brickHeight = map.brickHeight;
//
//                    Rect rect = map.mapBricks.get(i).get(j);
//                    //Rectangle ballRect = new Rectangle(ballposX, ballposY, 20, 20);
//                    //Rectangle brickRect = rect;
//                    Rectangle2D.Double brickRect = new Rectangle2D.Double(brickX, brickY, brickWidth, brickHeight);
//                    Ellipse2D.Double circle = new Ellipse2D.Double(ballposX, ballposY, ballDiameter, ballDiameter);
////                    if (ball.ballDirY == 0) {
////                        ball.ballDirY = 1;
////                    }
//
//                    ///////////////
//                    System.out.println(circle.intersects(brickRect));
//                    //9 halat dare
//                    if (ball.ballposX>rect.posX && ball.ballposX<rect.posX+brickWidth)
//                    {
//                        //halat 1
//                        if (ball.ballposY>rect.posY+brickHeight && ball.ballposY+ball.ballDirY<=rect.posY+brickHeight)
//                        {
//                            collision = true;
//                            ball.ballDirY = -ball.ballDirY;
//                        }
//                        //halat 2
//                        if (!collision && ball.ballposY+ballDiameter>rect.posY && ball.ballposY+ballDiameter+ball.ballDirY<=rect.posY)
//                        {
//                            collision = true;
//                            ball.ballDirY = -ball.ballDirY;
//                        }
//                    }
//                    if (!collision && ball.ballposY>rect.posY && ball.ballposY<rect.posY+brickHeight)
//                    {
//                        //halat 1
//                        if (ball.ballposX>rect.posX+brickWidth && ball.ballposY+ball.ballDirY<=rect.posY+brickWidth)
//                        {
//                            collision = true;
//                            ball.ballDirX = -ball.ballDirX;
//                        }
//                        //halat 2
//                        if (!collision && ball.ballposX+ballDiameter<rect.posX && ball.ballposY+ballDiameter+ball.ballDirY<=rect.posY)
//                        {
//                            collision = true;
//                            ball.ballDirX = -ball.ballDirX;
//                        }
//                    }
//                    if (!collision && ball.ballposY>rect.posY+brickHeight && ball.ballposX>rect.posX+brickWidth)
//                    {
//                        if (ball.ballposY+ball.ballDirY<=rect.posY+brickHeight)
//
//                    }
//                    if (circle.intersects(brickRect)) {
//                        System.out.println("krfjwrfklwef");
//                        map.mapBricks.get(i).get(j).valueOf -= power;
//                        if (ballposX + 18 <= brickRect.x || ballposX + 3 - 1 >= brickRect.x + brickRect.width) {
//                            ball.ballDirX = -ball.ballDirX;
//                        } else {
//                            ball.ballDirY = -ball.ballDirY;
//                        }
//                        if (map.mapBricks.get(i).get(j).valueOf <= 0) {
//                            map.mapBricks.get(i).remove(j);
//                            score += rect.originalValue;
//                            j--;
//                        }
//                        updateDatabasePosition();
                        //totalBricks--;
                        //break A;
//                    if (ball.ballDirY > 0 && ball.ballLastY + 10 < (rect.posY) && ball.ballposY + 10 >= (rect.posY)) {
//                        // important
//                        double tempX = (double) (ball.ballDirX / ball.ballDirY) * (rect.posY - (ball.ballLastY + 10)) + ball.ballLastX;
//                        if (tempX >= rect.posX && tempX <= rect.posX + 50) {
//                            ball.ballDirY = (-1) * ball.ballDirY;
//                            ball.ballposY = (int) (2 * (rect.posY - 10) - ball.ballposY);
//                            rect.valueOf -= power;
//                            collision = true;
//                        }
//                    }
//                    if (!collision && ball.ballDirY < 0 && ball.ballLastY - 10 > (rect.posY + 50) && ball.ballposY - 10 <= (rect.posY + 50)) {
//                        // important
//                        double tempX = (double) (ball.ballDirX / ball.ballDirY) * ((rect.posY + 50) - (ball.ballLastY - 10)) + ball.ballLastX;
//                        if (tempX >= rect.posX && tempX <= rect.posX + 50) {
//                            ball.ballDirY = (-1) * ball.ballDirY;
//                            ball.ballposY = (int) (2 * (rect.posY + 60) - ball.ballposY);
//                            rect.valueOf -= power;
//                            collision = true;
//                        }
//                    }
//                    if (!collision && ball.ballDirX > 0 && ball.ballLastX + 10 < (rect.posX) && ball.ballposX + 10 >= (rect.posX)) {
//                        // important
//                        double tempY = (double) (ball.ballDirY / ball.ballDirX) * (rect.posX - (ball.ballLastX + 10)) + ball.ballLastY;
//                        if (tempY >= rect.posY && tempY <= rect.posY + 50) {
//                            ball.ballDirX = (-1) * ball.ballDirX;
//                            ball.ballposX = (int) (2 * (rect.posX - 10) - ball.ballposX);
//                            rect.valueOf -= power;
//                            collision = true;
//                        }
//                    }
//                    if (!collision && ball.ballDirX < 0 && ball.ballLastX - 10 > (rect.posX + 50) && ball.ballposX - 10 <= (rect.posX + 50)) {
//                        // important
//                        double tempY = (double) (ball.ballDirY / ball.ballDirX) * ((rect.posX + 50) - (ball.ballLastX - 10)) + ball.ballLastY;
//                        if (tempY >= rect.posY && tempY <= rect.posY + 50) {
//                            ball.ballDirX = (-1) * ball.ballDirX;
//                            ball.ballposX = (int) (2 * (rect.posX + 60) - ball.ballposX);
//                            rect.valueOf -= power;
//                            collision = true;
//                        }
//                    }
//                    if (!collision && distance(rect.posX, rect.posY, ball.ballposX, ball.ballposY) < 10) {
//                        int temp = ball.ballDirX;
//                        ball.ballDirX = (-1) * ball.ballDirY;
//                        ball.ballDirY = (-1) * temp;
//                        ball.ballposX = ball.ballposX + (int) (ball.ballDirX * (10 - distance(rect.posX, rect.posY, ball.ballposX, ball.ballposY)) / 10);
//                        ball.ballposY = ball.ballposY + (int) (ball.ballDirY * (10 - distance(rect.posX, rect.posY, ball.ballposX, ball.ballposY)) / 10);
//                        rect.valueOf -= power;
//                        collision = true;
//                    }
//                    if (!collision && distance(rect.posX + 50, rect.posY, ball.ballposX, ball.ballposY) < 10) {
//                        int temp = ball.ballDirX;
//                        ball.ballDirX = ball.ballDirY;
//                        ball.ballDirY = temp;
//                        ball.ballposX = ball.ballposX + (int) (ball.ballDirX * (10 - distance(rect.posX + 50, rect.posY, ball.ballposX, ball.ballposY)) / 10);
//                        ball.ballposY = ball.ballposY + (int) (ball.ballDirY * (10 - distance(rect.posX + 50, rect.posY, ball.ballposX, ball.ballposY)) / 10);
//                        rect.valueOf -= power;
//                        collision = true;
//                    }
//                    if (!collision && distance(rect.posX, rect.posY + 50, ball.ballposX, ball.ballposY) < 10) {
//                        int temp = ball.ballDirX;
//                        ball.ballDirX = ball.ballDirY;
//                        ball.ballDirY = temp;
//                        ball.ballposX = ball.ballposX + (int) (ball.ballDirX * (10 - distance(rect.posX, rect.posY + 50, ball.ballposX, ball.ballposY)) / 10);
//                        ball.ballposY = ball.ballposY + (int) (ball.ballDirY * (10 - distance(rect.posX, rect.posY + 50, ball.ballposX, ball.ballposY)) / 10);
//                        rect.valueOf -= power;
//                        collision = true;
//                    }
//                    if (!collision && distance(rect.posX + 50, rect.posY + 50, ball.ballposX, ball.ballposY) < 10) {
//                        int temp = ball.ballDirX;
//                        ball.ballDirX = (-1) * ball.ballDirY;
//                        ball.ballDirY = (-1) * temp;
//                        ball.ballposX = ball.ballposX + (int) (ball.ballDirX * (10 - distance(rect.posX + 50, rect.posY + 50, ball.ballposX, ball.ballposY)) / 10);
//                        ball.ballposY = ball.ballposY + (int) (ball.ballDirY * (10 - distance(rect.posX + 50, rect.posY + 50, ball.ballposX, ball.ballposY)) / 10);
//                        rect.valueOf -= power;
//                    }
//                    if (ball.ballDirY == 0) {
//                        ball.ballDirY = 1;
//                    }
//                    if (rect.valueOf <= 0) {
//                        rects.remove(j);
//                        score += rect.originalValue;
//                        j--;
//
    }
       // repaint();
    @Override
    public void mousePressed(MouseEvent e) {
        Database.setPosXMouseData(e.getX());
        Database.setPosYMouseData(e.getY());
        //System.out.println("MX:"+e.getX()+"......MY"+e.getY());

        //System.out.println(1);
        if (!play)
        {
            double x = (double)(Database.getPosXMouseData()-ball.ballposX)/(double) (Database.getPosYMouseData()-ball.ballposY);
            ball.ballDirY = -Math.sqrt(25/(Math.pow(x,2)+1));
            if ((Database.getPosXMouseData()-ball.ballposX)>0)
                ball.ballDirX = (ball.ballDirY)*(x);
            else if ((Database.getPosXMouseData()-ball.ballposX)<0)
                ball.ballDirX = (ball.ballDirY)*(x);
            else
                ball.ballDirX = 0;
        }
        System.out.println("dirX:"+ball.ballDirX+".......dirY:"+ball.ballDirY);
        //repaint();
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        if (!play)
        {
            shart2 = true;
            play = true;
            //System.out.println("released: play:"+play+".....shart2:"+shart2);
        }
        repaint();
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        //new Game
        if (ended && e.getX()>100 && e.getX()<200 && e.getY()<200 && e.getY()>100)
        {
            Details details = new Details();
            details.name = name;
            details.score = score;
            details.level = level;
            this.removeAll();
            Database.namesData.add(details);
            new MyProgram().run();
            //detail.time = timePast;
        }
        if (ended &&e.getX()>300 && e.getX()<400 && e.getY()<200 && e.getY()>100)
        {
            Details details = new Details();
            details.name = name;
            details.score = score;
            details.level = level;
            Database.namesData.add(details);
            this.removeAll();
            play = false;
            ended = false;
            shart2 = true;
            shart1 = true;
            score = 0;
            map = new MapGenerator (3, 7);
            repaint();
            // if (!play) {
//                if (!shart2)
//                {
//
//                }
//                play = true;
//                shart1 = true;
//                shart2 = true;
//                ballposX = 220;
//                ballposY = 450;
//                ballXdir = Database.getBallXdir();
//                ballYdir = Database.getBallYdir();
//                playerX = 310;
//                score = 0;
//                //totalBricks = 21;
//                map = new MapGenerator (3, 7);
//
//                repaint();
//            }
//        }
            //detail.time = timePast;
        }

        //System.out.println(",wefjqefkwelfwelfwefwef;wef;wfe;");

    }


//    @Override
//    public void keyReleased (KeyEvent e) {}
//
//    @Override
//    public void keyTyped (KeyEvent e) {}
//
//    @Override
//    public void keyPressed (KeyEvent e) {
//        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
//            if (playerX >= 600) {
//                playerX = 600;
//            } else {
//                moveRight();
//            }
//        }
//        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
//            if (playerX < 10) {
//                playerX = 10;
//            } else {
//                moveLeft();
//            }
//        }
//
//        if(e.getKeyCode() == KeyEvent.VK_ENTER) {
//            if (!play) {
//                if (!shart2)
//                {
//
//                }
//                play = true;
//                shart1 = true;
//                shart2 = true;
//                ballposX = 220;
//                ballposY = 450;
//                ballXdir = Database.getBallXdir();
//                ballYdir = Database.getBallYdir();
//                playerX = 310;
//                score = 0;
//                //totalBricks = 21;
//                map = new MapGenerator (3, 7);
//
//                repaint();
//            }
//        }
//
//    }
//    public void moveRight() {
//        play = true;
//        playerX += 20;
//    }
//    public void moveLeft() {
//        play = true;
//        playerX -= 20;
//    }


    public MapGenerator getMap() {
        return map;
    }


}