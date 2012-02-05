package pro;

/**
 * @author Muhammad Ahmad Tirmazi
 */

import java.awt.Color;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;


public class MainApp extends KeyHandlerApplet {
	
	/* constants */
	long FPS = 60;
	int FRAME = 0;
	
	/* members */
	Thread thread;
	LevelParser levelParser = new LevelParser(20,20,this);
	
	/** singleton code **/
	static MainApp instance;
	public MainApp getInstance(){
		if ( instance == null )
			instance = new MainApp();
		return instance;
	}
	
	/* override methods */
	
	/* Applet - Loop */
	
	@Override 
	public void start(){
		this.setBackground(Color.black);
		
		thread = new Thread( this );
		thread.start();
		
		getAstro().setLevelParser(levelParser);
		levelParser.parseLevel("level_1.txt");
	}
	
	@Override 
	public void stop(){
		thread = null;
	}

	/* Applet - Graphics */
	@Override
	public void paint(Graphics g){
		super.paint(g);
		
		levelParser.drawTiles(g);
		
		getAstro().draw(g);
	}
	
	/* Runnable */
	
	@Override 
	public void run(){
		
		while( Thread.currentThread() == thread ){
			super.run();
			
			repaint();
			try{
				Thread.sleep( 1000 / FPS );
			}catch (InterruptedException e){
				break;
			}
			
			/* increment the frame */
			FRAME++;
			
			getAstro().update(this);
		}
	}
	
}


