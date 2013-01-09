/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package character;

/**
 *
 * @author Thomas
 */
public class Monster {
    private String name;
    private String description;
    private int hitPoints;
    private int attackPoints;

    public Monster(String name, String description, int hitPoints, int attackPoints) {
        this.name = name;
        this.description = description;
        this.hitPoints = hitPoints;
        this.attackPoints = attackPoints;
    }

    public String getName() {
        return name;
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
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public int getAttackPoints() {
        return attackPoints;
    }

    public void setAttackPoints(int attackPoints) {
        this.attackPoints = attackPoints;
    }
    
    
}