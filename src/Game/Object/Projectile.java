package Game.Object;

import Game.Const.Const;
import Game.Utility.GameObjectList;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Projectile extends GameObject {

    BufferedImage horizontalShot;
    BufferedImage verticalShot;

    int type = Const.TYPE_SHOOT;

    public Projectile(double x, double y, double alfa) throws IOException {

        if (alfa == 0) {
            alfa = -Math.PI / 2;
        } else if (alfa == 90) {
            alfa = 2 * Math.PI;
        } else if (alfa == 180) {
            alfa = Math.PI / 2;
        } else {
            alfa = Math.PI;
        }

        this.alfa = alfa;
        this.speed = Const.PROJECTILE_SPEED;
        this.x = x;
        this.y = y;
        radius = Const.PROJECTILE_RADIUS;
        String imagePath = "Image/shot.png";
        horizontalShot = ImageIO.read(new File(imagePath));
        imagePath = "Image/shotVertical.png";
        verticalShot = ImageIO.read(new File(imagePath));
        this.image = verticalShot;

    }

    public void move(double diffSeconds) {
        super.move(diffSeconds);
        colide();
    }

    public void colide() {
        GameObjectList collisions = world.getPhysicsSystem().getCollisions(this);
        for (int i = 0; i < collisions.size(); i++) {
            GameObject obj = collisions.get(i);

            int type = obj.type();

            // dont collide with player (avatar/canon)
            if (type == Const.TYPE_AVATAR || type == Const.TYPE_CANON) { //this.isLiving=false;
                continue;
            }

            // shot hit metroid
            else if (type == Const.TYPE_METROID && obj.isLiving) {
                Metroid meteor = (Metroid) obj;
                meteor.hasBeenShot();
                this.isLiving = false;
            }

            // shot hit rocket
            else if (type == Const.TYPE_ROCKET && obj.isLiving) {
                Rocket rocket = (Rocket) obj;
                rocket.hasBeenShot();
                this.isLiving = false;
            }
            // shot hit hunter
            else if (type == Const.TYPE_HUNTER && obj.isLiving) {
                Hunter hunter = (Hunter) obj;
                hunter.hasBeenShot();
                this.isLiving = false;
            }
            // shot hit boss
            else if (type == Const.TYPE_BOSS && obj.isLiving) {
                Boss boss = (Boss) obj;
                boss.hasBeenShot();
                this.isLiving = false;
            }
            //shot hit boss_rocket
            else if (type == Const.TYPE_BOSS_ROCKET && obj.isLiving){
                BossRocket bRocket = (BossRocket) obj;
                bRocket.hasBeenShot();
                this.isLiving = false;
            }
        }
    }

    public void rotate(int aimAngle) {
        switch (aimAngle) {
            case 0:
                image = verticalShot;
                break;
            case 90:
                image = horizontalShot;
                break;
            case 180:
                image = verticalShot;
                break;
            case 270:
                image = horizontalShot;
                break;
        }
    }

    @Override
    int type() {
        // TODO Auto-generated method stub
        return type;
    }
}
