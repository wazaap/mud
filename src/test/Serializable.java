/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mads
 */
public class Serializable {

    public static void main(String[] args) {
        String filename = "persons.txt";
        Person person1 = new Person("hemanth", 10, true, new Adress("Sundbyvestervej", 2300, "Denmark"));
        Person person2 = new Person("bob", 12, false, new Adress("H.C.vej", 1234, "Denmark"));
        Person person3 = new Person("Richa", 10, true, new Adress("Din mors vej 69", 4200, "England"));

        List list = new ArrayList();
        list.add(person1);
        list.add(person2);
        list.add(person3);


        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream(filename);
            out = new ObjectOutputStream(fos);
            out.writeObject(list);
            out.close();
            System.out.println("Object Persisted");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
