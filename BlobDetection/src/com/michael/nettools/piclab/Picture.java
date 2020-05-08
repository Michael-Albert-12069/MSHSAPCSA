package com.michael.nettools.piclab;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * A class that represents a picture. This class inherits from SimplePicture and
 * allows the student to add functionality to the Picture class.
 *
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture {
    public Blob blob;

    ///////////////////// constructors //////////////////////////////////



    /**
     * Constructor that takes a file name and creates the picture
     *
     * @param fileName the name of the file to create the picture from
     */
    public Picture(String fileName) {
        // let the parent class handle this fileName
        super(fileName);
        blob = new Blob();
    }

    /**
     * Constructor that takes the width and height
     *
     * @param height the height of the desired picture
     * @param width the width of the desired picture
     */
    public Picture(int height, int width) {
        // let the parent class handle this width and height
        super(width, height);

    }




    ////////////////////// methods ///////////////////////////////////////
    /**
     * Method to return a string with information about this picture.
     *
     * @return a string with information about the picture such as fileName,
     * height and width.
     */
    public String toString() {
        String output = "Picture, filename " + getFileName()
                + " height " + getHeight()
                + " width " + getWidth();
        return output;

    }

    /**
     * Method to set the blue to 0
     */

    public void keepOnlyBlue() {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels) {
            for (Pixel pixelObj : rowArray) {
                pixelObj.setGreen(0);
                pixelObj.setRed(0);
            }
        }
    }

    public void addContrast(double factor){
        Pixel[][] pixels = this.getPixels2D();

        for (Pixel[] rowArray : pixels) {
            for (Pixel pixelObj : rowArray) {
                pixelObj.addContrast(factor);
            }
        }
    }



    public void colorMask(int R, int G, int B, int threshold) {
        Pixel[][] pixels = this.getPixels2D();

        for(int y = 0; y < pixels.length; y ++){
            for(int x = 0; x < pixels[y].length; x ++){
                Pixel localsrc;

                localsrc = pixels[y][x];
                int blue = localsrc.getBlue();
                int red = localsrc.getRed();
                int green = localsrc.getGreen();

                if (red + threshold > R && red - threshold < R){
                    if (green + threshold > G && green - threshold < G){
                        if (blue + threshold > B && blue - threshold < B){
                            localsrc.setColor(Color.BLACK);
                            addBlob(x, y);
                        } else {
                            localsrc.setColor(Color.WHITE);
                        }
                    } else {
                        localsrc.setColor(Color.WHITE);
                    }
                } else {
                    localsrc.setColor(Color.WHITE);
                }
            }
        }
        System.out.println(blob);
        blob.drawBlob(this, Color.GREEN);

    }
    public void addBlob(int x, int y){
        blob.add(x, y);
    }


    public static int average(int[] args){
        int sum = sum(args);
        return sum/(args.length);
    }
    public static int sum(int[] args){
        int sum = 0;
        for(int num : args){
            sum += num;
        }
        return sum;
    }



}

class Blob{
    int size = 0;
    int minx;
    int miny;
    int maxx;
    int maxy;


    public Blob(){

    }

    public void add(int x, int y){

        if (size == 0){
            minx = x;
            miny = y;
            maxx = x;
            maxy = y;
            size++;

        }else {
            if (hasPixel(x, y)){
                size++;
                if (x > maxx){maxx = x;}
                if (y > maxy){maxy = y;}

                if (x < minx){minx = x;}
                if (y < miny){miny = y;}
            }

        }

    }


    public boolean hasPixel(int x, int y){
                if (x + 1 <= maxx || x - 1 >= minx){
                    if (y + 1 <= maxy || y - 1 >= miny){
                        System.out.println("blob activated");
                        return true;
                    }
                }

        return false;
    }



    public void drawBlob(Picture p, Color color){
        Pixel[][] pixels = p.getPixels2D();
        int i = miny;
        for (int j = minx; j < maxx; j++) {
            pixels[i][j].setColor(color);
        }
        i = maxy;
        for (int j = minx; j < maxx; j++) {
            pixels[i][j].setColor(color);
        }
        for (i = miny; i < maxy; i++) {
            int j = minx;
            pixels[i][j].setColor(color);
        }
        for (i = miny; i < maxy; i++) {
            int j = maxx;
            pixels[i][j].setColor(color);

        }
    }
}

