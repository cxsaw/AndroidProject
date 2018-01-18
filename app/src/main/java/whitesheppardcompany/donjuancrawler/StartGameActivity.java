package whitesheppardcompany.donjuancrawler;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.Image;
import android.media.MediaExtractor;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

import whitesheppardcompany.donjuancrawler.Beans.Foe;
import whitesheppardcompany.donjuancrawler.Beans.Player;
import whitesheppardcompany.donjuancrawler.IngameLogic.GameplayMethod;

import static android.view.View.GONE;
import static whitesheppardcompany.donjuancrawler.IngameLogic.GameplayMethod.fadeInImg;
import static whitesheppardcompany.donjuancrawler.IngameLogic.GameplayMethod.fadeOutImg;
import static whitesheppardcompany.donjuancrawler.IngameLogic.GameplayMethod.generateExp;
import static whitesheppardcompany.donjuancrawler.IngameLogic.GameplayMethod.initAttck;
import static whitesheppardcompany.donjuancrawler.IngameLogic.GameplayMethod.initFire;
import static whitesheppardcompany.donjuancrawler.IngameLogic.GameplayMethod.initLight;
import static whitesheppardcompany.donjuancrawler.IngameLogic.GameplayMethod.initWater;
import static whitesheppardcompany.donjuancrawler.IngameLogic.GameplayMethod.randomize;

public class StartGameActivity extends AppCompatActivity {

    Context context = StartGameActivity.this;
    private Player player;
    private Foe firstFoe = new Foe();
    // je mets 9999 pour ne pas que le jeu declare le mob mort des le debut de la phase
    private int hpFoe = 9999;
    private boolean canPressBackButton = false;
    MediaPlayer mp;
    Handler setDelay;
    Runnable startDelay;
    ImageView imgFoe;
    ImageView imgLoot;

    TextView nameTxt;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Log.i("DEBUG"," Wesh cassos");


        setContentView(R.layout.activity_start_game);

        /************************************************
         *                                               *
         * Association des variables aux id des layouts  *
         *                                               *
         ************************************************/

        //TextView hpPlayerText = (TextView)  findViewById(R.id.playerLife);
       // TextView hpFoeText    = (TextView)  findViewById(R.id.foeLife);

        final ImageButton goUp      = (ImageButton) findViewById(R.id.goUp);
        final ImageButton goLeft    = (ImageButton) findViewById(R.id.goLeft);
        final ImageButton goRight   = (ImageButton) findViewById(R.id.goRight);
        final ImageView   avatarImg         = (ImageView)   findViewById(R.id.infoPerso);

                 imgFoe       = (ImageView) findViewById(R.id.foe);
                 imgLoot      = (ImageView) findViewById(R.id.lootImg);
                 nameTxt      = (TextView)  findViewById(R.id.name);





        setDelay = new Handler();

        //je désactive les boutons qui me fait changer d'intent et donc de salle InGame
        goUp.setVisibility(View.INVISIBLE);
        goRight.setVisibility(View.INVISIBLE);
        goLeft.setVisibility(View.INVISIBLE);
        goUp.setClickable(false);
        goLeft.setClickable(false);
        goRight.setClickable(false);


        avatarImg.setVisibility(GONE);
        Toast.makeText(context, "Oh shit a monster appear! DON'T WALK INTO THE GRASS!",Toast.LENGTH_SHORT).show();
        //petit effet


                fadeInImg(imgFoe);


        //on s'assure que le joueur possede le statut 'vivant'
        player = (Player)getIntent().getSerializableExtra("player");
        player.setAlive(true);
        Log.i("DEBUG"," Wesh cassos");

        Log.i("DEBUG","mmmmm "+player.getName());
        Log.i("DEBUG","wwwww "+player.getHp());

        //génération du méchant

