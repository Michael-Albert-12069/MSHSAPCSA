package com.company;


import java.util.HashMap;
import java.util.Random;

public class CellPanel{
    boolean[][] cells;
    HashMap<Integer, Integer> activeCells;
    public CellPanel(int x, int y){
        cells = new boolean[y][x];
    }
    public void makeRandomActive(){
        int x = new Random().nextInt(cells[0].length);
        int y = new Random().nextInt(cells.length);
        cells[y][x] = true;
    }
    public void age(){
        for (int i = 1; i < cells.length -1; i++) {
            for (int j = 1; j < cells[i].length -1; j++) {
                if (!cells[i][j]) {
                    if ((cells[i - 1][j] || cells[i][j-1]) || (cells[i + 1][j] || cells[i][j + 1])) {
                        cells[i][j] = fiftyfifty();
                    }
                }
            }
        }
    }
    private static boolean fiftyfifty(){
        int rand = (int) Math.round(Math.random());
        if (rand == 1){
            return true;
        }
        return false;
    }
}
