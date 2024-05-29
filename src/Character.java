/*

    Title: Character Interface
    Author: Osy Okocha and Simon Huang

    
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

    boolean solvePuzzle(Puzzle puzzle);

    void useItem(int index);

    List<Item> getInventory();

}