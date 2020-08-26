package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class Game extends JPanel implements ActionListener {
	
	/*
	 * Author: Carl Zhang
	 * Date: Monday, June 19, 2017
	 * 
	 * ZCraft Game, renders everything, and contains class
	 * - KeyListener
	 * - MouseListener
	 * - ActionListener
	 * 
	 * Allows user to play
	 * 
	 */
	
	//dimensions of game
	public static final int WIDTH = 500;
	public static final int HEIGHT = 500;
		
	//timer
	private Timer timer = new Timer(5, this);	
	
	//other game components
	private Login login;
	private Menu menu;
	private BeforeGame beforeGame;
	private Instructions instruction;
	private BeforeHighscores beforeHighscore;
	private Highscores highscore;
	private GameOver go;
	
	//enum for switching between game components
	private GUI gui;
	private enum GUI{LOGIN, MENU, BEFORE_GAME, GAME, INSTRUCTION, BEFORE_HIGHSCORE, HIGHSCORE, GAMEOVER};
	
	//player
	private Player player;
		
	//controller
	private Controller c;
	
	//player score
	private int score;
	
	//number of enemies & waves
	private int enemyNum;
	private int enemySpeed;
	private int wave;
	
	//detects pause game
	private boolean pause;
	//allows pause game
	private Pause pauseButton;
	
	//detects gameOver
	private boolean gameOver;
	
	//background image (Moving)
	private Image background;
	private Image background2;
	private int backgroundX;
	private int backgroundY;
	private int background2Y;
	private final int BACKGROUND_SPEED = 1;
	
	//direction plane is going
	private boolean up;
	private boolean down;
	private boolean left;
	private boolean right;
	private boolean fire;
	
	public Game(){
		this.setFocusable(true);
		this.requestFocusInWindow();
		this.addKeyListener(new KeyInput(this));
		this.addMouseListener(new MouseInput(this));
		
		//components
		login = new Login(this);
		menu = new Menu(this);
		beforeGame = new BeforeGame(this);
		instruction = new Instructions(this);
		beforeHighscore = new BeforeHighscores(this);
		
		go = new GameOver(this);
		
		//initialize player, controller, and emc
		player = new Player(WIDTH/2 - WIDTH/24, HEIGHT/2 - HEIGHT/24, this);
		c = new Controller(player, this);
		//emc = new EnemyMissileController(this);
				
		//game is paused at start
		pause = true;
		pauseButton = new Pause(this);
		
		//background
		try {
			//this.background = ImageIO.read(new File("res//Background.png"));
			//this.background2 = ImageIO.read(new File("res//Background.png"));
			this.background = (new ImageIcon(getClass().getClassLoader().getResource("Background.png"))).getImage();
			this.background2 = (new ImageIcon(getClass().getClassLoader().getResource("Background.png"))).getImage();
		} catch (NullPointerException e) {
			System.out.println("Missing - Background");
		}
		
		//set directions to false (not moving)
		up = false;
		down = false;
		left = false;
		right = false;
		
		//set firing and shooting to false
		fire = false;
		
		//intialize score
		score = 0;
		
		//set enemyNum and wave number to 0
		enemyNum = 0;
		wave = 0;
		
		//set gui to the login
		gui = GUI.LOGIN;
		
		gameOver = false;
		
		backgroundX = 0;
		backgroundY = 0;
		background2Y = -500;
		
		//start Timer
		this.timer.start();
		
	}//end constructor GUI()

	public void update() {
		if(!up || player.getY() <= 0) {
			player.setY(player.getY());
			
		}else if(up) {
			player.setY(player.getY() - player.getSpeed());
			
		}//up
		
		if(!down || player.getY() >= this.getWidth() - player.getWidth()) {
			player.setY(player.getY());
			
		}else if(down) {
			player.setY(player.getY() + player.getSpeed());
			
		}//down
		
		if(!left || player.getX() <= 0) {
			player.setX(player.getX());
			
		}else if(left) {
			player.setX(player.getX() - player.getSpeed());
			
		}//left
		
		if(!right || player.getX() >= this.getHeight() - player.getHeight()) {
		player.setX(player.getX());
			
		}else if(right) {
			player.setX(player.getX() + player.getSpeed());
			
		}//right
		
		if(fire) {
			c.shootMissile(new Missile(player.getX() + player.getWidth()/2 - 10, 
					player.getY() + player.getHeight()/2 - 30, this));
			
			//set fire to false (player has to press key separately)1
			fire = false;

		}//fire
			
		//update
		if(!gameOver) {
			c.update();
			//emc.update();
			
		}

	}//end update()
	
	public void paintComponent(Graphics g) {
		//background (black)
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		if(gui == GUI.BEFORE_GAME) {
			beforeGame.render(g);
			
		}else if(gui == GUI.GAME) {
			//sound2.play("sound//ExplosionSound.wav");
			//override background
			g.drawImage(background, backgroundX,  backgroundY, getWidth(), getHeight(), null);
			g.drawImage(background2, backgroundX,  background2Y, getWidth(), getHeight(), null);
			
			//moves background 
			if(backgroundY >= HEIGHT) 
				backgroundY = -500;
				
			if(background2Y >= HEIGHT) 
				background2Y = -500;
			
			//score display
			g.setColor(Color.WHITE);
			g.drawString(" Score: " + String.valueOf(score), 0, getHeight()/100 * 99);
			
			g.drawString(" Wave: " + String.valueOf(wave), getWidth()/20 * 17, getHeight()/100 * 99);
			
			//draw player 
			player.render(g);
			//draw enemy planes and missiles
			c.render(g);
			
			//stop timer after pause button is shown
			if(pause) {
				pauseButton.render(g);
				timer.stop();
				
			}
			
		}else if(gui == GUI.LOGIN) {
			//draw login components
			login.render(g);
			
		}else if(gui == GUI.MENU) {
			//draw menu components
			menu.render(g);
			
		}else if(gui == GUI.INSTRUCTION) {
			//draw instruction components
			instruction.render(g);
			
		}else if(gui == GUI.BEFORE_HIGHSCORE) {
			//draw before highscore components
			beforeHighscore.render(g);
			
		}else if(gui == GUI.HIGHSCORE) {
			//draw highscore components
			highscore.render(g);
			
		}else if(gui == GUI.GAMEOVER) {
			//draw gameover Components
			go.render(g);
			
		}
		
	}//end paintComponenent(Graphics g)
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//update only when it Game is chosen
		if(gui == GUI.GAME) {
			//move background
			backgroundY += BACKGROUND_SPEED;
			background2Y += BACKGROUND_SPEED;
			
			//checks how many enemies are on the board
			//if 0, spawn more enemies
			if(c.getEnemyNum() == 0) {
				//add 50 to score after 1st wave
				if(wave != 0)
					score += 50;
				
				wave++;
				enemyNum++;
				
				//spawn
				for(int i = 0; i < enemyNum; i++) {
					c.spawnEnemy(new Enemy(c.getRandomX(), 0, this));
					//c.spawnEnemy(new Enemy(c.getRandomX(), getHeight()/10 * 9, direction, this));
					
				}
				
			}
			
			//update game 
			this.update();	
			
		}
		
		//repaint the different enum
		this.repaint();
		
	}//end actionPerformed(ActionEvent e)
	
	public void keyPressed(KeyEvent e) {
		//update only when it Game is chosen
		if(gui == GUI.GAME) {
			int key = e.getKeyCode();
			
			//setting a direction to true when key is pressed
			if(key == KeyEvent.VK_UP) {
				up = true;
			}
			
			if(key == KeyEvent.VK_DOWN) {
				down = true;
			}
		
			if(key == KeyEvent.VK_LEFT) {
				left = true;
			}
		
			if(key == KeyEvent.VK_RIGHT) {
				right = true;
			}
			
			//shoot
			if(key == KeyEvent.VK_SPACE) {
				fire = true;
				
			}
			
		}
		
	}//end keyPressed(KeyEvent e)
	
	public void keyReleased(KeyEvent e) {
		//update only when it Game is chosen
		if(gui == GUI.GAME) {
			int key = e.getKeyCode();
			
			//setting a direction to false when key is released
			//plane does not move
			if(key == KeyEvent.VK_UP) {
				up = false;
			}
			
			if(key == KeyEvent.VK_DOWN) {
				down = false;
			}
			
			if(key == KeyEvent.VK_LEFT) {
				left = false;
			}
			
			if(key == KeyEvent.VK_RIGHT) {
				right = false;
			}
			
			//if space is released don't fire
			if(key == KeyEvent.VK_SPACE) {
				fire = false;
				
			}
			
			//pause and continue game
			if(key == KeyEvent.VK_1) {
				
				if(!pause) {
					pause = true;
					
				}else if(pause) {
					timer.start();
					pause = false;
					
				}
				
			}
			
			if(key == KeyEvent.VK_2) {
				//pause game
				pause = true;
				
				int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to go to menu\nYou will lose your score", "Confirmation", JOptionPane.OK_CANCEL_OPTION);
				
				//check if user is sure
				if (option == JOptionPane.OK_OPTION) {	
					//start timer to allow change in enum
					timer.start();
					
					//reset everything and go to menu
					restart();
					
				}
				
			}
			
		}
		
	}//end keyReleased(KeyEvent e)
	
	public void mouseClicked(MouseEvent e) {
		// mouseClicked(MouseEvent e)
		if(gui == GUI.LOGIN) {
			//info already in game
			if(login.getLogin().contains(e.getX(), e.getY())) {
				JTextField username = new JTextField();
				JTextField password = new JTextField();
				Object[] info = {
						"Username:", username,
						"Password:", password,
				};
			
				int option = JOptionPane.showConfirmDialog(null, info, "Please Enter Login Info", JOptionPane.OK_CANCEL_OPTION);
				if (option == JOptionPane.OK_OPTION) {
					//checks if info is in file
					try {
						IO.openInputFile("data//Info.txt");
					
						String line = IO.readLine();	
						
						while(line != null) {
							//if username and password match, player is set to user
							if(line.compareTo(username.getText() + "%" + password.getText()) == 0) {
								player.setUser(username.getText());

								break;
									
							}else {
								line = IO.readLine();
									
							}

						}//end while
							
						IO.closeInputFile();
								
						//shows message when user has not logined in
						if(player.getUser() == null)
							JOptionPane.showMessageDialog(null, "Wrong Info, Please Create a New Account", "Error Login" , JOptionPane.OK_CANCEL_OPTION);
							
					} catch (IOException e1) {
						System.out.println("Error");
					
					}//end try/catch
				
					//goes to menu when user has logged in
					if(player.getUser() != null) 
						//set gui to menu
						gui = GUI.MENU;
					
				}
			
				//new user for game
			}else if(login.getCreateUser().contains(e.getX(), e.getY())) {
				JTextField username = new JTextField();
				JTextField password = new JTextField();
				Object[] info = {
						"Username:", username,
						"Password:", password,
				};
			
				int option = JOptionPane.showConfirmDialog(null, info, "Please Enter Info", JOptionPane.OK_CANCEL_OPTION);
			
				//controls if valid username is entered
				if (option == JOptionPane.OK_OPTION) {
					//checks if username is empty string
					if(username.getText().compareTo("") == 0) {
						//if username is empty string
						JOptionPane.showMessageDialog(null, "Invalid Username or Password", "Invalid", JOptionPane.OK_CANCEL_OPTION);
						
						
					}else {
						boolean pass = true;
						
						try {
							//checks if username already exists
							IO.openInputFile("data//Info.txt");
								
							String line = IO.readLine();
								
							while(line != null) {
								int last = line.indexOf("%");

								if(username.getText().compareTo(line.substring(0, last)) == 0) {
									JOptionPane.showMessageDialog(null, "This Username Already Exists", "Username Taken", JOptionPane.OK_CANCEL_OPTION);
											
									pass = false;
									break;
										
								}
									
								line = IO.readLine();
									
							}//end while
								
							IO.closeInputFile();
								
							if(pass) {
								//putting new info into file
								IO.appendOutputFile("data//Info.txt");
								IO.println(username.getText() + "%" + password.getText());
								IO.closeOutputFile();
								   	
							}	
								
						} catch (IOException e1) {
							System.out.println("Missing - Info");
							
						}//end try/catch
						
					}
					
				}
					    
			}
			
		//mouse mouseClicked(MouseEvent e)
		}else if(gui == GUI.MENU) {
			if(menu.getPlayButton().contains(e.getX(), e.getY())) {
				//set game to paused
				gameOver = false;
				pause = true;
				
				gui = GUI.BEFORE_GAME;
				
		    }else if(menu.getInstructionButton().contains(e.getX(), e.getY())) {
		    	gui = GUI.INSTRUCTION;
		    	
		    }else if(menu.getHighscoreButton().contains(e.getX(), e.getY())) {
		    	gui = GUI.BEFORE_HIGHSCORE;
		    	
		    }else if(menu.getBackButton().contains(e.getX(), e.getY())) {
		    	//set user to null when going back to login page 
		    	player.setUser(null);
		    	gui = GUI.LOGIN;
		    	
		    }
			
		//beforeGame mouseClicked(MouseEvent e)
		}else if(gui == GUI.BEFORE_GAME) {
			if(beforeGame.getEasy().contains(e.getX(), e.getY())) {
				enemySpeed = 1;
				gui = GUI.GAME;
				
			}else if(beforeGame.getMedium().contains(e.getX(), e.getY())) {
				enemySpeed = 2;
				gui = GUI.GAME;
				
			}else if(beforeGame.getHard().contains(e.getX(), e.getY()))  {
				enemySpeed = 3;
				gui = GUI.GAME;
				
			}else if(beforeGame.getBackButton().contains(e.getX(), e.getY())) {
				gui = GUI.MENU;
				
			}
			
		//instruction mouseClicked(MouseEvent e)
		}else if(gui == GUI.INSTRUCTION) {
			if(instruction.getBackButton().contains(e.getX(), e.getY()))
		    	gui = GUI.MENU;
			
		}else if(gui == GUI.BEFORE_HIGHSCORE) {	
			if(beforeHighscore.getEasy().contains(e.getX(), e.getY())) {
				enemySpeed = 1;
				//new highscore to update scores
				highscore = new Highscores(this);
				highscore.getHighscore();
				
				gui = GUI.HIGHSCORE;
				
			}else if(beforeHighscore.getMedium().contains(e.getX(), e.getY())) {
				enemySpeed = 2;
				
				//new highscore to update scores
				highscore = new Highscores(this);
				highscore.getHighscore();
				
				gui = GUI.HIGHSCORE;
				
			}else if(beforeHighscore.getHard().contains(e.getX(), e.getY()))  {
				enemySpeed = 3;
				
				//new highscore to update scores
				highscore = new Highscores(this);
				highscore.getHighscore();
				
				gui = GUI.HIGHSCORE;
				
			}else if(beforeHighscore.getBackButton().contains(e.getX(), e.getY())) {
				gui = GUI.MENU;
				
			}
			
		//highscore mouseClicked(MouseEvent e)
		}else if(gui == GUI.HIGHSCORE) {
			if(highscore.getBackButton().contains(e.getX(), e.getY()))
		    	gui = GUI.MENU;
			
		}else if(gui == GUI.GAMEOVER) {
			if(go.getBackButton().contains(e.getX(), e.getY()))
				restart();
			
		}
	
	}//end mouseClicked(MouseEvent e)
	
	public int getWidth() {
		return WIDTH;
		
	}//end getWidth() 
	
	public int getHeight() {
		return HEIGHT;
		
	}//end getHeight()
	
	public int getScore() {
		return score;
		
	}//get getScore()
	
	public void setScore(int s) {
		score = s;
		
	}//end setScore(int s)
	
	public void setWave(int w) {
		wave = 0;
		
	}//end setWave(int w)
	
	public int getEnemySpeed() {
		return enemySpeed;
				
	}//end getEnemySpeed()
	
	public void setPause(boolean b) {
		pause = b;
		
	}//end setPause(Boolean b)
	
	//get and set methods
	public void setStateGameOver() {
		gui = GUI.GAMEOVER;
		
	}//end setStateGameOver()
	
	public void restart() {
		//change enum to menu
		gui = GUI.MENU;
		//reset player and controller
		String tempUser = player.getUser();
		player = new Player(WIDTH/2 - WIDTH/24, HEIGHT/2 - HEIGHT/24, this);
		player.setUser(tempUser);
		
		c = new Controller(player, this);
	
		//set gameOver to true
		gameOver = true;
		
		//reset background
		backgroundY = 0;
		background2Y = -500;
		
		//reset keyListener
		up = false;
		down = false;
		left = false;
		right = false;
		fire = false;
		
		//reset stats
		score = 0;
		wave = 0;
		enemyNum = 0;
		
		//make pause true
		pause = true;
			
	}//end restart()
	
	public static void main(String[] args) {
		Game game = new Game();
		
		//allow pack()
		game.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		game.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		game.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		
		JFrame frame = new JFrame("ZCraft");
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
	}//end main

}//end class GUI
