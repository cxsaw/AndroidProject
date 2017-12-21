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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_level);

        String avatarName = null;

        setDelay= new Handler();

        Toast.makeText(LvlActivity.this, "...",Toast.LENGTH_SHORT).show();
        Toast.makeText(LvlActivity.this, "Wake-up!",Toast.LENGTH_SHORT).show();
        Toast.makeText(LvlActivity.this, "Oh crap! Finally you awake! It was close, those damned orcs almost kill you!",Toast.LENGTH_LONG).show();
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

            }, 13000);


        btn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String avatarName;
                EditText et = (EditText) findViewById(R.id.name);
                avatarName = et.getEditableText().toString();


                Log.i("DEBUG", ":" + avatarName);
                Intent i = new Intent(getApplicationContext(),ChoosePlayerActivity.class);
                i.putExtra(avatarName, avatarName);
                LvlActivity.this.startActivity(i);
                finish();
            }
        });
    }
}
