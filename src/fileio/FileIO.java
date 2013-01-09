/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fileio;

import dungeon.Dungeon;
import dungeon.Room;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 *
 * @author Mads
 */
public class FileIO {

    public static Dungeon readDungeon() {
        Dungeon tempDungeon = new Dungeon();
        try {
            FileInputStream fstream = new FileInputStream("test-dungeon.txt");
            // Get the object of DataInputStream
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;

            //Read File Line By Line
            while ((strLine = br.readLine()) != null) {
                String[] arr = strLine.split("#");
                // id, strLine, strLine, north, south, east, west

                // Creates a temperary room to be added
                Room tempRoom = new Room(
                        Integer.valueOf(arr[0]),
                        Integer.valueOf(arr[1]),
                        Integer.valueOf(arr[2]),
                        Integer.valueOf(arr[3]),
                        Integer.valueOf(arr[4]),
                        arr[5],
                        arr[6]);
                tempDungeon.addRoom(tempRoom);
            }
            //Close the input stream
            in.close();
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
        return tempDungeon;
    }
}
