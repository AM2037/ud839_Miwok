package com.example.android.miwok;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.ArrayList;

//WordAdapter extends or inherits behavior from the ArrayAdapter class
//to override click between two curly braces and then click code tab --> override methods
//or click ctrl+o, then scroll to click getView override method

public class WordAdapter extends ArrayAdapter<Word> {


    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context        The current context. Used to inflate the layout file.
     * @param words A List of Word objects to display in a list
     */
    WordAdapter(Activity context, ArrayList<Word> words) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, words);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //Check if existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent,false);
        }

        // Get the {@link Word} object located at this position on the list
        Word currentWord = getItem(position);

        //Find TextView in list_item.xml layout with the ID miwok_text_view
        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);
        //Get miwok translation from current Word object and set this text on miwok TextView
        assert currentWord != null;
        miwokTextView.setText(currentWord.getMiwokTranslation());

        //Find TextView in the list_item.xml layout with the ID default_text_view
        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
        //Get english translation from current Word object and set this text on default TextView
        defaultTextView.setText(currentWord.getDefaultTranslation());

        /*Find ImageView in list_item.xml layout with ID list_icon
        ImageView iconView = (ImageView) listItemView.findViewById(R.id.list_icon);
        //Get image resource ID from current Word object and set image to iconView
        iconView.setImageResource(currentWord.getmImageResourceId());*/
        return listItemView;
    }
}
