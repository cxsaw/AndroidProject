package whitesheppardcompany.donjuancrawler;

import android.content.Intent;
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
        TextView hpFoeText = (TextView) findViewById(R.id.foeLife);
        player = (Player)getIntent().getSerializableExtra("player");
        player.setAlive(true);
        Log.i("DEBUG","mmmmm "+player.getName());
        Log.i("DEBUG","wwwww "+player.getHp());
        firstFoe.setAttk(10);
        firstFoe.setHp(100);
        firstFoe.setDef(10);
        firstFoe.setName("Random Bonasse");
        firstFoe.setAlive(true);
        Log.i("DEBUG","wwwww "+firstFoe.getName());
        fight(player,firstFoe);
        hpFoeText.setVisibility(View.VISIBLE);
        hpPlayerText.setVisibility(View.VISIBLE);

        //quand l'ennemi meurt, on affiche le boutton
        if (firstFoe.getHp() == 0){
        ImageButton goUp = (ImageButton) findViewById(R.id.goUp);
        goUp.setVisibility(View.VISIBLE);
        }
    }

    private void fight(final Player player, final Foe firstFoe) {
        Log.i("DEBUG"," !baston!");
        ImageButton attack = (ImageButton) findViewById(R.id.swo);


        this.player     = player;
        this.firstFoe   = firstFoe;


        Log.i("DEBUG"," kiké plus fort?");
        ImageView bim =(ImageView)findViewById(R.id.bim);
        bim.setVisibility(View.INVISIBLE);

        attack.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    TextView hpPlayerText = (TextView) findViewById(R.id.playerLife);
                    TextView hpFoeText = (TextView) findViewById(R.id.foeLife);
                    hpFoe    = firstFoe.getHp();


                    //hpFoeText.setVisibility(View.VISIBLE);
                    //hpPlayerText.setVisibility(View.VISIBLE);
                    //set les var dans le textView
                   // hpFoeText.setText(vieFoe);
                    //hpPlayerText.setText(viePlayer);
                    Log.i("DEBUG", "Non le prob n'est oas là");

                    //phase attaque joueur
                    //initie l'attaque
                    if (hpFoe <= 0){
                        firstFoe.setAlive(false);

                    }
                    hpFoe = initAttck(firstFoe, player);


                    firstFoe.setHp(hpFoe);
                    Log.e("DEBUG", "vie mechant apres combat<<<<"+firstFoe.getHp());

                    //si le mechant est vivant, il riposte
                    if(firstFoe.isAlive() == true){
                        player.setHp(retribution(firstFoe, player));
                        Log.e("DEBUG", "vie joueur apres combat<<<<"+player.getHp());

                        //image de griffure pour visuellement verifier si il y a dégât
                        ImageView bim =(ImageView) findViewById(R.id.bim);
                        bim.setVisibility(View.VISIBLE);
                        } else {

                        Log.i("DEBUG", "tu as vaincu pitit coquin");

                        loot();

                    }
                    //si le joueur est mort on affiche un game over
                    if(player.getHp() <= 0){
                        player.setAlive(false);
                        ImageView gameOver =(ImageView)findViewById(R.id.gameOver);
                        gameOver.setVisibility(View.VISIBLE);

                    }


                }
            });


        }
    //phase de loot
    private void loot() {
    }

    //représaille de l'ennemi
    private int retribution(Foe foe, Player player) {
        Log.e("DEBUG", "MOI PAS CONTENT");
        int hpPlayer    = player.getHp();
        int attckFoe    = foe.getAttk();
        int defPlayer   = player.getDef();
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
        if(defFoe < attckPlayer ) {

            Log.i("DEBUG", "il est en mousse");
            return hpFoe - (attckPlayer - defFoe);
        }else{
            Log.i("DEBUG", "il est keuss l'autre tarba");
            return hpFoe - 1;
        }

    }
}
