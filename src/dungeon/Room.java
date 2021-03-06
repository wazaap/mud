/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeon;

import character.Monster;
import item.Item;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Mads & Thomas
 */
public class Room implements Serializable {

    private static final long serialVersionUID = 19981017L;
    private int id;
    private int north;
    private int south;
    private int east;
    private int west;
    private String title;
    private String description;
    private ArrayList<Monster> monsters = new ArrayList<>();
    private ArrayList<Item> chest = new ArrayList<>();

    public Room(int id, int north, int south, int east, int west, String title, String description) {
        this.id = id;
        this.north = north;
        this.south = south;
        this.east = east;
        this.west = west;
        this.title = title;
        this.description = description;
    }

    public void addMonster(Monster monster) {
        this.monsters.add(monster);
    }

    public void clearlMonsters() {
        monsters.clear();
    }

    /**
     * Returns a string with all directions available.
     *
     * @return String
     */
    public String getAvailableDirections() {
        String res = "You can continue towards:" + System.getProperty("line.separator");
        if (this.north != -1) {
            res += "[north]";
        }
        if (this.south != -1) {
            res += "[south]";
        }
        if (this.west != -1) {
            res += "[west]";
        }
        if (this.east != -1) {
            res += "[east]";
        }
        return res;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNorth() {
        return north;
    }

    public void setNorth(int north) {
        this.north = north;
    }

    public int getSouth() {
        return south;
    }

    public void setSouth(int south) {
        this.south = south;
    }

    public int getWest() {
        return west;
    }

    public void setWest(int west) {
        this.west = west;
    }

    public int getEast() {
        return east;
    }

    public void setEast(int east) {
        this.east = east;
    }

    public ArrayList<Monster> getMonsters() {
        return this.monsters;
    }
    
    /**
     * This method runs through the monsters arraylist and returns all monsters.
     * If the amount of monsters is 0 or less it returns that no monsters are in
     * the room.
     *
     * @return String
     */
    public String getMonstersToString() {
        String res;
        if (!this.monsters.isEmpty()) {
            res = "You see the following monsters:" + System.getProperty("line.separator");
            for (int i = 0; i < monsters.size(); i++) {
                res += this.monsters.get(i).getName() + " - " + this.monsters.get(i).getDescription() + System.getProperty("line.separator");
            }
        } else {
            res = "You see no monsters in this room!" + System.getProperty("line.separator");
        }
        return res;
    }

    public int getAmountOfMonsters() {
        return this.monsters.size();
    }

    public Monster getMonster(int i) {
        return this.monsters.get(i);
    }

    public void removeMonster(int i) {
        this.monsters.remove(i);
    }

    /**
     * Runs trough the chest array. Returns a string with all items in a chest.
     *
     * @return String
     */
    public String getItemsInChest() {
        String res = "";
        if (!chest.isEmpty()) {
            for (int i = 0; i < chest.size(); i++) {
                res += chest.get(i).getName() + System.lineSeparator();
            }
        }
        return res;
    }

    public void addItemToChest(Item item) {
        chest.add(item);
    }

    public int getChestSize() {
        return chest.size();
    }

    public ArrayList<Item> getChest() {
        return chest;
    }
}
