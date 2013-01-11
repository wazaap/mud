/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeon;

import character.Monster;
import fileio.FileIO;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Mads
 */
public class Room {

    private int id;
    private int north;
    private int south;
    private int east;
    private int west;
    private String title;
    private String description;
    private ArrayList<Monster> monsters = new ArrayList();

    public Room(int id, int north, int south, int east, int west, String title, String description) {
        this.id = id;
        this.north = north;
        this.south = south;
        this.east = east;
        this.west = west;
        this.title = title;
        this.description = description;

        // Create random monsters
        Random gen = new Random();
        int randomNumber = gen.nextInt(5);
        for (int i = 0; i < randomNumber; i++) {
            monsters.add(FileIO.readMonster());
        }
    }

    public String availableDirections() {
        String res = "You can continue towards: ";
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
            res = "You see the following monsters:\n";
            for (int i = 0; i < monsters.size(); i++) {
                res += monsters.get(i).getName() + "\n";
            }
        } else {
            res = "You see no monsters in this room!";
        }
        return res;
    }
    
    public int amountOfMonsters() {
        return monsters.size();        
    }
}
