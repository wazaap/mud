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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thomas & Mads
 */
public class Game implements Serializable {

    private static final long serialVersionUID = 19981017L;
    private static final String newLine = System.getProperty("line.separator");
    private Player player;
    private Dungeon dungeon;
    private Room currentRoom;
    private ArrayList<Item> items = FileIO.getAllItems();
    private ArrayList<Monster> monsters = FileIO.getAllMonsters();
    private boolean stop = false;

    public Game(String dungeonPath, OutputStreamWriter newOut, InputStreamReader newIn) {
        // Initialize the dungeon
        dungeon = FileIO.readDungeon(dungeonPath);
        if (dungeon != null) {

            // Add monsters to rooms
            for (int i = 0; i < dungeon.size(); i++) {
                Random gen = new Random();
                int nextMonster = gen.nextInt(monsters.size());
                if (gen.nextInt(10) > 5) {
                    int amountOfMonsters = gen.nextInt(5) + 1;
                    for (int j = 0; j < amountOfMonsters; j++) {
                        dungeon.getRoom(i).addMonster((Monster) monsters.get(nextMonster).clone());
                        nextMonster = gen.nextInt(monsters.size());
                    }
                }
            }

            // Add treasure chests to rooms
            for (int i = 0; i < dungeon.size(); i++) {
                Random gen = new Random();
                int nextChest = gen.nextInt(items.size() - 1);
                if (gen.nextInt(10) > 7) {
                    int amountOfItems = gen.nextInt(3) + 1;
                    for (int j = 0; j < amountOfItems; j++) {
                        dungeon.getRoom(i).addItmeToChest(items.get(nextChest));
                        nextChest = gen.nextInt(items.size() - 1);
                    }
                }
            }
        }
    }

    //gets a random monster from the monster ArrayList
    public Monster getRandomMonster() {
        Random gen = new Random();
        int num = gen.nextInt(monsters.size());
        return (Monster) monsters.get(num).clone();
    }

