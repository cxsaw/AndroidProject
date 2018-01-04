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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("DEBUG"," Wesh cassos");
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_start_game);

        player = (Player)getIntent().getSerializableExtra("player");
        String sdfjs = player.getName();
        Log.i("DEBUG","mmmmm "+sdfjs);
        Log.i("DEBUG","wwwww "+player.getHp());
        firstFoe.setAttk(10);
        firstFoe.setHp(100);
        firstFoe.setDef(10);
        firstFoe.setName("Random Bonasse");
        Log.i("DEBUG","wwwww "+firstFoe.getName());
        fight(player,firstFoe);

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
        int hpPlayer    = player.getHp();
        int hpFoe       = firstFoe.getHp();
        int attckPlayer = player.getAttk();
        int attckFoe    = firstFoe.getAttk();
        int defPlayer   = player.getDef();
        int defFoe      = firstFoe.getDef();


        Log.i("DEBUG"," kiké plus fort?");
        ImageView bim =(ImageView)findViewById(R.id.bim);
        bim.setVisibility(View.INVISIBLE);

        attack.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    TextView hpPlayerText = (TextView) findViewById(R.id.playerLife);
                    TextView hpFoeText = (TextView) findViewById(R.id.foeLife);
                    int viePlayer = player.getHp();
                    int vieFoe    = firstFoe.getHp();

                    Log.e("DEBUG", ">>>>"+viePlayer);
                    Log.e("DEBUG", ">>>>"+vieFoe);

                    //set les var dans le textView
                    hpFoeText.setText(firstFoe.getHp());
                    hpPlayerText.setText(player.getHp());

                    firstFoe.setHp(initAttck());
                    Log.e("DEBUG", "<<<<"+vieFoe);
                    if(firstFoe.getHp()>0){
                    player.setHp(retribution());
                    Log.e("DEBUG", "<<<<"+vieFoe);

                    //image de griffure pour visuellement verifier si il y a dégât
                    ImageView bim =(ImageView) findViewById(R.id.bim);
                    bim.setVisibility(View.VISIBLE);}

                    finish();

                }
            });


        }

    //représaille de l'ennemi
    private int retribution() {

        int hpPlayer    = player.getHp();
        int attckFoe    = firstFoe.getAttk();
        int defPlayer   = player.getDef();
        if(defPlayer < attckFoe ) {
            return hpPlayer = hpPlayer - (attckFoe - defPlayer);
        }else{
            return hpPlayer =- 1;
        }

    }

    //le joueur initie l'attaque
    private int initAttck() {

        int hpFoe       = firstFoe.getHp();
        int attckPlayer = player.getAttk();
        int defFoe      = firstFoe.getDef();
        if(defFoe < attckPlayer ) {
            return hpFoe = hpFoe - (attckPlayer - defFoe);
        }else{
            return hpFoe =- 1;
        }

    }
}
