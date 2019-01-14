package dev.codenmore.tilegame.entities.creatures;

import dev.codenmore.tilegame.Game;
import dev.codenmore.tilegame.entities.Entity;
import dev.codenmore.tilegame.tiles.Tile;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public abstract class Creature extends Entity {

	public static final float DEFAULT_SPEED=3.0f;
	public static final int DEFAULT_CREATURE_WIDTH=64,
	                        DEFAULT_CREATURE_HEIGHT=64;
	
	
	protected float speed;
	protected float xMove;
    protected float yMove;

	public Creature(Game game,float x, float y,int width,int height) {
		super(game,x, y,width,height);
		speed=DEFAULT_SPEED;
		xMove=0;
		yMove=0;
	}

	public void move() {
		if(!checkEntityCollisions(xMove,0f))
			moveX();
		if(!checkEntityCollisions(0f,yMove))
		moveY();

	}
	
	public void moveX() {
		
		
		if(xMove > 0){//Moving right
			
		int	tx = (int) ((x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH);
			
			if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&
					!collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)){
				x += xMove;
			}
			else {
				//x=tx*Tile.TILEWIDTH; //traps the player forever whenever he comes near the "danger" tile.
			      
				x=(tx*Tile.TILEHEIGHT-bounds.width-bounds.x-1);
			}
			
		}else if(xMove < 0){//Moving left
			int tx = (int) (x + xMove + bounds.x) / Tile.TILEWIDTH;
			
			if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&
					!collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)){
				x += xMove;
			}
     }
		
}
	
	
	public void moveY() {

		if(yMove>0) { //Moving Down
			
			int ty = (int)(y+bounds.y+bounds.height+yMove)/Tile.TILEHEIGHT;
			
			if(!collisionWithTile((int)(x+bounds.x)/Tile.TILEHEIGHT,ty)
					&& !collisionWithTile((int)(x+bounds.x+bounds.width)/Tile.TILEHEIGHT,ty)){
				y+=yMove;
			}
			else {
				//x+=1; makes the player crawl through rock tiles
				
				y=ty*Tile.TILEHEIGHT-bounds.y-bounds.height-1;
			}
		}
		
		else if(yMove<0) {//Moving Up
			
			int ty = (int)(y+bounds.y+yMove)/Tile.TILEHEIGHT;
			
			if(!collisionWithTile((int)(x+bounds.x)/Tile.TILEWIDTH,ty)
					&& !collisionWithTile((int)(x+bounds.x+bounds.width)/Tile.TILEWIDTH,ty))
			{
				y+=yMove;
			}
		
		}
		        		
	}
	
	protected boolean collisionWithTile(int x,int y) {
		return game.getWorld().getTile(x, y).isSolid();
	}
	
	
	//GETTERS AND SETTERS
	
	public float getxMove() {
		return xMove;
	}

	public float getyMove() {
		return yMove;
	}

	public float getSpeed() {
		return speed;
	}

}