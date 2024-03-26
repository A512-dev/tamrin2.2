package com.company;
import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class MapGenerator extends JPanel {
    public Details[][] map;
    public int brickWidth;
    public int brickHeight;
    public MapGenerator(int row, int col) {
        map = new Details[row][col];
        brickWidth = 540/col;
        brickHeight = 150/row;

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = new Details();
                map[i][j].valueOf = ThreadLocalRandom.current().nextInt(1, 40 + 1);
                map[i][j].posX = j * brickWidth + 80;
                map[i][j].posY = i * brickHeight + 50;
            }
        }
    }
    public void draw(Graphics2D g) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j].valueOf > 0) {
                    g.setColor(Color.blue);
                    g.fillRect(map[i][j].posX, map[i][j].posY, brickWidth,
                            brickHeight);
                    g.setColor(Color.white);
                    g.setFont(new Font("TimesRoman", Font.PLAIN, 12));
                    g.drawString(String.valueOf(map[i][j].valueOf),map[i][j].posX+ brickWidth/2, map[i][j].posY+brickHeight/2);



                    g.setStroke(new BasicStroke(5));
                    g.setColor(Color.red);
                    g.drawRect(map[i][j].posX, map[i][j].posY, brickWidth,
                            brickHeight);
                }
            }
        }
    }
    public void setBrickValue(int value, int row, int col) {
        map[row][col].valueOf--;
    }

    public boolean reposition() {
        boolean javab =true;
        for (int i = map.length-1; i >=0 ; i--) {
            for (int j =0; j<map[0].length; j++) {
                map[i][j].posY += 40;
                if (map[i][j].posY>510)
                {
                    javab = false;
                }
            }
        }
        return javab;
    }
}
