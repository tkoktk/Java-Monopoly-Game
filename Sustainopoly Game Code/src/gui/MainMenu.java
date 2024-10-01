package gui;

		
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.Box;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.awt.FontFormatException;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

import java.awt.Toolkit;
import java.awt.Graphics;
						   

public class MainMenu extends JFrame {

	// This serial ID just gets rid of an error
	private static final long serialVersionUID = 1L;
	// JPanels
	private JPanel contentPane;
	private JPanel centrePanel;
	private JPanel titlePanel;
						 
	private JPanel buttonPanel;
	// JLabels
	private JLabel title;
						 
	// Buttons
	private SustainopolyButton quitButton;
	private SustainopolyButton startButton;
						 
	// Ints
	int centrePanelWidth;
	int fontSize;
	private int currentFrame = 1;
	private Timer animationTimer;
	private ArrayList<String> splashTexts;// Incorporate
	private ArrayList<Color> colours;
	private final Image[] frames = new Image[160];
	/**
	 * Create the frame.
	 */
	public MainMenu() {
        // get custom fonts
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Main Menu");
        setIconImage(new ImageIcon("images/govanIcon.png").getImage());

        contentPane = new JPanel() {
            // Override the paintComponent() method to draw the image
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (frames[currentFrame] != null) {
                    // Get the dimensions of the screen
                    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                    int screenWidth = (int) screenSize.getWidth();
                    int screenHeight = (int) screenSize.getHeight();

                    // Scale the image to fit the screen
                    Image scaledImage = frames[currentFrame].getScaledInstance(screenWidth, screenHeight, Image.SCALE_SMOOTH);

                    // Draw the scaled image at the top-left corner of the panel
                    g.drawImage(scaledImage, 0, 0, null);
                }
            }
        };

        // Remove Top margin
        contentPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        // Set the panel to be transparent
        contentPane.setOpaque(false);
        setContentPane(contentPane);
 
        // Load the animation frames
        for (int i = 1; i < frames.length; i++) {
            try {
                frames[i] = ImageIO.read(new File("images/newerAnim/anim" + i + ".png"));
            } catch (IOException e) {
                System.out.println("Error loading animation frames: " + e.getMessage());
            }
        }

