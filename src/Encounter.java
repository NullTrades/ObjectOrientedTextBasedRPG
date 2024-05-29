/*

    Title: Encounter Class
    Author: Osy Okocha
    Date: 
    
*/

import java.util.Scanner;

public class Encounter {
    private Enemy enemy;

    public Encounter(Enemy enemy) {
        this.enemy = enemy;
    }

    public void start(Character character) {
        System.out.println("You have encountered " + enemy.getName());
        System.out.println("You have " + character.getHealth() + " health.");
        System.out.println("The " + enemy.getName() + " has " + enemy.getHealth() + " health.");
        Timer.wait(1); // call a 2 second "wait" from the Timer class

    }

    public boolean execute(Character character) {
        // Simulate a simple battle system - character and enemy take turns attacking each other until one of them loses all health
        Scanner scanner = new Scanner(System.in);
        while (character.getHealth() > 0 && enemy.getHealth() > 0) {
            System.out.println("Choose your action: \n1. Attack\n2. Use Item"); // Player Input
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    character.attack(enemy);
                    Timer.wait(1); // call a 1 second "wait" from the Timer class
                    System.out.println(enemy.getName() + " now has " + enemy.getHealth() + " health remaining.");
                    Timer.wait(1); // call a 1 second "wait" from the Timer class
                    break;
                case 2:
                    // Display inventory
                    System.out.println("Inventory:");
                    for (int i = 0; i < character.getInventory().size(); i++) {
                        System.out.println(i + 1 + ". " + character.getInventory().get(i).getName());
                    }
                    System.out.println("Choose an item to use:");
                    int itemChoice = scanner.nextInt();
                    character.useItem(itemChoice - 1);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    continue;
            }

            //Battle logic
            if (enemy.getHealth() <= 0) {
                System.out.println("You have defeated the " + enemy.getName() + "!");
                return true;
            }

            //Enemy's turn
            enemy.attack(character);
            if (character.getHealth() <= 0) {
                System.out.println("You have been defeated by the " + enemy.getName() + "!");
                return false;
            } else {
                System.out.println("You now have " + character.getHealth() + " health remaining.");
            }
            Timer.wait(1); // call a 1 second "wait" from the Timer class
        }
        return false;
    }

    public void end(Character character) {
        if (character.getHealth() > 0) {
            System.out.println("Congratulations! You have won the battle. " +
                    "\nYou have " + character.getHealth() + " health remaining.");
            Timer.wait(2);
        } else {
            System.out.println("You have been defeated!");
        }
    }

}
// add timers everywhere