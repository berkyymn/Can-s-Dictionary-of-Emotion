package com.emotions.emotionsdeneme;

import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;

public class ListeningInfo {
    String emotionName,dialogue1,dialogue2,dialogue3,dialogue4,dialogue5;
    MediaPlayer dialogue1Audio, dialogue2Audio,dialogue3Audio,dialogue4Audio,dialogue5Audio;
    Drawable picture1,picture2,picture3,picture4,picture5,baslik;

    public ListeningInfo(String emotionName,String dialogue1, String dialogue2, String dialogue3, String dialogue4,
                         String dialogue5,MediaPlayer dialogue1Audio, MediaPlayer dialogue2Audio,
                         MediaPlayer dialogue3Audio, MediaPlayer dialogue4Audio,
                         MediaPlayer dialogue5Audio, Drawable picture1, Drawable picture2,
                         Drawable picture3, Drawable picture4, Drawable picture5,Drawable baslik) {
        this.emotionName = emotionName;
        this.dialogue1 = dialogue1;
        this.dialogue2 = dialogue2;
        this.dialogue3 = dialogue3;
        this.dialogue4 = dialogue4;
        this.dialogue5 = dialogue5;
        this.dialogue1Audio = dialogue1Audio;
        this.dialogue2Audio = dialogue2Audio;
        this.dialogue3Audio = dialogue3Audio;
        this.dialogue4Audio = dialogue4Audio;
        this.dialogue5Audio = dialogue5Audio;
        this.picture1 = picture1;
        this.picture2 = picture2;
        this.picture3 = picture3;
        this.picture4 = picture4;
        this.picture5 = picture5;
        this.baslik = baslik;
    }
}
