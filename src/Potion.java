/*

    Title: Potion Class
    Author: Osy Okocha and Simon Huang

    
*/

public class Potion extends Item {
    private final int healthBoost; // Amount of health to restore
    private final double attackBoost; // Amount to boost attack (as a multiplier)
    private final int luckBoost; // Amount to boost luck
    String RESET = "\u001B[0m";
    String RED = "\u001B[31m";
    String GREEN = "\u001B[32m";
    String YELLOW = "\u001B[33m";

    public Potion(String itemName, String effect, int healthBoost, double attackBoost, int luckBoost) {
        super(itemName, effect);
        this.healthBoost = healthBoost;
        this.attackBoost = attackBoost;
        this.luckBoost = luckBoost;
    }

    @Override
    public void use(Character character) {
        System.out.println(" ");
        GameTimer.wait(1);
        if (healthBoost > 0) {
            character.setHealth(character.getHealth() + healthBoost);
            System.out.println(YELLOW + character.getName() + " drinks " + getName() + ", restoring " + healthBoost + " health." + RESET);
        }
        if (attackBoost > 0) {
            character.setAttackMultiplier(character.getAttackMultiplier() + attackBoost);
            System.out.println(YELLOW + character.getName() + " drinks " + getName() + ", boosting attack by a factor of " + attackBoost + "." + RESET);
        }
        if (luckBoost > 0) {
            character.setLuck(character.getLuck() + luckBoost);
            System.out.println(YELLOW + character.getName() + " drinks " + getName() + ", increasing luck by " + luckBoost + "." + RESET);
        }
    }


}
