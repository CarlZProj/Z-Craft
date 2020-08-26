package game;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Pause {

	/*
	 * Author: Carl Zhang
	 * Date: Monday 19, 2017
	 * Pause Button when 1 Pressed
	 */
	
	private Game game;
	
	private int x;
	private int y;
	private int sideLength;
	
	private Image pause;
	
	public Pause(Game game) {
		this.game = game;
		
		this.x = this.game.getWidth()/3;
		this.y = this.game.getHeight()/3;
		this.sideLength = this.game.getHeight()/3;
		
		try {
			//pause = ImageIO.read(new File("res//PauseButton.png"));
			pause = (new ImageIcon(getClass().getClassLoader().getResource("PauseButton.png"))).getImage();	
		} catch (NullPointerException e) {
			System.out.println("Missing - PauseButton");
		}
		
	}//end constructor Pause()
	
	public void render(Graphics g) {
		//draw pause button
		g.drawImage(pause, x, y, sideLength, sideLength, null);
		
	}//end render(Graphics g)
	
}//end class Pause
