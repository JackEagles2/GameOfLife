import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class GameOfLifeClass {

    public static void main(String[] args) {


        Game();
    }

    public static void Game() {

        int row = 30;
        int col = 100;

        String[][] before = new String[row][col];
        String[][] after = new String[row][col];

        int percent = (int) (col * row * 0.8);
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

        frame.setSize(col*30,row*30);

        //frame.getContentPane().setSize(new Dimension(row*5, col*5));
        //frame.setSize(row*5,col*5);

        frame.setVisible(true);
        boolean con = true;
        while (con) {

            for (int i = 0; i < before.length; i++) {
                for (int j = 0; j < before.length; j++) {
                    after[i][j] = Cell(i, j, before, before[i][j]);
                }

            }

            int count =0;
            for (int i = 0; i < before.length; i++) {
                for (int j = 0; j < before.length; j++)
                    if (before[i][j].equals(after[i][j])) {
                        count++;

                    }
            }

            if(count == 100){
                con =false;
            }

            for (int i = 0; i < before.length; i++) {
                for (int j = 0; j < before.length; j++) {
                    before[i][j] = after[i][j];
                }
            }

            count = 0;
            for (int i = 0; i < before.length; i++) {
                for (int j = 0; j < before.length; j++) {
                    if (before[i][j].equals("C")) {
                        rects.add(new RectDraw());
                        rects.get(count).Dims(10, 10, 30, 30, Color.GRAY);
                        frame.add(rects.get(count));
                    } else {
                        rects.add(new RectDraw());
                        rects.get(count).Dims(10, 10, 30, 30, Color.BLACK);
                        frame.add(rects.get(count));
                    }
                    count++;
                }
            }

            frame.getContentPane().setBackground(Color.BLACK);

            frame.repaint();


            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
            }

        }

        frame.dispose();
        Game();
    }

    public static String Cell(int a, int b, String[][] before, String p) {

        int count = 0;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {

                if (before[(((a + i) % before.length)+before.length)%before.length][(((b + j) % before[0].length)+before[0].length)%before[0].length].equalsIgnoreCase("C") && !(i == 0 && j == 0)) {
                        count++;

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

