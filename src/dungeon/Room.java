/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeon;

import character.Monster;
import character.Player;
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
    private int availableExits;
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
        monsters.add(monster);
    }

    public String availableDirections() {
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

    public String getMonsters() {
        String res;
        if (monsters.size() > 0) {
            res = "You see the following monsters:"  + System.getProperty("line.separator");
            for (int i = 0; i < monsters.size(); i++) {
                res += monsters.get(i).getName()  + System.getProperty("line.separator");
            }
        } else {
            res = "You see no monsters in this room!" + System.getProperty("line.separator");
        }
        return res;
    }

    public int amountOfMonsters() {
        return monsters.size();
    }

    public Monster getMonster(int i) {
        Monster thisMonster = monsters.get(i);
        return thisMonster;
    }

    public void removeFirstMonster() {
        monsters.remove(0);
    }
    
    public String getItemsInChest() {
        String res = null;
        if (chest.size() > 0){
            for (int i = 0; i < chest.size(); i++){
                res = chest.get(i).getName() + System.lineSeparator();
            }
        }
        return res;
    }
    
    public void addItmeToChest(Item item){
        chest.add(item);
    }
    
    public int chestSize(){
        return chest.size();
    }
    
    public ArrayList<Item> getChest(){
        return chest;
    }
}
