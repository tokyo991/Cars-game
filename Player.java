import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints.Key;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Player {
	
	public static final int MAX_SPEED = 100;
	public static final int MAX_TOP = 10;
	public static final int MAX_BOTTOM = 430;

	Image img = new ImageIcon("res/player.png").getImage();
	
	int speed = 0;
	int acceleration = 0;
	int kilometers = 0;
	
	//coordinates of the player's car
	int x = 10;		
	int y = 15;
	int dy = 0;
	
	//these layers are needed for animating movement
	int layer1=0;		
	int layer2 = 1200;
	
	public Rectangle getRect() {
		return new Rectangle(x,y,60, 61);
	}
	
	public void move() {
		
		kilometers += speed;
		speed+=acceleration;
		if(speed<=0) speed = 0;  //no negative speed
		if(speed>=MAX_SPEED) speed = MAX_SPEED;
		y-=dy;
		if(y<=MAX_TOP) y = MAX_TOP;
		if(y>=MAX_BOTTOM) y = MAX_BOTTOM;
		if(layer2 - speed <=0) {
			//return coordinates to default
			layer1 = 0;		
			layer2 = 1200;
		
		}else {
		layer1 -= speed;
		layer2 -= speed;
		}
		
	}
	
	//user interaction
	public void keyPressed(KeyEvent e) {
		
		int key = e.getKeyCode();
		if (key==KeyEvent.VK_RIGHT) acceleration = 5;
		
		if (key==KeyEvent.VK_LEFT) acceleration = -5;
		
		if (key==KeyEvent.VK_UP) dy= 10;
		
		if (key==KeyEvent.VK_DOWN) dy= -10;
	}
	
	//user interaction
	public void keyReleased(KeyEvent e) {
		

		int key = e.getKeyCode();
		if (key==KeyEvent.VK_RIGHT) acceleration = 0;
		
		if (key==KeyEvent.VK_LEFT) acceleration = 0;
		

		if (key==KeyEvent.VK_UP) dy= 0;
		
		if (key==KeyEvent.VK_DOWN) dy= 0;
		
	}
}
