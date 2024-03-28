package com.company;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class MapGenerator extends JPanel {
    final Border blackBorder = BorderFactory.createLineBorder(Color.black);
    public int numRowOfBlocks =2;
    ArrayList<ArrayList<Rect>> mapBricks = new ArrayList<>(3);
    public final int brickWidth = 540/3;
    public final int brickHeight = 150/3;
    public int[] numBricksInRow = new int[8];
    public MapGenerator(int row, int col) {
        ArrayList<Rect> d = new ArrayList<>();
        int k=0;
        for (int l=0; l<2; l++)
        {
            for (int i=0; i<8; i++) {
                Rect brick1 = new Rect(0,0,0);
                d.add(brick1);
            }
            mapBricks.add(d);
            d = new ArrayList<>();
        }

        //map = new Details[row][col];

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < mapBricks.get(i).size(); j++) {
                mapBricks.get(i).set(j, new Rect(0,0,0));
                //mapBricks.get(i).get(j).valueOf = ThreadLocalRandom.current().nextInt(1, 40 + 1);
                if (j==0)
                {
                    mapBricks.get(i).get(j).posX = ThreadLocalRandom.current().nextInt(1, 670-brickWidth);
                    //numBricksInRow[i]++;
                }
                else if (mapBricks.get(i).get(j-1).posX + 2*brickWidth<670)
                {
                    mapBricks.get(i).get(j).posX = ThreadLocalRandom.current().nextInt(
                            (int) mapBricks.get(i).get(j-1).posX+(brickWidth+1), 670-brickWidth);
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
        Database.setMapOfBricksData(mapBricks);
    }
    public void draw(Graphics2D g) {
        for (int i = 0; i < numRowOfBlocks; i++) {
            for (int j = 0; j < mapBricks.get(i).size(); j++) {
                if (mapBricks.get(i).get(j).valueOf > 0 && mapBricks.get(i).get(j).posX+brickWidth<669) {
                    g.setColor(Color.DARK_GRAY);
                    g.fillRect((int) mapBricks.get(i).get(j).posX,(int) mapBricks.get(i).get(j).posY, brickWidth,
                            brickHeight);
                    //mapBricks.get(i).get(j).printDetails();
                    g.setColor(Color.white);
                    g.setFont(new Font("TimesRoman", Font.PLAIN, 12));
                    g.drawString(String.valueOf(mapBricks.get(i).get(j).valueOf), (int) mapBricks.get(i).get(j).posX+ brickWidth/2,
                            (int) mapBricks.get(i).get(j).posY+brickHeight/2);
                    g.setStroke(new BasicStroke(1));
                    g.setColor(Color.YELLOW);
                    g.drawRect((int) mapBricks.get(i).get(j).posX, (int) mapBricks.get(i).get(j).posY, brickWidth,
                            brickHeight);
                }
            }
        }
    }
    public void setBrickValue(int row, int col) {
        //System.out.println(mapBricks.get(row).get(col).valueOf);
        mapBricks.get(row).get(col).valueOf--;
        //System.out.println(mapBricks.get(row).get(col).valueOf);
    }

    public boolean reposition() {
        boolean javab =true;
        for (int i = mapBricks.size()-1; i >=0 ; i--) {
            for (int j = 0; j< mapBricks.get(i).size(); j++) {
                mapBricks.get(i).get(j).posY += brickHeight+1;
                if (mapBricks.get(i).get(j).posY+brickHeight>600 && mapBricks.get(i).get(j).valueOf>0)
                {
                    javab = false;
                }
            }
        }
        addNewRow();
        /*for (int i = 0; i< mapBricks.size(); i++)
        {
            for (int j = 0; j< mapBricks.get(i).size(); j++)
            {
                mapBricks.get(i).get(j).printDetails();
            }
            System.out.println();
        }*/
        return javab;
    }
    void addNewRow() {
        ArrayList<Rect> newRow = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            newRow.add(new Rect(0,0,0));
            if (i == 0)
                newRow.get(i).posX = ThreadLocalRandom.current().nextInt(1, 670 - brickWidth);
            else if (newRow.get(i - 1).posX + 2*brickWidth < 671)
                newRow.get(i).posX = ThreadLocalRandom.current().nextInt(
                        (int) newRow.get(i - 1).posX + brickWidth + 1, 670 - brickWidth);
            else
                newRow.get(i).posX = 700;
            newRow.get(i).posY = 50;
            newRow.get(i).valueOf = ThreadLocalRandom.current().nextInt(1, 40 + 1);
            //newRow.get(i).printDetails();
        }
        mapBricks.add(newRow);
        Database.setMapOfBricksData(mapBricks);
        numRowOfBlocks++;
    }
    //mapBricks.get(i).get(j).valueOf = ThreadLocalRandom.current().nextInt(1, 40 + 1);
            //mapBricks.get(i).get(j).printDetails();
        // }
}
