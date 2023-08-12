package chart01;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class ThirdPanel extends JPanel {
	protected Color[] thirdcolors = {Color.red, Color.green, Color.blue, Color.magenta, Color.cyan, Color.yellow};
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//title
		MyChart.panelTitle(g, "<Greek Yogurt Sales by Type>");
		
		//chart 테두리
		g.drawLine(30, 50, 30, 350);
		g.drawLine(30, 350, 580, 350);
		
		//border
		g.drawRect(1, 1, 590, 375);
		
		//전공
		g.setColor(thirdcolors[3]);
		g.drawLine(70, 250, 170, 150);
		g.drawLine(170, 150, 370, 150);
		g.drawLine(370, 150, 520, 350);
		
		//수업
		g.setColor(thirdcolors[4]);
		g.drawLine(70, 150, 170, 150);
		g.drawLine(170, 150, 260, 80);
		g.drawLine(370, 150, 260, 80);
		g.drawLine(430, 230, 530, 230);
		
		//차트 속성
		g.setColor(thirdcolors[3]);
		g.fillRect(170, 240, 20, 20);
		g.setColor(thirdcolors[4]);
		g.fillRect(170, 280, 20, 20);
		
		g.setColor(Color.BLACK);
		g.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		g.drawString("Last Week", 200, 255);
		g.drawString("This Week", 200, 295);
		
		//요소명
		g.setColor(Color.BLACK);
		g.setFont(new Font("맑은 고딕", Font.PLAIN, 10));
		g.drawString("Plain yogurt", 40, 360);
		g.drawString("Berry yogurt", 130, 360);
		g.drawString("Choco yogurt", 220, 360);
		g.drawString("Lemon yogurt", 310, 360);
		g.drawString("Raspberry yogurt", 400, 360);
		g.drawString("Chestnut yogurt", 490, 360);
	}
}