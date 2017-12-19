package whitesheppardcompany.donjuancrawler.Beans;

/**
 * Created by saw on 18/12/17.
 */
/*
*   la class weapon représente tous les armes et boucliers
*   leurs particularité est qu'ils peuvent être à 2 ou 1 mains
*   On ne peut équiper un bouclier si on à déja un arc!
*/

public class Weapon extends Item {


    private int position;// la position réfere à la position future sur le GU
    private int position2;//si la position2 vaut 6 ou 7, alors l'arme est considéré comme à 2 mains et override l'emplacement correspondant
    private Effect effet;


    /*Getters & Setters*/

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition2() {
        return position2;
    }

    public void setPosition2(int position2) {
        this.position2 = position2;
    }

    public Effect getEffet() {
        return effet;
    }

    public void setEffet(Effect effet) {
        this.effet = effet;
    }


    /*
    *
    *
    * Override méthode depuis la classe mère item
    *
    * */
    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public void setId(int id) {
        super.setId(id);
    }

    @Override
    public String getItemName() {
        return super.getItemName();
    }

    @Override
    public void setItemName(String itemName) {
        super.setItemName(itemName);
    }

    @Override
    public String getItemDescription() {
        return super.getItemDescription();
    }

    @Override
    public void setItemDescription(String itemDescription) {
        super.setItemDescription(itemDescription);
    }


}
