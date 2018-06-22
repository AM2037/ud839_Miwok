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

public class NumbersActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);


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
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Word word = words.get(position);

                //Create and set up the {@link MediaPlayer} for the audio resource associated with the current word
                mMediaPlayer = MediaPlayer.create(NumbersActivity.this, word.getAudioResourceId());

                //Start audio file
                mMediaPlayer.start();
            }
        });
    }
}
