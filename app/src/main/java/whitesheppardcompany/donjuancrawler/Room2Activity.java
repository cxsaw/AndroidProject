package whitesheppardcompany.donjuancrawler;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;

import whitesheppardcompany.donjuancrawler.Beans.Foe;
import whitesheppardcompany.donjuancrawler.Beans.Player;
import whitesheppardcompany.donjuancrawler.IngameLogic.GameplayMethod;

import static android.view.View.GONE;
import static whitesheppardcompany.donjuancrawler.IngameLogic.GameplayMethod.fadeInImg;
import static whitesheppardcompany.donjuancrawler.IngameLogic.GameplayMethod.fadeOutImg;
import static whitesheppardcompany.donjuancrawler.IngameLogic.GameplayMethod.initAttck;
import static whitesheppardcompany.donjuancrawler.IngameLogic.GameplayMethod.initFire;
import static whitesheppardcompany.donjuancrawler.IngameLogic.GameplayMethod.initWater;
import static whitesheppardcompany.donjuancrawler.IngameLogic.GameplayMethod.randomize;
import static whitesheppardcompany.donjuancrawler.IngameLogic.PrefabFoe.foeFactory;

/**
 * Created by saw on 08/01/18.
 */

public class Room2Activity extends AppCompatActivity {

    Context context = Room2Activity.this;
    Player player;
    int dice;
    Foe foe;
    boolean resolved; // me sert à verifier si l'event en cours est terminé!
    private int hpFoe = 9999;
    private boolean canPressBackButton = false;

    ImageButton upBtn;
    ImageButton leftBtn;
    ImageButton rightBtn;

