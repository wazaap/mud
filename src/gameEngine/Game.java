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
    private Room currentRoom;

    public Game() {
        dungeon = FileIO.readDungeon();
        sword = new Item();
        shield = new Item();
        player = new Player("Mads", 10, sword, shield, 200, dungeon.getRoom(1));
    }

    public String move(String direction) {
        currentRoom = player.getCurrentRoom();
        String res = "";
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
        while (currentRoom.amountOfMonsters() > 0) {
            res = "In your attempt to escape, a monster attacks you";
            return res;
        } 
        if (moveTo > 0) {
            for (int i = 0; i < dungeon.size(); i++) {
                if (dungeon.getRoom(i).getId() == moveTo) {
                    player.setCurrentRoom(dungeon.getRoom(i));
                    res = "\nYou go towards " + direction + "\n";
                    res += "You enter " + getCurrentRoom().getTitle() + "\n";
                    res += getCurrentRoom().getDescription() + "\n";
                    res += player.getCurrentRoom().availableDirections() + "\n";
                    res += player.getCurrentRoom().getMonsters();
                    return res;
                }
            }
            res = "You walk into the wall.. AND IT HURTS!!";
        }



        return res;

    }

    public Room getCurrentRoom() {
        return player.getCurrentRoom();
    }

    public String help() {
        String res = "You can use the following commands: \n";
        res += "Type \"north\" to go towards the north \n";
        res += "Type \"south\" to go towards the south \n";
        res += "Type \"east\" to go towards the south \n";
        res += "Type \"west\" to go towards the south \n";
        res += "Type \"attack\" to attack a monster \n";
        res += "Type \"look\" to look around \n";
        return res;
    }

    public String look() {
        currentRoom = player.getCurrentRoom();
        String res;
        res = currentRoom.getTitle() + "\n";
        res += currentRoom.getDescription() + "\n";
        res += currentRoom.availableDirections() + "\n";
        res += currentRoom.getMonsters();

        return res;
    }

    public void attack() {
    }
}
