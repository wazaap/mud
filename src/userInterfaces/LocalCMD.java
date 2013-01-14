/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterfaces;

import gameEngine.GameStarter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 *
 * @author Mads
 */
public class LocalCMD {

    public static void main(String[] args) {
        // Gets a reader and a writer of the System: command-line
        OutputStreamWriter out = new OutputStreamWriter(System.out);
        InputStreamReader in = new InputStreamReader(System.in);
        
        GameStarter game = new GameStarter(out, in);
    }
}
