package whitesheppardcompany.donjuancrawler.subclass;

import whitesheppardcompany.donjuancrawler.subclass.Item;

/**
 * Created by saw on 19/12/17.
 */

/*
    le stuff désigne la partie vétements/accessoires des items
 */

public class Stuff extends Item {


    private int position; // la position réfere à la position future sur le GUI
    private Effect effet;


    /*Getter and setters*/

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Effect getEffet() {
        return effet;
    }

    public void setEffet(Effect effet) {
        this.effet = effet;
    }

    /* méthodes héritée de la classe mère*/
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
