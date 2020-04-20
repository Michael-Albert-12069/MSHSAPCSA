package com.company;

import java.awt.*;

import static com.company.BouncyBall.getSpeed;
import static com.company.Main.screenHeight;

public class BouncyBall {
    public int x = 100;
    public int y = 10;
    public int gravity = -5;
    public int blockHeight = 15;
    public Color ballColor;


    public void setBlockHeight(int blockHeight) {
        this.blockHeight = blockHeight;
    }







    public BouncyBall(int x, int y){
        ballColor = new Color((int)(Math.random() * 0x1000000));
        this.x = x;
        this.y = y;
    }
    public void startBouncing(Main m, double bounciness) throws InterruptedException {
        new Thread(new ThreadedBounce(this, m, bounciness)).start();
    }

    public static int getSpeed(int gravity, int time){
        return gravity * time;
    }
    public void updateY(int velocity){
        this.y -= velocity;
    }
}
class ThreadedBounce implements Runnable {
    private BouncyBall ball;
    private Main m;
    private double bounciness;
    public ThreadedBounce(BouncyBall ball, Main m, double bounciness){
        this.ball = ball;
        this.m = m;
        this.bounciness = bounciness;
    }

    @Override
    public void run() {
        try {
            int time = 1;
            int velocity = 0;
            int HEIGHT = 600;
            while (ball.y + ball.blockHeight < HEIGHT) {
                while (ball.y + ball.blockHeight < HEIGHT) {
                    m.repaint();
                    velocity += getSpeed(ball.gravity, time);
                    ball.updateY(velocity);
                    Thread.sleep(10);
                    System.out.println(velocity);
                }
                Thread.sleep(5);
                velocity *= -1;
                while (velocity > 0) {
                    m.repaint();
                    velocity += getSpeed(ball.gravity, time);
                    ball.updateY((int) (velocity * bounciness));
                    System.out.println(velocity);
                    Thread.sleep(5);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
