package game;

import java.awt.Graphics;
import java.util.LinkedList;

public class Controller {

	/*
	 * Author: Carl Zhang
	 * Date: Monday 19, 2017
	 * Controls Game Components (Spawning, Movement, etc...)
	 */
	
	private Game game;
	
	//components of game that will appear and disappear 
	private LinkedList<Missile> m = new LinkedList<Missile>();
	private LinkedList<Enemy> e = new LinkedList<Enemy>();
	private LinkedList<Explosion> exp = new LinkedList<Explosion>();
	
	Missile missile;
	Enemy enemy;
	Explosion explode;
	
	private Collision collide;
	
	//random x value where plane will appear
	int randomX;
	
	public Controller(Player player, Game game){
		this.game = game;
		
		collide = new Collision(player, m, e, exp, this, this.game);
		
		//random x value to spawn enemy (0 - WIDTH - width of enemy)
		randomX = (int) (Math.random() * (this.game.getWidth()/10 * 9) + 1);
		
	}//end constructor Controller()
	
	//update missile and enemy location
	public void update() {
		//missile
		for(int i = 0; i < m.size(); i++) {
			missile = m.get(i);
			
			//removes missile if off screen
			if(missile.getY() <= 0) {
				removeMissile(missile);
				
			}
			
			missile.update();
			
		}
		
		//enemy
		for(int i = 0; i < e.size(); i++) {
			enemy = e.get(i);
			
			//remove enemy once off screen
			if(enemy.getY() >= game.getHeight() - enemy.getHeight())
				removeEnemy(enemy);
			
			enemy.update();
			
		}
		
		//explosion
		for(int i = 0; i < exp.size(); i++) {
			explode = exp.get(i);
			
			//remove explosion once off screen
			if(explode.getY() >= game.getHeight()) {
				explodeGone(explode);
						
			}
					
			//update y value of explosion
			explode.update();
					
		}
		
		//checks if missile collides into enemy
		collide.checkCollisionMissileEnemy();
		//checks if player collides with enemy
		collide.checkCollisionPlayerEnemy();
		
	}//end update()
		
	//draw components
	public void render(Graphics g) {
		//missile
		for(int i = 0; i < m.size(); i++) {
			this.missile = m.get(i);
			this.missile.render(g);
			
		}
		
		//enemy
		for(int i = 0; i < e.size(); i++) {
			this.enemy = e.get(i);
			this.enemy.render(g);
			
		}	
		
		//explosion
		for(int i = 0; i < exp.size(); i++) {
			this.explode = exp.get(i);
			this.explode.render(g);
			
		}
		
	}//end render(Graphics g)
	
	public void shootMissile(Missile missile) {
		m.add(missile);
		
	}//end createMissile(Missile missile)
	
	public void removeMissile(Missile missile) {
		m.remove(missile);
		
	}//end removeMissile(Missile missile)
	
	public void spawnEnemy(Enemy enemy) {
		e.add(enemy);
		
	}//end spawnEnemy()
	
	public void removeEnemy(Enemy enemy) {
		e.remove(enemy);
		
	}//end removeEnemy(Enemy enemy)
	
	public void explodeBoom(Explosion explode) {
		exp.add(explode);
		
	}//end spawnEnemy()
	
	public void explodeGone(Explosion explode) {
		exp.remove(explode);
		
	}//end removeEnemy(Enemy enemy)
	
	public void removeAll() {
		m = new LinkedList<Missile>();
		e = new LinkedList<Enemy>();
		exp = new LinkedList<Explosion>();
		
	}//end removeAll()
	
	public int getRandomX() {
		randomX = (int) (Math.random() * (this.game.getWidth()/10 * 9) + 1);//CHANGE THIS
		return randomX;
		
	}//end randomX()
	
	public int getEnemyNum() {
		return e.size();
		
	}//end getEnemyNum()
	
}//end class Controller