        firstFoe.setAttk(10);
        firstFoe.setHp(30);
        firstFoe.setDef(10);
        firstFoe.setName("Random Bonasse"); //référence à "Crossed"
        firstFoe.setAlive(true);
        firstFoe.setSagesse(10);
        firstFoe.setIntell(10);
        firstFoe.setElement(3);//élément eau
        firstFoe.setIdFoe(1);

        //prépare la fiche du personnage
        nameTxt.setVisibility(GONE);
        nameTxt.setInputType(nameTxt.getInputType()| InputType.TYPE_TEXT_FLAG_MULTI_LINE);


        Log.i("DEBUG","wwwww "+firstFoe.getName()); //vérif de dev

        //affichage précaire des HP

        //hpFoeText.setVisibility(View.VISIBLE);
        //hpPlayerText.setVisibility(View.VISIBLE);
        Log.i("DEBUG","en quete!");

        //méthode de combat initié

        fight(player,firstFoe);

        if (firstFoe.isAlive() == false){
            Log.i("DEBUG","C'est le gg bro");
            imgFoe.setVisibility(GONE);
            loot(player);
        }
    }



    public void  fight(final Player player, final Foe firstFoe) {
        Log.i("DEBUG"," !baston!");


        //on initialise les boutons de l'interfaces
        final ImageButton attackBtn         = (ImageButton) findViewById(R.id.swo);
        final ImageButton fireSpellBtn      = (ImageButton) findViewById(R.id.fireSpell);
        final ImageButton waterSpellBtn     = (ImageButton) findViewById(R.id.waterSpell);
        final ImageButton lightningSpellBtn = (ImageButton) findViewById(R.id.lightningSpell);
        final ImageButton shortcutQuestBtn  = (ImageButton) findViewById(R.id.questShortcut);
        final ImageButton avatarInfoBtn     = (ImageButton) findViewById(R.id.avatarInfo);
        final ImageView   avatarImg         = (ImageView)   findViewById(R.id.infoPerso);




        mp = MediaPlayer.create(context,R.raw.main);
        mp.start();
        mp.setLooping(true);

        //je mets les cliquers en route!
        shortcutQuestBtn.setClickable(true);
        fireSpellBtn.setClickable(true);
        waterSpellBtn.setClickable(true);
        lightningSpellBtn.setClickable(true);
        attackBtn.setClickable(true);
        avatarInfoBtn.setClickable(true);

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
        * qui affiche la liste détaillé du perso
        *
        *
        *****************************************************************/
        //rappel avatarImg == infoperso
        avatarInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (avatarImg.getVisibility() == View.GONE){
                    nameTxt.setText(player.toString());
                    avatarImg.setVisibility( View.VISIBLE );
                    nameTxt.setVisibility(View.VISIBLE);

                } else {
                    avatarImg.setVisibility(View.GONE);
                    nameTxt.setVisibility(GONE);

                }

            }
        });


        Log.i("DEBUG"," kiké plus fort?");
        ImageView bim =(ImageView)findViewById(R.id.bim);
        bim.setVisibility(View.INVISIBLE);

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

                /*
                *
                * si on clique sur le parchemin à droite, on à la quete affiché
                * on ferme en cliquant sur la quete OU sur le shortcut
                *
                */
                final ImageButton queteImg   = (ImageButton)findViewById(R.id.questDisplay);
                final TextView txtQuest      = (TextView) findViewById(R.id.questText);

                Log.i("DEBUG","dans le listener quete");

                if (queteImg.getVisibility()==View.VISIBLE){

                    queteImg.setVisibility(View.GONE);
                    txtQuest.setVisibility(View.INVISIBLE);
                }else{
                    txtQuest.setVisibility(View.VISIBLE);
                    queteImg.setVisibility(View.VISIBLE);
                }
                queteImg.setClickable(true);
                queteImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.i("DEBUG","dans le deuxieme listenner uéééééééééé");
                        queteImg.setVisibility(GONE);
                        queteImg.setClickable(false);
                        txtQuest.setVisibility(GONE);
                    }
                });
            }
        });

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

                    ImageButton goUpBtn       = (ImageButton) findViewById(R.id.goUp);
                    TextView hpPlayerText     = (TextView) findViewById(R.id.playerLife);
                    TextView hpFoeText        = (TextView) findViewById(R.id.foeLife);
                    ImageView imgFoe          = (ImageView) findViewById(R.id.foe);
                    ImageView bim             = (ImageView) findViewById(R.id.bim);


                    hpFoe    = firstFoe.getHp();


                    Log.i("DEBUG", "Non le prob n'est pôas là");

                    //phase attaque joueur
                    //initie l'attaque

                    hpFoe = initAttck(firstFoe, player);
                    firstFoe.setHp(hpFoe);
                    if (firstFoe.getHp() <= 0){
                        firstFoe.setAlive(false);
                    }
                    Log.e("DEBUG", "vie mechant apres combat<<<<"+firstFoe.getHp());

                    //si le mechant est vivant, il riposte
                    if(firstFoe.isAlive() == true){

                        player.setHp(retribution(firstFoe, player));
                        Log.e("DEBUG", "vie joueur apres combat<<<<"+player.getHp());

                        //image de griffure pour visuellement verifier si il y a dégâts
                        //le handler sert à timer le coup pour qu'il s'affiche ponctuellement

                        }else {

                        ImageButton goUp = (ImageButton) findViewById(R.id.goUp);
                        goUp.setClickable(true);
                        goUp.setVisibility(View.VISIBLE);
                        goUp.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View view) {
                                Log.e("DEBUG","room1 over");
                            }
                        });
                        attackBtn.setClickable(false);
                        fireSpellBtn.setClickable(false);
                        waterSpellBtn.setClickable(false);
                        lightningSpellBtn.setClickable(false);
                        //et il disparait

                        fadeOutImg(imgFoe);
                        bim.setVisibility(GONE);
                        generateExp(player,firstFoe);
                        Log.e("DEBUG","EXP progression<< ---- "+player.getLvlQuantity());
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
                        loot(player);
                        //je permets l'accés à la salle suivante
                        goUpBtn.setVisibility(View.VISIBLE);
                        goUpBtn.setClickable(true);
                        player.setLevel(2);

                        goUpBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(context,Room2Activity.class);
                                intent.putExtra("player",(Serializable) player);
                                context.startActivity(intent);
                                finish();
                            }
                        });


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

                ImageView imgFoe             = (ImageView) findViewById(R.id.foe);
                ImageView bim                = (ImageView) findViewById(R.id.bim);
                ImageButton goUpBtn          = (ImageButton) findViewById(R.id.goUp);
                final ImageView   spellSheet = (ImageView) findViewById(R.id.fireAnim);


                Log.i("DEBUG", "Non le prob n'est pôas là");

                hpFoe    = firstFoe.getHp();

                if (firstFoe.getHp() <= 0){
                    firstFoe.setAlive(false);
                }else{
                    //phase attaque joueur
                    //initie l'attaque

                    hpFoe = initFire(firstFoe, player);
                    firstFoe.setHp(hpFoe);
                }


                spellSheet.setVisibility(View.VISIBLE);
                spellSheet.setImageResource(R.drawable.fireanim);//fire fait reference à fireanim.xml

                AnimationDrawable feu = (AnimationDrawable)spellSheet.getDrawable();
                feu.start();
                Handler fireHandler = new Handler();
                fireHandler.postDelayed(new Runnable() {


                    @Override
                    public void run() {


                        MediaPlayer mpSound = MediaPlayer.create(context, R.raw.fight1);
                        mpSound.start();
                    }
                }, 1000);
                spellSheet.setVisibility(View.VISIBLE);

                Log.e("DEBUG", "vie mechant apres combat<<<<"+firstFoe.getHp());

                //si le mechant est vivant, il riposte
                if(firstFoe.isAlive() == true){
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        player.setHp(retribution(firstFoe, player));

                    }
                }, 1000);

                    Log.e("DEBUG", "vie joueur apres combat<<<<"+player.getHp());

                    //image de griffure pour visuellement verifier si il y a dégâts
                    //le handler sert à timer le coup pour qu'il s'affiche ponctuellement

                }else {

                    ImageButton goUp = (ImageButton) findViewById(R.id.goUp);
                    goUp.setClickable(true);
                    goUp.setVisibility(View.VISIBLE);
                    goUp.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View view) {
                            Log.e("DEBUG","room1 over");
                        }
                    });
                    attackBtn.setClickable(false);
                    fireSpellBtn.setClickable(false);
                    waterSpellBtn.setClickable(false);
                    lightningSpellBtn.setClickable(false);
                    //et il disparait

                    fadeOutImg(imgFoe);
                    bim.setVisibility(GONE);
                    generateExp(player,firstFoe);
                    Log.e("DEBUG","EXP progression<< ---- "+player.getLvlQuantity());
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
                    loot(player);
                    goUpBtn.setVisibility(View.VISIBLE);
                    goUpBtn.setClickable(true);
                    //allez go pex!
                    player.setLevel(2);

                    goUpBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getApplicationContext(),Room2Activity.class);
                            intent.putExtra("player",(Serializable) player);
                            if (mp.isPlaying()){
                                mp.stop();
                                mp.release();
                            }

                            context.startActivity(intent);

                            finish();
                        }
                    });
                }
            }
        });

        /************************************************************************************
         *                           ____________________¶
         *                           ___________________¶¶¶
         *                           ___________________¶¶¶
         *                           __________________¶¶¶¶
         *                           _________________¶¶¶_¶
         *                           ________________¶¶¶__¶
         *                           ________________¶¶¶__¶¶
         *                           _______________¶¶¶____¶¶
         *                           ______________¶¶¶______¶¶
         *                           _____________¶¶¶________¶¶
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
         *                           ______¶¶¶¶¶¶¶¶____________¶¶¶¶¶
         *                           ________¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶
         *                           ____________¶¶¶¶¶¶¶¶¶¶¶¶¶¶
         *************************************************************************************/
        waterSpellBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                ImageView imgFoe      = (ImageView) findViewById(R.id.foe);
                ImageView bim         = (ImageView) findViewById(R.id.bim);
                ImageButton goUpBtn = (ImageButton) findViewById(R.id.goUp);
                final ImageView   spellSheet = (ImageView) findViewById(R.id.fireAnim);


                Log.i("DEBUG", "Non le prob n'est pôas là");

                hpFoe    = firstFoe.getHp();

                if (firstFoe.getHp() <= 0){
                    firstFoe.setAlive(false);
                }else{
                    //phase attaque joueur
                    //initie l'attaque

                    hpFoe = initWater(firstFoe, player);
                    firstFoe.setHp(hpFoe);
                }



                spellSheet.setVisibility(View.VISIBLE);
                spellSheet.setImageResource(R.drawable.eauanim);//fire fait reference à fireanim.xml

                AnimationDrawable eauAnim = (AnimationDrawable)spellSheet.getDrawable();
                eauAnim.start();
                Handler waterHandler = new Handler();
                waterHandler.postDelayed(new Runnable() {


                    @Override
                    public void run() {


                        MediaPlayer mpSound = MediaPlayer.create(context, R.raw.fight1);
                        mpSound.start();
                    }
                }, 1000);
                spellSheet.setVisibility(View.VISIBLE);

                Log.e("DEBUG", "vie mechant apres combat<<<<"+firstFoe.getHp());

                //si le mechant est vivant, il riposte
                //si le mechant est vivant, il riposte
                if(firstFoe.isAlive() == true){
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            player.setHp(retribution(firstFoe, player));

                        }
                    }, 1000);

                    Log.e("DEBUG", "vie joueur apres combat<<<<"+player.getHp());

                    //image de griffure pour visuellement verifier si il y a dégâts
                    //le handler sert à timer le coup pour qu'il s'affiche ponctuellement

                }else  {

                    ImageButton goUp = (ImageButton) findViewById(R.id.goUp);
                    goUp.setClickable(true);
                    goUp.setVisibility(View.VISIBLE);
                    goUp.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View view) {
                            Log.e("DEBUG","room1 over");
                        }
                    });
                    attackBtn.setClickable(false);
                    attackBtn.setClickable(false);
                    fireSpellBtn.setClickable(false);
                    waterSpellBtn.setClickable(false);
                    lightningSpellBtn.setClickable(false);
                    //et il disparait

                    fadeOutImg(imgFoe);
                    bim.setVisibility(GONE);
                    generateExp(player,firstFoe);
                    Log.e("DEBUG","EXP progression<< ---- "+player.getLvlQuantity());
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
                    loot(player);
                    goUpBtn.setVisibility(View.VISIBLE);
                    goUpBtn.setClickable(true);
                    player.setLevel(2);

                    goUpBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getApplicationContext(),Room2Activity.class);
                            intent.putExtra("player",(Serializable) player);
                            context.startActivity(intent);
                            finish();
                        }
                    });
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
                ImageButton goUpBtn = (ImageButton) findViewById(R.id.goUp);
                final ImageView   spellSheet = (ImageView) findViewById(R.id.fireAnim);


                Log.i("DEBUG", "Non le prob n'est pôas là");

                hpFoe    = firstFoe.getHp();

                if (firstFoe.getHp() <= 0){
                    firstFoe.setAlive(false);
                }else{
                    //phase attaque joueur
                    //initie l'attaque

                    hpFoe = initLight(firstFoe, player);
                    firstFoe.setHp(hpFoe);
                }

                if (firstFoe.getHp() <= 0){
                    firstFoe.setAlive(false);
                }

                spellSheet.setVisibility(View.VISIBLE);
                spellSheet.setImageResource(R.drawable.elecanim);//fire fait reference à fireanim.xml

                AnimationDrawable elecAnim = (AnimationDrawable)spellSheet.getDrawable();
                elecAnim.start();
                Handler lightningHandler = new Handler();
                lightningHandler.postDelayed(new Runnable() {


                    @Override
                    public void run() {


                        MediaPlayer mpSound = MediaPlayer.create(context, R.raw.fight1);
                        mpSound.start();
                    }
                }, 1000);
                spellSheet.setVisibility(View.VISIBLE);

                Log.e("DEBUG", "vie mechant apres combat<<<<"+firstFoe.getHp());

                //si le mechant est vivant, il riposte
                if(firstFoe.isAlive() == true){
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {

                                                player.setHp(retribution(firstFoe, player));

                                            }
                                        }, 1000
                    );}else {

                    ImageButton goUp = (ImageButton) findViewById(R.id.goUp);
                    goUp.setClickable(true);
                    goUp.setVisibility(View.VISIBLE);
                    goUp.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View view) {
                            Log.e("DEBUG","room1 over");
                        }
                    });
                    attackBtn.setClickable(false);
                    attackBtn.setClickable(false);
                    fireSpellBtn.setClickable(false);
                    waterSpellBtn.setClickable(false);
                    lightningSpellBtn.setClickable(false);
                    //et il disparait

                    fadeOutImg(imgFoe);
                    bim.setVisibility(GONE);
                    generateExp(player,firstFoe);
                    Log.e("DEBUG","EXP progression<< ---- "+player.getLvlQuantity());

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
                    loot(player);
                    goUpBtn.setVisibility(View.VISIBLE);
                    goUpBtn.setClickable(true);
                    player.setLevel(2);

                    goUpBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getApplicationContext(),Room2Activity.class);
                            intent.putExtra("player",(Serializable) player);
                            context.startActivity(intent);
                            finish();
                        }
                    });
                }
            }
        });


    }



    @Override
    public void onBackPressed(){
        if(!canPressBackButton){

        } else {
            super.onBackPressed();
        }
    }




    //phase de loot
    private void loot(final Player player) {
        Log.i("DEBUG","on loot");
        final int lootValue = randomize();
        if ( lootValue <= 20 && lootValue > 0){

            imgLoot.setImageResource(R.drawable.loot1);
            imgLoot.setClickable(true);
            imgLoot.setVisibility(View.VISIBLE);
            fadeInImg(imgLoot);
        }
        if ( lootValue <= 40 && lootValue > 20){

            imgLoot.setImageResource(R.drawable.loot2);
            imgLoot.setClickable(true);
            imgLoot.setVisibility(View.VISIBLE);
            fadeInImg(imgLoot);

        }
        if ( lootValue <= 60 && lootValue > 40){

            imgLoot.setImageResource(R.drawable.loot3);
            imgLoot.setClickable(true);
            imgLoot.setVisibility(View.VISIBLE);
            fadeInImg(imgLoot);

        }
        if ( lootValue <= 80 && lootValue > 60){

            imgLoot.setImageResource(R.drawable.loot4);
            imgLoot.setClickable(true);
            imgLoot.setVisibility(View.VISIBLE);
            fadeInImg(imgLoot);

        }
        if ( lootValue <= 92 && lootValue > 80){

            imgLoot.setImageResource(R.drawable.loot5);
            imgLoot.setClickable(true);
            imgLoot.setVisibility(View.VISIBLE);
            fadeInImg(imgLoot);

        }
        if ( lootValue <= 99 && lootValue > 80){

            imgLoot.setImageResource(R.drawable.loot6);
            imgLoot.setClickable(true);
            imgLoot.setVisibility(View.VISIBLE);
            fadeInImg(imgLoot);

        }

        imgLoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int money = player.getWallet();
                player.setWallet((money + lootValue));
                if(lootValue > 1 && lootValue != 0) {
                    Toast.makeText(context, "Valyria: Oh, tu viens de rammasser " + lootValue + "pièces d'or", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "Valyria: Quel radin ...",Toast.LENGTH_SHORT).show();

                }
                fadeOutImg(imgLoot);
                imgLoot.setVisibility(GONE);
                Log.e("DEBUG", "argent "+ player.getWallet());
            }
        });
    }

    @Override
    public  void onDestroy(){
        mp.stop();
        mp.release();

        super.onDestroy();
    }




    //représaille de l'ennemi
    private int retribution(Foe foe, Player player) {
        Log.e("DEBUG", "MOI PAS CONTENT");
        int hpPlayer    = player.getHp();
        int attckFoe    = foe.getAttk();
        int defPlayer   = player.getDef();
        final ImageView bim   = (ImageView) findViewById(R.id.bim);
            /*
            *
            *   animation griffe
            */
        bim.setVisibility(View.VISIBLE);
        bim.setImageResource(R.drawable.bim);//bim fait reference à bim.xml

        AnimationDrawable claw = (AnimationDrawable)bim.getDrawable();
        claw.start();
        bim.setVisibility(GONE);
        Handler clawHandler = new Handler();
        clawHandler.postDelayed(new Runnable() {


            @Override
            public void run() {
                ImageView bim   = (ImageView) findViewById(R.id.bim);

                MediaPlayer mpSound = MediaPlayer.create(context, R.raw.fight1);
                mpSound.start();
            }
        }, 100);

        bim.setVisibility(View.VISIBLE);

        if(defPlayer < attckFoe ) {
            Log.e("DEBUG", "EZ");
            return hpPlayer - (attckFoe - defPlayer);

        }else{
            Log.e("DEBUG", "ouch");
            return hpPlayer - 1;
        }
    }



}
