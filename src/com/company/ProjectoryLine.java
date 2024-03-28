package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.text.spi.BreakIteratorProvider;


public class ProjectoryLine extends JPanel implements MouseMotionListener, MouseListener {
    public static int ballPosXM = Database.getBallPosX();
    public static int ballPosYM = Database.getBallPosY();
    public static int ballXDirM = Database.getBallXdir();
    public static int ballYDirM = Database.getBallYdir();
    static int posXMouse = Database.getPosXMouseData();
    static int posYMouse = Database.getPosYMouseData();
    public ProjectoryLine(){
        addMouseListener(this);
    }
//    public MouseMotionEvent() {
//        addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                super.mouseClicked(e);
//            }
//            @Override
//            public void mouseDragged (MouseEvent e){
//
//            }
//        });
//
//    }
   /* @Override
    public void mouseDragged (MouseEvent e){

    }

    /*@Override
    public void mouseMoved (MouseEvent e){
        ballPosXM = Database.getBallPosX();
        ballPosYM = Database.getBallPosY();
        ballXDirM = e.getX();
        ballYDirM = e.getY();
        Database.setBallXdir(ballXDirM);
        Database.setBallYdir(ballYDirM);
    }*/
void drawLine(Graphics2D g){
        ballPosXM = Database.getBallPosX();
        ballPosYM = Database.getBallPosY();
        ballXDirM = Database.getBallXdir();
        ballYDirM = Database.getBallYdir();
        posXMouse = Database.getPosXMouseData();
        posYMouse = Database.getPosYMouseData();
        Database.ballXdirData = (posXMouse-ballPosXM);
        Database.ballYdirData = (posYMouse-ballPosYM);
        g.setColor(Color.white);
         //g.fillOval(10 , 33, 45, 5);
        //System.out.println(posXMouse+" ,,"+posYMouse);
        if(posYMouse-ballPosYM<0)
        {
            //System.out.println(10000);
            if (posXMouse-ballPosXM>0)
            {
                //System.out.println("jewefkwefhwefk");
                for (int i=8; i>0; i--)
                    g.fillOval( (posXMouse-ballPosXM)/i + ballPosXM, (posYMouse-ballPosYM)/i+ballPosYM, 5, 5);
                //g.fillOval(10 + ballPosXM, -20+ballPosYM, 5, 5);
                    //g.fillOval(ballPosXM +10*i, (ballPosYM - (posYMouse-ballYDirM)/(posXMouse-ballXDirM), 5, 5);
            }
            else if (posXMouse-ballXDirM<0)
            {
                for (int i=1; i<8; i++)
                    g.fillOval((posXMouse-ballPosXM)/i + ballPosXM, (posYMouse-ballPosYM)/i+ballPosYM, 5, 5);
            }
            else
            {
                for (int i=1; i<8; i++)
                    g.fillOval((posXMouse-ballPosXM)/i + ballPosXM, (posYMouse-ballPosYM)/i+ballPosYM, 5, 5);
            }
        }
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
