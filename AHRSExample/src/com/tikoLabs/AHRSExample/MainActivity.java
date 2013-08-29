package com.tikoLabs.AHRSExample;


import com.tikoLabs.AndroidAHRS.AndroidAHRSView;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {
    private AndroidAHRSView ahrsView = null;
    private float roll=0,pitch=0;
    final Handler mHandler = new Handler();
    static float sum = 0.0f;
    final Runnable mUpdateAHRS = new Runnable() {
        public void run() {
            ahrsView.setRoll(roll);
            ahrsView.setPitch(pitch);
        }
    };
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ahrsView = (AndroidAHRSView)findViewById(R.id.AHRSView);
        
        Thread t = new Thread() {
            public void run(){
                while(true){
                    sum += 1.0f;
                    pitch = sum - ((int)sum/180)*180.0f;
                    roll = sum - ((int)sum/180)*180.0f;
                    mHandler.post(mUpdateAHRS);
                    try{
                        sleep(10);
                    }
                    catch (Exception e)
                    {
                        
                    }
                    
                }
            }
        };
        t.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
