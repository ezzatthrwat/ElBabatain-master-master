package app.ideal.family.elbabatain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

import app.ideal.family.elbabatain.ui.main.MainActivity;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Timer timer= new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {

                startActivity(new Intent(SplashScreen.this , MainActivity.class));
                finish();
            }
        };

        timer.schedule(timerTask , 2000);
    }
}
