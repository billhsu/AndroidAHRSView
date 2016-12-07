package me.billhsu.androidahrsview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;

import me.billhsu.ahrsview.AHRSView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final AHRSView ahrsView = (AHRSView) findViewById(R.id.AHRSView);
        SeekBar seekBarRoll = (SeekBar) findViewById(R.id.seekBarRoll);
        if (seekBarRoll == null) {
            return;
        }
        seekBarRoll.setOnSeekBarChangeListener(new MyOnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (ahrsView != null) {
                    ahrsView.setRoll(progress - 180);
                }
            }
        });

        SeekBar seekBarPitch = (SeekBar) findViewById(R.id.seekBarPitch);
        if (seekBarPitch == null) {
            return;
        }
        seekBarPitch.setOnSeekBarChangeListener(new MyOnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (ahrsView != null) {
                    ahrsView.setPitch(progress - 180);
                }
            }
        });
    }

    private abstract static class MyOnSeekBarChangeListener implements SeekBar.OnSeekBarChangeListener {
        public abstract void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser);

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }
}
