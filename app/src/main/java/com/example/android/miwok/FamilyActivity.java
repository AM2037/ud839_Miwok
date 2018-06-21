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

public class FamilyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        ArrayList<Word> words = new ArrayList<>();
        words.add(new Word(getString(R.string.family_english1),getString(R.string.family_miwok1), R.drawable.family_father));
        words.add(new Word(getString(R.string.family_english2),getString(R.string.family_miwok2), R.drawable.family_mother));
        words.add(new Word(getString(R.string.family_english3),getString(R.string.family_miwok3), R.drawable.family_son));
        words.add(new Word(getString(R.string.family_english4),getString(R.string.family_miwok4), R.drawable.family_daughter));
        words.add(new Word(getString(R.string.family_english5),getString(R.string.family_miwok5), R.drawable.family_older_brother));
        words.add(new Word(getString(R.string.family_english6),getString(R.string.family_miwok6), R.drawable.family_younger_brother));
        words.add(new Word(getString(R.string.family_english7),getString(R.string.family_miwok7), R.drawable.family_older_sister));
        words.add(new Word(getString(R.string.family_english8),getString(R.string.family_miwok8), R.drawable.family_younger_sister));
        words.add(new Word(getString(R.string.family_english9),getString(R.string.family_miwok9), R.drawable.family_grandmother));
        words.add(new Word(getString(R.string.family_english10),getString(R.string.family_miwok10), R.drawable.family_grandfather));


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
    }
}
