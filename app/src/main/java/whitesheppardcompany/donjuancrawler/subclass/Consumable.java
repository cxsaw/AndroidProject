package whitesheppardcompany.donjuancrawler.subclass;

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

    public Consumable(Effect effet) {
        this.effet = effet;
    }

    public Effect getEffet() {

        return effet;
    }

    public void setEffet(Effect effet) {
        this.effet = effet;
    }
}
