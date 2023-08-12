package chart01;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class FourthPanel extends JPanel {
	
	protected Color fourtcolor = Color.white;
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//title
		MyChart.panelTitle(g, "<My Own Chart : SpongeBob>");
		//border 
		g.drawRect(1, 1, 590, 375);
		
		//chart
		g.setColor(Color.yellow);
		g.fillRect(30, 80, 230, 200);
		g.setColor(fourtcolor);
		g.fillOval(90, 120, 40, 40);
		g.fillOval(160, 120, 40, 40);
		g.setColor(Color.black);
		g.fillOval(100, 130, 20, 20);
		g.fillOval(170, 130, 20, 20);
		g.drawLine(50, 200, 240, 200);
		g.setColor(fourtcolor);
		g.fillRect(160, 200, 20, 20);
		g.fillRect(110, 200, 20, 20);
		g.setColor(Color.orange);
		g.fillRect(30, 250, 230, 60);
		g.setColor(Color.yellow);
		g.fillRect(90, 310, 20, 65);
		g.fillRect(180, 310, 20, 65);
		
		g.setColor(Color.black);
		g.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 15));
		g.drawString("SpongeBob", 280, 250);
		g.drawString("shows the changes according to sales!", 280, 270);
		g.drawString("Press the button!", 280, 290);
	}
}