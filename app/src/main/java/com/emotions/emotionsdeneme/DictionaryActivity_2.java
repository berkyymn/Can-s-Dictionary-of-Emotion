package com.emotions.emotionsdeneme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class DictionaryActivity_2 extends AppCompatActivity {

    TextView soundText1,soundText2,soundText3,soundText4,descText1,descText2,descText3,descText4,
            exampleText1,exampleText2,exampleText3,exampleText4;
    ImageButton playButton1,playButton2,playButton3,playButton4,nextButton;
    ImageView emojiPicture,textPicture;
    ConstraintLayout layout_AD;
    String emotionName;

    DictionaryInfo dictionaryInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary2);



        layout_AD = findViewById(R.id.layout_AD);
        soundText1 = findViewById(R.id.soundText1);
        soundText2 = findViewById(R.id.soundText2);
        soundText3 = findViewById(R.id.soundText3);
        soundText4 = findViewById(R.id.soundText4);
        descText1 = findViewById(R.id.descText1);
        descText2 = findViewById(R.id.descText2);
        descText3 = findViewById(R.id.descText3);
        descText4 = findViewById(R.id.descText4);
        exampleText1 = findViewById(R.id.exampleText1);
        exampleText2 = findViewById(R.id.exampleText2);
        exampleText3 = findViewById(R.id.exampleText3);
        exampleText4 = findViewById(R.id.exampleText4);
        playButton1 = findViewById(R.id.playButton1);
        playButton2 = findViewById(R.id.playButton2);
        playButton3 = findViewById(R.id.playButton3);
        playButton4 = findViewById(R.id.playButton4);
        nextButton = findViewById(R.id.nextButton);
        emojiPicture = findViewById(R.id.emojiPicture);
        textPicture = findViewById(R.id.textPicture);

        Intent intent = getIntent();
        emotionName = intent.getStringExtra("emotionNameIntent");

        implementEmotions(emotionName);

        soundText1.setText(dictionaryInfo.soundText1);
        soundText2.setText(dictionaryInfo.soundText2);
        soundText3.setText(dictionaryInfo.soundText3);
        soundText4.setText(dictionaryInfo.soundText4);
        descText1.setText(dictionaryInfo.descText1);
        descText2.setText(dictionaryInfo.descText2);
        descText3.setText(dictionaryInfo.descText3);
        descText4.setText(dictionaryInfo.descText4);
        exampleText1.setText(dictionaryInfo.exampleText1);
        exampleText2.setText(dictionaryInfo.exampleText2);
        exampleText3.setText(dictionaryInfo.exampleText3);
        exampleText4.setText(dictionaryInfo.exampleText4);
        emojiPicture.setImageDrawable(dictionaryInfo.emojiPic);
        textPicture.setImageDrawable(dictionaryInfo.defPic);

        if (dictionaryInfo.count == 2){
            playButton3.setVisibility(View.INVISIBLE);
            playButton4.setVisibility(View.INVISIBLE);
        }else if (dictionaryInfo.count == 3){
            playButton4.setVisibility(View.INVISIBLE);
        }



    }

    public void implementEmotions(String emotionName){

        switch (emotionName){

            case "happiness":
                dictionaryInfo = new DictionaryInfo(2,"Happy /ˈhæpi/ adjective ","Happiness /ˈhæp.i.nəs/ noun",
                        "","",
                        "feeling, showing pleasure.","being happy or pleased.",
                        "","",
                        "We are a happy family!\n" +"Happy birthday to you!\n","Playing with friends brings me happiness.",
                        "","",
                        getDrawable(R.drawable.happy),getDrawable(R.drawable.happiness_baslik),
                        MediaPlayer.create(this,R.raw.happiness_happy_feeling_or_showing_pleasure),MediaPlayer.create(this,R.raw.happiness_being_happy_or_pleased),
                        null,null);
                int happiness_color = Color.parseColor("#FCE7E6");
                layout_AD.setBackgroundColor(happiness_color);
                break;
            case "emotion1":
                dictionaryInfo = new DictionaryInfo(2,"Sad - /sad/ Adjective","Sadness  - /ˈsadnəs/ Noun ",
                        "","",
                        "feeling or showing sorrow; unhappy. ","the condition or quality of being sad.",
                        "","",
                        "My brother shouts at me. I am sad","His loud voice brings me sadness",
                        "","",
                        getDrawable(R.drawable.sad),getDrawable(R.drawable.sadness_baslik),
                        MediaPlayer.create(this,R.raw.sadness_sad_feeling_or_showing_sorrow_unhappy),MediaPlayer.create(this,R.raw.sadness_the_condition_or_quality_of_being_sad),
                        null,null);
                int saddness_color = Color.parseColor("#A3CA90C2");
                layout_AD.setBackgroundColor(saddness_color);
                break;
            case "emotion2":
                dictionaryInfo = new DictionaryInfo(3,"Fear - /fıə/ noun","Fear - /fıə/ verb",
                        "Scared - /skeəd/ adjective","",
                        "An emotion caused by dangerous, painful, or harmful situation.","Be afraid of someone or something because of dangerous, painful, or harmful situation.",
                        "Feelig fearful.","",
                        "\nAyşe has a fear of spiders.","\nAlice fears to stay in dark.",
                        "\nTom is scared of monsters.","",
                        getDrawable(R.drawable.fear),getDrawable(R.drawable.fear_baslik),
                        MediaPlayer.create(this,R.raw.fear_an_emotion_caused_by_a_danferous_painful_situation_abb),MediaPlayer.create(this,R.raw.fear_be_afraid_of_something_oe_someone_ab),
                        MediaPlayer.create(this,R.raw.fear_scared_feeling_fearful_ab),null);
                int fear_color = Color.parseColor("#435D308F");
                layout_AD.setBackgroundColor(fear_color);
                break;
            case "emotion3":
                dictionaryInfo = new DictionaryInfo(4,"Disgust : /dıs’ɡʌst/ Noun","Disgust : /dıs’ɡʌst/ Verb","Disgusted: /dıs’ɡʌstıd/ Adjective","Disgusting: /dıs’ɡʌstıŋ/ Adjective",
                        "a strong feeling of dislike for something that looks, smells bad","if something disgusts you, it makes you feel almost sick because it is so unpleasant.",
                        "feeling or showing disgust","Extremely unpleasant",
                        "He is filled with disgust because there is a fly in the soup.","Bad smell disgusts me.",
                        "Why are you disgusted?","Ugh! It’s disgusting!",
                        getDrawable(R.drawable.disgust),getDrawable(R.drawable.disgust_baslik),
                        MediaPlayer.create(this,R.raw.disgust_disgust_a_strong_feeling_of_dislike_for_something_that_looks_smells_bad),MediaPlayer.create(this,R.raw.disgust_disgust_if_something_disgusts_you_it_makes_you_feel_almost_sick_because_it_is_so_unpleasant),
                        MediaPlayer.create(this,R.raw.disgust_disgusted_feeling_or_showing_disgust),MediaPlayer.create(this,R.raw.disgust_disgusting_extremely_unpleasant));
                int disgust_color = Color.parseColor("#A81AB360");
                layout_AD.setBackgroundColor(disgust_color);
                break;
            case "emotion4":
                dictionaryInfo = new DictionaryInfo(2,"Anger: /ˈæŋɡər/ noun","Angry: /ˈæŋɡri/  adjective",
                        "","",
                        "the strong feeling you have when something has happened that you think is bad.","having strong feelings about something that you dislike very much.",
                        "","",
                        "-What is the reason for your anger? \n" +"-My brother always takes my toys.\n","I am angry because my sister always takes my candy",
                        "","",
                        getDrawable(R.drawable.anger),getDrawable(R.drawable.anger_baslik),
                        MediaPlayer.create(this,R.raw.angry_anger_the_strong_feeling_you_have_when_sth_has_happened_thay_you_think_is_bad),MediaPlayer.create(this,R.raw.angry_having_strong_feelings_about_something_that_you_dislike_very_much),
                        null,null);
                int anger_color = Color.parseColor("#8BE34E4E");
                layout_AD.setBackgroundColor(anger_color);
                break;
            case "emotion5":
                dictionaryInfo = new DictionaryInfo(2,"Hate:  /heɪt/ Verb ","Hatred: /ˈheɪ.trɪd/ Noun",
                        "","",
                        "to dislike someone or something very much","An extremely strong feeling of dislike",
                        "","",
                        "I hate insects!","He looks at me with hatred.",
                        "","",
                        getDrawable(R.drawable.hate),getDrawable(R.drawable.hate_baslik),
                        MediaPlayer.create(this,R.raw.hate_hate_to_dislike),MediaPlayer.create(this,R.raw.hate_hatred_an_extremly_strong),
                        null,null);
                int hate_color = Color.parseColor("#7A5A1855");
                layout_AD.setBackgroundColor(hate_color);
                break;
            case "emotion6":
                dictionaryInfo = new DictionaryInfo(3,"Shame:  /ʃeɪm/ Noun    ","Ashamed : /əˈʃeɪmd/ Adjective",
                        "Shy: / ʃaɪ/ Adjective","",
                        "The feeling you have when you feel guilty or embarrassed because you have done something wrong. ","feeling very sorry and embarrassed because of something you have done.",
                        "Being quiet and nervous  with other people.","",
                        "\nHe feels a deep sense of shame.","\nI am ashamed to sing a song.",
                        "I don’t want to sing because I am shy ","",
                        getDrawable(R.drawable.shy),getDrawable(R.drawable.shame_baslik),
                        MediaPlayer.create(this,R.raw.shame_the_feeling ),MediaPlayer.create(this,R.raw.shame_ashamed_feeling_very_sorry_and_embarrassed_because),
                        MediaPlayer.create(this,R.raw.shame_shy_being_quiet_and_nervous_with_other_people),null);
                int shame_color = Color.parseColor("#92D53971");
                layout_AD.setBackgroundColor(shame_color);
                break;
            case "emotion7":
                dictionaryInfo = new DictionaryInfo(2,"Wonder: /ˈwʌn·dər/ Noun","Wonder: /ˈwʌn·dər/ Verb",
                        "","",
                        "surprise and admiration","to want to know something or to try to understand the reason for something",
                        "","",
                        "The boys gazed in wonder at the shiny, red Ferrari.","\nI wonder what he’s making for dinner.",
                        "","",
                        getDrawable(R.drawable.wonder),getDrawable(R.drawable.wonder_baslik),
                        MediaPlayer.create(this,R.raw.wonder_surprise_nd_admiration),MediaPlayer.create(this,R.raw.wonder_to_want_to_know_something_or_to_try_to_understand_the_reason_for_something),
                        null,null);
                int wonder_color = Color.parseColor("#9EFEED21");
                layout_AD.setBackgroundColor(wonder_color);
                break;
            case "emotion8":
                break;


        }

    }

    public void goToNextActivity(View view){
        Intent intent = new Intent(DictionaryActivity_2.this,CatchActivity.class);
        intent.putExtra("emotionNameIntent",emotionName);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finish();
    }
    public void playAudio1(View view){
        dictionaryInfo.audio1.start();
        stopAudio2();
        stopAudio3();
        stopAudio4();
    }
    public void playAudio2(View view){
        dictionaryInfo.audio2.start();
        stopAudio1();
        stopAudio3();
        stopAudio4();
    }
    public void playAudio3(View view){
        dictionaryInfo.audio3.start();
        stopAudio1();
        stopAudio2();
        stopAudio4();
    }
    public void playAudio4(View view){
        dictionaryInfo.audio4.start();
        stopAudio1();
        stopAudio2();
        stopAudio3();

    }
    public void stopAudio1(){
        if (dictionaryInfo.audio1.isPlaying()){
            dictionaryInfo.audio1.stop();
        }
    }
    public void stopAudio2(){
        if (dictionaryInfo.audio2.isPlaying()){
            dictionaryInfo.audio2.stop();
        }
    }
    public void stopAudio3(){
        if (dictionaryInfo.audio3 != null){
            if (dictionaryInfo.audio3.isPlaying()){
                dictionaryInfo.audio3.stop();
            }
        }
    }
    public void stopAudio4(){
        if (dictionaryInfo.audio4 != null){
            if (dictionaryInfo.audio4.isPlaying()){
                dictionaryInfo.audio4.stop();
            }
        }
    }

    @Override
    protected void onStop() {
        stopAudio1();
        stopAudio2();
        stopAudio3();
        stopAudio4();
        super.onStop();
    }
}