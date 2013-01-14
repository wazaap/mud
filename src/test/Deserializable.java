/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import gameEngine.Game;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 *
 * @author Mads
 */
public class Deserializable {
    public static void main(String[] args) {
        String filename = "savegame.txt";
        
		Game myGame = null;
		FileInputStream fis = null;
		ObjectInputStream in = null;
                
		try {
			fis = new FileInputStream(filename);
			in = new ObjectInputStream(fis);
			myGame = (Game) in.readObject();
                        myGame.run(new OutputStreamWriter(System.out), new InputStreamReader(System.in));
			in.close();
                        
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		// print out the size
		System.out.println();

    }
    
}
