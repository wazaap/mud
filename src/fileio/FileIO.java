package fileio;

import character.Monster;
import dungeon.Dungeon;
import dungeon.Room;
import gameEngine.Game;
import item.Item;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mads
 */
public class FileIO implements Serializable {

    private static final long serialVersionUID = 19981017L;
    private static final String MONSTERS_FILEPATH = "characters/random-monsters.txt";
    private static final String BOSSES_FILEPATH = "characters/bosses.txt";
    private static final String STANDARD_WEAPONS_PATH = "data/items.txt";

    /**
     * Reads a file from a specified filepath, and returns a string with the
     * content.
     *
     * @param filepath Path to the file
     * @return String
     */
    private static String readFile(String filepath) {
        String res = "";
        try {
            // Forsøg at indlæse filen
            FileInputStream fstream = new FileInputStream(filepath);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            // Temperary input
            String tempStr;
            try {
                // Imens inputtet ikke er tomt, tilføj det til strLine
                while ((tempStr = br.readLine()) != null) {
                    res += tempStr + "\n";
                }
                // Remove the last linebreak
                res = res.substring(0, res.length() - 1);

            } catch (IOException ex) {
                System.out.println("ERROR: File: \"" + filepath + "\" could not be read!" + System.lineSeparator());
            }
        } catch (FileNotFoundException ex) {
            System.out.println("ERROR: File: \"" + filepath + "\" could not be found!" + System.lineSeparator());
        }
        return res;
    }

    /**
     * Reads a dungeon from a specified file, and adds a boss to the endroom of
     * the dungeon. Returns the dungeon if the file is read correctly, otherwise
     * null.
     *
     * @return dungeon The dungeon read from file
     */
    public static Dungeon readDungeon(String filepath) {
        String fileInput;
        fileInput = readFile(filepath);

        Dungeon tempDungeon = new Dungeon();

        if (!"".equals(fileInput)) {
            String[] rooms = fileInput.split("\n");

            String[] firstLineInfo = rooms[0].split("#");
            tempDungeon.setStartRoom(Integer.parseInt(firstLineInfo[0]) - 1);
            tempDungeon.setEndRoom(Integer.parseInt(firstLineInfo[1]) - 1);
            tempDungeon.setDescription(firstLineInfo[2]);

            // id, strLine, strLine, north, south, east, west
            for (int i = 1; i < rooms.length; i++) {
                String[] arr = rooms[i].split("#");
                // Creates a temperary room to be added
                Room tempRoom = new Room(
                        Integer.parseInt(arr[0]),
                        Integer.parseInt(arr[1]),
                        Integer.parseInt(arr[2]),
                        Integer.parseInt(arr[3]),
                        Integer.parseInt(arr[4]),
                        arr[5].replace("\n", ""),
                        arr[6].replace("\n", ""));
                tempDungeon.addRoom(tempRoom);

            }
            // Add a random boss to the endroom of the dungeon
            Random gen = new Random();
            tempDungeon.getRoom(tempDungeon.getEndRoom()).
                    addMonster(getAllBosses().get(gen.nextInt(getAllBosses().size())));

            return tempDungeon;
        } else {
            return null;
        }
    }

    /**
     * Reads all monsters from the path to 'Random-monsters.txt', and returns an
     * ArrayList containing them.
     *
     * @return ArrayList of all monsters
     */
    public static ArrayList<Monster> getAllMonsters() {
        String[] strInput = readFile(MONSTERS_FILEPATH).split("\n");

        ArrayList<Monster> monsters = new ArrayList<>();
        for (int i = 0; i < strInput.length; i++) {
            String[] arr = strInput[i].split("#");
            Monster tempMonster = new Monster(
                    arr[0],
                    arr[1],
                    Integer.parseInt(arr[2]),
                    Integer.parseInt(arr[3]),
                    Integer.parseInt(arr[4]));
            monsters.add(tempMonster);
        }
        return monsters;
    }

    public static ArrayList<Monster> getAllBosses() {
        String[] strInput = readFile(BOSSES_FILEPATH).split("\n");

        ArrayList<Monster> monsters = new ArrayList<>();
        for (int i = 0; i < strInput.length; i++) {
            String[] arr = strInput[i].split("#");
            Monster tempMonster = new Monster(
                    arr[0],
                    arr[1],
                    Integer.parseInt(arr[2]),
                    Integer.parseInt(arr[3]),
                    Integer.parseInt(arr[4]));
            monsters.add(tempMonster);
        }
        return monsters;
    }

    /**
     * Reads all items from the path to 'weapons.txt', and returns an ArrayList
     * containing them.
     *
     * @return ArrayList of all monsters
     */
    public static ArrayList<Item> getAllItems() {
        String[] strArr = readFile(STANDARD_WEAPONS_PATH).split("\n");

        ArrayList<Item> items;
        items = new ArrayList<>();

        for (int i = 0; i < strArr.length; i++) {
            String[] arr = strArr[i].split("#");
            Item tempItem = new Item(
                    arr[0],
                    arr[1],
                    Integer.parseInt(arr[2]),
                    Integer.parseInt(arr[3]),
                    Integer.parseInt(arr[4]),
                    Integer.parseInt(arr[5]),
                    Integer.parseInt(arr[6]));
            items.add(tempItem);
        }
        return items;
    }

    /**
     * Saves a game with a specific savegame-name. Returns a string which tells
     * if the game was saved or not.
     *
     * @param game the game to be saved
     * @param savedName the name of the saved game
     * @return String telling if the game was saved or not
     */
    public static String saveGame(Game game, String savedName) {
        String filename = "savegames/" + savedName + ".mud";


        FileOutputStream fos;
        ObjectOutputStream out;

        try {
            fos = new FileOutputStream(filename);
            out = new ObjectOutputStream(fos);
            out.writeObject(game);
            out.close();
            //System.out.println("Object Persisted");
        } catch (IOException ex) {
            System.out.println(ex);
            return "Game could not be saved!";
        }
        return "Game was saved to: \"" + savedName + ".mud\"!";
    }

    /**
     * Reads a specified game from file, and returns the gameobject.
     *
     * @param filepath path to the savegame
     * @return Game loaded game
     */
    public static Game loadGame(String filepath) {
        String filename = "savegames/" + filepath;

        Game myGame;
        FileInputStream fis;
        ObjectInputStream in;

        try {
            fis = new FileInputStream(filename);
            in = new ObjectInputStream(fis);
            myGame = (Game) in.readObject();
            in.close();
            return myGame;

        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(FileIO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     * Returns a string containing a list of files in a specified folder.
     *
     * @param folder the folder to be searched
     * @return String list of files
     */
    public static String readFilesInFolder(String folder) {
        String res = "";
        File actual = new File(folder);
        for (File f : actual.listFiles()) {
            res += f.getName() + System.getProperty("line.separator");
        }
        if ("".equals(res)) {
            res += "No files found!" + System.getProperty("line.separator");
            return res;
        } else {
            return res;
        }
    }
}
