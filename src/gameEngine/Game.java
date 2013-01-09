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
        player = new Player("Mads", 10, sword, shield, 200);
    }

    public void move(String direction) {
       Room currenRoom = player.getCurrentRoom();
       int moveTo = 0;
       switch (direction) {
           case "north": moveTo = currenRoom.getNorth();
               break;
           case "south": moveTo = currenRoom.getSouth();
               break;
           case "east": moveTo = currenRoom.getEast();
               break;
           case "west": moveTo = currenRoom.getWest();
               break;
       }
       if (moveTo > 0){
           player.setCurrentRoom();
       }
       
    }
}
