package fileio;

import character.Monster;
import dungeon.Dungeon;
import dungeon.Room;
import item.Item;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Mads
 */
public class FileIO {

    public static final String RANDOM_MONSTERS_FILEPATH = "random-monsters.txt";
    public static final String STANDARD_DUNGEON_FILEPATH = "testDungeon.txt";
    public static final String STANDARD_WEAPONS_PATH = "weapons.txt";

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
                System.out.println("ERROR: File: \"" + filepath + "\" could not be read!\n" + ex);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("ERROR: File: \"" + filepath + "\" could not be found!\n" + ex);
        }


        return res;
    }

    /**
     * Reads a dungeon from a specified file.
     *
     * @return A dungeon
     */
    public static Dungeon readDungeon(String filepath) {
        String fileInput;
        if (filepath == null) {
            fileInput = readFile(STANDARD_DUNGEON_FILEPATH);
        } else {
            fileInput = readFile(filepath);
        }
        Dungeon tempDungeon = new Dungeon();


        String[] rooms = fileInput.split("\n");
        // id, strLine, strLine, north, south, east, west

        for (int i = 0; i < rooms.length; i++) {
            String[] arr = rooms[i].split("#");
            // Creates a temperary room to be added
            Room tempRoom = new Room(
                    Integer.parseInt(arr[0]),
                    Integer.parseInt(arr[1]),
                    Integer.parseInt(arr[2]),
                    Integer.parseInt(arr[3]),
                    Integer.parseInt(arr[4]),
                    arr[5],
                    arr[6]);
            tempDungeon.addRoom(tempRoom);
        }
        return tempDungeon;
    }

    /**
     * Reads a random monster from the 'random-monsters.txt' file. More monsters
     * can be added there.
     *
     * @return A random monster
     */
    public static ArrayList<Monster> getAllMonsters() {
        String[] strInput = readFile(RANDOM_MONSTERS_FILEPATH).split("\n");

        ArrayList<Monster> monsters = new ArrayList();
        for (int i = 0; i < strInput.length; i++) {
            String[] arr = strInput[i].split("#");
            Monster tempMonster = new Monster(
                    arr[0],
                    arr[1],
                    Integer.parseInt(arr[2]),
                    Integer.parseInt(arr[3]));
            monsters.add(tempMonster);
        }
        return monsters;
    }

    /**
     * Reads a random monster from the 'random-monsters.txt' file. More monsters
     * can be added there.
     *
     * @return A random monster
     */
    public static ArrayList<Item> getAllItems() {
        String[] strArr = readFile(STANDARD_WEAPONS_PATH).split("\n");

        ArrayList<Item> items = new ArrayList();

        for (int i = 0; i < strArr.length; i++) {
            String[] arr = strArr[i].split("#");
            Item tempItem = new Item(
                    arr[0],
                    arr[1],
                    Integer.parseInt(arr[2]),
                    Integer.parseInt(arr[3]),
                    Integer.parseInt(arr[4]),
                    Integer.parseInt(arr[5]));
            items.add(tempItem);
        }

        return items;
    }
}
