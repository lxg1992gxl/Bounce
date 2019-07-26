package com.tutorial.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    private Handler handler;
    private boolean[] keyDown = new boolean[4];

    public KeyInput(Handler handler) {
        this.handler = handler;

        keyDown[0] = false;
        keyDown[1] = false;
        keyDown[2] = false;
        keyDown[3] = false;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        for (int i = 0; i < handler.gameObjects.size(); i++) {
            GameObject tempObject = handler.gameObjects.get(i);

            if (tempObject.getId() == ID.Player) {
                //key events for player 1

                if (key == KeyEvent.VK_W) {
                    tempObject.setVelY(-2);
                    keyDown[0] = true;
                }
                if (key == KeyEvent.VK_S) {
                    tempObject.setVelY(2);
                    keyDown[1] = true;
                }
                if (key == KeyEvent.VK_A) {
                    tempObject.setVelX(-2);
                    keyDown[2] = true;
                }
                if (key == KeyEvent.VK_D) {
                    tempObject.setVelX(2);
                    keyDown[3] = true;
                }
            }
//            if (tempObject.getId() == ID.Player2){
//                //key events for player 2
//
//                if (key == KeyEvent.VK_UP) tempObject.setVelY(-1);
//                if (key == KeyEvent.VK_DOWN) tempObject.setVelY(1);
//                if (key == KeyEvent.VK_LEFT) tempObject.setVelX(-1);
//                if (key == KeyEvent.VK_RIGHT) tempObject.setVelX(1);
//            }
        }
        if (key == KeyEvent.VK_ESCAPE) System.exit(1);

    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        for (int i = 0; i < handler.gameObjects.size(); i++) {
            GameObject tempObject = handler.gameObjects.get(i);

            if (tempObject.getId() == ID.Player) {
                //key events for player 1

                if (key == KeyEvent.VK_W) keyDown[0] = false;//tempObject.setVelY(0);
                if (key == KeyEvent.VK_S) keyDown[1] = false;//tempObject.setVelY(0);
                if (key == KeyEvent.VK_A) keyDown[2] = false;//tempObject.setVelX(0);
                if (key == KeyEvent.VK_D) keyDown[3] = false;//tempObject.setVelX(0);

                //vertical movement
                if (!keyDown[0]&&!keyDown[1]) tempObject.setVelY(0);
                //horizontal movement
                if (!keyDown[2]&&!keyDown[3]) tempObject.setVelX(0);
            }
//            if (tempObject.getId() == ID.Player2){
//                //key events for player 2
//
//                if (key == KeyEvent.VK_UP) tempObject.setVelY(0);
//                if (key == KeyEvent.VK_DOWN) tempObject.setVelY(0);
//                if (key == KeyEvent.VK_LEFT) tempObject.setVelX(0);
//                if (key == KeyEvent.VK_RIGHT) tempObject.setVelX(0);
//            }
        }

    }

}
