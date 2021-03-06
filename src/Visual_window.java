import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Visual_window extends JPanel {
	private int numberOfBars;
	private int[] array;
	private int select1 = -1;
	private int select2 = -1;
	private int speed = 4;
	
	public Visual_window(int number) {
		setBackground(Color.DARK_GRAY);
		//Creates the array
		numberOfBars = number;
		resetArray(numberOfBars);
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		super.paintComponent(g2D);
		
		g2D.setColor(Color.WHITE);
		for(int i = 0; i < numberOfBars; i++) {
			int heightOfBar = array[i] * (550 / numberOfBars);
			int xBeginCor = 20 + i + (1100 / numberOfBars) * i;
			int yBeginCor = 559 - heightOfBar;
			
			g2D.fillRect(xBeginCor, yBeginCor, (int)(1000 / numberOfBars), heightOfBar);
		}
		if(select1 != -1 && select2 != -1) {
			g2D.setColor(Color.GREEN);
			g2D.fillRect(20 + select1 + (1100 / numberOfBars) * select1, 559 - array[select1] * (550 / numberOfBars), (int)(1000 / numberOfBars), array[select1] * (550 / numberOfBars));
			g2D.setColor(Color.BLUE);
			g2D.fillRect(20 + select2 + (1100 / numberOfBars) * select2, 559 - array[select2] * (550 / numberOfBars), (int)(1000 / numberOfBars), array[select2] * (550 / numberOfBars));
		} else if(select1 != -1 && select2 == -1) {
			g2D.setColor(Color.GREEN);
			g2D.fillRect(20 + select1 + (1100 / numberOfBars) * select1, 559 - array[select1] * (550 / numberOfBars), (int)(1000 / numberOfBars), array[select1] * (550 / numberOfBars));
		} else if(select1 == -1 && select2 != -1) {
			g2D.setColor(Color.BLUE);
			g2D.fillRect(20 + select2 + (1100 / numberOfBars) * select2, 559 - array[select2] * (550 / numberOfBars), (int)(1000 / numberOfBars), array[select2] * (550 / numberOfBars));
		}
	}
	
	public int getValue(int index) {
		return array[index];
	}
	
	public void swap(int index1, int index2) {
		select1 = index1;
		select2 = index2;
		int temp = array[index1];
		array[index1] = array[index2];
		array[index2] = temp;
		
		repaint();
		try {
			Thread.sleep((2 * 4) / speed);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void swapSingleLeft(int leftIndex, int ValueForSwap) {
		select1 = leftIndex;
		array[leftIndex] = ValueForSwap;
		repaint();
		try {
			Thread.sleep((6 * 4) / speed);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void swapSingleRight(int rightIndex, int ValueForSwap) {
		select2 = rightIndex;
		array[rightIndex] = ValueForSwap;
		repaint();
		try {
			Thread.sleep((6 * 4) / speed);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int max_value() {
		int max = array[0];
		for(int i = 0; i < array.length; i++) {
			if(array[i] > max) {
				max = array[i];
			}
		}
		return max;
	}
	
	public void setSpeed(int spdValue) {
		speed = spdValue;
	}
	
	public void resetArray(int number) {
		select1 = -1;
		select2 = -1;
		numberOfBars = number;
		array = new int[numberOfBars];
		for(int i = 0; i < numberOfBars; i++) {
			array[i] = i + 1;
		}
		
		//Shuffles the array
		for(int i = 0; i < numberOfBars; i++) {
			int random = (int) (Math.random() * numberOfBars);
			int temp = array[i];
			array[i] = array[random];
			array[random] = temp;
		}
		
		repaint();
	}
}
