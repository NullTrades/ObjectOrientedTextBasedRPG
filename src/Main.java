/*

    Title: Main Class
    Author: Osy Okocha and Simon Huang

*/

/*
    Each class in this project serves an individual role. I started with five classes initially, but branched out as a
    saw different needs in play. We utilized references to integrate the seperate classes without complicating the
    project structure. The specific, distinctively-named, classes exhibit modularity as changes made to an individual
    class are less likely to hurt the project in several areas.
 */

public class Main {

    public static void main(String[] args) {
        Game game = new Game();
        game.startGame();
    }

}