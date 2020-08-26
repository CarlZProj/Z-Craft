package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.Timer;

public class Highscores implements ActionListener {

	/*
	 * Author: Carl Zhang
	 * Date: Monday 19, 2017
	 * Outputs Highscores (Easy, Medium, Hard)
	 * File Access
	 * 
	 */
	
	private Game game;
	
	//slower timer
	private Timer timer = new Timer(600, this);
	
	//back button
	private Image image;
	private Rectangle back;
	
	private ArrayList<String> highscores;
	//index of how many highscores to display
	private int index;
	
	//controls which highscore difficulty file to access
	String file;
	
	String title;
	String difficulty;
	
	private Font f1 = new Font("Arial", Font.BOLD, 90);
	private Font f2 = new Font("Arial", Font.BOLD, 50);
	private Font f3 = new Font("Arial", Font.ITALIC, 30);
	
	public Highscores(Game game) {
		this.game = game;
		highscores = new ArrayList<String>();
		
		back = new Rectangle(0, game.getHeight() - game.getHeight()/10, game.getWidth()/10, game.getHeight()/10);
		
		try {
			//image = ImageIO.read(new File("res//BackButton.png"));
			image = (new ImageIcon(getClass().getClassLoader().getResource("BackButton.png"))).getImage();
		} catch (NullPointerException e) {
			System.out.println("Missing - BackButton");
		}
		
		title = "Highscores";
		
		timer.start();
		
	}//end constructor Highscores()
	
	public void render(Graphics g) {
		//chooses difficulty to show initialize
		if(game.getEnemySpeed() == 1) {
			difficulty = "Easy";
							
		}else if(game.getEnemySpeed() == 2) {
			difficulty =  "Medium";
							
		}else {
			difficulty = "Hard";
							
		}
		
		Graphics2D g2d = (Graphics2D) g;
		
		//draw title and back button
		g2d.setColor(Color.WHITE);
		g2d.setFont(f1);
		g2d.drawString(title, 0, game.getHeight()/5);
		
		g2d.setFont(f2);
		g2d.drawString(difficulty, game.getWidth()/24 * 7, game.getHeight()/3);
		
		g2d.drawImage(image, 0, game.getHeight() - game.getHeight()/10, game.getWidth()/10, game.getHeight()/10, null);
		g2d.draw(back);
		
		//update the number of highscores
		update(g2d);
		
	}//end paintComponent(Graphics g)
	
	public void getHighscore() {
		//choosing which while to access
		if(game.getEnemySpeed() == 1) {
			file = "data//Highscores_Easy.txt";
					
		}else if(game.getEnemySpeed() == 2) {
			file = "data//Highscores_Medium.txt";
					
		}else {
			file = "data//Highscores_Hard.txt";
					
		}
				
		try {
			IO.openInputFile(file);
			
			String line = IO.readLine();

			while(line != null) {
				highscores.add(line);
				line = IO.readLine();
				
			}
			
			IO.closeInputFile();
			
		}catch(IOException e) {
			System.out.println("Missing - Highscores");
			
		}//end try/catch
		
		index = highscores.size() - 1;
		
	}//end getHighscore() {
	
	public void update(Graphics g) {
		g.setFont(f3);
		
		//adding scores
		for(int i = 0; i < highscores.size() - index; i++) {
			String tempScore = highscores.get(i).replace("%", ": ");
			g.drawString((10 - i) + ". " + tempScore, game.getWidth()/5 + game.getWidth()/10, game.getHeight()/15 * (10 - i) + game.getHeight()/100 * 33);
			
			//stop animations when all scores are printed
			if(i == 9)
				timer.stop();
				
			/*

			//reset to create animation of highscores
			if(index == -1) {
				i = 0;
				index = highscores.size() - 1;
				
			}
			
			 */
			
		}

	}//end update(Graphics g)
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//decrease index so more highscores are printed
		index--;
		
	}//end actionPerformed(ActionEvent e)
	
	public Rectangle getBackButton() {
		return back;
		
	}//end getBackButton()
	
}//end class Highscores