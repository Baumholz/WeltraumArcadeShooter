package Game.Object;

import Game.Const.Const;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpeedUp extends GameObject {
	
	public SpeedUp(double x, double y){
		this.x = x;
		this.y = y;
		radius = Const.SPEED_UP_RADIUS;
		type = Const.TYPE_SPEEDUP; 
		
		String imagePath = "Image/speedup.png";
		try {
			image = ImageIO.read(new File(imagePath));
		} catch (IOException e) {
			System.out.println("speedup image load failed");
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
