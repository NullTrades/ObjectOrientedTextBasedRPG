/*

    Title: Enemy Class
    Author: Osy Okocha and Simon Huang

    
*/

public class Enemy {
    private String name;
    private int health; // default health for an enemy
    private double attackMultiplier; // attack multiplier for an enemy

    public Enemy(String name, int health, double attackMultiplier) {
        this.name = name;
        this.health = health;
        this.attackMultiplier = attackMultiplier;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public double getAttackMultiplier() {
        return attackMultiplier;
    }

    // Setters
    public void setHealth(int health) {
        this.health = health;
    }

    // Attack method
    public void attack(Character character) {
        int damage = (int) (Math.random() * 10 * attackMultiplier);
        System.out.println(name + " attacks " + character.getName() + " with " + damage + " damage.");
        character.setHealth(character.getHealth() - damage);
    }

    // Method to handle receiving damage
    public void receiveDamage(int damage) {
        this.health -= damage;
    }
}



