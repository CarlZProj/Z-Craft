package game;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Missile {

	/*
	 * Author: Carl Zhang
	 * Date: Monday 19, 2017
	 * Missile object - Coordinates, Size, Speed, Image
	 */
	
	private Game game;
	
	//coordinate, dimensions, and speed
	private int x;
	private int y;
	private int width;
	private int height;
	private final int SPEED = 1;
	
	//missile image
	private Image image;
	
	public Missile(int x, int y, Game game){
		this.x = x;
		this.y = y;
		this.game = game;
		
		this.width = this.game.getWidth()/25;
		this.height = this.game.getHeight()/25;
		
		try {
			//this.image = ImageIO.read(new File("res//Missile.png"));
			this.image = (new ImageIcon(getClass().getClassLoader().getResource("Missile.png"))).getImage();	
		} catch (NullPointerException e) {
			System.out.println("Missing - Missile");
		}
		
	}//end constructor Missile(int x, int y, Game game)
	
	public void update() {
		//coordinates of missile will move up
		this.y -= SPEED;
		
	}//end update()
	
	public void render(Graphics g) {
		//draw missile
		g.drawImage(image, x,  y, width, height, null);
		
	}//end render(Graphics g)
	
	public int getX() {
		return x;
		
	}//end getX()
	
	public int getY() {
		return y;
		
	}//end getY()
	
	public int getWidth() {
		return width;
		
	}//end getWidth()
	
	public int getHeight() {
		return height;
		
	}//end getHeight()
	
}//end Missile
