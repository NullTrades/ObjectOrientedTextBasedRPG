/*

    Title:
    Author: Osy Okocha
    Date: 
    
*/

import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TimedAnagramPuzzle implements Puzzle{
    private final String originalWord; // original word to be unscrambled
    private final String scrambledWord; // scrambled version of the original word
    private final int timeLimitInSeconds; // time limit to solve the puzzle in seconds

    public TimedAnagramPuzzle(String originalWord, int timeLimitInSeconds) {
        this.originalWord = originalWord;
        this.scrambledWord = scramble(originalWord);
        this.timeLimitInSeconds = timeLimitInSeconds;
    }


    @Override
    public void start() {
        // start the timed anagram puzzle
        System.out.println("Unscramble the word: " + scrambledWord);
        System.out.println("You have " + timeLimitInSeconds + " seconds to solve the puzzle.");
        // additional logic to start the timer
    }

    @Override
    public boolean solve() {
        Scanner scanner = new Scanner(System.in);
        double startTime = System.currentTimeMillis(); // get the start time
        System.out.println("Enter the solution: ");
        String playerSolution = scanner.nextLine();
        double endTime = System.currentTimeMillis(); // get the end time
        if ((endTime - startTime) / 1000 > timeLimitInSeconds) {
            System.out.println("Time's up! You took too long to solve the puzzle.");
            return false;
        }
        return playerSolution.equalsIgnoreCase(originalWord);

    }

    private String scramble(String word) {
        List<java.lang.Character> letters = word.chars().mapToObj(c -> (char)c).collect(Collectors.toList()); // convert the word to a list of characters
        Collections.shuffle(letters); // shuffle the list of characters
        StringBuilder shuffled = new StringBuilder(); // create a StringBuilder to store the shuffled characters
        for (java.lang.Character letter : letters) { // append each shuffled character to the StringBuilder
            shuffled.append(letter);
        }
        return shuffled.toString(); // return the shuffled word as a String
    }

}
