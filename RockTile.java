package dev.codenmore.tilegame.tiles;

//import java.awt.image.BufferedImage;

import dev.codenmore.tilegame.gfx.Assets;

public class RockTile extends Tile {

	public RockTile(int id) {
		super(Assets.rock, id);
	}

	public boolean isSolid() {
		return true;
	}
}
