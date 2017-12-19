package whitesheppardcompany.donjuancrawler;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class Splash extends AppCompatActivity {

    private int SLEEP_TIMER = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //supprime le taskbar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        LogoLauncher lgLauncher = new LogoLauncher();
        lgLauncher.start();
    }

    private  class LogoLauncher extends Thread{

        public void run() {

        try {
            sleep(1000 * SLEEP_TIMER);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

            Intent intent = new Intent(Splash.this, MainActivity.class);
            startActivity(intent);
            Splash.this.finish();

        }

    }
}
