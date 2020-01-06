package com.bullhead.androidequalizer;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.bullhead.equalizer.AudioSessionReceiver;
import com.bullhead.equalizer.EqualizerFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int sessionId = AudioSessionReceiver.getActiveSession();

        if (sessionId > 0) {
            EqualizerFragment equalizerFragment = EqualizerFragment.newBuilder()
                    .setAccentColor(Color.parseColor("#4caf50"))
                    .setAudioSessionId(sessionId)
                    .build();

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.eqFrame, equalizerFragment)
                    .commit();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
//        try {
//            mediaPlayer.pause();
//        } catch (Exception ex) {
//            //ignore
//        }
//        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        try {
//            mediaPlayer.start();
//        } catch (Exception ex) {
//            //ignore
//        }
    }
}
