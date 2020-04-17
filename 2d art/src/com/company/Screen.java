package com.company;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Screen<lambda> extends JPanel {

    Pixel[][] pixels;

    public Screen(int width, int height, int[] rgb) {
        pixels = new Pixel[width][height];
        for (int y = 0; y < pixels.length; y++) {
            for (int x = 0; x < pixels[y].length; x++) {
                Pixel p = new Pixel(rgb);
                pixels[y][x] = p;

            }
        }
    }
    public Screen(int width, int height) {
        pixels = new Pixel[width][height];
    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);

        for(int x = 0; x < pixels.length; x++){
            for(int y = 0; y < pixels[0].length; y++){
                g.setColor(pixels[x][y].getColor());
                g.fillRect(x, y, 1, 1);
            }
        }
    }





}