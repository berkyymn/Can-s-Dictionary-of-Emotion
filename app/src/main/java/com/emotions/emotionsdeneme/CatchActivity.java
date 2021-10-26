package com.emotions.emotionsdeneme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;
import java.util.Random;

public class CatchActivity extends AppCompatActivity {
    ImageView picture, descriptionPicture, questionPic, emotionNamePic;
    TextView sentenceText,stageText,insText;
    ConstraintLayout layout_1;
    Handler handler;
    Runnable runnable;
    String emotionName;
    ImageButton nextButton;
    Time time;
    int a;
    int stage = 1;
    float x = 300, y = 1000;
    EmotionInfo emotionInfo;
    MediaPlayer mediaPlayer;
    Animation animation;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catch);

        layout_1 = findViewById(R.id.layout_1);

        insText = findViewById(R.id.insText_ls);
        picture = findViewById(R.id.picture);
        descriptionPicture = findViewById(R.id.descriptionPicture);
        emotionNamePic = findViewById(R.id.emotionNamePic);
        sentenceText = findViewById(R.id.sentenceText);
        stageText = findViewById(R.id.stageText);
        nextButton = findViewById(R.id.nextButton);
        questionPic = findViewById(R.id.questionPic);


        animation = AnimationUtils.loadAnimation(CatchActivity.this,R.anim.fadein);

        Intent intent = getIntent();
        emotionName = intent.getStringExtra("emotionNameIntent");
        implementEmotions(emotionName);

        Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/PatrickHand-Regular.ttf");
        sentenceText.setTypeface(tf);
        insText.setTypeface(tf);
        stageText.setTypeface(tf);

        descriptionPicture.setImageDrawable(emotionInfo.headPicture);
        emotionNamePic.setImageDrawable(emotionInfo.baslik);
        sentenceText.setText(emotionInfo.sentence1);
        picture.setImageDrawable(emotionInfo.picture1);
        emotionInfo.questionAudio.start();


        stageText.setText(stage+"/5");

        changePosition(picture);

    }

    public void changeTextViewBackground(Drawable textBackground, int left, int top, int right, int bottom,Drawable questionBackground){
        sentenceText.setBackground(textBackground);
        sentenceText.setPadding(left,top,right,bottom);
        questionPic.setImageDrawable(questionBackground);
    }


    public void changePosition(ImageView imageView) {

        handler = new Handler();
        runnable = new Runnable() {

            @Override
            public void run() {


                Random random = new Random();
                float i = random.nextFloat();

                if (i >= 0.5 && x < 760) {
                    //x = x + i * 5;
                } else {
                    //x = x - i * 7;

                }

                if (y < -300) {
                    a++;
                    y = 1000;
                    x = random.nextInt(80);
                    while (x<20 || x>70){
                        x = random.nextInt(80);
                    }

                    x = x*10;
                } else {
                    y -= 10;
                    imageView.setY(y);
                    imageView.setX(x);
                }

                handler.postDelayed(this, 20);
            }
        };
        handler.post(runnable);


    }

    public void pictureSelected(View view) {
        stage++;
        picture.setImageDrawable(null);

        picture.setClickable(false);
        new CountDownTimer(3000, 3000) {
            @Override
            public void onTick(long millisUntilFinished) {



                System.out.println("visible"+ picture.getVisibility());
                System.out.println("coundotnw");
                if (stage <= 5) {

                    switch (stage) {

                        case 1:
                            emotionInfo.sentence1Audio.start();

                            break;
                        case 2:

                            emotionInfo.sentence1Audio.start();

                            break;
                        case 3:

                            emotionInfo.sentence2Audio.start();
                            break;
                        case 4:

                            emotionInfo.sentence3Audio.start();
                            break;
                        case 5:

                            emotionInfo.sentence4Audio.start();
                            break;

                    }

                    //picture.startAnimation(animation);

                    stageText.setText(stage+"/5");



                }else {
                    int green = Color.parseColor("#5ECF0E");
                    nextButton.setBackgroundColor(green);
                    emotionInfo.sentence4Audio.stop();
                    emotionInfo.sentence5Audio.start();
                    stopHandler();
                    picture.setImageDrawable(null);

                }

                System.out.println("stage" + stage);
            }

            @Override
            public void onFinish() {
                //picture.setVisibility(View.VISIBLE);
                picture.setClickable(true);
                if (stage <= 5) {
                    Random random = new Random();
                    y = 1000;
                    x = random.nextInt(80);
                    while (x<20 || x>70){
                        x = random.nextInt(80);
                    }
                    x = x*10;

                    switch (stage) {

                        case 1:



                            break;
                        case 2:
                            picture.setImageDrawable(emotionInfo.picture2);
                            sentenceText.setText(emotionInfo.sentence2);

                            break;
                        case 3:
                            picture.setImageDrawable(emotionInfo.picture3);
                            sentenceText.setText(emotionInfo.sentence3);

                            break;
                        case 4:
                            picture.setImageDrawable(emotionInfo.picture4);
                            sentenceText.setText(emotionInfo.sentence4);

                            break;
                        case 5:
                            picture.setImageDrawable(emotionInfo.picture5);
                            sentenceText.setText(emotionInfo.sentence5);

                            break;

                    }
                    picture.startAnimation(animation);

                    stageText.setText(stage+"/5");

                }/*else {
                    int green = Color.parseColor("#5ECF0E");
                    nextButton.setBackgroundColor(green);
                    emotionInfo.sentence4Audio.stop();
                    emotionInfo.sentence5Audio.start();
                    stopHandler();
                    picture.setImageDrawable(null);

                }
                */
                System.out.println("stage" + stage);
            }
        }.start();
        /*
        if (stage <= 5) {
            Random random = new Random();
            y = 1000;
            x = random.nextInt(80);
            while (x<20 || x>70){
                x = random.nextInt(80);
            }
            x = x*10;

            switch (stage) {

                case 1:
                    emotionInfo.sentence1Audio.start();


                    break;
                case 2:
                    picture.setImageDrawable(emotionInfo.picture2);
                    sentenceText.setText(emotionInfo.sentence2);
                    emotionInfo.sentence1Audio.start();

                    break;
                case 3:
                    picture.setImageDrawable(emotionInfo.picture3);
                    sentenceText.setText(emotionInfo.sentence3);
                    emotionInfo.sentence1Audio.stop();
                    emotionInfo.sentence2Audio.start();
                    break;
                case 4:
                    picture.setImageDrawable(emotionInfo.picture4);
                    sentenceText.setText(emotionInfo.sentence4);
                    emotionInfo.sentence2Audio.stop();
                    emotionInfo.sentence3Audio.start();
                    break;
                case 5:
                    picture.setImageDrawable(emotionInfo.picture5);
                    sentenceText.setText(emotionInfo.sentence5);
                    emotionInfo.sentence3Audio.stop();
                    emotionInfo.sentence4Audio.start();
                    break;

            }
            picture.startAnimation(animation);

            stageText.setText(stage+"/5");

        }else {
            int green = Color.parseColor("#5ECF0E");
            nextButton.setBackgroundColor(green);
            emotionInfo.sentence4Audio.stop();
            emotionInfo.sentence5Audio.start();
            stopHandler();
            picture.setImageDrawable(null);

        }
        System.out.println("stage" + stage);
        */
    }

    public void implementEmotions(String name) {

        switch (name) {

            case "happiness":
                emotionInfo = new EmotionInfo("Happiness", "Happy",
                        "Because I am reading a book", "Because I am riding a bike!",
                        "Because I am eating birthday cake!", "Because we are playing a game!",
                        "Because I have a birthday present!", getDrawable(R.drawable.happiness_reading), getDrawable(R.drawable.happiness_bisiklet),
                        getDrawable(R.drawable.happiness_dugum_gunu), getDrawable(R.drawable.happiness_topluluk), getDrawable(R.drawable.happiness_hediye_kutusu),
                        getDrawable(R.drawable.happiness_kiz),getDrawable(R.drawable.happiness_baslik),
                        MediaPlayer.create(this,R.raw.happiness_because_i_am_reading_a_book),MediaPlayer.create(this,R.raw.happiness_because_i_am_riding_a_bike),
                        MediaPlayer.create(this,R.raw.happiness_because_i_am_eating_birthday_cake),MediaPlayer.create(this,R.raw.happiness_because_we_are_playing_a_game),
                        MediaPlayer.create(this,R.raw.happiness_because_i_have_a_birthday_present),
                        MediaPlayer.create(this,R.raw.happiness_why_are_you_happy));
                changeTextViewBackground(getDrawable(R.drawable.happiness_speak),60,40,0,0,getDrawable(R.drawable.happiness_2a));
                int happiness_color = Color.parseColor("#FCE7E6");
                layout_1.setBackgroundColor(happiness_color);
                break;

            case "emotion1":
                emotionInfo = new EmotionInfo("Sadness", "Sadd",
                        "Because I am lonely", "Because my brother shouts at me",
                        "Because my sister takes my toy", "Because my sister is ill",
                        "Because my dog is lost", getDrawable(R.drawable.sadness_3), getDrawable(R.drawable.sadness_5),
                        getDrawable(R.drawable.sadness_2), getDrawable(R.drawable.sadness_6), getDrawable(R.drawable.sadness_4),
                        getDrawable(R.drawable.sadness_1),getDrawable(R.drawable.sadness_baslik),
                        MediaPlayer.create(this,R.raw.sadness_because_i_am_lonely),MediaPlayer.create(this,R.raw.sadness_because_my_brother_shouts_at_me),
                        MediaPlayer.create(this,R.raw.sadness_because_my_sister_takes_my_toy),MediaPlayer.create(this,R.raw.sadness_because_my_sister_is_ill),
                        MediaPlayer.create(this,R.raw.sadness_because_my_dog_is_lost),
                        MediaPlayer.create(this,R.raw.sadness_why_are_you_sad));
                changeTextViewBackground(getDrawable(R.drawable.sadness_speak),150,60,50,0,getDrawable(R.drawable.sadness_2a));
                int saddness_color = Color.parseColor("#A3CA90C2");
                layout_1.setBackgroundColor(saddness_color);
                break;

            case "emotion2":
                emotionInfo = new EmotionInfo("Fear", "fear",
                        "Because there is a spider in my room", "I am having nightmares",
                        "Because my room is dark", "Because I don't want to go to the dentist",
                        "Because there is ghost in the cartoon", getDrawable(R.drawable.korku3), getDrawable(R.drawable.fear_bed),
                        getDrawable(R.drawable.fear_3), getDrawable(R.drawable.fear11), getDrawable(R.drawable.fear12),
                        getDrawable(R.drawable.fear10),getDrawable(R.drawable.fear_baslik),
                        MediaPlayer.create(this,R.raw.fear_because_there_is_a_spider_in_my_room),MediaPlayer.create(this,R.raw.fear_i_am_having_nightmares),
                        MediaPlayer.create(this,R.raw.fear_because_my_room_is_dark),MediaPlayer.create(this,R.raw.fear_because_i_dont_want_to_go_to_the_dentist),
                        MediaPlayer.create(this,R.raw.fear_because_there_is_a_ghost_in_the_cartoon),
                        MediaPlayer.create(this,R.raw.fear_why_are_you_scared));
                int white = Color.parseColor("#FFFFFFFF");
                sentenceText.setTextColor(white);

                changeTextViewBackground(getDrawable(R.drawable.fear_speak),100,50,20,0,getDrawable(R.drawable.fear_2a));
                int fear_color = Color.parseColor("#435D308F");
                layout_1.setBackgroundColor(fear_color);
                break;

            case "emotion3":
                emotionInfo = new EmotionInfo("Disgust", "disgust",
                        "Because there is a fly in the soup.", "Because there is a worm in the apple.",
                        "Because they smell bad.", "Because it smells awful.",
                        "Because I hate flies.", getDrawable(R.drawable.disgust__1), getDrawable(R.drawable.disgust_kusma_3),
                        getDrawable(R.drawable.disgust_2), getDrawable(R.drawable.disgust_igrelti), getDrawable(R.drawable.disgust_9),
                        getDrawable(R.drawable.disgust_adam),getDrawable(R.drawable.disgust_baslik),
                        MediaPlayer.create(this,R.raw.disgust_because_there_is_a_fly_in_the_soup),MediaPlayer.create(this,R.raw.disgust_because_there_is_a_worm_in_the_apple),
                        MediaPlayer.create(this,R.raw.disgust_because_they_smell_bad),MediaPlayer.create(this,R.raw.disgust_because_it_smells_awful),
                        MediaPlayer.create(this,R.raw.disgust_because_i_hate_flies),
                        MediaPlayer.create(this,R.raw.disgust_why_are_you_disgusted));
                changeTextViewBackground(getDrawable(R.drawable.disgust_speak),120,40,0,0,getDrawable(R.drawable.disgust_2a));
                int disgust_color = Color.parseColor("#A81AB360");
                layout_1.setBackgroundColor(disgust_color);
                break;

            case "emotion4":
                emotionInfo = new EmotionInfo("Anger", "anger",
                        "Because Zeynep takes my candy.", "Because Emir takes my toy.",
                        "Because Oya makes fun of me", "Because I am hungry.",
                        "Because my friends don't play with me", getDrawable(R.drawable.anger_22), getDrawable(R.drawable.anger_66),
                        getDrawable(R.drawable.anger_1222), getDrawable(R.drawable.anger_44), getDrawable(R.drawable.anger_16),
                        getDrawable(R.drawable.anger_11),getDrawable(R.drawable.anger_baslik),
                        MediaPlayer.create(this,R.raw.anger_because_zeynep_takes_my_candy),MediaPlayer.create(this,R.raw.anger_because_emir_takes_my_toy),
                        MediaPlayer.create(this,R.raw.anger_because_oya_makes_fun_of_me),MediaPlayer.create(this,R.raw.anger_because_i_m_hungry),
                        MediaPlayer.create(this,R.raw.anger_because_my_friends_dont_play_with_me),
                        MediaPlayer.create(this,R.raw.anger_why_are_you_angry));
                changeTextViewBackground(getDrawable(R.drawable.anger_speak),100,45,0,0,getDrawable(R.drawable.anger_2a));
                int anger_color = Color.parseColor("#8BE34E4E");
                layout_1.setBackgroundColor(anger_color);
                break;
            case "emotion5":
                emotionInfo = new EmotionInfo("Hate", "hate",
                        "Because she hits me.", "Because he breaks my toy.",
                        "Because I hate sleeping earlier.", "Because insects are disgusting!",
                        "Because he is pulling my hair.", getDrawable(R.drawable.hate2), getDrawable(R.drawable.hate_2),
                        getDrawable(R.drawable.hate3), getDrawable(R.drawable.hate_4), getDrawable(R.drawable.hate_5),
                        getDrawable(R.drawable.hate_5),getDrawable(R.drawable.hate_baslik),
                        MediaPlayer.create(this,R.raw.hate_because_she_hits_me),MediaPlayer.create(this,R.raw.hate_because_he_breaks_my_toy),
                        MediaPlayer.create(this,R.raw.hate_because_i_hate_sleeping_earlier),MediaPlayer.create(this,R.raw.hate_because_insects_are_disgusting),
                        MediaPlayer.create(this,R.raw.hate_because_he_is_puling_my_hair),
                        MediaPlayer.create(this,R.raw.hate_why_do_you_hate));
                changeTextViewBackground(getDrawable(R.drawable.hate_speak),150,60,0,0,getDrawable(R.drawable.hate_2a));
                int hate_color = Color.parseColor("#7A5A1855");
                layout_1.setBackgroundColor(hate_color);
                break;
            case "emotion6":
                emotionInfo = new EmotionInfo("Shame", "shame",
                        "Because the room is crowded.", "Because I am new in the class",
                        "I am ashamed to play", "I am ashamed to sing",
                        "I am ashamed to dance", getDrawable(R.drawable.shame_8), getDrawable(R.drawable.shame_7),
                        getDrawable(R.drawable.shame_3), getDrawable(R.drawable.shame_9), getDrawable(R.drawable.shame_1),
                        getDrawable(R.drawable.shame_9),getDrawable(R.drawable.shame_baslik),
                        MediaPlayer.create(this,R.raw.shame_because_the_room_is_crowded),MediaPlayer.create(this,R.raw.shame_because_i_am_new_in_the_class),
                        MediaPlayer.create(this,R.raw.shame_i_am_ashamed_to_play),MediaPlayer.create(this,R.raw.shame_i_am_ashamed_to_sing),
                        MediaPlayer.create(this,R.raw.shame_i_am_ashamed_to_dance),
                        MediaPlayer.create(this,R.raw.shame_why_are_you_ashamed));
                changeTextViewBackground(getDrawable(R.drawable.shame_speak),130,50,110,0,getDrawable(R.drawable.shame_2a));
                int shame_color = Color.parseColor("#92D53971");
                layout_1.setBackgroundColor(shame_color);
                break;
            case "emotion7":
                emotionInfo = new EmotionInfo("Wonder", "wonder",
                        "Because it is a spiderman T-shirt", "Because I am going to the zoo",
                        "Because it is a red firetruck", "Oh! my favorite cartoon is on TV",
                        "Because there are balloons in the sky", getDrawable(R.drawable.wonder_10), getDrawable(R.drawable.wonder_12),
                        getDrawable(R.drawable.wonder_2), getDrawable(R.drawable.wonder_11), getDrawable(R.drawable.wonder_13),
                        getDrawable(R.drawable.wonder_4),getDrawable(R.drawable.wonder_baslik),
                        MediaPlayer.create(this,R.raw.wonder_because_it_is_spiderman_t_hirt),MediaPlayer.create(this,R.raw.wonder_because_i_going_to_the_zoo),
                        MediaPlayer.create(this,R.raw.wonder_because_it_is_a_red_firetruck),MediaPlayer.create(this,R.raw.wonder_my_favourite_cartoon_on_tv),
                        MediaPlayer.create(this,R.raw.wonnder_because_there_are_baloon_in_the_sky),
                        MediaPlayer.create(this,R.raw.wonder_why_are_you_so_surprised));
                changeTextViewBackground(getDrawable(R.drawable.wonder_speak),100,60,40,0,getDrawable(R.drawable.wonder_2a));
                int wonder_color = Color.parseColor("#9EFEED21");
                layout_1.setBackgroundColor(wonder_color);
                break;
            case "emotion8":

                break;

        }

    }

    public void stopHandler(){
        handler.removeCallbacks(runnable);
    }

    public void goToNextActivity(View view){
        if (stage >5){
            emotionInfo.sentence5Audio.stop();
            Intent intent = new Intent(this,ListeningActivity.class);
            intent.putExtra("emotionNameIntent",emotionName);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish();


        }else {
            Toast.makeText(this,"First touch all the pictures",Toast.LENGTH_SHORT).show();
            emotionInfo.sentence5Audio.stop();
            Intent intent = new Intent(this,ListeningActivity.class);
            intent.putExtra("emotionNameIntent",emotionName);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish();
        }
    }

}