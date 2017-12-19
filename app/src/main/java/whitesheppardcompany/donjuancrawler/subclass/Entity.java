package whitesheppardcompany.donjuancrawler.subclass;

/**
 * Created by saw on 18/12/17.
 */

public class Entity {


    private String name;

    /*   Stats */
     private int hp;
     private int def;
     private int attk;

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
}
