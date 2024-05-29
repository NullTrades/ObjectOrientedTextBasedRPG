/*

    Title: GameEngine Class
    Author: Osy Okocha
    Date: 
    
*/
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;

public class Game {
    private Character currentPlayer;
    private List<Encounter> encounters;
    private int currentEncounterIndex;
    // initialize scanner
    private Scanner scanner = new Scanner(System.in);

    public void startGame() {
        // Initialize game components and start the main game loop
        initializeEncounters();
        currentPlayer = chooseCharacter();
        currentEncounterIndex = 0;
        playNextEncounter();
    }

    public void endGame() {
        // End the game and display the "results" based on player performance
        System.out.println("Game Over! Better luck next time.");
    }

    public void initializeEncounters() {
        // Define the sequence of encounters for your game
        encounters = new ArrayList<>();
        // Add encounters to the list
        encounters.add(new Encounter(new Enemy("Ogre", 100, 2))); // creating instances of enemies
    }

    public void playNextEncounter() {
        if (currentEncounterIndex < encounters.size()) {
            Encounter currentEncounter = encounters.get(currentEncounterIndex);
            currentEncounter.start(currentPlayer); // Start the encounter
            boolean playerWon = currentEncounter.execute(currentPlayer); // Execute the encounter
            if (playerWon) {
                currentEncounter.end(currentPlayer); // End the encounter if the player wins
                currentEncounterIndex++; // Move to the next encounter
                if (currentEncounterIndex == encounters.size()) {
                    // Player has completed all encounters - game victory
                    System.out.println("Congratulations! You have completed the game!");
                    // Handle game ending or victory conditions
                }
            } else {
                // Player lost the encounter - game over
                System.out.println("Game Over! You have been defeated.");
                // Handle game ending or failure conditions
            }
        } else {
            // Player has completed all encounters - game victory
            System.out.println("Congratulations! You have completed the game!");
            // Handle game ending or victory conditions
        }
    }



    private Character chooseCharacter() {
        // Display character selection menu and return the selected character
        System.out.println("Choose your character:");
        System.out.println("1. Warrior");
        System.out.println("2. Mage");
        System.out.println("3. Rogue");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                System.out.println("You have chosen the Warrior!");
                return new Warrior("Warrior");
            case 2:
                System.out.println("You have chosen the Mage!");
                return new Mage("Mage");
            case 3:
                System.out.println("You have chosen the Rogue!");
                return new Rogue("Rogue");
            default:
                System.out.println("Invalid choice. Please try again.");
                return chooseCharacter();
        }
    }

}
