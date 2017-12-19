package whitesheppardcompany.donjuancrawler.Beans;

/**
 * Created by saw on 19/12/17.
 */

/*
    Les consommables ont cette particularité d'être à usage unique
    une fois consommé, elles sont détruite!
    elles implementes obligatoirement un effet!
*/

public class Consumable extends Item {

    private Effect effet;


    /*
    *
    * Setters & Getters
    *
    */

    public Consumable(Effect effet) {
        this.effet = effet;
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
    *   Overide depuis la classe mère
    *
    */

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
