package whitesheppardcompany.donjuancrawler.IngameLogic;

import java.util.List;
import java.util.Vector;

import whitesheppardcompany.donjuancrawler.Beans.Player;
import whitesheppardcompany.donjuancrawler.Beans.Weapon;

/**
 * Created by saw on 19/12/17.
 */

public class Initialize {


    Player player = ChooseAvatar();



    // le joueur choisi son avatar
    private Player ChooseAvatar() {
        Player player1 = new Player();
        Player player2 = new Player();
        Player player3 = new Player();

        List<Weapon>weaponsCorentin = new Vector<>();
        List<Weapon>weaponsThomas = new Vector<>();
        List<Weapon>weaponsCamille = new Vector<>();


        Weapon joyeuse = new Weapon();

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


        return player1;
    }
}
