package whitesheppardcompany.donjuancrawler.IngameLogic;

import java.util.List;
import java.util.Vector;

import whitesheppardcompany.donjuancrawler.Beans.Effect;
import whitesheppardcompany.donjuancrawler.Beans.Player;
import whitesheppardcompany.donjuancrawler.Beans.Weapon;

/**
 * Created by saw on 19/12/17.
 */

/*
*
* il y a de fortes chance que tout ça ne soit jamais utilisé!
*/
public class Initialize {
    private boolean newGame = true;


        Player player = ChooseAvatar();
        //loadlvl(1);

    private void loadlvl(int i) {
    }

    // le joueur choisi son avatar
    private Player ChooseAvatar() {
        Player player1 = new Player();
        Player player2 = new Player();
        Player player3 = new Player();

        List<Weapon>weaponsCorentin = new Vector<>();
        List<Weapon>weaponsThomas = new Vector<>();
        List<Weapon>weaponsCamille = new Vector<>();

        //on crée une arme pour coco
        Effect effet   = new Effect(0,5,0);
        Weapon joyeuse = new Weapon(6,effet, 98); //position 1 ||position2 si position 2 >15, alors arme à une main
        weaponsCorentin.add(1,joyeuse);

        //on crée une arme pour bibi
        Effect effet2  = new Effect(0,30,10);
        Weapon alastor = new Weapon(6,effet2, 7); //position 1 ||position2 si position 2 >15, alors arme à une main
        weaponsThomas.add(1,alastor);

        //on crée une arme pour la miss
        Effect effet3  = new Effect(0,1,0);
        Weapon baton = new Weapon(6,effet3, 98); //position 1 ||position2 si position 2 >15, alors arme à une main
        weaponsCamille.add(1,baton);



        player1.setAttk(10);
        player1.setDef(10);
        player1.setHp(25);
        player1.setName("Corentin, le Chevalier à la petite épée");
        player1.setWallet(0);
        player1.setWeapons(weaponsCorentin);

        player2.setAttk(3);
        player2.setDef(25);
        player2.setHp(50);
        player2.setName("Thomas, le bouffon aux 1001 boutades");
        player2.setWallet(1001);
        player2.setWeapons(weaponsThomas);

        player3.setAttk(0);
        player3.setDef(0);
        player3.setHp(1);
        player3.setName("Camille, la jouvencelle en détresse");
        player3.setWallet(100000*100000);
        player3.setWeapons(weaponsCamille);

        // on choisi pas pour le moment
        return player1;


    }
}
