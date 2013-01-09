/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameEngine;

import dungeon.Dungeon;
import fileio.FileIO;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thomas
 */
public class Game {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Dungeon test;
                test = FileIO.readDungeon();
                System.out.println("");
                
                
                
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }


       
    }
}
