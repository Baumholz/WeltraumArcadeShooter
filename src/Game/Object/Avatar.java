package Game.Object;

import Game.Const.Const;
import Game.Const.Stats;
import Game.Utility.GameObjectList;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Avatar extends GameObject {

	BufferedImage l_img;
	BufferedImage r_img;
	BufferedImage u_img;
	BufferedImage d_img;
	int health;

	public Avatar(double x_, double y_) throws IOException {
		super();
		x = x_;
		y = y_;
		speed = 0;
		health = Const.AVATAR_HEALTH;
		this.radius = Const.AVATAR_RADIUS;
		type = Const.TYPE_AVATAR;
		try {
			String imagePath = "Image/shuttle-left.png";
			l_img = ImageIO.read(new File(imagePath));
			imagePath = "Image/shuttle-right.png";
			r_img = ImageIO.read(new File(imagePath));
			imagePath = "Image/shuttle-up.png";
			u_img = ImageIO.read(new File(imagePath));
			imagePath = "Image/shuttle-down.png";
			d_img = ImageIO.read(new File(imagePath));
			image = l_img;
		} catch (IOException e) {
			System.out.println("Can't load the avatar image");
		}

	}

	public void move(double diffSeconds) {
		reflectOnBorders();
		super.move(diffSeconds);
		Stats.X = (int) x;
		Stats.Y = (int) y;
		Stats.SPEED = (int) speed;
		Stats.HEALTH = health;
		colide();

	}

	public void colide(){
		GameObjectList collisions = world.getPhysicsSystem().getCollisions(this);
		for (int i = 0; i < collisions.size(); i++) {
			GameObject obj = collisions.get(i);

			int type = obj.type();

			// tree: shot is deleted
			if (type == Const.TYPE_CANON || type == Const.TYPE_AVATAR) { // this.isLiving=false;
				continue;
			} else if (type == Const.TYPE_METROID && obj.isLiving) {
				obj.isLiving = false;
				this.hasBeenHit(type);
			} else if (type == Const.TYPE_ROCKET && obj.isLiving) {
				Rocket rocket = (Rocket) obj;
				rocket.hasBeenShot();
				this.hasBeenHit(type);
			} else if (type == Const.TYPE_HEALTHUP && obj.isLiving) {
				obj.isLiving = false;
				this.health = health + 50;
			} else if (type == Const.TYPE_SPEEDUP && obj.isLiving) {
				obj.isLiving = false;
				this.speed = speed + 20;
				this.world.canon.speed = this.speed;
			} else if (type == Const.TYPE_HUNTER && obj.isLiving) {
				Hunter hunter = (Hunter) obj;
				hunter.hasBeenShot();
				this.hasBeenHit(type);
			} else if (type == Const.TYPE_HUNTER_PROJECTILE && obj.isLiving) {
				obj.isLiving = false;
				this.hasBeenHit(type);
			} else if (type == Const.TYPE_BOSS && obj.isLiving) {
				this.hasBeenHit(type);
			}else if (type == Const.TYPE_BOSS_PROJECTILE && obj.isLiving) {
				obj.isLiving = false;
				this.hasBeenHit(type);
			}else if(type == Const.TYPE_BOSS_ROCKET && obj.isLiving){
				BossRocket bRocket = (BossRocket) obj;
				bRocket.hasBeenShot();
				this.hasBeenHit(type);
			}

		}
	}

	private void hasBeenHit(int type) {
		if (type == Const.TYPE_ROCKET) {
			health -= 51/3;
		} else if (type == Const.TYPE_METROID) {
			health -= 20;
		} else if (type == Const.TYPE_HUNTER) {
			health -= 51/3;
		} else if (type == Const.TYPE_HUNTER_PROJECTILE) {
			health -= 10;
		} else if (type == Const.TYPE_BOSS) {
			health -= 10;
		}else if (type == Const.TYPE_BOSS_PROJECTILE) {
			health -= 50;
		}else if (type == Const.TYPE_BOSS_ROCKET) {
			health -= 42/3;
		}

		speed = Const.AVATAR_SPEED;
		this.world.canon.speed = this.speed;
		// if(health <= 0){
		// isLiving = false;
		// }
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

	// public void shoot(int direction) {
	// System.out.println("SCHUSS!");
	// }

	@Override
	int type() {
		return type;
	}
}
