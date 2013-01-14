/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterfaces;

import gameEngine.Game;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Mads
 */
public class TelnetServer {

    public static void main(String[] args) {

        ServerSocket myServer;
        Socket myClient;
        // Opret serveren. Luk igen, hvis porten er optaget
        try {
            myServer = new ServerSocket(4444);
            myClient = myServer.accept();

            OutputStreamWriter out = new OutputStreamWriter(myClient.getOutputStream());
            InputStreamReader in = new InputStreamReader(myClient.getInputStream());

            Game myGame = new Game(null, out, in);
            
        } catch (IOException ex) {
            System.out.println("The server was not created!");
            System.out.println(ex);
        }
    }
}
