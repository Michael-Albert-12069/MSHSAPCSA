package com.company;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

import static com.company.BouncyBall.getSpeed;
import static com.company.Main.*;

public class BouncyBall {
    public int x = 100;
    public int y = 10;
    public int gravity = -5;
    public int blockHeight = 15;
    public Color ballColor;
    public int yVelocity;
    public int xVelocity;


    public void setBlockHeight(int blockHeight) {
        this.blockHeight = blockHeight;
    }

    public int[] getCenter(){
        return new int[]{x + blockHeight/2, x + blockHeight/2};
    }

    public static double distBtwn(BouncyBall ball1, BouncyBall ball2){
        return Math.sqrt(Math.pow(ball2.x - ball1.x, 2) + Math.pow(ball2.y - ball1.y, 2));
    }

    public int radius(){
        return blockHeight/2;
    }

    public boolean isTouching(BouncyBall ball2){
        if (distBtwn(this, ball2) <=2 + this.radius() + ball2.radius()){
            return true;
        }
        return false;
    }
    public void startDeflecting(Main m) throws InterruptedException {
        new Thread(new BallsBounce(this, m, .1)).start();
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
        int diffconst =15;
        try {
            int time = 1;
            int HEIGHT = 600;
            ball.yVelocity = 0;
            while (ball.y + ball.blockHeight < HEIGHT) {
                while (ball.y + ball.blockHeight < HEIGHT) {
                    m.repaint();
                    ball.yVelocity += getSpeed(ball.gravity, time);
                    ball.updateY(ball.yVelocity);
                    Thread.sleep(diffconst);
                    System.out.println(ball.yVelocity);
                }
                Thread.sleep(5);
                ball.yVelocity *= -1;
                while (ball.yVelocity > 0) {
                    m.repaint();
                    ball.yVelocity += getSpeed(ball.gravity, time);
                    ball.updateY((int) (ball.yVelocity * bounciness));
                    System.out.println(ball.yVelocity);
                    Thread.sleep(diffconst);
                }
            }
            System.exit(0);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
class BallsBounce implements Runnable {
    private BouncyBall ball;
    private Main m;
    private double friction;
    public BallsBounce(BouncyBall ball, Main m, double friction ){
        this.ball = ball;
        this.m = m;
        this.friction = friction;
    }

    @Override
    public void run() {
        int diffconst =5;
        try {
            ball.xVelocity = 0;
            while (true){
                for (BouncyBall bball: balls) {
                    if (ball.isTouching(bball) && ball.x > bball.x){
                        ball.xVelocity ++;


                    }else if (ball.isTouching(bball) && ball.x < bball.x){
                        ball.xVelocity --;


                    }
                    if (ball.x + ball.xVelocity <=0 || ball.x + ball.xVelocity >= screenWidth){
                        ball.xVelocity *= -1;
                    }
//                    alterVelocity(ball);
                    ball.x += ball.xVelocity;
                    Thread.sleep(diffconst);
                    m.repaint();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void alterVelocity(BouncyBall ball){
        int sign = ball.xVelocity / Math.abs(ball.xVelocity);
        int velocity = Math.abs(ball.xVelocity);
        velocity = (int) (velocity * (1-friction));
        ball.xVelocity = velocity * sign;
    }

}
