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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_choose_player);

        //on reçoit l'intent
        Intent i = getIntent();
        String name=i.getStringExtra("avatarName");



        //coco = corentin cam = camille toto = moi, et oui je serais complètement pété as fu*k
        ImageButton coco = (ImageButton) findViewById(R.id.coco);
        ImageButton cam = (ImageButton) findViewById(R.id.cam);
        ImageButton toto = (ImageButton) findViewById(R.id.toto);



        coco.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent i = getIntent();
                String name=i.getStringExtra("avatarName");
                Player playerCoco = new Player();
                playerCoco.setName(name);
                playerCoco.setHp(100);
                playerCoco.setDef(50);
                playerCoco.setAttk(10);
                Log.i("DEBUG", ":  on commence enfin le jeuuuuuuuuu" );
                Intent intentCoco = new Intent(getApplicationContext(),StartGameActivity.class);
                intentCoco.putExtra("player",(Serializable) playerCoco);
                context.startActivity(intentCoco);
                finish();
            }
        });


        cam.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent i = getIntent();
                String name=i.getStringExtra("avatarName");
                Player playerCam = new Player();
                playerCam.setName(name);
                playerCam.setHp(10);
                playerCam.setDef(0);
                playerCam.setAttk(1);
                playerCam.setWallet(100000*100000);

                Log.i("DEBUG", ":  on commence enfin le jeuuuuuuuuu" );
                Intent intentCam = new Intent(getApplicationContext(),StartGameActivity.class);
                intentCam.putExtra("player", (Serializable) playerCam);
                context.startActivity(intentCam);
                finish();
            }
        });


        toto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = getIntent();
                String name=i.getStringExtra("avatarName");
                Player playerToto = new Player();
                playerToto.setName(name);
                playerToto.setHp(10000);
                playerToto.setDef(5000);
                playerToto.setAttk(1000);


                Log.i("DEBUG", ":  on commence enfin le jeuuuuuuuuu" );
                Intent intentToto = new Intent(getApplicationContext(),StartGameActivity.class);
                intentToto.putExtra("player",(Serializable) playerToto);
                context.startActivity(intentToto);
                finish();
            }
        });
    }
}
