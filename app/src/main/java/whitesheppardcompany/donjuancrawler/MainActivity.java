package whitesheppardcompany.donjuancrawler;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;



public class MainActivity extends AppCompatActivity {
    Handler setDelay;
    Runnable startDelay;
    //array pour le circle menu
    String btnMenu[]={  "New Game",
                        "Quitter",
                        "Option",
                        "Crédits"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("DEBUG", "Ici c'est bon!");
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Log.i("DEBUG", "On arrive à l'init du cercle");
            /* Pour le menu en forme de cercle */
        CircleMenu circleMenu = (CircleMenu)findViewById(R.id.circle_menu);
        Log.i("DEBUG", "init du cercle");
        circleMenu.setMainMenu(Color.parseColor("#CDCDCD"),R.mipmap.plus, R.mipmap.cross)

                .addSubMenu(Color.parseColor("#124FD1"),R.mipmap.book)
                .addSubMenu(Color.parseColor("#D12212"),R.mipmap.x)
                .addSubMenu(Color.parseColor("#CCCC35"),R.mipmap.credit)
                .addSubMenu(Color.parseColor("#CDCDCD"),R.mipmap.gear);
                 setDelay = new Handler();
                circleMenu.setOnMenuSelectedListener(new OnMenuSelectedListener()
                {
                    @Override
                    public void onMenuSelected(int index) {
                        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>index="+index);
                        Log.i("DEBUG", "ça va toaster chérie!"+index);
                        Toast.makeText(MainActivity.this,"It's time for: "+btnMenu[index], Toast.LENGTH_SHORT);
                        switch (index) {

                            case 0:
                                Log.i("DEBUG","LvlActivity"+index);
                                startDelay = new Runnable() {

                                @Override
                                public void run() {

                                    Intent intentLvl = new Intent(MainActivity.this,LvlActivity.class);
                                    startActivity(intentLvl);
                                    }
                                };

                                setDelay.postDelayed(startDelay, 500);

                                break;

                                case 1:

                                    Log.i("DEBUG","OnDestroy"+index);
                                        Toast.makeText(MainActivity.this, "C U Soon Bro, U R Always in my heart <3", Toast.LENGTH_SHORT).show();
                                        onDestroy();

                                     break;

                                case 2:

                                    Log.i("DEBUG","CreditActivity"+index);
                                    startDelay = new Runnable() {
                                    @Override
                                     public void run() {
                                         Intent intentCredit = new Intent(MainActivity.this,CreditActivity.class);
                                         startActivity(intentCredit); }
                                        };

                                    setDelay.postDelayed(startDelay,1100);
                                    Toast.makeText(MainActivity.this, "Personne ne lit ça...",Toast.LENGTH_SHORT).show();
                                    break;

                                case 3:
                                    Log.i("DEBUG","OptionActivity"+index);
                                    startDelay = new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent intentOption = new Intent(MainActivity.this,OptionActivity.class);
                                        startActivity(intentOption); }
                                        };
                                    setDelay.postDelayed(startDelay,1000);
                                    Toast.makeText(MainActivity.this, "Non les options sont prévus en DLC",Toast.LENGTH_SHORT).show();
                                    break;
                                }//END Switch
                        }

                });
        //initialize();
    }
}
