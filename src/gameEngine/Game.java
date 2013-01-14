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
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Thomas
 */
public class Game implements Serializable {
private static final long serialVersionUID = 19981017L;

    private static final String newLine = System.getProperty("line.separator");
    private Item sword;
    private Item shield;
    private Player player;
    private Dungeon dungeon;
    private Room currentRoom;
    private ArrayList<Item> items = FileIO.getAllItems();
    private ArrayList<Monster> monsters = FileIO.getAllMonsters();

    public Game(String dungeonPath, OutputStreamWriter newOut, InputStreamReader newIn) {
        // Initialize the dungeon
        dungeon = FileIO.readDungeon(dungeonPath);
        if (dungeon != null) {

            // Add monsters to rooms
            for (int i = 0; i < dungeon.size(); i++) {
                Random gen = new Random();
                int nextMonster = gen.nextInt(monsters.size());
                int amountOfMonsters = gen.nextInt(5);
                for (int j = 0; j < amountOfMonsters; j++) {
                    dungeon.getRoom(i).addMonster(monsters.get(nextMonster));
                    nextMonster = gen.nextInt(monsters.size());
                }
            }
            sword = items.get(0);
            shield = items.get(4);
            player = new Player("Mads", 1000, sword, shield, 200, dungeon.getRoom(1));
            player.getInventory().add(items.get(1));
            player.getInventory().add(items.get(2));
            player.getInventory().add(items.get(3));
            this.run(newOut, newIn);
        }
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
            res = "In your attempt to escape, a " + monsters.get(0).getName() + " blocks your path" + System.getProperty("line.separator");
            return res;
        }
        if (moveTo > 0) {
            for (int i = 0; i < dungeon.size(); i++) {
                if (dungeon.getRoom(i).getId() == moveTo) {
                    player.setCurrentRoom(dungeon.getRoom(i));
                    res = "You go towards " + direction + System.getProperty("line.separator");
                    res += "You enter " + getCurrentRoom().getTitle() + System.getProperty("line.separator");
                    res += getCurrentRoom().getDescription() + System.getProperty("line.separator");
                    res += player.getCurrentRoom().availableDirections() + System.getProperty("line.separator");
                    res += player.getCurrentRoom().getMonsters();
                    return res;
                }
            }
            res = "You walk into the wall.. AND IT HURTS!!" + System.getProperty("line.separator");
        }



