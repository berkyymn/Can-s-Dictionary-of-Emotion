package com.emotions.emotionsdeneme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ListeningSpeakingActivity extends AppCompatActivity {
    TextView  dialogue1Text, dialogue2Text,
            blank1Text, blank2Text, blank3Text, blank4Text, answer1Text, answer2Text, answer3Text,
            answer4Text, stageText,instText_lsa;
    ImageView picture, picture2,emotionNameText;
    String emotionName;
    ImageButton nextButton, audioButton,audioButton2;
    int stage = 1, sesCalindi = 0, currentStateProgress = 0;
    ConstraintLayout layout_ALS;
    MediaPlayer dogruAudio, yanlisAudio;

    ListeningSpeakingInfo listeningSpeakingInfo;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listening_speaking);
        layout_ALS = findViewById(R.id.layout_ALS);

        sharedPreferences = this.getSharedPreferences("com.example.emotionsdeneme", Context.MODE_PRIVATE);
        emotionNameText = findViewById(R.id.emotionNameText);
        dialogue1Text = findViewById(R.id.dialogue1Text);
        dialogue2Text = findViewById(R.id.dialogue2Text);
        blank1Text = findViewById(R.id.blank1Text);
        blank2Text = findViewById(R.id.blank2Text);
        blank3Text = findViewById(R.id.blank3Text);
        blank4Text = findViewById(R.id.blank4Text);
        answer1Text = findViewById(R.id.answer1Text);
        answer2Text = findViewById(R.id.answer2Text);
        answer3Text = findViewById(R.id.answer3Text);
        answer4Text = findViewById(R.id.answer4Text);
        stageText = findViewById(R.id.stageText);
        picture = findViewById(R.id.picture);
        picture2 = findViewById(R.id.picture2);
        nextButton = findViewById(R.id.nextButton);
        audioButton = findViewById(R.id.audioButton);
        instText_lsa = findViewById(R.id.inst_text_lsa);

        Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/PatrickHand-Regular.ttf");
        blank1Text.setTypeface(tf);
        blank2Text.setTypeface(tf);
        blank3Text.setTypeface(tf);
        blank4Text.setTypeface(tf);
        answer1Text.setTypeface(tf);
        answer2Text.setTypeface(tf);
        answer3Text.setTypeface(tf);
        answer4Text.setTypeface(tf);
        dialogue1Text.setTypeface(tf);
        dialogue2Text.setTypeface(tf);
        stageText.setTypeface(tf);
        instText_lsa.setTypeface(tf);



        dogruAudio = MediaPlayer.create(this,R.raw.pit_audio);
        yanlisAudio = MediaPlayer.create(this,R.raw.yanlis_audio);

        answer1Text.setOnLongClickListener(longClickListener);
        answer2Text.setOnLongClickListener(longClickListener);
        answer3Text.setOnLongClickListener(longClickListener);
        answer4Text.setOnLongClickListener(longClickListener);

        blank1Text.setOnDragListener(dragListener1);
        blank2Text.setOnDragListener(dragListener2);
        blank3Text.setOnDragListener(dragListener3);
        blank4Text.setOnDragListener(dragListener4);

        Intent intent = getIntent();
        emotionName = intent.getStringExtra("emotionNameIntent");
        implementEmotions(emotionName);

        stageText.setText(stage + "/5");

        picture.setImageDrawable(listeningSpeakingInfo.picture1);
        picture2.setImageDrawable(listeningSpeakingInfo.picture1_2);
        //picturelarda picture'stage'_'pictureNo'
        emotionNameText.setImageDrawable(listeningSpeakingInfo.baslik);
        dialogue1Text.setText(listeningSpeakingInfo.dialogue1_1);
        dialogue2Text.setText(listeningSpeakingInfo.dialogue2_1);
        answer1Text.setText(listeningSpeakingInfo.answer1_1);
        answer2Text.setText(listeningSpeakingInfo.answer2_1);
        answer3Text.setText(listeningSpeakingInfo.answer3_1);
        answer4Text.setText(listeningSpeakingInfo.answer4_1);


        isFourthAnswerFull(1);

        System.out.println("paddingleft "+ dialogue1Text.getPaddingLeft());


    }

    public void playDogru(){
        dogruAudio.start();
    }

    public void playYanlis(){
        yanlisAudio.start();
    }

    public void playAudio(View view) {
        sesCalindi++;

        switch (stage) {
            case 1:
                listeningSpeakingInfo.audio1.start();
                break;
            case 2:
                listeningSpeakingInfo.audio2.start();
                break;
            case 3:
                listeningSpeakingInfo.audio3.start();
                break;
            case 4:
                listeningSpeakingInfo.audio4.start();
                break;
            case 5:
                listeningSpeakingInfo.audio5.start();
                break;
        }


    }

    public void playAudio_1(View view) {
        switch (stage) {
            case 1:
                listeningSpeakingInfo.audio1_2.start();
                break;
            case 2:
                listeningSpeakingInfo.audio2_2.start();
                break;
            case 3:
                listeningSpeakingInfo.audio3_2.start();
                break;
            case 4:
                listeningSpeakingInfo.audio4_2.start();
                break;
            case 5:
                listeningSpeakingInfo.audio5_2.start();
                break;
        }
    }

    public void changeNextButtonColor() {
        if (currentStateProgress >= 3) {
            int green = Color.parseColor("#5ECF0E");
            nextButton.setBackgroundColor(green);
        } else {
            int red = Color.parseColor("#DA3939");
            nextButton.setBackgroundColor(red);
        }
    }

    public void goToNext(View view) {
        if (currentStateProgress >= 0) {
            stage++;
            if (stage > 5) {
                sharedPreferences.edit().putBoolean(emotionName + "Completed", true).apply();
                Intent intent = new Intent(ListeningSpeakingActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            } else {
                currentStateProgress = 0;
                sesCalindi = 0;
                stageText.setText(stage + "/5");
                answer1Text.setVisibility(View.VISIBLE);
                answer2Text.setVisibility(View.VISIBLE);
                answer3Text.setVisibility(View.VISIBLE);
                answer4Text.setVisibility(View.VISIBLE);
                blank1Text.setVisibility(View.VISIBLE);
                blank2Text.setVisibility(View.VISIBLE);
                blank3Text.setVisibility(View.VISIBLE);
                blank4Text.setVisibility(View.VISIBLE);

                blank1Text.setBackground(getDrawable(R.drawable.rounded_corner));
                blank2Text.setBackground(getDrawable(R.drawable.rounded_corner));
                blank3Text.setBackground(getDrawable(R.drawable.rounded_corner));
                blank4Text.setBackground(getDrawable(R.drawable.rounded_corner));

                blank1Text.setText(null);
                blank2Text.setText(null);
                blank3Text.setText(null);
                blank4Text.setText(null);

                changeNextButtonColor();
                switch (stage) {
                    case 2:
                        dialogue1Text.setText(listeningSpeakingInfo.dialogue1_2);
                        dialogue2Text.setText(listeningSpeakingInfo.dialogue2_2);
                        answer1Text.setText(listeningSpeakingInfo.answer1_2);
                        answer2Text.setText(listeningSpeakingInfo.answer2_2);
                        answer3Text.setText(listeningSpeakingInfo.answer3_2);
                        answer4Text.setText(listeningSpeakingInfo.answer4_2);
                        picture.setImageDrawable(listeningSpeakingInfo.picture2);
                        picture2.setImageDrawable(listeningSpeakingInfo.picture2_2);
                        isFourthAnswerFull(2);
                        break;
                    case 3:
                        dialogue1Text.setText(listeningSpeakingInfo.dialogue1_3);
                        dialogue2Text.setText(listeningSpeakingInfo.dialogue2_3);
                        answer1Text.setText(listeningSpeakingInfo.answer1_3);
                        answer2Text.setText(listeningSpeakingInfo.answer2_3);
                        answer3Text.setText(listeningSpeakingInfo.answer3_3);
                        answer4Text.setText(listeningSpeakingInfo.answer4_3);
                        picture.setImageDrawable(listeningSpeakingInfo.picture3);
                        picture2.setImageDrawable(listeningSpeakingInfo.picture3_2);
                        isFourthAnswerFull(3);
                        break;
                    case 4:
                        dialogue1Text.setText(listeningSpeakingInfo.dialogue1_4);
                        dialogue2Text.setText(listeningSpeakingInfo.dialogue2_4);
                        answer1Text.setText(listeningSpeakingInfo.answer1_4);
                        answer2Text.setText(listeningSpeakingInfo.answer2_4);
                        answer3Text.setText(listeningSpeakingInfo.answer3_4);
                        answer4Text.setText(listeningSpeakingInfo.answer4_4);
                        picture.setImageDrawable(listeningSpeakingInfo.picture4);
                        picture2.setImageDrawable(listeningSpeakingInfo.picture4_2);
                        isFourthAnswerFull(4);
                        break;
                    case 5:
                        dialogue1Text.setText(listeningSpeakingInfo.dialogue1_5);
                        dialogue2Text.setText(listeningSpeakingInfo.dialogue2_5);
                        answer1Text.setText(listeningSpeakingInfo.answer1_5);
                        answer2Text.setText(listeningSpeakingInfo.answer2_5);
                        answer3Text.setText(listeningSpeakingInfo.answer3_5);
                        answer4Text.setText(listeningSpeakingInfo.answer4_5);
                        picture.setImageDrawable(listeningSpeakingInfo.picture5);
                        picture2.setImageDrawable(listeningSpeakingInfo.picture5_2);
                        isFourthAnswerFull(5);
                        break;

                }
            }
        } else {
            Toast.makeText(ListeningSpeakingActivity.this, "Fill all the blanks", Toast.LENGTH_SHORT).show();
        }
    }

    public void changeTextViewBackground(Drawable textBackground,int left, int top, int right, int bottom){
        dialogue1Text.setBackground(textBackground);
        dialogue2Text.setBackground(textBackground);
        dialogue1Text.setPadding(left,top,right,bottom);
        dialogue2Text.setPadding(left,top,right,bottom);
    }


    public void implementEmotions(String emotionName) {
        switch (emotionName) {

            case "happiness":
                listeningSpeakingInfo = new ListeningSpeakingInfo("Happiness",
                        "Mom:", "Boy:", "It is your birthday today",
                        "Yuppie! _ _ _ _", "I", "very", "happy", "am",
                        "Girl:", "Boy:", "You look very happy!",
                        "Because _ _ _", "I", "a birthday present", "have", "",
                        "Girl:", "Boy:", "You look very happy",
                        "Because _ _ _ _", "I", "eating", "am", "birthday cake",
                        "Girl:", "Boy:", "You look very happy", "Because _ _ _",
                        "I", "riding a bike", "am", "",
                        "Boy", "Girl", "We are very happy!", "Because _ _ _",
                        "we", "playing a game", "are", "",
                        getDrawable(R.drawable.happiness_anne_cocuk), getDrawable(R.drawable.happiness_kiz), getDrawable(R.drawable.happiness_kiz),
                        getDrawable(R.drawable.happiness_kiz), getDrawable(R.drawable.happiness_topluluk),
                        getDrawable(R.drawable.happiness_anne_cocuk), getDrawable(R.drawable.happiness_dogum_gunu_cocuk), getDrawable(R.drawable.happiness_dugum_gunu),
                        getDrawable(R.drawable.happiness_bisiklet), getDrawable(R.drawable.happiness_topluluk),
                        getDrawable(R.drawable.happiness_baslik),
                        MediaPlayer.create(this, R.raw.happiness_yuppie_i_am_very_happy), MediaPlayer.create(this, R.raw.happiness_because_i_have_a_birthday_present),
                        MediaPlayer.create(this, R.raw.happiness_because_i_am_eating_birthday_cake), MediaPlayer.create(this, R.raw.happiness_because_i_am_riding_a_bike),
                        MediaPlayer.create(this, R.raw.happiness_because_we_are_playing_a_game),
                        MediaPlayer.create(this, R.raw.happiness_it_is_your_birthday_today), MediaPlayer.create(this, R.raw.happiness_you_look_very_happy),
                        MediaPlayer.create(this, R.raw.happiness_you_look_very_happy), MediaPlayer.create(this, R.raw.happiness_you_look_very_happy),
                        MediaPlayer.create(this, R.raw.happiness_we_are_very_happy));

                changeTextViewBackground(getDrawable(R.drawable.happiness_speak),80,40,0,0);
                int happiness_color = Color.parseColor("#FCE7E6");
                layout_ALS.setBackgroundColor(happiness_color);
                break;

            case "emotion1":
                listeningSpeakingInfo = new ListeningSpeakingInfo("Sadness",
                        "Woman:", "Boy", "What is the matter?", "_ _ _ _",
                        "is", "sister", "my", "ill",
                        "Woman:", "Girl:", "What happened?", "_ _ _ ",
                        "lost", "is", "my dog ", "",
                        "Woman:", "Girl:", "What is the matter?", "_ _ _",
                        "lonely", "am", "I", "",
                        "Woman:", "Girl:", "What happened?", "_ _ _",
                        "takes", "my sister", "my toy", "",
                        "Woman:", "Boy:", "What is the matter?", "_ _ _",
                        "shouts", "my brother", "at me", "",
                        getDrawable(R.drawable.sadness_1), getDrawable(R.drawable.sadness_1), getDrawable(R.drawable.sadness_1),
                        getDrawable(R.drawable.sadness_1), getDrawable(R.drawable.sadness_1),
                        getDrawable(R.drawable.sadness_6), getDrawable(R.drawable.sadness_4), getDrawable(R.drawable.sadness_3),
                        getDrawable(R.drawable.sadness_2), getDrawable(R.drawable.sadness_5),
                        getDrawable(R.drawable.sadness_baslik),
                        MediaPlayer.create(this, R.raw.sadness_my_sister_is_ill), MediaPlayer.create(this, R.raw.sadness_my_dog_is_lost),
                        MediaPlayer.create(this, R.raw.sadness_i_am_lonely), MediaPlayer.create(this, R.raw.sadness_my_sister_takes_my_toy),
                        MediaPlayer.create(this, R.raw.sadness_my_brother_shouts_at_me),
                        MediaPlayer.create(this, R.raw.sadness_what_is_the_matter), MediaPlayer.create(this, R.raw.sadness_what_happened),
                        MediaPlayer.create(this, R.raw.sadness_what_is_the_matter), MediaPlayer.create(this, R.raw.sadness_what_happened),
                        MediaPlayer.create(this, R.raw.sadness_what_is_the_matter));
                changeTextViewBackground(getDrawable(R.drawable.sadness_speak),150,60,0,0);
                int saddness_color = Color.parseColor("#A3CA90C2");
                layout_ALS.setBackgroundColor(saddness_color);
                break;

            case "emotion2":
                listeningSpeakingInfo = new ListeningSpeakingInfo("Fear",
                        "Woman:", "Boy:", "Why are you scared?", "Because _ _ _ _",
                        "there", "in my room", "spider", "is a",
                        "Girl:", "Boy:", "What's the matter?","_ _ _ _",
                        "am", "having", "nightmares", "I",
                        "Mom:", "Girl:", "Why don`t you play with your toys?", "Because _ _ _",
                        "is", "dark", "my room", "",
                        "Mom:", "Girl:", "Why don`t you watch the cartoon?", "Because _ _ _ _",
                        "ghost", "there is a", "in the cartoon", "",
                        "Boy:", "Woman:", "Why are you crying?", "Because",
                        "I don't want", "the dentist", "to go to", "",
                        getDrawable(R.drawable.fear6), getDrawable(R.drawable.fear_4), getDrawable(R.drawable.fear4),
                        getDrawable(R.drawable.fear_16), getDrawable(R.drawable.fear9),
                        getDrawable(R.drawable.korku3), getDrawable(R.drawable.korku1), getDrawable(R.drawable.fear8),
                        getDrawable(R.drawable.fear10), getDrawable(R.drawable.fear_1),
                        getDrawable(R.drawable.fear_baslik),
                        MediaPlayer.create(this, R.raw.fear_because_there_is_a_spider_in_my_room), MediaPlayer.create(this, R.raw.fear_i_am_having_nightmares),
                        MediaPlayer.create(this, R.raw.fear_because_my_room_is_dark), MediaPlayer.create(this, R.raw.fear_because_there_is_a_ghost_in_the_cartoon),
                        MediaPlayer.create(this, R.raw.fear_because_i_dont_want_to_go_to_the_dentist),
                        MediaPlayer.create(this, R.raw.fear_why_are_you_scared), MediaPlayer.create(this, R.raw.fear_whats_the_matter),
                        MediaPlayer.create(this, R.raw.fear_why_dont_you_play_with_your_toys), MediaPlayer.create(this, R.raw.fear_why_dont_you_watch_the_cartoon),
                        MediaPlayer.create(this, R.raw.fear_why_are_you_crying));
                changeTextViewBackground(getDrawable(R.drawable.fear_speak),100,50,0,0);
                int fear_color = Color.parseColor("#435D308F");
                layout_ALS.setBackgroundColor(fear_color);
                break;

            case "emotion3":
                listeningSpeakingInfo = new ListeningSpeakingInfo("Disgust",
                        "Woman:", "Man:", "What happened?", "They smell bad. _ _ _",
                        "disgusting", "is", "it", "",
                        "Waitress:", "Customer:", "Why are you disgusted?", "Ugh! There is a _ _ _ _",
                        "soup", "in", "fly", "the",
                        "Waiter:", "Customer:", "Are you ok?", "I hate soup _ _ _",
                        "smells", "bad", "it", "",
                        "Boy:", "Woman:", "Gross! There is a _ _ _ _", "What?",
                        "apple", "the", "in", "worm",
                        "Man:", "Girl:", "What are they?", "They are flies. _ _ _",
                        "is", "awful", "it", "",
                        getDrawable(R.drawable.disgust_kadin_tek), getDrawable(R.drawable.disgust_8), getDrawable(R.drawable.disgust_10),
                        getDrawable(R.drawable.disgust_4), getDrawable(R.drawable.disgust_12),
                        getDrawable(R.drawable.disgust_erkek_cop), getDrawable(R.drawable.disgust_7), getDrawable(R.drawable.disgust_11),
                        getDrawable(R.drawable.disgust_kadin), getDrawable(R.drawable.disgust_9),
                        getDrawable(R.drawable.disgust_baslik),
                        MediaPlayer.create(this, R.raw.disgust_they_smell_bad_it_is_disgusting), MediaPlayer.create(this, R.raw.disgust_ugh_there_is_a_fly_in_the_soup),
                        MediaPlayer.create(this, R.raw.disgust_i_hate_soup_it_smells_bad), MediaPlayer.create(this, R.raw.disgust_what),
                        MediaPlayer.create(this, R.raw.disgust_they_are_flies_it_is_awful),
                        MediaPlayer.create(this, R.raw.disgust_what_happened), MediaPlayer.create(this, R.raw.disgust_why_are_you_disgusted),
                        MediaPlayer.create(this, R.raw.disgust_are_you_ok), MediaPlayer.create(this, R.raw.disgust_gross_there_is_a_worm_in_the_apple),
                        MediaPlayer.create(this, R.raw.disgust_what_are_they));
                changeTextViewBackground(getDrawable(R.drawable.disgust_speak),120,45,0,0);
                int disgust_color = Color.parseColor("#A81AB360");
                layout_ALS.setBackgroundColor(disgust_color);

                break;
            case "emotion4":
                listeningSpeakingInfo = new ListeningSpeakingInfo("Anger",
                        "Mom:", "Boy:", "What is the matter?", "My friends _ _ _ _",
                        "with", "play", "don't", "me",
                        "Mom:", "Boy", "You look angry. Why?", "Because Zeynep _ _ _",
                        "my", "takes", "candy", "",
                        "Mom:", "Boy:", "Why are you angry?", "Because Oya _ _ _ _",
                        "fun", "makes", "me", "of",
                        "Mom:", "Girl:", "What is the reason for your anger?", "_ _ _",
                        "hungry", "I", "am", "",
                        "Mom:", "Boy:", "Why are you angry?", "Because Emir _ _ _",
                        "my", "takes", "toy", "",
                        getDrawable(R.drawable.anger_anne_sag), getDrawable(R.drawable.anger_anne_sag), getDrawable(R.drawable.anger_anne_sag),
                        getDrawable(R.drawable.anger_cocuk3), getDrawable(R.drawable.anger_anne_sag),
                        getDrawable(R.drawable.anger_cocuk_balon7), getDrawable(R.drawable.anger_cocuk_balon2), getDrawable(R.drawable.anger_cocuk_balon5),
                        getDrawable(R.drawable.anger_44), getDrawable(R.drawable.anger_cocuk_balon3),
                        getDrawable(R.drawable.anger_baslik),
                        MediaPlayer.create(this, R.raw.anger_my_friends_dont_play_with_me), MediaPlayer.create(this, R.raw.anger_because_zeynep_takes_my_candy),
                        MediaPlayer.create(this, R.raw.anger_because_oya_makes_fun_of_me), MediaPlayer.create(this, R.raw.anger_i_am_hungry),
                        MediaPlayer.create(this, R.raw.anger_because_emir_takes_my_toy),
                        MediaPlayer.create(this, R.raw.sadness_what_is_the_matter), MediaPlayer.create(this, R.raw.anger_you_look_angry_why),
                        MediaPlayer.create(this, R.raw.anger_why_are_you_angry), MediaPlayer.create(this, R.raw.anger_what_is_the_reason_for_your_anger),
                        MediaPlayer.create(this, R.raw.anger_why_are_you_angry));

                changeTextViewBackground(getDrawable(R.drawable.anger_speak),100,45,0,0);
                int anger_color = Color.parseColor("#8BE34E4E");
                layout_ALS.setBackgroundColor(anger_color);
                break;


            case "emotion5":
                listeningSpeakingInfo = new ListeningSpeakingInfo("Hate",
                        "Girl:", "Boy:", "Why do you hate Olivia?", "Because _ _ _",
                        "hits", "she", "me", "",
                        "Boy:", "Girl:", "Why do you hate Emre?", "Because he _ _ _",
                        "breaks", "toy", "my", "",
                        "Boy:", "Man:", "Why are you angry?", "Because I _ _ _",
                        "earlier", "hate", "sleeping", "",
                        "Boy:", "Man:", "Look! There is an insect!", "I _ _ _ ",
                        "like", "insects", "don't", "",
                        "Girl", "Children:", "Why are you fighting?", "Because he is _ _ _ _",
                        "pulling", "hair", "my", "",
                        getDrawable(R.drawable.hate_6), getDrawable(R.drawable.hate_6), getDrawable(R.drawable.hate_7),
                        getDrawable(R.drawable.hate_8), getDrawable(R.drawable.hate_6),
                        getDrawable(R.drawable.hate2), getDrawable(R.drawable.hate_2), getDrawable(R.drawable.hate3),
                        getDrawable(R.drawable.hate_4), getDrawable(R.drawable.hate_5),
                        getDrawable(R.drawable.hate_baslik),
                        MediaPlayer.create(this, R.raw.hate_because_she_hits_me), MediaPlayer.create(this, R.raw.hate_because_he_breaks_my_toy),
                        MediaPlayer.create(this, R.raw.hate_because_i_hate_sleeping_earlier), MediaPlayer.create(this, R.raw.hate_i_dont_like_insects),
                        MediaPlayer.create(this, R.raw.hate_because_he_is_puling_my_hair),
                        MediaPlayer.create(this, R.raw.hate_why_do_you_hate_olivia), MediaPlayer.create(this, R.raw.hate_why_do_you_hate_emre),
                        MediaPlayer.create(this, R.raw.hate_why_are_you_angry), MediaPlayer.create(this, R.raw.hate_look_there_is_an_insect),
                        MediaPlayer.create(this, R.raw.hate_why_are_you_fighting));

                changeTextViewBackground(getDrawable(R.drawable.hate_speak),150,60,0,0);
                int hate_color = Color.parseColor("#7A5A1855");
                layout_ALS.setBackgroundColor(hate_color);
                break;

            case "emotion6":
                listeningSpeakingInfo = new ListeningSpeakingInfo("Shame",
                        "Boys:", "Shy boy:", "Why don't you play?", "_ _ _",
                        "to play", "ashamed", "I am", "",
                        "Boy:", "Boy", "Why are you red in the face?", "_ _ _",
                        "new", "I'm", "in the class", "",
                        "Boy:", "Girl:", "Why are you ashamed?", "_ _ _ _",
                        "the", "is", "crowded", "room",
                        "Boy:", "Man:", "Come on, what is the matter?", "_ _ _",
                        "ashamed", "I'm", "to sing", "",
                        "Boy:", "Boy:", "Why don't you dance?", "_ _ _",
                        "because", "shy", " I am feeling", "",
                        getDrawable(R.drawable.shame_3), getDrawable(R.drawable.shame_5), getDrawable(R.drawable.shame_6),
                        getDrawable(R.drawable.shame_5), getDrawable(R.drawable.shame_6),
                        getDrawable(R.drawable.shame_3), getDrawable(R.drawable.shame_7), getDrawable(R.drawable.shame_8),
                        getDrawable(R.drawable.shame_9), getDrawable(R.drawable.shame_1),
                        getDrawable(R.drawable.shame_baslik),
                        MediaPlayer.create(this, R.raw.shame_i_am_ashamed_to_play), MediaPlayer.create(this, R.raw.shame_i_am_new_in_the_class),
                        MediaPlayer.create(this, R.raw.shame_the_room_is_crowded), MediaPlayer.create(this, R.raw.shame_i_am_ashamed_to_sing),
                        MediaPlayer.create(this, R.raw.shames_because_i_am_feeling_shy),
                        MediaPlayer.create(this, R.raw.shame_why_dont_you_play), MediaPlayer.create(this, R.raw.shame_why_are_you_red_in_the_face),
                        MediaPlayer.create(this, R.raw.shame_why_are_you_ashamed), MediaPlayer.create(this, R.raw.shame_come_on_what_is_the_matter),
                        MediaPlayer.create(this, R.raw.shame_why_dont_you_dance));

                changeTextViewBackground(getDrawable(R.drawable.shame_speak),130,50,110,0);
                int shame_color = Color.parseColor("#92D53971");
                layout_ALS.setBackgroundColor(shame_color);
                break;
            case "emotion7":
                listeningSpeakingInfo = new ListeningSpeakingInfo("Wonder",
                        "Girl:", "Boy:", "You look so surprised?", "OH! My favorite _ _ _ !",
                        "cartoon", "is on", "TV", "",
                        "Boy:", "Girl:", "Why are you excited?", "Because I _ _ _",
                        "going to", "I am", "the zoo", "",
                        "Girl:", "Boy:", "Why are you astonished?", "Because it _ _ _",
                        "firetruck", "red", "is a", "",
                        "Girl:", "Boy:", "Why are you surprised?", "Because it _ _ _",
                        "spiderman", "is a", "T-shirt", "",
                        "Girl:", "Girl:", "Why are you amazed?", "Because there are _ _ _",
                        "balloons", "the sky", "in", "",
                        getDrawable(R.drawable.wonder_6), getDrawable(R.drawable.wonder_8), getDrawable(R.drawable.wonder_14),
                        getDrawable(R.drawable.wonder_7), getDrawable(R.drawable.wonder_1),
                        getDrawable(R.drawable.wonder_11), getDrawable(R.drawable.wonder_5), getDrawable(R.drawable.wonder_2),
                        getDrawable(R.drawable.wonder_10), getDrawable(R.drawable.wonder_8),
                        getDrawable(R.drawable.wonder_baslik),
                        MediaPlayer.create(this, R.raw.wonder_my_favourite_cartoon_on_tv), MediaPlayer.create(this, R.raw.wonder_because_i_going_to_the_zoo),
                        MediaPlayer.create(this, R.raw.wonder_because_it_is_a_red_firetruck), MediaPlayer.create(this, R.raw.wonder_because_it_is_spiderman_t_hirt),
                        MediaPlayer.create(this, R.raw.wonnder_because_there_are_baloon_in_the_sky),
                        MediaPlayer.create(this, R.raw.wonder_you_look_so_surprised), MediaPlayer.create(this, R.raw.wonder_why_are_you_so_excited_),
                        MediaPlayer.create(this, R.raw.wonder_why_are_you_astonished), MediaPlayer.create(this, R.raw.wonder_why_are_you_so_surprised),
                        MediaPlayer.create(this, R.raw.wonder_why_are_you_amazed));
                changeTextViewBackground(getDrawable(R.drawable.wonder_speak),100,60,40,0);
                int wonder_color = Color.parseColor("#9EFEED21");
                layout_ALS.setBackgroundColor(wonder_color);
                break;

        }
    }

    public boolean isFourthAnswerFull(int stage) {
        boolean check = true;
        switch (stage) {
            case 1:
                if (listeningSpeakingInfo.answer4_1.isEmpty()) {
                    check = false;
                } else check = true;
                break;
            case 2:
                if (listeningSpeakingInfo.answer4_2.isEmpty()) check = false;
                else check = true;
                break;
            case 3:
                if (listeningSpeakingInfo.answer4_3.isEmpty()) check = false;
                else check = true;
                break;
            case 4:
                if (listeningSpeakingInfo.answer4_4.isEmpty()) check = false;
                else check = true;
                break;
            case 5:
                if (listeningSpeakingInfo.answer4_5.isEmpty()) check = false;
                else check = true;
                break;
        }
        if (check == false) {
            answer4Text.setVisibility(View.INVISIBLE);
            blank4Text.setVisibility(View.INVISIBLE);
            System.out.println("you are here");
        }
        return check;
    }

    View.OnLongClickListener longClickListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            if (/*sesCalindi > 0*/ true) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                v.startDragAndDrop(data, shadowBuilder, v, 0);
                //api kontrol et 23 aşağısını da desteklet
                return true;
            } else {
                Animation animation = AnimationUtils.loadAnimation(ListeningSpeakingActivity.this, R.anim.blink_anim);
                audioButton.startAnimation(animation);
                Toast.makeText(getApplicationContext(), "You should listen the audio before", Toast.LENGTH_LONG).show();
                return false;
            }

        }
    };

    View.OnDragListener dragListener1 = new View.OnDragListener() {
        @Override
        public boolean onDrag(View v, DragEvent event) {

            final int dragEvent = event.getAction();
            switch (dragEvent) {

                case DragEvent.ACTION_DROP:
                    final View view = (View) event.getLocalState();


                    switch (stage) {
                        case 1:
                            switch (emotionName) {
                                case "happiness":
                                case "emotion2":
                                case "emotion7":
                                    if (view.getId() == R.id.answer1Text) {
                                        blank1Text.setText(listeningSpeakingInfo.answer1_1);
                                        answer1Text.setVisibility(View.INVISIBLE);
                                        blank1Text.setBackground(getDrawable(R.drawable.green_rounded_corner));
                                        currentStateProgress++;
                                        dogruAudio.start();
                                    }else yanlisAudio.start();
                                    break;

                                case "emotion1":
                                case "emotion3":
                                case "emotion4":
                                case "emotion6":
                                    if (view.getId() == R.id.answer3Text) {
                                        blank1Text.setText(listeningSpeakingInfo.answer3_1);
                                        answer3Text.setVisibility(View.INVISIBLE);
                                        blank1Text.setBackground(getDrawable(R.drawable.green_rounded_corner));
                                        currentStateProgress++;
                                        dogruAudio.start();
                                    }else yanlisAudio.start();
                                    break;
                                case "emotion5":
                                    if (view.getId() == R.id.answer2Text) {
                                        blank1Text.setText(listeningSpeakingInfo.answer2_1);
                                        answer2Text.setVisibility(View.INVISIBLE);
                                        blank1Text.setBackground(getDrawable(R.drawable.green_rounded_corner));
                                        currentStateProgress++;
                                        dogruAudio.start();
                                    }else yanlisAudio.start();
                                    break;

                                //other emotions
                            }
                            break;
                        case 2:
                            switch (emotionName) {
                                case "happiness":
                                case "emotion5":
                                    if (view.getId() == R.id.answer1Text) {
                                        blank1Text.setText(listeningSpeakingInfo.answer1_2);
                                        answer1Text.setVisibility(View.INVISIBLE);
                                        blank1Text.setBackground(getDrawable(R.drawable.green_rounded_corner));
                                        currentStateProgress++;
                                        dogruAudio.start();
                                    }else yanlisAudio.start();
                                    break;
                                case "emotion1":
                                case "emotion3":
                                    if (view.getId() == R.id.answer3Text) {
                                        blank1Text.setText(listeningSpeakingInfo.answer3_2);
                                        answer3Text.setVisibility(View.INVISIBLE);
                                        blank1Text.setBackground(getDrawable(R.drawable.green_rounded_corner));
                                        currentStateProgress++;
                                        dogruAudio.start();
                                    }else yanlisAudio.start();
                                    break;
                                case "emotion2":
                                    if (view.getId() == R.id.answer4Text) {
                                        blank1Text.setText(listeningSpeakingInfo.answer4_2);
                                        answer4Text.setVisibility(View.INVISIBLE);
                                        blank1Text.setBackground(getDrawable(R.drawable.green_rounded_corner));
                                        currentStateProgress++;
                                        dogruAudio.start();
                                    }else yanlisAudio.start();
                                    break;
                                case "emotion4":
                                case "emotion7":
                                case "emotion6":
                                    if (view.getId() == R.id.answer2Text) {
                                        blank1Text.setText(listeningSpeakingInfo.answer2_2);
                                        answer2Text.setVisibility(View.INVISIBLE);
                                        blank1Text.setBackground(getDrawable(R.drawable.green_rounded_corner));
                                        currentStateProgress++;
                                        dogruAudio.start();
                                    }else yanlisAudio.start();
                                    break;
                                //other emotions
                            }
                            //
                            break;
                        case 3:
                            switch (emotionName) {
                                case "happiness":
                                case "emotion6":
                                    if (view.getId() == R.id.answer1Text) {
                                        blank1Text.setText(listeningSpeakingInfo.answer1_3);
                                        answer1Text.setVisibility(View.INVISIBLE);
                                        blank1Text.setBackground(getDrawable(R.drawable.green_rounded_corner));
                                        currentStateProgress++;
                                        dogruAudio.start();
                                    }else yanlisAudio.start();
                                    break;

                                case "emotion1":
                                case "emotion2":
                                case "emotion3":
                                case "emotion7":
                                    if (view.getId() == R.id.answer3Text) {
                                        blank1Text.setText(listeningSpeakingInfo.answer3_3);
                                        answer3Text.setVisibility(View.INVISIBLE);
                                        blank1Text.setBackground(getDrawable(R.drawable.green_rounded_corner));
                                        currentStateProgress++;
                                        dogruAudio.start();
                                    }else yanlisAudio.start();
                                    break;
                                case "emotion4":
                                case "emotion5":
                                    if (view.getId() == R.id.answer2Text) {
                                        blank1Text.setText(listeningSpeakingInfo.answer2_3);
                                        answer2Text.setVisibility(View.INVISIBLE);
                                        blank1Text.setBackground(getDrawable(R.drawable.green_rounded_corner));
                                        currentStateProgress++;
                                        dogruAudio.start();
                                    }else yanlisAudio.start();
                                    break;
                                //other emotions
                            }
                            //
                            break;
                        case 4:
                            switch (emotionName) {
                                case "happiness":
                                    if (view.getId() == R.id.answer1Text) {
                                        blank1Text.setText(listeningSpeakingInfo.answer1_4);
                                        answer1Text.setVisibility(View.INVISIBLE);
                                        blank1Text.setBackground(getDrawable(R.drawable.green_rounded_corner));
                                        currentStateProgress++;
                                        dogruAudio.start();
                                    }else yanlisAudio.start();
                                    break;

                                case "emotion1":
                                case "emotion2":
                                case "emotion4":
                                case "emotion6":
                                case "emotion7":
                                    if (view.getId() == R.id.answer2Text) {
                                        blank1Text.setText(listeningSpeakingInfo.answer2_4);
                                        answer2Text.setVisibility(View.INVISIBLE);
                                        blank1Text.setBackground(getDrawable(R.drawable.green_rounded_corner));
                                        currentStateProgress++;
                                        dogruAudio.start();
                                    }else yanlisAudio.start();
                                    break;
                                case "emotion3":
                                    if (view.getId() == R.id.answer4Text) {
                                        blank1Text.setText(listeningSpeakingInfo.answer4_4);
                                        answer4Text.setVisibility(View.INVISIBLE);
                                        blank1Text.setBackground(getDrawable(R.drawable.green_rounded_corner));
                                        currentStateProgress++;
                                        dogruAudio.start();
                                    }else yanlisAudio.start();
                                    break;
                                case "emotion5":
                                    if (view.getId() == R.id.answer3Text) {
                                        blank1Text.setText(listeningSpeakingInfo.answer3_4);
                                        answer3Text.setVisibility(View.INVISIBLE);
                                        blank1Text.setBackground(getDrawable(R.drawable.green_rounded_corner));
                                        currentStateProgress++;
                                        dogruAudio.start();
                                    }else yanlisAudio.start();
                                    break;
                                //other emotions
                            }
                            //
                            break;
                        case 5:
                            switch (emotionName) {
                                case "happiness":
                                case "emotion2":
                                case "emotion5":
                                case "emotion6":
                                case "emotion7":
                                    if (view.getId() == R.id.answer1Text) {
                                        blank1Text.setText(listeningSpeakingInfo.answer1_5);
                                        answer1Text.setVisibility(View.INVISIBLE);
                                        blank1Text.setBackground(getDrawable(R.drawable.green_rounded_corner));
                                        currentStateProgress++;
                                        dogruAudio.start();
                                    }else yanlisAudio.start();
                                    break;

                                case "emotion1":
                                case "emotion4":
                                    if (view.getId() == R.id.answer2Text) {
                                        blank1Text.setText(listeningSpeakingInfo.answer2_5);
                                        answer2Text.setVisibility(View.INVISIBLE);
                                        blank1Text.setBackground(getDrawable(R.drawable.green_rounded_corner));
                                        currentStateProgress++;
                                        dogruAudio.start();
                                    }else yanlisAudio.start();
                                    break;
                                case "emotion3":
                                    if (view.getId() == R.id.answer3Text) {
                                        blank1Text.setText(listeningSpeakingInfo.answer3_5);
                                        answer3Text.setVisibility(View.INVISIBLE);
                                        blank1Text.setBackground(getDrawable(R.drawable.green_rounded_corner));
                                        currentStateProgress++;
                                        dogruAudio.start();
                                    }else yanlisAudio.start();
                                    break;

                                //other emotions
                            }
                            //
                            break;

                    }
                    changeNextButtonColor();
            }
            return true;
        }
    };


    View.OnDragListener dragListener2 = new View.OnDragListener() {
        @Override
        public boolean onDrag(View v, DragEvent event) {

            final int dragEvent = event.getAction();
            switch (dragEvent) {

                case DragEvent.ACTION_DROP:
                    final View view = (View) event.getLocalState();

                    switch (stage) {
                        case 1:
                            switch (emotionName) {
                                case "happiness":
                                case "emotion2":
                                    if (view.getId() == R.id.answer4Text) {
                                        blank2Text.setText(listeningSpeakingInfo.answer4_1);
                                        answer4Text.setVisibility(View.INVISIBLE);
                                        blank2Text.setBackground(getDrawable(R.drawable.green_rounded_corner));
                                        currentStateProgress++;
                                        dogruAudio.start();
                                    }else yanlisAudio.start();
                                    break;

                                case "emotion1":
                                case "emotion3":
                                case "emotion4":
                                case "emotion6":
                                case "emotion7":
                                    if (view.getId() == R.id.answer2Text) {
                                        blank2Text.setText(listeningSpeakingInfo.answer2_1);
                                        answer2Text.setVisibility(View.INVISIBLE);
                                        blank2Text.setBackground(getDrawable(R.drawable.green_rounded_corner));
                                        currentStateProgress++;
                                        dogruAudio.start();
                                    }else yanlisAudio.start();
                                    break;
                                case "emotion5":
                                    if (view.getId() == R.id.answer1Text) {
                                        blank2Text.setText(listeningSpeakingInfo.answer1_1);
                                        answer1Text.setVisibility(View.INVISIBLE);
                                        blank2Text.setBackground(getDrawable(R.drawable.green_rounded_corner));
                                        currentStateProgress++;
                                        dogruAudio.start();
                                    }else yanlisAudio.start();
                                    break;
                                //other emotions
                            }
                            break;
                        case 2:
                            switch (emotionName) {
                                case "happiness":
                                case "emotion5":
                                    if (view.getId() == R.id.answer3Text) {
                                        blank2Text.setText(listeningSpeakingInfo.answer3_2);
                                        answer3Text.setVisibility(View.INVISIBLE);
                                        blank2Text.setBackground(getDrawable(R.drawable.green_rounded_corner));
                                        currentStateProgress++;
                                        dogruAudio.start();
                                    }else yanlisAudio.start();
                                    break;

                                case "emotion1":
                                case "emotion3":
                                    if (view.getId() == R.id.answer2Text) {
                                        blank2Text.setText(listeningSpeakingInfo.answer2_2);
                                        answer2Text.setVisibility(View.INVISIBLE);
                                        blank2Text.setBackground(getDrawable(R.drawable.green_rounded_corner));
                                        currentStateProgress++;
                                        dogruAudio.start();
                                    }else yanlisAudio.start();
                                    break;
                                case "emotion2":
                                case "emotion4":
                                case "emotion6":
                                case "emotion7":
                                    if (view.getId() == R.id.answer1Text) {
                                        blank2Text.setText(listeningSpeakingInfo.answer1_2);
                                        answer1Text.setVisibility(View.INVISIBLE);
                                        blank2Text.setBackground(getDrawable(R.drawable.green_rounded_corner));
                                        currentStateProgress++;
                                        dogruAudio.start();
                                    }else yanlisAudio.start();
                                    break;
                                //other emotions
                            }
                            //
                            break;
                        case 3:
                            switch (emotionName) {
                                case "happiness":
                                case "emotion5":
                                    if (view.getId() == R.id.answer3Text) {
                                        blank2Text.setText(listeningSpeakingInfo.answer3_3);
                                        answer3Text.setVisibility(View.INVISIBLE);
                                        blank2Text.setBackground(getDrawable(R.drawable.green_rounded_corner));
                                        currentStateProgress++;
                                        dogruAudio.start();
                                    }else yanlisAudio.start();
                                    break;
                                case "emotion1":
                                case "emotion7":
                                    if (view.getId() == R.id.answer2Text) {
                                        blank2Text.setText(listeningSpeakingInfo.answer2_3);
                                        answer2Text.setVisibility(View.INVISIBLE);
                                        blank2Text.setBackground(getDrawable(R.drawable.green_rounded_corner));
                                        currentStateProgress++;
                                        dogruAudio.start();
                                    }else yanlisAudio.start();
                                    break;
                                case "emotion2":
                                case "emotion4":
                                    if (view.getId() == R.id.answer1Text) {
                                        blank2Text.setText(listeningSpeakingInfo.answer1_3);
                                        answer1Text.setVisibility(View.INVISIBLE);
                                        blank2Text.setBackground(getDrawable(R.drawable.green_rounded_corner));
                                        currentStateProgress++;
                                        dogruAudio.start();
                                    }else yanlisAudio.start();
                                    break;
                                case "emotion6":
                                    if (view.getId() == R.id.answer4Text) {
                                        blank2Text.setText(listeningSpeakingInfo.answer4_3);
                                        answer4Text.setVisibility(View.INVISIBLE);
                                        blank2Text.setBackground(getDrawable(R.drawable.green_rounded_corner));
                                        currentStateProgress++;
                                        dogruAudio.start();
                                    }else yanlisAudio.start();
                                    break;
                                case "emotion3":
                                    if (view.getId() == R.id.answer1Text) {
                                        blank2Text.setText(listeningSpeakingInfo.answer1_3);
                                        answer1Text.setVisibility(View.INVISIBLE);
                                        blank2Text.setBackground(getDrawable(R.drawable.green_rounded_corner));
                                        currentStateProgress++;
                                        dogruAudio.start();
                                    }else yanlisAudio.start();
                                    break;
                                //other emotions
                            }
                            //
                            break;
                        case 4:
                            switch (emotionName) {
                                case "happiness":
                                case "emotion4":
                                    if (view.getId() == R.id.answer3Text) {
                                        blank2Text.setText(listeningSpeakingInfo.answer3_4);
                                        answer3Text.setVisibility(View.INVISIBLE);
                                        blank2Text.setBackground(getDrawable(R.drawable.green_rounded_corner));
                                        currentStateProgress++;
                                        dogruAudio.start();
                                    }else yanlisAudio.start();
                                    break;

                                case "emotion1":
                                case "emotion2":
                                case "emotion5":
                                case "emotion6":
                                    if (view.getId() == R.id.answer1Text) {
                                        blank2Text.setText(listeningSpeakingInfo.answer1_4);
                                        answer1Text.setVisibility(View.INVISIBLE);
                                        blank2Text.setBackground(getDrawable(R.drawable.green_rounded_corner));
                                        currentStateProgress++;
                                        dogruAudio.start();
                                    }else yanlisAudio.start();
                                    break;
                                case "emotion3":
                                    if (view.getId() == R.id.answer3Text) {
                                        blank2Text.setText("in");
                                        answer3Text.setVisibility(View.INVISIBLE);
                                        blank2Text.setBackground(getDrawable(R.drawable.green_rounded_corner));
                                        currentStateProgress++;
                                        dogruAudio.start();
                                    }else yanlisAudio.start();
                                    break;
                                case "emotion7":
                                    if (view.getId() == R.id.answer1Text) {
                                        blank2Text.setText(listeningSpeakingInfo.answer1_4);
                                        answer1Text.setVisibility(View.INVISIBLE);
                                        blank2Text.setBackground(getDrawable(R.drawable.green_rounded_corner));
                                        currentStateProgress++;
                                        dogruAudio.start();
                                    }else yanlisAudio.start();
                                    break;
                                //other emotions
                            }
                            //
                            break;
                        case 5:
                            switch (emotionName) {
                                case "happiness":
                                case "emotion2":
                                case "emotion5":
                                case "emotion6":
                                case "emotion7":
                                    if (view.getId() == R.id.answer3Text) {
                                        blank2Text.setText(listeningSpeakingInfo.answer3_5);
                                        answer3Text.setVisibility(View.INVISIBLE);
                                        blank2Text.setBackground(getDrawable(R.drawable.green_rounded_corner));
                                        currentStateProgress++;
                                        dogruAudio.start();
                                    }else yanlisAudio.start();
                                    break;

                                case "emotion1":
                                case "emotion3":
                                case "emotion4":
                                    if (view.getId() == R.id.answer1Text) {
                                        blank2Text.setText(listeningSpeakingInfo.answer1_5);
                                        answer1Text.setVisibility(View.INVISIBLE);
                                        blank2Text.setBackground(getDrawable(R.drawable.green_rounded_corner));
                                        currentStateProgress++;
                                        dogruAudio.start();
                                    }else yanlisAudio.start();
                                    break;
                                //other emotions
                            }
                            //
                            break;
                    }
                    changeNextButtonColor();
            }
            return true;
        }
    };

    View.OnDragListener dragListener3 = new View.OnDragListener() {
        @Override
        public boolean onDrag(View v, DragEvent event) {

            final int dragEvent = event.getAction();
            switch (dragEvent) {

                case DragEvent.ACTION_DROP:
                    final View view = (View) event.getLocalState();

                    switch (stage) {
                        case 1:
                            switch (emotionName) {
                                case "happiness":
                                    if (view.getId() == R.id.answer2Text) {
                                        blank3Text.setText(listeningSpeakingInfo.answer2_1);
                                        answer2Text.setVisibility(View.INVISIBLE);
                                        blank3Text.setBackground(getDrawable(R.drawable.green_rounded_corner));
                                        currentStateProgress++;
                                        dogruAudio.start();
                                    }else yanlisAudio.start();
                                    break;

                                case "emotion1":
                                case "emotion3":
                                case "emotion4":
                                case "emotion6":
                                    if (view.getId() == R.id.answer1Text) {
                                        blank3Text.setText(listeningSpeakingInfo.answer1_1);
                                        answer1Text.setVisibility(View.INVISIBLE);
                                        blank3Text.setBackground(getDrawable(R.drawable.green_rounded_corner));
                                        currentStateProgress++;
                                        dogruAudio.start();
                                    }else yanlisAudio.start();
                                    break;
                                case "emotion2":
                                case "emotion5":
                                case "emotion7":
                                    if (view.getId() == R.id.answer3Text) {
                                        blank3Text.setText(listeningSpeakingInfo.answer3_1);
                                        answer3Text.setVisibility(View.INVISIBLE);
                                        blank3Text.setBackground(getDrawable(R.drawable.green_rounded_corner));
                                        currentStateProgress++;
                                        dogruAudio.start();
                                    }else yanlisAudio.start();
                                    break;
                                //other emotions
                            }
                            break;
                        case 2:
                            switch (emotionName) {
                                case "happiness":
                                case "emotion2":
                                case "emotion5":
                                    if (view.getId() == R.id.answer2Text) {
                                        blank3Text.setText(listeningSpeakingInfo.answer2_2);
                                        answer2Text.setVisibility(View.INVISIBLE);
                                        blank3Text.setBackground(getDrawable(R.drawable.green_rounded_corner));
                                        currentStateProgress++;
                                        dogruAudio.start();
                                    }else yanlisAudio.start();
                                    break;

                                case "emotion1":
                                    if (view.getId() == R.id.answer1Text) {
                                        blank3Text.setText(listeningSpeakingInfo.answer1_2);
                                        answer1Text.setVisibility(View.INVISIBLE);
                                        blank3Text.setBackground(getDrawable(R.drawable.green_rounded_corner));
                                        currentStateProgress++;
                                        dogruAudio.start();
                                    }else yanlisAudio.start();
                                    break;
                                case "emotion3":
                                    if (view.getId() == R.id.answer4Text) {
                                        blank3Text.setText(listeningSpeakingInfo.answer4_2);
                                        answer4Text.setVisibility(View.INVISIBLE);
                                        blank3Text.setBackground(getDrawable(R.drawable.green_rounded_corner));
                                        currentStateProgress++;
                                        dogruAudio.start();
                                    }else yanlisAudio.start();
                                    break;
                                case "emotion4":
                                case "emotion6":
                                case "emotion7":
                                    if (view.getId() == R.id.answer3Text) {
                                        blank3Text.setText(listeningSpeakingInfo.answer3_2);
                                        answer3Text.setVisibility(View.INVISIBLE);
                                        blank3Text.setBackground(getDrawable(R.drawable.green_rounded_corner));
                                        currentStateProgress++;
                                        dogruAudio.start();
                                    }else yanlisAudio.start();
                                    break;
                                //other emotions
                            }
                            //
                            break;
                        case 3:
                            switch (emotionName) {
                                case "happiness":
                                case "emotion2":
                                case "emotion3":
                                case "emotion6":
                                    if (view.getId() == R.id.answer2Text) {
                                        blank3Text.setText(listeningSpeakingInfo.answer2_3);
                                        answer2Text.setVisibility(View.INVISIBLE);
                                        blank3Text.setBackground(getDrawable(R.drawable.green_rounded_corner));
                                        currentStateProgress++;
                                        dogruAudio.start();
                                    }else yanlisAudio.start();
                                    break;
                                case "emotion1":
                                case "emotion5":
                                case "emotion7":
                                    if (view.getId() == R.id.answer1Text) {
                                        blank3Text.setText(listeningSpeakingInfo.answer1_3);
                                        answer1Text.setVisibility(View.INVISIBLE);
                                        blank3Text.setBackground(getDrawable(R.drawable.green_rounded_corner));
                                        currentStateProgress++;
                                        dogruAudio.start();
                                    }else yanlisAudio.start();
                                    break;
                                case "emotion4":
                                    if (view.getId() == R.id.answer4Text) {
                                        blank3Text.setText(listeningSpeakingInfo.answer4_3);
                                        answer4Text.setVisibility(View.INVISIBLE);
                                        blank3Text.setBackground(getDrawable(R.drawable.green_rounded_corner));
                                        currentStateProgress++;
                                        dogruAudio.start();
                                    }else yanlisAudio.start();
                                    break;
                                //other emotions
                            }
                            //
                            break;
                        case 4:
                            switch (emotionName) {
                                case "happiness":
                                case "emotion3":
                                    if (view.getId() == R.id.answer2Text) {
                                        blank3Text.setText(listeningSpeakingInfo.answer2_4);
                                        answer2Text.setVisibility(View.INVISIBLE);
                                        blank3Text.setBackground(getDrawable(R.drawable.green_rounded_corner));
                                        currentStateProgress++;
                                        dogruAudio.start();
                                    }else yanlisAudio.start();
                                    break;

                                case "emotion1":
                                case "emotion2":
                                case "emotion6":
                                case "emotion7":
                                    if (view.getId() == R.id.answer3Text) {
                                        blank3Text.setText(listeningSpeakingInfo.answer3_4);
                                        answer3Text.setVisibility(View.INVISIBLE);
                                        blank3Text.setBackground(getDrawable(R.drawable.green_rounded_corner));
                                        currentStateProgress++;
                                        dogruAudio.start();
                                    }else yanlisAudio.start();
                                    break;
                                case "emotion4":
                                    if (view.getId() == R.id.answer1Text) {
                                        blank3Text.setText(listeningSpeakingInfo.answer1_4);
                                        answer1Text.setVisibility(View.INVISIBLE);
                                        blank3Text.setBackground(getDrawable(R.drawable.green_rounded_corner));
                                        currentStateProgress++;
                                        dogruAudio.start();
                                    }else yanlisAudio.start();
                                    break;
                                case "emotion5":
                                    if (view.getId() == R.id.answer2Text) {
                                        blank3Text.setText(listeningSpeakingInfo.answer2_4);
                                        answer2Text.setVisibility(View.INVISIBLE);
                                        blank3Text.setBackground(getDrawable(R.drawable.green_rounded_corner));
                                        currentStateProgress++;
                                        dogruAudio.start();
                                    }else yanlisAudio.start();
                                    break;
                                //other emotions
                            }
                            //
                            break;
                        case 5:
                            switch (emotionName) {
                                case "happiness":
                                case "emotion2":
                                case "emotion3":
                                case "emotion6":
                                case "emotion7":
                                    if (view.getId() == R.id.answer2Text) {
                                        blank3Text.setText(listeningSpeakingInfo.answer2_5);
                                        answer2Text.setVisibility(View.INVISIBLE);
                                        blank3Text.setBackground(getDrawable(R.drawable.green_rounded_corner));
                                        currentStateProgress++;
                                        dogruAudio.start();
                                    }else yanlisAudio.start();
                                    break;

                                case "emotion1":
                                case "emotion4":
                                    if (view.getId() == R.id.answer3Text) {
                                        blank3Text.setText(listeningSpeakingInfo.answer3_5);
                                        answer3Text.setVisibility(View.INVISIBLE);
                                        blank3Text.setBackground(getDrawable(R.drawable.green_rounded_corner));
                                        currentStateProgress++;
                                        dogruAudio.start();
                                    }else yanlisAudio.start();
                                    break;
                                case "emotion5":
                                    if (view.getId() == R.id.answer2Text) {
                                        blank3Text.setText(listeningSpeakingInfo.answer2_5);
                                        answer2Text.setVisibility(View.INVISIBLE);
                                        blank3Text.setBackground(getDrawable(R.drawable.green_rounded_corner));
                                        currentStateProgress++;
                                        dogruAudio.start();
                                    }else yanlisAudio.start();
                                    break;
                                //other emotions
                            }
                            //
                            break;
                    }
                    changeNextButtonColor();
            }
            return true;
        }
    };

    View.OnDragListener dragListener4 = new View.OnDragListener() {
        @Override
        public boolean onDrag(View v, DragEvent event) {

            final int dragEvent = event.getAction();
            switch (dragEvent) {

                case DragEvent.ACTION_DROP:
                    final View view = (View) event.getLocalState();

                    switch (stage) {
                        case 1:
                            switch (emotionName) {
                                case "happiness":
                                    if (view.getId() == R.id.answer3Text) {
                                        blank4Text.setText(listeningSpeakingInfo.answer3_1);
                                        answer3Text.setVisibility(View.INVISIBLE);
                                        blank4Text.setBackground(getDrawable(R.drawable.green_rounded_corner));
                                        currentStateProgress++;
                                        dogruAudio.start();
                                    }else yanlisAudio.start();
                                    break;

                                case "emotion1":
                                    if (view.getId() == R.id.answer4Text) {
                                        blank4Text.setText(listeningSpeakingInfo.answer4_1);
                                        answer4Text.setVisibility(View.INVISIBLE);
                                        blank4Text.setBackground(getDrawable(R.drawable.green_rounded_corner));
                                        currentStateProgress++;
                                        dogruAudio.start();
                                    }else yanlisAudio.start();
                                    break;
                                case "emotion2":
                                    if (view.getId() == R.id.answer2Text) {
                                        blank4Text.setText(listeningSpeakingInfo.answer2_1);
                                        answer2Text.setVisibility(View.INVISIBLE);
                                        blank4Text.setBackground(getDrawable(R.drawable.green_rounded_corner));
                                        currentStateProgress++;
                                        dogruAudio.start();
                                    }else yanlisAudio.start();
                                    break;
                                case "emotion4":
                                    if (view.getId() == R.id.answer4Text) {
                                        blank4Text.setText(listeningSpeakingInfo.answer4_1);
                                        answer4Text.setVisibility(View.INVISIBLE);
                                        blank4Text.setBackground(getDrawable(R.drawable.green_rounded_corner));
                                        currentStateProgress++;
                                        dogruAudio.start();
                                    }else yanlisAudio.start();
                                    break;
                                //other emotions
                            }
                            break;
                        case 2:
                            switch (emotionName) {
                                case "emotion2":
                                    if (view.getId() == R.id.answer3Text) {
                                        blank4Text.setText(listeningSpeakingInfo.answer3_2);
                                        answer3Text.setVisibility(View.INVISIBLE);
                                        blank4Text.setBackground(getDrawable(R.drawable.green_rounded_corner));
                                        currentStateProgress++;
                                        dogruAudio.start();
                                    }else yanlisAudio.start();
                                    break;
                                case "emotion3":
                                    if (view.getId() == R.id.answer1Text) {
                                        blank4Text.setText(listeningSpeakingInfo.answer1_2);
                                        answer1Text.setVisibility(View.INVISIBLE);
                                        blank4Text.setBackground(getDrawable(R.drawable.green_rounded_corner));
                                        currentStateProgress++;
                                        dogruAudio.start();
                                    }else yanlisAudio.start();
                                    break;
                                case "emotion5":
                                    if (view.getId() == R.id.answer4Text) {
                                        blank4Text.setText(listeningSpeakingInfo.answer4_2);
                                        answer4Text.setVisibility(View.INVISIBLE);
                                        blank4Text.setBackground(getDrawable(R.drawable.green_rounded_corner));
                                        currentStateProgress++;
                                        dogruAudio.start();
                                    }else yanlisAudio.start();
                                    break;
                                //emotionda 2.stage 4.durum yok
                                //other emotions
                            }
                            //
                            break;
                        case 3:
                            switch (emotionName) {
                                case "happiness":
                                    if (view.getId() == R.id.answer4Text) {
                                        blank4Text.setText(listeningSpeakingInfo.answer4_3);
                                        answer4Text.setVisibility(View.INVISIBLE);
                                        blank4Text.setBackground(getDrawable(R.drawable.green_rounded_corner));
                                        currentStateProgress++;
                                        dogruAudio.start();
                                    }else yanlisAudio.start();
                                    break;
                                case "emotion4":
                                case "emotion6":
                                    if (view.getId() == R.id.answer3Text) {
                                        blank4Text.setText(listeningSpeakingInfo.answer3_3);
                                        answer3Text.setVisibility(View.INVISIBLE);
                                        blank4Text.setBackground(getDrawable(R.drawable.green_rounded_corner));
                                        currentStateProgress++;
                                        dogruAudio.start();
                                    }else yanlisAudio.start();
                                    break;
                                //other emotions
                            }
                            //
                            break;
                        case 4:
                            switch (emotionName) {
                                case "emotion3":
                                    if (view.getId() == R.id.answer1Text) {
                                        blank4Text.setText(listeningSpeakingInfo.answer1_4);
                                        answer1Text.setVisibility(View.INVISIBLE);
                                        blank4Text.setBackground(getDrawable(R.drawable.green_rounded_corner));
                                        currentStateProgress++;
                                        dogruAudio.start();
                                    }else yanlisAudio.start();
                                    break;
                                case "emotion5":
                                    if (view.getId() == R.id.answer2Text) {
                                        blank4Text.setText(listeningSpeakingInfo.answer2_4);
                                        answer2Text.setVisibility(View.INVISIBLE);
                                        blank4Text.setBackground(getDrawable(R.drawable.green_rounded_corner));
                                        currentStateProgress++;
                                        dogruAudio.start();
                                    }else yanlisAudio.start();
                                    break;
                                //emotionda 4.stage 4.durum yok
                                //other emotions
                            }
                            //
                            break;
                        case 5:
                            switch (emotionName) {
                                case "emotion5":
                                    if (view.getId() == R.id.answer2Text) {
                                        blank4Text.setText(listeningSpeakingInfo.answer2_5);
                                        answer2Text.setVisibility(View.INVISIBLE);
                                        blank4Text.setBackground(getDrawable(R.drawable.green_rounded_corner));
                                        currentStateProgress++;
                                        dogruAudio.start();
                                    }else yanlisAudio.start();
                                    break;
                                //emotionda 5.stage 4.durum yok
                                //other emotions
                            }
                            //
                            break;
                    }
                    changeNextButtonColor();
            }
            return true;
        }
    };


}