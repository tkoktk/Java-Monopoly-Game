package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicScrollBarUI;

import sustainopoly.*;

final class GuiAttributes {

	static Color COLOR_LIGHT_RED = new Color(215, 0, 0);
	static Color COLOR_LIGHT_GREEN = new Color(38, 197, 38);
	static Color COLOR_LIGHT_BLUE = new Color(51, 153, 255);
	static Color COLOR_WHITE = new Color(255, 255, 255);
	static Color COLOR_BLACK = new Color(0, 0, 0);
	static Color COLOR_CREAM = new Color(0xF5F2D0);

	static Color COLOR_NOT_COMPLETE = GuiAttributes.COLOR_LIGHT_RED;
	static Color COLOR_PROGRESS = Color.ORANGE;
	static Color COLOR_COMPLETE = GuiAttributes.COLOR_LIGHT_GREEN;
	
	static int FONT_SIZE_TASK_HEADER = 60;
	static int FONT_SIZE_TASK_NAME = 20;
	static int FONT_SIZE_STEP_DESC = 13;
}

class GuiUtils {

//	private static final String TextFont = "Comic Sans MS";

	public static ImageIcon getScaledIcon(ImageIcon icon, int width, int height) {

		Image image = icon.getImage();
		Image scaled = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);

		return new ImageIcon(scaled);
	}

	public static Font getFont(int size) {
//		return new Font(GuiUtils.TextFont, Font.BOLD, size);
		return new FontOne(size);
	}
	
	
	public static JScrollPane createScrollPane(JComponent component, boolean vbar, boolean hbar) {
		// create a scroller for the main panel(tasks)
		JScrollPane scroll = new JScrollPane(component, 
				(vbar)?JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED:JScrollPane.VERTICAL_SCROLLBAR_NEVER,
				(hbar)?JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED:JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.getVerticalScrollBar().setBackground(GuiAttributes.COLOR_CREAM);
		scroll.getHorizontalScrollBar().setBackground(GuiAttributes.COLOR_CREAM);
		
		scroll.setBackground(component.getBackground());
		scroll.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

		// to set the foreground colour we need to use this
		scroll.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
			@Override
			protected void configureScrollBarColors() {
				this.thumbColor = GuiAttributes.COLOR_LIGHT_BLUE;
			}
		});
		
		// resize bar
		float wscale = 0.8f;
		scroll.getVerticalScrollBar().setPreferredSize(new Dimension(
		        (int) ((float)scroll.getVerticalScrollBar().getPreferredSize()
		                .getWidth() * wscale),
		        (int) scroll.getVerticalScrollBar().getPreferredSize().getHeight()
		));
			
		
		scroll.getHorizontalScrollBar().setUI(new BasicScrollBarUI() {
				@Override
				protected void configureScrollBarColors() {
					this.thumbColor = GuiAttributes.COLOR_LIGHT_BLUE;
				}
		});
		
		scroll.getHorizontalScrollBar().setPreferredSize(new Dimension(
		        (int) ((float)scroll.getHorizontalScrollBar().getPreferredSize()
		                .getWidth() * wscale),
		        (int) scroll.getHorizontalScrollBar().getPreferredSize().getHeight()
		));
		
		return scroll;
	}
}

// Step class with description and colour
class Step extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextArea description;

	public Step(String name) {
		// set a border layout
		setLayout(new BorderLayout());
		setBackground(GuiAttributes.COLOR_NOT_COMPLETE);
		setForeground(Color.WHITE);
		setBorder(BorderFactory.createLineBorder(GuiAttributes.COLOR_BLACK, 1));
		
		// create and add the description to the centre
		description = new JTextArea(name, 4, 10);
		description.setText(name);		

		// a tool tip for larger text
		description.setToolTipText(name);
		
		description.setBackground(GuiAttributes.COLOR_NOT_COMPLETE);
		description.setEditable (false);
		description.setLineWrap(true);
		description.setWrapStyleWord (true);
		description.setFont(GuiUtils.getFont(GuiAttributes.FONT_SIZE_STEP_DESC));
		description.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		description.setForeground(GuiAttributes.COLOR_WHITE);
	
		add(GuiUtils.createScrollPane(description, true, true), BorderLayout.CENTER);
	}

	// change the background
	void setComplete(boolean complete) {
		if (complete) {
			description.setBackground(GuiAttributes.COLOR_COMPLETE);
		} else {
			description.setBackground(GuiAttributes.COLOR_PROGRESS);
		}
	}
}

