package chart01;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class FirstPanel extends JPanel {
	//color
	protected Color[] firstcolors = {Color.red, Color.green, Color.blue, Color.magenta, Color.cyan, Color.yellow};
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//title
		MyChart.panelTitle(g, "<Greek Yogurt Sales by Type>");
		//background
		//border
		g.drawRect(1, 1, 590, 375);
		//¿ä¼Ò
		g.setColor(firstcolors[0]);
		g.fillOval(20, 60, 20, 20);
		g.setColor(firstcolors[1]);
		g.fillOval(20, 90, 20, 20);
		g.setColor(firstcolors[2]);
		g.fillOval(20, 120, 20, 20);
		g.setColor(firstcolors[3]);
		g.fillOval(20, 150, 20, 20);
		g.setColor(firstcolors[4]);
		g.fillOval(20, 180, 20, 20);
		g.setColor(firstcolors[5]);
		g.fillOval(20, 210, 20, 20);
		//¿ä¼Ò¸í
		g.setColor(Color.BLACK);
		g.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		g.drawString("Plain Greekyogurt", 60, 75);
		g.drawString("Berry Greekyogurt", 60, 105);
		g.drawString("Choco Greekyogurt", 60, 135);
		g.drawString("Lemon Greekyogurt", 60, 165);
		g.drawString("Raspberry Greekyogurt", 60, 195);
		g.drawString("Chestnut Greekyogurt", 60, 225);
		//chart
		g.setColor(firstcolors[0]);
		g.fillArc(300, 80, 250, 250, 30, 60);
		g.setColor(firstcolors[1]);
		g.fillArc(300, 80, 250, 250, 320, 70);
		g.setColor(firstcolors[2]);
		g.fillArc(300, 80, 250, 250, 210, 110);
		g.setColor(firstcolors[3]);
		g.fillArc(300, 80, 250, 250, 150, 60);
		g.setColor(firstcolors[4]);
		g.fillArc(300, 80, 250, 250, 120, 30);
		g.setColor(firstcolors[5]);
		g.fillArc(300, 80, 250, 250, 90, 30);
	}
}
