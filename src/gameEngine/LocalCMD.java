/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameEngine;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 *
 * @author Mads
 */
public class LocalCMD {
    public static void main(String[] args) {
        UserInterface myGame = new UserInterface(new OutputStreamWriter(System.out), new InputStreamReader(System.in));
    }
            
}
