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

public class BeforeHighscores {

	/*
	 * Author: Carl Zhang
	 * Date: Monday 19, 2017
	 * Highscore Menu (Easy, Medium, Hard)
	 */
	
	Game game;
	
	private String title;
	
	private Rectangle easy;
	private Rectangle medium;
	private Rectangle hard;
	
	private String easyString;
	private String mediumString;
	private String hardString;
	
	private Font f1;
	private Font f2;

	//back button
	private Image image;
	private Rectangle back;
	
	private Image topTenImage;
	
	public BeforeHighscores(Game game) {
		this.game = game;
		
		this.title = "Highscores";
		
		this.easy = new Rectangle(0, game.getHeight()/5, game.getWidth()/3, game.getHeight()/3);
		this.medium = new Rectangle(game.getWidth()/3, game.getHeight()/5, game.getWidth()/3, game.getHeight()/3);
		this.hard = new Rectangle(game.getWidth()/3 * 2, game.getHeight()/5, game.getWidth()/3, game.getHeight()/3);
		
		easyString = "EASY";
		mediumString = "MEDIUM";
		hardString = "HARD";
		
		this.f1 = new Font("Arial", Font.BOLD, 50);
		this.f2 = new Font("Arial", Font.BOLD, 40);
		
		back = new Rectangle(0, game.getHeight() - game.getHeight()/10, game.getWidth()/10, game.getHeight()/10);
		
		try {
			//image = ImageIO.read(new File("res//BackButton.png"));
			image = (new ImageIcon(getClass().getClassLoader().getResource("BackButton.png"))).getImage();
		}catch (NullPointerException e) {
			System.out.println("Missing - BackButton");
		}
		
		try {
			//topTenImage = ImageIO.read(new File("res//TopTen.png"));
			image = (new ImageIcon(getClass().getClassLoader().getResource("TopTen.png"))).getImage();
		}catch (NullPointerException e) {
			System.out.println("Missing - TopTen");
		}
		
	}//end constructor BeforeHighscores(Game game)
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(Color.WHITE);
		g2d.setFont(f1);
		g2d.drawString(title, game.getWidth()/5, game.getHeight()/10);
		g2d.drawString(easyString, game.getWidth()/30, game.getHeight()/5 * 2);
		g2d.drawString(hardString, game.getWidth()/3 * 2 + game.getWidth()/30, game.getHeight()/5 * 2);
		
		g2d.setFont(f2);
		g2d.drawString(mediumString, game.getWidth()/3, game.getHeight()/5 * 2);
		
		g2d.draw(easy);
		g2d.draw(medium);
		g2d.draw(hard);
		
		//draw image & rectangle
		g2d.drawImage(image, 0, game.getHeight() - game.getHeight()/10, game.getWidth()/10, game.getHeight()/10, null);
		g2d.draw(back);
		
		g2d.drawImage(topTenImage, game.getWidth()/4, game.getHeight()/50 * 27,  game.getWidth()/2, game.getHeight()/2, null);
		
	}//end render(Graphics g)
	
	public Rectangle getEasy() {
		return easy;
		
	}//end getEasy()
	
	public Rectangle getMedium() {
		return medium;
		
	}//end getMedium

	public Rectangle getHard() {
		return hard;
		
	}//end getHard()
	
	public Rectangle getBackButton() {
		return back;
		
	}//end getBackButton()
	
}//end class BeforeHighscores
