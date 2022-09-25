package Game.Object;

import Game.Object.GameObject;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public abstract class ExplosionObjects extends GameObject {

	protected BufferedImage[] explosion;
	int deathCounter = 3;

	public ExplosionObjects( ) {
		try {
			BufferedImage[] explosion = { ImageIO.read(new File("Image/explosion1.png")),
					ImageIO.read(new File("Image/explosion2.png")),ImageIO.read(new File("Image/explosion3.png")) };
				this.explosion = explosion;
		} catch (Exception e) {

		}
		
	}
}
