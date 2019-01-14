package dev.codenmore.tilegame.entities.statics;

import dev.codenmore.tilegame.Game;
import dev.codenmore.tilegame.entities.Entity;

public abstract class StaticEntity extends Entity{

    public StaticEntity(Game game, float x, float y, int width, int height){
        super(game,x,y,width,height);

    }
}
