package dev.codenmore.tilegame.entities;

import dev.codenmore.tilegame.Game;
import dev.codenmore.tilegame.entities.creatures.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class EntityManger {
    private Game game;
    private Player player;
    private ArrayList<Entity> entities;
    private Comparator<Entity> renderSorter = new Comparator<Entity>() {
        @Override
        public int compare(Entity a, Entity b) {
            if(a.getY()+a.getHeight()<b.getY()+b.getHeight())
                return -1;
            return  1;
        }
    };
    public EntityManger(Game game,Player player){
        this.game=game;
        this.player=player;
        entities=new ArrayList<Entity>();
        addEntity(player);


    }
    public void tick(){
        for(int i=0;i<entities.size();i++){
            Entity e = entities.get(i);
            e.tick();
            if(!e.isActive())
                entities.remove(e);
        }
entities.sort(renderSorter);
    }
    public void render(Graphics g){
        for(Entity e : entities) {
            e.render(g);
        }


    }
    public void addEntity(Entity e){
        entities.add(e);

    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Player getPlayer() {
        return player;
    }

    //public void setPlayer(Player player) {
      //  this.player = player;
    //}

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    //public void setEntities(ArrayList<Entity> entities) {
      //  this.entities = entities;
   // }


}
