/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package character;

import dungeon.Room;
import item.Item;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Thomas
 */
public class Player implements Serializable {
    private static final long serialVersionUID = 19981017L;

    private String name;
    private int hitPoints;
    private Item slot1;
    private Item slot2;
    private int gold;
    private Room currentRoom;
    ArrayList<Item> inventory = new ArrayList<>();

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

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<Item> inventory) {
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
        return slot2;
    }

    public String useItem(int itemNumber) {
        String res = null;

        Item item;
        item = inventory.get(itemNumber);
        switch (item.getItemType()) {
            case 3:
                this.hitPoints = this.hitPoints + item.getHealthpoints();
                res = "You use a " + item.getName() + System.getProperty("line.separator");
                res += "You now have " + this.getHitPoints() + " healthpoints";
                inventory.remove(itemNumber);
                break;
            case 4:
                gold = 0;
                for (int i = 0; i < inventory.size(); i++) {
                    item = inventory.get(i);
                    gold += item.getGold();
                }
                res = "You look at all your valuables.... They are worth " + gold + " goldpieces.";
                break;
        }
        return res;
    }

    public String equip(int itemNumber, int slotNumber) {
        String res;
        switch (slotNumber) {
            case 1:
                res = "You put your " + this.slot1.getName() + " in your backpack." + System.lineSeparator();
                inventory.add(this.slot1);
                this.slot1 = inventory.get(itemNumber);
                inventory.remove(itemNumber);
                res += "and you equip a " + this.slot1.getName() + " in slot 1" + System.lineSeparator();
                break;
            case 2:
                res = "You put your " + this.slot2.getName() + " in your backpack." + System.lineSeparator();
                inventory.add(this.slot2);
                this.slot2 = inventory.get(itemNumber);
                inventory.remove(itemNumber);
                res += "and you equip a " + this.slot2.getName() + " in slot 2" + System.lineSeparator();
                break;
            default :
                res = "That slot does not exist!";
        }
        return res;
    }
}
