package dev.codenmore.tilegame.states;

import java.awt.Graphics;

import dev.codenmore.tilegame.Game;
import dev.codenmore.tilegame.entities.creatures.Player;
//import dev.codenmore.tilegame.gfx.Assets;
//import dev.codenmore.tilegame.tiles.GrassTile;
//import dev.codenmore.tilegame.tiles.Tile;
import dev.codenmore.tilegame.entities.statics.Tree;
import dev.codenmore.tilegame.worlds.World;

public class GameState extends State {

	//private Player player;
	private World world;

	
	
	public GameState(Game game) {
		super(game);
		world = new World(game,"res/worlds/world1.txt");
		game.setWorld(world);
		//player = new Player(game,100,100);

	}
	
	public void tick() {
		world.tick();
		//player.tick();

	}

	
	public void render(Graphics g) {
		world.render(g);
		//player.render(g);

    }

	
}
