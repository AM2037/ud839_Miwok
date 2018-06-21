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

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        ArrayList<Word> words = new ArrayList<>();
        words.add(new Word(getString(R.string.phrases_english1), getString(R.string.phrases_miwok1)));
        words.add(new Word(getString(R.string.phrases_english2),getString(R.string.phrases_miwok2)));
        words.add(new Word(getString(R.string.phrases_english3),getString(R.string.phrases_miwok3)));
        words.add(new Word(getString(R.string.phrases_english4),getString(R.string.phrases_miwok4)));
        words.add(new Word(getString(R.string.phrases_english5),getString(R.string.phrases_miwok5)));
        words.add(new Word(getString(R.string.phrases_english6),getString(R.string.phrases_miwok6)));
        words.add(new Word(getString(R.string.phrases_english7),getString(R.string.phrases_miwok7)));
        words.add(new Word(getString(R.string.phrases_english8),getString(R.string.phrases_miwok8)));
        words.add(new Word(getString(R.string.phrases_english9),getString(R.string.phrases_miwok9)));
        words.add(new Word(getString(R.string.phrases_english10),getString(R.string.phrases_miwok10)));


        // Create an {@link ArrayAdapter}, whose data source is a list of Strings. The
        // adapter knows how to create layouts for each item in the list, using the
        // simple_list_item_1.xml layout resource defined in the Android framework.
        // This list item layout contains a single {@link TextView}, which the adapter will set to
        // display a single word.

        WordAdapter adapter = new WordAdapter(this, words, R.color.category_phrases);

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
    }
}
