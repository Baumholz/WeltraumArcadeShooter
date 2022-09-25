package Game.Const;

public final class Const {
    // size of the world
    public static final int WORLD_WIDTH = 4000;
    public static final int WORLD_HEIGHT = WORLD_WIDTH;
    // size of the displayed part of the world
    public static final int WORLDPART_WIDTH = 1000;
    public static final int WORLDPART_HEIGHT = 720;
    public static final int SIDEBAR_WIDTH = 200;

    // border: when to scroll
    public static final int SCROLL_BOUNDS = 250;
    public static final int SLEEP_TIME = 30;

    public static final int PROJECTILE_SPEED = 1500;
    public static final int ROCKET_SPEED = 130;
    public static final int AVATAR_SPEED = 200;
    public static final int HUNTER_SPEED = 120;
    public static final int HUNTER_PROJECTILE_SPEED = 300;
    public static final int BOSS_SPEED = 70;
    public static final int BOSS_PROJECTILE_SPEED = 250;
    public static final int BOSS_ROCKET_SPEED = 130;

    public static final int STAR_COUNT = WORLD_WIDTH / 5;

    public static final int SPRITE_SPEED = 20;
    public static final int FIRE_RATE = 100;

    public static final int METROID_SPAWN_RATE = 2;
    public static final int ROCKET_SPAWN_RATE = 60;
    public static final int HEALTHUP_SPAWN_RATE = 100;
    public static final int SPEEDUP_SPAWN_RATE = 300;
    public static final int HUNTER_SPAWN_RATE = 200;
    public static final int BOSS_SPAWN_RATE = 1200;


    public static final int METROID_RADIUS = 50;
    public static final int PROJECTILE_RADIUS = 0;
    public static final int AVATAR_RADIUS = 37;
    public static final int KANNON_RADIUS = 50;
    public static final int HEALTH_UP_RADIUS = 20;
    public static final int SPEED_UP_RADIUS = 20;
    public static final int HUNTER_RADIUS = 50;
    public static final int ROCKET_RADIUS = 20;
    public static final int HUNTER_PROJECTILE_RADIUS = 10;
    public static final int BOSS_RADIUS = 100;
    public static final int BOSS_PROJECTILE_RADIUS = 30;
    public static final int BOSS_ROCKET_RADIUS = 20;

    public static final int METROID_HEALTH = 3;
    public static final int AVATAR_HEALTH = 20000;
    public static final int ROCKET_HEALTH = 1;
    public static final int HUNTER_HEALTH = 10;
    public static final int BOSS_HEALTH = 50;
    public static final int BOSS_ROCKET_HEALTH = 1;

    public static final int TYPE_SHOOT = 1;
    public static final int TYPE_METROID = 2;
    public static final int TYPE_AVATAR = 3;
    public static final int TYPE_CANON = 4;
    public static final int TYPE_ROCKET = 5;
    public static final int TYPE_HEALTHUP = 6;
    public static final int TYPE_SPEEDUP = 7;
    public static final int TYPE_HUNTER = 8;
    public static final int TYPE_HUNTER_PROJECTILE = 9;
    public static final int TYPE_BOSS = 10;
    public static final int TYPE_BOSS_PROJECTILE = 11;
    public static final int TYPE_BOSS_ROCKET = 12;

    public static final double HUNTER_RANGE = 500;
    public static final double BOSS_RANGE = 500;
    public static final double BOSS_RANGE_MAIN_GUN = 3000;
    public static final double BOSS_ROCKET_RANGE = 3000;

    public static final double HUNTER_FOLLOW_RANGE = 300;
    public static final double BOSS_FOLLOW_RANGE = 300;

    public static final double HUNTER_FIRE_RATE = 5;
    public static final double BOSS_FIRE_RATE = 5;
    public static final double BOSS_FIRE_RATE_MAIN_GUN = 60;
    public static final double BOSS_FIRE_RATE_ROCKET = 300;
}
