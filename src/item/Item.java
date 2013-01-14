package item;

import java.io.Serializable;

/**
 * Describes an item with a name and a description. New items should extends
 * this class, and overwrite the values which the item should improve.
 *
 * @author Mads
 */
public class Item implements Serializable {

    private String name;
    private String description;
    //Item type: 1. Weapon 2.Amour 3.Healing 4.Valuable
    private int itemType;
    // Attributes
    private int healthpoints;
    private int damage;
    private int gold;
    private int shield;

    public Item(String name, String description, int itemType, int healthpoints, int damage, int gold, int amour) {
        this.name = name;
        this.description = description;
        this.itemType = itemType;
        this.healthpoints = healthpoints;
        this.damage = damage;
        this.gold = gold;
        this.shield = amour;
    }

    @Override
    public String toString() {
        String res = "";
        if (healthpoints > 0) {
            res += this.name + ": " + this.description + " (Healthpoints: " + this.healthpoints +")";
        }
        if (damage > 0) {
            res += this.name + ": " + this.description + " (Damage: " + this.damage +")";
        }
        if (gold > 0) {
            res += this.name + ": " + this.description + " (Value: " + this.gold +")";
        }
        if (shield > 0) {
            res += this.name + ": " + this.description + " (Amour points: " + this.damage +")";
        }

        return res;
    }

    public int getShield() {
        return shield;
    }

    public void setShield(int shield) {
        this.shield = shield;
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

    public int getItemType() {
        return itemType;
    }
    
}
