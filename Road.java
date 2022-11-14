import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
//import java.util.Timer;
import java.util.TimerTask;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Road extends JPanel implements ActionListener, Runnable {
	
	//every 20 miliseconds this timer will call function actionPerformed() in the object "this"
	Timer mainTimer = new Timer(20,this);

	//background image
	Image img = new ImageIcon("res/road4.png").getImage();
	
	Player p = new Player();
	
	Thread enemiesFactory = new Thread (this);
	
	List<Enemy> enemies = new ArrayList<Enemy>();
	
	public Road() {				
	      mainTimer.start(); 
	      enemiesFactory.start();
	      addKeyListener(new MyKeyAdapter());
	      setFocusable(true);
	}
	
	private class MyKeyAdapter extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			p.keyPressed(e);
		} 
		
		public void keyReleased(KeyEvent e) {
			p.keyReleased(e);
		} 
	}
	
	//function for redrawing the panel, gets called automatically
	public void paint(Graphics g) {		
		g = (Graphics2D)g;
		
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		RenderingHints.VALUE_ANTIALIAS_ON);
		
		g.drawImage(img,p.layer1,0,null);
		g.drawImage(img,p.layer2,0,null);
		g.drawImage(p.img,p.x,p.y,null);
		
		Iterator<Enemy> i = enemies.iterator();
		while(i.hasNext()) {
			Enemy e = i.next();
			if(e.x>=2400||e.x<=-2400) i.remove();
			else {
				e.move();
				g.drawImage(e.img,e.x,e.y,null);
			
			
			}
		}
	}
	
	
	public void actionPerformed(ActionEvent e ) {
		p.move();
		
		repaint();
		
		testCollisionWithEnemies();
	}

	private void testCollisionWithEnemies() {
		Iterator<Enemy> i = enemies.iterator();
		while(i.hasNext()) {
			Enemy e = i.next();
			if(p.getRect().intersects(e.getRect())) {
				JOptionPane.showMessageDialog (null, "GAME OVER");
				System.exit(1);
			}
		}
	}
	
	@Override
	public void run() {		//creates enemies indefinitely with different speed of moving
		while(true) {
			Random rand = new Random();
			try {
				Thread.sleep(rand.nextInt(2000));
				enemies.add(new Enemy (1200,rand.nextInt(600),rand.nextInt(60),this));
				
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}
		
	}
	
}
