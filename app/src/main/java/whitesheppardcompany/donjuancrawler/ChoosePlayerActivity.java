package whitesheppardcompany.donjuancrawler;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;

import java.io.Serializable;

import whitesheppardcompany.donjuancrawler.Beans.Player;

public class ChoosePlayerActivity extends AppCompatActivity implements Serializable {


    Context context = ChoosePlayerActivity.this;
    String name;
    Player player = new Player();
    Player playerToto = new Player();
    Player playerCam = new Player();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_choose_player);

        //on reçoit l'intent
        Bundle bundle = getIntent().getExtras();
        name= bundle.getString("avatarName");

        Log.i("DEBUG","gggg "+name);


        //coco = corentin cam = camille toto = moi, et oui je serais complètement pété as fu*k
        ImageButton coco = (ImageButton) findViewById(R.id.coco);
        ImageButton cam = (ImageButton) findViewById(R.id.cam);
        ImageButton toto = (ImageButton) findViewById(R.id.toto);



        coco.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {



                Log.i("DEBUG","gggg "+name);

                player.setName(name);
                player.setHp(100);
                player.setDef(50);
                player.setAttk(0);
                Log.i("DEBUG", ":  on commence enfin le jeuuuuuuuuu coco" );
                Intent intentCoco = new Intent(ChoosePlayerActivity.this , StartGameActivity.class);
                intentCoco.putExtra("player", (Serializable) player);
                context.startActivity(intentCoco);
                //finish();
            }
        });


        cam.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Log.i("DEBUG","gggg "+name);
                player.setName(name);
                player.setHp(10);
                player.setDef(0);
                player.setAttk(1);
                player.setWallet(100000*100000);

                Log.i("DEBUG", ":  on commence enfin le jeuuuuuuuuu cam" );
                Intent intentCam = new Intent(ChoosePlayerActivity.this , StartGameActivity.class);
                intentCam.putExtra("player", (Serializable) player);
                context.startActivity(intentCam);
                //finish();
            }
        });


        toto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Log.i("DEBUG","gggg "+name);
                player.setName(name);
                player.setHp(10000);
                player.setDef(5000);
                player.setAttk(1000);


                Log.i("DEBUG", ":  on commence enfin le jeuuuuuuuuu toto" );
                Intent intentToto = new Intent(getApplicationContext(),StartGameActivity.class);
                intentToto.putExtra("player",(Serializable) player);
                context.startActivity(intentToto);
                //finish();
            }
        });
    }
}
