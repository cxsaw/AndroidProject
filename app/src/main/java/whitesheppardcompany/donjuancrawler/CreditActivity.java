package whitesheppardcompany.donjuancrawler;

import android.app.Activity;
import android.app.ProgressDialog;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.MediaController;
import android.widget.MediaController.MediaPlayerControl;
import android.widget.Toast;
import android.widget.VideoView;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController.MediaPlayerControl;
import android.widget.Button;
import android.widget.VideoView;

import java.net.URI;

public class CreditActivity extends AppCompatActivity {
    Context context = CreditActivity.this;

    public VideoView myVideoView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_credit);

        String url = "android.resource://"+getPackageName()+"/"+R.raw.sww;
        Uri uri = Uri.parse(url);
        myVideoView = (VideoView)findViewById(R.id.sw);
        myVideoView.setVideoURI(uri);
        myVideoView.setMediaController(new MediaController(this));
        myVideoView.requestFocus();
        myVideoView.start();
    }
}
