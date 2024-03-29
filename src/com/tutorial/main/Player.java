package com.tutorial.main;

import java.awt.*;
import java.util.Random;

public class Player extends GameObject {

    Random r = new Random();
    Handler handler;

    public Player(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;

//        velX = r.nextInt(5)+1;
//        velY = r.nextInt(5)+1;

    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 32, 32);

    }

    public void tick() {
        x += velX;
        y += velY;
        x = Game.clamp(x, 0, Game.WIDTH - 37);
        y = Game.clamp(y, 0, Game.HEIGHT - 60);

        collision();

        handler.addObject(new Trail(x, y, ID.Trail, Color.white, 32, 32, 0.1f, handler));


    }

    private void collision() {
        for (int i = 0; i < handler.gameObjects.size(); i++) {
            GameObject tempObject = handler.gameObjects.get(i);

            if (tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FastEnemy || tempObject.getId() == ID.SmartEnemy) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    //collision code
                    if (tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.SmartEnemy) HUD.HEALTH -= 2;
                    if (tempObject.getId() == ID.FastEnemy) HUD.HEALTH -= 10;
                }
            }
        }
    }

    public void render(Graphics g) {

//        if (id == ID.Player) g.setColor(Color.white);
//        if (id == ID.Player2)

        g.setColor(Color.white);
        g.fillRect((int) x, (int) y, 32, 32);

    }
}
