package whitesheppardcompany.donjuancrawler;

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
import android.widget.Toast;

public class LvlActivity extends AppCompatActivity {
    Handler setDelay;
    Runnable startDelay;
    String avatarName = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_level);

        setDelay= new Handler();

        Toast.makeText(LvlActivity.this, "...",Toast.LENGTH_SHORT).show();
        Toast.makeText(LvlActivity.this, "Wake-up!",Toast.LENGTH_SHORT).show();
        Toast.makeText(LvlActivity.this, "Oh crap! Finally you awake! It was close, those damned orcs almost kill you!",Toast.LENGTH_LONG).show();
        Toast.makeText(LvlActivity.this, "My name is Valyria stellforged, mother of the fallen angels, queen of the last stone guard, queen of the losts kingdoms, god of no-one, daughter of Someone the best and I'm also the one who saved you!",Toast.LENGTH_LONG).show();
        Toast.makeText(LvlActivity.this, "but you should call me Random bonasse!",Toast.LENGTH_SHORT).show();

        Toast.makeText(LvlActivity.this, "Tell me, what's your name?",Toast.LENGTH_SHORT).show();

        ImageButton btn1 = (ImageButton) findViewById(R.id.btn1);

            //petit délai pour afficher l'input où le joueur va entre son pseudo
            setDelay.postDelayed(new Runnable() {

                @Override
                public void run() {
                    ImageButton btn1 = (ImageButton) findViewById(R.id.btn1);
                    EditText et = (EditText) findViewById(R.id.name);
                    et.setVisibility(View.VISIBLE);
                    btn1.setVisibility(View.VISIBLE);
                }

            }, 15000);


        btn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                EditText et = (EditText) findViewById(R.id.name);
                avatarName = et.getEditableText().toString();
                Toast.makeText(LvlActivity.this, "Nice to meet you "+avatarName,Toast.LENGTH_SHORT).show();
                setDelay.postDelayed(new Runnable() {

                    @Override
                    public void run() {

                        Log.i("DEBUG", ":" + avatarName);
                        Intent i = new Intent(LvlActivity.this, ChoosePlayerActivity.class);
                        i.putExtra("avatarName", avatarName);
                        startActivity(i);
                        Log.i("DEBUG", ":" + avatarName);
                        finish();
                    }

                }, 3000);


            }
        });
    }
}
