/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeon;

import character.Monster;
import java.util.ArrayList;

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
    }

    public String availableDirections() {
        String res = "Available directions: ";
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
}
