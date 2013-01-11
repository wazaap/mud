/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameEngine;


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
        String command;
        System.out.println("Type \"help\" to see a list of commands. \nType \"stop\" to quit the game.\n");
        System.out.println(game.getCurrentRoom().availableDirections());
        System.out.println("Where would you like to go: ");
        command = scanner.next();
        while (command.equals("stop") == false) {
            switch (command) {
                case "north":
                    System.out.println(game.move(command));
                    break;
                case "south":
                    System.out.println(game.move(command));
                    break;
                case "east":
                    System.out.println(game.move(command));
                    break;
                case "west":
                    System.out.println(game.move(command));
                    break;
                case "help":
                    System.out.println(game.help());
                    break;
                case "look":
                    System.out.println(game.look());
                    break;
                default :
                    System.out.println("I do not understand the command: " + command + "\nType \"help\" to see the commands.");
            }
            System.out.println("What would you like to do?: ");
            command = scanner.next();
        }
        System.out.println("You quit the game!! ");
    }
}
