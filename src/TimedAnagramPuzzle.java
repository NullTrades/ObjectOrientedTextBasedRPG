/*

    Title: Anagram Puzzle Class
    Author: Osy Okocha and Simon Huang

*/

import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class TimedAnagramPuzzle implements Puzzle {
    private final String originalWord; // original word to be unscrambled
    private final String scrambledWord; // scrambled version of the original word
    private final int timeLimitInSeconds; // time limit to solve the puzzle in seconds
    private boolean onTime = true;

    public TimedAnagramPuzzle(String originalWord, int timeLimitInSeconds) {
        this.originalWord = originalWord;
        this.scrambledWord = scramble(originalWord);
        this.timeLimitInSeconds = timeLimitInSeconds;
    }


    @Override
    public void start() {
        // start the timed anagram puzzle
        System.out.println(" ");
        System.out.println("Unscramble the word: " + scrambledWord);
        System.out.println("You have " + timeLimitInSeconds + " seconds to solve the puzzle.");
        System.out.println(" ");
        // additional logic to start the timer
    }

    @Override
    public boolean solve() {
        Scanner scanner = new Scanner(System.in);
        ExecutorService executor = Executors.newSingleThreadExecutor(); // create an ExecutorService to manage the thread
        Future<String> future = executor.submit(() -> { // submit a task to the executor to get player input
            System.out.println("Enter the solution: ");
            return scanner.nextLine(); // read  and return the player's input
        });

        String playerSolution = ""; // variable to store the player's solution
        try {
            playerSolution = future.get(timeLimitInSeconds, TimeUnit.SECONDS); // wait for the player's input with a timeout
            onTime = true; // set onTime to true if input is received within the time limit
        } catch (TimeoutException e) {
            onTime = false; // Set onTime to false if the time limit is exceeded
            System.out.println("Time's up! You took too long to solve the puzzle.");
            future.cancel(true); // Cancel the task if it times out - to stop a repeating loop error
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace(); // print stack trace for debugging
        } finally {
            executor.shutdownNow(); // shut down executor
        }

        //return ture if the solution is correct and within the time limit
        return playerSolution.equalsIgnoreCase(originalWord) && onTime;
    }

    private String scramble(String word) {
        List<java.lang.Character> letters = word.chars().mapToObj(c -> (char) c).collect(Collectors.toList()); // convert the word to a list of characters
        Collections.shuffle(letters); // shuffle the list of characters
        StringBuilder shuffled = new StringBuilder(); // create a StringBuilder to store the shuffled characters
        for (java.lang.Character letter : letters) { // append each shuffled character to the StringBuilder
            shuffled.append(letter);
        }
        return shuffled.toString(); // return the shuffled word as a String
    }

}
