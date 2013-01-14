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
        GameStarter game = new GameStarter(new OutputStreamWriter(System.out), new InputStreamReader(System.in) );
    }
}
