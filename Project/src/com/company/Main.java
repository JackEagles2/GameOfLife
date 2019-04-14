package com.company;


import javax.swing.*;
import java.awt.*;

import java.util.ArrayList;
import java.util.Random;

class MyPanel extends JPanel {
    public void paint(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(10,10,100,100);
    }
}

class GameOfLife {
    public static void main(String[] args) {

    }
    public void Game() {


        int row = 10;
        int col = 10;

        String[][] before = new String[row][col];
        String[][] after = new String[row][col];

        int percent = (int) (col * row * 0.5);
        Random rand = new Random();

        for (int i = 0; i < before.length; i++) {
            for (int j = 0; j < before.length; j++) {
                before[i][j] = "-";
            }
        }

        int n1;
        int n2;

        for (int i = 0; i < percent; i++) {
            n1 = rand.nextInt(row);
            n2 = rand.nextInt(col);
            before[n1][n2] = "C";

        }


        for (int i = 0; i < before.length; i++) {
            for (int j = 0; j < before.length; j++) {
                if (before[i][j].equals("-")) {
                    after[i][j] = "-";
                } else {
                    after[i][j] = "C";
                }
            }
        }


        JFrame frame = new JFrame("Game of Life");
        JPanel p = new JPanel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ArrayList<RectDraw> rects = new ArrayList<RectDraw>();


        frame.setLayout(new GridLayout(0,10));


        frame.setSize(500, 500);
        frame.setVisible(true);

        while (true) {

            for (int i = 0; i < before.length; i++) {
                for (int j = 0; j < before.length; j++) {
                    after[i][j] = Cell(i, j, before, before[i][j]);
                }

            }

            for (int i = 0; i < before.length; i++) {
                for (int j = 0; j < before.length; j++) {
                    before[i][j] = after[i][j];
                }
            }

            for(int i = 0; i < 10; i++){
                for(int j = 0; j<10; j++){
                    if(before[i][j].equals("C")) {
                        rects.add(new RectDraw());
                        rects.get(i).Dims(10, 10, 10, 10, Color.GRAY);
                        frame.add(rects.get(i));
                    }else{
                        rects.add(new RectDraw());
                        rects.get(i).Dims(10, 10, 10, 10, Color.BLACK);
                        frame.add(rects.get(i));
                    }
            }
            }

            frame.repaint();
            for (int i = 0; i < before.length; i++) {
                for (int j = 0; j < before.length; j++) {
                    System.out.print(before[i][j]);
                }
                System.out.println();
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            System.out.println();

        }














    }


    public static String Cell(int a, int b, String[][] before, String p) {

        int count = 0;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                try {
                    if (before[a + i][b + j].equalsIgnoreCase("C") && !(i == 0 && j == 0)) {
                        count++;

                    }
                } catch (Exception e) {

                }
            }
        }


        //System.out.println(a + " " + b +":" +count);
        if (count == 3 && p.equalsIgnoreCase("-"))
            return "C";
        else if ((count == 2 || count == 3) && p.equalsIgnoreCase("C"))
            return "C";
        else if (count > 3 && p.equalsIgnoreCase("C"))
            return "-";
        else if ((count < 2 && p.equalsIgnoreCase("C"))) {
            return "-";
        }
        return "-";


    }
}


public class Main extends JPanel {
    public static void main(String[] args) {
        GameOfLife game = new GameOfLife();
        game.Game();
    }
}

class RectDraw extends JPanel {

    public void Dims(int x, int y, int width, int height, Color Color1){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.Color = Color1;
    }
    public void paintComponent(Graphics g){
        g.setColor(Color);
        g.fillRect(x, y, width, height);
    }
    private int x, y, width, height;
    private Color Color;

  }


