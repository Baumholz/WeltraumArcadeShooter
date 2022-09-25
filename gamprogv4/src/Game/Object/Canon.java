package Game.Object;

import Game.Const.Const;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Canon extends GameObject {

	BufferedImage l_img;
	BufferedImage r_img;
	BufferedImage u_img;
	BufferedImage d_img;

	public Canon(double x_, double y_) throws IOException {
		super();
		x = x_;
		y = y_;
		speed = 0;
		type = Const.TYPE_CANON;
		this.radius = Const.AVATAR_RADIUS;
		this.collidable = false;
		
		try {
			String imagePath = "Image/canon-left.png";
			l_img = ImageIO.read(new File(imagePath));
			imagePath = "Image/canon-right.png";
			r_img = ImageIO.read(new File(imagePath));
			imagePath = "Image/canon-up.png";
			u_img = ImageIO.read(new File(imagePath));
			imagePath = "Image/canon-down.png";
			d_img = ImageIO.read(new File(imagePath));
			image = r_img;
		} catch (IOException e) {
			System.out.println("Can't load the avatar image");
		}
		
		
	}

	public void move(double diffSeconds) {
		reflectOnBorders();
		super.move(diffSeconds);
	}

	@Override
	public void reflectOnBorders() {
		double rad = radius;

		if (x <= rad && this.alfa == Math.PI) {
			this.speed = 0;
		}
		if (y <= rad && this.alfa == -Math.PI / 2) {
			this.speed = 0;
		}
		if (x >= Const.WORLD_WIDTH - rad && this.alfa == 2 * Math.PI) {
			this.speed = 0;
		}
		if (y >= Const.WORLD_HEIGHT - rad && this.alfa == Math.PI / 2) {
			this.speed = 0;
		}
	}

	public void rotate(int aimAngle) {
		switch (aimAngle) {
		case 0:
			image = u_img;
			break;

		case 90:
			image = r_img;
			break;

		case 180:
			image = d_img;
			break;

		case 270:
			image = l_img;
		}
	}

	public void shoot(int direction) {
		System.out.println("SCHUSS!");
	}

	@Override
	int type() {
		// TODO Auto-generated method stub
		return type;
	}
}
