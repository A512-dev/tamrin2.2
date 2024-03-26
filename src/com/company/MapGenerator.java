package com.company;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class MapGenerator extends JPanel {
    public int numRowOfBlocks =2;
    ArrayList<ArrayList<Details>> mapBricks = new ArrayList<>(3);
    public final int brickWidth = 540/3;
    public final int brickHeight = 150/3;
    public int[] numBricksInRow = new int[8];
    public MapGenerator(int row, int col) {
        ArrayList<Details> d = new ArrayList<>();
        int k=0;
        for (int l=0; l<2; l++)
        {
            for (int i=0; i<8; i++) {
                Details detail1 = new Details();
                d.add(detail1);
            }
            mapBricks.add(d);
            d = new ArrayList<>();
        }

        //map = new Details[row][col];

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < mapBricks.get(i).size(); j++) {
                mapBricks.get(i).set(j, new Details());
                //mapBricks.get(i).get(j).valueOf = ThreadLocalRandom.current().nextInt(1, 40 + 1);
                if (j==0)
                {
                    mapBricks.get(i).get(j).posX = ThreadLocalRandom.current().nextInt(1, 670-brickWidth);
                    //numBricksInRow[i]++;
                }
                else if (mapBricks.get(i).get(j-1).posX + 2*brickWidth<670)
                {
                    mapBricks.get(i).get(j).posX = ThreadLocalRandom.current().nextInt(
                            mapBricks.get(i).get(j-1).posX+brickWidth+1, 670-brickWidth);
                    //numBricksInRow[i]++;
                }
                else
                {
                    mapBricks.get(i).get(j).posX = 700;
                }
                mapBricks.get(i).get(j).posY = i * brickHeight + 50;
            }
           // System.out.println(numBricksInRow[i]);
        }
        for (int i = 0; i< mapBricks.size(); i++)
        {
            for (int j = 0; j< mapBricks.get(i).size(); j++)
            {
                mapBricks.get(i).get(j).valueOf = ThreadLocalRandom.current().nextInt(1, 40 + 1);
                mapBricks.get(i).get(j).printDetails();
            }
            System.out.println();
        }
    }
    public void draw(Graphics2D g) {
        for (int i = 0; i < numRowOfBlocks; i++) {
            for (int j = 0; j < mapBricks.get(i).size(); j++) {
                if (mapBricks.get(i).get(j).valueOf > 0 && mapBricks.get(i).get(j).posX+brickWidth<669) {
                    g.setColor(Color.blue);
                    g.fillRect(mapBricks.get(i).get(j).posX, mapBricks.get(i).get(j).posY, brickWidth,
                            brickHeight);
                    //mapBricks.get(i).get(j).printDetails();
                    g.setColor(Color.white);
                    g.setFont(new Font("TimesRoman", Font.PLAIN, 12));
                    g.drawString(String.valueOf(mapBricks.get(i).get(j).valueOf), mapBricks.get(i).get(j).posX+ brickWidth/2,
                            mapBricks.get(i).get(j).posY+brickHeight/2);
                    g.setStroke(new BasicStroke(5));
                    g.setColor(Color.red);
                    g.drawRect(mapBricks.get(i).get(j).posX, mapBricks.get(i).get(j).posY, brickWidth,
                            brickHeight);
                }
            }
        }
    }
    public void setBrickValue(int value, int row, int col) {
        System.out.println(mapBricks.get(row).get(col).valueOf);
        mapBricks.get(row).get(col).valueOf--;
        System.out.println(mapBricks.get(row).get(col).valueOf);
    }

    public boolean reposition() {
        boolean javab =true;
        for (int i = mapBricks.size()-1; i >=0 ; i--) {
            for (int j = 0; j< mapBricks.get(i).size(); j++) {
                mapBricks.get(i).get(j).posY += brickHeight+10;
                if (mapBricks.get(i).get(j).posY>510)
                {
                    javab = false;
                }
            }
        }
        addNewRow();
        for (int i = 0; i< mapBricks.size(); i++)
        {
            for (int j = 0; j< mapBricks.get(i).size(); j++)
            {
                mapBricks.get(i).get(j).printDetails();
            }
            System.out.println();
        }
        return javab;
    }
    void addNewRow() {
        ArrayList<Details> newRow = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            newRow.add(new Details());
            if (i == 0)
                newRow.get(i).posX = ThreadLocalRandom.current().nextInt(1, 670 - brickWidth);
            else if (newRow.get(i - 1).posX + 2 * brickWidth < 670)
                newRow.get(i).posX = ThreadLocalRandom.current().nextInt(
                        newRow.get(i - 1).posX + brickWidth + 1, 670 - brickWidth);
            else
                newRow.get(i).posX = 700;
            newRow.get(i).posY = 50;
            newRow.get(i).valueOf = ThreadLocalRandom.current().nextInt(1, 40 + 1);
            //newRow.get(i).printDetails();
        }
        mapBricks.add(newRow);
        numRowOfBlocks++;
//        System.out.println();
//        System.out.println(mapBricks.size());
//        mapBricks.add(newRow);
//
//        System.out.println(mapBricks.size());
    }
            //mapBricks.get(i).get(j).valueOf = ThreadLocalRandom.current().nextInt(1, 40 + 1);
            //mapBricks.get(i).get(j).printDetails();
        // }
}
