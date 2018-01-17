package whitesheppardcompany.donjuancrawler.IngameLogic;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import whitesheppardcompany.donjuancrawler.Beans.Foe;
import whitesheppardcompany.donjuancrawler.Beans.Player;

/**
 * Created by saw on 08/01/18.
 */

public class GameplayMethod {
//méthode de gameplay et autres, quand il est possible de les extraire!



    /*
    *
    *
    *
    * méthodes pour un rendu fondu
    *
    *
    * */
    public static void fadeInImg(ImageView imageView)  {

        imageView.setVisibility(View.VISIBLE);
//Animation fade in
        ObjectAnimator fadeInImageView = ObjectAnimator.ofFloat(imageView, imageView.ALPHA, 0,1);
        fadeInImageView.setDuration(1200);
        AnimatorSet fadeInAnimator = new AnimatorSet();
        fadeInAnimator.play(fadeInImageView);
        fadeInAnimator.start();
    }

    public static void fadeOutImg(ImageView imageView) {

//Animation fade in
        ObjectAnimator fadeOutImageView = ObjectAnimator.ofFloat(imageView, imageView.ALPHA, 1,0);
        fadeOutImageView.setDuration(500);
        AnimatorSet fadeOutAnimator = new AnimatorSet();
        fadeOutAnimator.play(fadeOutImageView);
        fadeOutAnimator.start();
    }

    /********************************************************
    *                                                       *
    *                                                       *
    *       Méthode de génération d'expérience qui dépend   *
    *       du niveau du joueur                             *
    *                                                       *
    ********************************************************/

    public static Player generateExp(Player player, Foe foe){
        int currentLvl         = player.getLevel();
        int currentProgression = player.getLvlQuantity();
        int quantityExpNeeded  =(int) (1000 + ((1000*0.1) * currentLvl)); //augmente de 10% * niveau du joueur

        int expBase            = 100;

        if(foe.isAlive() == false){
            int expGot = expBase * foe.getDifficulty();
            player.setLvlQuantity((currentProgression+expGot)); //ajout de l'exp reçu à l'xp actuel

            // ce while gère le passage de niveau
            // j'ai préféré le while au if pour gérer le passage de plusieurs niveau d'un coup
            while(currentProgression >= quantityExpNeeded){
                currentProgression = currentProgression - quantityExpNeeded;
                player.setLvlQuantity(currentProgression); //ajout de la progression de la barre d'exp
                currentLvl ++; //
                player.setLevel(currentLvl);
            }

        }
       return player;
    }


    //le joueur initie l'attaque
    public static int initAttck(Foe foe, Player player) {

        Log.i("DEBUG", "je lui casse la machoire");

        int hpFoe       = foe.getHp();
        int attckPlayer = player.getAttk();
        int defFoe      = foe.getDef();
        if (hpFoe <=0){
            return hpFoe;
        }
        if(defFoe < attckPlayer ) {

            Log.i("DEBUG", "il est en mousse");
            return hpFoe - (attckPlayer - defFoe);
        }else{
            Log.i("DEBUG", "il est keuss l'autre tarba");
            return hpFoe - 1;
        }

    }

    //métode du cast de l'éclair
    public static int initLight(Foe foe, Player player) {
        Log.i("DEBUG", "ja VOLTTTTTTTTTTT her colonel!");

        int hpFoe = foe.getHp();
        int intelPlayer = player.getIntell();
        int sagesseFoe = foe.getSagesse();
        int elmt = foe.getElement();


        if (intelPlayer >= sagesseFoe) {
            switch (elmt) {
                case 1://fire
                    Log.i("DEBUG", "1");
                    //echec total pas de dégat
                    break;
                case 2://eleck
                    Log.i("DEBUG", "2");
                    hpFoe =- 5;    //normale attaque
                    break;
                case 3://eau
                    Log.i("DEBUG", "3");
                    hpFoe =- 25; //attaque critique
                    break;
                case 5:
                    Log.i("DEBUG", "5");
                    hpFoe = initAttck(foe, player); // dans le cas d'un élément neutre on envoie les dégats classique
                    break;
            }
        } else {
            switch (elmt) {
                case 1://feu
                    Log.i("DEBUG", "1");
                    //echec total pas de dégat
                    break;
                case 2://eleck
                    Log.i("DEBUG", "2");
                    hpFoe =- 10; //attaque critique

                    break;
                case 3://eau
                    Log.i("DEBUG", "3");
                    hpFoe =- 2;    //normale attaque
                    break;
                case 5:
                    Log.i("DEBUG", "5");
                    initAttck(foe, player); // dans le cas d'un élément neutre on envoie les dégats classique
                    break;
            }
        }
        Log.e("DEBUG","erzrzrez" + hpFoe);
        return hpFoe;
    }

