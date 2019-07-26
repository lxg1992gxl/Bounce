package com.tutorial.main;

import java.awt.*;

public class SmartEnemy extends GameObject {

    private Handler handler;
    private GameObject player;

    public SmartEnemy(float x, float y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;

        for (int i = 0; i < handler.gameObjects.size(); i++) {
            if (handler.gameObjects.get(i).getId() == ID.Player) {
                player = handler.gameObjects.get(i);
            }
        }

    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 16, 16);

    }

    public void tick() {
        x += velX;
        y += velY;

        float diffX = x - player.getX() - 8;
        float diffY = y - player.getY() - 8;
        float distance = (float) Math.sqrt((x - player.getX()) * (x - player.getX()) + (y - player.getY()) * (y - player.getY()));

        velX = (float) ((-1.2 / distance) * diffX);
        velY = (float) ((-1.2 / distance) * diffY);

        if (x <= 0 || x >= Game.WIDTH - 16) velX *= (-1);
        if (y <= 0 || y >= Game.HEIGHT - 32) velY *= (-1);

        handler.addObject(new Trail(x, y, ID.Trail, Color.green, 16, 16, 0.02f, handler));

//        System.out.println(Math.sqrt((velX) * (velX) + (velY) * (velY)));

    }

    public void render(Graphics g) {
        g.setColor(Color.green);
        g.fillRect((int) x, (int) y, 16, 16);

    }


}