// Task panel class that holds all the steps
class TaskPanel extends JPanel {
	private TaskSquare mTaskSquare;
	private ArrayList<Step> mSteps = new ArrayList<Step>();

	// hold all the individual step components
	private JPanel stepPanel;

	public TaskPanel(TaskSquare taskSquare) {

		// copy the object
		mTaskSquare = taskSquare;

		setLayout(new BorderLayout());

		// enable border lines
		setBackground(GuiAttributes.COLOR_WHITE);
		setBorder(BorderFactory.createLineBorder(GuiAttributes.COLOR_BLACK, 1));

		// loop the steps in the TaskSquare and store them locally
		for (String step : mTaskSquare.getSteps()) {
			Step s = new Step(step);
			mSteps.add(s);
		}

		// create the steps gui components from our local array list of steps
		// and add them to the step panel
		ImageIcon arrow = new ImageIcon("images/step_arrow.png");
		arrow = GuiUtils.getScaledIcon(arrow, 25, 25);

		int arrowCount = mSteps.size() - 1;

		stepPanel = new JPanel();
		stepPanel.setBackground(GuiAttributes.COLOR_WHITE);
		stepPanel.setLayout(new SpringLayout());

		for (Step step : mSteps) {

			stepPanel.add(step);

			// add an arrow, but not after last step
			if (arrowCount-- != 0) {
				JLabel label = new JLabel(arrow);
				stepPanel.add(label);
			}
		}

		// set the number of columns in the step panel
		arrowCount = mSteps.size() - 1;
		if (mSteps.size() > 0) {
			SpringUtilities.makeGrid(stepPanel, 1, mSteps.size() + arrowCount, 5, 5, 5, 5);
		}

		// add the step panel to the centre of the task panel
		add(stepPanel, BorderLayout.CENTER);

		// add a task name to the top (NORTH) of the task panel
		Color daColour = taskSquare.getDevelopmentArea().getColour();
		String daRGBString = "rgb(" + daColour.getRed() + ", " + daColour.getGreen() + ", " + daColour.getBlue() + ")";
		
		JLabel taskName = new JLabel("<html><p><span style='color: " + daRGBString + "'>" + taskSquare.getName() + "</span></p></html>");

		// a tool tip for larger text
		taskName.setToolTipText(taskSquare.getName());
		taskName.setFont(GuiUtils.getFont(GuiAttributes.FONT_SIZE_TASK_NAME));
		taskName.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		taskName.setHorizontalAlignment(SwingConstants.CENTER);
		taskName.setForeground(GuiAttributes.COLOR_WHITE);

		add(taskName, BorderLayout.NORTH);

		// check if we need to change color of tasks and steps
		checkComplete();

	}

	// check which of the individual steps are complete and change colour
	private void checkComplete() {
		int cuurrentStep = mTaskSquare.getCurrentStepNumber();

		// set the steps to complete if they are below the current completed step
		for (int i = 1; i <= mSteps.size(); i++) {
			if (i <= cuurrentStep) {
				Step s = mSteps.get(i - 1);
				s.setComplete(true);
			}
		}

		// set the whole task panel to complete if needed (i.e. if all the steps are complete).
		// if we are haven't started any steps we are not complete and if we have
		// started we are in progress
		if (mTaskSquare.isComplete()) {
			setBackground(GuiAttributes.COLOR_COMPLETE);
		} else {
			if(cuurrentStep == 0) {
				setBackground(GuiAttributes.COLOR_NOT_COMPLETE);
			}
			else if(cuurrentStep <= mSteps.size()) {
				setBackground(GuiAttributes.COLOR_PROGRESS);
			}
		}
	}
}

class HeaderPanel extends JPanel {

	JPanel namePanel = new JPanel();
	JPanel portraitPanel = new JPanel();
	