        // Set up the animation timer
        animationTimer = new Timer(65, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentFrame == frames.length - 1) {
                    currentFrame = 1;
                } else {
                    currentFrame++;
                }
                repaint();
            }
        });
        animationTimer.setRepeats(true);
        animationTimer.start();
        // Set up centre Panel and get variable values associated with it
        setUpCentrePanel();

        // Get centre panel's width, to use in dynamically sizing title + van + buttons
        centrePanelWidth = (int) centrePanel.getPreferredSize().getWidth();
        // Font size of buttons will be centrePanelWidth / 32.
        // Eg for a 1920 x 1080 screen, the centre Panel will be 960 width
        // 960 / 32 = 30, font size 30
        // This will vary between screens but not to a point of significance
        fontSize = centrePanelWidth / 32;

        // set up top titlePanel
        setUpTitlePanel();
        // Set up bottom buttonPanel
        setUpButtonPanel();

        // Set the panel's preferred size to the screen size
        contentPane.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());

        pack();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);

       
    }
	/**
	 * This method sets up a Panel in the Centre of the Screen and sets its width by
	 * calculating the size of The user's device screen size. This Panel contains
	 * all of the main components of the Main Menu.
	 */
	public void setUpCentrePanel() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		// Get screensize for dynamic sizing
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		// Get a dimension that is half as wide as the screen for the content to be in
		Dimension preferredSize = new Dimension(screenWidth / 2, screenHeight);

		centrePanel = new JPanel();
		centrePanel.setOpaque(false);
		//centrePanel.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 5, Color.BLACK));

		// set GridBagLayout for overall controlsPanel- one column, three rows (for
		// playerPanel and buttonPanel)
		GridBagLayout gbc_centrePanel = new GridBagLayout();
		gbc_centrePanel.columnWeights = new double[] { 1.70 };
		gbc_centrePanel.rowWeights = new double[] { 0.2, 0.8, 0.2 };
		// gbc_centrePanel.fill = GridBagConstraints.CENTER;
		centrePanel.setLayout(gbc_centrePanel);

		centrePanel.setBackground(new Color(123, 94, 73));
		centrePanel.setPreferredSize(preferredSize);

		contentPane.add(centrePanel);
	}

	/**
	 * This method sets up the Title Panel that takes up the top third of the centre
	 * panel. The Title Panel is divided into two other Panels. 1: topPanel takes up
	 * 2/10 of the size of the titlePanel and contains a JLabel containing the
	 * Sustainopoly logo image. This image is resized dynamically depending on the
	 * size of the user's screen. 2: bottomPanel takes up 8/10 of the size of the
	 * titlePanel, containing a JLabel anchored at the top called splashLabel.
	 * SplashLabel's font is resized dynamically depending on the size of the user's
	 * screen. A timer is used to set the splashLabel's text to a random string in
	 * the String ArrayList "splashTexts", and a random colour in the Color
	 * ArrayList "colours".
	 */
	public void setUpTitlePanel() {

		titlePanel = new JPanel();
		titlePanel.setOpaque(false);
		// Divide the panel into two rows with a 3/4 : 1/4 height ratio
		GridBagLayout gbc_titlePanel = new GridBagLayout();
		gbc_titlePanel.columnWeights = new double[] { 1.0 };
		gbc_titlePanel.rowWeights = new double[] { 0.1, 0.9 };
		titlePanel.setLayout(gbc_titlePanel);

		// First row of titlePanel = topPanel containing title
		JPanel topPanel = new JPanel();
		topPanel.setOpaque(false);
		// topPanel.setBackground(Color.BLUE);

		// Title JLabel:
		// Title image (Sustainopoly logo)
		ImageIcon logo = new ImageIcon("images/Sustainopoly Home Screen Logo 3.png");

		// Set the image to be 0.8 of the centrePanel size whilst maintaining its aspect
		// ratio.
		// the centrePanel is 1/2 of the screen width, so this allows for dynamic
		// sizing.
		Image image = logo.getImage().getScaledInstance((int) (centrePanelWidth * 0.8), -1, Image.SCALE_SMOOTH);
		ImageIcon resizedLogo = new ImageIcon(image);
		// Apply values to title JLabel, centre and add border
		title = new JLabel(resizedLogo);
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		title.setAlignmentY(Component.CENTER_ALIGNMENT);
		title.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));

		// Add title to topPanel
		topPanel.add(title);

		GridBagConstraints topPanel_gbc = new GridBagConstraints();
		topPanel_gbc.gridx = 0;
		topPanel_gbc.gridy = 0;
		topPanel_gbc.weightx = 1.0;
		// topPanel_gbc.fill = GridBagConstraints.BOTH;
		titlePanel.add(topPanel, topPanel_gbc);

		// Create bottomPanel 1/4 the height of titlePanel for holding Splash Text
		JPanel bottomPanel = new JPanel();
		bottomPanel.setOpaque(false);
		// bottomPanel.setBackground(Color.WHITE);

		// Create a JLabel for the splash text
		JLabel splashLabel = new JLabel("Welcome to Sustainopoly!");
		splashLabel.setForeground(new Color(83, 48, 57));
		splashLabel.setAlignmentY(Component.CENTER_ALIGNMENT);
		splashLabel.setVerticalAlignment(JLabel.TOP);

		splashLabel.setFont(new FontOne(fontSize));
		// Populate splashTexts ArrayList with messages to be randomly displayed
		splashTexts = new ArrayList<String>();
		splashTexts.add("Don't be mean, be green!");
		splashTexts.add("Reduce, reuse, recycle!");
		splashTexts.add("Govan needs your help!");
		splashTexts.add("Teamwork solves any problem!");
		splashTexts.add("Let's save the world!");
		splashTexts.add("Don't forget to complete your task steps!");
		splashTexts.add("Time to halt Global Warming!");
		splashTexts.add("Contribute some resources!");
		splashTexts.add("Be a champion of your community!");
		splashTexts.add("Help those in need!");
		splashTexts.add("Nobody has ever become poor from giving!");
		splashTexts.add("Save Earth, plant trees!");
		splashTexts.add("Act now, save the world later!");
		splashTexts.add("Be the change you seek in the world!");
		// Populate colors ArrayList with colours that are randomly assigned
		colours = new ArrayList<>();
		colours.add(new Color(83, 48, 57));
		colours.add(new Color(56, 73, 90));
		colours.add(new Color(96, 55, 34));
		colours.add(new Color(91, 34, 52));
		colours.add(new Color(40, 77, 70));
		colours.add(new Color(57, 76, 52));
		colours.add(new Color(71, 49, 76));
		colours.add(new Color(85, 63, 44));
		colours.add(new Color(49, 50, 63));
		colours.add(new Color(56, 45, 53));
		
		// This timer goes every 4 seconds and changes the text field + colour
		// Of the splashText JLabel. Will add more features eg rotation soon.
		Timer splashTimer = new Timer(4000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Pick a random value from both ArrayLists and set their values to the
				// splashText JLabel
				Random random = new Random();
				int randomIndex = random.nextInt(splashTexts.size());
				// Set the text to the random message
				splashLabel.setText(splashTexts.get(randomIndex));
				Color randomColor = colours.get(random.nextInt(colours.size()));
				// Set the text color to the random color
				splashLabel.setForeground(randomColor);
			}
		});

		// Start the timer
		splashTimer.start();

		// Add splashLabel to bottomPanel
		bottomPanel.add(splashLabel);
		// Add bottomPanel to titlePanel
		GridBagConstraints bottomPanel_gbc = new GridBagConstraints();
		bottomPanel_gbc.gridx = 0;
		bottomPanel_gbc.gridy = 1;
		bottomPanel_gbc.fill = GridBagConstraints.BOTH;
		bottomPanel_gbc.anchor = GridBagConstraints.NORTH;

		titlePanel.add(bottomPanel, bottomPanel_gbc);

		// Now that topPanel and bottomPanel are added, add the complete topPanel to top
		// of centrePanel
		GridBagConstraints titlePanel_gbc = new GridBagConstraints();
		titlePanel_gbc.gridx = 0;
		titlePanel_gbc.gridy = 0;
		titlePanel_gbc.fill = GridBagConstraints.BOTH;
		centrePanel.add(titlePanel, titlePanel_gbc);
	}

	/**
	 * This method sets up buttonPanel, a JPanel taking up 1/3 of the centrePanel.
	 * buttonPanel contains two buttons, "startButton" and "quitButton", both of
	 * which have actionListeners added for their respective functionality. Buttons
	 * are arranged one on top of the other by using a box layout.
	 */
	public void setUpButtonPanel() {

		// Create buttonPanel
		buttonPanel = new JPanel();
		buttonPanel.setOpaque(false);
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

		// "Start Game" button
		startButton = new SustainopolyButton("START GAME");
		startButton.setFont(new FontOne(40));
		// Action Listener that navigates to EnrolPlayersDialog when clicked
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new EnrolPlayersDialog();
				dispose();
			}
		});

		// Create the "Quit" button
		quitButton = new SustainopolyButton("QUIT");
		quitButton.setFont(new FontOne(40));

		// "Quit" behaviour for the button
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {// When clicked on, close game

				int confirmed = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?",
						"Sustainopoly: Quit Game", JOptionPane.YES_NO_OPTION);
				if (confirmed == JOptionPane.YES_OPTION) {
					dispose();
				}
			}
		});
		
		JPanel lowerPanel = new JPanel();
		lowerPanel.setLayout(new BoxLayout(lowerPanel, BoxLayout.X_AXIS));
		lowerPanel.setOpaque(false);
  		
		// Create "How to Play" button
		SustainopolyButton helpBtn = new SustainopolyButton("ABOUT");
		helpBtn.setFont(new FontOne(40));
		
		helpBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new HelpDialog();
			}
		});
		
		lowerPanel.add(helpBtn);
		lowerPanel.add(Box.createRigidArea(new Dimension(20, 0)));
		lowerPanel.add(quitButton);

		// Add Start button
		// buttonPanel.add(Box.createVerticalGlue());
		buttonPanel.add(startButton);
		buttonPanel.add(Box.createVerticalStrut(7)); // add some vertical spacing between buttons
		
		buttonPanel.add(lowerPanel);
		
		buttonPanel.add(Box.createVerticalGlue()); // add vertical glue to center buttons vertically


		// Add buttonPanel to centrePanel
		GridBagConstraints buttonPanel_gbc = new GridBagConstraints();
		buttonPanel_gbc.gridx = 0;
		buttonPanel_gbc.gridy = 2;
		buttonPanel_gbc.fill = GridBagConstraints.VERTICAL;

		centrePanel.add(buttonPanel, buttonPanel_gbc);
	}

}
