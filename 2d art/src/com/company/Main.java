package com.company;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.*;

public class Main extends JPanel {
    public static int screenHeight = 750;
    public static int screenWidth = 1000;
    public static PhysObject object;


    //Draws whatever you want.
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillOval(object.x, object.y, object.blockHeight, object.blockHeight);

    }

    public static void main(String[] args) throws InterruptedException {
        object = new PhysObject(100, 0);
        object.setBlockHeight(25);
        JFrame jf = new JFrame("Cell Spread");
        Main m = new Main();
        jf.setSize(screenWidth,screenHeight + 50);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.add(m);
        m.repaint();
        Thread.sleep(1000);
        object.velocity = 1;
        while (object.velocity != 0) {
            while (object.y + object.blockHeight * 2 + (object.time * object.gravity) < screenHeight) { // while above ground
                object.y += object.getVelocity(); // accelerate
                m.repaint();
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    System.out.println("got interrupted!");
                }
            }
            object.setVelocity(-1 * object.getVelocity());
            int bouncetime = 0;
            while (object.velocity < 0) { // while rebounding
                object.y += object.getVelocity() / 2;
                bouncetime++;
                m.repaint();
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    System.out.println("got interrupted!");
                }
            }
        }

    }
}
