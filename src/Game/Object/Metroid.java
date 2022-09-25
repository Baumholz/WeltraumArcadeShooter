package Game.Object;

import Game.Const.Const;
import Game.Utility.Sounds;
import Game.Const.Stats;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

import javax.imageio.ImageIO;

public class Metroid extends ExplosionObjects {

    BufferedImage[] images;
 
    int counter;
    int img_counter;
    int live = Const.METROID_HEALTH;

    public Metroid(double x_, double y_) {
    	super();
        x = x_;
        y = y_;
        type = Const.TYPE_METROID;
        speed = ThreadLocalRandom.current().nextDouble(50, 350);
        alfa = ThreadLocalRandom.current().nextDouble(0, 360);
        radius = Const.METROID_RADIUS;
        images = new BufferedImage[5];
       
        counter = 0;
        img_counter = 0;

        try {
            String imagePath = "Image/asteroid1.png";
            images[0] = ImageIO.read(new File(imagePath));
            imagePath = "Image/asteroid2.png";
            images[1] = ImageIO.read(new File(imagePath));
            imagePath = "Image/asteroid3.png";
            images[2] = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            System.out.println("Can't load the asteroid image");
        }

        this.image = images[2];
    }

    public void move(double diffSeconds) {
        super.move(diffSeconds);
    }

    @Override
    int type() {
        return type;
    }

    public void hasBeenShot() {
        new Sounds().playExplosion();
        live--;
        if (live <= 0) {
        	isLiving = false;
            Stats.KILLS++;
        } else {
            image = images[live - 1];
        }

    }

}
