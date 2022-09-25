package Game.Graphic;

import Game.Const.Const;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;


public class Frame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private Panel panel = null;
	private Sidebar sidebar = null;
	
	public Frame() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(Const.WORLDPART_WIDTH + Const.SIDEBAR_WIDTH, Const.WORLDPART_HEIGHT);
		this.setLayout(new BorderLayout());
		panel = new Panel();
		sidebar = new Sidebar();
		panel.setPreferredSize(new Dimension(Const.WORLDPART_WIDTH,Const.WORLDPART_HEIGHT));
		sidebar.setPreferredSize(new Dimension(Const.SIDEBAR_WIDTH,Const.WORLDPART_HEIGHT));
		
		this.add(panel,BorderLayout.CENTER);
		this.add(sidebar,BorderLayout.EAST);
	}
	
	public void displayOnScreen() {
		validate();
		setVisible(true);
	}

	public Panel getPanel() {
		return panel;
	}
	
	public Sidebar getSidebar(){
		return sidebar;
	}
	
	public GraphicSystem getGraphicSystem() {
		return panel;
	}
	
}
