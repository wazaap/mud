package gameEngine;

import java.io.FileReader;
import java.io.IOException;

public class Welcome {
    public static void main(String[] args) throws IOException {
        try (FileReader inputStream = new FileReader("welcome.txt")) {


            int c;
            while ((c = inputStream.read()) != -1) {
                System.out.print((char) c);
            }
        }
    }
}
