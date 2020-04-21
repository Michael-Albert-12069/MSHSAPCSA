package com.company;
import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageTranscoder;
import javax.swing.*;

public class Main extends JPanel {
    public static int screenHeight = 200;
    public static int screenWidth = 500;
    public static CellPanel cells;

    //Draws whatever you want.
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for (int i = 0; i < cells.cells.length; i++) {
            for (int j = 0; j < cells.cells[i].length; j++) {
                boolean cell = cells.cells[i][j];
                if (cells.cells[i][j]){
                    g.setColor(Color.BLACK);
                    g.drawRect(j, i, 1, 1);
                }else{
                    g.setColor(Color.GRAY);
                    g.drawRect(j, i, 1, 1);
                }
                cells.cells[i][j] = cell;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        cells = new CellPanel(screenWidth, 200);
        cells.makeRandomActive();
        cells.makeRandomActive();
        cells.makeRandomActive();


        JFrame jf = new JFrame("Cell Spread");
        Main m = new Main();
        jf.setSize(screenWidth,screenHeight + 50);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.add(m);
        m.repaint();
        Thread.sleep(1000);
        while (true){
            cells.age();
            m.repaint();
            Thread.sleep(20);
        }

    }


}
