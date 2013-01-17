/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package character;

import java.io.Serializable;

/**
 *
 * @author Thomas
 */
public class Monster implements Serializable {
    private static final long serialVersionUID = 19981017L;

    private String name;
    private String description;
    private int healthpoints;
    private int attackPoints;
    private int xp;

    public Monster() {
    }

    public Monster(String name, String description, int hitPoints, int attackPoints, int xp) {
        this.name = name;
        this.description = description;
        this.healthpoints = hitPoints;
        this.attackPoints = attackPoints;
        this.xp = xp;
    }

    public String getName() {
        return name;
    }

    public int getXp() {
        return xp;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getHitPoints() {
        return healthpoints;
    }

    public void setHitPoints(int hitPoints) {
        this.healthpoints = hitPoints;
    }

    public int getAttackPoints() {
        return attackPoints;
    }

    public void setAttackPoints(int attackPoints) {
        this.attackPoints = attackPoints;
    }
}
