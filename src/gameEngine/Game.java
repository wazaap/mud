/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameEngine;

import dungeon.Dungeon;
import fileio.FileIO;

/**
 *
 * @author Thomas
 */
public class Game {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Dungeon test = FileIO.readDungeon();
        System.out.println("");
    }
}