    //méthode de random pour les event

    public  static int randomize(){
        /*
        *
        * le but ci est de retourner une nombre compris entre 1 et 100
        * 0-70 mob pop
        * 71-91 npc pop
        * 91-96 nothing
        * 96-100 treasure
        */

        int rand;
          rand = ( int )( Math.random() * (100 - 1));
          Log.e("DEBUG", "réandom <<<<<<<" +rand);
        return rand;

    }

    //méthode du cast de l'eau
    public static int initWater(Foe foe, Player player) {
        Log.i("DEBUG", "Alors, tu mouilles?");

        int hpFoe = foe.getHp();
        int intelPlayer = player.getIntell();
        int sagesseFoe = foe.getSagesse();
        int elmt = foe.getElement();


        if (intelPlayer >= sagesseFoe) {
            switch (elmt) {
                case 1://feu
                    Log.i("DEBUG", "1");

                    hpFoe =- 25; //attaque critique
                    break;

                case 2://eleck
                    Log.i("DEBUG", "2");
                    //echec total pas de dégat
                    break;
                case 3://eau
                    Log.i("DEBUG", "3");
                    hpFoe =- 5;    //normale attaque
                    break;
                case 5:
                    Log.i("DEBUG", "5");
                    initAttck(foe, player); // dans le cas d'un élément neutre on envoie les dégats classique
                    break;
            }
        } else {
            switch (elmt) {
                case 1://feu
                    Log.i("DEBUG", "1");
                    hpFoe =- 10; //attaque critique
                    break;
                case 2://eleck
                    Log.i("DEBUG", "2");
                    //echec total pas de dégat
                    break;
                case 3://eau
                    Log.i("DEBUG", "3");
                    hpFoe =- 2;    //normale attaque
                    break;
                case 5://eau
                    Log.i("DEBUG", "5");
                    initAttck(foe, player); // dans le cas d'un élément neutre on envoie les dégats classique
                    break;
            }
        }
        Log.e("DEBUG","erzrzrez" + hpFoe);
        return hpFoe;// je ne devrais pas arriver ici
    }

    //méthode du cast du feu
    public static int initFire(Foe foe, Player player) {
        Log.i("DEBUG", "je joue avec le feu");

        int hpFoe = foe.getHp();
        int intelPlayer = player.getIntell();
        int sagesseFoe = foe.getSagesse();
        int elmt = foe.getElement();


        if (intelPlayer >= sagesseFoe) {
            switch (elmt) {
                case 1://feu
                    Log.i("DEBUG", "1");
                    hpFoe =- 5;    //normale attaque

                    break;
                case 2://eleck
                    Log.i("DEBUG", "2");
                    hpFoe =- 25; //attaque critique
                    break;
                case 3://eau
                    Log.i("DEBUG", "3");
                    //nothing echec total
                    break;
                case 5:
                    Log.i("DEBUG", "5");
                    hpFoe = initAttck(foe, player); // dans le cas d'un élément neutre on envoie les dégats classique
                    break;
            }
        } else {
            switch (elmt) {
                case 1://feu
                    Log.i("DEBUG", "1");
                    hpFoe =- 2;    //normale attaque
                    break;
                case 2://eleck
                    hpFoe =- 10; //attaque critique
                    Log.i("DEBUG", "2");
                    break;
                case 3://eau
                    Log.i("DEBUG", "3");
                    //echec
                    break;
                case 5:
                    Log.i("DEBUG", "5");
                    hpFoe = initAttck(foe, player); // dans le cas d'un élément neutre on envoie les dégats classique
                    break;
            }
        }
        Log.e("DEBUG","erzrzrez" + hpFoe);
        return hpFoe;

    }


}
