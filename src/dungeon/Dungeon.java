/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeon;

import java.util.ArrayList;

/**
 *
 * @author Thomas
 */
public class Dungeon {

    private ArrayList<Room> rooms = new ArrayList();

    public Dungeon() {
    }

    public void addRoom(Room newRoom) {
        rooms.add(newRoom);
    }
    
    public int size(){
        return rooms.size();
    }
    
    public Room getRoom(int id){
        return rooms.get(id);
    }
}
