/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterfaces;

import gameEngine.GameMenu;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 *
 * @author Mads
 */
public class LocalCMD {

    /**
     * 
     * @param args 
     */
    public static void main(String[] args) {
        // Gets a reader and a writer of the System: command-line
        OutputStreamWriter out = new OutputStreamWriter(System.out);
        InputStreamReader in = new InputStreamReader(System.in);
        
        GameMenu game = new GameMenu(out, in);
    }
}
