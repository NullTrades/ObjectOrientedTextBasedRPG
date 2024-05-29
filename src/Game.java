/*

    Title: GameEngine Class
    Author: Osy Okocha and Simon Huang

    
*/

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer; // potentially add timers to slow the game down

public class Game {
    private Character currentPlayer;
    private List<Encounter> encounters;
    private int currentEncounterIndex;
    // initializing scanner
    private static Scanner scanner = new Scanner(System.in);

    public void startGame() {
        // Initialize game components and start the main game loop
        initializeEncounters();
        currentPlayer = chooseCharacter();
        GameTimer.wait(1);
        currentEncounterIndex = 0;
        playNextEncounter();
    }

    public void endGame() {
        // End the game and - maybe - display the "results" based on player performance
        System.out.println("Game Over! Better luck next time.");
    }

    public void initializeEncounters() {
        // Define the sequence of encounters for the game
        encounters = new ArrayList<>();
        // Add encounters to the list
        encounters.add(new Encounter(new Enemy("Ogre", 100, 2)));
        encounters.add(new Encounter(new Enemy("Goblin", 80, 1.5)));
        encounters.add(new Encounter(new Enemy("Dragon", 150, 3)));
        encounters.add(new Encounter(new Enemy("Troll", 120, 2.5)));
        encounters.add(new Encounter(new Enemy("Vampire", 110, 2.2)));
        // creating instances of enemies
        //maybe one final boss?
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
                } else {
                    // Add a puzzle after defeating an enemy
                    System.out.println(" ");
                    System.out.println("You must solve a puzzle to proceed to the next level and restore your health.");
                    GameTimer.wait(1);
                    TimedAnagramPuzzle puzzle = new TimedAnagramPuzzle("mystery", 10);
                    if (currentPlayer.solvePuzzle(puzzle)) {
                        System.out.println("Puzzle solved! Your health is restored.");
                        currentPlayer.setHealth(100);
                    } else {
                        System.out.println("Failed to solve the puzzle. You remain at your current health.");
                    }
                    playNextEncounter(); // move onto the next encounter after puzzle
                }
            } else {
                GameTimer.wait(1);
                endGame();
            }
        } else {
            // Player has completed all encounters - game victory
            System.out.println("Congratulations! You have completed the game!");
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
