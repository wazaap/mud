/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameEngine;

import fileio.FileIO;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mads
 */
public class GameStarter {

    public GameStarter(OutputStreamWriter out, InputStreamReader inS) {
        Game game;
        try {
            InputStreamReader instream = inS;
            BufferedReader in = new BufferedReader(instream);

            boolean stop = false;
            while (!stop) {
                // Indlæs og skriv velkomstskærmen
                out.write(System.getProperty("line.separator"));
                out.write(System.getProperty("line.separator"));
                try (FileReader inputStream = new FileReader("welcome.txt")) {
                    int c;
                    while ((c = inputStream.read()) != -1) {
                        out.write((char) c);
                        out.flush();
                    }
                    out.write(System.getProperty("line.separator"));
                    out.write(System.getProperty("line.separator"));
                }

                out.write("MENU:" + System.getProperty("line.separator"));
                out.write("------------" + System.getProperty("line.separator"));
                out.write("n: New Game" + System.getProperty("line.separator"));
                out.write("l: Load Game" + System.getProperty("line.separator"));
                out.write("q: Quit" + System.getProperty("line.separator"));
                out.write(System.getProperty("line.separator") + "What do you want to do?");
                out.write(System.getProperty("line.separator"));
                out.flush();

                String choice = in.readLine();
                // In case brugeren afbryder spillet, bliver choice sat til null. Så bliver der smidt exceptions over det hele.. 
                if (choice == null) {
                    choice = "q";
                }
                switch (choice) {
                    case "n":
                        // New game
                        out.write("Please select a dungeon to load:" + System.getProperty("line.separator"));
                        out.write(FileIO.readFilesInFolder("dungeons"));
                        out.write("Type the name of the dungeon you want to load:" + System.getProperty("line.separator"));
                        out.flush();
                        String dungeon = in.readLine();
                        Game newGame = new Game(dungeon, out, inS);
                        newGame.run(out, inS);
                        break;
                    case "l":
                        // Load game
                        out.write("Please select a savegame to load:" + System.getProperty("line.separator"));
                        out.write(FileIO.readFilesInFolder("savegames"));
                        out.write("Type the name of the savegame you want to load:" + System.getProperty("line.separator"));
                        out.flush();
                        String loadedGame;
                        loadedGame = in.readLine();
                        game = FileIO.loadGame(loadedGame);
                        if (game != null) {
                            game.run(out, inS);
                        }
                        break;
                    case "q":
                        stop = true;
                        break;
                    default:
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
