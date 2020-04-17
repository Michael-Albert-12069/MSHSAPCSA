package com.company;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.*;

public class Main extends JPanel {
	public static int screenHeight = 200;
	public static int screenWidth = 1000;
	public static cell[][] cells = new cell[screenWidth][screenHeight];

	//Draws whatever you want.
	public void paintComponent(Graphics g){
		super.paintComponent(g);

		for(int x = 0; x < cells.length; x++){
			for(int y = 0; y < cells[0].length; y++){
				if(cells[x][y].blinking == 0){
					g.setColor(Color.BLACK);
				}else{
					int colorVal = 255 - (cells[x][y].blinking * 5);
					g.setColor(new Color(colorVal, colorVal, colorVal));
				}
				g.fillRect(x, y, 1, 1);
			}
		}
	}

	public static void main(String[] args) {
		JFrame jf = new JFrame("Cell Spread");
		Main m = new Main();
		jf.setSize(screenWidth,screenHeight);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.add(m);

		for(int x = 0; x < cells.length; x++){
			for(int y = 0; y < cells[0].length; y++){
				cells[x][y] = new cell(x, y);
			}
		}

		while(true) {
			for(int x = 1; x < cells.length - 1; x++){
				for(int y = 1; y < cells[0].length - 1; y++){
					cells[x][y].tick(cells);
				}
			}
			m.repaint();
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				System.out.println("got interrupted!");
			}
		}

	}
}

class cell{
	int x;
	int y;
	int blinkCount;

	//When 0 it means you are not blinking, 1 means you are, 2 means you are finishing.
	int blinking = 0;

	public cell(int x, int y){
		this.x = x;
		this.y = y;

		this.blinkCount = (int) (Math.random() * 1000);
	}

	public void tick(cell[][] cells){
		if(this.blinking > 1){
			//The number in the if statement below is how many ticks the fade lasts.
			if(this.blinking > 40){
				this.blinking = 0;
			}else{
				this.blinking++;
			}
		}else if(this.blinking == 1){
			//If you are blinking, make those around you want to blink more.
			double increment = 1.1;
			cells[this.x + 1][this.y].blinkCount *= increment;
			cells[this.x - 1][this.y].blinkCount *= increment;
			cells[this.x][this.y + 1].blinkCount *= increment;
			cells[this.x][this.y - 1].blinkCount *= increment;

			cells[this.x + 1][this.y + 1].blinkCount *= increment;
			cells[this.x - 1][this.y - 1].blinkCount *= increment;
			cells[this.x - 1][this.y + 1].blinkCount *= increment;
			cells[this.x + 1][this.y - 1].blinkCount *= increment;
			blinking++;
		}else if(this.blinkCount > 1000){
			//If you're not blinking, check to see if you should be.
			this.blinkCount = 0;
			this.blinking = 1;
		} else{
			//If you're not supposed to be, just increase you're blink count variable.
			this.blinkCount++;
		}
	}


}

