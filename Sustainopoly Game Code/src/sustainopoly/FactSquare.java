package sustainopoly;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class FactSquare extends Square {
	private ArrayList<Fact> facts = new ArrayList<>();

	// inner class to create Fact objects made up of the String description of the fact plus an associated QR code
	class Fact {
		private String factDescription;
		private ImageIcon factQR;
		
		public Fact(String factDescription, String factQRImagePath) {
			this.factDescription = factDescription;
			this.factQR = new ImageIcon(factQRImagePath);
		}
		
		public String getFactDescription() {
			return this.factDescription;
		}
		
		public ImageIcon getFactQR() {
			return this.factQR;
		}
	}
	
	/**
	 * Creates a Fact square with name "Fact"
	 */
	public FactSquare(String name) {
		setName(name);
		
		facts.addAll(Arrays.asList(
				new Fact("The Moogety Food Hub located on Elderpark Street run free \n"
				+ "six-week cookery courses, cookery demos and community meals.", "images/QR/moogety_food_hub_qr.png"),
				
				new Fact("Kinning Park Complex offers weekly community meals to support local \n"
				+ "asylum seekers and refugees. It is a cornerstone of the community!", "images/QR/kinning_park_complex_qr.png"),
				
				new Fact("Govan residents currently have to travel to Glasgow city centre to purchase \n"
				+ "Halal meat, as there are no local providers. How do we fix this problem?", "images/QR/govan_community_project_food_qr.png"),
				
				new Fact("The Dig In social enterprise in Govan is an affordable greengrocer which works \n"
				+ "with the community to promote healthy eating and cooking.", "images/QR/dig_in_social_enterprise_qr.png"),
				
				new Fact("60% of people in Govan are overweight or obese, a figure which must be reduced \n"
				+ "with access to healthier food options. Your health is your wealth!", "images/QR/obesity_stats_qr.png"),
				
				new Fact("An estimated 6.5 million people in the UK are in fuel poverty, significantly up from previous years.", 
						"images/QR/fuel_poverty_stats_qr.png"),
				
				new Fact("The cost of living has been increasing across the UK since early 2021. \n"
				+ "As of July 2022, inflation has hit a 40 year high.", "images/QR/inflation_stats_qr.png"),
				
				new Fact("Seasonal affective disorder is common in Govan but rarely talked about. \n "
				+ "Caused by a lack of natural sunlight, it is thought to affect the \n"
				+ "production of the hormones serotonin and melatonin, which can lead to \n"
				+ "lethargy, poor diet and symptoms of depression.", "images/QR/seasonal_affective_disorder_qr.png")	
				));
	}

	/**
	 * Gets a fact from the facts ArrayList, displays the fact to the Player, and
	 * increments the Player's expertise by 5
	 */
	public void playAction() {
		//Get random fact from facts ArrayList
		Fact fact = getRandomFact();
		
		// increment expertise by 5
		Player currentPlayer = SustainopolyGame.getInstance().getCurrentPlayer();
		currentPlayer.setExpertise(currentPlayer.getExpertise() + 5);
		
		// code to display fact on GUI
		JOptionPane.showMessageDialog(null, fact.getFactDescription(), "Learn Fact", JOptionPane.INFORMATION_MESSAGE,
				fact.getFactQR());
	}
	
	private Fact getRandomFact() {
		return facts.get(new Random().nextInt(facts.size()));
	}
}
