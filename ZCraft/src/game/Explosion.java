package game;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Explosion {

	/*
	 * Author: Carl Zhang
	 * Date: Monday 19, 2017
	 * Explosion Generation - Coorindates, Size, Image, Speed (Same as Background)
	 */
	
	private Game game;
	
	//explosion coordinates, size,
	private int x;
	private int y;
	private int width;
	private int height;
	
	//explosion image
	private Image image;

	public Explosion(int x, int y, Game game) {
		this.x = x;
		this.y = y;
		this.game = game;
		
		this.width = this.game.getWidth()/10;
		this.height = this.game.getHeight()/10;
		
		try {
			//this.image = ImageIO.read(new File("res//Explosion.png"));
			this.image = (new ImageIcon(getClass().getClassLoader().getResource("Explosion.png"))).getImage();
		} catch (NullPointerException e) {
			System.out.println("Missing - Explosion");
		}//end try/catch
		
	}//end constructor Explosion()
	
	public void render(Graphics g) {
		//draw explosion
		g.drawImage(image, x, y, width, height, null);
		
	}//end render(Graphics g)
	
	public void update() {
		y++;
		
	}//end update()
	
	//gets y coordinate of explosion to increment 
	//makes it look like explosion is going behind player's plane
	public int getY() {
		return y;
		
	}//end getY()
	
}//end class Explosion
