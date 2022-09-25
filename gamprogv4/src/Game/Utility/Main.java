package Game.Utility;

import Game.Graphic.Frame;
import Game.Object.GameObject;

public class Main {

	private World world = null;
	
	public Main() {
		Frame frame = new Frame();
		frame.displayOnScreen();

		world = new World();
		world.setGraphicSystem(frame.getGraphicSystem());
		world.setInputSystem(frame.getPanel());
		
		GameObject.setWorld(world);
		frame.getGraphicSystem().setWorld(world);
		
		
		world.sidebar = frame.getSidebar();
		world.init();
		world.run();
	}

	public static void main(String args[]) {
		new Main();
	}
}