    /**
     * Verifies your move is valid and moves you to the correct room. Returns
     * what way you go, what room you enter, the description and what directions
     * you have available.
     *
     * @param direction
     * @return String
     */
    public String move(String direction) {
        currentRoom = player.getCurrentRoom();
        String res;
        int moveTo = 0;
        while (currentRoom.amountOfMonsters() > 0) {
            res = "In your attempt to escape, a " + currentRoom.getMonster(0).getName() + " blocks your path" + System.getProperty("line.separator");
            return res;
        }
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
                    res = System.lineSeparator();
                    res += "You go towards " + direction + System.getProperty("line.separator");
                    res += System.lineSeparator();
                    res += "You enter " + getCurrentRoom().getTitle() + System.getProperty("line.separator");
                    res += getCurrentRoom().getDescription() + System.getProperty("line.separator");
                    res += player.getCurrentRoom().availableDirections() + System.getProperty("line.separator");
                    return res;
                }
            }

        }
        res = "You walk into the wall.. AND IT HURTS!!" + System.getProperty("line.separator");
        return res;
    }

    //Returns the current room the player is in.
    public Room getCurrentRoom() {
        return player.getCurrentRoom();
    }

    /**
     * Returns all available commands in a string.
     *
     * @return String
     */
    public String help() {
        String res = "You can use the following commands:" + System.getProperty("line.separator");
        res += "Type \"north\" to go towards the north" + System.getProperty("line.separator");
        res += "Type \"south\" to go towards the south" + System.getProperty("line.separator");
        res += "Type \"east\" to go towards the south" + System.getProperty("line.separator");
        res += "Type \"west\" to go towards the south" + System.getProperty("line.separator");
        res += "Type \"attack\" to attack a monster" + System.getProperty("line.separator");
        res += "Type \"look\" to look around in the room" + System.getProperty("line.separator");
        res += "Type \"search\" to search the room for treasure" + System.getProperty("line.separator");
        res += "Type \"use\" to use somthing in your inventory" + System.getProperty("line.separator");
        res += "Type \"gear\" to see what gear you got equipped" + System.getProperty("line.separator");
        res += "Type \"savegame\" to save your progress" + System.getProperty("line.separator");
        res += "Type \"stop\" to quit the game." + System.lineSeparator();
        return res;
    }

    //Returns a string containing the title of the room, description of the room, available directions and monsters in the room.
    public String look() {
        currentRoom = player.getCurrentRoom();
        String res;
        res = currentRoom.getTitle() + System.getProperty("line.separator");
        res += currentRoom.getDescription() + System.getProperty("line.separator");
        res += currentRoom.availableDirections() + System.getProperty("line.separator");
        res += currentRoom.getMonsters() + System.getProperty("line.separator");

        return res;
    }

    /**
     * This method checks if the chest in the room contains anything. If it
     * does, it returns the items of the chest and adds them to the player
     * inventory Returns a string with the result.
     *
     * @return String
     */
    public String search() {
        currentRoom = player.getCurrentRoom();
        String res = "You start searching the room....";
        if (currentRoom.chestSize() > 0) {
            res += "You find a treasure chest in the corner..." + System.lineSeparator();
            res += "You pick up the following items from the chest and put them in your backpack" + System.lineSeparator();
            res += currentRoom.getItemsInChest();
        } else {
            res = "You find nothing of intrest in the room...";
        }
        return res;
    }

    //this method transfers the items from a chest to the players inventory.
    public void takeItemsFromChest() {
        currentRoom = player.getCurrentRoom();
        for (int i = 0; i < currentRoom.getChest().size(); i++) {
            player.addToInventory(currentRoom.getChest().get(i));
            currentRoom.getChest().remove(i);
        }

    }

    /**
     * This method subtracts the attack points of a monster from your hit points
     * and vice versa. After subtraction it checks if either of you have 0 or
     * less hit points to determine if anybody is dead. If the monster is dead,
     * it runs the monsterDropsLoot() method for a chance of finding loot on the
     * corpse. If you are dead the game ends.
     *
     * @return String
     */
    public String attack() {
        String res;

        if (currentRoom.amountOfMonsters() != 0) {
            player.setHitPoints(player.getHitPoints() - currentRoom.getMonster(0).getAttackPoints());
            currentRoom.getMonster(0).setHitPoints((currentRoom.getMonster(0).getHitPoints() - player.getWeapon().getDamage()));
            res = "You hit a " + currentRoom.getMonster(0).getName() + "! It now has " + currentRoom.getMonster(0).getHitPoints() + " healthpoints left!" + System.getProperty("line.separator");
            res += "The " + currentRoom.getMonster(0).getName() + " hits you back! You now have " + player.getHitPoints() + " healthpoints left!" + System.getProperty("line.separator");
            if (currentRoom.getMonster(0).getHitPoints() < 1) {
                String monsterName = currentRoom.getMonster(0).getName();
                int gainedXp = currentRoom.getMonster(0).getXp();
                player.setXp(player.getXp() + gainedXp);
                currentRoom.removeMonster(0);
                res += "You have killed a " + monsterName + " and you gain " + gainedXp + " xp" + System.getProperty("line.separator");
                res += monsterDropsLoot();
            }
            if (player.getHitPoints() < 1) {
                res += "You died a horrible death!" + System.getProperty("line.separator");
                stop = true;
            }

            if (currentRoom.getId() == dungeon.getEndRoom() && currentRoom.amountOfMonsters() < 1) {
                res += "You have completed the dungeon...";
                stop = true;
            }

            return res;
        }
        return "There is no monsters left in the room." + System.getProperty("line.separator");

    }

    /**
     * This method determines if a monster drops a random item that is
     * automatically added to the player inventory Returns a string with the
     * loot dropped or a string if there is no loot.
     *
     * @return String
     */
    public String monsterDropsLoot() {
        String res;
        Random gen = new Random();
        if (gen.nextInt(100) > 75) {
            int tmp = gen.nextInt(items.size());
            player.addToInventory(items.get(tmp));
            res = "You search the corpse and loot a " + items.get(tmp).getName() + System.lineSeparator();
        } else {
            res = "You find nothing of value on the corpse.." + System.lineSeparator();
        }
        return res;
    }

    /**
     * Returns all items in the player inventory ArrayList to a String.
     *
     * @return String
     */
    public String getPlayerInventory() {
        String res = "You have the following items in your inventory:" + System.getProperty("line.separator");
        for (int i = 0; i < player.getInventory().size(); i++) {
            res += i + ": " + player.getInventory().get(i).toString() + System.getProperty("line.separator");
        }
        return res;
    }

    /**
     * Returns all the gear in the players slot 1 and slot 2 Returns a string if
     * the player has nothing equipped in a slot.
     *
     * @return String
     */
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

    public final void run(OutputStreamWriter newOut, InputStreamReader newIn) {

        OutputStreamWriter ostream = newOut;
        BufferedWriter out = new BufferedWriter(ostream);

        InputStreamReader instream = newIn;
        BufferedReader in = new BufferedReader(instream);

        try {
            // Introduction to the loaded game.
            String command;
            out.newLine();
            out.newLine();
            out.write("-------------------------------------");
            out.newLine();
            out.flush();
            createPlayer(newOut, newIn);
            out.newLine();
            out.flush();
            out.write("-------------------------------------");
            out.newLine();
            out.write("Welcome the this awsome sud " + player.getName());
            out.newLine();
            out.newLine();
            out.write(dungeon.getDescription().replace("$name", player.getName()));
            out.newLine();
            out.newLine();
            out.write(player.getPlayerStats());
            out.newLine();

            out.newLine();
            out.write(getPlayerGear());
            out.newLine();
            out.write("Remeber you can allways type \"help\" to see a list of commands.");
            out.newLine();
            out.newLine();
            out.write(this.getCurrentRoom().getTitle());
            out.newLine();
            out.write(this.getCurrentRoom().getDescription());
            out.newLine();
            out.write(this.getCurrentRoom().availableDirections());
            out.newLine();
            out.flush();

            //Switch case that handles all the commands the player can write.
            while (!stop) {
                if (gainLevel() == true) {
                    out.write("Congratulations!! You gained a level and are now level " + this.player.getLevel());
                    out.newLine();
                    out.flush();
                }
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
                    case "flee":
                        out.write(this.flee());
                        out.newLine();
                        out.flush();
                        break;
                    case "use":
                        useItem(newOut, newIn);
                        break;
                    case "gear":
                        out.write(this.getPlayerGear());
                        out.newLine();
                        out.flush();
                        break;
                    case "stats":
                        out.write(player.getPlayerStats());
                        out.newLine();
                        out.flush();
                        break;
                    case "search":
                        out.write(this.search());
                        this.takeItemsFromChest();
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
                    case "inventory":
                        out.write("You have the following items in your backpack: " + System.lineSeparator());
                        out.write(this.getPlayerInventory());
                        out.flush();
                        break;
                    case "god":
                        player.getInventory().add(items.get(1));
                        player.getInventory().add(items.get(2));
                        player.getInventory().add(items.get(3));
                        break;
                    default:
                        out.write("I do not understand the command: " + command);
                        out.newLine();
                        out.write("Type \"help\" to see the commands.");
                        out.newLine();
                        out.flush();
                }
            }
        } catch (IOException | NullPointerException ex) {
            System.out.println("Håndteret fejl?" + ex);
        }
    }

    /**
     * Lists the player inventory and gives the opportunity to choose a item to
     * use. Determines the type of item the player wants to use. If its
     * consumable or equip-able. Consumable items are called with the useItem()
     * method Equip-able items gives the player the options to equip in slot 1
     * or slot 2.
     *
     * @param out
     * @param newIn
     */
    public void useItem(OutputStreamWriter out, InputStreamReader newIn) {
        InputStreamReader instream = newIn;
        BufferedReader in = new BufferedReader(instream);
        boolean equipped = false;
        if (this.player.getInventory().isEmpty()) {
            try {
                out.write("You have nothing in your inventory you can use.." + System.lineSeparator());
            } catch (IOException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            while (!equipped) {
                try {
                    out.write(this.getPlayerInventory());
                    out.write(System.getProperty("line.separator"));
                    out.write("Choose an item by pressing a number: ");
                    out.write(System.getProperty("line.separator"));
                    out.flush();
                    try {
                        int itemNumber = Integer.parseInt(in.readLine());
                        if (itemNumber >= player.getInventory().size() || itemNumber < 0) {
                            out.write("You do not have that item...");
                            out.write(System.getProperty("line.separator"));
                            out.flush();
                        } else {
                            if (player.getInventory().get(itemNumber).getItemType() == 3 || player.getInventory().get(itemNumber).getItemType() == 4) {
                                out.write(player.useItem(itemNumber));
                                equipped = true;
                                out.write(System.getProperty("line.separator"));
                                out.flush();
                            } else {
                                out.write("Choose between slot 1 and slot 2 by pressing 1 or 2: ");
                                out.write(System.getProperty("line.separator"));
                                out.flush();
                                int slotNumber = Integer.parseInt(in.readLine());
                                while (slotNumber != 1 && slotNumber != 2) {
                                    out.write("You have to choose between slot 1 and slot 2" + System.lineSeparator());
                                    out.flush();
                                    slotNumber = Integer.parseInt(in.readLine());
                                }
                                out.write(player.equip(itemNumber, slotNumber));
                                equipped = true;
                                out.write(System.getProperty("line.separator"));
                                out.flush();
                            }
                        }
                    } catch (NumberFormatException ex) {
                        out.write("You have to enter a number. Please try again!" + System.lineSeparator());
                        out.write(System.getProperty("line.separator"));
                        out.flush();
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /**
     * Asks the player to enter his name and creates an object of the class
     * Player with the name and predefined statistics.
     *
     * @param newOut
     * @param newIn
     * @return String
     */
    public String createPlayer(OutputStreamWriter newOut, InputStreamReader newIn) {
        String name;
        int hitPoints = 100;
        Item slot1 = items.get(0);
        Item slot2 = null;
        int gold = 0;
        Room room = dungeon.getRoom(dungeon.getStartRoom());

        OutputStreamWriter ostream = newOut;
        BufferedWriter out = new BufferedWriter(ostream);

        InputStreamReader instream = newIn;
        BufferedReader in = new BufferedReader(instream);
        try {
            out.write("Enter your name: ");
            out.newLine();
            out.flush();
            name = in.readLine();
            player = new Player(name, hitPoints, slot1, slot2, gold, room);
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * This method determines if there is any monsters to flee from. If there is
     * you will have a chance that the monsters will attack you. If you manage
     * to flee, the method will send you to a random room corresponding to the
     * current room. Returns a string with the action performed.
     *
     * @return String
     */
    public String flee() {
        currentRoom = player.getCurrentRoom();
        Random random = new Random();
        int rooms;
        String res;
        if (currentRoom.amountOfMonsters() > 0) {
            if (random.nextInt(100) > 90) {
                res = "In your attempt to flee a " + currentRoom.getMonster(0) + " blocks the path and you start to fight.";
                res += attack();
            } else {
                res = "In panic you scramble to a nearby room ";
                boolean flee = false;
                while (flee == false) {
                    rooms = random.nextInt(3);
                    switch (rooms) {
                        case 0:
                            if (currentRoom.getNorth() != -1) {
                                for (int i = 0; i < dungeon.size(); i++) {
                                    if (dungeon.getRoom(i).getId() == currentRoom.getNorth()) {
                                        player.setCurrentRoom(dungeon.getRoom(i));
                                        flee = true;
                                    }
                                }
                            }
                            break;

                        case 1:
                            if (currentRoom.getSouth() != -1) {
                                for (int i = 0; i < dungeon.size(); i++) {
                                    if (dungeon.getRoom(i).getId() == currentRoom.getSouth()) {
                                        player.setCurrentRoom(dungeon.getRoom(i));
                                        flee = true;
                                    }
                                }
                            }
                            break;
                        case 2:
                            if (currentRoom.getEast() != -1) {
                                for (int i = 0; i < dungeon.size(); i++) {
                                    if (dungeon.getRoom(i).getId() == currentRoom.getEast()) {
                                        player.setCurrentRoom(dungeon.getRoom(i));
                                        flee = true;
                                    }
                                }
                            }
                            break;
                        case 3:
                            if (currentRoom.getWest() != -1) {
                                for (int i = 0; i < dungeon.size(); i++) {
                                    if (dungeon.getRoom(i).getId() == currentRoom.getWest()) {
                                        player.setCurrentRoom(dungeon.getRoom(i));
                                        flee = true;
                                    }
                                }
                            }
                            break;
                    }
                }
            }
        } else {
            res = "There is nothing to flee from...";
        }
        return res;
    }

    public boolean gainLevel() {
        if (this.player.getXp() > 100) {
            this.player.setXp(0);
            this.player.setMaxHp(this.player.getMaxHp() * 3 / 2);
            this.player.setHitPoints(this.player.getMaxHp());
            this.player.setLevel(this.player.getLevel() + 1);
            return true;
        }
        return false;
    }
}
