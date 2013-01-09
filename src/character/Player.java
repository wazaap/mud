/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package character;

import java.util.ArrayList;
import item.Item;
/**
 *
 * @author Thomas
 */
public class Player {
    private String name;
    private int hitPoints;
    private Item weapon;
    private Item amour;
    private int gold;
    ArrayList inventory = new ArrayList();

    public Player(String name, int hitPoints, Item weapon, Item amour, int gold) {
        this.name = name;
        this.hitPoints = hitPoints;
        this.weapon = weapon;
        this.amour = amour;
        this.gold = gold;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public Item getWeapon() {
        return weapon;
    }

    public void setWeapon(Item weapon) {
        this.weapon = weapon;
    }

    public Item getAmour() {
        return amour;
    }

    public void setAmour(Item amour) {
        this.amour = amour;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public ArrayList getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList inventory) {
        this.inventory = inventory;
    }
    
    
    
    
    
}
