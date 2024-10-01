package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

public class ScrollingTextPanel extends JPanel implements ActionListener {

	private Timer creditTimer = new Timer(40, this);
	private String text;
	private int textY = 680;
	
	public ScrollingTextPanel(boolean completedGame) {
		setBackground(new Color(125, 219, 67));
		setBorder(BorderFactory.createMatteBorder(0, 0, 0, 4, Color.BLACK));	// create border on right
		
		if (completedGame)
			text = "GAME COMPLETED\n\n"
					+ "By will of collaboration, empathy and charity,\n "
					+ "you successfully created the GoVan food truck scheme\n"
					+ "to freely feed the streets of Govan.\n\n"
					+ "You took on the roles of an impoverished Asylum Seeker, a\n"
					+ "gritty Govan Local, an empathetic Volunteer, a courageous\n"
					+ "Councillor, a passionate Community Leader and driven Organisations.\n\n"
					+ "Your hard work has diminished food insecurity for residents who\n"
					+ "lack the funds to travel to food banks, especially asylum seekers \n"
					+ "having to live on only $40.85 a week.\n"
					+ "You also eased the lives of many asylum seekers who cannot find\n"
					+ "culturally appropriate food at food banks without travelling far\n"
					+ "into the city center and paying even higher transport costs.\n"
					+ "You've decreased obesity rates and associated illnesses, improved\n"
					+ "livelihood of asylum seekers, and decreased their digital poverty.\n"
					+ "All of this has been done while increasing their expendable income,\n"
					+ "eliminating their need to choose between heating or eating, and\n"
					+ "without contributing to the greenhouse effect by using an electrically\n"
					+ "powered vehicle.\n\n"
					+ "The scheme still relies on many outside sources, and isn't able\n"
					+ "to provide all the food types and variety it would like, but that can\n"
					+ "change in the Road Ahead!\n\n"
					+ "2024\n\n"
					+ "Donations and grants increased, allowing you to purchase unused land\n"
					+ "where you start a farm. There you grow crops, animals to graze the\n"
					+ "land and offer to teach locals small-scale farming techniques to\n"
					+ "practice in Elder Park urban farms. This eliminated the reliance on\n"
					+ "outside sources for many of the ingredients, thereby improving supply\n"
					+ "chains. Additionally, the popularity of the scheme's YouTube channel\n"
					+ "and social following has generated massive interest, bringing more\n"
					+ "sponsorship money and volunteers to the scheme. With these additions,\n"
					+ "better software has been installed for stock management and planning.\n\n"
					+ "2025\n\n"
					+ "With the increased success of the charity, you gained enough to\n"
					+ "renovate the base of operations, which now doubles as a community\n"
					+ "centre for many social activities, subsidised by the GoVan scheme.\n"
					+ "Furthering your efforts to reduce digital poverty, an internet cafe is\n"
					+ "installed on the premises, with many digital devices and demonstrators\n"
					+ "on hand to help. Talks begin with nearby councils about expanding the\n"
					+ "scheme beyond Govan.\n\n"
					+ "2026\n\n"
					+ "The GoVan scheme is extended to feature food vans which cook fresh-food\n"
					+ "at highly subsidised prices, rather than distribute the usual non-\n"
					+ "perishable food items. This involved refurbishing second-hand food vans,\n"
					+ "upskilling workers from the community as chefs, and establishing new\n"
					+ "supply agreements with distributors. In this process, the scheme finds new\n"
					+ "suppliers for Halal and other culturally accesible food options.\n\n"
					+ "2027\n\n"
					+ "More Vans\n\n\n\n"
					+ "\"There is nothing greater than love invested in helping to feed the \n"
					+ "impoverished living in hunger.\"\n"
					+ "-Wayne Chirisa\n\n\n"
					+ "Thanks For Playing\n";
		else
			text = "GAME OVER\n\n"
					+ "By will of collaboration, empathy and charity,\n "
					+ "you successfully created the GoVan food truck scheme\n"
					+ "to freely feed the streets of Govan.\n\n"
					+ "You took on the roles of an impoverished Asylum Seeker, a\n"
					+ "gritty Govan Local, an empathetic Volunteer, a courageous\n"
					+ "Councillor, a passionate Community Leader and driven Organisations.\n\n"
					+ "Your hard work has diminished food insecurity for residents who\n"
					+ "lack the funds to travel to food banks, especially asylum seekers \n"
					+ "having to live on only $40.85 a week.\n"
					+ "You also eased the lives of many asylum seekers who cannot find\n"
					+ "culturally appropriate food at food banks without travelling far\n"
					+ "into the city center and paying even higher transport costs.\n"
					+ "You've decreased obesity rates and associated illnesses, improved\n"
					+ "livelihood of asylum seekers, and decreased their digital poverty.\n"
					+ "All of this has been done while increasing their expendable income,\n"
					+ "eliminating their need to choose between heating or eating, and\n"
					+ "without contributing to the greenhouse effect by using an electrically\n"
					+ "powered vehicle.\n\n"
					+ "The scheme still relies on many outside sources, and isn't able\n"
					+ "to provide all the food types and variety it would like, but that can\n"
					+ "change in the Road Ahead!\n\n"
					+ "2024\n\n"
					+ "Donations and grants increased, allowing you to purchase unused land\n"
					+ "where you start a farm. There you grow crops, animals to graze the\n"
					+ "land and offer to teach locals small-scale farming techniques to\n"
					+ "practice in Elder Park urban farms. This eliminated the reliance on\n"
					+ "outside sources for many of the ingredients, thereby improving supply\n"
					+ "chains. Additionally, the popularity of the scheme's YouTube channel\n"
					+ "and social following has generated massive interest, bringing more\n"
					+ "sponsorship money and volunteers to the scheme. With these additions,\n"
					+ "better software has been installed for stock management and planning.\n\n"
					+ "2025\n\n"
					+ "With the increased success of the charity, you gained enough to\n"
					+ "renovate the base of operations, which now doubles as a community\n"
					+ "centre for many social activities, subsidised by the GoVan scheme.\n"
					+ "Furthering your efforts to reduce digital poverty, an internet cafe is\n"
					+ "installed on the premises, with many digital devices and demonstrators\n"
					+ "on hand to help. Talks begin with nearby councils about expanding the\n"
					+ "scheme beyond Govan.\n\n"
					+ "2026\n\n"
					+ "The GoVan scheme is extended to feature food vans which cook fresh-food\n"
					+ "at highly subsidised prices, rather than distribute the usual non-\n"
					+ "perishable food items. This involved refurbishing second-hand food vans,\n"
					+ "upskilling workers from the community as chefs, and establishing new\n"
					+ "supply agreements with distributors. In this process, the scheme finds new\n"
					+ "suppliers for Halal and other culturally accesible food options.\n\n"
					+ "2027\n\n"
					+ "More Vans\n\n\n\n"
					+ "\"There is nothing greater than love invested in helping to feed the \n"
					+ "impoverished living in hunger.\"\n"
					+ "-Wayne Chirisa\n\n\n"
					+ "Thanks For Playing\n";
		
		creditTimer.start();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.setFont(new FontOne(22));
		g2d.setColor(Color.BLACK);
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		int y = textY;
		
		for (String line : text.split("\n")) {
			int stringLength = (int)g2d.getFontMetrics().getStringBounds(line,  g2d).getWidth();
			int x = getWidth() / 2 - stringLength / 2;
			g2d.drawString(line, x, y += 24);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		textY--;
		
		repaint();
	}
}
