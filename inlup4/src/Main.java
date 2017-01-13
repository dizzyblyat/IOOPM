import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * <h1>Main</h1>
 * The main loop
 * 
 * @author Damir Suvajac
 * @version 1.0
 * @since 2017-01-08
 */
public class Main {
	
	/**
	 * Welcome message
	 * 
	 * @return s the String
	 */
	private static String welcomeMsg() {
	String s = "";
	s += "_____________________________________________________________________________\n\n";
	s += "██████╗  ██████╗ ██╗     ██╗      █████╗ ██╗  ██╗███╗   ███╗██╗   ██╗██████╗ \n";
	s += "██╔══██╗██╔═══██╗██║     ██║     ██╔══██╗╚██╗██╔╝████╗ ████║██║   ██║██╔══██╗\n";
	s += "██████╔╝██║   ██║██║     ██║     ███████║ ╚███╔╝ ██╔████╔██║██║   ██║██║  ██║\n";
	s += "██╔═══╝ ██║   ██║██║     ██║     ██╔══██║ ██╔██╗ ██║╚██╔╝██║██║   ██║██║  ██║\n";
	s += "██║     ╚██████╔╝███████╗███████╗██║  ██║██╔╝ ██╗██║ ╚═╝ ██║╚██████╔╝██████╔╝\n";
	s += "╚═╝      ╚═════╝ ╚══════╝╚══════╝╚═╝  ╚═╝╚═╝  ╚═╝╚═╝     ╚═╝ ╚═════╝ ╚═════╝ \n";
	s += "_____________________________________________________________________________\n\n";
	s += "   Welcome to PollaxMUD! If you need help with the commands, type \"help\".    \n";
	s += "_____________________________________________________________________________\n";
	return s;
	}
	
	/**
	 * <h1>Screen clearer</h1>
	 * 
	 * UNIX
	 */
	/*
	public static void clearScreen() {  
	    System.out.print("\033[H\033[2J");  
	    System.out.flush();  
	}
	 */
	
	/**
	 * <h1>Screen clearer</h1>
	 * 
	 * WINDOWS
	 */
	public static void clearScreen() {
		System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
	}
	
