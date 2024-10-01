package sustainopoly;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * GameBoardComplete class which will contain all the specific TaskSquares and
 * other squares which make up the game layout
 */
public class GameBoardComplete extends GameBoard {

	/**
	 * Creates a GameBoard which is filled with the squares of our game layout, to
	 * be used in the application class
	 */
	public GameBoardComplete() {
		super(); // creates a GoVan square as the first square

		ArrayList<String> steps = new ArrayList<>();
		// --------------- CONSULT COMMUNITY SQUARES ---------------

		// Talk to Community Groups steps
		steps.addAll(Arrays.asList("Send meeting invitations to Govan's many community groups, such as Govan Community Project members",
				"Host a community meeting to discuss the issue of food bank transport accessibility.",
				"Collate summary reports of the key issues identified"));
		TaskSquare cc1 = new TaskSquare("Community Groups", "In order to know how to best help the people of Govan, we must find out their needs. "
				+ "Community Groups are a great source of this information.",
				DevelopmentArea.CONSULT_COMMUNITY, steps, 200, 0, 100, 40, 0);
		steps.clear();

		// Food Bank Survey Steps
		steps.addAll(Arrays.asList("Develop survey in collaboration with The Govan Pantry", "Print and distribute survey in food banks, where they can be returned anonymously in a dropbox",
				"Identify the most pressing issues for food bank users, receive consultance on how to improve these. "));
		TaskSquare cc2 = new TaskSquare("Food Bank Survey", "Those who use food banks are those who know best what the provisions are currently "
				+ "like in Govan. Getting their perspective is crucial for the success of the project.", 
				DevelopmentArea.CONSULT_COMMUNITY, steps, 320, 0, 180, 40, 0);
		steps.clear();
		
		// Plan Analysis Steps
		steps.addAll(Arrays.asList("Consult with environmentalists regarding ways to reduce landfill waste that will inevitably be produced by the scheme's activities", 
				"Consult with the local council and community groups to see what current schemes are in place and possible improvements on these which can be built upon"));
		TaskSquare cc3 = new TaskSquare("Analysis", "Receive consultance on analysis of the GoVan scheme's plans to ensure they are sustainable within the community and will benefit the targeted groups.", 
				DevelopmentArea.CONSULT_COMMUNITY, steps, 350, 0, 180, 60, 0);
		steps.clear();

		addSquare(cc1);
		addSquare(cc2);
		addSquare(cc3);

		// --------------- Chance Square ---------------

		ChanceSquare cs1 = new ChanceSquare("Chance Square 1");

		addSquare(cs1);

		// --------------- FIND FUNDING SQUARES --------------- 

		//  Development Fund steps
		steps.addAll(Arrays.asList("Prepare documentation collated from community consultation just carried out, as well as existing material", 
				"Submit the application, in line with the guidance at https://glasgowcity.hscp.scot/public-petitions",
				"Liaise with the Board to receive sufficient money to get the scheme off the ground"));
		TaskSquare ff1 = new TaskSquare("Development Fund",
				"Apply for Development Fund from Glasgow City Integration Joint Board, which supports health and social care initiatives and "
				+ "may be able to provide money for the upstart of the GoVan scheme.", 
				DevelopmentArea.FIND_FUNDING, steps, 500, 0, 220, 60, 0);
		steps.clear();

				// Gain sponsorship steps
		steps.addAll(Arrays.asList("Approach local businesses, for example at the \"Glasgow Riverside Innovation District\"(GRID), or at \"Rookie Oven\"", 
				"Establish contract terms, providing funding for the short term initially with options to extend", "Add company logos to the GoVan, which will be an excellent "
						+ "form of advertisement, as well as give a strong reputation for the companies on social media"));
		TaskSquare ff3 = new TaskSquare("Sponsorships", "If the scheme can develop significant social media attention, sponsors could be a consistent funding stream "
				+ "for the project. Start with trying to secure some local business sponsorships.",
				DevelopmentArea.FIND_FUNDING, steps, 200, 0, 220, 80, 0);
		steps.clear();
		
		// Online funding steps - Most expensive, would need to hire video equipment and an editor etc
		steps.addAll(Arrays.asList("Create a hip, informative video about the proposed GoVan scheme, and the benefits it would bring to many people in need in Govan", 
				"Leverage social media to reach a large audience with the scheme- use the algorithm!", "Ensure transparency in any funding received from the public, "
						+ "and maintain updates on how this money is being spent"));
		TaskSquare ff2 = new TaskSquare("Online Funding", "Online funding is not a sustainable or consistent funding source, however an initial "
				+ "cash injection would greatly benefit the scheme. Set up an online funding campaign.",
				DevelopmentArea.FIND_FUNDING, steps, 1000, 0, 220, 60, 0);
		steps.clear();

		addSquare(ff1);
		addSquare(ff2);
		addSquare(ff3);

		// --------------- ADVANCE AWARENESS SQUARES ---------------

		// Local publicity steps
		steps.addAll(Arrays.asList("Contact local newspapers, such as \"The Southern Reporter\", to organise for a column on the GoVan scheme to be published", 
				"Approach local radio stations, such as \"Sunny G Community Radio\", to make appearances on their show", 
				"STV News has a big reach on TV. Work with them to produce a piece for airing about the idea and aspirations of the scheme."));
		TaskSquare aa1 = new TaskSquare("Publicity", "To spread awareness of the scheme within the local community, it will "
				+ "help to get publicity through working with local media.",
				DevelopmentArea.ADVANCE_AWARENESS, steps, 400, 6, 280, 80, 0);
		steps.clear();
		
		// --------------- Social Issue Square ---------------

		SocialIssueSquare si1 = new SocialIssueSquare();

		// social media steps
		steps.addAll(Arrays.asList("Invest time and resources into learning about how to get good diffusion online. Making the GoVan scheme hip and fashionable "
				+ "is essential, not least to help in removing the stigma around food banks", "Create initial content for the scheme's pages. Try involve some local influencers to spread your "
						+ "audience", "Create a YouTube page which will document the day to day workings of the GoVan, which is sure to be interesting for viewers"));
		TaskSquare aa2 = new TaskSquare("Social Media",
				"In this day and age, social media is essential in reaching the masses. Developing the GoVan scheme's social media presence will be important"
				+ "both for spreading awareness, as well as attracting potential sponsors", DevelopmentArea.ADVANCE_AWARENESS, steps, 500, 6, 280, 80, 0);
		steps.clear();
		
		// fliers and posters step
		steps.addAll(Arrays.asList("\"Work with local graphic designers to produce eye-catching, friendly and hip posters/fliers, for example at \"Glasgow Creative\"",
				"Through consultation with food banks, decide on the required quantities to be printed", "Distribute fliers around town both actively in person around busy areas, as well as passively by leaving "
						+ "some in food banks around town", "Work with the local council to be able to place posters around town for the scheme"));
		TaskSquare aa3 = new TaskSquare("Posters", "It needs to be remembered that not everyone will have access to technology. The scheme needs to produce posters and fliers to reach those through non-digital means.",
				DevelopmentArea.ADVANCE_AWARENESS, steps, 500, 6, 300, 100, 0);
		steps.clear();

		addSquare(aa1);
		addSquare(si1);
		addSquare(aa2);
		addSquare(aa3);

		// --------------- Fact Square ---------------

		FactSquare fs1 = new FactSquare("Fact Square");

		addSquare(fs1);

		// --------------- TRAIN TEAM SQUARES ---------------

		// gather team steps
		steps.addAll(Arrays.asList("Leveraging the social media presence of the scheme, appeal to any locals who would be willing to volunteer. Host a local event for people interested to explain more details and engage with them",
				"Receive consultation on the required staffing of the scheme, then publish job openings to look for suitable individuals passionate about the project", 
				"Work with the local community- see if there are any sports clubs or religious centres who would be willing to lend a hand, and provide them the resources to play their part"));
		TaskSquare tt1 = new TaskSquare("Gather Team", "A good team of workers and volunteers will be very important to the project. Go find a team of special people!", 
				DevelopmentArea.TRAIN_TEAM, steps, 1000, 8, 300, 100, 5);
		steps.clear();

		// Cultural Awareness Training Steps
		steps.addAll(Arrays.asList("Educate the team on the richness in variety of cultures living in Govan- it's important, as far as is possible, to try and help people maintain their "
				+ "culinary customs and traditions", "Some groups may see it as shameful having to access food support, so train the team in how to help treat people with friendliness and respect"
						+ "to break down this stigma", "Bring in training from locals of different ethnic groups to offer culturally accessible food preparation classes"));
		TaskSquare tt2 = new TaskSquare("Cultural Training",
				"Train the team in important skills which will be essential to make the scheme successful and respectful towards all individuals.", DevelopmentArea.TRAIN_TEAM, 
				steps, 1300, 8, 400, 150, 10);
		steps.clear();

		// Technician Training Steps
		steps.addAll(Arrays.asList("In collaboration with the local mechanic, look for government support schemes regarding EVs and education in the sector", 
				"Hire an experienced educator on the topic to deliver an initial curriculum, which will go in tandom with hands on experience", 
				"Work on developing a community network to bring together local EV mechanics- Govan could become a centre for EV maintenance"));
		TaskSquare tt3 = new TaskSquare("Apprenticeship", "Making use of electric vehicle(s) is an important aspect to the sustainability of the scheme. To be able to maintain these, "
				+ "set up electric vehicle apprenticeship in a local mechanic, which will have benefit even beyond the GoVan scheme",
				DevelopmentArea.TRAIN_TEAM, steps, 1000, 16, 350, 200, 20);
		steps.clear();
		
		addSquare(tt1);
		addSquare(tt2);
		addSquare(tt3);

		// --------------- ORGANIZE ONLINE PRESENCE SQUARES ---------------

		// Create Website For The Food Truck Scheme Steps
		steps.addAll(Arrays.asList("Make use of a no-code website builder to create initial drafts of the website", 
				"Receive consultation from graphic designers worked with previously on the appeal and welcoming-ness of the design, important to ensure people don't feel pushed away", 
				"Embed an interactive truck route on a map displaying times and locations of the GoVan's services", 
				"Hire a developer to ensure the cyber-security of the website- sadly, some people may have aggression towards those using the services, like asylum seekers, and wish to bring it down..."));
		TaskSquare oo1 = new TaskSquare("Website", "Create an informative website for the GoVan scheme, with information about the scheme as well as how to access its services",
				DevelopmentArea.ORGANIZE_ONLINE_PRESENCE, steps, 800, 6, 150, 50, 5);
		steps.clear();

		// online advertising steps
		steps.addAll(Arrays.asList("Work with the team who've been creating scheme content to produce fun, hip and creative ads", 
				"Collaborate with social media platforms and see if there is a possibility of receiving reduced ad fees for a charitable organisation such as yours, and publish the ads on platforms like Google and Facebook", 
				"Monitor the click-through stats of the ads on an ongoing basis to determine their value. Even so, they will have spread local recognition of the scheme, which will filter down to the people who need to hear about it"));
		TaskSquare oo2 = new TaskSquare("Advertising",
				"Experiment with online advertising, testing if it has a worthwhile return of attention with comparison to expenditure",
				DevelopmentArea.ORGANIZE_ONLINE_PRESENCE, steps, 400, 10, 100, 12, 5);
		steps.clear();

		// --------------- Break Square ---------------

		BreakSquare bs1 = new BreakSquare();

		// blog steps
		steps.addAll(Arrays.asList("Extend the website's functionality by adding a blog and forum section, which can be used to inform about the scheme's activities, as well as allow people to ask questions easily", 
				"Promote an incentive for people to go to and ask questions on the forum- for example, the possibility of winning gift cards. This will help increase the reach of the scheme",
				"Find a volunteer if possible, or else hire a content writer, who's passionate about the scheme and willing to produce consistent posts for the blog"));
		TaskSquare oo3 = new TaskSquare("Blog", "Keeping the public and scheme users up to date with the scheme is essntial to ensure transparency and trust. A great way to do this is to develop a regular blog, detailing the ins and outs of the scheme's day-to-day activities.", 
				DevelopmentArea.ORGANIZE_ONLINE_PRESENCE, steps, 300, 10, 150, 100, 15);
		steps.clear();

		// Create App for GoVan Steps
		steps.addAll(Arrays.asList("Work with local graphic designers, for example at \"Glasgow Creative\", to design a memorable and eye-catching logo for the GoVan scheme's app", 
				"Decide on the features of the app and hire a local app designer, such as at \"Bad Dinosaur\" to securely implement these features; these may include an interactive truck route, "
				+ "a live feed on the current location of the GoVan(s), current supply statuses of the scheme etc", 
				"Go through application and approval mechanisms to be able to get the app published on the App Store and Play Store. Promote it on your social medias"));
		TaskSquare oo4 = new TaskSquare("GoVan App", "A dedicated GoVan app is a great way to be able to more easily communicate with users, for example through announcements and notifications", DevelopmentArea.ORGANIZE_ONLINE_PRESENCE,
				steps, 1000, 16, 300, 200, 10);
		steps.clear();

		addSquare(oo1);
		addSquare(oo2);
		addSquare(bs1);
		addSquare(oo3);
		addSquare(oo4);

		// --------------- Chance Square ---------------

		ChanceSquare cs2 = new ChanceSquare("Chance Square 2");
		addSquare(cs2);

		// --------------- DISPENSE DEVICES SQUARES ---------------

		// Implement Free WiFi on the GoVan Steps
		steps.addAll(Arrays.asList("Negotiate with internet service providers to try and get the best ongoing deal as possible. Note that this is not standard home broadband, as it is for a mobile van and not through fibre cables", 
				"Install the networking equipment in the van, ensuring cyber-security through means such as firewalls and VPNs",
				"Use updated fliers and posters designed previously to spread awareness of this new important service to those who may be in digital poverty; collect user profiles to help identify how the "
				+ "service can be improved to help the most vulnerable"));
		TaskSquare dd1 = new TaskSquare("Get WiFi", "Install free WiFi on the GoVan, allowing the GoVan to transform into a type of \"mobile digital hub\", providing access to internet-connected devices for short periods for those who require this.",
				DevelopmentArea.DISPENSE_DEVICES, steps, 600, 12, 200, 150, 20);
		steps.clear();
		
		// Work with Glasgow's Govan Community Project Steps
		steps.addAll(Arrays.asList("Develop \"Device Donation\" scheme with Govan Community Project, and work to promote and spread this appeal to the people of Govan", 
				"Create on-site mobile tablet usage facilities, such as charging ports, and uninstall any old unneccesary software on the tablets which would pose a security threat",
				"Purchase and install software for multi-lingual support, catering to the numerous languages spoken by potential users of the scheme"));
		TaskSquare dd2 = new TaskSquare("Get Devices",
				"Work with Govan Community Project to receive donations of unused, secondhand devices for distribution at the GoVan- this will also help reduce e-waste.",
				DevelopmentArea.DISPENSE_DEVICES, steps, 350, 12, 150, 100, 15);
		steps.clear();

		addSquare(dd1);
		addSquare(dd2);

		// --------------- GET GOVAN SQUARES ---------------

		// Get Electric Truck Steps
		steps.addAll(Arrays.asList("Research and contact potential companies, for example \"Arrival\", a British EV manufacturer", "Negotiate terms of agreement, drawing up the contract for what kind of "
				+ "benefits the scheme can give in return for the company's sponsorship", "Work with specialists to decide on a seamless interior design for the GoVan, which will faciliate and make easy the storage and distribution "
						+ "of food. Send the plans to the company so they can manufacture to these specifications (within reason)"));
		TaskSquare gg1 = new TaskSquare("Truck Sponsor",
				"Work with electric vehicle companies to find a sponsor who will provide the GoVan itself, in return for advertising on the truck and diffusion on the scheme's social medias.", 
				DevelopmentArea.GET_GOVAN, steps, 200, 14, 200, 100, 5);
		steps.clear();
		
		// Refurbish the GoVan
		steps.addAll(Arrays.asList("Source and purchase appropriate equipment, for example shelving, refrigeration units, and equipment for the concession door/window that food is distributed through", 
				"Create a friendly and exciting design for the external of the refurbished van, as well as work with ergonomic designers for the arrangement and layouts of the internals", 
				"Hire a mechanic to carry out any necessary technical work on the GoVan to ensure its safe and mission-ready"));
		TaskSquare gg2 = new TaskSquare("Refurbish", "The GoVan will not just be a standard van with many passenger seats in the back... Renovate the electric van so that it can fulfil its function in storing "
				+ "and distributing large quantities of food, and potentially serving fresh food down the line.",
				DevelopmentArea.GET_GOVAN, steps, 1500, 20, 500, 300, 30);
		steps.clear();

		// develop route and get permissions steps
		steps.addAll(Arrays.asList("Work in collaboration with food bank users and food bank providers to design the GoVan route, which it will service on a rotating basis, with stops at each location for a number of hours before moving on to the next", 
				"Contact \"The Trussel Trust\"- the UK's only food bank network- to learn about what admin work is required for the scheme to function sustainably and in accordance with any regulations", 
				"Prepare necessary documentation and apply to Glasgow City Council for permissions to stop at the proposed route locations"));
		TaskSquare gg3 = new TaskSquare("Permissions", "The scheme must ensure it works collaboratively with the local council, and that includes ensuring that all necessary permissions and licences are acquired for the proposed stop locations of the GoVan.",
				DevelopmentArea.GET_GOVAN, steps, 300, 6, 100, 50, 10);
		steps.clear();

		// --------------- Community Betterment Square ---------------

		CommunityBettermentSquare cbs1 = new CommunityBettermentSquare();

		// Develop Accessible Menus Steps
		steps.addAll(Arrays.asList("A key need of many ethnic groups is Halal and Kosher food in Govan. Work to devise realistic foodstuffs of these categories which could be sourced and distributed", 
				"Vegetarian and vegan food is often more nutritious as well as eco-friendly than their processed counterparts. Develop appealing food offers to help introduce this into the diet of those using food banks",
				"Research and receive consultance on what kind of allergy-free foodstuffs the food bank could realistically offer to cater to these people's needs",
				"Develop recipe cards for a wide range of recipes e.g. African, Middle-Eastern, which can be handed out from the GoVan"));
		TaskSquare gg4 = new TaskSquare("Make Menus", "The GoVan should be able to cater for different cultures and culinary needs. Work to find out what this entails and develop food lists which meet these criteria.", 
				DevelopmentArea.GET_GOVAN, steps, 500, 6, 200, 100, 10);
		steps.clear();

		addSquare(gg1);
		addSquare(gg2);
		addSquare(gg3);
		addSquare(cbs1);
		addSquare(gg4);

		// --------------- SOURCE SUPPLY CHAIN SQUARES ---------------

		// Set Up Community Groups for Urban Farm Steps
		steps.addAll(Arrays.asList("Establish contact with \"Fairfield Farm\", discussing terms of an agreement where the GoVan scheme could benefit from produce of the farm in return for advertising and additional funding",
				"Leverage the scheme's social medias to advertise the community group and try and encourage more members to come and join, helping to create a sense of community",
				"Hire consultation on potential design renovations to the community urban farm, which includes the maintainence of an 1850 farmhouse- maintain Govan's heritage!"));
		TaskSquare ss1 = new TaskSquare("Urban Farm", "Work with the \"Fairfield Farm\" team in Elder Park to set up a growing scheme which could provide supplies to the GoVan scheme.",
				DevelopmentArea.SOURCE_SUPPLYCHAIN, steps, 800, 16, 600, 200, 40);
		steps.clear();

		// Consult Local Butchers/Farms for Meat Supply Steps
		steps.addAll(Arrays.asList("Establish initial contacts with local butchers for a meat supply, for example Low’s Butchers",
				"Negotiate the terms of the contract with the involved parties, offering as part of their compensation to advertise them on the GoVan as well as on social medias",
				"Based on previous community consultation, devise estimates of required supplies, and schedules on how often deliveries will occur"));
		TaskSquare ss2 = new TaskSquare("Partnerships", "Ideally, the GoVan scheme will be able to distribute fresh meat to allow residents to cook from scratch, something very important to many food bank users. Develop partnerships with local butchers to secure this.", 
				DevelopmentArea.SOURCE_SUPPLYCHAIN, steps, 800, 16, 600, 200, 40);
		steps.clear();

		// Community Food Hub steps
		steps.addAll(Arrays.asList("Contact potential sites prepared to offer these facilities, for example \"Riverside Hall\", and set up a timetabled schedule", 
				"The Saturday market in Govan used to have 20 stalls, but now only has 3. Re-start this initiative in the secured location, with promotion through social media and fliers", 
				"Hire a local chef to offer culturally-diverse cooking classes weekly, which will help educate on the variety of rich cultures in Govan, as well as help people learn to cook nutritious food"));
		TaskSquare ss3 = new TaskSquare("Food Hub", "Set up a community food hub to provide an opportunity for locals to have cooking classes, share food or try out food from different cultures",
				DevelopmentArea.SOURCE_SUPPLYCHAIN, steps, 1000, 16, 600, 200, 40);
		steps.clear();

		// Supermarkets steps
		steps.addAll(Arrays.asList("Contact local supermarkets, for example the Asda and Iceland, to discuss possibilites for better provisioning for food for the GoVan scheme", 
				"Devise a system in collaboration with the supermarkets that allows them to monitor demand of product stocks, and if there is a low-demand, send excess of these items which are unlikely to sell to the GoVan scheme",
				"Negotiate contracts whereby supermarkets will act as an intermediary with wholesalers to allow the scheme to buy food directly at a much lower price, and possibly with subsidies"));
		TaskSquare ss4 = new TaskSquare("Supermarkets",
				"Organise systems with local supermarkets to receive excess stock BEFORE it goes out of date, which is a consistent problem for food banks trying to provide fresh goods.",
				DevelopmentArea.SOURCE_SUPPLYCHAIN, steps, 1500, 16, 600, 300, 50);
		steps.clear();

		addSquare(ss1);
		addSquare(ss2);
		addSquare(ss3);
		addSquare(ss4);

		// --------------- INCREMENT INFRASTRUCTURE SQUARES ---------------

		// Create Base of Operations Steps
		steps.addAll(Arrays.asList("Consult with possible site owners- Govan Community Project will likely to be able to help with this, having sourced 2 offices of their own", 
				"Hire an architect and builders to refurbish the site into an open, community space which will be open and welcoming to the public and users of the GoVan scheme", 
				"Install necessary facilities in the office and warehousing spaces, for example computers, refrigeration and stock-tracking software"));
		TaskSquare ii1 = new TaskSquare("Headquarters", "The GoVan scheme needs a suitable base of operations. Work with local providers to find a potential site, and set up shop!",
				DevelopmentArea.INCREMENT_INFRASTRUCTURE, steps, 1500, 20, 800, 400, 60);
		steps.clear();

		// charging point steps
		steps.addAll(Arrays.asList("Avail of government grants which offer significant subsidies on EV charging points- prepare and submit required documentation", 
				"Hire a local electrician to install the charging point alongside the building, ensuring safety regulations are met", 
				"Install security cameras and secure facilities regarding storage of the GoVan"));
		TaskSquare ii2 = new TaskSquare("Charging Point", "Set up an electric charging station for the GoVan at the new scheme headquarters, as well as a secure storage place for the vehicle.",
				DevelopmentArea.INCREMENT_INFRASTRUCTURE, steps, 1300, 18, 600, 300, 60);
		steps.clear();

		// Purchase Un-used Land Around Govan To Start a Farm Steps
		steps.addAll(Arrays.asList("Survey appropriate land, and negotiate with land owners to be able to make use of it at a reduced price for its charitable activities", 
				"Carry out archeological surveys on the land to ensure that none of Govan's heritage is hidden and would be damaged by use of the land", 
				"Employ a local farmer to sew and begin the initial procedures of setting up the farm, ensuring crops are grown in a sustainable manner"));
		TaskSquare ii3 = new TaskSquare("Start Farm", "Govan has much derelict and vacant land which can be repurposed. Look for land near the scheme's site to purchase and install the beginnings of an urban farm on.",
				DevelopmentArea.INCREMENT_INFRASTRUCTURE, steps, 2000, 18, 1000, 400, 70);
		steps.clear();

		addSquare(ii1);
		addSquare(ii2);
		addSquare(ii3);

		// --------------- CHANCE CARDS ---------------

		// FreeStep cards

		addChanceCard(new FreeStepCard("You have been awarded a Community Hero award "
				+ "in recognition for your ongoing support. "
				+ "\nComplete your next task step for free."));

		addChanceCard(
				new FreeStepCard("You attended a local Digital Skills class and have found that with your new skills,"
						+ "\ntasks that were once time-consuming are now completed effortlessy, boosting your productivity overall."
						+ "\nTherefore, your next step can be completed for free."));

		// Money Cards

		addChanceCard(new MoneyCard("With the heavy rain in the city, your home has flooded."
				+ "\nPay $120 on urgent repairs.", -120));

		addChanceCard(new MoneyCard("A nationwide strike was called on the public transportation sector."
				+ "\nTherefore, your local buses are not running. You had to get an expensive Uber instead,"
				+ "\nwhich charged you $50.", -50));

		addChanceCard( new MoneyCard("The extremely admirable work you've completed to help your local community has been "
						+ "\nrecognised by the National Lottery Community Fund, who wish you reward you with a cash payment."
						+ "\nReceive $150.", 150));

		addChanceCard(new MoneyCard("You won a pub quiz for a local fundraiser hosted by Sunny G radio."
				+ "\nYou will recieve $100.", 100));

		// Timeout Cards

		addChanceCard(new TimeoutCard("You have injured your back after a nasty slip. "
				+ "\nYou won't be able to complete task steps for 10 additional squares.", 10));

		addChanceCard(new TimeoutCard("The effects of political crisis in Westminster have spread to your local area."
				+ "\nHuge strikes across all of the public sector have rendered you temporarily unavaiable to complete task steps."
				+ "\nYou will not be able to complete task steps for the next 20 squares, until the crisis has calmed down."
				+ "\n ", 20));

		// FreeContribution Cards

		addChanceCard(new FreeContributionCard("Your local community group has put forward a new scheme to strengthen community ties. "
						+ "\nYou join the initiative and are working in a team to help other members of the community complete their tasks."
						+ "\nBy working together, you are able to help other members of the community make much greater progress than they would alone, "
						+ "\nand so your next contribution towards another member's task is free."));

		addChanceCard(new FreeContributionCard("Alone, we can do so little; together, we can do so much- Helen Keller."
				+ "\nTo encourage community development, your next contribution towards a task is free."));

		addChanceCard(new FreeContributionCard("You've been very busy trying to solve community problems by yourself... \n"
				+ "help another out with a free next contribution to their task."));

	}

}
