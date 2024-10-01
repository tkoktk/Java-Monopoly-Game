package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * IconButton is designed for use in GUI elements such as the "ControlPanels" Panel.
 */
public class IconButton extends JButton implements MouseListener {

	private static final long serialVersionUID = 1L;

	/**
	 * @param text: the caption for the button. Will be aligned under the button in
	 *              the centre. This child of JButton overrides MouseListener
	 *              methods for hovering over the button, which change the colour of
	 *              this text.
	 */
	public IconButton(String text, ImageIcon buttonIcon) {
		super(text); // call the constructor of the JButton class

		// Sets the size of the button's icon and therefore the button, since the button
		// is just the icon with its text
		buttonIcon.setImage(buttonIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
		setIcon(buttonIcon);

		// Font and Colour
		setFont(new FontOne(20));
		setForeground(Color.WHITE);
		// The Button's text is set to be aligned in the centre, under the button.
		setHorizontalTextPosition(SwingConstants.CENTER);
		setVerticalTextPosition(SwingConstants.BOTTOM);
		// The border and content area filled are turned off so that the user only sees
		// the button's image and its text.
		setBorder(null);
		setContentAreaFilled(false);

		// Ensures that the overriden MouseListener methods work
		addMouseListener(this);
	}

	/**
	 * The mouseEntered MouseListener is overridden for this class. If the button is
	 * enabled, hovering over it will change its text to the colour yellow. If the
	 * button is disabled, it will change its text to the colour red to indicate to
	 * the user that it's not available.
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		if (isEnabled()) {
			setForeground(Color.YELLOW);
		} else {
			setForeground(Color.RED);
		}
	}

	/**
	 * The mouseExited MouseListener is overridden for this class. If a user is
	 * hovering over the button, and then stops hovering on the button, the button's
	 * text will change back to the colour white.
	 */

	@Override
	public void mouseExited(MouseEvent e) {
		setForeground(Color.WHITE);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

}