	public static void main(String[] args) {
		World world = new World();
		Avatar avatar = new Avatar(world.getAvatarsCourses(), world.placeAvatar());
		Scanner inputReader = new Scanner(System.in);
		String input;
		boolean quit = false;
		
		clearScreen();
		System.out.println(welcomeMsg());
		System.out.println(avatar);
		
		while(!quit) {
			System.out.print(": "); // TODO: make it blink
			input = inputReader.nextLine();
			String parsedInput[] = input.split(" ");
			clearScreen();
			switch(parsedInput[0].toLowerCase()) {
			
			
			case "quit":
				inputReader.close();
				System.out.println("Quitting...");
				System.out.println("Okay bye!");
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				quit = true;
				break;
				
			case "mystats":
				System.out.println(avatar);
				break;
				
			case "look":
				avatar.look();
				break;
				
			case "move":
				if(parsedInput.length > 2) {
					System.out.println("Too many arguments!");
				} else if (parsedInput.length == 1) {
					System.out.println("Too few arguments!");
				} else {
					if(parsedInput[0].toLowerCase().equals("move") && parsedInput[1].toLowerCase().equals("n") || parsedInput[1].toLowerCase().equals("north")) {
						avatar.move("n");
						
					} else if(parsedInput[0].toLowerCase().equals("move") && parsedInput[1].toLowerCase().equals("e") || parsedInput[1].toLowerCase().equals("east")) {
						avatar.move("e");
						
					} else if(parsedInput[0].toLowerCase().equals("move") && parsedInput[1].toLowerCase().equals("s") || parsedInput[1].toLowerCase().equals("south")) {
						avatar.move("s");
						
					} else if(parsedInput[0].toLowerCase().equals("move") && parsedInput[1].toLowerCase().equals("w") || parsedInput[1].toLowerCase().equals("west")) {
						avatar.move("w");
						
					} else {
						System.out.println("Direction \"" + parsedInput[1] + "\" does not exist!");
					}
				}
				break;
				
			case "unlock":
				if(parsedInput.length > 2) {
					System.out.println("Too many arguments!");
				} else if (parsedInput.length == 1) {
					System.out.println("Too few arguments!");
				} else {
					if(parsedInput[0].toLowerCase().equals("unlock") && parsedInput[1].toLowerCase().equals("n") || parsedInput[1].toLowerCase().equals("north")) {
						avatar.unlock("n");
					} else if(parsedInput[0].toLowerCase().equals("unlock") && parsedInput[1].toLowerCase().equals("e") || parsedInput[1].toLowerCase().equals("east")) {
						avatar.unlock("e");
					} else if(parsedInput[0].toLowerCase().equals("unlock") && parsedInput[1].toLowerCase().equals("s") || parsedInput[1].toLowerCase().equals("south")) {
						avatar.unlock("s");
					} else if(parsedInput[0].toLowerCase().equals("unlock") && parsedInput[1].toLowerCase().equals("w") || parsedInput[1].toLowerCase().equals("west")) {
						avatar.unlock("w");
					} else {
						System.out.println("Direction \"" + parsedInput[1] + "\" does not exist!");
					}
				}
				break;
				
			case "inventory":
				avatar.getInventoryList();
				break;
				
			case "take":
				if (parsedInput.length == 1) {
					System.out.println("Too few arguments!");
				} else {
					String itemName = input.substring(5).toLowerCase();
					if(avatar.getPosition().hasItem(itemName)) {
						avatar.take(itemName);
					} else {
						System.out.println("Item \"" + input.substring(5) + "\" does not exist!");
					}
				}
			break;
			
			case "drop":
				if (parsedInput.length == 1) {
					System.out.println("Too few arguments!");
				} else {
					avatar.drop(input.substring(5).toLowerCase());
				}
				break;
				
				
			case "greedisgood":
				avatar.setDoneHP(180);
				System.out.println("YOU'VE DONE WELL! CHEATS ARE GOOD!");
				System.out.println("HP: " + avatar.getDoneHP());
				break;
				
			case "talk":
				if(parsedInput.length > 2) {
					System.out.println("Too many arguments!");
				} else if (parsedInput.length == 1) {
					System.out.println("Too few arguments!");
				} else {
					if(avatar.getPosition().isCreatureThere(parsedInput[1].toLowerCase())) {
						Creature c = avatar.getPosition().getCreature(parsedInput[1].toLowerCase());
						if(c instanceof Teacher) {
							((Teacher) c).talk(avatar.getCompleteCourses(), avatar.getIncompleteCourses());
						} else {
							c.talk();
						}
					} else {
						System.out.println("\"" + parsedInput[1] + "\" is not in the room...");
					}
				}
				break;
				
			case "enroll":
				if(parsedInput.length == 1) {
					System.out.println("Too few arguments!");
				} else if(avatar.isCourseEnrolled(input.substring(7).toLowerCase())) {
					System.out.println("You are already enrolled or passed \"" + input.substring(7) + "\".");
				} else {
					boolean isCourseFound = false;
					for(Creature c : avatar.getPosition().getCreatures()) {
						if(c instanceof Teacher) {
							Course course = ((Teacher) c).returnCourse(input.substring(7).toLowerCase());
							if(course != null) {
								avatar.addIncompleteCourse(course);
								isCourseFound = true;
								break;	
							}
						}
					}
					if(isCourseFound) {
						System.out.println("You enrolled \"" + input.substring(7) + "\"!");
					} else {
						System.out.println("Course \"" + input.substring(7) + "\" was not found!");
					}
				}
				break;
				
			case "trade":
				if(parsedInput.length == 1) {
					System.out.println("Too few arguments!");
				} else if(parsedInput.length > 2) {
					System.out.println("Too many arguments!");
				} else {
					if(avatar.getPosition().isCreatureThere(parsedInput[1].toLowerCase())) {
						Creature c = avatar.getPosition().getCreature(parsedInput[1].toLowerCase());
						
						if(c instanceof Student) {
							boolean isInTrade = true;
							Student s = (Student) c;
							
							if(avatar.hasItem(s.wantedBookName())) {
								
								if(s.isTradeable()) {
									while(isInTrade) {
										System.out.println(s.creatureName + ": Do you want the BOOK, the ANSWER or to CANCEL our conversation?");
										System.out.print(": ");
										String inputTemp = inputReader.nextLine();
										String parsedInputTemp[] = inputTemp.split(" ");
										switch(parsedInputTemp[0].toLowerCase()) {
								
										case "book":
											int weightOfInvAfterTrade = ((avatar.getInventoryWeight() + s.getBookToGiveWeight()) - world.getItemWeight(s.wantedBookName()));
											int weightOfMaxInv = (avatar.getInventoryMaxWeight() + avatar.getInventoryWeight());
											
											if(weightOfInvAfterTrade <= weightOfMaxInv) {
												avatar.drop(s.wantedBookName().toLowerCase());
												avatar.addItemToInventory(s.getBookToGive());
												System.out.println("You gave \"" + s.wantedBookName() + "\" to \"" + s.creatureName + "\".");
												System.out.println("You received \"" + s.getBookToGive().itemName + "\" in your Inventory!");
												s.setTradeable(false);
												isInTrade = false;
											} else {
												System.out.println("You do not have enough space in your Inventory to complete trade!");
												System.out.println("You need to free up "
												+ (weightOfInvAfterTrade - weightOfMaxInv)
												+ " slots in your Inventory to make the trade!");
												System.out.println("Drop an Item from Inventory in order to try trading again!");
												isInTrade = false;
											}
											break;
											
										case "answer":
											avatar.drop(s.wantedBookName().toLowerCase());
											System.out.println("You gave \"" + s.wantedBookName() + "\" to \"" + s.creatureName + "\".");
											System.out.println(s.creatureName + ": Okay, the answer to \"" + s.getCompleteCourseName() + "\" is:");
											System.out.println(s.getAnswerToCourse() +"\n");
											s.setTradeable(false);
											isInTrade = false;
											break;
											
										case "cancel":
											isInTrade = false;
											break;
											
										default:
											System.out.println("\"" + inputTemp + "\" is not valid.");
											break;
										}
									}
								} else {
									System.out.println("You have already traded with \"" + s.creatureName + "\"!");
								}
							} else {
								System.out.println(s.creatureName + ": You don't have the book that I want...");
							}
						}
					} else {
						System.out.println("\"" + parsedInput[1] + "\" is not in the room...");
					}
				}
				break;
				
			case "graduate":
				if(avatar.getPosition().isCreatureThere("sfinx")) {
					if(avatar.isDone()) {
						avatar.diploma();
						try {
							TimeUnit.SECONDS.sleep(10);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						quit = true;
					} else {
						System.out.println("Sfinx: Fool! You have yet to pass the test! Grind some points child!");
					}
				} else {
					System.out.println("The SFINX is not in this room!");
				}
				break;
			
			case "help":
				String helpCommands = "Commands:\n";
				helpCommands += "Help - Displays all available commands\n\n";
				helpCommands += "Mystats - Displays your current stats\n\n";
				helpCommands += "Look - See what is in your current location\n\n";
				helpCommands += "Inventory - Displays the space and item(s) in your inventory\n\n";
				helpCommands += "Move X (replace X with north, east, south, west, or n, e, s, w)\n";
				helpCommands += "  - Moves you in given direction\n\n";
				helpCommands += "Pick Y (replace Y with an item name)\n";
				helpCommands += "  - Picks up an item and stores it in your inventory\n\n";
				helpCommands += "Drop Y (replace Y with an item name)\n";
				helpCommands += "  - Drops an item from your inventory\n\n";
				helpCommands += "Unlock X (replace X with north, east, south, west, or n, e, s, w)\n";
				helpCommands += "  - Unlocks a locked door in given direction\n\n";
				helpCommands += "Talk Z (replace Z with creature name such as Student1, Teacher1 or Sfinx)\n";
				helpCommands += "  - Interact with specified creature\n\n";
				helpCommands += "Trade Z (replace Z with Student name, such as Student1)\n";
				helpCommands += "  - Trade items with other Students\n\n";
				helpCommands += "Enroll Z (replace Z with Teacher name, such as Teacher1)\n";
				helpCommands += "  - Enroll the course that the Teacher is holding\n\n";
				helpCommands += "Graduate - If you have enough HP, the Sfinx will let you graduate\n\n";
				helpCommands += "Quit - Quits the game\n\n";
				System.out.print(helpCommands);
				break;
				
			default:
				System.out.println("Does not know how to \"" + input + "\".");
				break;
			}	
		}
	}
}