	public HeaderPanel(String name, ImageIcon portrait, Color color) {
	
		setLayout(new BorderLayout());
		setBackground(color);
		
		namePanel.setBackground(color);
		portraitPanel.setBackground(color);

		// a tool tip for larger text
		setToolTipText("Tasks: " + name);
		
		// make sure we can handle a huge name by using html tag
		// (automatically inserts a <BR> to wrap
		if(name.length() > 12) name = name.substring(0, 11);
		
		JLabel txt = new JLabel("<html><p>" + name + "'s " + "Tasks</p></html>", JLabel.CENTER);
		txt.setFont(GuiUtils.getFont(GuiAttributes.FONT_SIZE_TASK_HEADER));
		txt.setForeground(GuiAttributes.COLOR_WHITE);

		// add name to the panel
		namePanel.add(txt, BorderLayout.CENTER);
		namePanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 5, 5));

		// resize
		portrait = GuiUtils.getScaledIcon(portrait, 150, 150);

		// create a label and add the image
		JLabel img = new JLabel(portrait);
		img.setBorder(BorderFactory.createLineBorder(GuiAttributes.COLOR_WHITE, 4));

		// finally add the image
		portraitPanel.add(img, BorderLayout.CENTER);
		
		//add panels
		add(namePanel, BorderLayout.WEST);
		add(portraitPanel, BorderLayout.EAST);

		// thin border
		setBorder(BorderFactory.createLineBorder(GuiAttributes.COLOR_BLACK, 1));
	}
}

// Frame based class to show players tasks and their steps.
public class TaskMenu {

	
	// top level dialog
	private JDialog dialog;

	// main panel for holding the task panels
	private HeaderPanel header;

	// main panel for holding the task panels
	private JPanel main;

	// store for the task panels (gui components)
	private ArrayList<TaskPanel> mTasks = new ArrayList<TaskPanel>();

	/**
	 * Create the application.
	 */

	public TaskMenu(Player player) {
		initialize(player.getName(), player.getPortrait(), player.getColour(), player.getTasks());
	}

	/**
	 * Initialise the contents of the frame.
	 */
	private void initialize(String name, ImageIcon portrait, Color color, ArrayList<TaskSquare> taskSquares) {
		
		// create frame and set the title, size etc
		dialog = new JDialog();
		dialog.setTitle(name + "'s Tasks");
		dialog.setIconImage(new ImageIcon("images/govanIcon.png").getImage());

		// set a frame size and don't allow to resize
		dialog.setMinimumSize(new Dimension(1000, 500));
		dialog.setMaximumSize(new Dimension(1000, 500));
		dialog.setLocationRelativeTo(null);		// make frame appear in centre of screen
		dialog.setResizable(false);
		dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		dialog.setBackground(GuiAttributes.COLOR_WHITE);

		// the header panel contains Text and Image
		header = new HeaderPanel(name, portrait, color);

		// the main panel contains the task panels in rows (mTasks.size())
		main = new JPanel(new SpringLayout());
		main.setBackground(GuiAttributes.COLOR_CREAM);
		main.setBorder(BorderFactory.createLineBorder(GuiAttributes.COLOR_BLACK, 1));

		// create an array list of task panels (which contain the steps)
		for (TaskSquare taskSquare : taskSquares) {
			TaskPanel t = new TaskPanel(taskSquare);
			mTasks.add(t);
		}

		// add all the task panels to the main panel
		for (TaskPanel task : mTasks) {
			main.add(task);
		}

		// set the grid for the task rows
		if (mTasks.size() > 0) {
			SpringUtilities.makeGrid(main, mTasks.size(), 1, 5, 5, 5, 50);
		} else {

			// if we have no tasks just show a label
			JLabel none = new JLabel("You have none!", JLabel.CENTER);
			none.setFont(GuiUtils.getFont(GuiAttributes.FONT_SIZE_TASK_HEADER));
			main.add(none);

			SpringUtilities.makeGrid(main, 1, 1, 250, 100, 50, 50);
		}

		// container is a page box layout of header and the main panel (inside the
		// scroll)
		JPanel container = new JPanel();
		container.setLayout(new BorderLayout());
		container.setBackground(GuiAttributes.COLOR_WHITE);
		container.add(header, BorderLayout.NORTH);

		// add scroller to the container
		container.add(GuiUtils.createScrollPane(main, true, false), BorderLayout.CENTER);

		// add the main panel
		dialog.getContentPane().add(container);
	}

	public void show() {
		dialog.setVisible(true);
		dialog.setAlwaysOnTop(true);
		dialog.toFront();
	}
}
