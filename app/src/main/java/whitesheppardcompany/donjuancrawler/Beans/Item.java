package whitesheppardcompany.donjuancrawler.Beans;

/**
 * Created by saw on 19/12/17.
 */

public abstract class Item {
    /*
    *
    *  l'item est l'objet mère qui peut aussi bien être une potion qu'une arme
    *    l'item est persisté en bdd ainsi on ne charge pas la liste de toute les itérations possibles au lancement de l'appli
    */

    private int id;
    private String itemName;
    private String itemDescription;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }
}
