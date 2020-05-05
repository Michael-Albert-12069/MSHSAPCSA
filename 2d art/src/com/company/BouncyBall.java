package com.company;

import java.awt.*;

public class BouncyBall {
    public int x = 100;
    public int y = 10;

    public int blockHeight = 15;
    public Color ballColor;



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






    public BouncyBall(int x, int y){
        //ballColor = new Color((int)(Math.random() * 0x1000000));
        ballColor = Color.BLACK;
        this.x = x;
        this.y = y;
    }

    public void startOscillation(Main main) throws InterruptedException {
        int amp = 100;
        int per = 500;



        new yOscillation(this, main, per, amp).start();
    }


    public static int getSpeed(int gravity, int time){
        return gravity * time;
    }
    public void updateY(int velocity){
        this.y -= velocity;
    }
}




class yOscillation extends Thread{
    BouncyBall ball;
    double time = 0;
    int amp;
    int per;
    double a;
    Main panel;
    private int INIT_HEIGHT;
    private int INIT_WIDTH;

    public yOscillation(BouncyBall ball, Main m, int per, int amp){
        this.per = per;
        this.amp = amp;
        this.ball = ball;
        panel = m;
        INIT_HEIGHT = ball.y;
        this.a = (2 * Math.PI)/per;
        INIT_WIDTH = ball.x;
    }

    @Override
    public void run() {
        while (true){
            try {
            time += 1;
                ball.x = (int) getX() + INIT_WIDTH;
                ball.y = (int) getY() + INIT_HEIGHT;
            panel.repaint();
            Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public double getY(){
        double y = amp * (Math.cos(time * a));
        return (getRounded(y));
    }
    public double getX(){

        double x = amp * (Math.cos((time + (per/4.0)) * a));
        return (getRounded(x));
    }

    private static double getRounded(double n){
        return (double)Math.round(n * 100000d) / 100000d;
    }
}