        return res;

    }

    public Room getCurrentRoom() {
        return player.getCurrentRoom();
    }

    public String help() {
        String res = "You can use the following commands:" + System.getProperty("line.separator");
        res += "Type \"north\" to go towards the north" + System.getProperty("line.separator");
        res += "Type \"south\" to go towards the south" + System.getProperty("line.separator");
        res += "Type \"east\" to go towards the south" + System.getProperty("line.separator");
        res += "Type \"west\" to go towards the south" + System.getProperty("line.separator");
        res += "Type \"attack\" to attack a monster" + System.getProperty("line.separator");
        res += "Type \"look\" to look around" + System.getProperty("line.separator");
        res += "Type \"use\" to use somthing in your inventory" + System.getProperty("line.separator");
        res += "Type \"gear\" to see what gear you got equipped" + System.getProperty("line.separator");
        return res;
    }

    public String look() {
        currentRoom = player.getCurrentRoom();
        String res = "";
        res = currentRoom.getTitle() + System.getProperty("line.separator");
        res += currentRoom.getDescription() + System.getProperty("line.separator");
        res += currentRoom.availableDirections() + System.getProperty("line.separator");
        res += currentRoom.getMonsters() + System.getProperty("line.separator");

        return res;
    }

    public String attack() {
        currentRoom = player.getCurrentRoom();
        if (currentRoom.amountOfMonsters() == 0) {
            return "There is no monsters left in the room." + System.getProperty("line.separator");
        }
        if (player.getHitPoints() > 0 && currentRoom.getMonster(0).getHitPoints() > 0) {
            player.setHitPoints(player.getHitPoints() - currentRoom.getMonster(0).getAttackPoints());
            currentRoom.getMonster(0).setHitPoints((currentRoom.getMonster(0).getHitPoints() - player.getWeapon().getDamage()));

        }
        if (player.getHitPoints() <= 0) {
            return "You died!" + System.getProperty("line.separator");
        } else if (currentRoom.getMonster(0).getHitPoints() <= 0) {
            String monsterName = currentRoom.getMonster(0).getName();
            currentRoom.removeFirstMonster();
            return "You have killed the a " + monsterName + System.getProperty("line.separator");
        }
        String res = "You hit a " + currentRoom.getMonster(0).getName() + "! It now has " + currentRoom.getMonster(0).getHitPoints() + " healthpoints left!" + System.getProperty("line.separator");
        res += "The " + currentRoom.getMonster(0).getName() + " hits you back! You now have " + player.getHitPoints() + " healthpoints left!" + System.getProperty("line.separator");
        return res;

    }

    public String getPlayerInventory() {
        String res = "You have the following items in your inventory:" + System.getProperty("line.separator");
        for (int i = 0; i < player.getInventory().size(); i++) {
            res += i + ": " + player.getInventory().get(i).toString() + System.getProperty("line.separator");
        }
        return res;
    }

    public String getPlayerGear() {
        String res = "You have the following gear equipped:" + System.getProperty("line.separator");
        if (player.getGearSlot1() == null) {
            res += "You have nothing equipped in slot 1." + System.getProperty("line.separator");
        } else {
            res += player.getGearSlot1().toString() + System.getProperty("line.separator");
        }
        if (player.getGearSlot2() == null) {
            res += "You have nothing equipped in slot 2." + System.getProperty("line.separator");
        } else {
            res += player.getGearSlot2().toString() + System.getProperty("line.separator");
        }
        return res;
    }

    public void run(OutputStreamWriter newOut, InputStreamReader newIn) {


        OutputStreamWriter ostream = newOut;
        BufferedWriter out = new BufferedWriter(ostream);

        InputStreamReader instream = newIn;
        BufferedReader in = new BufferedReader(instream);

        System.out.println("LORT!");

        try {
            //Scanner scanner = new Scanner(System.in);
            String command;
            out.write("Type \"help\" to see a list of commands.");
            out.newLine();
            out.write("Type \"stop\" to quit the game.");
            out.newLine();
            out.write(this.getCurrentRoom().availableDirections());
            out.newLine();
            out.flush();
            boolean stop = false;
            boolean equipped = false;
            boolean slotted = false;
            while (!stop) {
                out.write("What would you like to do:");
                out.newLine();
                out.flush();
                command = in.readLine();
                switch (command) {
                    case "stop":
                        out.write("You quit the game!");
                        out.flush();
                        stop = true;
                        break;
                    case "north":
                        out.write(this.move(command));
                        out.newLine();
                        out.flush();
                        break;
                    case "south":
                        out.write(this.move(command));
                        out.newLine();
                        out.flush();
                        break;
                    case "east":
                        out.write(this.move(command));
                        out.newLine();
                        out.flush();
                        break;
                    case "west":
                        out.write(this.move(command));
                        out.newLine();
                        out.flush();
                        break;
                    case "help":
                        out.write(this.help());
                        out.newLine();
                        out.flush();
                        break;
                    case "look":
                        out.write(this.look());
                        out.newLine();
                        out.flush();
                        break;
                    case "attack":
                        out.write(this.attack());
                        out.newLine();
                        out.flush();
                        break;
                    case "use":
                        while (equipped == false) {
                            out.write(this.getPlayerInventory());
                            out.newLine();
                            out.flush();
                            out.write("Choose an item by pressing a number: ");
                            out.newLine();
                            out.flush();
                            try {
                                int itemNumber = Integer.parseInt(in.readLine());
                                if (itemNumber > player.getInventory().size() || itemNumber < 0) {
                                    out.write("You do not have that item...");
                                    out.newLine();
                                    out.flush();
                                } else {
                                    if (player.getInventory().get(itemNumber).getItemType() == 3 || player.getInventory().get(itemNumber).getItemType() == 4) {
                                        out.write(player.useItem(itemNumber));
                                        equipped = true;
                                        out.newLine();
                                        out.flush();
                                    } else {
                                        out.write("Choose between slot 1 and slot 2 by pressing 1 or 2: ");
                                        out.newLine();
                                        out.flush();
                                        int slotNumber = Integer.parseInt(in.readLine());
                                        while (slotNumber != 1 && slotNumber != 2){
                                            out.write("You have to choose between slot 1 and slot 2" + System.lineSeparator());
                                            out.flush();
                                            slotNumber = Integer.parseInt(in.readLine());
                                        }
                                        out.write(player.equip(itemNumber, slotNumber));
                                        equipped = true;
                                        out.newLine();
                                        out.flush();
                                    }
                                }
                            } catch (NumberFormatException ex) {
                                out.write("You have to enter a number. Please try again!" + System.lineSeparator());
                                out.newLine();
                                out.flush();
                            }
                        }
                        equipped = false;
                        break;
                    case "gear":
                        out.write(this.getPlayerGear());
                        out.newLine();
                        out.flush();
                        break;
                    case "savegame":
                        out.write("Please give your savegame a name: " + System.getProperty("line.separator"));
                        out.flush();

                        String input = in.readLine();
                        out.write(FileIO.saveGame(this, input) + System.getProperty("line.separator"));
                        out.write("Resuming game..." + System.getProperty("line.separator"));
                        out.flush();
                        break;
                    default:
                        out.write("I do not understand the command: " + command);
                        out.newLine();
                        out.write("Type \"help\" to see the commands.");
                        out.newLine();
                        out.flush();
                }
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

}
