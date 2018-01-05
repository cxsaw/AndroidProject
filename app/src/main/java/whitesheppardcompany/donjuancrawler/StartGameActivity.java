package whitesheppardcompany.donjuancrawler;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;

import whitesheppardcompany.donjuancrawler.Beans.Foe;
import whitesheppardcompany.donjuancrawler.Beans.Player;

public class StartGameActivity extends AppCompatActivity {

    private Player player;
    private Foe firstFoe = new Foe();
    // je mets 9999 pour ne pas que le jeu declare le mob mort des le debut de la phase
    private int hpFoe = 9999;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Log.i("DEBUG"," Wesh cassos");

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_start_game);

        TextView hpPlayerText = (TextView) findViewById(R.id.playerLife);
        TextView hpFoeText    = (TextView) findViewById(R.id.foeLife);
        ImageView imgFoe      = (ImageView) findViewById(R.id.foe);
        ImageButton goUp      = (ImageButton) findViewById(R.id.goUp);
        //je désactive le bouton qui me fait changer d'intent et donc de salle InGame
        goUp.setClickable(false);
        fadeInImg(imgFoe);
        //on s'assure que le joueur possede le statut 'vivant'
        player = (Player)getIntent().getSerializableExtra("player");
        player.setAlive(true);

        Log.i("DEBUG","mmmmm "+player.getName());
        Log.i("DEBUG","wwwww "+player.getHp());

        //génération du méchant
        firstFoe.setAttk(10);
        firstFoe.setHp(100);
        firstFoe.setDef(10);
        firstFoe.setName("Random Bonasse"); //référence à "Crossed"
        firstFoe.setAlive(true);
        Log.i("DEBUG","wwwww "+firstFoe.getName()); //vérif de dev

        //affichage précaire des HP


        hpFoeText.setVisibility(View.VISIBLE);
        hpPlayerText.setVisibility(View.VISIBLE);

        //méthode de combat initié

        fight(player,firstFoe);

        if (firstFoe.isAlive() == false){
            Log.i("DEBUG","C'est le gg bro");
            imgFoe.setVisibility(View.GONE);
            loot();

        }

    }



    private void fight(final Player player, final Foe firstFoe) {
        Log.i("DEBUG"," !baston!");

        final ImageButton attack = (ImageButton) findViewById(R.id.swo);

        attack.setClickable(true);

        this.player     = player;
        this.firstFoe   = firstFoe;


        Log.i("DEBUG"," kiké plus fort?");
        ImageView bim =(ImageView)findViewById(R.id.bim);
        bim.setVisibility(View.INVISIBLE);

        attack.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    TextView hpPlayerText = (TextView) findViewById(R.id.playerLife);
                    TextView hpFoeText    = (TextView) findViewById(R.id.foeLife);
                    ImageView imgFoe      = (ImageView) findViewById(R.id.foe);
                    ImageView bim         =(ImageView) findViewById(R.id.bim);


                    hpFoe    = firstFoe.getHp();

                    //hpFoeText.setVisibility(View.VISIBLE);
                    //hpPlayerText.setVisibility(View.VISIBLE);
                    //set les var dans le textView
                    //hpFoeText.setText(vieFoe);
                    //hpPlayerText.setText(viePlayer);
                    Log.i("DEBUG", "Non le prob n'est oas là");

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
                        attack.setClickable(false);
                        //et il disparait

                        fadeOutImg(imgFoe);
                        bim.setVisibility(View.GONE);
                    }
                    //si le joueur est mort on affiche un game over
                    if(player.getHp() <= 0){
                        player.setAlive(false);
                        //forcément si on meurt on ne peut attaquer
                        attack.setClickable(false);
                        //on lance le GameOver
                        ImageView gameOver =(ImageView)findViewById(R.id.gameOver);
                        gameOver.setVisibility(View.VISIBLE);

                    }

                    if (firstFoe.getHp() <= 0){
                        loot();
                    }
                }
            });
        }


    private void fadeInImg(ImageView imageView) {
        imageView.setVisibility(View.VISIBLE);
//Animation fade in
        ObjectAnimator fadeInImageView = ObjectAnimator.ofFloat(imageView, imageView.ALPHA, 0,1);
        fadeInImageView.setDuration(1200);
        AnimatorSet fadeInAnimator = new AnimatorSet();
        fadeInAnimator.play(fadeInImageView);
        fadeInAnimator.start();
    }

    private void fadeOutImg(ImageView imageView) {

//Animation fade in
        ObjectAnimator fadeOutImageView = ObjectAnimator.ofFloat(imageView, imageView.ALPHA, 1,0);
        fadeOutImageView.setDuration(500);
        AnimatorSet fadeOutAnimator = new AnimatorSet();
        fadeOutAnimator.play(fadeOutImageView);
        fadeOutAnimator.start();
    }

    //phase de loot
    private void loot() {
        Log.i("DEBUG","on loot");
    }

    //représaille de l'ennemi
    private int retribution(Foe foe, Player player) {
        Log.e("DEBUG", "MOI PAS CONTENT");
        int hpPlayer    = player.getHp();
        int attckFoe    = foe.getAttk();
        int defPlayer   = player.getDef();
        ImageView bim = (ImageView) findViewById(R.id.bim);

        Handler clawHandler = new Handler();
        clawHandler.postDelayed(new Runnable() {
            ImageView bim = (ImageView) findViewById(R.id.bim);

            @Override
            public void run() {

                bim.setVisibility(View.GONE);
            }
        }, 200);
        bim.setVisibility(View.VISIBLE);

        if(defPlayer < attckFoe ) {
            Log.e("DEBUG", "EZ");
            return hpPlayer - (attckFoe - defPlayer);

        }else{
            Log.e("DEBUG", "ouch");
            return hpPlayer - 1;
        }

    }

    //le joueur initie l'attaque
    private int initAttck(Foe foe, Player player) {

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
}
