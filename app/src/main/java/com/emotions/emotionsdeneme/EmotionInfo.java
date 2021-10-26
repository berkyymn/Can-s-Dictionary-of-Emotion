package com.emotions.emotionsdeneme;

import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;

public class EmotionInfo {

    public String emotionName, emotionDefiniton, sentence1, sentence2, sentence3, sentence4, sentence5;
    public Drawable picture1,picture2,picture3,picture4,picture5,headPicture,baslik;
    public MediaPlayer sentence1Audio,sentence2Audio,sentence3Audio,sentence4Audio,sentence5Audio,questionAudio;

    public EmotionInfo(String emotionName, String emotionDefiniton, String sentence1,
                       String sentence2, String sentence3, String sentence4, String sentence5,
                       Drawable picture1, Drawable picture2, Drawable picture3, Drawable picture4, Drawable picture5, Drawable headPicture,
                       Drawable baslik,
                       MediaPlayer sentence1Audio, MediaPlayer sentence2Audio, MediaPlayer sentence3Audio,
                       MediaPlayer sentence4Audio, MediaPlayer sentence5Audio, MediaPlayer questionAudio) {
        this.emotionName = emotionName;
        this.emotionDefiniton = emotionDefiniton;
        this.sentence1 = sentence1;
        this.sentence2 = sentence2;
        this.sentence3 = sentence3;
        this.sentence4 = sentence4;
        this.sentence5 = sentence5;
        this.picture1 = picture1;
        this.picture2 = picture2;
        this.picture3 = picture3;
        this.picture4 = picture4;
        this.picture5 = picture5;
        this.headPicture = headPicture;
        this.baslik = baslik;
        this.sentence1Audio = sentence1Audio;
        this.sentence2Audio = sentence2Audio;
        this.sentence3Audio = sentence3Audio;
        this.sentence4Audio = sentence4Audio;
        this.sentence5Audio = sentence5Audio;
        this.questionAudio = questionAudio;
    }




}
