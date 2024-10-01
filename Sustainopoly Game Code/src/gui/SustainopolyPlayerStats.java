package gui;

import java.awt.Component;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import sustainopoly.Player;
import sustainopoly.Role;
import sustainopoly.SustainopolyGame;
import sustainopoly.TaskSquare;

class StatsCellRenderer extends DefaultTableCellRenderer {
	private static final long serialVersionUID = 1L;
	private Player [] players;
	
	public StatsCellRenderer(Player [] players) {
		this.players = players.clone();
		
		setHorizontalAlignment(SwingConstants.CENTER);
	}
	
	@Override
    public Component getTableCellRendererComponent(JTable table,
                                                   Object value,
                                                   boolean isSelected,
                                                   boolean hasFocus,
                                                   int row,
                                                   int column) {

      final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

      //set cell background based on player colour
      setBackground(players[row].getColour());
      setForeground(Color.WHITE);
      
      //tasks, left aligned
      if(column == 3) {
    	  setHorizontalAlignment(SwingConstants.LEFT);    	  
      } else {
  		setHorizontalAlignment(SwingConstants.CENTER);
      }
      
      return c;
    }
};

public class SustainopolyPlayerStats extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JScrollPane scroll;
	
	//player information
	private Player [] players = new Player[SustainopolyGame.getInstance().getNumPlayers()];
	
	// Column Headers for the table
	private final String[] columns = new String[] {
		"Portrait", "Player", "Role", "Tasks", "Expertise", "Money", "Alliances", "<html><center>Square<br>Timeout</center></html>"
	};
	
	// Column class types
    @SuppressWarnings("rawtypes")
	private final Class[] columnClass = new Class[] {
         ImageIcon.class, String.class, String.class, String.class, Integer.class, Integer.class, Integer.class, Integer.class
    };
    
    // Table data
    private Object[][] data = new Object[SustainopolyGame.getInstance().getNumPlayers()][columnClass.length];
	
	public SustainopolyPlayerStats() {
		
		setLayout(new BorderLayout());
		
		//set default, override if needed
		setBackground(Color.WHITE);
		
		//create an array of players for ease of use
		for(int p = 0; p < SustainopolyGame.getInstance().getNumPlayers(); p++) {
			players[p] = SustainopolyGame.getInstance().getPlayer(p);
		}

        //actual data for the table in a 2d array
        for(int p = 0; p < SustainopolyGame.getInstance().getNumPlayers(); p++) {
        	data[p][0] = GuiUtils.getScaledIcon(players[p].getPortrait(), 120, 120);
        	data[p][1] = getName(players[p].getName());
        	data[p][2] = getRole(players[p].getRole());
        	data[p][3] = getTasks(players[p].getTasks());
        	data[p][4] = players[p].getExpertise();
        	data[p][5] = "$" + players[p].getMoney();
        	data[p][6] = SustainopolyGame.getInstance().getPlayerAlliances(players[p]).size();
        	data[p][7] = players[p].getSquareTimeout();
		}
        
        //create table model with data
        DefaultTableModel model = new DefaultTableModel(data, columns) {
            private static final long serialVersionUID = 1L;
			@Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
            @Override
            public Class<?> getColumnClass(int columnIndex)
            {
                return columnClass[columnIndex];
            }
        };
		
        //create table and add the model
        table = new JTable(model);
        table.setFillsViewportHeight(true);
        table.setShowGrid(false);
        
        //set height and width
        table.setRowHeight(120);
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(120);  //image
        columnModel.getColumn(1).setPreferredWidth(120);  //player
        columnModel.getColumn(2).setPreferredWidth(150);  //role
        columnModel.getColumn(3).setPreferredWidth(160);  //tasks       
        
        //set header font 
        JTableHeader th = table.getTableHeader(); 
        th.setPreferredSize(new Dimension(th.getPreferredSize().width, 40));
        th.setResizingAllowed(false);
        th.setReorderingAllowed(false);
        th.setFont(GuiUtils.getFont(18));
        
        //set font 
        table.setFont(GuiUtils.getFont(18));
         
        //create a customer renderer so we can change colour
        StatsCellRenderer renderer = new StatsCellRenderer(players);
        table.setDefaultRenderer(String.class, renderer);
        table.setDefaultRenderer(Integer.class, renderer);

        //add table to the scroll pane and add to panel
        scroll = GuiUtils.createScrollPane(table, true, false);
        add(scroll, BorderLayout.CENTER);
	}

	private String getName(String name) {
		if (name ==  null) {
			return new String("Unknown");
		}
		
		return "<html>" + name + "</html>";
	}
	
	private String getRole(Role role) {
		if (role ==  null) {
			return new String("Unknown");
		}
		
		return "<html>" + role.toString() + "</html>";
	}

	private String getTasks(ArrayList<TaskSquare> tasks) {
		if(tasks ==  null || tasks.size() ==  0) {
			return "";
		}

		StringBuffer sb = new StringBuffer();
		String complete = new String("<font face=\"Dingbats\" color=\"green\">&#10004;</font>");
		String incomplete = new String("<font face=\"Dingbats\" color=\"red\">&#10008;</font>");
		
		sb.append("<html>");
		for(TaskSquare task: tasks) {			
			sb.append("&nbsp;");
			sb.append(task.isComplete()?complete:incomplete);
			sb.append(" " + task.getName());
			sb.append("<br>");
		}
		sb.append("</html>");

		return sb.toString();
	}
	
	@SuppressWarnings("unused")
	private int getTaskCount(ArrayList<TaskSquare> tasks) {
		if(tasks ==  null || tasks.size() ==  0) {
			return 0;
		}
		
		return tasks.size();
	}
	
    public void setBackground(Color bg) {
    	super.setBackground(bg);
    	if(scroll != null) {
    		scroll.setBackground(bg);
    	}
    }
}
