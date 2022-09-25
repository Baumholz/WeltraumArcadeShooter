package Game.Graphic;

import Game.Const.Const;
import Game.Const.Stats;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Sidebar extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private JLabel speedLabel;
	private JLabel healthLabel;
	private JLabel positionLabel;
	private JLabel shotLabel;
	private JLabel killLabel;
	
	private int r = 0;
	private int g = 0;
	private int b = 0;
	
	
	
	public Sidebar() {
		this.setSize(Const.SIDEBAR_WIDTH, Const.WORLD_HEIGHT);
		this.setLayout(new GridLayout(6, 0));
		this.setBackground(Color.black);

		speedLabel = new JLabel();
		healthLabel = new JLabel();
		positionLabel = new JLabel();
		shotLabel = new JLabel();
		killLabel = new JLabel();

		speedLabel.setText("Speed: " + Stats.SPEED);
		setLabel(speedLabel);

		healthLabel.setText("Health: " + Stats.HEALTH);
		setLabel(healthLabel);
		
		positionLabel.setText("x: " + Stats.X + ", y: " + Stats.Y);
		setLabel(positionLabel);
		
		shotLabel.setText("Shots fired: " + Stats.SHOTS);
		setLabel(shotLabel);
		
		killLabel.setText("Kills: " + Stats.KILLS);
		setLabel(killLabel);

		this.add(speedLabel);
		this.add(healthLabel);
		this.add(positionLabel);
		this.add(shotLabel);
		this.add(killLabel);

		this.setFocusable(true);
	}
	
	public void updateStats(){
		speedLabel.setText("Speed: " + Stats.SPEED);
		healthLabel.setText("Health: " + Stats.HEALTH);
		positionLabel.setText("x: " + Stats.X + ", y: " + Stats.Y);
		shotLabel.setText("Shots fired: " + Stats.SHOTS);
		killLabel.setText("Kills: " + Stats.KILLS);
	}

	public void setLabel(JLabel label) {
		label.setBorder(new EmptyBorder(10, 10, 10, 10));
		Color labelColor = new Color(r,g,b);
		label.setFont(new Font("Arial", Font.BOLD, 20));
		label.setForeground(Color.white);
		label.setBackground(labelColor);
		label.setOpaque(true);
		
		r += 25;
		g += 25;
		b += 25;
	}
	
	
}
