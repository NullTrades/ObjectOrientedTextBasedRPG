/*

    Title: Warrior Class
    Author: Osy Okocha and Simon Huang

    
*/

import java.util.ArrayList;
import java.util.List;

public class Warrior implements Character {
    private List<Item> inventory;
    private String name;
    private int health;
    private double attackMultiplier = 1.5; // attack multiplier for a warrior
    private double potionMultiplier = 1.0; // potion multiplier for a warrior
    private int luck = 1; // luck value for a warrior

    public Warrior(String name) {
        this.name = name;
        this.health = 100; // default health for a warrior
        this.inventory = new ArrayList<>();
        initializeInventory();
    }

    private void initializeInventory() {
        inventory.add(new Potion("Health Potion", "Restores 20 health", 20, 0, 0));
        inventory.add(new Potion("Attack Potion", "Boosts attack by 1.5", 0, (1.5), 0));
        inventory.add(new Potion("Luck Potion", "Increases luck by 2", 0, 0, 2));
    }

    @Override
    public void useItem(int index) {
        if (index >= 0 && index < inventory.size()) {
            Item item = inventory.get(index);
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
        // warrior attack logic
        int diceRoll = DiceUtility.rollDice(20); // roll a 10-sided dice
        int damage = (int) (diceRoll * attackMultiplier); // calculate damage
        System.out.println("Warrior attacks for " + damage + " damage!");
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
