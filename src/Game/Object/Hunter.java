package Game.Object;

import Game.Const.Const;
import Game.Const.Stats;
import Game.Utility.GameObjectList;
import Game.Utility.Sounds;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Hunter extends ExplosionObjects {

    double distanceY;
    double distanceX;
    double oldX;
    double oldY;
    double oldXH;
    double oldXL;
    double oldYH;   //xhigh
    double oldYL;   //xLOW
    int live;
    double fireRate;
    double counter;
    BufferedImage north;
    BufferedImage northEast;
    BufferedImage east;
    BufferedImage southEast;
    BufferedImage south;
    BufferedImage southWest;
    BufferedImage west;
    BufferedImage northWest;


    public Hunter(double x, double y) {

        radius = Const.HUNTER_RADIUS;
        this.x = x;
        this.y = y;
        speed = Const.HUNTER_SPEED;
        live = Const.HUNTER_HEALTH;
        type = Const.TYPE_HUNTER;
        counter = 0;
        fireRate = Const.HUNTER_FIRE_RATE;
        try {
            String imagePath = "Image/hunter_n.png";
            north = ImageIO.read(new File(imagePath));
            imagePath = "Image/hunter_ne.png";
            northEast = ImageIO.read(new File(imagePath));
            imagePath = "Image/hunter_e.png";
            east = ImageIO.read(new File(imagePath));
            imagePath = "Image/hunter_se.png";
            southEast = ImageIO.read(new File(imagePath));
            imagePath = "Image/hunter_s.png";
            south = ImageIO.read(new File(imagePath));
            imagePath = "Image/hunter_sw.png";
            southWest = ImageIO.read(new File(imagePath));
            imagePath = "Image/hunter_w.png";
            west = ImageIO.read(new File(imagePath));
            imagePath = "Image/hunter_nw.png";
            northWest = ImageIO.read(new File(imagePath));

            image = north;
        } catch (IOException e) {
            System.out.println("Can't load the hunter image");
        }

    }

    public void move(double diffSeconds) {
        setDestination(world.avatar.x - Const.AVATAR_RADIUS * 0.85, world.avatar.y - Const.AVATAR_RADIUS * 0.85);
        rotate();
        super.move(diffSeconds);
        shoot();
        if (deathCounter <= -1) {
            isLiving = false;
        } else if (deathCounter < 3) {
            this.image = this.explosion[deathCounter];
            deathCounter--;
        }
        colide();

    }

    public void setDestination(double dx, double dy) {
        alfa = Math.atan2(dy - y, dx - x);
        distanceX = Math.abs(x - world.avatar.x);
        distanceY = Math.abs(y - world.avatar.y);
        double followRange = Const.HUNTER_FOLLOW_RANGE;

        if (distanceX < followRange && distanceY < followRange) {
            speed = 0;
        } else {
            speed = Const.HUNTER_SPEED;
        }
    }

    public void colide() {
        GameObjectList collisions = world.getPhysicsSystem().getCollisions(this);
        for (int i = 0; i < collisions.size(); i++) {
            GameObject obj = collisions.get(i);

            int type = obj.type();

            // dont collide with hunter
            if (type == Const.TYPE_HUNTER) { //this.isLiving=false;
                continue;
            }

            //  hit metroid
            else if (type == Const.TYPE_METROID && obj.isLiving) {
                Metroid meteor = (Metroid) obj;
                meteor.isLiving = false;
                hasBeenShot();
                hasBeenShot();
            }
        }
    }

    public void shoot() {
        if (fireRate < counter) {
            distanceX = Math.abs(x - world.avatar.x);
            distanceY = Math.abs(y - world.avatar.y);
            double range = Const.HUNTER_RANGE;
            if (distanceX < range && distanceY < range) {
                HunterProjectile hunterProjectile = new HunterProjectile(x, y);
                world.objectList.add(hunterProjectile);
            }
            counter = 0;
        }
        counter++;
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
        if (live <= 0) {
            deathCounter--;
            Stats.KILLS++;
        }
    }

    int type() {
        return Const.TYPE_HUNTER;
    }
}
