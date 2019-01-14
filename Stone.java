package dev.codenmore.tilegame.entities.statics;

import dev.codenmore.tilegame.Game;
import dev.codenmore.tilegame.gfx.Assets;
import dev.codenmore.tilegame.tiles.Tile;

import java.awt.*;

public class Stone extends StaticEntity{

    public Stone(Game game,float x,float y){
        super(game,x,y, Tile.TILEWIDTH,Tile.TILEHEIGHT*2);

        bounds.x=10;
        bounds.y=(int)(height/1.5f);
        bounds.width=width-20;
        bounds.height=(int)(height-height/1.5f);

    }
    public void tick(){

    }
    public void die(){
        System.out.println("you lose");
    }

    public void render(Graphics g) {
        g.drawImage(Assets.stone, (int) (x - game.getGameCamera().getxOffset()), (int) (y - game.getGameCamera().getyOffset()), width, height, null);
    }

}

