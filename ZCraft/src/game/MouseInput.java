package game;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter{

	/*
	 * Author: Carl Zhang
	 * Date: Monday 19, 2017
	 * MouseListener
	 */
	
	Game game;
	
	public MouseInput(Game game) {
		this.game = game;
		
	}//end MouseInput(Game game)
	
	public void mouseClicked(MouseEvent e) {
		game.mouseClicked(e);
		
	}//end mouseClicked(MoueEvent e)
	
}
