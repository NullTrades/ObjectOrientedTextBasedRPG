/*

    Title: Character Interface
    Author: Osy Okocha
    Date: 
    
*/

import java.util.List;

public interface Character {
    String getName();

    int getHealth();

    void setHealth(int health);

    double getAttackMultiplier();
    void setAttackMultiplier(double attackMultiplier);

    double getPotionMultiplier();

    int getLuck();
    void setLuck(int luck);

    void attack(Enemy enemy);

//    void useItem(Item item);

    boolean solvePuzzle(Puzzle puzzle);

    void useItem(int index);

    List<Item> getInventory();

    // ... additional methods and attributes
}