    MediaPlayer mp;
    ImageButton shortcutQuestBtn;
    ImageButton avatarInfoBtn;
    ImageView   avatarImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("DEBUG", "Ici c'est bon!");
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_room);
        ImageView   foeImg       = (ImageView)   findViewById(R.id.foe);

        upBtn        = (ImageButton) findViewById(R.id.goUp);
        leftBtn      = (ImageButton) findViewById(R.id.goLeft);
        rightBtn     = (ImageButton) findViewById(R.id.goRight);

        shortcutQuestBtn  = (ImageButton) findViewById(R.id.questShortcut);
        avatarInfoBtn     = (ImageButton) findViewById(R.id.avatarInfo);
        avatarImg         = (ImageView)   findViewById(R.id.infoPerso);

        ImageView   gameOverImg  = (ImageView)   findViewById(R.id.gameOver);
        ImageView   locateoImg   = (ImageView)   findViewById(R.id.locateView);
        ImageButton avatarBtn    = (ImageButton) findViewById(R.id.avatarInfo);

        ImageButton attkBtn      = (ImageButton) findViewById(R.id.swo);
        ImageButton fireBtn      = (ImageButton) findViewById(R.id.fireSpell);
        ImageButton waterBtn     = (ImageButton) findViewById(R.id.waterSpell);
        ImageButton lightningBtn = (ImageButton) findViewById(R.id.lightningSpell);

        foeImg.setVisibility(View.INVISIBLE);
        player = (Player)getIntent().getSerializableExtra("player");

        upBtn.setVisibility(GONE);
        leftBtn.setVisibility(GONE);
        rightBtn.setVisibility(GONE);

        upBtn.setClickable(false);
        leftBtn.setClickable(false);
        rightBtn.setClickable(false);

        avatarInfoBtn.setClickable(true);
        shortcutQuestBtn.setClickable(true);

        dice = randomize();

        mp = MediaPlayer.create(context,R.raw.main);
        mp.start();
        mp.setLooping(true);


        //je set l'avatar correspondant au joueur en faisant correspondre l'id à l'image
        switch(player.getId()){

            case 2:
                avatarInfoBtn.setImageResource(R.drawable.ava3);
                avatarImg.setImageResource(R.drawable.bgcam);
                break;
            case 3:
                avatarInfoBtn.setImageResource(R.drawable.ava2);
                avatarImg.setImageResource(R.drawable.bgtoto);
                break;
            default:
                avatarInfoBtn.setImageResource(R.drawable.ava);
                avatarImg.setImageResource(R.drawable.bgcoco);
        }
        /***************************************************************
         *
         * Bouton listener avatar
         *
         *
         *
         *
         *****************************************************************/

        avatarInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (avatarImg.getVisibility() == View.GONE){
                    avatarImg.setVisibility( View.VISIBLE );
                } else {
                    avatarImg.setVisibility(View.GONE);
                }

            }
        });

        /*******************************************************************
         *
         *
         *
         *   Listenner quete log
         *
         *
         ********************************************************************/

        shortcutQuestBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                final ImageButton log   = (ImageButton)findViewById(R.id.questDisplay);
                final TextView txtQuest = (TextView) findViewById(R.id.questText);
                Log.i("DEBUG","dans le listener quete2");
                log.setVisibility(View.VISIBLE);
                txtQuest.setVisibility(View.VISIBLE);
                log.setClickable(true);
                log.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.i("DEBUG","dans le deuxieme listenner uéééééééééé2");
                        log.setVisibility(GONE);
                        log.setClickable(false);
                        txtQuest.setVisibility(GONE);
                    }
                });
            }
        });
        /**
         *
         *
         *
         * Navigation Listenner
         *
         *
         */

        upBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,Room2Activity.class);
                intent.putExtra("player",(Serializable) player);
                context.startActivity(intent);
                finish();
            }
        });
        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,Room2Activity.class);
                intent.putExtra("player",(Serializable) player);
                context.startActivity(intent);
                finish();
            }
        });
        rightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,Room2Activity.class);
                intent.putExtra("player",(Serializable) player);
                context.startActivity(intent);
                finish();
            }
        });


        Log.i("DEBUG", "dice >>>"+dice);
        if (dice <= 70) { //dans ces cas là on à un pop de mob
            //pour donner de l'aléatoire un peu je cherche un nombre entre un et 10 qui me donnera un monstre

            int futurId = (int)(randomize() / 10) ;
            switch (futurId){
                case 1:

                    foeImg.setImageResource(R.drawable.foe2);
                    fadeInImg(foeImg);
                    break;

                case 2:

                    foeImg.setImageResource(R.drawable.foe3);
                    fadeInImg(foeImg);
                    break;

                case 3:
                    foeImg.setImageResource(R.drawable.defaultcard);
                    fadeInImg(foeImg);
                    break;

                case 4:
                    foeImg.setImageResource(R.drawable.defaultcard);
                    fadeInImg(foeImg);
                    break;

                case 5:
                    foeImg.setImageResource(R.drawable.defaultcard);
                    fadeInImg(foeImg);
                    break;

                case 6:

                    foeImg.setImageResource(R.drawable.foe4);
                    fadeInImg(foeImg);
                    break;

                case 7:

                    foeImg.setImageResource(R.drawable.foe);
                    fadeInImg(foeImg);
                    break;

                case 8:
                    foeImg.setImageResource(R.drawable.defaultcard);
                    foeImg.setImageResource(R.drawable.foe6);
                    fadeInImg(foeImg);
                    break;

                case 9:
                    foeImg.setImageResource(R.drawable.defaultcard);
                    fadeInImg(foeImg);
                    break;
                case 10:

                    foeImg.setImageResource(R.drawable.foe5);
                    fadeInImg(foeImg);
                    break;

                default:
                    foeImg.setImageResource(R.drawable.defaultcard);
                    fadeInImg(foeImg);

            }

           foe = foeFactory(futurId, player.getLevel());

        }

        /*
        * dans ces cas on pop un pnj
        * mais comme j'ai pas implémanter!
        * on se casse!
        */

        if ( dice <= 91 && dice >= 71) {
            upBtn.setClickable(true);
            leftBtn.setClickable(true);
            rightBtn.setClickable(true);
        }

        //dans ces cas on pop RIEN
        if (dice >= 92 && dice <= 96){
            wayResolver();

        }

        //dans ces cas on pop un trésor!
        if (dice >= 97 && dice <= 100){
            resolved = loot();
            wayResolver();

        }

        if (foeImg.getVisibility() == View.VISIBLE ){
            fight(player, foe);

        }


    }
    /*
    *
    *
    * Le resolver est une fonction qui va choisir quels arrow vont s'activer
    *
    */
    private void  wayResolver(){
        if (resolved == true){
            int rand = ( int )( Math.random() * (3 - 1));
            switch (rand){
                case 1:
                    upBtn.setClickable(true);
                    leftBtn.setClickable(true);
                    rightBtn.setClickable(true);
                    break;

                case 2:
                    upBtn.setVisibility(View.VISIBLE);
                    upBtn.setClickable(true);
                    leftBtn.setVisibility(GONE);
                    rightBtn.setVisibility(GONE);
                    break;
                case 0:
                    upBtn.setVisibility(GONE);
                    leftBtn.setVisibility(View.VISIBLE);
                    leftBtn.setClickable(true);
                    rightBtn.setVisibility(GONE);
                    break;
                default:
                    upBtn.setVisibility(GONE);
                    leftBtn.setVisibility(GONE);
                    rightBtn.setVisibility(View.VISIBLE);
                    rightBtn.setClickable(true);
                    break;
            }
        }

    }



    private boolean loot() {
        return true;
    }


    /*
    *
    *
    *
    *
    *
    *   Methode fight !
    *
    *
    *
    */
    private void fight(final Player player, final Foe firstFoe) {
        Log.i("DEBUG"," !baston2!");


        //on initialise les boutons de l'interfaces
        final ImageButton attackBtn         = (ImageButton) findViewById(R.id.swo);
        final ImageButton fireSpellBtn      = (ImageButton) findViewById(R.id.fireSpell);
        final ImageButton waterSpellBtn     = (ImageButton) findViewById(R.id.waterSpell);
        final ImageButton lightningSpellBtn = (ImageButton) findViewById(R.id.lightningSpell);

        //je mets les cliquers en route!

        fireSpellBtn.setClickable(true);
        waterSpellBtn.setClickable(true);
        lightningSpellBtn.setClickable(true);
        attackBtn.setClickable(true);




        Log.i("DEBUG"," kiké plus fort?2");
        ImageView bim =(ImageView)findViewById(R.id.bim);
        bim.setVisibility(View.INVISIBLE);



        /**********************************************************************
         *
         *
         *                              attackBtn listener
         *
         *
         *      [O\\\\\[========================-
         *
         * ************************************************************************/

        // tout le listener gère la phase d'attaque aux armes lors du clique sur le bouton épée
        attackBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                TextView hpPlayerText     = (TextView) findViewById(R.id.playerLife);
                TextView hpFoeText        = (TextView) findViewById(R.id.foeLife);
                ImageView imgFoe          = (ImageView) findViewById(R.id.foe);
                ImageView bim             = (ImageView) findViewById(R.id.bim);


                hpFoe = firstFoe.getHp();

                //hpFoeText.setVisibility(View.VISIBLE);
                //hpPlayerText.setVisibility(View.VISIBLE);
                //set les var dans le textView
                //hpFoeText.setText(vieFoe);
                //hpPlayerText.setText(viePlayer);
                Log.i("DEBUG", "Non le prob n'est pôas là2");

                //phase attaque joueur
                //initie l'attaque

                hpFoe = initAttck(firstFoe, player);
                firstFoe.setHp(hpFoe);
                if (firstFoe.getHp() <= 0){
                    firstFoe.setAlive(false);
                }
                Log.e("DEBUG", "vie mechant apres combat2<<<<"+firstFoe.getHp());

                //si le mechant est vivant, il riposte
                if(firstFoe.isAlive() == true){

                    player.setHp(retribution(firstFoe, player));
                    Log.e("DEBUG", "vie joueur apres combat2<<<<"+player.getHp());

                    //image de griffure pour visuellement verifier si il y a dégâts
                    //le handler sert à timer le coup pour qu'il s'affiche ponctuellement

                }else {

                    wayResolver();
                    attackBtn.setClickable(false);
                    fireSpellBtn.setClickable(false);
                    waterSpellBtn.setClickable(false);
                    lightningSpellBtn.setClickable(false);
                    //et il disparait

                    fadeOutImg(imgFoe);
                    bim.setVisibility(GONE);
                }
                //si le joueur est mort on affiche un game over
                if(player.getHp() <= 0){

                    mp.stop();
                    mp.release();
                    MediaPlayer mpGameover = MediaPlayer.create(context, R.raw.gameover);
                    mpGameover.start();
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    player.setAlive(false);
                    //forcément si on meurt on ne peut attaquer
                    attackBtn.setClickable(false);
                    fireSpellBtn.setClickable(false);
                    waterSpellBtn.setClickable(false);
                    lightningSpellBtn.setClickable(false);

                    //on lance le GameOver
                    ImageView gameOver =(ImageView)findViewById(R.id.gameOver);
                    gameOver.setVisibility(View.VISIBLE);

                }

                if (firstFoe.getHp() <= 0){
                    loot();
                    //je permets l'accés à la salle suivante

                    wayResolver();




                }
            }
        });

        /****************************************************************************************
         *
         *       Bouton de spell FEU
         *
         *
         *                            (  .      )
         *                         )           (              )
         *                             .  '   .   '  .  '  .
         *                     (    , )       (.   )  (   ',    )
         *                   .' ) ( . )    ,  ( ,     )   ( .
         *                  ). , ( .   (  ) ( , ')  .' (  ,    )
         *                 (_,) . ), ) _) _,')  (, ) '. )  ,. (' )
         *     FUER FREII^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^BANG BANG!
         *
         *
         ********************************************************************************************/


        fireSpellBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                ImageView imgFoe      = (ImageView) findViewById(R.id.foe);
                ImageView bim         = (ImageView) findViewById(R.id.bim);


                hpFoe    = firstFoe.getHp();

                Log.i("DEBUG", "Non le prob n'est pôas là2");

                //phase attaque joueur
                //initie l'attaque

                hpFoe = initFire(firstFoe, player);
                firstFoe.setHp(hpFoe);

                if (firstFoe.getHp() <= 0){
                    firstFoe.setAlive(false);
                }

                Log.e("DEBUG", "vie mechant apres combat2<<<<"+firstFoe.getHp());

                //si le mechant est vivant, il riposte
                if(firstFoe.isAlive() == true){

                    player.setHp(retribution(firstFoe, player));
                    Log.e("DEBUG", "vie joueur apres combat2<<<<"+player.getHp());

                    //image de griffure pour visuellement verifier si il y a dégâts
                    //le handler sert à timer le coup pour qu'il s'affiche ponctuellement

                }else {
                    wayResolver();
                    attackBtn.setClickable(false);
                    fireSpellBtn.setClickable(false);
                    waterSpellBtn.setClickable(false);
                    lightningSpellBtn.setClickable(false);
                    //et il disparait

                    fadeOutImg(imgFoe);
                    bim.setVisibility(GONE);
                }
                //si le joueur est mort on affiche un game over
                if(player.getHp() <= 0){

                    mp.stop();
                    mp.release();

                    MediaPlayer mpGameover = MediaPlayer.create(context, R.raw.gameover);
                    mpGameover.start();
                    player.setAlive(false);
                    //forcément si on meurt on ne peut attaquer
                    attackBtn.setClickable(false);
                    fireSpellBtn.setClickable(false);
                    waterSpellBtn.setClickable(false);
                    lightningSpellBtn.setClickable(false);
                    //on lance le GameOver
                    ImageView gameOver =(ImageView)findViewById(R.id.gameOver);
                    gameOver.setVisibility(View.VISIBLE);

                }

                if (firstFoe.getHp() <= 0){
                    loot();
                    wayResolver();
                }
            }
        });

        /************************************************************************************
         *                           ____________________¶¶
         *                           ___________________¶¶¶
         *                           __________________¶¶_¶¶
         *                           __________________¶¶__¶
         *                           _________________¶¶¶__¶¶
         *                           ________________¶¶¶___¶¶
         *                           ________________¶¶¶___¶¶
         *                           _______________¶¶¶_____¶¶
         *                           ______________¶¶¶_______¶¶
         *                           _____________¶¶¶¶_______¶¶
         *        Water spell        _____________¶¶¶_________¶¶
         *                           ____________¶¶¶___________¶¶
         *                           ___________¶¶¶_____________¶¶
         *                           __________¶¶¶______________¶¶
         *                           __________¶¶________________¶¶
         *                           _________¶¶__________________¶¶
         *                           ________¶¶____________________¶¶
         *                           _______¶¶______________________¶¶
         *                           ______¶¶________________________¶¶
         *                           _____¶¶_________________________¶¶
         *                           ____¶¶¶__________________________¶¶
         *                           ____¶¶____________________________¶¶
         *                           ___¶¶_____________________________¶¶¶
         *                           ___¶¶______________________________¶¶
         *                           __¶¶_______________________________¶¶¶
         *                           __¶¶________________________________¶¶
         *                           _¶¶_________________________________¶¶¶
         *                           _¶¶_________________________________¶¶¶
         *                           _¶¶_________________________________¶¶¶
         *                           _¶¶_________________________________¶¶
         *                           _¶¶_________________________________¶¶
         *                           _¶¶________________________________¶¶¶
         *                           __¶¶_______________________________¶¶
         *                           __¶¶______________________________¶¶
         *                           __¶¶¶____________________________¶¶¶
         *                           ___¶¶¶__________________________¶¶¶
         *                           ____¶¶¶________________________¶¶¶
         *                           _____¶¶¶¶¶___________________¶¶¶¶
         *                            ______¶¶¶¶¶¶¶¶____________¶¶¶¶¶
         *                           _______¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶
         *                           ____________¶¶¶¶¶¶¶¶¶¶¶¶¶¶
         *************************************************************************************/
        waterSpellBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                ImageView imgFoe      = (ImageView) findViewById(R.id.foe);
                ImageView bim         = (ImageView) findViewById(R.id.bim);

                hpFoe    = firstFoe.getHp();

                Log.i("DEBUG", "Non le prob n'est pôas là2");

                //phase attaque joueur
                //initie l'attaque

                hpFoe = initWater(firstFoe, player);
                firstFoe.setHp(hpFoe);
                if (firstFoe.getHp() <= 0){
                    firstFoe.setAlive(false);
                }
                Log.e("DEBUG", "vie mechant apres combat<<<<2"+firstFoe.getHp());

                //si le mechant est vivant, il riposte
                if(firstFoe.isAlive() == true){

                    player.setHp(retribution(firstFoe, player));
                    Log.e("DEBUG", "vie joueur apres combat<<<<2"+player.getHp());

                    //image de griffure pour visuellement verifier si il y a dégâts
                    //le handler sert à timer le coup pour qu'il s'affiche ponctuellement

                }else {
                    wayResolver();
                    attackBtn.setClickable(false);
                    attackBtn.setClickable(false);
                    fireSpellBtn.setClickable(false);
                    waterSpellBtn.setClickable(false);
                    lightningSpellBtn.setClickable(false);
                    //et il disparait

                    fadeOutImg(imgFoe);
                    bim.setVisibility(GONE);
                }
                //si le joueur est mort on affiche un game over
                if(player.getHp() <= 0){

                    mp.stop();
                    mp.release();

                    MediaPlayer mpGameover = MediaPlayer.create(context, R.raw.gameover);
                    mpGameover.start();
                    player.setAlive(false);
                    //forcément si on meurt on ne peut attaquer
                    attackBtn.setClickable(false);
                    fireSpellBtn.setClickable(false);
                    waterSpellBtn.setClickable(false);
                    lightningSpellBtn.setClickable(false);

                    //on lance le GameOver
                    ImageView gameOver =(ImageView)findViewById(R.id.gameOver);
                    gameOver.setVisibility(View.VISIBLE);

                }

                if (firstFoe.getHp() <= 0){
                    loot();
                    wayResolver();
                }
            }
        });
        /**********************************************************************************
         *              ___(                        )                                     *
         *             (                          _)                                      *
         *            (_                       __))                                       *
         *              ((                _____)                                          *
         *                (_________)----'                                                *
         *                   _/  /                                                        *
         *                  /  _/                                                         *
         *                _/  /                                                           *
         *               / __/                                                            *
         *             _/ /                                                               *
         *            /__/                                                                *
         *           //                                                                   *
         *          /'    Lightning spell                                                 *
         *                                                                                *
         *                                                                                *
         **********************************************************************************/
        lightningSpellBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                ImageView imgFoe      = (ImageView) findViewById(R.id.foe);
                ImageView bim         = (ImageView) findViewById(R.id.bim);

                hpFoe    = firstFoe.getHp();

                Log.i("DEBUG", "Non le prob n'est pôas là2");

                //phase attaque joueur
                //initie l'attaque

                hpFoe = GameplayMethod.initLight(firstFoe, player);
                firstFoe.setHp(hpFoe);
                if (firstFoe.getHp() <= 0){
                    firstFoe.setAlive(false);
                }
                Log.e("DEBUG", "vie mechant apres combat<<<<2"+firstFoe.getHp());

                //si le mechant est vivant, il riposte
                if(firstFoe.isAlive() == true){

                    player.setHp(retribution(firstFoe, player));
                    Log.e("DEBUG", "vie joueur apres combat<<<<2"+player.getHp());

                    //image de griffure pour visuellement verifier si il y a dégâts
                    //le handler sert à timer le coup pour qu'il s'affiche ponctuellement

                }else {
                    wayResolver();

                    attackBtn.setClickable(false);
                    attackBtn.setClickable(false);
                    fireSpellBtn.setClickable(false);
                    waterSpellBtn.setClickable(false);
                    lightningSpellBtn.setClickable(false);
                    //et il disparait

                    fadeOutImg(imgFoe);
                    bim.setVisibility(GONE);
                }
                //si le joueur est mort on affiche un game over
                if(player.getHp() <= 0){

                    mp.stop();
                    mp.release();

                    MediaPlayer mpGameover = MediaPlayer.create(context, R.raw.gameover);
                    mpGameover.start();
                    player.setAlive(false);
                    //forcément si on meurt on ne peut attaquer
                    attackBtn.setClickable(false);
                    fireSpellBtn.setClickable(false);
                    waterSpellBtn.setClickable(false);
                    lightningSpellBtn.setClickable(false);
                    //on lance le GameOver
                    ImageView gameOver =(ImageView)findViewById(R.id.gameOver);
                    gameOver.setVisibility(View.VISIBLE);

                }

                if (firstFoe.getHp() <= 0){
                    loot();
                    wayResolver();
                }
            }
        });


    }


    //représaille de l'ennemi
    private int retribution(Foe foe, Player player) {
        Log.e("DEBUG", "MOI PAS CONTENT2");
        int hpPlayer    = player.getHp();
        int attckFoe    = foe.getAttk();
        int defPlayer   = player.getDef();
        ImageView bim = (ImageView) findViewById(R.id.bim);

        Handler clawHandler = new Handler();
        clawHandler.postDelayed(new Runnable() {
            ImageView bim = (ImageView) findViewById(R.id.bim);

            @Override
            public void run() {

                bim.setVisibility(GONE);
                MediaPlayer mpSound = MediaPlayer.create(context, R.raw.fight1);
                mpSound.start();
            }
        }, 200);

        bim.setVisibility(View.VISIBLE);

        if(defPlayer < attckFoe ) {
            Log.e("DEBUG", "EZ2");
            return hpPlayer - (attckFoe - defPlayer);

        }else{
            Log.e("DEBUG", "ouch2");
            return hpPlayer - 1;
        }
    }


    @Override
    public  void onDestroy(){
        mp.stop();
        mp.release();
        super.onDestroy();
    }



    @Override
    public void onBackPressed(){
        if(!canPressBackButton){

        } else {
            super.onBackPressed();
        }
    }

}
