/*

    Title: Mage Class
    Author: Osy Okocha
    Date: 
    
*/

import java.util.ArrayList;
import java.util.List;

public class Mage implements Character {
    private List<Item> inventory;
    private String name;
    private int health;
    private double attackMultiplier = 1.0; // attack multiplier for a mage
    private double potionMultiplier = 1.5; // potion multiplier for a mage
    private int luck = 1; // luck value for a mage -  average

    public Mage(String name) {
        this.name = name;
        this.health = 100; // default health for a mage
        this.inventory = new ArrayList<>();
        initializeInventory();
    }

    private void initializeInventory() {
        inventory.add(new Potion("Health Potion", "Restores 20 health", (int)(20*potionMultiplier), 0, 0)); // turn a double into an int by multiplying by potionMultiplier and casting to int
        inventory.add(new Potion("Attack Potion", "Boosts attack by 1.5", 0, 1.5*potionMultiplier, 0));
        inventory.add(new Potion("Luck Potion", "Increases luck by 2", 0, 0, (int)(2*potionMultiplier)));
    }

    @Override
    public void useItem(int index) {
        if (index >= 0 && index < inventory.size()) {
            Item item = inventory.get(index);
            System.out.println(name + " uses " + item.getName() + " with enhanced effects.");
            item.use(this);
            inventory.remove(index);
        } else {
            System.out.println("Invalid item index.");
        }
    }

    @Override
    public List<Item> getInventory() {
        return inventory;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public double getAttackMultiplier() {
        return attackMultiplier;
    }

    @Override
    public void setAttackMultiplier(double multiplier) {
        this.attackMultiplier = multiplier; // Multiply current attack multiplier
    }

    @Override
    public double getPotionMultiplier() {
        return potionMultiplier;
    }

    @Override
    public int getLuck() {
        return luck;
    }

    @Override
    public void setLuck(int luck) {
        this.luck += luck; // Increase luck
    }

    @Override
    public void attack(Enemy enemy) {
        // mage attack logic - mages use magical attacks so there will be a higher complexity but greater extremes for the results
        int baseDamage = DiceUtility.rollDice(20); // roll a 20-sided dice
        int damage = (int) (baseDamage * attackMultiplier); // calculate damage
        System.out.println("Mage casts a spell dealing " + damage + " damage!");
        enemy.receiveDamage(damage);

    }

    @Override
    public boolean solvePuzzle(Puzzle puzzle) {
        // warrior puzzle logic
        System.out.println(name + " starts solving the puzzle...");
        puzzle.start();
        return puzzle.solve();
    }
}
