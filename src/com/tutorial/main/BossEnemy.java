package com.tutorial.main;

import java.awt.*;
import java.util.Random;

public class BossEnemy extends GameObject {

    private Handler handler;
    Random r = new Random();

    public BossEnemy(float x, float y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;

        velX = 2;
        velY = 2;

    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 16, 16);

    }

    public void tick() {
        x += velX;
        y += velY;


        if (x <= 0 || x >= Game.WIDTH - 16) velX *= -1;
        if (y <= 0 || y >= Game.HEIGHT - 32) velY *= -1;

//        handler.addObject(new Trail(x, y, ID.Trail, Color.orange, 16, 16, 0.02f, handler));
        int spawn = r.nextInt(10);
        if (spawn==0) handler.addObject(new BossEnemyBullet(x, y, ID.BossEnemy, handler));

    }

    public void render(Graphics g) {
        g.setColor(Color.orange);
        g.drawRect((int) x, (int) y, 50, 50);

    }


}
