package whitesheppardcompany.donjuancrawler;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;

public class MainActivity extends AppCompatActivity {

    String btnMenu[]={"New Game",
                        "Quitter",
                        "Option",
                        "Crédits"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("DEBUG", "Ici c'est bon!");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Log.i("DEBUG", "On arrive à l'init du cercle");
            /* Pour le menu en forme de cercle */
        CircleMenu circleMenu = (CircleMenu)findViewById(R.id.circle_menu);
        Log.i("DEBUG", "init du cercle");
        circleMenu.setMainMenu(Color.parseColor("#CDCDCD"),R.mipmap.plus, R.mipmap.cross)

                .addSubMenu(Color.parseColor("#124FD1"),R.mipmap.book)
                .addSubMenu(Color.parseColor("#D12212"),R.mipmap.x)
                .addSubMenu(Color.parseColor("#CCCC35"),R.mipmap.credit)
                .addSubMenu(Color.parseColor("#CDCDCD"),R.mipmap.gear)
                .setOnMenuSelectedListener(new OnMenuSelectedListener()
                {
                    @Override
                    public void onMenuSelected(int index) {
                        Log.i("DEBUG", "ça va toaster chérie!");
                        Toast.makeText(MainActivity.this,"It's time for: "+btnMenu[index], Toast.LENGTH_SHORT);

                    }
                });
        //initialize();
    }
}
