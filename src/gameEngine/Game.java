/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameEngine;

import character.Monster;
import character.Player;
import dungeon.Dungeon;
import dungeon.Room;
import fileio.FileIO;
import item.Item;
import java.util.ArrayList;
import java.util.Random;

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

    private ArrayList<Item> items = FileIO.getAllItems();
    private ArrayList<Monster> monsters = FileIO.getAllMonsters();
    
    public Game() {
        // Initialize the dungeon
        dungeon = FileIO.readDungeon(null);
        
        // Add monsters to rooms
        for(int i = 0; i < dungeon.size(); i++) {
            Random gen = new Random();
            int nextMonster = gen.nextInt(monsters.size());
            int amountOfMonsters = gen.nextInt(5);
            for(int j = 0; j < amountOfMonsters; j++) {
                dungeon.getRoom(i).addMonster(monsters.get(nextMonster));
                
            } 
        }
        
        sword = items.get(0);
        shield = items.get(4);
        player = new Player("Mads", 1000, sword, shield, 200, dungeon.getRoom(1));
        
    }

    public Monster getRandomMonster() {
        Random gen = new Random();
        int num = gen.nextInt(monsters.size());
        return monsters.get(num);
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

    public String attack() {
        if (currentRoom.amountOfMonsters() > 0) {
            if (player.getHitPoints() > 0 && currentRoom.getMonster(0).getHitPoints() > 0) {
                player.setHitPoints(player.getHitPoints() - currentRoom.getMonster(0).getAttackPoints());
                currentRoom.getMonster(0).setHitPoints((currentRoom.getMonster(0).getHitPoints() - player.getWeapon().getDamage()));
                if (player.getHitPoints() <= 0) {
                    return "You have died!";
                } else if (currentRoom.getMonster(0).getHitPoints() <= 0) {
                    currentRoom.removeFirstMonster();
                    return "You have killed the a " + currentRoom.getMonster(0).getName();
                }
            }
            
        }
        return "There is no monsters left in the room.";
    }
}
