/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeon;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Thomas
 */
public class Dungeon implements Serializable {

    private static final long serialVersionUID = 19981017L;
    private ArrayList<Room> rooms = new ArrayList<>();
    private int startRoom;
    private int endRoom;
    private String description;

    public int getStartRoom() {
        return startRoom;
    }

    public void setStartRoom(int startRoom) {
        this.startRoom = startRoom;
    }

    public int getEndRoom() {
        return endRoom;
    }

    public void setEndRoom(int endRoom) {
        this.endRoom = endRoom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Dungeon() {
    }

    public void addRoom(Room newRoom) {
        rooms.add(newRoom);
    }

    public int size() {
        return rooms.size();
    }

    public Room getRoom(int id) {
        return rooms.get(id);
    }
}
