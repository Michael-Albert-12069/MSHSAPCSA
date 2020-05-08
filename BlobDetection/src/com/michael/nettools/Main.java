package com.michael.nettools;

import com.michael.nettools.piclab.Picture;

public class Main {

    public static void main(String[] args) {
        Picture test = new Picture("C:\\Users\\Micha\\Pictures\\mikeCV\\CV.jpg");
        test.explore();

        test.colorMask(255, 133, 0, 10);
        test.explore();
    }
}
