/*

    Title: GameEngine Class
    Author: Osy Okocha and Simon Huang

    
*/

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private Character currentPlayer;
    private List<Encounter> encounters;
    private int currentEncounterIndex;
    // initializing scanner
    private static Scanner scanner = new Scanner(System.in);
    String RESET = "\u001B[0m";
    String RED = "\u001B[31m";
    String GREEN = "\u001B[32m";
    String YELLOW = "\u001B[33m";

    public void startGame() {
        // Initialize game components and start the main game loop
        initializeEncounters();
        startScreen();
        currentPlayer = chooseCharacter();
        GameTimer.wait(1);
        currentEncounterIndex = 0;
        playNextEncounter();
    }

    private void startScreen() {
        System.out.println(YELLOW + "Welcome to the Land of Eldoria!");
        GameTimer.wait(3);
        System.out.println(YELLOW + "In this world, you are destined to face numerous challenges and emerge as a hero.");
        GameTimer.wait(4);
        System.out.println(YELLOW + "Choose your character and embark on an epic journey filled with battles, puzzles, and adventures." + RESET);
        GameTimer.wait(5);
        System.out.println(" ");
    }

    public void endGame() {
        // End the game and - maybe - display the "results" based on player performance
        System.out.println(RED + "Game Over! Better luck next time." + RESET);
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
                    System.out.println(GREEN + "Congratulations! You have completed the game!" + RESET);
                } else {
                    // Add a puzzle after defeating an enemy
                    System.out.println(" ");
                    System.out.println(YELLOW + "You must solve a puzzle to proceed to the next level and restore your health." + RESET);
                    GameTimer.wait(1);
                    TimedAnagramPuzzle puzzle = new TimedAnagramPuzzle("mystery", 10);
                    if (currentPlayer.solvePuzzle(puzzle)) {
                        System.out.println(GREEN + "Puzzle solved!" +RESET + YELLOW + "Your health is restored." + RESET);
                        currentPlayer.setHealth(100);
                    } else {
                        System.out.println(RED + "Failed to solve the puzzle. You remain at your current health." + RESET);
                    }
                    playNextEncounter(); // move onto the next encounter after puzzle
                }
            } else {
                GameTimer.wait(1);
                endGame();
            }
        } else {
            // Player has completed all encounters - game victory
            System.out.println(GREEN + "Congratulations! You have completed the game!" + RESET);
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
                System.out.println("You have chosen the " + YELLOW + "Warrior!");
                return new Warrior("Warrior");
            case 2:
                System.out.println("You have chosen the " + YELLOW + "Mage!");
                return new Mage("Mage");
            case 3:
                System.out.println("You have chosen the " + YELLOW + "Rogue!");
                return new Rogue("Rogue");
            default:
                System.out.println(RED + "Invalid choice. Please try again." + RESET);
                return chooseCharacter();
        }

    }
}
