package whitesheppardcompany.donjuancrawler.IngameLogic;

import whitesheppardcompany.donjuancrawler.Beans.Foe;

/**
 * Created by saw on 10/01/18.
 */

public class PrefabFoe {

    public static Foe foeFactory(int id, int playerLevel){

        Foe foe;

        switch (id) {
            case 1:

                foe = new Foe(true,id,1);//foe2.png
                foe.setIntell( + playerLevel);
                foe.setSagesse(25 + playerLevel);
                foe.setHp(38 + playerLevel + 1);
                foe.setDef(12+playerLevel - 2);
                foe.setAttk(34+playerLevel - 1);
                foe.setName("Fire Spirit");
                break;
            case 2:

                foe = new Foe(true,id,1);//foe3.png
                foe.setIntell(12 + playerLevel);
                foe.setSagesse(16 + playerLevel);
                foe.setHp(42 + playerLevel + 3);
                foe.setDef(16+playerLevel - 2);
                foe.setAttk(23+playerLevel - 1);
                foe.setName("Fire Wyrm");
                break;
            case 3:

                foe = new Foe(true,id,1);
                break;
            case 4:

                 foe = new Foe(true,id,2);
                break;
            case 5:

                foe = new Foe(true,id,2);
                break;
            case 6:

                foe = new Foe(true,id,2);//foe4.png
                foe.setIntell(20 + playerLevel);
                foe.setSagesse(20 + playerLevel);
                foe.setHp(56 + playerLevel + 3);
                foe.setDef(32+playerLevel - 2);
                foe.setAttk(37+playerLevel - 1);
                foe.setName("Dragon de jade jaune");

                break;
            case 7:

                foe = new Foe(true,id,3);//foe.png
                foe.setIntell(10 + playerLevel);
                foe.setSagesse(10 + playerLevel);
                foe.setHp(26 + playerLevel + 3);
                foe.setDef(12+playerLevel - 2);
                foe.setAttk(11+playerLevel - 1);
                foe.setName("Ondine");
                break;
            case 8:

                foe = new Foe(true,id,3);//foe6.png
                foe.setIntell(15 + playerLevel);
                foe.setSagesse(19 + playerLevel);
                foe.setHp(33 + playerLevel + 3);
                foe.setDef(12+playerLevel - 2);
                foe.setAttk(13+playerLevel - 1);
                foe.setName("Mermaid hell");
                break;
            case 9:

                foe = new Foe(true,id,3);
                break;
            case 10:

                foe = new Foe(true,id,5);//foe5.png
                foe.setIntell(3 + playerLevel*2);
                foe.setSagesse(3 + playerLevel*2);
                foe.setHp(10 + playerLevel + 3);
                foe.setDef(60 + playerLevel - 2);
                foe.setAttk(1 + playerLevel - 1);
                foe.setName("Brown Snow");
                break;
            default: foe = new Foe(true,id,5);
        }

        return foe ;
    }
}
