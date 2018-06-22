/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    // Handles playback of all the sound files
    private MediaPlayer mMediaPlayer;

    //Handles audio focus when playing sound files
    private AudioManager mAudioManager;

    /**
     * This listener is triggered when the audio refocuses
     * (i.e. gain or lose focus due to another app or device)
     */
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            //AUDIOFOCUS_LOSS_TRANSIENT: temporary loss of audio focus
            //AUDIOFOCUS_LOSS_CAN_DUCK: app can play sound at lower volume
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
            focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK && mMediaPlayer != null) {
                //Pause playback and reset player to the start of the file so we can play the word
                //from the beginning when we resume playback
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                //The AUDIOFOCUS_GAIN means we have regained focus and can resume playback
                mMediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                //The AUDIOFOCUS_LOSS case means we've lost audio focus and
                //need to stop playback and clean up resources
                releaseMediaPlayer();
            }
        }
    };

    /**
     * This listener gets triggered when the {@link MediaPlayer} has completed playing
     * the audio file
     */

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            //now that the sound file has finished playing, release the resources
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        // Create and setup the {@link AudioManager} to request audio focus
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);


        final ArrayList<Word> words = new ArrayList<>();
        words.add(new Word(getString(R.string.numbers_english1),getString(R.string.numbers_miwok1), R.drawable.number_one, R.raw.number_one));
        words.add(new Word(getString(R.string.numbers_english2),getString(R.string.numbers_miwok2), R.drawable.number_two, R.raw.number_two));
        words.add(new Word(getString(R.string.numbers_english3),getString(R.string.numbers_miwok3), R.drawable.number_three, R.raw.number_three));
        words.add(new Word(getString(R.string.numbers_english4),getString(R.string.numbers_miwok4), R.drawable.number_four, R.raw.number_four));
        words.add(new Word(getString(R.string.numbers_english5),getString(R.string.numbers_miwok5), R.drawable.number_five, R.raw.number_five));
        words.add(new Word(getString(R.string.numbers_english6),getString(R.string.numbers_miwok6), R.drawable.number_six, R.raw.number_six));
        words.add(new Word(getString(R.string.numbers_english7),getString(R.string.numbers_miwok7), R.drawable.number_seven, R.raw.number_seven));
        words.add(new Word(getString(R.string.numbers_english8),getString(R.string.numbers_miwok8), R.drawable.number_eight, R.raw.number_eight));
        words.add(new Word(getString(R.string.numbers_english9),getString(R.string.numbers_miwok9), R.drawable.number_nine, R.raw.number_nine));
        words.add(new Word(getString(R.string.numbers_english10),getString(R.string.numbers_miwok10), R.drawable.number_ten, R.raw.number_ten));


        // Create an {@link ArrayAdapter}, whose data source is a list of Strings. The
        // adapter knows how to create layouts for each item in the list, using the
        // simple_list_item_1.xml layout resource defined in the Android framework.
        // This list item layout contains a single {@link TextView}, which the adapter will set to
        // display a single word.

        WordAdapter adapter = new WordAdapter(this, words, R.color.category_numbers);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_list.xml file.
        ListView listView = (ListView) findViewById(R.id.list);

        // Make the {@link ListView} use the {@link ArrayAdapter} we created above, so that the
        // {@link ListView} will display list items for each word in the list of words.
        // Do this by calling the setAdapter method on the {@link ListView} object and pass in
        // 1 argument, which is the {@link ArrayAdapter} with the variable name itemsAdapter.
        assert listView != null;
        listView.setAdapter(adapter);

        //set a click listener to play the audio when the list item is clicked on
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //Get the {@link Word} object at the given position the user clicked on
                Word word = words.get(position);

                //Release the media player if it currently exists because we are about to
                //play a different file
                releaseMediaPlayer();


                //Request audio focus in order to play audio file. The app needs to play a short
                //audio file, so we'll request focus with a short amount of time using AUDIOFOCUS_GAIN_TRANSIENT
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    //We have audio focus now

                    //Create and setup the {@link MediaPlayer} for the audio resource associated
                    //with the current word
                    mMediaPlayer = MediaPlayer.create(NumbersActivity.this, word.getAudioResourceId());

                    //Start the audio file
                    mMediaPlayer.start();

                    //Setup a listener on the media player so we can stop/release the player
                    //once the sound has finished playing
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                    }
                }
            });
        }

    @Override
    protected void onStop() {
        super.onStop();
        //When the activity is stopped, release the media resources since we won't
        //be playing any more sounds
        releaseMediaPlayer();
    }

    /**
     * Clean up media player by releasing its resources
     */

    private void releaseMediaPlayer(){
        // If it's not null it may be currently playing a sound
        if (mMediaPlayer != null) {
            //release it regardless because we no longer need it
            mMediaPlayer.release();

            //Set back to null to tell MediaPlayer it's not configured to play audio at the moment
            mMediaPlayer = null;

            //Regardless of whether or not we were granted audio focus, abandon it. This also
            //unregisters the AudioFocusChangeListener so we don't get anymore callbacks
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}

