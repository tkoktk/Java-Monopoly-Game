package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import sustainopoly.DevelopmentArea;
import sustainopoly.SustainopolyGame;
import sustainopoly.TaskSquare;

public class DevAreaButton extends JButton implements MouseListener{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DevelopmentArea devArea;

	public DevAreaButton(DevelopmentArea da) {
		
		super(da.toString().toUpperCase());
		devArea = da;
		setHorizontalAlignment(SwingConstants.CENTER);
		setFont(new FontOne(17));
	    setBorder(new LineBorder(Color.BLACK, 5));
        setAlignmentX(Component.CENTER_ALIGNMENT);
        setPreferredSize(new Dimension(400, 50));
        addMouseListener(this);
        setFocusable(false);
        setEnabled(true);
        
        
		if(da.isManagedByAlliance()) {
			setEnabled(false);
			setToolTipText("An alliance has already been formed on this development area.");
		} else if(!SustainopolyGame.getInstance().getCurrentPlayer().getDevelopmentAreas().contains(devArea)) {
			setEnabled(false);
			setToolTipText("You do not manage a task in this development area.");
		} else if(sameOwner(da)) {
			setEnabled(false);
			setToolTipText("Unable to form alliance as you manage all tasks in this development area");
		} else {
			TaskSquare[] incrementInfTasks = SustainopolyGame.getInstance().getGameBoard().getTasksInDevelopmentArea(devArea);
			for (TaskSquare task : incrementInfTasks) {
				if(task.getOwner() == null) {
					setEnabled(false);
					setToolTipText("All of the tasks in the developement area must be managed to form an alliance.");
					break;
				}
			}

		}
	}
	
	/**
	 * Checks if all the tasks in the provided DA are managed by the same Player, returning true if so and false if not
	 * @param da DevelopmentArea for whose tasks to check if owner is the same
	 * @return Boolean value- true if all tasks have some owner, false if not
	 */
	private boolean sameOwner(DevelopmentArea da) {
		TaskSquare[] tasks = SustainopolyGame.getInstance().getGameBoard().getTasksInDevelopmentArea(da);
		
		int tasksOwned = 0;
		for(TaskSquare task : tasks) {
			if(task.getOwner() == SustainopolyGame.getInstance().getCurrentPlayer()) {
				tasksOwned++;
			}
		}
		
		// will return true if tasksOwned is same as length of provided tasks, false if not
		return tasksOwned == tasks.length;
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

	@Override
	public void mouseEntered(MouseEvent e) {
    	if (isEnabled()) {
    		setBackground(devArea.getColour().brighter());
    		setForeground(Color.BLACK);
    	}
	}

	@Override
	public void mouseExited(MouseEvent e) {
    	if (isEnabled()) {
    		setBackground(devArea.getColour());
    		setForeground(Color.BLACK);
    	}
	}
	
    @Override
    public void setEnabled(boolean b) {
    	super.setEnabled(b);
    	if (b) {
    		setForeground(Color.BLACK);
    		setBackground(devArea.getColour());
    	} else {
    		setBackground(devArea.getColour().darker());
    		setForeground(Color.GRAY);
    	}
    }
}


