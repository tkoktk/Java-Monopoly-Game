package sustainopoly;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class CommunityBettermentSquare extends Square {
	private SustainopolyGame game = SustainopolyGame.getInstance();		// SustainopolyGame instance, just to save having to write it each time
	private HashMap<Role, ArrayList<Effect>> roleEffects = new HashMap<>();		// HashMap which maps Role to ArrayList<Effect> for that role

	/**
	 * Creates a Community Betterment square with name "Community Betterment"
	 */
	public CommunityBettermentSquare() {

		setName("Comm. Betterment");

		// will temporarily hold the effects for each role- is cleared with each new role
		ArrayList<Effect> effects = new ArrayList<>();
		
		// map each role-unique ArrayList<Effect> to the role using the roleEffects HashMap
		// ----- ASYLUM SEEKER -----
		effects.add(new Effect(
				"The Government Community Project recieved a large amount of \n"
						+ "funding this month, and has decided to give its excess money \n"
						+ "to local asylum seekers so that they can buy clothes and food.\n"
						+ "You have recieved $400 as a result.",
				() -> game.getCurrentPlayer().setMoney(game.getCurrentPlayer().getMoney() + 400)));
		effects.add(new Effect(
				"You have recieved some food vouchers from the government \n"
						+ "to help alleviate food price inflation. \n"
						+ "This makes life easier and gives you more time to help \n"
						+ "local community. Therefore, you can complete your next \n" + "task step for free.",
				() -> game.getCurrentPlayer().setNextStepFree(true)));
		effects.add(new Effect("You attended a digital skills workshop that was held by \n"
				+ "Govan Community Project. There, you learned about using \n"
				+ "new skills in Zoom and Microsoft Word. Your expertise has increased by 20. \n"
				+ "You're able to better help others with digital skills, \n"
				+ "so your next task contribution will be free.", 
				() -> {
					game.getCurrentPlayer().setExpertise(game.getCurrentPlayer().getExpertise() + 20);
					game.getCurrentPlayer().setNextContributionFree(true);
				}));

		roleEffects.put(Role.ASYLUM_SEEKER, new ArrayList<Effect>(effects));
		
		// ----- GOVAN LOCAL -----
		effects.clear();
		effects.add(new Effect("You attended a digital skills workshop that was held by \n"
				+ "Govan Community Project. There, you learned about using \n"
				+ "new skills in Zoom and Microsoft Word. Your expertise has increased by 20. \n" 
				+ "You're able to better help others with digital skills, \n"
				+ "so your next task contribution will be free.", 
				() -> {
					game.getCurrentPlayer().setExpertise(game.getCurrentPlayer().getExpertise() + 20);
					game.getCurrentPlayer().setNextContributionFree(true);
				}));
		effects.add(new Effect("Joined the Glasgow's Community Food Growers' Forum \n"
				+ "where you learned to grow your own food locally to save money. \n"
				+ "Your expertise has increased by 10, and your money has increased by 50", () -> {
					game.getCurrentPlayer().setExpertise(game.getCurrentPlayer().getExpertise() + 10);
					game.getCurrentPlayer().setMoney(game.getCurrentPlayer().getMoney() + 50);
				}));
		effects.add(new Effect("Strike organised by your Glasgow Trades Council was successful!\n" + "Next Step Free",
				() -> game.getCurrentPlayer().setNextStepFree(true)));

		roleEffects.put(Role.GOVAN_LOCAL, new ArrayList<Effect>(effects));
		
		// ----- VOLUNTEER -----
		effects.clear();
		effects.add(new Effect("You volunteer to teach digital skills locally,\n"
				+ "resulting in your volunteering experience to increase by 20.\n"
				+ "You're able to better help others, so your next task contribution will be free.", 
				() -> {
					game.getCurrentPlayer().setExpertise(game.getCurrentPlayer().getExpertise() + 20);
					game.getCurrentPlayer().setNextContributionFree(true);
				}));
		effects.add(new Effect("You Volunteer for the Govan Community Cleanup Hub to clean up the streets, \n"
				+ "resulting in your volunteering experience to increase by 25", 
				() -> game.getCurrentPlayer().setExpertise(game.getCurrentPlayer().getExpertise() + 25)));
		effects.add(new Effect(
				"You Volunteer to help with administrative tasks at the Govan Pantry,\n"
						+ "to free up staff time and resources. \nNext task step free, Experience increases by 15",
				() -> game.getCurrentPlayer().setNextStepFree(true)));

		roleEffects.put(Role.VOLUNTEER, new ArrayList<Effect>(effects));
		
		// ----- COMMUNITY LEADER -----
		effects.clear();
		effects.add(new Effect(
				"You work with Govan High School to establish apprenticeship programs that provide \n"
						+ "students with hands-on experience in the Food Sector.\n" + "You have gained 30 expertise.",
				() -> game.getCurrentPlayer().setExpertise(game.getCurrentPlayer().getExpertise() + 30)));
		effects.add(new Effect(
				"You develop a program to incentivize large corporations to partner with local businesses,\n"
						+ " thereby encouraging investment in the community.\n"
						+ "You have gained $750 and 30 expertise.",
				() -> {
					game.getCurrentPlayer().setMoney(game.getCurrentPlayer().getMoney() + 750);
					game.getCurrentPlayer().setExpertise(game.getCurrentPlayer().getExpertise() + 30);
					}));
		effects.add(new Effect(
				"Establish a program to support and promote local food production, such as urban gardens\n"
						+ "to encourage sustainable practices and promote healthy eating habits.\n"
						+ "Next step and next task contribution free.",
				() -> {
					game.getCurrentPlayer().setNextStepFree(true);
					game.getCurrentPlayer().setNextContributionFree(true);
				}));
		
		roleEffects.put(Role.COMMUNITY_LEADER, new ArrayList<Effect>(effects));
		
		// ----- COUNCILLOR -----
		effects.clear();
		effects.add(new Effect(
				"Partner with local organizations and community groups to host fundraising events and secure donations\n"
						+ "from corporate sponsors and philanthropic organizations.\n" + "Money increases by $1000",
				() -> game.getCurrentPlayer().setMoney(game.getCurrentPlayer().getMoney() + 1000)));
		effects.add(new Effect(
				"Advocate for the creation of an adult education center that offers classes and training in a variety of subjects,\n"
						+ "including business management,/n" + "financial literacy, and digital skills.\n"
						+ "Experience Increases by 20",
				() -> game.getCurrentPlayer().setExpertise(game.getCurrentPlayer().getExpertise() + 20)));
		effects.add(new Effect(
				"Encourage the development of community-based recycling programs and waste reduction initiatives\n"
						+ "that promote sustainability and reduce waste in the area.\n"
						+ "Next step and next task contribution free",
				() -> {
					game.getCurrentPlayer().setNextStepFree(true);
					game.getCurrentPlayer().setNextContributionFree(true);
				}));

		roleEffects.put(Role.COUNCILLOR, new ArrayList<Effect>(effects));
		
		// ----- ORGANISATION -----
		effects.clear();
		effects.add(new Effect("Create a community-based transportation program that provides affordable,\n"
				+ "efficient, and sustainable transportation options to residents in the area.\n"
				+ "Expertise Increases by 20 ", 
				() -> game.getCurrentPlayer().setExpertise(game.getCurrentPlayer().getExpertise() + 20)));
		effects.add(new Effect(
				"Develop a social impact investment fund that provides financial support to community-based \n"
						+ "projects such as the Govan Community Project that have a positive impact on the local economy.\n"
						+ "Money increases by $2000",
				() -> game.getCurrentPlayer().setMoney(game.getCurrentPlayer().getMoney() + 2000)));
		effects.add(new Effect(
				"Work with local utilities and energy providers to develop renewable energy\n"
						+ "projects that reduce carbon emissions and energy costs in the area.\n" 
						+ "Next step and next task contribution free",
				() -> {
					game.getCurrentPlayer().setNextStepFree(true);
					game.getCurrentPlayer().setNextContributionFree(true);
				}));
		
		roleEffects.put(Role.ORGANISATION, new ArrayList<Effect>(effects));
	}

	/**
	 * On the Community Betterment square, a positive help is provided to the player
	 * Different benefits are provided depending on the player's role
	 */
	public void playAction() {
		Role playerRole = game.getCurrentPlayer().getRole();
		
		ArrayList<Effect> effects = roleEffects.get(playerRole);

		// randomly pick an effect from the ArrayList and have it affect the player
		Effect randomEffect = getRandomEffect(effects);
		randomEffect.applyEffect();

		// code to display message on GUI
		JOptionPane.showMessageDialog(null, randomEffect.getMessage(), "Community Betterment",
				JOptionPane.INFORMATION_MESSAGE, new ImageIcon("images/community_betterment.png"));
	}
	
	private Effect getRandomEffect(ArrayList<Effect> effects) {
		return effects.get(new Random().nextInt(effects.size()));
	}
}
