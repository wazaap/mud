/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameEngine;

import dungeon.Dungeon;
import fileio.FileIO;
import item.Item;
import java.util.Scanner;

/**
 *
 * @author Thomas
 */
public class UserInterface {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Game game = new Game();
        Scanner scanner = new Scanner(System.in);
        String move = "";
        while (move.equals("stop") == false) {
            System.out.println("Where would you like to go: ");
            move = scanner.next();
            game.move(move);
            System.out.println(game.getCurrentRoom().getId());
        }
    }
}
