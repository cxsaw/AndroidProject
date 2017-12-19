package whitesheppardcompany.donjuancrawler.Beans;

/**
 * Created by saw on 19/12/17.
 */

/*
*   je suis sur que vous en rêviez! vous êtes en présence de divinité ici!
*   En effet le Npc(Non playable caracther) possèdera un attribut GodLike
 */
public class Npc extends Entity {

    private boolean hasQuest;


    /*override depuis la classe mère*/

    //je laisse les set attk etc.. car sait on jamais peut être qu'il sera
    //possible d'avoir des compagnons enbarqué dans la galère de notre héros
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
