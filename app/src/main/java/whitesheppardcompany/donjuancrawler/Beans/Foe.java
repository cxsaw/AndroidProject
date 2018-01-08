package whitesheppardcompany.donjuancrawler.Beans;

import java.io.Serializable;

/**
 * Created by saw on 18/12/17.
 */
/*
* foe est le terme générique choisi pour implémenter les ennemis
* il n'ajoute rien de plus à l'entity si ce n'est la possibilité de loot via l'une des classes d'Ingamelogique
* que j'implémenterais après.
*/

public class Foe extends Entity implements Serializable {

    private static long serialVersionUID = 5524L;

    boolean alive;

    int element;

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    /*
                    **Tableau Périodique des éléments
    *Element 1 = feu;
    *Element 2 = lightning;
    *Element 3 = water;
    *Element 5 = neutre
    *   lightning> water > feu > ligthning
    *       2       3       1       2
    */

    public Foe() {
    }

    public int getElement() {
        return element;
    }

    public void setElement(int element) {
        this.element = element;
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public int getHp() {
        return super.getHp();
    }

    @Override
    public void setHp(int hp) {
        super.setHp(hp);
    }

    @Override
    public int getDef() {
        return super.getDef();
    }

    @Override
    public void setDef(int def) {
        super.setDef(def);
    }

    @Override
    public int getAttk() {
        return super.getAttk();
    }

    @Override
    public void setAttk(int attk) {
        super.setAttk(attk);
    }

}