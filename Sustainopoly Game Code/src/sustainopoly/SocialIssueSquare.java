package sustainopoly;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class SocialIssueSquare extends Square {
	private SustainopolyGame game = SustainopolyGame.getInstance();		// SustainopolyGame instance, just to save having to write it each time
	private HashMap<Role, ArrayList<Effect>> roleEffects = new HashMap<>();		// HashMap which maps Role to ArrayList<Effect> for that role
	
	/**
	 * Creates a Social Issue square with name "Social Issue" Creates a HashMap of
	 * different effects for each player depending on their role.
	 */
	public SocialIssueSquare() {
		setName("Social Issue");
		
		// will temporarily hold the effects for each role- is cleared with each new role
		ArrayList<Effect> effects = new ArrayList<>();

		// map each role-unique ArrayList<Effect> to the role using the roleEffects HashMap
		// ----- ASYLUM SEEKER -----
		effects.add(new Effect(
				"The poor, damp, poorly insulated quality of the allocated housing you are \n"
				+ "living in has resulted in you falling ill and falling behind on your commitments.\n"
				+ "Therefore, you will not be able to complete task steps for an additional 12 squares,\n"
				+ "and your next task contribution will cost double.",
				() -> {
					game.getCurrentPlayer().setSquareTimeout(game.getCurrentPlayer().getSquareTimeout() + 12);
					game.getCurrentPlayer().setNextContributionCostsDouble(true);
				}));
		effects.add(new Effect(
				"You went to the Govan Community Project food bank for your week's groceries,\n"
				+ " but the food had sadly ran out. You have to spend time finding food elsewhere, \n"
				+ "unable to complete task steps for an additional 14 squares.",
				() -> game.getCurrentPlayer().setSquareTimeout(game.getCurrentPlayer().getSquareTimeout() + 14)));
		effects.add(new Effect(
				"Digital Poverty is heavily impeding your ability to be productive in society. \n"
				+ "Therefore, you will not be able to complete task steps for an additional 10 squares,\n"
				+ "and your next task step will cost double.",
				() -> {
					game.getCurrentPlayer().setSquareTimeout(game.getCurrentPlayer().getSquareTimeout() + 10);
					game.getCurrentPlayer().setNextStepCostsDouble(true);
				}));
		effects.add(new Effect(
				"Bus Strikes have made it even harder for you to get around Govan. \n"
				+ "You had to book a Taxi to get to your next square in advance for \n"
				+ "extortionate prices, and have been deducted $40 as a result.",
				() -> game.getCurrentPlayer().setMoney(game.getCurrentPlayer().getMoney() - 40)));

		effects.add(new Effect("You travel to your energy contract provider to get \n"
				+ "your card topped up. Bus fees are expensive on your low income, \n"
				+ "you have been deducted $30 as a result, and your square\n"
				+ " timeout has been increased by 8", () -> {
					game.getCurrentPlayer().setMoney(game.getCurrentPlayer().getMoney() - 30);
					game.getCurrentPlayer().setSquareTimeout(game.getCurrentPlayer().getSquareTimeout() + 8);
				}));

		effects.add(new Effect(
				"There is no halal meat available in Govan. \n"
				+ "You had to travel to the city centre to get some, which is \n"
				+ "both costly in terms of time and money. Therefore, you have \n"
				+ "been deducted $40 and your square timeout has been increased by 15.",
				() -> {
					game.getCurrentPlayer().setMoney(game.getCurrentPlayer().getMoney() - 40);
					game.getCurrentPlayer().setSquareTimeout(game.getCurrentPlayer().getSquareTimeout() + 15);
				}));

		roleEffects.put(Role.ASYLUM_SEEKER, new ArrayList<Effect>(effects));

		// ----- GOVAN LOCAL -----
		effects.clear();
		effects.add(new Effect(
				"The large, endless waiting lists caused by the social care sector's \n"
			  + "staffing crisis result in you not being able to recieve the mental \n"
			  + "health care you need. \n"
			  + "Your mental state spirals, and so you can't complete task steps for\n"
			  + "an additional 15 squares.",
				() -> game.getCurrentPlayer().setSquareTimeout(game.getCurrentPlayer().getSquareTimeout() + 15)));

		effects.add(new Effect(
				"Intense rainfall for the past three days, coupled with inadequate\n"
				+ "drainage systems in Govan, has resulted in your local area \n"
				+ "essentially being paralysed for the next week. \n"
				+ "Your square timeout has been increased by 7, and your next task step will cost double\n"
				+ "with the labour shortages.",
				() -> {
					game.getCurrentPlayer().setSquareTimeout(game.getCurrentPlayer().getSquareTimeout() + 7);
					game.getCurrentPlayer().setNextStepCostsDouble(true);
				}));

		effects.add(new Effect(
				"Inflation continues to devastate the working class across the country, \n"
				+ "heavily damaging your purchasing power, and so you have been deducted $80,\n"
				+ "and your next task contribution will cost double.",
				() -> {
					game.getCurrentPlayer().setMoney(game.getCurrentPlayer().getMoney() - 80);
					game.getCurrentPlayer().setNextContributionCostsDouble(true);
				}));

		effects.add(new Effect(
				"As unemployment continues to be a huge issue in Govan, your wife loses her job. \n"
				+ "You now must financially support your entire family on one small income. \n"
				+ "$140 has been deducted.",
				() -> game.getCurrentPlayer().setMoney(game.getCurrentPlayer().getMoney() - 140)));

		effects.add(new Effect(
				"For the past 5 years, Gentrification has been affecting the area you grew up in \n"
				+ "and have lived in your whole life. Your rent price has just been increased, \n"
				+ "overwhelming you with stress and anxiety. You are charged $100 and your \n"
				+ "square timeout is increased by 7, as well as your next task step costing double.",
				() -> {
					game.getCurrentPlayer().setMoney(game.getCurrentPlayer().getMoney() - 100);
					game.getCurrentPlayer().setSquareTimeout(game.getCurrentPlayer().getSquareTimeout() + 7);
					game.getCurrentPlayer().setNextStepCostsDouble(true);
				}));

		roleEffects.put(Role.GOVAN_LOCAL, new ArrayList<Effect>(effects));

		// ----- VOLUNTEER -----
		effects.clear();
		effects.add(new Effect(
				"Inflation continues to devastate the country, heavily damaging \n"
				+ "your purchasing power, and so you have been deducted $80,\n"
				+ "and your next task contribution will cost double.",
				() -> {
					game.getCurrentPlayer().setMoney(game.getCurrentPlayer().getMoney() - 80);
					game.getCurrentPlayer().setNextContributionCostsDouble(true);
				}));


		effects.add(new Effect(
				"As unemployment continues to be a huge issue in Govan, your wife loses her job. \n"
				+ "You now must financially support your entire family on one small income. \n"
				+ "$140 has been deducted.",
				() -> game.getCurrentPlayer().setMoney(game.getCurrentPlayer().getMoney() - 140)));

		effects.add(new Effect(
				"You have been volunteering for the Govan Community Project for the past 4 months. \n"
				+ "However, the charity's funding has been dwindling and so its ability to create \n"
				+ "positive changes for the community has fallen. \n"
				+ "Your square timeout has been increased by 20, and your next task step will cost double.",
				() -> {
					game.getCurrentPlayer().setSquareTimeout(game.getCurrentPlayer().getSquareTimeout() + 20);
					game.getCurrentPlayer().setNextStepCostsDouble(true);
				}));

		effects.add(new Effect(
				"There has been a shortage of local charity volunteers in Govan, so you have had to \n"
				+ "overwork, resulting in burnout, and now you need to take a break. \n"
				+ "You will not be able to complete task steps for an additional 10 squares.",
				() -> game.getCurrentPlayer().setSquareTimeout(game.getCurrentPlayer().getSquareTimeout() + 10)));

		effects.add(new Effect(
				"For the past 5 years, Gentrification has been affecting the area you grew up in \n"
				+ "and have lived in your whole life. Your rent price has just been increased, \n"
				+ "overwhelming you with stress and anxiety. You are charged $100 and your \n"
				+ "square timeout is increased by 7, as well as your next task step costing double.",
				() -> {
					game.getCurrentPlayer().setMoney(game.getCurrentPlayer().getMoney() - 100);
					game.getCurrentPlayer().setSquareTimeout(game.getCurrentPlayer().getSquareTimeout() + 7);
					game.getCurrentPlayer().setNextStepCostsDouble(true);
				}));

		roleEffects.put(Role.VOLUNTEER, new ArrayList<Effect>(effects));

		// ----- COMMUNITY LEADER -----
		effects.clear();
		effects.add(new Effect(
				"Inflation continues to devastate the country, heavily damaging \n"
				+ "your purchasing power, and so you have been deducted $80,\n"
				+ "and your next task contribution will cost double.",
				() -> {
					game.getCurrentPlayer().setMoney(game.getCurrentPlayer().getMoney() - 80);
					game.getCurrentPlayer().setNextContributionCostsDouble(true);
				}));


		effects.add(new Effect(
				"You attempted to have the processes for Food Banks in Govan expanded \n"
				+ "to increase food availablity, but were unable to secure funding.\n"
				+ "Your square timeout has been increased by 8, and your next task step will cost double.",
				() -> {
					game.getCurrentPlayer().setSquareTimeout(game.getCurrentPlayer().getSquareTimeout() + 8);
					game.getCurrentPlayer().setNextStepCostsDouble(true);
				}));

		effects.add(new Effect(
				"Your Community Group has been struggling to gain volunteers for the past \n"
				+ "few weeks, and membership is dwindling. This has made it harder for your \n"
				+ "group to make positive reforms, and as a result you will not be able to \n"
				+ "complete task steps for an additional 15 squares.",
				() -> game.getCurrentPlayer().setSquareTimeout((game.getCurrentPlayer().getSquareTimeout() + 15))));

		effects.add(new Effect(
				"Your Community Group recieved a grant for a cultural exchange festival, \n"
				+ "but were unable to effectively organise it on time due to a shortage \n"
				+ "of volunteers. Therefore, the Government has withdrawn the payment. \n"
				+ "You have lost 200 money as a result, and your next task contribution will cost double.",
				() -> {
					game.getCurrentPlayer().setMoney(game.getCurrentPlayer().getMoney() - 200);
					game.getCurrentPlayer().setNextContributionCostsDouble(true);
				}));
		
		effects.add(new Effect(
				"For the past 5 years, Gentrification has been affecting the area you grew up in \n"
				+ "and have lived in your whole life. Your rent price has just been increased, \n"
				+ "overwhelming you with stress and anxiety. You are charged $100 and your \n"
				+ "square timeout is increased by 7, as well as your next task step costing double.",
				() -> {
					game.getCurrentPlayer().setMoney(game.getCurrentPlayer().getMoney() - 100);
					game.getCurrentPlayer().setSquareTimeout(game.getCurrentPlayer().getSquareTimeout() + 7);
					game.getCurrentPlayer().setNextStepCostsDouble(true);
				}));

		roleEffects.put(Role.COMMUNITY_LEADER, new ArrayList<Effect>(effects));

		// ----- COUNCILLOR -----
		effects.clear();
		effects.add(new Effect(
				"Your local social services were overwhelmed by the pandemic and have still not recovered. \n"
				+ "Extra funding has not been allocated to fix the problems, and so you are unable\n"
				+ "to complete task steps for an additional 10 squares.",
				() -> game.getCurrentPlayer().setSquareTimeout((game.getCurrentPlayer().getSquareTimeout() + 10))));
		
		effects.add(new Effect(
				"Your local area has been struggling to integrate asylum seekers into Govan. \n"
				+ "You need to allocate more resources to support their resettlement, \n"
				+ "and therefore will be deducted $150 money, along with your next task contribution costing double.",
				() -> {
					game.getCurrentPlayer().setMoney(game.getCurrentPlayer().getMoney() - 150);
					game.getCurrentPlayer().setNextContributionCostsDouble(true);
				}));

		effects.add(new Effect(
				"Inflation continues to devastate the country, heavily damaging \n"
				+ "your purchasing power, and so you have been deducted $80,"
				+ "and your next task contribution will cost double.",
				() -> {
					game.getCurrentPlayer().setMoney(game.getCurrentPlayer().getMoney() - 80);
					game.getCurrentPlayer().setNextContributionCostsDouble(true);
				}));
		
		effects.add(new Effect(
				"As unemployment continues to be a huge issue in Govan, your wife loses her job. \n"
				+ "You now must financially support your entire family on one small income. \n"
				+ "$140 has been deducted, and your next step will cost double.",
				() -> {
					game.getCurrentPlayer().setMoney(game.getCurrentPlayer().getMoney() - 140);
					game.getCurrentPlayer().setNextStepCostsDouble(true);
				}));

		roleEffects.put(Role.COUNCILLOR, new ArrayList<Effect>(effects));

		// ----- ORGANISATION -----
		effects.clear();
		effects.add(new Effect(
				"Inflation continues to devastate the country, heavily damaging \n"
				+ "your purchasing power, and so you have been deducted $80,\n"
				+ "and your next task contribution will cost double.",
				() -> {
					game.getCurrentPlayer().setMoney(game.getCurrentPlayer().getMoney() - 80);
					game.getCurrentPlayer().setNextContributionCostsDouble(true);
				}));

		effects.add(new Effect(
				"The funding your organisation gets from the government has been cut heavily,\n "
				+ "given the current state of the economy. You have been deducted $300 and\n "
				+ "will not be able to complete task steps for an additional 20 squares.", () -> {
					game.getCurrentPlayer().setMoney(game.getCurrentPlayer().getMoney() - 300);
					game.getCurrentPlayer().setSquareTimeout(game.getCurrentPlayer().getSquareTimeout() + 20);
				}));

		effects.add(new Effect(
				"The rising cost of living in Govan means that your donators have less \n"
				+ "to give than before. Therefore, you lose $150 and your productivity \n"
				+ "falls, so you can't complete task steps for an additional 10 squares.",
				() -> {
					game.getCurrentPlayer().setMoney(game.getCurrentPlayer().getMoney() - 150);
					game.getCurrentPlayer().setSquareTimeout(game.getCurrentPlayer().getSquareTimeout() + 10);
				}));

		effects.add(new Effect(
				"Your board of directors are completely divided and unable to come to an agreement \n"
						+ "on how to spend funds in this current social climate.\n"
						+ "You will not be able to complete task steps for an additional 10 squares,\n"
						+ "and your next task step will cost double with the delays.",
				() -> {
					game.getCurrentPlayer().setSquareTimeout(game.getCurrentPlayer().getSquareTimeout() + 10);
					game.getCurrentPlayer().setNextStepCostsDouble(true);
				}));
		
		effects.add(new Effect(
				"The charity is struggling to recruit volunteers due to the high workloads \n"
				+ "that the projects demand. The lack of volunteers means that your productivity \n"
				+ "is jeopardized, and so you will not be able to complete task steps for an additional 12 squares,\n"
				+ "while your next step costs double due to labour shortages.",
				() -> {
					game.getCurrentPlayer().setSquareTimeout(game.getCurrentPlayer().getSquareTimeout() + 12);
					game.getCurrentPlayer().setNextStepCostsDouble(true);
				}));
		
		roleEffects.put(Role.ORGANISATION, new ArrayList<Effect>(effects));
	}

	/**
	 * On the Social Issue square, the player suffers resource/game play losses
	 * Different harmful effects are invoked depending on the player's role
	 */
	public void playAction() {
		Role playerRole = game.getCurrentPlayer().getRole();
		
		ArrayList<Effect> effects = roleEffects.get(playerRole);

		// randomly pick an effect from the ArrayList and have it affect the player
		Effect randomEffect = getRandomEffect(effects);
		randomEffect.applyEffect();

		// code to display message on GUI
		JOptionPane.showMessageDialog(null, randomEffect.getMessage(), "Social Issue",
				JOptionPane.INFORMATION_MESSAGE, new ImageIcon("images/social_issue.png"));
	}
	
	private Effect getRandomEffect(ArrayList<Effect> effects) {
		return effects.get(new Random().nextInt(effects.size()));
	}
}
