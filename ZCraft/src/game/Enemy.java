package game;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Enemy {

	/*
	 * Author: Carl Zhang
	 * Date: Monday 19, 2017
	 * Enemy Aircraft - Coordinates, Size, Speed, Image
	 */
	
	private Game game;
	
	//enemy coordinates, size, and speed
	private int x;
	private double y;
	private int width;
	private int height;
	private final double SPEED;
	
	private Image image;
	
	public Enemy(int x, int y, Game game){
		this.x = x;
		this.y = y;
		this.game = game;
		
		this.width = this.game.getWidth()/10;
		this.height = this.game.getHeight()/10;
		
		if(game.getEnemySpeed() == 1) {
			//random speed for enemy (1 - 1.499) 
			this.SPEED = /*(int)*/ Math.random() * 1.5 + 1;
			
		}else if(game.getEnemySpeed() == 2) {
			//random speed for enemy (1.5 - 2.499) 
			this.SPEED = /*(int)*/ Math.random() * 2.5 + 1.5;
			
		}else {
			//random speed for enemy (2.5 - 3.499) 
			this.SPEED = /*(int)*/ Math.random() * 3.5 + 2.5;
			
		}
		
		try {
			//this.image = ImageIO.read(new File("res//EnemyCraft.png"));
			this.image = (new ImageIcon(getClass().getClassLoader().getResource("EnemyCraft.png"))).getImage();
		} catch (NullPointerException e) {
			System.out.println("Missing - EnemeyCraft");
		}
		
	}//end constructor Enemy(int x, int y, Game game)
	
	public void update() {
		y += SPEED;	
		
	}//end update()
	
	public void render(Graphics g) {
		//draw enemy plane
		g.drawImage(image, x, (int) y, width, height, null);
		
	}//end render(Graphics g)
	
	//get and set methods
	public int getX() {
		return x;
		
	}//end getX()
	
	public int getY() {
		return (int) y;
		
	}//end getY()
	
	public int getWidth() {
		return width;
		
	}//end getWidth();
	
	public int getHeight() {
		return height;
		
	}//end getHeight()
	
}//end class Enemy
