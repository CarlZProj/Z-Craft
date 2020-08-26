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

public class Menu{
	
	/*
	 * Author: Carl Zhang
	 * Date: Monday 19, 2017
	 * Options - Play, Instructions, Highscores, or Logout
	 */
	
	private Game game;
	
	private Image background;
	
	private String[] options = {"Play", "Instructions", "Highscores", "Back"};
	private Rectangle[] rect = new Rectangle[4];
	
	private Font f1, f2;
	
	public Menu(Game game){
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
		
		//bounds of rectangle buttons
		for(int i = 0; i < rect.length; i++) {
			if(i == 0 || i  == 3) {
				rect[i] = new Rectangle(this.game.getWidth()/3, this.game.getHeight()/5 * (i + 1) + this.game.getHeight()/20,  this.game.getWidth()/3, this.game.getHeight()/10);
				
			}else {
				rect[i] = new Rectangle(this.game.getWidth()/5, this.game.getHeight()/5 * (i + 1) + this.game.getHeight()/20,  this.game.getWidth()/5 * 3, this.game.getHeight()/10);
			}
			
		}
		
	}//end constructor Menu(Game game)
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		//draw background
		g2d.drawImage(background, 0, 0, game.getWidth(), game.getHeight(), null);
		
		//title
		g2d.setColor(Color.WHITE);
		g2d.setFont(f1);
		g2d.drawString("Z-Craft", game.getWidth()/6, game.getHeight()/5);
			
		//draw rect & label
		for(int i = 0; i < rect.length; i++) {
			g2d.draw(rect[i]);
			g2d.setFont(f2);

			if(i == 0 || i == 3) {
				g2d.drawString(options[i], game.getWidth()/3 + this.game.getWidth()/18, game.getHeight()/5 * (i + 2) - game.getHeight()/15);
				
			}else {
				g2d.drawString(options[i], game.getWidth()/5 + this.game.getWidth()/18, game.getHeight()/5 * (i + 2) - game.getHeight()/15);
				
			}
			
		}

	}//end render(Graphics g)

	public Rectangle getPlayButton() {
		return rect[0];
		
	}//end getPlayButton()
	
	public Rectangle getInstructionButton() {
		return rect[1];
		
	}//end getInstructions()
	
	public Rectangle getHighscoreButton() {
		return rect[2];
		
	}//end getHeightscores()
	
	public Rectangle getBackButton() {
		return rect[3];
		
	}//end getBackButton()
	
}//end class Menu
