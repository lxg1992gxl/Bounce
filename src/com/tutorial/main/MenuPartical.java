package com.tutorial.main;

import java.awt.*;
import java.util.Random;

public class MenuPartical extends GameObject {

    private Handler handler;
    Random r = new Random();


    private Color color;

    public MenuPartical(float x, float y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;


        velX = r.nextInt(4) + 1;
        velY = r.nextInt(4) + 1;

        color = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));

    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 16, 16);

    }

    public void tick() {
        x += velX;
        y += velY;

        if (x <= 0 || x >= Game.WIDTH - 8) velX *= -1;
        if (y <= 0 || y >= Game.HEIGHT - 32) velY *= -1;

        handler.addObject(new Trail(x, y, ID.Trail, color, 8, 8, 0.02f, handler));

    }

    public void render(Graphics g) {
        g.setColor(color);
        g.fillRect((int) x, (int) y, 8, 8);

//        if (Game.gameState != Game.STATE.Game) {
            for (int i = 0; i < 8; i++) {
                handler.addObject(new MenuPartical(r.nextInt(Game.WIDTH - 100) + 50, r.nextInt(Game.HEIGHT - 100) + 50, ID.MenuPartical, handler));
            }
//        }
    }


}
