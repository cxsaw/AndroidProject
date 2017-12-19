package whitesheppardcompany.donjuancrawler.subclass;

import whitesheppardcompany.donjuancrawler.subclass.Item;

/**
 * Created by saw on 18/12/17.
 */
/*
*   la class weapon représente tous les armes et boucliers
*   leurs particularité est qu'ils peuvent être à 2 ou 1 mains
*   On ne peut équiper un bouclier si on à déja un arc!
*/

public class Weapon extends Item {

    /*BONUS/MALUS des stats*/
    int addAttck;
    int addDef;
    int addHP;


    public int getAddAttck() {

        return addAttck;
    }

    public void setAddAttck(int addAttck) {

        this.addAttck = addAttck;
    }
    
    public int getAddDef() {

        return addDef;
    }

    public void setAddDef(int addDef) {

        this.addDef = addDef;
    }

    public int getAddHP()
    {
        return addHP;
    }

    public void setAddHP(int addHP) {

        this.addHP = addHP;
    }





}
