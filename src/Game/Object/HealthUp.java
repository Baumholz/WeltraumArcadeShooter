package Game.Object;

import Game.Const.Const;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class HealthUp extends GameObject {
	
	public HealthUp(double x, double y){
		this.x = x;
		this.y = y;
		radius = Const.HEALTH_UP_RADIUS;
		type = Const.TYPE_HEALTHUP; 
		
		String imagePath = "Image/healthup.png";
		try {
			image = ImageIO.read(new File(imagePath));
		} catch (IOException e) {
			System.out.println("healthup image load failed");
		}
	}
	
	public void move(double diffSeconds) {	
	}
	
	@Override
	int type() {
		// TODO Auto-generated method stub
		return type;
	}

}
