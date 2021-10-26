package com.emotions.emotionsdeneme;

import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;

public class DictionaryInfo {

    String soundText1,soundText2,soundText3,soundText4,descText1,descText2,descText3,descText4,
            exampleText1,exampleText2,exampleText3,exampleText4;
    MediaPlayer audio1,audio2,audio3,audio4;
    Drawable emojiPic,defPic;
    int count;

    public DictionaryInfo(int count,String soundText1, String soundText2, String soundText3, String soundText4,
                          String descText1, String descText2, String descText3, String descText4,
                          String exampleText1, String exampleText2, String exampleText3,
                          String exampleText4, Drawable emojiPic, Drawable defPic,
                          MediaPlayer audio1,MediaPlayer audio2,MediaPlayer audio3,MediaPlayer audio4) {
        this.count = count;
        this.soundText1 = soundText1;
        this.soundText2 = soundText2;
        this.soundText3 = soundText3;
        this.soundText4 = soundText4;
        this.descText1 = descText1;
        this.descText2 = descText2;
        this.descText3 = descText3;
        this.descText4 = descText4;
        this.exampleText1 = exampleText1;
        this.exampleText2 = exampleText2;
        this.exampleText3 = exampleText3;
        this.exampleText4 = exampleText4;
        this.emojiPic = emojiPic;
        this.defPic = defPic;
        this.audio1 = audio1;
        this.audio2 = audio2;
        this.audio3 = audio3;
        this.audio4 = audio4;
    }
}
