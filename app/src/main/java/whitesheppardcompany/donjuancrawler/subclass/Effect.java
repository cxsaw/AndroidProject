package whitesheppardcompany.donjuancrawler.subclass;

/**
 * Created by saw on 19/12/17.
 */

public class Effect {

    public int restoreHp;
    public int addAttk;
    public int addDef;


    public Effect() {

    }

    public Effect(int restoreHp, int addAttk, int addDef) {
        this.restoreHp = restoreHp;
        this.addAttk = addAttk;
        this.addDef = addDef;
    }

    /*
        *       Getters & setters
        **/
    public int getAddAttk() {
        return addAttk;
    }

    public void setAddAttk(int addAttk) {
        this.addAttk = addAttk;
    }

    public int getAddDef() {
        return addDef;
    }

    public void setAddDef(int addDef) {
        this.addDef = addDef;
    }

    public int getRestoreHp() {

        return restoreHp;
    }

    public void setRestoreHp(int restoreHp) {
        this.restoreHp = restoreHp;
    }
}
