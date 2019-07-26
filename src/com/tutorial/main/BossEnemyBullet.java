package com.tutorial.main;

import java.awt.*;
import java.util.Random;

public class BossEnemyBullet extends GameObject {

    private Handler handler;
    private GameObject player;

    Random r = new Random();

    public BossEnemyBullet(float x, float y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;

        for (int i = 0; i < handler.gameObjects.size(); i++) {
            if (handler.gameObjects.get(i).getId() == ID.Player) {
                player = handler.gameObjects.get(i);
            }
        }

        float diffX = x - player.getX() - 16;
        float diffY = y - player.getY() - 16;
        float distance = (float) Math.sqrt((x - player.getX()) * (x - player.getX()) + (y - player.getY()) * (y - player.getY()));

        velX = (float) ((-5 / distance) * diffX);
        velY = (float) ((-5 / distance) * diffY);

    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 16, 16);

    }

    public void tick() {
        x += velX;
        y += velY;


//        if (x <= 0 || x >= Game.WIDTH - 16) velX *= -1;
//        if (y <= 0 || y >= Game.HEIGHT - 32) velY *= -1;
        if (y<=0||y>= Game.HEIGHT) handler.removeObject(this);
        if (x<=0||x>= Game.WIDTH) handler.removeObject(this);

        handler.addObject(new Trail(x, y, ID.Trail, Color.gray, 2, 2, 0.02f, handler));

    }

    public void render(Graphics g) {
        g.setColor(Color.orange);
        g.fillRect((int) x, (int) y, 2, 2);

    }


}
