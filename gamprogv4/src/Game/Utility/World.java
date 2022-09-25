package Game.Utility;

import Game.Const.Const;
import Game.Const.Stats;
import Game.Graphic.GraphicSystem;
import Game.Graphic.Sidebar;
import Game.Object.*;
import Game.Physics.PhysicsSystem;

import java.util.ArrayList;
import java.util.Random;

public class World {

    private GraphicSystem graphicSystem;
    private PhysicsSystem physicsSystem;
    private InputSystem inputSystem;
    private UserInput userInput;
    public Avatar avatar;
    public Canon canon;
  
    private Random r = new Random();
    public GameObjectList objectList = new GameObjectList();
    public ArrayList<Star> stars = new ArrayList<Star>();
    public Sidebar sidebar = null;

    // top left corner of the displayed pane of the world
    public double worldPartX = 0;
    public double worldPartY = 0;

    public World() {
        setPhysicsSystem(new PhysicsSystem(this));
    }

    public void init() {
        try {
            // Create Game.Object.Avatar and Game.Object.Canon
            avatar = new Avatar(Const.WORLD_WIDTH / 2, Const.WORLD_HEIGHT / 2);
            objectList.add(avatar);
            canon = new Canon(Const.WORLD_WIDTH / 2, Const.WORLD_HEIGHT / 2);
            objectList.add(canon);

            BossRocket bossRocket = new BossRocket(2500,2500);
            objectList.add(bossRocket);

            // Create Stars
            for (int i = 0; i <= Const.STAR_COUNT; i++) {
                int x = r.nextInt(Const.WORLD_WIDTH);
                int y = r.nextInt(Const.WORLD_HEIGHT);
                Star star = new Star(x, y);
                stars.add(star);
            }
           } catch (Exception e) {
            System.out.println("Error create Game.Object.Avatar / Game.Object.Canon / Stars");
        }

    }

    public void run() {
    
        int frameCount = 0;
        long lastTick = System.currentTimeMillis();

        // Game.Utility.Main Game Loop
        while (true) {
        	
        	
            long currentTick = System.currentTimeMillis();
            double diffSeconds = (currentTick - lastTick) / 1000.0;
            lastTick = currentTick;
            
          
            userInput = inputSystem.getUserInput();
            if (userInput != null) {
                inputSystem.command(avatar, canon, userInput);
                // processUserInput();
            }

            // Cap Framerate
            try {
                Thread.sleep(Const.SLEEP_TIME);
            } catch (Exception ex) {
            }

            // remove dead objects (Garbage Collection does the rest)
            for (int i = 0; i < objectList.size(); i++) {
                objectList.get(i).move(diffSeconds);
                if (!objectList.get(i).isLiving) {
                    objectList.remove(i);
                    System.out.println(objectList.size());
                }
            }

            // adjust displayed pane of the world
            this.adjustWorldPart();
            graphicSystem.clear();
            for (int i = 0; i < objectList.size(); i++) {
                graphicSystem.draw(objectList.get(i));
            }


            //Spawn Metroids
            if (frameCount % Const.METROID_SPAWN_RATE == 0) {
                double x = Const.WORLD_WIDTH * r.nextDouble();
                double y = Const.WORLD_HEIGHT * r.nextDouble();
                if ((x < avatar.x - Const.WORLDPART_WIDTH || x > avatar.x + Const.WORLDPART_WIDTH)
                        && (y < avatar.y - Const.WORLDPART_HEIGHT || y > avatar.y + Const.WORLDPART_HEIGHT)) {
                    objectList.add(new Metroid(x, y));
                    System.out.println("matroid spawned");
                }
            }
            //Spawn Rockets
            if (frameCount % Const.ROCKET_SPAWN_RATE == 0) {
                double x = Const.WORLD_WIDTH * r.nextDouble();
                double y = Const.WORLD_HEIGHT * r.nextDouble();
                if ((x < avatar.x - Const.WORLDPART_WIDTH || x > avatar.x + Const.WORLDPART_WIDTH)
                        && (y < avatar.y - Const.WORLDPART_HEIGHT || y > avatar.y + Const.WORLDPART_HEIGHT)) {
                    objectList.add(new Rocket(x, y));
                    System.out.println("rocket spawned");
                }
            }

            //Spawn healthups
            if (frameCount % Const.HEALTHUP_SPAWN_RATE == 0) {
                double x = Const.WORLD_WIDTH * r.nextDouble();
                double y = Const.WORLD_HEIGHT * r.nextDouble();
                if ((x < avatar.x - Const.WORLDPART_WIDTH || x > avatar.x + Const.WORLDPART_WIDTH)
                        && (y < avatar.y - Const.WORLDPART_HEIGHT || y > avatar.y + Const.WORLDPART_HEIGHT)) {
                    objectList.add(new HealthUp(x, y));
                    System.out.println("health-up spawned");
                }
            }

            //Spawn speedups
            if (frameCount % Const.SPEEDUP_SPAWN_RATE == 0) {
                double x = Const.WORLD_WIDTH * r.nextDouble();
                double y = Const.WORLD_HEIGHT * r.nextDouble();
                if ((x < avatar.x - Const.WORLDPART_WIDTH || x > avatar.x + Const.WORLDPART_WIDTH)
                        && (y < avatar.y - Const.WORLDPART_HEIGHT || y > avatar.y + Const.WORLDPART_HEIGHT)) {
                    objectList.add(new SpeedUp(x, y));
                    System.out.println("speed-up spawned");
                }
            }

            //Spawn Game.Object.Hunter
            if (frameCount % Const.HUNTER_SPAWN_RATE == 0) {
                double x = Const.WORLD_WIDTH * r.nextDouble();
                double y = Const.WORLD_HEIGHT * r.nextDouble();
                if ((x < avatar.x - Const.WORLDPART_WIDTH || x > avatar.x + Const.WORLDPART_WIDTH)
                        && (y < avatar.y - Const.WORLDPART_HEIGHT || y > avatar.y + Const.WORLDPART_HEIGHT)) {
                    objectList.add(new Hunter(x, y));
                    System.out.println("Game.Object.Hunter spawned");
                }
            }

            //Spawn Game.Object.Boss
            if (frameCount % Const.BOSS_SPAWN_RATE == 0) {
                double x = Const.WORLD_WIDTH * r.nextDouble();
                double y = Const.WORLD_HEIGHT * r.nextDouble();
                if ((x < avatar.x - Const.WORLDPART_WIDTH || x > avatar.x + Const.WORLDPART_WIDTH)
                        && (y < avatar.y - Const.WORLDPART_HEIGHT || y > avatar.y + Const.WORLDPART_HEIGHT)) {
                    objectList.add(new Boss(x, y));
                    System.out.println("Game.Object.Boss spawned");
                }
            }
            // check avatar's collisions
            // for(Game.Object.GameObject obj : objectList){
            //
            // getPhysicsSystem().getCollisions(obj);
            //
            // if(avatar.x > obj.x && avatar.x < obj.x + obj.radius &&
            // avatar.y > obj.y && avatar.y < obj.y + obj.radius){
            // System.out.println("collision");
            //
            //
            // //return;
            // }
            // }

            //Increase framecounter
            if (frameCount > 10000) {
                frameCount = 0;
            }
            frameCount++;
            //redraw world and update stats
            graphicSystem.redraw();
            sidebar.updateStats();
            if(Stats.HEALTH< 1 ) {
            	avatar.isLiving = false;
            }
            //check for game over
            if (!avatar.isLiving) {
            	deathScreen();
                return;
            }
        }

    }

