/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package character;

import item.Item;
import dungeon.Room;
import java.util.ArrayList;

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
    private Room currentRoom;
    ArrayList inventory = new ArrayList();

    public Player(String name, int hitPoints, Item weapon, Item amour, int gold, Room room) {
        this.name = name;
        this.hitPoints = hitPoints;
        this.weapon = weapon;
        this.amour = amour;
        this.gold = gold;
        this.currentRoom = room;
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

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }
    
}
