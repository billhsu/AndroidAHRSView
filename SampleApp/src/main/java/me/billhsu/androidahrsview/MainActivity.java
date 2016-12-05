package me.billhsu.androidahrsview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import me.billhsu.ahrsview.AHRSView;

public class MainActivity extends AppCompatActivity {
    static float sum = 0.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final AHRSView ahrsView = (AHRSView) findViewById(R.id.AHRSView);

        Thread t = new Thread() {
            public void run() {
                while (true) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ahrsView.setRoll(sum);
                            ahrsView.setPitch(sum);
                        }
                    });
                    sum += 0.3;
                    try {
                        sleep(50);
                    } catch (InterruptedException exp) {
                        exp.printStackTrace();
                    }
                }
            }
        };
        t.start();
    }
}
