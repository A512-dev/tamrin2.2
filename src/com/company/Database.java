package com.company;
import java.util.ArrayList;
class Details {
    String name;
    int score;
    int time;
    Levels level;
}
public class Database {
      public static ArrayList<ArrayList<Rect>> MapOfBricksData = new ArrayList<>();
      public static ArrayList<ArrayList<Rect>> getMapOfBricksData() {
         return MapOfBricksData;
     }
      static void setMapOfBricksData(ArrayList<ArrayList<Rect>> mapOfBricksData) {
         MapOfBricksData = mapOfBricksData;
     }
      static int ballPosXData;
      static int ballPosYData;
      static int ballXdirData;
      static int ballYdirData;
      static int posXMouseData;
      static int posYMouseData;
      static ArrayList<Details> namesData = new ArrayList<>();
    public static int getPosXMouseData() {
        return posXMouseData;
    }

    public static void setPosXMouseData(int posXMouseData) {
        Database.posXMouseData = posXMouseData;
    }

    public static int getPosYMouseData() {
        return posYMouseData;
    }

    public static void setPosYMouseData(int posYMouseData) {
        Database.posYMouseData = posYMouseData;
    }

    public static int getBallPosXData() {
        return ballPosXData;
    }

    public static void setBallPosXData(int ballPosXData) {
        Database.ballPosXData = ballPosXData;
    }

    public static int getBallPosX() {
        return ballPosXData;
    }

    public static void setBallPosX(int ballPosX) {
        Database.ballPosXData = ballPosX;
    }

    public static int getBallPosY() {
        return ballPosYData;
    }

    public static void setBallPosY(int ballPosY) {
        Database.ballPosYData = ballPosY;
    }

    public static int getBallXdir() {
        return ballXdirData;
    }

    public static void setBallXdir(int ballXdir) {
        Database.ballXdirData = ballXdir;
    }

    public static int getBallYdir() {
        return ballYdirData;
    }

    public static void setBallYdir(int ballYdir) {
        Database.ballYdirData = ballYdir;
    }
}
