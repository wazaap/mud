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

    public GameStarter(OutputStreamWriter outstream, InputStreamReader instream) {
        Game game;
        try {
            BufferedReader in = new BufferedReader(instream);

            boolean stop = false;
            while (!stop) {
                // Indlæs og skriv velkomstskærmen
                outstream.write(System.getProperty("line.separator"));
                outstream.write(System.getProperty("line.separator"));
                try (FileReader inputStream = new FileReader("welcome.txt")) {
                    int c;
                    while ((c = inputStream.read()) != -1) {
                        outstream.write((char) c);
                        outstream.flush();
                    }
                    outstream.write(System.getProperty("line.separator"));
                    outstream.write(System.getProperty("line.separator"));
                }

                outstream.write("MENU:" + System.getProperty("line.separator"));
                outstream.write("------------" + System.getProperty("line.separator"));
                outstream.write("n: New Game" + System.getProperty("line.separator"));
                outstream.write("l: Load Game" + System.getProperty("line.separator"));
                outstream.write("q: Quit" + System.getProperty("line.separator"));
                outstream.write(System.getProperty("line.separator") + "What do you want to do?");
                outstream.write(System.getProperty("line.separator"));
                outstream.flush();

                String choice = in.readLine();
                // In case brugeren afbryder spillet, bliver choice sat til null. Så bliver der smidt exceptions over det hele.. 
                if (choice == null) {
                    choice = "q";
                }
                switch (choice) {
                    case "n":
                        // New game
                        outstream.write("Please select a dungeon to load:" + System.getProperty("line.separator"));
                        outstream.write(FileIO.readFilesInFolder("dungeons"));
                        outstream.write("Type the name of the dungeon you want to load:" + System.getProperty("line.separator"));
                        outstream.flush();
                        String dungeon = "dungeons\\" + in.readLine();
                        Game newGame = new Game(dungeon, outstream, instream);
                        newGame.run(outstream, instream);
                        break;
                    case "l":
                        // Load game
                        outstream.write("Please select a savegame to load:" + System.getProperty("line.separator"));
                        outstream.write(FileIO.readFilesInFolder("savegames"));
                        outstream.write("Type the name of the savegame you want to load:" + System.getProperty("line.separator"));
                        outstream.flush();
                        String loadedGame;
                        loadedGame = in.readLine();
                        game = FileIO.loadGame(loadedGame);
                        if (game != null) {
                            game.run(outstream, instream);
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
