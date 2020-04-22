package com.company;
import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageTranscoder;
import javax.swing.*;

public class Main extends JPanel {
    public static int screenHeight = 750;
    public static int screenWidth = 1000;
    public static ArrayList<BouncyBall> balls = new ArrayList<>();


    //Draws whatever you want.
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for (BouncyBall ball: balls) {

            g.setColor(ball.ballColor);
            g.fillOval(ball.x, ball.y, ball.blockHeight, ball.blockHeight);
        }


    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Random rand = new Random();
            balls.add(new BouncyBall(rand.nextInt(screenWidth), 0));
            balls.get(i).setBlockHeight(new Random().nextInt(70) + 30);
            //balls.get(i).gravity = -1 *( new Random().nextInt(10)/2 + 1);
            balls.get(i).gravity = -1 ;

        }

        JFrame jf = new JFrame("Cell Spread");
        Main m = new Main();
        jf.setSize(screenWidth,screenHeight + 50);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.add(m);
        m.repaint();
        Thread.sleep(1000);
        for (BouncyBall ball: balls) {
            ball.startBouncing(m, .99);
            ball.startDeflecting(m);
            Thread.sleep((long) (new Random().nextInt(40) + 10));
        }

    }


}
