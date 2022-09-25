package Game.Object;

import Game.Const.Const;
import Game.Const.Stats;
import Game.Utility.GameObjectList;
import Game.Utility.Sounds;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Boss extends ExplosionObjects {

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
    double fireRateMAINGUN;
    double fireRateRocket;
    double counter;
    double counterMainGUN;
    double counterRocket;
    BufferedImage north;
    BufferedImage northEast;
    BufferedImage east;
    BufferedImage southEast;
    BufferedImage south;
    BufferedImage southWest;
    BufferedImage west;
    BufferedImage northWest;

    public Boss(double x, double y) {
        radius = Const.BOSS_RADIUS;
        this.x = x;
        this.y = y;
        speed = Const.BOSS_SPEED;
        live = Const.BOSS_HEALTH;
        type = Const.TYPE_BOSS;
        counter = 0;
        fireRate = Const.BOSS_FIRE_RATE;
        fireRateMAINGUN = Const.BOSS_FIRE_RATE_MAIN_GUN;
        fireRateRocket = Const.BOSS_FIRE_RATE_ROCKET;

        try {
            String imagePath = "Image/boss_n.png";
            north = ImageIO.read(new File(imagePath));
            imagePath = "Image/boss_ne.png";
            northEast = ImageIO.read(new File(imagePath));
            imagePath = "Image/boss_e.png";
            east = ImageIO.read(new File(imagePath));
            imagePath = "Image/boss_se.png";
            southEast = ImageIO.read(new File(imagePath));
            imagePath = "Image/boss_s.png";
            south = ImageIO.read(new File(imagePath));
            imagePath = "Image/boss_sw.png";
            southWest = ImageIO.read(new File(imagePath));
            imagePath = "Image/boss_w.png";
            west = ImageIO.read(new File(imagePath));
            imagePath = "Image/boss_nw.png";
            northWest = ImageIO.read(new File(imagePath));
            image = north;

        } catch (IOException e) {
            System.out.println("Can't load the boss image");
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
        double followRange = Const.BOSS_FOLLOW_RANGE;

        if (distanceX < followRange && distanceY < followRange) {
            speed = 0;
        } else {
            speed = Const.BOSS_SPEED;
        }
    }

    public void shoot() {
        if (fireRate < counter) {
            distanceX = Math.abs(x - world.avatar.x);
            distanceY = Math.abs(y - world.avatar.y);
            double range = Const.BOSS_RANGE;
            if (distanceX < range && distanceY < range) {
                HunterProjectile hunterProjectile = new HunterProjectile(x + Const.BOSS_RADIUS / 2, y + Const.BOSS_RADIUS / 2);
                HunterProjectile hunterProjectile2 = new HunterProjectile(x + Const.BOSS_RADIUS + 5, y + Const.BOSS_RADIUS);
                HunterProjectile hunterProjectile3 = new HunterProjectile(x + Const.BOSS_RADIUS/4, y + Const.BOSS_RADIUS/4);
                world.objectList.add(hunterProjectile);
                world.objectList.add(hunterProjectile2);
                world.objectList.add(hunterProjectile3);
            }
            counter = 0;
        }
        if (fireRateMAINGUN < counterMainGUN) {
            distanceX = Math.abs(x - world.avatar.x);
            distanceY = Math.abs(y - world.avatar.y);
            double range = Const.BOSS_RANGE_MAIN_GUN;
            if (distanceX < range && distanceY < range) {
               BossProjectile bossProjectile = new BossProjectile(x + Const.BOSS_RADIUS / 2, y + Const.BOSS_RADIUS / 2);
                world.objectList.add(bossProjectile);
            }
            counterMainGUN = 0;
        }
        if (fireRateRocket < counterRocket){
            distanceX = Math.abs(x - world.avatar.x);
            distanceY = Math.abs(y - world.avatar.y);
            double range = Const.BOSS_ROCKET_RANGE;
            if (distanceX < range && distanceY < range){
                BossRocket bossRocket = new BossRocket(x + Const.BOSS_RADIUS / 2, y + Const.BOSS_RADIUS / 2);
                world.objectList.add(bossRocket);
            }
            counterRocket = 0;
        }
        counter++;
        counterMainGUN++;
        counterRocket++;
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

    public void rotate() {

        oldXH = oldX + 1;
        oldXL = oldX - 1;
        oldYH = oldY + 1;
        oldYL = oldY - 1;

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

    @Override
    int type() {
        return Const.TYPE_BOSS;
    }
}
