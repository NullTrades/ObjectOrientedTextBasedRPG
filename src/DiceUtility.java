/*

    Title:
    Author: Osy Okocha
    Date: 
    
*/

public class DiceUtility {

    /***
     * resource: javadoc comment tags - "https://ioflood.com/blog/javadoc-comments/#:~:text=Javadoc%20comments%20in%20Java%20are,and%20to%20your%20future%20self"
     * This method simulates the rolling of a dice.
     * @param sides the number of sides on the dice
     * @return the result of the dice roll
     */
    public static int rollDice(int sides) {
        return (int) (Math.random() * sides) + 1;
    }

}
