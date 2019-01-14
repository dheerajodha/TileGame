package dev.codenmore.tilegame.gfx;

import java.awt.image.BufferedImage;

public class Assets {

	public static BufferedImage stone,flower,rock,grass,cgrass,dirt,player,wall,tree;
	public static BufferedImage[] player_down;
	public static final int width=64,height=64;

	public static void init() {
		//Texture tiles
		
		SpriteSheet sheet1= new SpriteSheet(ImageLoader.loadImage("/textures/texture.png"));
		
		rock=sheet1.crop(0, 0, width, height);
		grass=sheet1.crop(656, 300, width, height-1);
		dirt=sheet1.crop(720, 300, width, height);
		wall= sheet1.crop(592, 300, width, height);
		tree= sheet1.crop(160,0,41,64);
		stone=
		flower=
		//Other Elements 
		
		player=sheet1.crop(width, 0, width-8, height);
		 
		
	}
			
}	