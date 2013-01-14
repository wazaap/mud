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

    public String useItem(int itemNumber) {
        String res = null;
        int gold = 0;
        if (itemNumber > inventory.size() || itemNumber < 0) {
            return "You do not have that item.. ";
        } else {
            Item item = (Item) inventory.get(itemNumber);
            switch (item.getItemType()) {
                case 1:
                    res = null;
                    break;
                case 2:
                    res = null;
                    break;
                case 3:
                    this.hitPoints = this.hitPoints + item.getHealthpoints();
                    res = "You use a " + item.getName() + System.getProperty("line.separator");
                    res += "You now have " + this.getHitPoints() + " healthpoints";
                    inventory.remove(itemNumber);
                    break;
                case 4:
                    for (int i = 0; i < inventory.size(); i++) {
                        item = (Item) inventory.get(i);
                        gold += item.getGold();
                    }
                    res = "You look at all your gold.... You got " + gold;
                    break;
            }
            return res;
        }

    }
}
