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

public class GameOver {

	/*
	 * Author: Carl Zhang
	 * Date: Monday 19, 2017
	 * Game Over when Player Hit (Output Score/Message)
	 * 
	 */
	
	private Game game;

	private Rectangle back;
	private Image image;
	
	private Image explosionImage;
	
	private String gameOverTitle;
	private String gameOverMessage;
	private String gameOverScore;
	
	private Font f1;
	private Font f2;

	public GameOver(Game game) {
		this.game = game;
		
		//draw rect
		back = new Rectangle(0, game.getHeight() - game.getHeight()/10, game.getWidth()/10, game.getHeight()/10);
		
		try {
			//image = ImageIO.read(new File("res//BackButton.png"));
			image = (new ImageIcon(getClass().getClassLoader().getResource("BackButton.png"))).getImage();
		}catch (NullPointerException e) {
			System.out.println("Missing - BackButton");
		}
		
		try {
			//explosionImage = ImageIO.read(new File("res//Explosion.png"));
			explosionImage = (new ImageIcon(getClass().getClassLoader().getResource("Explosion.png"))).getImage();
		}catch (NullPointerException e) {
			System.out.println("Missing - Explosion");
		}
		
		gameOverTitle = "GAME OVER";
		gameOverMessage = "You Were Shot Down, Better Luck Next Time!";
		gameOverScore = "Score: ";

		
		f1 = new Font("Arial", Font.BOLD, 50);
		f2 = new Font("Arial", Font.BOLD, 20);
		
	}//end GameOver(Game game)

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(Color.WHITE);
		
		//gameOverTitle
		g2d.setFont(f1);
		g2d.drawString(gameOverTitle, game.getWidth()/5, game.getHeight()/10);
		
		//gameOverScore;
		g2d.drawString(gameOverScore + this.game.getScore(), game.getWidth()/5, game.getHeight()/5 * 2);
		
		//gameOverMessage
		g2d.setFont(f2);
		g2d.drawString(gameOverMessage, game.getWidth()/12, game.getHeight()/4);
		
		//sad face
		g2d.drawImage(explosionImage, game.getWidth()/4, game.getHeight()/2, game.getWidth()/2, game.getHeight()/2, null);
		
		//draw back button
		g2d.drawImage(image, 0, game.getHeight() - game.getHeight()/10, game.getWidth()/10, game.getHeight()/10, null);
		g2d.draw(back);
		
	}//end render(Graphics g)
	
	public Rectangle getBackButton() {
		return back;
		
	}//end getBackButton()

}//end GameOver
