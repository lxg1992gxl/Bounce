package com.tutorial.main;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Menu extends MouseAdapter {
    private Game game;
    private Handler handler;
    private HUD hud;
    Random r = new Random();


    public Menu(Game game, HUD hud, Handler handler) {
        this.game = game;
        this.hud = hud;
        this.handler = handler;

    }

    public void mousePressed(MouseEvent e) {

        int mx = e.getX();
        int my = e.getY();

        if (Game.gameState == Game.STATE.Menu) {
            //play button
            if (mouseOver(mx, my, 240, 300, 100, 100)) {
                Game.gameState = Game.STATE.Game;
                hud.setLevel(1);
                hud.setScore(0);
                handler.gameObjects.clear();
                handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler));
                handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 100) + 50, r.nextInt(Game.HEIGHT - 100) + 50, ID.MenuPartical, handler));
                AudioPlayer.getSound("click").play();
            }
        } else if (Game.gameState == Game.STATE.End) {
            //End button
            if (mouseOver(mx, my, 240, 300, 100, 100)) {
                handler.gameObjects.clear();
                Game.gameState = Game.STATE.Menu;
            }
        }
    }

    public void mouseReleased(MouseEvent e) {


    }

    private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
        if (mx > x && mx < x + width) {
            if (my > y && my < y + height) {
                return true;
            } else return false;
        } else return false;
    }

    public void tick() {

    }

    public void render(Graphics g) {

        if (Game.gameState == Game.STATE.Menu) {

            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);

            g.setFont(fnt);
            g.setColor(Color.WHITE);
            g.drawString("Menu", 240, 70);

            g.setFont(fnt2);
            g.setColor(Color.WHITE);
            g.drawString("Play", 240, 370);

        } else if (Game.gameState == Game.STATE.End) {
            handler.gameObjects.clear();


            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);

            g.setFont(fnt);
            g.setColor(Color.WHITE);
            g.drawString("Game Over", 240, 70);

            g.setFont(fnt2);
            g.setColor(Color.WHITE);
            g.drawString("Your SCORE is " + hud.getScore(), 240, 150);

            g.setFont(fnt2);
            g.setColor(Color.WHITE);
            g.drawString("Try again", 240, 370);

            hud.HEALTH = 100;
            hud.SCORE = 0;
        }

    }
}
