/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameEngine;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 *
 * @author Thomas
 */
public class UserInterface {

    /**
     * @param args the command line arguments
     */
    public UserInterface(OutputStreamWriter newOut, InputStreamReader newIn) {
        OutputStreamWriter ostream = newOut;
        BufferedWriter out = new BufferedWriter(ostream);

        InputStreamReader instream = newIn;
        BufferedReader in = new BufferedReader(instream);



        try {
            Game game = new Game(null);
            //Scanner scanner = new Scanner(System.in);
            String command;
            out.write("Type \"help\" to see a list of commands.");
            out.newLine();
            out.write("Type \"stop\" to quit the game.");
            out.newLine();
            out.write(game.getCurrentRoom().availableDirections());
            out.newLine();
            out.flush();
            boolean stop = false;
            while (!stop) {
                out.write("Where would you like to do:");
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
                        out.write(game.move(command));
                        out.newLine();
                        out.flush();
                        break;
                    case "south":
                        out.write(game.move(command));
                        out.newLine();
                        out.flush();
                        break;
                    case "east":
                        out.write(game.move(command));
                        out.newLine();
                        out.flush();
                        break;
                    case "west":
                        out.write(game.move(command));
                        out.newLine();
                        out.flush();
                        break;
                    case "help":
                        out.write(game.help());
                        out.newLine();
                        out.flush();
                        break;
                    case "look":
                        out.write(game.look());
                        out.newLine();
                        out.flush();
                        break;
                    case "attack":
                        out.write(game.attack());
                        out.newLine();
                        out.flush();
                        break;
                    case "use":
                        out.write(game.getPlayerInventory());
                        out.newLine();
                        out.flush();
                        break;
                    case "gear":
                        out.write(game.getPlayerGear());
                        out.newLine();
                        out.flush();
                        break;
                    default:
                        out.write("I do not understand the command: " + command);
                        out.newLine();
                        out.write("Type \"help\" to see the commands.");
                        out.newLine();
                        out.flush();
                }
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }


    }
}
