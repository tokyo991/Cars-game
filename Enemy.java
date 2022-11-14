import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Enemy {
	int x;
	int y;
	int speed;
	Image img = new ImageIcon("res/enemy5.png").getImage();
	Road road;
	public Rectangle getRect() {
		return new Rectangle(x,y,100,100);}
	
	public Enemy (int x, int y, int speed,Road road) {
		this.x=x;
		this.y=y;
		this.speed=speed;
		this.road=road;
		
	}
	
	public void move() {
		x = x - road.p.speed + speed;
	}
}
