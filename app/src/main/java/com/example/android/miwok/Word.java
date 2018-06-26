package com.example.android.miwok;
/*
+ * Copyright (C) 2016 The Android Open Source Project
+ *
+ * Licensed under the Apache License, Version 2.0 (the "License");
+ * you may not use this file except in compliance with the License.
+ * You may obtain a copy of the License at
+ *
+ *      http://www.apache.org/licenses/LICENSE-2.0
+ *
+ * Unless required by applicable law or agreed to in writing, software
+ * distributed under the License is distributed on an "AS IS" BASIS,
+ * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
+ * See the License for the specific language governing permissions and
+ * limitations under the License.
+ */

/**
 + * {@link Word} represents a vocabulary word that the user wants to learn.
 + * It contains a default translation and a Miwok translation, and image for that word.
 + */
class Word {

    /*Default translation for the word*/
    private final String mDefaultTranslation;

    /*Miwok translation for the word*/
    private final String mMiwokTranslation;

    //Drawable resource ID
    private int mImageResourceId = NO_IMAGE_PROVIDED;

    /**
     * static and final are access modifiers, can never change values aka constant
     * Constant value that represents no image was provided for this word
     */

    private static final int NO_IMAGE_PROVIDED = -1;

    //Audio resource ID
    private final int mAudioResourceId;


    /**
     * Create a new Word object
     * @param defaultTranslation is the word in a language the user is already familiar with (such as English)
     * @param miwokTranslation is the word in the Miwok language
     * @param audioResourceId is the audio file associated with the word
     */

    public Word(String defaultTranslation, String miwokTranslation, int audioResourceId) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mAudioResourceId = audioResourceId;
    }

    /**
     * Create a new Word object
     * @param defaultTranslation is the word in a language the user is already familiar with (such as English)
     * @param miwokTranslation is the word in the Miwok language
     * @param imageResourceId is the drawable resource ID for the image associated with the word
     * @param audioResourceId is the audio file associated with the word
     */

    public Word(String defaultTranslation, String miwokTranslation, int imageResourceId, int audioResourceId) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mImageResourceId = imageResourceId;
        mAudioResourceId = audioResourceId;

    }



    /**
     * Get the default translation of the word.
     */
    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    /**
     * Get the Miwok translation of the word.
     */
    public String getMiwokTranslation() {
        return mMiwokTranslation;
    }

    /**
     * Get the image resource ID of the word.
     */

    public int getImageResourceId() {
        return mImageResourceId;
    }

    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }

    /**
     * Get the audio resource ID of the word.
     */
    public int getAudioResourceId() {
        return mAudioResourceId;
    }

    /*@Override
    public String toString() {
        return "Word{" +
                "mDefaultTranslation='" + mDefaultTranslation + '\'' +
                ", mMiwokTranslation='" + mMiwokTranslation + '\'' +
                ", mImageResourceId=" + mImageResourceId +
                ", mAudioResourceId=" + mAudioResourceId +
                '}';
    }*/
}


