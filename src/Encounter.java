/*

    Title: Encounter Class
    Author: Osy Okocha and Simon Huang

*/


/* Osy - Composition
    This class manages the interactions between the user-driven character and the "pc" enemy in the RPG. It handles the
    battle sequence, initiating the encounter (with dialogue), processing player actions (attacking or using items), and
    managing enemy counterattacks. This class uses composition by incorporating instances of other classes.
    - References to the Enemy object (e.g. - enemy.getHealth() )
    - Interactions with the Character object (e.g. - character.attack(enemy) )
    - Utilizing the GameTimer class to create pauses, simulating delays, and increasing the narrative feel of the RPG
    These integrations allow the Encounter class to manage battle sequences effectively while maintaining modular code.
 */

import java.util.Scanner;

public class Encounter {
    String RESET = "\u001B[0m";
    String RED = "\u001B[31m";
    String GREEN = "\u001B[32m";
    String YELLOW = "\u001B[33m";

    private Enemy enemy;

    public Encounter(Enemy enemy) {
        this.enemy = enemy;
    }

    public void start(Character character) {
        GameTimer.wait(1);
        System.out.println(" ");
        System.out.println(YELLOW + "You have encountered " + enemy.getName() + "!" + RESET);
        System.out.println("You have " + character.getHealth() + " health.");
        System.out.println("The " + enemy.getName() + " has " + enemy.getHealth() + " health.");
        System.out.println(" ");
        GameTimer.wait(1); // call a 2 second "wait" from the Timer class

    }

    public boolean execute(Character character) {
        // Simulate a simple battle system - character and enemy take turns attacking each other until one of them loses all health
        Scanner scanner = new Scanner(System.in);
        while (character.getHealth() > 0 && enemy.getHealth() > 0) {
            if (character.getInventory().isEmpty()) {
                System.out.println("Choose your action: \n1. Attack"); // Player Input
            } else {
                System.out.println("Choose your action: \n1. Attack\n2. Use Item");}
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    character.attack(enemy);
                    GameTimer.wait(1); // call a 1 second "wait" from the Timer class
                    if(enemy.getHealth() > 0) {
                        System.out.println(enemy.getName() + " now has " + YELLOW + enemy.getHealth() + RESET + " health remaining.");
                    } else {
                        break;}
                    GameTimer.wait(1); // call a 1 second "wait" from the Timer class
                    System.out.println(" ");
                    break;
                case 2:
                    // Display inventory
                    System.out.println("Inventory:");
                    for (int i = 0; i < character.getInventory().size(); i++) {
                        System.out.println(i + 1 + ". " + character.getInventory().get(i).getName());
                    }
                    System.out.println("Choose an item to use:");
                    int itemChoice = scanner.nextInt();
                    System.out.println(" ");
                    character.useItem(itemChoice - 1);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    continue;
            }

            //Battle logic
            if (enemy.getHealth() <= 0) {
                System.out.println(YELLOW + "You have defeated the " + enemy.getName() + "!" + RESET);
                System.out.println(" ");
                GameTimer.wait(1);
                return true;
            }

            //Enemy's turn
            enemy.attack(character);
            if (character.getHealth() <= 0) {
                GameTimer.wait(1);
                System.out.println(RED + "You have been defeated by the " + enemy.getName() + "!" + RESET);
                return false;
            } else {
                System.out.println("You now have " + GREEN + character.getHealth() + RESET + " health remaining.");
                System.out.println(" ");
            }
            GameTimer.wait(1); // call a 1 second "wait" from the Timer class
        }
        return false;
    }

    public void end(Character character) {
        if (character.getHealth() > 0) {
            System.out.println(GREEN + "Congratulations! You have won the battle. " +
                    "\nYou have " + character.getHealth() + " health remaining." + RESET);
            System.out.println(" ");
            GameTimer.wait(2);
        } else {
            GameTimer.wait(1);
            System.out.println(RED + "You have been defeated!" + RESET);
        }
    }

}
// maybe add timers everywhere?