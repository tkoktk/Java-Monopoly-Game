package gui;

import sustainopoly.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.table.*;

public class AllianceTable extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private ArrayList<Alliance> alliances;
	private String[] columns = { "Dev. Areas", "Tasks", "Members", "Member Portraits" };
	private Player player;
	private JLabel title;
	private JTable table;
	//private JPanel portraitPanel;

	public AllianceTable() {
		player = SustainopolyGame.getInstance().getCurrentPlayer();
		/*
		 * Styling is all done within this constructor, most of it is because you
		 * override the JTable methods to be able to change the colours of the headers,
		 * rows etc
		 */

		table = new JTable(this) {
			private static final long serialVersionUID = 1L;

			// Disable editing so that people can't drag the columns around
			public boolean isCellEditable(int row, int column) {
				return false;
			}

			/*
			 * Overriding the JTable methods to set Font Colour + Size of Table Header
			 */
			@Override
			public JTableHeader getTableHeader() {
				// Table Header Font + Colour attributes set here
				JTableHeader header = super.getTableHeader();
				header.setFont(new FontOne(20));
				header.setForeground(Color.WHITE);
				header.setBackground(Color.BLACK);
				return header;
			}

			// Overriding the Table cell colours to change the colour of the rows to match
			// the Alliance's Development Area
			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				Component c = super.prepareRenderer(renderer, row, column);
				Alliance alliance = alliances.get(row);
				c.setBackground(alliance.getDevelopmentArea().getColour());

				// automatically resize the height of the rows to allow the text/portraits to
				// fit
				int preferredHeight = c.getPreferredSize().height;
				if (table.getRowHeight(row) < preferredHeight) {
					table.setRowHeight(row, preferredHeight);
				}
				return c;
			}
		};

		// Setting the default renderer is how we modify properties of cells outside of
		// the header
		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			private static final long serialVersionUID = 1L;

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {

				super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				if (value instanceof JPanel) {
		            // If the cell already contains a JPanel (created earlier), reuse it
		            return (JPanel) value;
				}
				
				setForeground(Color.WHITE);
			    
			    // Set horizontal alignment of text to center
			    setHorizontalAlignment(JLabel.CENTER);
				
				// Set font size of the content in rows outside the header
				setFont(new FontOne(15));
				return this;
			}
		});

		// Autoresizing the table columns, eg this will set column width.
		
		this.alliances = SustainopolyGame.getInstance().getPlayerAlliances(player);
		
		int largestAlliance = 0;
		for(Alliance alliance : alliances) {
			if(alliance.getMembers().size() > largestAlliance) {
				largestAlliance = alliance.getMembers().size() - 2;//-1 to remove count of current player
			}
		}
		// -1 because the player is in the alliance
		table.getColumnModel().getColumn(3).setPreferredWidth((largestAlliance) * 75);
		//Auto Resize all other columns
		table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		// Calculate the preferred size of the panel
		//Dimension preferredSize = portraitPanel.getPreferredSize();

		// Set the preferred width of the column
		//table.getColumnModel().getColumn(3).setPreferredWidth(preferredSize.width);
		
		// These two methods disable the ability to modify the table by clicking and
		// dragging
		table.getTableHeader().setResizingAllowed(false);
		table.getTableHeader().setReorderingAllowed(false);
		table.setBackground(player.getColour());
		table.setShowGrid(false);
		
		this.alliances = SustainopolyGame.getInstance().getPlayerAlliances(player);

		// Title JLabel (This is at the top of the Frame, above the table).
		title = new JLabel(player.getName() + "'s Alliances");
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setFont(new FontOne(30));
		title.setForeground(Color.WHITE);

		JScrollPane scrollPane = new JScrollPane(table);
		// scrollPane.setPreferredSize(new Dimension(600, 800));
		// Automatically set ScrollPane to be based on the table's size
		scrollPane.getViewport().setPreferredSize(table.getPreferredSize());
		// scrollPane.getViewport().setPreferredSize(new
		// Dimension(table.getPreferredSize().width, table.getRowHeight(0)));
	}

	public void display() {
		// Need to style
		JFrame frame = new JFrame();
		
		frame.setIconImage(new ImageIcon("images/govanIcon.png").getImage());
		frame.setTitle(player.getName() + "'s Alliances");
		frame.setSize(700, 400);
		frame.setLayout(new BorderLayout());
		frame.add(title, BorderLayout.NORTH);
		frame.add(new JScrollPane(table), BorderLayout.CENTER);
		//Make background colour = the player colour
		Container contentPane = frame.getContentPane();
		contentPane.setBackground(player.getColour());

		frame.setLocationRelativeTo(null); // make frame appear in centre of screen
		frame.setVisible(true);
	}

	@Override
	public int getRowCount() {
		// +5 to create empty rows for styling purposes
		return alliances.size();
	}

	@Override
	public int getColumnCount() {
		return columns.length;
	}

	/**
	 * This is how we get the values for each row for the first 3 columns
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// Each Alliance is a row of the table
		Alliance alliance = alliances.get(rowIndex);
		// Can get the members here to avoid code duplication
		ArrayList<Player> members = alliance.getMembers();

		switch (columnIndex) {
		// Development Area Name:
		case 0:
			return alliance.getDevelopmentArea().toString();

		// Names of the Tasks that make up that Development Area:
		case 1:
			// Simple code for getting all the Dev area names and adding them into the
			// column
			ArrayList<TaskSquare> tasks = alliance.getDevelopmentArea().getTasks();
			String taskMessage = "<html> ";
			for (TaskSquare task : tasks) {
				taskMessage += task.getName() + ", ";
			}
			return taskMessage.substring(0, taskMessage.length() - 2) + "</html>";
			

		// Names of the members of that alliance excluding the current player:
		case 2:
			String membersMessage = "<html> ";
			for (Player member : members) {
				if (member.getName() != player.getName()) {
					membersMessage += member.getName() + ", ";
				}
			}
			return membersMessage.substring(0, membersMessage.length() - 2) + "</html>";
		case 3:
		    JPanel portraitPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		    for (Player member : members) {
		        if (member.getName() != player.getName()) {
		            portraitPanel.add(new JLabel(member.getPortrait(75, 75)));
		        }
		    }

		    return portraitPanel;

		default:
			return null;
		}
	}

	@Override
	public String getColumnName(int column) {
		return columns[column];
	}
}
