package gui;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

import javax.swing.UIManager;

// can use this class eg quitButton.setFont(new FontOne(fontSize));
public class FontOne extends Font {

	private static final long serialVersionUID = 1L;
	
	private static String fontPath = "fonts/SustainopolyFont.otf";
	
	static {
		setOptionPaneFont();
	}

    public FontOne(int size) {
        super(getCustomFont(size));
    }
    
	/**
	 * This method loads in the custom font "Retro" so that it can be used in
	 * the splash text and title. If there is an error with reading in the Font from
	 * its file, an exception is caught and Arial font is returned instead, allowing
	 * the program to still run.
	 * 
	 * @param size: The specified size of the font.
	 * @return: Retro custom Font at its specified size
	 */
    private static Font getCustomFont(int size) {
        try {
            // Load in the font from the file and set its size
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File(fontPath)).deriveFont(Font.PLAIN, size);
            GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
            
            genv.registerFont(customFont);
            return customFont;
        } catch (IOException | FontFormatException e) {
            // If the font is not found, just use plain Arial font.
            e.printStackTrace();
            return new Font("Arial", Font.PLAIN, size);
        }
    }
    
    private static void setOptionPaneFont() {
        UIManager.put("OptionPane.messageFont", getCustomFont(17));
        UIManager.put("OptionPane.buttonFont", getCustomFont(14));
    }

}
