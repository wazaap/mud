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
    private Item slot1;
    private Item slot2;
    private int gold;
    private Room currentRoom;
    ArrayList inventory = new ArrayList();

    public Player(String name, int hitPoints, Item slot1, Item slot2, int gold, Room room) {
        this.name = name;
        this.hitPoints = hitPoints;
        this.slot1 = slot1;
        this.slot2 = slot2;
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
        return slot1;
    }

    public void setWeapon(Item weapon) {
        this.slot1 = weapon;
    }

    public Item getAmour() {
        return slot2;
    }

    public void setAmour(Item amour) {
        this.slot2 = amour;
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

    public Item getGearSlot1() {
        return slot1;
    }

    public Item getGearSlot2() {
        return slot1;
    }
}
