package whitesheppardcompany.donjuancrawler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import whitesheppardcompany.donjuancrawler.Beans.Player;

public class StartGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);
        Player player = (Player)getIntent().getSerializableExtra("player");
    }
}
