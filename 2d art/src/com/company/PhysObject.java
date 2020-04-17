package com.company;

public class PhysObject {
    public int x = 100;
    public int y = 10;
    public int time = 0;
    public int gravity = 2;
    public int blockHeight = 15;
    public int velocity;

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setGravity(int gravity) {
        this.gravity = gravity;
    }

    public void setBlockHeight(int blockHeight) {
        this.blockHeight = blockHeight;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getTime() {
        return time;
    }

    public int getGravity() {
        return gravity;
    }
    public int getVelocity(){
        velocity += gravity;
        return velocity;
    }
    public void setVelocity(int v){
        velocity = v;
    }


    public int getBlockHeight() {
        return blockHeight;
    }




    public PhysObject(int x, int y){
        this.x = x;
        this.y = y;
    }



}
