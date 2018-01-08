package whitesheppardcompany.donjuancrawler;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class Splash extends AppCompatActivity {

    private Context context = Splash.this;



    private int SLEEP_TIMER = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("DEBUG","Ravi de vous voir ici");
        //supprime le taskbar

        setContentView(R.layout.activity_splash);
        LogoLauncher lgLauncher = new LogoLauncher();
        lgLauncher.start();
        Log.i("DEBUG","fini on create");
    }

    private  class LogoLauncher extends Thread{

        public void run() {
        Log.i("DEBUG","On débute");
        try {
            sleep(550 * SLEEP_TIMER);
            MediaPlayer mMediaPlayer = MediaPlayer.create(context, R.raw.sf_chien);
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mMediaPlayer.setLooping(false);
            mMediaPlayer.start();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
            Log.i("DEBUG","init dde l'intent");
            Intent intent = new Intent(context, MainActivity.class);
            startActivity(intent);
            Splash.this.finish();
            Log.i("DEBUG","On est passé par là");
        }

    }
}
