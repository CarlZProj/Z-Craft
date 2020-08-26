package game;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Player {
	
	/*
	 * Author: Carl Zhang
	 * Date: Monday 19, 2017
	 * Player aircraft - (Image, Coordinates, Speed, Size, Score)
	 */
	
	private Game game;
	
	//username
	String user;
	
	//player coordinates, size, speed
	private int x;
	private int y;
	private int width;
	private int height;
	private final int SPEED = 2;
	
	private Image image;
	
	public Player(int x, int y, Game game){
		
		this.user = null;
		
		this.x = x;
		this.y = y;
		this.game = game;
		
		this.width = this.game.getWidth()/10;
		this.height = this.game.getHeight()/10;
				
		try {
			//this.image = ImageIO.read(new File("res//PlayerCraft.png"));
			this.image = (new ImageIcon(getClass().getClassLoader().getResource("PlayerCraft.png"))).getImage();	
		} catch (NullPointerException e) {
			System.out.println("Missing - PlayerCraft");
		}//end try/catch
		
	}//end constructor Player()
	
	public void render(Graphics g) {
		//draw player aircraft
		g.drawImage(this.image, x, y, width, height, null);
		
	}//end render(Graphics g)
	
	//get and set methods
	public String getUser() {
		return user;
		
	}//end getUser()
	public void setUser(String s) {
		user = s;
		
	}//end setUser(String s)
	
	public int getX() {
		return x;
		
	}//end getX()
	
	public int getY() {
		return y;
		
	}//end getY()
	
	public void setX(int x) {
		this.x = x;
		
	}//end setX()
	
	public void setY(int y) {
		this.y = y;
		
	}//end setY()
	
	public int getWidth() {
		return width;
		
	}//end getWidth()
	
	public int getHeight() {
		return height;
		
	}//end getHeight()
	
	public int getSpeed() {
		return SPEED;
		
	}//end getSpeed()

}//end class Player
