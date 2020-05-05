package com.michael.nettools;

import com.michael.nettools.piclab.Picture;
import org.bytedeco.javacv.*;

import javax.swing.*;

import java.awt.event.WindowEvent;

import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_imgcodecs.cvSaveImage;


/**
 * Created by gtiwari on 1/3/2017.
 */

public class Webcam implements Runnable {
    final int INTERVAL = 10000;///you may use interval
    CanvasFrame canvas = new CanvasFrame("Web Cam");

    public Webcam() {
        canvas.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
    }

    public void run() {

        FrameGrabber grabber = new VideoInputFrameGrabber(0); // 1 for next camera
        grabber.setImageWidth(230);
        grabber.setImageHeight(120);
        OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();
        IplImage img;
        int i = 0;
        try {
            grabber.start();
                Frame frame = grabber.grab();

                img = converter.convert(frame);

                //the grabbed frame will be flipped, re-flip to make it right
                cvFlip(img, img, 1);// l-r = 90_degrees_steps_anti_clockwise

                //save
                cvSaveImage("img.jpg", img);

                canvas.showImage(converter.convert(img));
            grabber.stop();
            Thread.sleep(10000);
            canvas.dispatchEvent(new WindowEvent( canvas, WindowEvent.WINDOW_CLOSING));

            Main.sendMessage(new Picture("G:\\Shared drives\\School_LaptopBridge\\MSHSAPCSA\\Webcam_Client\\img.jpg").toText());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

