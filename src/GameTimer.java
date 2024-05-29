/*

    Title: GameTimer Class
    Author: Osy Okocha and Simon Huang

    
*/

public class GameTimer {
    // wait function that takes in a time in seconds and "pauses" the console for that period of time
    public static void wait(int seconds) {
        try {
            Thread.sleep(seconds * 1000L); // convert seconds to milliseconds - long data type
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }


}
