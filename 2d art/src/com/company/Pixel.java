package com.company;

import java.awt.*;

public class Pixel {
    int[] rgb;
    public Pixel(int r, int g, int b ){
        rgb = new int[]{r, g, b};
    }
    public Pixel(int[] rgb){
        this.rgb = new int[]{rgb[0], rgb[1], rgb[2]};
    }
    public Pixel(Color c){
        this.rgb = new int[]{c.getRed(), c.getGreen(), c.getBlue()};
    }
    public Color getColor(){
        return new Color(this.r(), this.g(), this.b());
    }

    public int r(){
        return rgb[0];
    }
    public int g(){
        return rgb[1];
    }
    public int b(){
        return rgb[2];
    }


}
