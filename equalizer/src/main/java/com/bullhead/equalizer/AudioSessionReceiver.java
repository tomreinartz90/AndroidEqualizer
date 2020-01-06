package com.bullhead.equalizer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.audiofx.Equalizer;
import android.util.Log;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static android.content.ContentValues.TAG;

public class AudioSessionReceiver extends BroadcastReceiver {

    private int mAdded = 0;
    private int mRemoved = 0;

    private final CountDownLatch mLatch;
    private static int activeSession;

    public AudioSessionReceiver(int count) {
        mLatch = new CountDownLatch(count);
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        boolean added = intent.getBooleanExtra(Equalizer.EXTRA_AUDIO_SESSION, false);
        int info = intent.getParcelableExtra(Equalizer.EXTRA_AUDIO_SESSION);
        Log.d(TAG, "onReceive: " + info);

        if (added) {
            mAdded++;
            AudioSessionReceiver.activeSession = info;
        } else {
            mRemoved++;
        }

        mLatch.countDown();
    }

    public int getNumAdded() {
        return mAdded;
    }

    public int getNumRemoved() {
        return mRemoved;
    }

    public static int getActiveSession() {
        return AudioSessionReceiver.activeSession;
    }

    public void waitForSessions() throws InterruptedException {
        mLatch.await(60, TimeUnit.SECONDS);
    }
};

