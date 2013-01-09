/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeon;

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

    public Room(int id, int north, int south, int east, int west, String title, String description) {
        this.id = id;
        this.north = north;
        this.south = south;
        this.east = east;
        this.west = west;
        this.title = title;
        this.description = description;
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
