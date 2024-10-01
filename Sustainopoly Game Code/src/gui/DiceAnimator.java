package gui;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.DiceAnimator.DiceAnimateEvent;

class Positioner {
	public int x;
	public int y;
	public int dx;
	public int dy;
	
	public Positioner(int x, int y, int dx, int dy) {
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
	}
	
	public static boolean collide(JPanel die1, JPanel die2) {
		Rectangle one = die1.getBounds();
		Rectangle two = die2.getBounds();
		return one.intersects(two);
	}

	// make sure we dont go out of bonds, if we do change the vector
	public void checkBounds(JComponent parent, JPanel panel) {
		if (x < 0) {
			dx = -dx;
			x = 0;
		} else if ((x + panel.getWidth()) > (parent.getX() + parent.getWidth())) {
			dx = -dx;
			x = (parent.getX() + parent.getWidth()) - panel.getWidth();
		}

		if (y < 0) {
			dy = -dy;
			y = 0;
		} else if ((y + panel.getHeight()) > (parent.getY() + parent.getHeight())) {
			dy = -dy;
			y = (parent.getY() + parent.getHeight()) - panel.getHeight();
		}		
	}	
}

public class DiceAnimator extends Thread {
	private DiceAnimateEvent ae;
	private JComponent parent;
	private JPanel die1Panel;
	private JPanel die2Panel;
	private JLabel cache[];
	private Random rand;
	
	//position
	private int x1, y1;
	private int x2, y2;
	
	//vector
	private long time;
	private int speed;
	private final int decayRate = 2;
	private int dx1 = 0;
	private int dy1 = 0;
	private int dx2 = 0;
	private int dy2 = 0;
	
	public static final long ANIMATION_TIME_MS_MAX = 3000;

	public static ImageIcon getImage(int number, int width, int height) {
		ImageIcon icon = new ImageIcon("images/Dice/Dice" + number + ".png");
		Image image = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);		
		return new ImageIcon(image);
	}
	
	public DiceAnimator(DiceAnimateEvent ae, JComponent parent, JPanel die1Panel, JPanel die2Panel, long time, int speed) {
		this.ae = ae;
		this.parent = parent;
		this.die1Panel = die1Panel;
		this.die2Panel = die2Panel;

		//cap
		this.time = (time > ANIMATION_TIME_MS_MAX)?ANIMATION_TIME_MS_MAX:time;
		this.speed = speed;

		//random
		this.rand = new Random();
		
		//cache
		this.cache = new JLabel[6];
		for (int i = 1; i <= 6; i++) {
			cache[i - 1] = new JLabel(DiceAnimator.getImage(i, 40, 40));
		}
	}

	private void decay() {
		if(dx1 != 0) {
			//decay towards zero velocity
			if(dx1 > 0) {
				dx1 -= decayRate;
			} else {
				dx1 += decayRate;
			}
		}
	
		if(dy1 != 0) {
			if(dy1 > 0) {
				dy1 -= decayRate;
			} else {
				dy1 += decayRate;
			}
		}
		
		if(dx2 != 0) {
			if(dx2 > 0) {
				dx2 -= decayRate;
			} else {
				dx2 += decayRate;
			}
		}

		if(dy2 != 0) {
			if(dy2 > 0) {
				dy2 -= decayRate;
			} else {
				dy2 += decayRate;
			}
		}		
	}
	
	@Override
	public void run() {

		long startTime = System.currentTimeMillis();
		// default throw start location
		x1 = parent.getX() + (rand.nextInt(parent.getWidth() / 10) + die1Panel.getWidth()); 
		y1 = parent.getY() + (rand.nextInt(parent.getHeight()) - die1Panel.getHeight());
		die1Panel.setLocation(x1, y1);
		
		x2 = x1 + die1Panel.getWidth() + rand.nextInt(50); 
		y2 = y1 + die1Panel.getHeight() + rand.nextInt(50);
		die2Panel.setLocation(x2, y2);
		
		die1Panel.setVisible(true);
		die2Panel.setVisible(true);
		
		//initialise a random addition
		dx1 = speed + rand.nextInt(10);
		dy1 = speed + rand.nextInt(10);
		dx2 = speed + rand.nextInt(5);
		dy2 = speed + rand.nextInt(5);		

		// while we haven't timed out move the die about while honouring bounds
		// and collision.  Decay speed (step size) too
		while (true) {
			try {
				Thread.sleep(100);
				
				x1 = x1 + dx1 ;
		        y1 = y1 + dy1;
		        
		        Positioner p1 = new Positioner (x1, y1, dx1, dy1);		
		        p1.checkBounds(parent, die1Panel);
		        x1 = p1.x;
		        dx1 = p1.dx;
		        y1 = p1.y;
		        dy1 = p1.dy;
		        
				x2 = x2 + dx2 ;
		        y2 = y2 + dy2;
		        
		        Positioner p2 = new Positioner (x2, y2, dx2, dy2);		
		        p2.checkBounds(parent, die2Panel);
		        x2 = p2.x;
		        dx2 = p2.dx;
		        y2 = p2.y;
		        dy2 = p2.dy;
				
				if (Positioner.collide(die1Panel, die2Panel)) {
					//meh, do something
					if(x1 < x2) {
						dx1 -= 3 ;
						dx2 += 5 ;
					}
					else {
						dx1 += 3 ;
						dx2 -= 5 ;
					}					
				}

				// set location and set a random die face
				die1Panel.setLocation(x1, y1);
				die1Panel.removeAll();
				die1Panel.add(cache[rand.nextInt(6)]);

				// set location and set a random die face
				die2Panel.setLocation(x2, y2);
				die2Panel.removeAll();
				die2Panel.add(cache[rand.nextInt(6)]);

				// if we have elapsed the break out
				long elapsedTime = System.currentTimeMillis() - startTime;
				if (elapsedTime > time) {
					break;
				} else {
					decay();
				}

			} catch (InterruptedException ie) {
				// nothing to do
			}
		}

		if (ae != null) {
			ae.onAnimateComplete();
		}
	}

	public void animate() {
		start();
	}

	public interface DiceAnimateEvent {
		void onAnimateComplete();
	}
}
