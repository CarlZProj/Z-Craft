package game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{

	/*
	 * Author: Carl Zhang
	 * Date: Monday 19, 2017
	 * KeyListener
	 */
	
	Game game;
	
	public KeyInput(Game game){
		this.game = game;
		
	}//end constructor KeyInput(Game game)
	
	public void keyPressed(KeyEvent e) {
		game.keyPressed(e);
		
	}//end keyPressed(KeyEvent e)
	
	public void keyReleased(KeyEvent e) {
		game.keyReleased(e);
		
	}//end keyReleased(KeyEvent e)
	
}//end class KeyInput
