package com.tutorial.main;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = 1550691097823471818L;

    public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;


    private Thread thread;
    private boolean running = false;

    private Handler handler;
    private Random r;
    private HUD hud;
    private Spawn spawnner;
    private Menu menu;
    private com.tutorial.main.AudioPlayer audioPlayer;

    public enum STATE {
        Menu,
        Game,
        End
    }

    public static STATE gameState = STATE.Menu;

    public Game() {


        handler = new Handler();
        hud = new HUD();
        menu = new Menu(this, hud, handler);

        this.addKeyListener(new KeyInput(handler));
        this.addMouseListener(menu);

        new Window(WIDTH, HEIGHT, "Title", this);

        spawnner = new Spawn(handler, hud);

        audioPlayer.load();
        audioPlayer.getMusic("music").loop();

//        r = new Random();

//        if (gameState == STATE.Game) {
//            handler.addObject(new Player(WIDTH / 2 - 32, HEIGHT / 2 - 32, ID.Player, handler));
//            handler.addObject(new BasicEnemy(r.nextInt(WIDTH - 100) + 50, r.nextInt(HEIGHT - 100) + 50, ID.MenuPartical, handler));
//
//        } else {
//            for (int i = 0; i < 20; i++) {
//                handler.addObject(new MenuPartical(r.nextInt(100), r.nextInt(100), ID.MenuPartical, handler));
//            }
//        }
    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;

    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void run() {
        this.requestFocus();
        //copied by YouTube "Java Programming: Let's Build a Game #1" 14:55
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            if (running)
                render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();

    }

    private void tick() {
        handler.tick();

        if (gameState == STATE.Game) {
            hud.tick();
            spawnner.tick();

            if (HUD.HEALTH <= 0) {
                HUD.HEALTH = 100;

                gameState = STATE.End;
                for (int i = 0; i < 20; i++) {
                    handler.addObject(new MenuPartical(r.nextInt(100), r.nextInt(100), ID.MenuPartical, handler));
                }
                handler.gameObjects.clear();
            }
        } else if (gameState == STATE.Menu || gameState == STATE.End) {
            menu.tick();
        }


    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        handler.render(g);

        if (gameState == STATE.Game) {
            hud.render(g);
        } else if (gameState == STATE.End || gameState == STATE.Menu) {
            //End Game
            menu.render(g);
        }

        g.dispose();
        bs.show();

    }

    public static float clamp(float var, float min, float max) {
        if (var >= max) {
            return var = max;
        } else if (var <= min) {
            return var = min;
        } else return var;

    }

    public static void main(String[] args) {
        new Game();

    }
}
