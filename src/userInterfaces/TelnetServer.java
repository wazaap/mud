/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterfaces;

import gameEngine.GameMenu;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mads
 */
public class TelnetServer {

    public static void main(String[] args) {
        TelnetServer myServer = new TelnetServer();
        myServer.startServer();
    }

    public void startServer() {

        ServerSocket myServer;

        Socket myClient;
        ArrayList<Thread> threads = new ArrayList<>();
        // Opret serveren. Luk igen, hvis porten er optaget
        try {

            myServer = new ServerSocket(4444);
            while (true) {
                myClient = myServer.accept();
                System.out.println("Connection opened.");
                Thread t = new Thread(new ClientGame(myClient));
                t.start();
            }
        } catch (IOException ex) {
            System.out.println("The server was not created!");
            System.out.println(ex);
        }
    }

    class ClientGame implements Runnable {

        private Socket client;
        private OutputStreamWriter out;
        private InputStreamReader in;

        public ClientGame(Socket client) {
            try {
                this.client = client;
                this.out = new OutputStreamWriter(client.getOutputStream());
                this.in = new InputStreamReader(client.getInputStream());
            } catch (IOException ex) {
                Logger.getLogger(TelnetServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        @Override
        public void run() {
            try {
                GameMenu game = new GameMenu(out, in);
                out.close();
                in.close();
                client.close();
            } catch (IOException | NullPointerException ex) {
                Logger.getLogger(TelnetServer.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}
