package com.michael.nettools.piclab;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * A class that represents a picture. This class inherits from SimplePicture and
 * allows the student to add functionality to the Picture class.
 *
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture {
    ///////////////////// constructors //////////////////////////////////

    /**
     * Constructor that takes no arguments
     */
    public Picture() {
        /* not needed but use it to show students the implicit call to super()
         * child constructors always call a parent constructor 
         */
        super();
    }

    /**
     * Constructor that takes a file name and creates the picture
     *
     * @param fileName the name of the file to create the picture from
     */
    public Picture(String fileName) {
        // let the parent class handle this fileName
        super(fileName);
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

    /**
     * Constructor that takes a picture and creates a copy of that picture
     *
     * @param copyPicture the picture to copy
     */
    public Picture(Picture copyPicture) {
        // let the parent class do the copy
        super(copyPicture);
    }

    /**
     * Constructor that takes a buffered image
     *
     * @param image the buffered image to use
     */
    public Picture(BufferedImage image) {
        super(image);
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



    public String toText(){
        String out = "";
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels) {
            out += "[";
            for (Pixel pixel : rowArray) {
                out += "{" + (pixel.getRed() + pixel.getGreen() + pixel.getBlue())/3.0;
            }

        }
        String[] rows = out.split("\\[");
        String[][] pxarray = new String[rows.length][];
        for (int i = 0; i < rows.length; i++) {
            String[] row = rows[i].split("\\{");
            pxarray[i] = row;
        }
        for (int i = 0, pixelsLength = pxarray.length; i < pixelsLength; i++) {
            String[] row = pxarray[i];
            for (int j = 0; j < row.length; j++) {
                String pixel = row[j];
                System.out.print(Arrays.toString(pixel.split(",")));
            }
            System.out.println("\n");
        }
        return out;
    }

    public static Picture fromText(String text){
        String converter = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

        String[] rows = text.split("\\[");
        String[][] pixels = new String[rows.length][];
        for (int i = 0; i < rows.length; i++) {
            String[] row = rows[i].split("\\{");
            pixels[i] = row;
        }
        System.out.println(pixels.length + ", "+  pixels[1].length);
        Picture out = new Picture(pixels.length, pixels[1].length);
        out.setAllPixelsToAColor(Color.BLACK);

        Pixel[][] outPixels = new Pixel[pixels.length][pixels[0].length];
        for (int i = 1, pixelsLength = pixels.length; i < pixelsLength; i++) {
            String[] row = pixels[i];
            for (int j = 1; j < row.length; j++) {
                String pixel = row[j];
                Pixel curPixel = new Pixel(out, j, i);
                int pixcolor = converter.indexOf(pixel) * 4;

                    try {
                        System.out.println(j + ", " + i + ": " + pixel);
                        curPixel.setRed(pixcolor);
                        curPixel.setGreen(pixcolor);
                        curPixel.setBlue(pixcolor);
                    }catch (Exception e){
                        //do nothing
                    }
            }
        }

        return out.scale(4, 4);

    }




    /**
     * Method to set the blue to 0
     */
    public void zeroBlue() {
        Pixel[][] pixels = this.getPixels2D();

        this.setPixels2D(pixels);
    }

    public void deSteg(int constant){
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels) {
            for (Pixel pixelObj : rowArray) {
                pixelObj.setGreen(pixelObj.getGreen()-200);
                pixelObj.setRed(pixelObj.getRed()-200);
                pixelObj.setBlue(pixelObj.getBlue()-200);
            }
        }
    }

    public void deStegwithSrc(Picture picture2){
        Pixel[][] pixels = this.getPixels2D();
        Pixel[][] bkgrnd = picture2.getPixels2D();

        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[i].length; j++) {
                Pixel crypt = pixels[i][j];
                Pixel OG = bkgrnd[i][j];
                crypt.setGreen(crypt.getGreen()-OG.getGreen());
                crypt.setRed(crypt.getRed()-OG.getRed());
                crypt.setBlue(crypt.getBlue()-OG.getBlue());
            }
        }

    }


    public void keepOnlyBlue() {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels) {
            for (Pixel pixelObj : rowArray) {
                pixelObj.setGreen(0);
                pixelObj.setRed(0);
            }
        }
    }
    public void negate() {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels) {
            for (Pixel pixelObj : rowArray) {
                pixelObj.setGreen(255 - pixelObj.getGreen());
                pixelObj.setRed(255 - pixelObj.getRed());
                pixelObj.setBlue(255 - pixelObj.getBlue());
            }
        }
    }
    public void grayScale() {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels) {
            for (Pixel pixelObj : rowArray) {
                int gray = (pixelObj.getGreen() + pixelObj.getRed() + pixelObj.getBlue())/3;
                pixelObj.setGreen(gray);
                pixelObj.setRed(gray);
                pixelObj.setBlue(gray);
            }
        }
    }
    public void fixUnderwater() {
        Pixel[][] pixels = this.getPixels2D();

        for (Pixel[] rowArray : pixels) {
            for (Pixel pixelObj : rowArray) {
                pixelObj.colorCorrect(new int[]{30, -100, -77});
                pixelObj.addContrast(2.5);
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






    //    for (Pixel[] rowArray : pixels) {
    //    for (Pixel pixelObj : rowArray) {
    public void mirrorVerticalRightToLeft() {
        Pixel[][] pixels = this.getPixels2D();

        for (int i = 0; i < pixels.length; i++) {
            Pixel[] pixelRow = pixels[i];
            for (int j = 0; j < pixelRow.length/2; j++) {
                Pixel right = pixelRow[pixelRow.length - j - 1];
                int[] pRGB = right.getRGB();
                pixels[i][j].setRGB(pRGB);
            }
        }
    }
    public void mirrorArms(){
        mirrorOBJx(160, 105, 170, 190);//left
        mirrorOBJx(170, 240, 295, 195);//right // his arm be hangin' on by a thread
    }
    public void addBirb(){
        cloneStampFeather(232, 235, 325, 343, 222, 126, 4);//left

    }

    private void cloneStampFeather(int yi, int xi, int ymax, int xmax, int yf, int xf, int feather){
        Pixel[][] pixels = this.getPixels2D();
        Pixel src;
        Pixel dest;
        for(int y = yi; y < ymax; y ++){
            for(int x = xi; x < xmax; x ++){
                int deltaX = x - xi;
                int deltaY = y - yi;
                src = pixels[y][x];
                dest = pixels[(deltaY) + yf][(deltaX) + xf];

                if (deltaY < feather ||  y > (ymax + -feather) ) {
                    dest.setRGB(avgVals(dest.getRGB(), src.getRGB(), (2+deltaX/(xmax - xi))));
                }else if (deltaX < feather ||  x > (xmax + -feather)){
                    dest.setRGB(avgVals(dest.getRGB(), src.getRGB(), (2+deltaY/(ymax - yi))));

                } else {
                    dest.setRGB(src.getRGB());
                }
            }
        }
    }
    private void cloneStamp(int yi, int xi, int ymax, int xmax, int yf, int xf) {
        Pixel[][] pixels = this.getPixels2D();
        Pixel src;
        Pixel dest;
        for(int y = yi; y < ymax; y ++){
            for(int x = xi; x < xmax; x ++){
                src = pixels[y][x];
                dest = pixels[(y - yi) + yf][(x - xi) + xf];
                dest.setRGB(src.getRGB());
            }
        }
    }
    public int[] avgVals(int[] arr1, int[] arr2, int scale){
        int[] returned = new int[arr1.length];
        for (int i = 0; i < arr1.length; i++) {
            returned[i] = (arr1[i] + arr2[i])/(scale);
        }
        return returned;

    }




    private void mirrorOBJx(int yi, int xi, int xmax, int mid) {
        Pixel[][] pixels = this.getPixels2D();
        Pixel src;
        Pixel dest;
        for(int y = yi; y < mid; y ++){
            for(int x = xi; x < xmax; x ++){
                src = pixels[y][x];
                dest = pixels[(mid - y) + mid][x];
                dest.setColor(src.getColor());
            }
        }
    }
    public void chromaKey(Picture bckgrnd) {
        Pixel[][] replace = bckgrnd.getPixels2D();
        Pixel[][] pixels = this.getPixels2D();
        Pixel localsrc;
        Pixel extsrc;
        for(int y = 0; y < pixels.length; y ++){
            for(int x = 0; x < pixels[y].length; x ++){
                localsrc = pixels[y][x];
                extsrc = replace[y][x];
                int blue = localsrc.getBlue();
                int red = localsrc.getRed();
                int green = localsrc.getGreen();

                    if (blue > red && blue > green){
                        localsrc.setColor(extsrc.getColor());
                    }

            }
        }
    }
    public void chromaKey2(Picture bckgrnd) {
        Pixel[][] replace = bckgrnd.getPixels2D();
        Pixel[][] pixels = this.getPixels2D();
        Pixel localsrc;
        Pixel extsrc;
        for(int y = 0; y < pixels.length; y ++){
            for(int x = 0; x < pixels[y].length; x ++){
                localsrc = pixels[y][x];
                extsrc = replace[y][x];
                int blue = localsrc.getBlue();
                int red = localsrc.getRed();
                int green = localsrc.getGreen();

                if (green > blue && green > red){
                    localsrc.setColor(extsrc.getColor());
                }

            }
        }
    }


//    public void copySection(int startRow, int startCol,int endRow, int endCol, int w, int h) {
//        for (int i = 0; i < w; i++) {
//            for (int j = 0; j < ; j++) {
//
//            }
//        }
//    }

    public void mirrorHorizontal() {
        Pixel[][] pixels = this.getPixels2D();

        for (int i = pixels.length/2; i > 0; i--) {
            for (int j = 0; j < pixels[i].length; j++) {
                Pixel pixel = pixels[i][j];
                int[] pRGB = pixel.getRGB();
                pixels[pixels.length - i - 1][j].setRGB(pRGB);
            }
        }
    }

    public void mirrorHorizontalBotToTop() {
        Pixel[][] pixels = this.getPixels2D();

        for (int i = pixels.length-1; i > pixels.length/2; i--) {
            for (int j = 0; j < pixels[i].length; j++) {
                Pixel pixel = pixels[i][j];
                int[] pRGB = pixel.getRGB();
                pixels[pixels.length - i - 1][j].setRGB(pRGB);
            }
        }
    }
    public void mirrorSection(int midline){
        int mirrorPoint = 276;
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int count = 0;
        Pixel[][] pixels = this.getPixels2D();

        // loop through the rows
        for (int row = 27; row < 97; row++) {
            // loop from 13 to just before the mirror point
            for (int col = 13; col < mirrorPoint; col++) {

                leftPixel = pixels[row][col];
                rightPixel = pixels[row][mirrorPoint - col + mirrorPoint];
                rightPixel.setColor(leftPixel.getColor());
            }
        }
    }
    public void mirrorDiagonal() {
        Pixel[][] pixels = this.getPixels2D();
        int width = pixels[0].length;
        int height = pixels.length;
        if (width > height){
            width = height;
        } else if (height > width){
            height = width;
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {

                    Pixel pixel = pixels[i][j];
                    int[] pRGB = pixel.getRGB();
                    pixels[j][i].setRGB(pRGB);


            }

        }
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

    /**
     * Method that mirrors the picture around a vertical mirror in the center of
     * the picture from left to right
     */
    public void mirrorVertical() {
        Pixel[][] pixels = this.getPixels2D();
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int width = pixels[0].length;
        for (int row = 0; row < pixels.length; row++) {
            for (int col = 0; col < width / 2; col++) {
                leftPixel = pixels[row][col];
                rightPixel = pixels[row][width - 1 - col];
                rightPixel.setColor(leftPixel.getColor());
            }
        }
    }

    /**
     * Mirror just part of a picture of a temple
     */
    public void mirrorTemple() {
        int mirrorPoint = 276;
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int count = 0;
        Pixel[][] pixels = this.getPixels2D();

        // loop through the rows
        for (int row = 27; row < 97; row++) {
            // loop from 13 to just before the mirror point
            for (int col = 13; col < mirrorPoint; col++) {

                leftPixel = pixels[row][col];
                rightPixel = pixels[row][mirrorPoint - col + mirrorPoint];
                rightPixel.setColor(leftPixel.getColor());
            }
        }
    }

    /**
     * copy from the passed fromPic to the specified startRow and startCol in
     * the current picture
     *
     * @param fromPic the picture to copy from
     * @param startRow the start row to copy to
     * @param startCol the start col to copy to
     */
    public void copy(Picture fromPic,
                     int startRow, int startCol) {
        Pixel fromPixel = null;
        Pixel toPixel = null;
        Pixel[][] toPixels = this.getPixels2D();
        Pixel[][] fromPixels = fromPic.getPixels2D();
        for (int fromRow = 0, toRow = startRow;
                fromRow < fromPixels.length
                && toRow < toPixels.length;
                fromRow++, toRow++) {
            for (int fromCol = 0, toCol = startCol;
                    fromCol < fromPixels[0].length
                    && toCol < toPixels[0].length;
                    fromCol++, toCol++) {
                fromPixel = fromPixels[fromRow][fromCol];
                toPixel = toPixels[toRow][toCol];
                toPixel.setColor(fromPixel.getColor());
            }
        }
    }

    /**
     * Method to create a collage of several pictures
     */
    public void createCollage() {
        Picture flower1 = new Picture("flower1.jpg");
        Picture flower2 = new Picture("flower2.jpg");
        this.copy(flower1, 0, 0);
        this.copy(flower2, 100, 0);
        this.copy(flower1, 200, 0);
        Picture flowerNoBlue = new Picture(flower2);
        flowerNoBlue.zeroBlue();
        this.copy(flowerNoBlue, 300, 0);
        this.copy(flower1, 400, 0);
        this.copy(flower2, 500, 0);
        this.mirrorVertical();
        this.write("collage.jpg");
    }

    /**
     * Method to show large changes in color
     *
     * @param edgeDist the distance for finding edges
     */
    public void edgeDetection(int edgeDist) {
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        Pixel[][] pixels = this.getPixels2D();
        Color rightColor = null;
        for (int row = 0; row < pixels.length; row++) {
            for (int col = 0;
                    col < pixels[0].length - 1; col++) {
                leftPixel = pixels[row][col];
                rightPixel = pixels[row][col + 1];
                rightColor = rightPixel.getColor();
                if (leftPixel.colorDistance(rightColor)
                        > edgeDist) {
                    leftPixel.setColor(Color.BLACK);
                } else {
                    leftPixel.setColor(Color.WHITE);
                }
            }
        }
    }

    /* Main method for testing - each class in Java can have a main 
     * method 
     */
    public static void main(String[] args) {
        Picture beach = new Picture("resources/images/beach.jpg");
        beach.explore();
        beach.zeroBlue();
        beach.explore();
    }

} // this } is the end of class Picture, put all new methods before this
