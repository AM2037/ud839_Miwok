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

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            //now that the sound file has finished playing, release the resources
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        final ArrayList<Word> words = new ArrayList<>();
        words.add(new Word(getString(R.string.family_english1),getString(R.string.family_miwok1), R.drawable.family_father, R.raw.family_father));
        words.add(new Word(getString(R.string.family_english2),getString(R.string.family_miwok2), R.drawable.family_mother, R.raw.family_mother));
        words.add(new Word(getString(R.string.family_english3),getString(R.string.family_miwok3), R.drawable.family_son, R.raw.family_son));
        words.add(new Word(getString(R.string.family_english4),getString(R.string.family_miwok4), R.drawable.family_daughter, R.raw.family_daughter));
        words.add(new Word(getString(R.string.family_english5),getString(R.string.family_miwok5), R.drawable.family_older_brother, R.raw.family_older_brother));
        words.add(new Word(getString(R.string.family_english6),getString(R.string.family_miwok6), R.drawable.family_younger_brother, R.raw.family_younger_brother));
        words.add(new Word(getString(R.string.family_english7),getString(R.string.family_miwok7), R.drawable.family_older_sister, R.raw.family_older_sister));
        words.add(new Word(getString(R.string.family_english8),getString(R.string.family_miwok8), R.drawable.family_younger_sister, R.raw.family_younger_sister));
        words.add(new Word(getString(R.string.family_english9),getString(R.string.family_miwok9), R.drawable.family_grandmother, R.raw.family_grandmother));
        words.add(new Word(getString(R.string.family_english10),getString(R.string.family_miwok10), R.drawable.family_grandfather, R.raw.family_grandfather));


        // Create an {@link ArrayAdapter}, whose data source is a list of Strings. The
        // adapter knows how to create layouts for each item in the list, using the
        // simple_list_item_1.xml layout resource defined in the Android framework.
        // This list item layout contains a single {@link TextView}, which the adapter will set to
        // display a single word.

        WordAdapter adapter = new WordAdapter(this, words, R.color.category_family);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // activity_numbers.xml layout file.
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


                //Create and set up the {@link MediaPlayer} for the audio resource associated with the current word
                mMediaPlayer = MediaPlayer.create(FamilyActivity.this, word.getAudioResourceId());

                //Start audio file
                mMediaPlayer.start();

                //get notified when complete
                mMediaPlayer.setOnCompletionListener(mCompletionListener);
            }
        });
    }

    private void releaseMediaPlayer(){
        // If it's not null it may be currently playing a sound
        if (mMediaPlayer != null) {
            //release it regardless because we no longer need it
            mMediaPlayer.release();

            //Set back to null to tell MediaPlayer it's not configured to play audio at the moment
            mMediaPlayer = null;
        }
    }
}
