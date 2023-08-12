package chart01;

import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyChart extends JFrame {
	private FirstPanel firstPanel = new FirstPanel();
	private SecondPanel secondPanel = new SecondPanel();
	private ThirdPanel thirdPanel = new ThirdPanel();
	private FourthPanel fourthPanel = new FourthPanel();
	
	public MyChart() {
		//title
		setTitle("My Chart");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		Container container = getContentPane();
		
		//������ ��ġ �� ���� ����
		container.setLayout(new GridLayout(2, 2));
		container.setBackground(Color.white);
		
		//������(chart)
		JButton[] button = new JButton[4];
		for(int i=0; i<button.length; i++){
			button[i] = new JButton("Change Color");
		}
		
		//1�� ��Ʈ
		button[0].addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i < firstPanel.firstcolors.length; i++) {
					firstPanel.firstcolors[i] = getRandomColor();
				}
				firstPanel.repaint();
			}
		});
		button[0].setBounds(70, 300, 120, 30); // ���ϴ� ��ġ�� ũ��� ����
		firstPanel.setLayout(null);
		firstPanel.setBackground(Color.white);
		firstPanel.add(button[0]);
		container.add(firstPanel);
		
		//2�� ��Ʈ
		button[1].addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i < secondPanel.secondcolors.length; i++) {
					secondPanel.secondcolors[i] = getRandomColor();
				}
				secondPanel.repaint();
			}
		});
		button[1].setBounds(450, 80, 120, 30); // ���ϴ� ��ġ�� ũ��� ����
		secondPanel.setLayout(null);
		secondPanel.setBackground(Color.white);
		secondPanel.add(button[1]);
		container.add(secondPanel);
		
		//3�� ��Ʈ
		button[2].addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i < thirdPanel.thirdcolors.length; i++) {
					thirdPanel.thirdcolors[i] = getRandomColor();
				}
				thirdPanel.repaint();
			}
		});
		button[2].setBounds(450, 80, 120, 30); // ���ϴ� ��ġ�� ũ��� ����
		thirdPanel.setLayout(null);
		thirdPanel.setBackground(Color.white);
		thirdPanel.add(button[2]);
		container.add(thirdPanel);
		
		//4�� ��Ʈ
		JButton spongeBobbtn1 = new JButton("High");
		JButton spongeBobbtn2 = new JButton("Medium");
		JButton spongeBobbtn3 = new JButton("Low");
		fourthPanel.setLayout(null);
		
		spongeBobbtn1.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				fourthPanel.fourtcolor = Color.magenta;
				fourthPanel.repaint();
			}
		});
		
		spongeBobbtn2.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				fourthPanel.fourtcolor = Color.green;
				fourthPanel.repaint();
			}
		});
		
		spongeBobbtn3.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				fourthPanel.fourtcolor = Color.red;
				fourthPanel.repaint();
			}
		});
		
		//��ư ��ġ
		spongeBobbtn1.setBounds(450, 80, 100, 30);
		spongeBobbtn2.setBounds(450, 120, 100, 30);
		spongeBobbtn3.setBounds(450, 160, 100, 30);

		//�гο� ��ư �߰��� �����̳ʿ� ���
		fourthPanel.add(spongeBobbtn1);
		fourthPanel.add(spongeBobbtn2);
		fourthPanel.add(spongeBobbtn3);
		fourthPanel.setBackground(Color.white);
		container.add(fourthPanel);
		
		//main Frame size
		setSize(1200, 800);
		setVisible(true);
	}
	
	//����
	public static Graphics panelTitle(Graphics g, String title) {
		g.setColor(Color.BLACK);
		g.setFont(new Font("���� ���", Font.BOLD, 30));
		g.drawString(title, 60, 40);
		return g;
	}
	
	//random color
	public Color getRandomColor() {
        int r = (int) (Math.random() * 256);
        int g = (int) (Math.random() * 256);
        int b = (int) (Math.random() * 256);
        return new Color(r, g, b);
    }

	
	public static void main(String[] args) {
		new MyChart();
	}

}