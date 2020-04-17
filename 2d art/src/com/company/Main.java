package com.company;

import com.company.Screen;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {


    public static void main(String[] args)
    {
        JFrame frame;
        Main main = new Main();
        frame = new JFrame("Testing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(200, 300);

        Screen screen = new Screen(100,200);
        for (int i = 0; i < screen.pixels.length; i++) {
            for (int j = 0; j < screen.pixels[0].length; j++) {
                screen.pixels[i][j] = new Pixel(new Color((int)(Math.random() * 0x1000000)));
            }
        }
        screen.repaint();

        frame.add(screen);
    }
}