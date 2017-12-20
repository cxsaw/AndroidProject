package whitesheppardcompany.donjuancrawler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class Splash extends AppCompatActivity {

    private int SLEEP_TIMER = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("DEBUG","Ravi de vous voir ici");
        //supprime le taskbar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splash);
        LogoLauncher lgLauncher = new LogoLauncher();
        lgLauncher.start();
        Log.i("DEBUG","fini on create");
    }

    private  class LogoLauncher extends Thread{

        public void run() {
        Log.i("DEBUG","On débute");
        try {
            sleep(1000 * SLEEP_TIMER);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
            Log.i("DEBUG","init dde l'intent");
            Intent intent = new Intent(Splash.this, MainActivity.class);
            startActivity(intent);
            Splash.this.finish();
            Log.i("DEBUG","On est passé par là");
        }

    }
}
