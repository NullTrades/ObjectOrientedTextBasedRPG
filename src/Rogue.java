/*

    Title: Rogue Class
    Author: Osy Okocha
    Date: 
    
*/

import java.util.ArrayList;
import java.util.List;

public class Rogue implements Character {
    private List<Item> inventory;
    private String name;
    private int health;
    private double attackMultiplier = 1.0; // Default attack multiplier
    private int luck = 5; // Default luck

    public Rogue(String name) {
        this.name = name;
        this.health = 100; // Default health
        this.inventory = new ArrayList<>();
        initializeInventory();
    }

    private void initializeInventory() {
        inventory.add(new Potion("Health Potion", "Restores 20 health", 20, 0, 0));
        inventory.add(new Potion("Attack Potion", "Boosts attack by 1.5", 0, 1.5, 0));
        inventory.add(new Potion("Luck Potion", "Increases luck by 2", 0, 0, 2));
    }

    /***
     * Uses an item from the inventory based on the specified index.
     * If the index is invalid, prompts the user to retry.
     * @param index The index of the item in the inventory.
     */
    @Override
    public void useItem(int index) {
        if (index >= 0 && index < inventory.size()) {
            Item item = inventory.get(index);
            System.out.println(name + " uses " + item.getName());
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
        this.attackMultiplier = multiplier;
    }

    @Override
    public double getPotionMultiplier() {
        return 1.0;
    }

    @Override
    public int getLuck() {
        return luck;
    }

    @Override
    public void setLuck(int luck) {
        this.luck = luck;
    }

    @Override
    public void attack(Enemy enemy) {
        int baseDamage = DiceUtility.rollDice(20);
        int damage = (int) (baseDamage * attackMultiplier);
        System.out.println("Rogue attacks for " + damage + " damage!");
        enemy.receiveDamage(damage);
    }


    @Override
    public boolean solvePuzzle(Puzzle puzzle) {
        // rogue puzzle solving logic
        // make rogue puzzle solving much easier and reliant on luck
        System.out.println(name + " attempts to solve the puzzle...");
        return DiceUtility.rollDice(10) <= luck; // return true if dice roll is less than or equal to luck value
    }
}

