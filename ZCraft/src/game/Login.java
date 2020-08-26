package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Login {

	/*
	 * Author: Carl Zhang
	 * Date: Monday 19, 2017
	 * Login/Create User Function
	 */
	
	private Game game;
	
	private Image background;
	
	private String[] options = {"Login", "New User"};
	
	private Rectangle[] rect = new Rectangle[2];
	
	private Font f1, f2;
		
	public Login(Game game) {
		this.game = game;
		
		//background
		try {
			//this.background = ImageIO.read(new File("res//LoginMenuBackground.jpg"));
			this.background = (new ImageIcon(getClass().getClassLoader().getResource("LoginMenuBackground.jpg"))).getImage();
		} catch (NullPointerException e) {
			System.out.println("Missing - LoginMenuBackground");	
		}
		
		f1 = new Font("Arial", Font.BOLD, 100);
		f2 = new Font("Arial", Font.PLAIN, 50);
		
		//boundaries of buttons
		rect[0] = new Rectangle(this.game.getWidth()/10 * 3, this.game.getHeight()/5 * 2,  this.game.getWidth()/10 * 4, this.game.getHeight()/8);
		rect[1] = new Rectangle(this.game.getWidth()/4, this.game.getHeight()/5 * 4,  this.game.getWidth()/2, this.game.getHeight()/8);
		
	}//end constructor Login(Game game) 
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		//draw background
		g2d.drawImage(background, 0, 0, game.getWidth(), game.getHeight(), null);
		
		g2d.setColor(Color.WHITE);
		g2d.setFont(f1);
		g2d.drawString("Z-Craft", game.getWidth()/6, game.getHeight()/5);
		
		for(int i = 0; i < rect.length; i++) {
			g2d.draw(rect[i]);		
			g2d.setFont(f2);
			
		}
		
		//draw button labels
		g2d.drawString(options[0], game.getWidth()/100 * 38, game.getHeight()/5 * 2 + this.game.getHeight()/10);
		g2d.drawString(options[1], game.getWidth()/4 + game.getWidth()/30, game.getHeight()/5 * 4 + + this.game.getHeight()/10);
		
	}//end render(Graphics g)
	
	public Rectangle getLogin() {
		return rect[0];
		
	}//end getLogin()
	
	public Rectangle getCreateUser() {
		return rect[1];
		
	}//end getCreateUser()
	
}//end class Login
