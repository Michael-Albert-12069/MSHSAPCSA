package com.michael.nettools.piclab;

/**
 * This class contains class (static) methods that will help you test the
 * Picture class methods. Uncomment the methods and the code in the main to
 * test.
 *
 * @author Barbara Ericson
 */
public class PictureTester {

    /**
     * Method to test zeroBlue
     */
    public static void testZeroBlue() {
        Picture beach = new Picture("resources/images/warren.jpg");
        beach.explore();
        beach.zeroBlue();
        beach.explore();
    }

    public static void testKeepOnlyBlue() {

    }




    /**
     * Method to test mirrorVertical
     */
    public static void testMirrorVertical() {
        Picture caterpillar = new Picture("caterpillar.jpg");
        caterpillar.explore();
        caterpillar.mirrorVertical();
        caterpillar.explore();
    }

    /**
     * Method to test mirrorTemple
     */
    public static void testMirrorTemple() {
        Picture temple = new Picture("temple.jpg");
        temple.explore();
        temple.mirrorTemple();
        temple.explore();
    }

    public static void testMirrorArms(){
        Picture newpic = new Picture("resources/images/snowman.jpg");
        newpic.mirrorArms();
        newpic.explore();
    }
    public static void testMirrorGull(){
        Picture newpic = new Picture("resources/images/seagull.jpg");
        newpic.addBirb();
        newpic.explore();
    }

    /**
     * Method to test the collage method
     */
    public static void testCollage() {
        Picture canvas = new Picture("640x480.jpg");
        canvas.createCollage();
        canvas.explore();
    }

    /**
     * Method to test edgeDetection
     */
    public static void testEdgeDetection() {
        Picture swan = new Picture("swan.jpg");
        swan.edgeDetection(10);
        swan.explore();
    }
    public static void testScale(String fileName) {
        Picture swan = new Picture(fileName);
        swan.scale(42.0, 42.0);
        swan.explore();
    }

    /**
     * Main method for testing. Every class can have a main method in Java
     */
    public static void main(String[] args) {
        Picture newpic = new Picture("C:\\Users\\Micha\\Pictures\\proof1A.png");
        newpic.zeroBlue();
        newpic.explore();
        String pic = newpic.toText();
        System.out.println(pic);
        Picture.fromText(pic).explore();
    }


}
