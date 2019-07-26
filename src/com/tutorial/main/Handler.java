package com.tutorial.main;

import java.awt.*;
import java.util.LinkedList;

public class Handler {

    LinkedList<GameObject> gameObjects = new LinkedList<GameObject>();

    public void tick() {
        for (int i = 0; i < gameObjects.size(); i++) {
            GameObject currentObject = gameObjects.get(i);

            currentObject.tick();

        }
    }

    public void render(Graphics g) {
        for (int i = 0; i < gameObjects.size(); i++) {
            GameObject currentObject = gameObjects.get(i);

            currentObject.render(g);

        }
    }

    public void clearEnemys(){
        for (int i = 0; i<gameObjects.size();i++){
            GameObject currentObject = gameObjects.get(i);

            if (currentObject.getId() == ID.Player){
                gameObjects.clear();
                if (Game.gameState!=Game.STATE.End)
                addObject(new Player((int)currentObject.getX(), (int)currentObject.getY(), ID.Player, this));
            }
        }
    }

    public void addObject(GameObject object) {
        this.gameObjects.add(object);
    }

    public void removeObject(GameObject object) {
        this.gameObjects.remove(object);
    }

}
