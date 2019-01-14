package dev.codenmore.tilegame.entities;
import java.awt.Graphics;
import java.awt.Rectangle;

import dev.codenmore.tilegame.Game;

public abstract class Entity {
    public static final int DEFAULT_HEALTH=10;
    protected Game game;
    protected float x, y;
    protected int width,height;
    protected int health;
    protected boolean active = true;
    protected Rectangle bounds;

    public Entity(Game game,float x,float y,int width,int height) {
        this.game=game;
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        health = DEFAULT_HEALTH;


        bounds = new Rectangle(0,0,width,height);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }


    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }


    public int getWidth() {
        return width;
    }


    public int getHeight() {
        return height;
    }


    public abstract void tick();

    public abstract void render(Graphics g);
    public abstract void die();
    public void hurt(int amt){
        health-=amt;
        if(health<=0) {
            active = false;
            die();
        }
    }

    public boolean checkEntityCollisions(float xOffset,float yOffset){
        for(Entity e:game.getWorld().getEntityManger().getEntities()){
            if(e.equals(this))
                continue;
            if(e.getCollisionBounds(0f,0f).intersects(getCollisionBounds(xOffset,yOffset)))
                return true;
        }
        return false;
    }
    public  Rectangle getCollisionBounds(float xOffset,float yOffset){
    return new Rectangle((int)(x+bounds.x+xOffset),(int)(y+bounds.y+yOffset),bounds.width,bounds.height);
    }

}
