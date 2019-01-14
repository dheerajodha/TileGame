package dev.codenmore.tilegame;
//import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
//import java.awt.image.BufferedImage;

import dev.codenmore.tilegame.display.Display;
import dev.codenmore.tilegame.gfx.Assets;
import dev.codenmore.tilegame.gfx.GameCamera;
//import dev.codenmore.tilegame.gfx.ImageLoader;
//import dev.codenmore.tilegame.gfx.SpriteSheet;
import dev.codenmore.tilegame.input.KeyManager;
import dev.codenmore.tilegame.states.GameState;
import dev.codenmore.tilegame.states.State;
import dev.codenmore.tilegame.worlds.World;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;


public class Game implements Runnable{

	private Display display;
	private int width,height;
	
	
	private World world;
	
	
	public String title;
	
	private boolean running=false;
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;
	
	
	//States
	private State gameState;
	
	//INPUT
	private KeyManager keyManager;
	
	//CAMERA
	private GameCamera gameCamera;
	
	public Game(String title,int width,int height) {
		this.width=width;
		this.height=height;
		this.title=title;
		keyManager = new KeyManager();
	}
	
	public void init() {
		display=new Display(title,width,height);
		display.getFrame().addKeyListener(keyManager);
		Assets.init();
		gameCamera = new GameCamera(this,0,0);
		
		gameState = new GameState(this);
		
		
		State.setState(gameState);


		try{
			File sound = new File("soundtest//24035__bebeto__loop018.wav");
			AudioInputStream ais = AudioSystem.getAudioInputStream(sound);
			Clip clip = AudioSystem.getClip();
			clip.open(ais);
			clip.start();
			clip.loop(2893289);

		}catch(Exception e) {System.out.println(e);}



	}
	
	
	
	public void tick() {
	
		keyManager.tick();
		
		if(State.getState()!=null) {
			State.getState().tick();
		}
	}
	
	
	public void setWorld(World world) {
		this.world = world;
	}

	public void setKeyManager(KeyManager keyManager) {
		this.keyManager = keyManager;
	}

	public void render() {
		bs = display.getCanvas().getBufferStrategy();
		if(bs==null) {
			display.getCanvas().createBufferStrategy(3);
			return;
	      }
		
		
		g = bs.getDrawGraphics();
		//clear screen
		g.clearRect(0, 0, width, height);
		
		//Draw Here!
		//g.drawLine(0, 0, 1, 1);
		if(State.getState()!=null) {
			State.getState().render(g);
		}
		
		//End Drawing!
		bs.show();
		g.dispose();
	}
	
	public void run() {
		
		
		int fps=60;
		double timePerTick=1000000000/fps;
		double delta=0;
		long now;
		long lastTime = System.nanoTime();
		long timer=0;
		int ticks=0;
		
		while(running) {
			
			now=System.nanoTime();
			delta+= (now - lastTime)/timePerTick;
			timer+=(now-lastTime);
			lastTime=now;
			
			if(delta>=1) {
			  tick();
			  render();
			  ticks++;			  
			  delta--;
			}
			
			if(timer>=1000000000) {
				System.out.println("Frames:"+ticks);
				ticks=0;
				timer=0;
			}
		}
		
		try {
			stop();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public World getWorld() {
		return world;
	}

	
	public KeyManager getKeyManager() {
		return keyManager;
	}
	
	public GameCamera getGameCamera() {
		return gameCamera;
	}
	
	public synchronized void start() {
        if(running)
        	return;
        running=true;
		thread=new Thread(this);
		init();
		thread.start();
	}
	
	public synchronized void stop() throws InterruptedException {
		if(!running)
			return;
		running=false;
		thread.join();
	}
}