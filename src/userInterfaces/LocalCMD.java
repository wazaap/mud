/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterfaces;

import gameEngine.Game;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 *
 * @author Mads
 */
public class LocalCMD {

    public static void main(String[] args) {
        Game myGame = new Game(null,new OutputStreamWriter(System.out), new InputStreamReader(System.in) );
    }
}
