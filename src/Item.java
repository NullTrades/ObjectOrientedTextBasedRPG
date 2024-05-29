/*

    Title: Item Class
    Author: Osy Okocha
    Date: 
    
*/

public abstract class Item { // abstract - class that cannot be instantiated, but can be subclassed.
    private final String name;
    private final String effect;

    public Item(String itemName, String effect) {
        this.name = itemName;
        this.effect = effect;
    }

    public String getName() {
        return name;
    }

    public String getEffect() {
        return effect;
    }
    // abstract method that will be implemented in the subclasses
    public abstract void use(Character Character);

}


