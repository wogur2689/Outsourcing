package chart01;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class SecondPanel extends JPanel {
	protected Color[] secondcolors = {Color.red, Color.green, Color.blue, Color.magenta, Color.cyan, Color.yellow};

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//title
		MyChart.panelTitle(g, "<Greek Yogurt Sales by Type>");
		//border
		g.drawRect(1, 1, 590, 375);
		
		//chart Å×µÎ¸®
		g.drawLine(30, 50, 30, 350);
		g.drawLine(30, 350, 580, 350);
		
		//chart
		g.setColor(secondcolors[0]);
		g.fillRect(50, 160, 50, 190);
		g.setColor(secondcolors[1]);
		g.fillRect(140, 160, 50, 190);
		g.setColor(secondcolors[2]);
		g.fillRect(230, 90, 50, 260);
		g.setColor(secondcolors[3]);
		g.fillRect(320, 160, 50, 190);
		g.setColor(secondcolors[4]);
		g.fillRect(410, 250, 50, 100);
		g.setColor(secondcolors[5]);
		g.fillRect(500, 250, 50, 100);
		
		//¿ä¼Ò¸í
		g.setColor(Color.BLACK);
		g.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 10));
		g.drawString("Plain yogurt", 40, 360);
		g.drawString("Berry yogurt", 130, 360);
		g.drawString("Choco yogurt", 220, 360);
		g.drawString("Lemon yogurt", 310, 360);
		g.drawString("Raspberry yogurt", 400, 360);
		g.drawString("Chestnut yogurt", 490, 360);
	}
}
