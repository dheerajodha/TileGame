package dev.codenmore.tilegame.worlds;

import java.awt.Graphics;

import dev.codenmore.tilegame.Game;
import dev.codenmore.tilegame.entities.EntityManger;
import dev.codenmore.tilegame.entities.creatures.Player;
import dev.codenmore.tilegame.entities.statics.Tree;
import dev.codenmore.tilegame.tiles.Tile;
import dev.codenmore.tilegame.utils.Utils;

public class World {
    public EntityManger getEntityManger() {
        return entityManger;
    }

    private Game game;
	private int width,height;
	private int[][] tiles;
	//Entities
	private EntityManger entityManger;
	
	public World(Game game,String path) {
		this.game=game;
		entityManger=new EntityManger(game,new Player(game,100,100));
		loadWorld(path);
		entityManger.addEntity(new Tree(game,100,250));
		entityManger.addEntity(new Tree(game,100,450));
		entityManger.addEntity(new Tree(game,100,650));
    }
	
	public void tick() {
		entityManger.tick();
		
	}
	
	public void render(Graphics g) {
		
		for(int y=0;y<height;y++)
			for(int x=0;x<width;x++) {
				getTile(x,y).render(g,(int) (x*Tile.TILEWIDTH-game.getGameCamera().getxOffset()),
						(int)(y*Tile.TILEHEIGHT-game.getGameCamera().getyOffset()));
			}
			entityManger.render(g);
	}
	
	public Tile getTile (int x, int y) {
	
		if(x<0|| y<0 || x>=width || y>=height) {
			return Tile.grassTile;
		}
		
		Tile t = Tile.tiles[tiles[x][y]];
	    if(t==null)
	    	return Tile.tiles[2];
	    return t;
	}
	
	
	private void loadWorld(String path) {
    
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		tiles = new int[width][height];
		for(int y=0;y<height;y++) {
			for(int x=0;x<width;x++) {
				tiles[x][y] = Utils.parseInt(tokens[x+y*width+2]);
			}
		}
		
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
}