package gui;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.geom.GeneralPath;
import java.awt.geom.PathIterator;

import sustainopoly.Player;

public class PlayerAnimator extends Thread {
	private GeneralPath generalPath;
	private PlayerAnimateEvent pae;
	private JComponent parent;
	private JPanel playerPanel;
	private ImageIcon playerIcon;
	private int speed;

	public ImageIcon getImage(ImageIcon icon, int width, int height) {
		Image image = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return new ImageIcon(image);
	}

	public PlayerAnimator(PlayerAnimateEvent ae, JComponent parent, JPanel playerPanel, ImageIcon playerIcon, GUISquare from, GUISquare to, int speed) {
		this.pae = ae;
		this.parent = parent;
		this.playerPanel = playerPanel;
		this.playerIcon = playerIcon;
		this.speed = speed;
		playerPanel.add(new JLabel(getImage(this.playerIcon, 40, 40)));
		playerPanel.setBounds(from.getX() + from.getWidth() / 2, from.getY() + from.getHeight() / 2, 40, 40);
		playerPanel.setVisible(true);
		creatPath(from, to);
	}

	private Point animatePath(Point to, int speed) {
		double step = speed;
		Point f = new Point(playerPanel.getX(), playerPanel.getY());
		double delta_x = to.x - f.x;
		double delta_y = to.y - f.y;
		double dist = 0.0;

		float width = playerPanel.getWidth();
		float height = playerPanel.getHeight();

		double total = Math.sqrt((delta_x * delta_x) + (delta_y * delta_y));
		double halfWay = total / 2;

		while (true) {
			delta_x = to.x - f.x;
			delta_y = to.y - f.y;
			dist = Math.sqrt((delta_x * delta_x) + (delta_y * delta_y));

			if (dist > step) {
				double ratio = step / dist;
				double x_move = ratio * delta_x;
				double y_move = ratio * delta_y;
				int new_x_pos = (int) x_move + f.x;
				int new_y_pos = (int) y_move + f.y;
				playerPanel.setLocation(new_x_pos, new_y_pos);

				// up toward halfway, and down thereafter 
				if (dist >= halfWay) {
					width += 2;
					height += 2;
				} else {
					width -= 3;
					height -= 3;
				}
				//re-scale to get a pop out of board effect
				playerPanel.setSize(new Dimension((int) width, (int) height));
				playerPanel.add(new JLabel(getImage(playerIcon, (int) width, (int) height)));
				playerPanel.remove(0);

				//hang about
				animationDelay(60);

				//update
				f = playerPanel.getLocation();

			} else {
				break;
			}
		}

		return f;
	}

	private void animationDelay(int delay) {
		try {
			sleep(delay);
		} catch (InterruptedException e) {
			// noting to do
		}
	}

	private void creatPath(GUISquare fromSquare, GUISquare toSquare) {
		generalPath = getFromTo(fromSquare, toSquare);
	}

	private GeneralPath getFromTo(GUISquare fromSquare, GUISquare toSquare) {
		GeneralPath path = new GeneralPath();

		path.moveTo(fromSquare.getX() + fromSquare.getWidth() / 2, fromSquare.getY() + fromSquare.getHeight() / 2);
		path.lineTo(toSquare.getX() + toSquare.getWidth() / 2, toSquare.getY() + toSquare.getHeight() / 2);

		return path;
	}

	private Point getPathDestination(PathIterator pi) {
		double[] coordinates = new double[6];
		int type = pi.currentSegment(coordinates);
		Point p = null;

		switch (type) {
		case PathIterator.SEG_MOVETO:
			playerPanel.setLocation((int) coordinates[0], (int) coordinates[1]);
			break;
		case PathIterator.SEG_LINETO:
			p = new Point((int) coordinates[0], (int) coordinates[1]);
			break;
		case PathIterator.SEG_QUADTO:
			System.out.println("quadratic to " + coordinates[0] + ", " + coordinates[1] + ", " + coordinates[2] + ", "
					+ coordinates[3]);
			break;
		case PathIterator.SEG_CUBICTO:
			System.out.println("cubic to " + coordinates[0] + ", " + coordinates[1] + ", " + coordinates[2] + ", "
					+ coordinates[3] + ", " + coordinates[4] + ", " + coordinates[5]);
			break;
		case PathIterator.SEG_CLOSE:
			System.out.println("close");
			break;
		default:
			break;
		}
		return p;
	}

	@Override
	public void run() {
		if (pae != null) {
			if (generalPath != null) {
				PathIterator pi = generalPath.getPathIterator(null);

				while (true) {

					// do some animation and notify when done
					while (pi.isDone() == false) {
						Point p = getPathDestination(pi);

						if (p != null) {
							// do the animation
							animatePath(p, speed);
						}
						pi.next();
					}

					break;
				}
			}

			pae.onAnimateComplete();
		}
	}

	public void animate() {
		start();
	}

	public interface PlayerAnimateEvent {
		void onAnimateComplete();
	}
}
