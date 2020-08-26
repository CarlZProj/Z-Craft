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

public class Instructions {

	/*
	 * Author: Carl Zhang
	 * Date: Monday 19, 2017
	 * Instructions
	 */
	
	private Game game;
	
	//back button
	private Image image;
	private Rectangle back;
	
	private Image happyImage;
	
	private String[] instructions = {"Goal: ", "- Avoid enemy aircrafts",
			"and shoot them down to earn points", "How to Play: ", "- Press arrow key UP to move forward",
			"- Press arrow key RIGHT to move right", "- Press arrow key DOWN to move backward", "- Press arrow key LEFT to move left", "- Press key SPACE to shoot", "Pause & Continue Game:", "- Press 1", "Go to Menu:", "- Press 2"};
	private Font f1 = new Font("Arial", Font.BOLD, 20);
	
	
	public Instructions(Game game){
		this.game = game;
		
		back = new Rectangle(0, game.getHeight() - game.getHeight()/10, game.getWidth()/10, game.getHeight()/10);
		
		try {
			//image = ImageIO.read(new File("res//BackButton.png"));
			image = (new ImageIcon(getClass().getClassLoader().getResource("BackButton.png"))).getImage();	
		}catch (NullPointerException e) {
			System.out.println("Missing - BackButton");
			
		}
		
		try {
			//happyImage = ImageIO.read(new File("res//Happy.png"));
			happyImage = (new ImageIcon(getClass().getClassLoader().getResource("Happy.png"))).getImage();
		}catch (NullPointerException e) {
			System.out.println("Missing - Happy");
		}
		
	}//end constructor Instruction()
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		//draw image & rectangle
		g2d.drawImage(image, 0, game.getHeight() - game.getHeight()/10, game.getWidth()/10, game.getHeight()/10, null);
		g2d.draw(back);
		
		//draw instructions
		g2d.setColor(Color.WHITE);
		g2d.setFont(f1);
		
		for(int i = 0; i < instructions.length; i++) {
			g2d.drawString(instructions[i], game.getWidth()/8, game.getHeight()/8 + i * 30);
		}
		
		g.drawImage(happyImage, game.getWidth()/ 16 * 11, game.getHeight()/ 16 * 10, game.getWidth()/4, game.getHeight()/4, null);
		
	}//end paintComponent(Graphics g)
	
	public Rectangle getBackButton() {
		return back;
		
	}//end getBackButton()
	
}//end class Instructions
