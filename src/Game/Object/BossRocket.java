package Game.Object;

import Game.Const.Const;
import Game.Const.Stats;
import Game.Utility.GameObjectList;
import Game.Utility.Sounds;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BossRocket extends ExplosionObjects {

    double oldX;
    double oldY;
    double oldXH;
    double oldXL;
    double oldYH;
    double oldYL;
    int live;
    BufferedImage north;
    BufferedImage northEast;
    BufferedImage east;
    BufferedImage southEast;
    BufferedImage south;
    BufferedImage southWest;
    BufferedImage west;
    BufferedImage northWest;

    public BossRocket(double x, double y){

        live = Const.BOSS_ROCKET_HEALTH;
        oldX = x;
        oldY = y;
        this.x = x;
        this.y = y;
        this.radius = Const.BOSS_ROCKET_RADIUS;
        this.speed = Const.BOSS_ROCKET_SPEED;
        type = Const.TYPE_BOSS_ROCKET;
        try {
            String imagePath = "Image/boss_rocket_n.png";
            north = ImageIO.read(new File(imagePath));
            imagePath = "Image/boss_rocket_ne.png";
            northEast = ImageIO.read(new File(imagePath));
            imagePath = "Image/boss_rocket_e.png";
            east = ImageIO.read(new File(imagePath));
            imagePath = "Image/boss_rocket_se.png";
            southEast = ImageIO.read(new File(imagePath));
            imagePath = "Image/boss_rocket_s.png";
            south = ImageIO.read(new File(imagePath));
            imagePath = "Image/boss_rocket_sw.png";
            southWest = ImageIO.read(new File(imagePath));
            imagePath = "Image/boss_rocket_w.png";
            west = ImageIO.read(new File(imagePath));
            imagePath = "Image/boss_rocket_nw.png";
            northWest = ImageIO.read(new File(imagePath));

            image = north;
        } catch (IOException e) {
            System.out.println("Can't load the boss_rocket image");
        }
    }

    public void move(double diffSeconds) {
        setDestination(world.avatar.x - Const.AVATAR_RADIUS * 0.85, world.avatar.y - Const.AVATAR_RADIUS * 0.85);
        rotate();
        super.move(diffSeconds);
        if (deathCounter <= -1) {
            isLiving = false;
        } else if (deathCounter < 3) {
            this.image = this.explosion[deathCounter];
            deathCounter--;
        }
        colide();
    }

    public void colide() {
        GameObjectList collisions = world.getPhysicsSystem().getCollisions(this);
        for (int i = 0; i < collisions.size(); i++) {
            GameObject obj = collisions.get(i);

            int type = obj.type();

            // dont collide with player (Game.Object.Rocket)
            if (type == Const.TYPE_BOSS_ROCKET) { // this.isLiving=false;
                continue;
            } // rocket hit metroid
            else if (type == Const.TYPE_METROID && obj.isLiving) {
                Metroid meteor = (Metroid) obj;
                meteor.isLiving = false;
                this.hasBeenShot();
            } else if (type == Const.TYPE_HUNTER && obj.isLiving) {
                Hunter hunter = (Hunter) obj;
                for (int j = 0; j < Const.HUNTER_HEALTH; j++) {
                    hunter.hasBeenShot();
                }
                this.isLiving = false;
            }
        }
    }

    public void setDestination(double dx, double dy) {
        alfa = Math.atan2(dy - y, dx - x);
    }

    public void rotate() {

        oldXH = oldX + 2;
        oldXL = oldX - 2;
        oldYH = oldY + 2;
        oldYL = oldY - 2;

        if (x > oldX && y == oldY || (x > oldX && y > oldYL && y < oldYH)) {
            image = east;
        } else if (x == oldX && y > oldY || (x > oldXL && x < oldXH && y > oldY)) {
            image = south;
        } else if (x < oldX && y == oldY || (x < oldX && y > oldYL && y < oldYH)) {
            image = west;
        } else if (x == oldX && y < oldY || (x > oldXL && x < oldXH && y < oldY)) {
            image = north;
        } else if (x > oldX && y > oldY) {
            image = southEast;
        } else if (x > oldX && y < oldY) {
            image = northEast;
        } else if (x < oldX && y < oldY) {
            image = northWest;
        } else if (x < oldX && y > oldY) {
            image = southWest;
        }

        oldX = x;
        oldY = y;

    }

    public void hasBeenShot() {
        new Sounds().playExplosion();
        live--;
        System.out.println("rocket has been shot");
        if (live <= 0) {
            deathCounter--;
            Stats.KILLS++;
        }
    }


    @Override
    int type() {
        return Const.TYPE_BOSS_ROCKET;
    }
}