    private void deathScreen() {
		// TODO Auto-generated method stub
		graphicSystem.draw(new TextObject(200,200,"YOU DIED"));
		  graphicSystem.redraw();
	}

	// adjust display according to avatar and bounds
    private void adjustWorldPart() {
        final int RIGHT_END = Const.WORLD_WIDTH - Const.WORLDPART_WIDTH;
        final int BOTTOM_END = Const.WORLD_HEIGHT - Const.WORLDPART_HEIGHT;

        // if avatar is too much right in display ...
        if (avatar.x > worldPartX + Const.WORLDPART_WIDTH - Const.SCROLL_BOUNDS) {
            // ... adjust display
            worldPartX = avatar.x + Const.SCROLL_BOUNDS - Const.WORLDPART_WIDTH;
            if (worldPartX >= RIGHT_END) {
                worldPartX = RIGHT_END;
            }
        }

        // same left
        else if (avatar.x < worldPartX + Const.SCROLL_BOUNDS) {
            worldPartX = avatar.x - Const.SCROLL_BOUNDS;
            if (worldPartX <= 0) {
                worldPartX = 0;
            }
        }

        // same bottom
        if (avatar.y > worldPartY + Const.WORLDPART_HEIGHT - Const.SCROLL_BOUNDS) {
            worldPartY = avatar.y + Const.SCROLL_BOUNDS - Const.WORLDPART_HEIGHT;
            if (worldPartY >= BOTTOM_END) {
                worldPartY = BOTTOM_END;
            }
        }

        // same top
        else if (avatar.y < worldPartY + Const.SCROLL_BOUNDS) {
            worldPartY = avatar.y - Const.SCROLL_BOUNDS;
            if (worldPartY <= 0) {
                worldPartY = 0;
            }
        }

    }

    protected void setGraphicSystem(GraphicSystem p) {
        graphicSystem = p;
    }

    protected void setInputSystem(InputSystem p) {
        inputSystem = p;
    }

    public PhysicsSystem getPhysicsSystem() {
        return physicsSystem;
    }

    public void setPhysicsSystem(PhysicsSystem physicsSystem) {
        this.physicsSystem = physicsSystem;
    }
}
