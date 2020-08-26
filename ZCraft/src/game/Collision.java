package game;

import java.io.IOException;
import java.util.LinkedList;

import javax.swing.ImageIcon;

public class Collision {

	/*
	 * Author: Carl Zhang
	 * Date: Monday 19, 2017
	 * Collision Detection
	 * Inputs Highscore into File
	 */
	
	private Game game;
	
	//all components involved in collision
	private Player player;
	private LinkedList<Missile> m;
	private LinkedList<Enemy> e;
	private LinkedList<Explosion> exp;
	
	private Controller c;
	
	//file used according to difficulty of game
	private String file;
	
	//score before death
	private int lastScore;

	public Collision(Player player, LinkedList<Missile> m, LinkedList<Enemy> e, LinkedList<Explosion> exp, Controller c, Game game) {
		this.player = player;
		this.m = m;
		this.e = e;
		this.exp = exp;
		this.game = game;
		
		this.c = c;
		
	}//end constructor Collision(Player player, LinkedList<Missile> m, LinkedList<Enemy> e, LinkedList<Explosion> exp, Controller c, Game game)
	
	public void checkCollisionMissileEnemy() {
		//loops through all missile and enemies on the field and checks if they are colliding
		for(int i = 0; i < m.size(); i++) {
			for(int j = 0; j < e.size(); j++) {
				if(m.get(i).getY() <= e.get(j).getY() + e.get(j).getHeight() &&
						m.get(i).getY() + m.get(i).getHeight() >= e.get(j).getY() + e.get(j).getHeight() && 
						m.get(i).getX() + m.get(i).getWidth() >= e.get(j).getX() && 
						m.get(i).getX() <= e.get(j).getX() + e.get(j).getWidth()) {
					
					//add Explosion
					c.explodeBoom(new Explosion(e.get(j).getX(), e.get(j).getY(), game));
					
					//remove Missile and Enemy
					c.removeMissile(m.get(i));
					c.removeEnemy(e.get(j));
					
					//add to Score
					game.setScore(game.getScore() + 100);
					
					//used to escape loop if collision is detected
					return;

				}
				
			}
			
		}
		
	}//end checkCollsion()
	
	public void checkCollisionPlayerEnemy() {
		//checks if any enemies have collided with player
		for(int i = 0; i < e.size(); i++) {
			if(player.getX() + player.getWidth() >= e.get(i).getX() && 
					player.getX() <= e.get(i).getX() + e.get(i).getWidth()) {
				
				if(player.getY() <= e.get(i).getY() + e.get(i).getHeight() &&
						player.getY() + player.getHeight() >= e.get(i).getY() + e.get(i).getHeight() ||
						player.getY() <= e.get(i).getY() &&
						player.getY() + player.getHeight() >= e.get(i).getY()) {
					
					//remove the enemy that crashed
					e.remove(e.get(i));
					
					//set highscore of player
					setHighscore(game.getScore());
					
					//go to gameover Panel
					game.setStateGameOver();
					
				}
				
			}
				
		}
		
	}//end checkCollisionPlayer()
	
	public void setHighscore(int score) {
		//choosing which while to access
		if(game.getEnemySpeed() == 1) {
			file = "data//Highscores_Easy.txt";	
			
		}else if(game.getEnemySpeed() == 2) {
			file = "data//Highscores_Medium.txt";
			
		}else {
			file = "data//Highscores_Hard.txt";
			
		}
		
		//lines in file
		String[] lines = new String[10];
		//scores in lines
		int[] fileScores = new int[10];
		int lineIndex = 0;	
		
		try {
			IO.openInputFile(file);
			
			String line = IO.readLine();
			
			//inserting previous highscores into arraylist
			while(line != null) {
				int last = line.indexOf("%") + 1;
				int scores = Integer.parseInt(line.substring(last, line.length()));
				lines[lineIndex] = line;
				fileScores[lineIndex] = scores;
					
				//increment index
				lineIndex++;
				line = IO.readLine();
				
			}
			
			IO.closeInputFile();

			//check if new highscore is larger than lowest score in top 10
			if(fileScores[0] < score) {
				//replace previous lowest with new top 10 score
				lines[0] = player.getUser() + "%" + game.getScore();
				fileScores[0] = score;
			}

			//sorts highscores
			for(int i = 0; i < lines.length - 1; i++) {
				int temp = fileScores[i];
				String tempLine = lines[i];
					
				if(fileScores[i] >= fileScores[i+1]) {
					fileScores[i] = fileScores[i+1];
					fileScores[i + 1] = temp;
					
					lines[i] = lines[i+1];
					lines[i + 1] = tempLine;

					
				}
				
			} 
			
			//replace existing highscores with new top 10
			IO.createOutputFile(file);
			for(int i = 0; i < lines.length; i++) {
				IO.println(lines[i]);
				
			}
			
			IO.closeOutputFile();

		}catch (IOException e) {
			System.out.println("Missing - Highscores");
			
		}
		
	}//end setHighscore
	
	public int getLastScore() {
		return lastScore;
		
	}//end getLastScore()
	
}//end class Collision
