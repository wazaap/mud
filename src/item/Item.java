package item;

/**
 * Describes an item with a name and a description. New items should extends
 * this class, and overwrite the values which the item should improve.
 *
 * @author Mads
 */
public class Item {

    private String name;
    private String description;
    // Attributes
    private int healthpoints;
    private int damage;
    private int gold;
    private int amour;

    public Item(String name, String description, int healthpoints, int damage, int gold, int amour) {
        this.name = name;
        this.description = description;
        this.healthpoints = healthpoints;
        this.damage = damage;
        this.gold = gold;
        this.amour = amour;
    }

    
    
    public int getAmour() {
        return amour;
    }

    public void setAmour(int amour) {
        this.amour = amour;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getHealthpoints() {
        return healthpoints;
    }

    public void setHealthpoints(int healthpoints) {
        this.healthpoints = healthpoints;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }
}
