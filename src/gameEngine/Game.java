/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameEngine;

import character.Player;
import dungeon.Dungeon;
import dungeon.Room;
import fileio.FileIO;
import item.Item;

/**
 *
 * @author Thomas
 */
public class Game {

    private Item sword;
    private Item shield;
    private Player player;
    private Dungeon dungeon;

    public Game() {
        dungeon = FileIO.readDungeon();
        sword = new Item();
        shield = new Item();
        player = new Player("Mads", 10, sword, shield, 200, dungeon.getRoom(5));
    }

    public void move(String direction) {
        Room currentRoom = player.getCurrentRoom();
        int moveTo = 0;
        switch (direction) {
            case "north":
                moveTo = currentRoom.getNorth();
                break;
            case "south":
                moveTo = currentRoom.getSouth();
                break;
            case "east":
                moveTo = currentRoom.getEast();
                break;
            case "west":
                moveTo = currentRoom.getWest();
                break;
        }
        if (moveTo > 0) {
            for (int i = 0; i < dungeon.size(); i++) {
                if (dungeon.getRoom(i).getId() == moveTo) {
                    player.setCurrentRoom(dungeon.getRoom(i));
                }
            }
        }

    }
}
