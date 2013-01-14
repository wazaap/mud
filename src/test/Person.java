/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.Serializable;

/**
 *
 * @author Mads
 */
public class Person implements Serializable {
        private String name;
        private int Age;
        private boolean danish;
        private Adress adress;

    public Person(String name, int Age, boolean danish, Adress adress) {
        this.name = name;
        this.Age = Age;
        this.danish = danish;
        this.adress = adress;
    }

    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int Age) {
        this.Age = Age;
    }

    public boolean isDanish() {
        return danish;
    }

    public void setDanish(boolean danish) {
        this.danish = danish;
    }
}
