package whitesheppardcompany.donjuancrawler.Beans;

import java.io.Serializable;

/**
 * Created by saw on 18/12/17.
 */

public class Entity implements Serializable {

    private static long serialVersionUID = 5524L;
    private String name;

    /*   Stats */
     private int hp;
     private int def;
     private int attk;
     private int intell;   //attaque magique


    private int sagesse; //défense magique

    public Entity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public int getAttk() {
        return attk;
    }

    public void setAttk(int attk) {
        this.attk = attk;
    }


    public int getIntell() {
        return intell;
    }

    public void setIntell(int intell) {
        this.intell = intell;
    }

    public int getSagesse() {
        return sagesse;
    }

    public void setSagesse(int sagesse) {
        this.sagesse = sagesse;
    }
}
