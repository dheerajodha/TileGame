package dev.codenmore.tilegame.entities.creatures;

import java.awt.*;
import java.util.HashMap;

import dev.codenmore.tilegame.Game;
import dev.codenmore.tilegame.entities.Entity;
import dev.codenmore.tilegame.gfx.Assets;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;


public class Player extends Creature {

    //attack timer
    private long lastAttackTimer, attackCooldown=1000,attackTimer=attackCooldown;

	public Player(Game game,float x, float y) {
		super(game,x, y,Creature.DEFAULT_CREATURE_WIDTH,Creature.DEFAULT_CREATURE_HEIGHT);
	
		bounds.x=17;
		bounds.y=32;
		bounds.width =30;
		bounds.height=28;
		
	}
	private HashMap<String, AudioSystem> sfx;
	AudioInputStream ais;
	public void tick() {

		getInput();
		move();
		game.getGameCamera().centerOnEntity(this);
        //attack
        checkAttacks();

	}
	private void checkAttacks(){
	    attackTimer+=System.currentTimeMillis()-lastAttackTimer;
	    lastAttackTimer=System.currentTimeMillis();
	    if(attackTimer<attackCooldown)
	        return;
	    Rectangle cb = getCollisionBounds(0,0);
        Rectangle ar = new Rectangle();
        int arSize=20;
        ar.width=arSize;
        ar.height=arSize;

        if(game.getKeyManager().aUp){
           ar.x=cb.x+cb.width/2-arSize/2;
           ar.y=cb.y-arSize;
        }else  if(game.getKeyManager().aDown){
            ar.x=cb.x+cb.width/2-arSize/2;
            ar.y=cb.y+cb.height;
        }else  if(game.getKeyManager().aLeft){
            ar.x=cb.x-arSize;
            ar.y=cb.y+cb.height/2-arSize/2;
        }else  if(game.getKeyManager().aRight){
            ar.x=cb.x + cb.width;
            ar.y=cb.y+cb.height/2-arSize/2;
        }else{
            return;
        }
        for(Entity e:game.getWorld().getEntityManger().getEntities()){
            if(e.equals(this))
                continue;
            if(e.getCollisionBounds(0,0).intersects(ar)){
                e.hurt(1);
                return;
            }
        }
        attackTimer=0;
    }
	public void die(){
		System.out.println("you lose");
	}

	public void getInput() {
		xMove=0;
		yMove=0;
		
		if(game.getKeyManager().up)
			yMove = -speed;
		if(game.getKeyManager().down)
			yMove =  speed;
		if(game.getKeyManager().left)
			xMove = -speed;
    	if(game.getKeyManager().right)
	    	xMove =  speed;

	}
	
	public void render(Graphics g) {
	
	
		g.drawImage(Assets.player, (int)(x-game.getGameCamera().getxOffset()),
				(int)(y-game.getGameCamera().getyOffset()),width,height, null);
	
		//g.setColor(Color.red);
		//g.fillRect((int)(x+bounds.x - game.getGameCamera().getxOffset())
		
		//	,(int)(y+bounds.y - game.getGameCamera().getyOffset()), bounds.width, bounds.height);
	}

}
