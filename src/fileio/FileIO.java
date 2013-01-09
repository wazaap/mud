/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fileio;

import character.Monster;
import dungeon.Dungeon;
import dungeon.Room;
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

    public static Dungeon readDungeon() throws FileNotFoundException, IOException {
        Dungeon tempDungeon = new Dungeon();

        FileInputStream fstream = new FileInputStream("testDungeon.txt");
        try (DataInputStream in = new DataInputStream(fstream)) {
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;

            //Read File Line By Line
            while ((strLine = br.readLine()) != null) {
                String[] arr = strLine.split("#");
                // id, strLine, strLine, north, south, east, west

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
        }

        return tempDungeon;
    }

    public static Monster readMonster() {

        Monster tempMonster = null;
        // Read a random line between 0 and the lineCount
        try {
            FileInputStream fstream = null;
            try {
                fstream = new FileInputStream("random-monsters.txt");
            } catch (FileNotFoundException ex) {
                System.out.println(ex);
            }
            try (DataInputStream in = new DataInputStream(fstream)) {
                BufferedReader br = new BufferedReader(new InputStreamReader(in));


                ArrayList<Monster> tempMonsters;
                tempMonsters = new ArrayList();

                String strLine;
                while ((strLine = br.readLine()) != null) {
                    String[] arr = strLine.split("#");
                    tempMonster = new Monster(arr[0], arr[1], Integer.parseInt(arr[2]), Integer.parseInt(arr[3]));
                    tempMonsters.add(tempMonster);
                }

                Random gen = new Random();
                int randomNumber = gen.nextInt(tempMonsters.size());
                return tempMonsters.get(randomNumber);
            }
        } catch (IOException | NumberFormatException e) {//Catch exception if any
            System.err.println("ddError: " + e);
        }
        return tempMonster;
    }
}
