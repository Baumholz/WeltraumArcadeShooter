package Game.Object;

import Game.Const.Const;
import Game.Utility.GameObjectList;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class HunterProjectile extends GameObject {

    public HunterProjectile(double x, double y) {

        this.x = x;
        this.y = y;
        radius = Const.HUNTER_PROJECTILE_RADIUS;
        type = Const.TYPE_HUNTER_PROJECTILE;
        speed = Const.HUNTER_PROJECTILE_SPEED;
        String imagePath = "Image/hunter_projectile.png";
        try {
            image = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        double avatarX = world.avatar.x - Const.AVATAR_RADIUS * 0.85;
        double avatarY = world.avatar.y - Const.AVATAR_RADIUS * 0.85;

        alfa = Math.atan2(avatarY - y, avatarX - x);

    }

    public void move(double diffSeconds) {
        super.move(diffSeconds);
        colide();
    }

    public void colide(){
        GameObjectList collisions = world.getPhysicsSystem().getCollisions(this);
        for (int i = 0; i < collisions.size(); i++) {
            GameObject obj = collisions.get(i);

            int type = obj.type();

            // dont collide with
            if (type == Const.TYPE_HUNTER_PROJECTILE) { //this.isLiving=false;
                continue;
            }

            // shot hit metroid
            else if (type == Const.TYPE_METROID && obj.isLiving) {
                Metroid meteor = (Metroid) obj;
                meteor.hasBeenShot();
                this.isLiving = false;
            }
        }
    }


    @Override
    int type() {
        return Const.TYPE_HUNTER_PROJECTILE;
    }
}
