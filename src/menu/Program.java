package menu;

import java.util.Scanner;

import models.PC;

public class Program {
	private PC a;
	private PC b;
	private PC c;

	public Program() {
		a = new PC(1, "PC of A");
		b = new PC(2, "PC of B");
		c = new PC(3, "PC of C");
	}

	private void printMenu(String title, String[] options) {
		System.out.printf("\n===================  %s  =================\n", title);
		int i;
		for (i = 0; i < options.length; i++) {
			System.out.printf("%d. %s\n", i + 1, options[i]);
		}

		System.out.printf("%d. Exit\n", i + 1);

		System.out.println("=================================================");
		System.out.print("Enter your choice: ");
	}

	private int choiceValidation() {
		Scanner scanner = new Scanner(System.in);
		int choice;
		while (true) {
			try {
				choice = Integer.parseInt(scanner.nextLine());
				return choice;
			} catch (Exception e) {
				// CPU sẽ chạy đến chỗ này khi có cà chớn xảy ra
				// => JVM không giết app như truyền thống nữa
				System.out.println("Invalid Input. Please try again!");
				System.out.print("Enter your choice: ");
			}
		}
	}

	private String messageValidation() {
		Scanner scanner = new Scanner(System.in);
		String message;
		
		while (true) {
			System.out.print("Write your Message: ");
			message = scanner.nextLine().trim();

			if(message.isEmpty()) {
				System.out.println("Your Message is Empty. Please Write Something!");
			} else {
				return message;
			}
		}
	}

	public void mainMenu() {
		Scanner scanner = new Scanner(System.in);
		String[] mainMenuOptions = { "Send Messages", "View Messages"};
		int choice;

		do {
			printMenu("Main Menu", mainMenuOptions);

			choice = choiceValidation();

			switch (choice) {
			case 1:
				sendMessageMenu();
				break;
			case 2:
				viewMessages();
				break;
			case 3:
				System.out.println("Goodbye!");
				break;
			default:	
				System.out.println("Invalid choice. Please try again.");
				break;
			}
		} while (choice != 4);

	}

	private void sendMessageMenu() {
		int choice = 0;
		String[] mainMenuOptions = { "From PC 1", "From PC 2", "From PC 3" };

		while (choice != 4) {
			printMenu("Send Message From", mainMenuOptions);

			choice = choiceValidation();

			switch (choice) {
			case 1:
				String[] subMenuOptions_1 = { "Send to PC 2", "Send to PC 3" };
				Scanner sub_scanner_1 = new Scanner(System.in);
				int sub_choice_1 = 0;
				while (sub_choice_1 != 3) {
					printMenu("Send Message To", subMenuOptions_1);

					sub_choice_1 = choiceValidation();

					switch (sub_choice_1) {
					case 1:
						String message = messageValidation();
						this.a.sendMessage(b, message);
						break;
					case 2:
						String message2 = messageValidation();
						this.a.sendMessage(c, message2);
						break;
					case 3:
						break;
					default:
						System.out.print("Invalid input. Try again!");
						break;
					}
				}
				break;
			case 2:
				Scanner sub_scanner_2 = new Scanner(System.in);
				int sub_choice_2 = 0;
				String[] subMenuOptions_2 = { "Send to PC 1", "Send to PC 3" };
				while (sub_choice_2 != 3) {

					printMenu("Send Message To", subMenuOptions_2);

					sub_choice_2 = choiceValidation();

					switch (sub_choice_2) {
					case 1:
						String message3 = messageValidation();
						this.b.sendMessage(a, message3);
						break;
					case 2:
						String message4 = messageValidation();
						this.b.sendMessage(c, message4);
						break;
					case 3:
						break;
					default:
						System.out.print("Invalid input. Try again!");
						break;
					}
				}
				break;
			case 3:
				Scanner sub_scanner_3 = new Scanner(System.in);
				int sub_choice_3;
				String[] subMenuOptions_3 = { "Send to PC 1", "Send to PC 2" };
				do {
					printMenu("Send Message To", subMenuOptions_3);

					sub_choice_3 = choiceValidation();

					switch (sub_choice_3) {
					case 1:
						String message = messageValidation();
						this.c.sendMessage(a, message);
						break;
					case 2:
						String message2 = messageValidation();
						this.c.sendMessage(b, message2);
						break;
					case 3:
						break;
					default:
						System.out.print("Invalid input. Try again!");
						break;
					}
				} while (sub_choice_3 != 3);
				break;
			case 4:
				System.out.println("Goodbye!");
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
				break;
			}
		}
	}

	private void viewMessages() {

		int choice;
		String[] subMenuOptions = { "View PC 1", "View PC 2", "View PC 3" };
		do {
			printMenu("View Message", subMenuOptions);

			choice = choiceValidation();

			switch (choice) {
			case 1:
				this.a.displayReceiveQueue();
				this.a.receiveMessage();
				break;
			case 2:
				this.b.displayReceiveQueue();
				this.b.receiveMessage();
				break;
			case 3:
				this.c.displayReceiveQueue();
				this.c.receiveMessage();
				break;
			case 4:
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
				break;
			}
		} while (choice != 4);
	}

}
