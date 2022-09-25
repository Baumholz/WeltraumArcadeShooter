package Game.Object;

import Game.Const.Const;
import Game.Utility.World;

import java.awt.image.BufferedImage;

public abstract class GameObject {

	public double x;
	public double y;
	public double speed = 0;
	public double alfa = 0;
	public int radius = 0;
	public BufferedImage image;
	protected boolean collidable = true;
	public boolean isLiving = true;
	protected int type = 0;
	protected static World world;


	public void move(double diffSeconds) {
		x = x + Math.cos(alfa) * speed * diffSeconds;
		y = y + Math.sin(alfa) * speed * diffSeconds;
		if (x < 0 || x > Const.WORLD_WIDTH || y < 0 || y > Const.WORLD_HEIGHT) {
			this.isLiving = false;
		}
	}

	// test and reflect on Window Borders
	protected void reflectOnBorders() {
		// double rad = radius;
		// double PI = Math.PI;
		//
		// if (x < rad && (alfa > PI / 2 && alfa < PI * 3 / 2)) {
		// alfa = Math.PI - alfa;
		// }
		// if (y < rad && alfa > PI) {
		// alfa = Math.PI * 2 - alfa;
		// }
		// if (x > Game.Const.Const.WORLD_WIDTH - rad) {
		// alfa = Math.PI - alfa;
		// }
		// if (y > Game.Const.Const.WORLD_HEIGHT - rad) {
		// alfa = Math.PI * 2 - alfa;
		// }
		//
		// if (alfa < 0)
		// alfa += PI * 2;
		// if (alfa > PI * 2)
		// alfa -= PI * 2;
	}

	public static void setWorld(World w) {
		world = w;
	}

	abstract int type();
}
