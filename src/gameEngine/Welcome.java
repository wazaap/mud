package gameEngine;

import java.io.FileReader;
import java.io.IOException;

public class Welcome {
    public static void main(String[] args) throws IOException {
        FileReader inputStream = null;
        try {
            
            inputStream = new FileReader("welcome.txt");


            int c;
            while ((c = inputStream.read()) != -1) {
                System.out.print((char) c);
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }
}
