package com.emotions.emotionsdeneme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.ClipData;
import android.content.Intent;
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

public class ListeningActivity extends AppCompatActivity {
    ListeningInfo listeningInfo;
    TextView  dialogue1Text,dialogue2Text,dialogue3Text,dialogue4Text,dialogue5Text,insText_ls;
    ImageView dialogue1Picture,dialogue2Picture,dialogue3Picture,dialogue4Picture,dialogue5Picture,emotionNameText;
    String emotionName;
    ConstraintLayout layout_AL;
    ImageButton nextButton, audio1Button,audio2Button,audio3Button,audio4Button,audio5Button;
    boolean dialogue1Played = false, dialogue2Played = false, dialogue3Played=false, dialogue4Played=false, dialogue5Played=false;
    int score = 0;
    MediaPlayer dogruAudio,yanlisAudio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listening);
        layout_AL = findViewById(R.id.layout_AL);

        emotionNameText = findViewById(R.id.emotionNameText);
        dialogue1Text = findViewById(R.id.dialogue1Text);
        dialogue2Text = findViewById(R.id.dialogue2Text);
        dialogue3Text = findViewById(R.id.dialogue3Text);
        dialogue4Text = findViewById(R.id.dialogue4Text);
        dialogue5Text = findViewById(R.id.dialogue5Text);
        nextButton = findViewById(R.id.nextButton);
        insText_ls = findViewById(R.id.insText_ls);

        dialogue1Picture = findViewById(R.id.dialogue1Picture);
        dialogue2Picture = findViewById(R.id.dialogue2Picture);
        dialogue3Picture = findViewById(R.id.dialogue3Picture);
        dialogue4Picture = findViewById(R.id.dialogue4Picture);
        dialogue5Picture = findViewById(R.id.dialogue5Picture);

        audio1Button = findViewById(R.id.audio1Button);
        audio2Button = findViewById(R.id.audio2Button);
        audio3Button = findViewById(R.id.audio3Button);
        audio4Button = findViewById(R.id.audio4Button);
        audio5Button = findViewById(R.id.audio5Button);

        Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/PatrickHand-Regular.ttf");
        dialogue1Text.setTypeface(tf);
        dialogue2Text.setTypeface(tf);
        dialogue3Text.setTypeface(tf);
        dialogue4Text.setTypeface(tf);
        dialogue5Text.setTypeface(tf);
        insText_ls.setTypeface(tf);


        Intent intent = getIntent();
        emotionName = intent.getStringExtra("emotionNameIntent");

        dogruAudio = MediaPlayer.create(this,R.raw.dogru_audio);
        yanlisAudio = MediaPlayer.create(this,R.raw.yanlis_audio);

        getListeningInfo(emotionName);

        emotionNameText.setImageDrawable(listeningInfo.baslik);
        dialogue1Text.setText(listeningInfo.dialogue1);
        dialogue2Text.setText(listeningInfo.dialogue2);
        dialogue3Text.setText(listeningInfo.dialogue3);
        dialogue4Text.setText(listeningInfo.dialogue4);
        dialogue5Text.setText(listeningInfo.dialogue5);
        dialogue1Picture.setImageDrawable(listeningInfo.picture1);
        dialogue2Picture.setImageDrawable(listeningInfo.picture2);
        dialogue3Picture.setImageDrawable(listeningInfo.picture3);
        dialogue4Picture.setImageDrawable(listeningInfo.picture4);
        dialogue5Picture.setImageDrawable(listeningInfo.picture5);

        dialogue1Text.setOnLongClickListener(longClickListener);
        dialogue2Text.setOnLongClickListener(longClickListener);
        dialogue3Text.setOnLongClickListener(longClickListener);
        dialogue4Text.setOnLongClickListener(longClickListener);
        dialogue5Text.setOnLongClickListener(longClickListener);

        dialogue1Picture.setOnDragListener(dragListener1);
        dialogue2Picture.setOnDragListener(dragListener2);
        dialogue3Picture.setOnDragListener(dragListener3);
        dialogue4Picture.setOnDragListener(dragListener4);
        dialogue5Picture.setOnDragListener(dragListener5);


    }

    public void goToNext(View view){
        if (score>=5){
            Intent intent = new Intent(ListeningActivity.this,ListeningSpeakingActivity.class);
            intent.putExtra("emotionNameIntent",emotionName);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish();
        }else {
            Toast.makeText(ListeningActivity.this,"You should complete all the sentences before",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ListeningActivity.this,ListeningSpeakingActivity.class);
            intent.putExtra("emotionNameIntent",emotionName);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish();
        }
    }

    public void myChangeTextViewBackground(Drawable textBackground, int left, int top, int right, int bottom){
        dialogue1Text.setBackground(textBackground);
        dialogue2Text.setBackground(textBackground);

        dialogue1Text.setPadding(left,top,right,bottom);
        dialogue2Text.setPadding(left,top,right,bottom);

        dialogue3Text.setBackground(textBackground);
        dialogue4Text.setBackground(textBackground);

        dialogue3Text.setPadding(left,top,right,bottom);
        dialogue4Text.setPadding(left,top,right,bottom);

        dialogue5Text.setBackground(textBackground);
        dialogue5Text.setPadding(left,top,right,bottom);

    }

    public void playDogru(){
        dogruAudio.start();
    }

    public void playYanlis(){
        yanlisAudio.start();
    }

    public void playDialogue1(View view){
        if (!listeningInfo.dialogue2Audio.isPlaying() && !listeningInfo.dialogue3Audio.isPlaying() &&
        !listeningInfo.dialogue4Audio.isPlaying() && !listeningInfo.dialogue5Audio.isPlaying()) {
            listeningInfo.dialogue1Audio.start();
            dialogue1Played = true;
        }
    }
    public void playDialogue2(View view){
        if (!listeningInfo.dialogue1Audio.isPlaying() && !listeningInfo.dialogue3Audio.isPlaying() &&
                !listeningInfo.dialogue4Audio.isPlaying() && !listeningInfo.dialogue5Audio.isPlaying()) {
            listeningInfo.dialogue2Audio.start();
            dialogue2Played = true;
        }
    }
    public void playDialogue3(View view){
        if (!listeningInfo.dialogue2Audio.isPlaying() && !listeningInfo.dialogue1Audio.isPlaying() &&
                !listeningInfo.dialogue4Audio.isPlaying() && !listeningInfo.dialogue5Audio.isPlaying()) {
            listeningInfo.dialogue3Audio.start();
            dialogue3Played = true;
        }
    }
    public void playDialogue4(View view){
        if (!listeningInfo.dialogue2Audio.isPlaying() && !listeningInfo.dialogue3Audio.isPlaying() &&
                !listeningInfo.dialogue1Audio.isPlaying() && !listeningInfo.dialogue5Audio.isPlaying()) {
            listeningInfo.dialogue4Audio.start();
            dialogue4Played = true;
        }
    }
    public void playDialogue5(View view){
        if (!listeningInfo.dialogue2Audio.isPlaying() && !listeningInfo.dialogue3Audio.isPlaying() &&
                !listeningInfo.dialogue4Audio.isPlaying() && !listeningInfo.dialogue1Audio.isPlaying()) {
            listeningInfo.dialogue5Audio.start();
            dialogue5Played = true;
        }
    }

    public void getListeningInfo(String emotionName){

        switch (emotionName){

            case "happiness":
                listeningInfo = new ListeningInfo("Happiness","I am reading a book",
                        "I have a birthday present","We are playing a game", "I am riding a bike",
                        "I am eating a birthday cake", MediaPlayer.create(this,R.raw.happiness_i_am_riding_a_bike),
                        MediaPlayer.create(this,R.raw.happiness_i_am_reading_a_book),MediaPlayer.create(this,R.raw.happiness_i_am_eating_birthday_cake),
                        MediaPlayer.create(this,R.raw.happiness_we_are_playing_a_game),MediaPlayer.create(this,R.raw.happiness_oh_i_have_a_birthday_present),
                        getDrawable(R.drawable.happiness_bisiklet),getDrawable(R.drawable.happiness_reading),getDrawable(R.drawable.happiness_dugum_gunu),
                        getDrawable(R.drawable.happiness_topluluk),getDrawable(R.drawable.happiness_dogum_gunu_cocuk),
                        getDrawable(R.drawable.happiness_baslik));
                myChangeTextViewBackground(getDrawable(R.drawable.happiness_speak),70,40,0,0);
                int happiness_color = Color.parseColor("#FCE7E6");
                layout_AL.setBackgroundColor(happiness_color);
                break;

            case "emotion1":
                listeningInfo = new ListeningInfo("Sadness","My brother shouts at me",
                        "My sister is ill","I am lonely", "My dog is lost",
                        "My sister takes my toy", MediaPlayer.create(this,R.raw.sadness_my_sister_is_ill),
                        MediaPlayer.create(this,R.raw.sadness_my_sister_takes_my_toy),MediaPlayer.create(this,R.raw.sadness_i_am_lonely),
                        MediaPlayer.create(this,R.raw.sadness_my_brother_shouts_at_me),MediaPlayer.create(this,R.raw.sadness_my_dog_is_lost),
                        getDrawable(R.drawable.sadness_6),getDrawable(R.drawable.sadness_2),getDrawable(R.drawable.sadness_3),
                        getDrawable(R.drawable.sadness_5),getDrawable(R.drawable.sadness_4),
                        getDrawable(R.drawable.sadness_baslik));
                myChangeTextViewBackground(getDrawable(R.drawable.sadness_speak),135,40,30,0);
                int saddness_color = Color.parseColor("#A3CA90C2");
                layout_AL.setBackgroundColor(saddness_color);
                break;
            case "emotion2":
                listeningInfo = new ListeningInfo("Fear","There is a ghost in the cartoon",
                        "There is a spider","I am having nightmares", "I don't want to go to the dentist",
                        "My room is dark", MediaPlayer.create(this,R.raw.fear_there_is_a_spider),
                        MediaPlayer.create(this,R.raw.fear_i_am_having_nightmares),MediaPlayer.create(this,R.raw.fear_my_room_is_dark),
                        MediaPlayer.create(this,R.raw.fear_there_is_a_ghost_in_the_cartoon),MediaPlayer.create(this,R.raw.fear_i_dont_want_to_go_to_the_dentist),
                        getDrawable(R.drawable.orumcek),getDrawable(R.drawable.fear_bed),getDrawable(R.drawable.fear_3),
                        getDrawable(R.drawable.fear_5),getDrawable(R.drawable.fear11),
                        getDrawable(R.drawable.fear_baslik));
                myChangeTextViewBackground(getDrawable(R.drawable.fear_speak),70,35,0,0);
                int white = Color.parseColor("#FFFFFFFF");
                dialogue1Text.setTextColor(white);
                dialogue2Text.setTextColor(white);
                dialogue3Text.setTextColor(white);
                dialogue4Text.setTextColor(white);
                dialogue5Text.setTextColor(white);
                int fear_color = Color.parseColor("#435D308F");
                layout_AL.setBackgroundColor(fear_color);
                break;
            case "emotion3":
                listeningInfo = new ListeningInfo("Disgust","Ugh! It’s disgusting. There is a fly in the soup",
                        "It's awful. There is a worm in the apple","They smell bad", "I dislike the soup. It smells bad",
                        "I hate flies",MediaPlayer.create(this,R.raw.disgust_i_hate_flies),
                        MediaPlayer.create(this,R.raw.disgust_ugh_its_disgusting_there_is_a_fly_in_the_soup),MediaPlayer.create(this,R.raw.disgust_i_dislike_the_soup_it_smells_bad),
                        MediaPlayer.create(this,R.raw.disgust_they_smell_bad), MediaPlayer.create(this,R.raw.disgust_its_awful_there_is_a_worm_in_the_apple),
                        getDrawable(R.drawable.disgust_kiz),getDrawable(R.drawable.disgust_7),getDrawable(R.drawable.disgust_igrelti),
                        getDrawable(R.drawable.disgust_6),getDrawable(R.drawable.disgust_kurt),
                        getDrawable(R.drawable.disgust_baslik));
                myChangeTextViewBackground(getDrawable(R.drawable.disgust_speak),100,30,0,0);
                dialogue1Text.setTextSize(14);
                dialogue2Text.setTextSize(14);
                dialogue3Text.setTextSize(14);
                dialogue4Text.setTextSize(14);
                dialogue5Text.setTextSize(14);
                int disgust_color = Color.parseColor("#A81AB360");
                layout_AL.setBackgroundColor(disgust_color);
                break;
            case "emotion4":
                listeningInfo = new ListeningInfo("Anger","My friend makes fun of me",
                        "My friends don't play with me","I am hungry", "My brother takes my toy",
                        "My sister takes my candy", MediaPlayer.create(this,R.raw.anger_my_sister_takes_my_candy),
                        MediaPlayer.create(this,R.raw.anger_i_am_hungry),MediaPlayer.create(this,R.raw.anger_my_friend_makes_fun_of_me),
                        MediaPlayer.create(this,R.raw.anger_my_friends_dont_play_with_me),MediaPlayer.create(this,R.raw.anger_my_brother_takes_my_toy),
                        getDrawable(R.drawable.anger_22),getDrawable(R.drawable.anger_44),getDrawable(R.drawable.anger_1222),
                        getDrawable(R.drawable.anger_16),getDrawable(R.drawable.anger_66),
                        getDrawable(R.drawable.anger_baslik));
                myChangeTextViewBackground(getDrawable(R.drawable.anger_speak),80,35,0,0);
                int anger_color = Color.parseColor("#8BE34E4E");
                layout_AL.setBackgroundColor(anger_color);
                break;

            case "emotion5":
                listeningInfo = new ListeningInfo("Hate","Olivia hits me",
                        "I hate sleeping earlier!","William pulls my hair!", "My brother breaks my toy",
                        "I don't like insects!", MediaPlayer.create(this,R.raw.hate_olivia_hits_me),
                        MediaPlayer.create(this,R.raw.hate_my_brother_breaks_my_toy),MediaPlayer.create(this,R.raw.hate_i_hate_sleeping_earlier),
                        MediaPlayer.create(this,R.raw.hate_i_dont_like_insects),MediaPlayer.create(this,R.raw.hate_william_pulls_my_hair),
                        getDrawable(R.drawable.hate2),getDrawable(R.drawable.hate_2),getDrawable(R.drawable.hate3),
                        getDrawable(R.drawable.hate_4),getDrawable(R.drawable.hate_5),
                        getDrawable(R.drawable.hate_baslik));
                myChangeTextViewBackground(getDrawable(R.drawable.hate_speak),120,55,0,0);
                int hate_color = Color.parseColor("#7A5A1855");
                layout_AL.setBackgroundColor(hate_color);
                break;
            case "emotion6":
                listeningInfo = new ListeningInfo("Shame","I am ashamed to dance",
                        "I am new in the class","I am ashamed to sing", "The room is crowded.",
                        "I am ashamed to play", MediaPlayer.create(this,R.raw.shame_the_room_is_crowded),
                        MediaPlayer.create(this,R.raw.shame_i_am_new_in_the_class),MediaPlayer.create(this,R.raw.shame_i_am_ashamed_to_play),
                        MediaPlayer.create(this,R.raw.shame_i_am_ashamed_to_sing),MediaPlayer.create(this,R.raw.shame_i_am_ashamed_to_dance),
                        getDrawable(R.drawable.shame_8),getDrawable(R.drawable.shame_7),getDrawable(R.drawable.shame_3),
                        getDrawable(R.drawable.shame_9),getDrawable(R.drawable.shame_1),
                        getDrawable(R.drawable.shame_baslik));
                myChangeTextViewBackground(getDrawable(R.drawable.shame_speak),100,40,80,0);
                int shame_color = Color.parseColor("#92D53971");
                layout_AL.setBackgroundColor(shame_color);
                break;
            case "emotion7":
                listeningInfo = new ListeningInfo("Wonder","I am going to the zoo",
                        "Oh! My favorite cartoon is on TV ","It is a red firetruck", "It is a spiderman T-shirt",
                        "There are balloons in the sky", MediaPlayer.create(this,R.raw.wonder_it_is_spider_man_t_shirt),
                        MediaPlayer.create(this,R.raw.wonder_i_am_going_to_the_zoo),MediaPlayer.create(this,R.raw.wonder_it_is_red_firetruck),
                        MediaPlayer.create(this,R.raw.wonder_my_favourite_cartoon_on_tv),MediaPlayer.create(this,R.raw.wonder_there_are_baloons_in_the_sky),
                        getDrawable(R.drawable.wonder_10),getDrawable(R.drawable.wonder_12),getDrawable(R.drawable.wonder_2),
                        getDrawable(R.drawable.wonder_11),getDrawable(R.drawable.wonder_13),
                        getDrawable(R.drawable.wonder_baslik));
                myChangeTextViewBackground(getDrawable(R.drawable.wonder_speak),70,40,20,0);
                int wonder_color = Color.parseColor("#9EFEED21");
                layout_AL.setBackgroundColor(wonder_color);
                break;
            case "emotion8":

        }
    }

    public boolean isTestCompleted(){
        if (score>=5){
            int green = Color.parseColor("#5ECF0E");
            nextButton.setBackgroundColor(green);
            return true;

        }else return false;
    }

    View.OnLongClickListener longClickListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            ClipData data = ClipData.newPlainText("","");
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
            v.startDragAndDrop(data,shadowBuilder,v,0);
            //api kontrol et 23 aşağısını da desteklet
            return true;
        }
    };

    View.OnDragListener dragListener1 = new View.OnDragListener() {
        @Override
        public boolean onDrag(View v, DragEvent event) {

            final int dragEvent = event.getAction();
            switch (dragEvent){
                case DragEvent.ACTION_DROP:
                    if (dialogue1Played == false){
                        Toast.makeText(ListeningActivity.this,"You should listen the audio first", Toast.LENGTH_SHORT).show();
                        Animation animation = AnimationUtils.loadAnimation(ListeningActivity.this,R.anim.blink_anim);
                        audio1Button.startAnimation(animation);
                        return false;
                    }
                    final View view = (View) event.getLocalState();


                    switch (emotionName){
                        case "happiness":
                            if (view.getId() == R.id.dialogue4Text){
                                dialogue1Picture.setImageDrawable(getDrawable(R.drawable.indir));
                                dialogue4Text.setVisibility(View.INVISIBLE);
                                score++;
                                playDogru();
                            }else playYanlis();
                            break;
                        case "emotion1":
                            if (view.getId() == R.id.dialogue2Text){
                                dialogue1Picture.setImageDrawable(getDrawable(R.drawable.indir));
                                dialogue2Text.setVisibility(View.INVISIBLE);
                                score++;
                                playDogru();
                            }else playYanlis();
                            break;
                        case "emotion2":
                            if (view.getId() == R.id.dialogue2Text){
                                dialogue1Picture.setImageDrawable(getDrawable(R.drawable.indir));
                                dialogue2Text.setVisibility(View.INVISIBLE);
                                score++;
                                playDogru();
                            }else playYanlis();
                            break;
                        case "emotion3":
                            if (view.getId() == R.id.dialogue5Text){
                                dialogue1Picture.setImageDrawable(getDrawable(R.drawable.indir));
                                dialogue5Text.setVisibility(View.INVISIBLE);
                                score++;
                                playDogru();
                            }else playYanlis();
                            break;
                        case "emotion4":
                            if (view.getId() == R.id.dialogue5Text){
                                dialogue1Picture.setImageDrawable(getDrawable(R.drawable.indir));
                                dialogue5Text.setVisibility(View.INVISIBLE);
                                score++;
                                playDogru();
                            }else playYanlis();
                            break;
                        case "emotion5":
                            if (view.getId() == R.id.dialogue1Text){
                                dialogue1Picture.setImageDrawable(getDrawable(R.drawable.indir));
                                dialogue1Text.setVisibility(View.INVISIBLE);
                                score++;
                                playDogru();
                            }else playYanlis();
                            break;
                        case "emotion6":
                            if (view.getId() == R.id.dialogue4Text){
                                dialogue1Picture.setImageDrawable(getDrawable(R.drawable.indir));
                                dialogue4Text.setVisibility(View.INVISIBLE);
                                score++;
                                playDogru();
                            }else playYanlis();
                            break;
                        case "emotion7":
                            if (view.getId() == R.id.dialogue4Text){
                                dialogue1Picture.setImageDrawable(getDrawable(R.drawable.indir));
                                dialogue4Text.setVisibility(View.INVISIBLE);
                                score++;
                                playDogru();
                            }else playYanlis();
                            break;
                        case "emotion8":
                            if (view.getId() == R.id.dialogue1Text){
                                dialogue1Picture.setImageDrawable(getDrawable(R.drawable.indir));
                                dialogue1Text.setVisibility(View.INVISIBLE);
                                score++;
                                playDogru();
                            }else playYanlis();
                            break;

                    }
                    isTestCompleted();
            }
            return true;
        }
    };

    View.OnDragListener dragListener2 = new View.OnDragListener() {
        @Override
        public boolean onDrag(View v, DragEvent event) {

            final int dragEvent = event.getAction();
            switch (dragEvent){
                case DragEvent.ACTION_DROP:
                    if (dialogue2Played == false){
                        Animation animation = AnimationUtils.loadAnimation(ListeningActivity.this,R.anim.blink_anim);
                        audio2Button.startAnimation(animation);
                        Toast.makeText(ListeningActivity.this,"You should listen the audio first", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                    final View view = (View) event.getLocalState();
                    switch (emotionName){
                        case "happiness":
                            if (view.getId() == R.id.dialogue1Text){
                                dialogue2Picture.setImageDrawable(getDrawable(R.drawable.indir));
                                dialogue1Text.setVisibility(View.INVISIBLE);
                                score++;
                                playDogru();
                            }else playYanlis();
                            break;
                        case "emotion1":
                            if (view.getId() == R.id.dialogue5Text){
                                dialogue2Picture.setImageDrawable(getDrawable(R.drawable.indir));
                                dialogue5Text.setVisibility(View.INVISIBLE);
                                score++;
                                playDogru();
                            }else playYanlis();
                            break;
                        case "emotion2":
                            if (view.getId() == R.id.dialogue3Text){
                                dialogue2Picture.setImageDrawable(getDrawable(R.drawable.indir));
                                dialogue3Text.setVisibility(View.INVISIBLE);
                                score++;
                                playDogru();
                            }else playYanlis();
                            break;
                        case "emotion3":
                            if (view.getId() == R.id.dialogue1Text){
                                dialogue2Picture.setImageDrawable(getDrawable(R.drawable.indir));
                                dialogue1Text.setVisibility(View.INVISIBLE);
                                score++;
                                playDogru();
                            }else playYanlis();
                            break;
                        case "emotion4":
                            if (view.getId() == R.id.dialogue3Text){
                                dialogue2Picture.setImageDrawable(getDrawable(R.drawable.indir));
                                dialogue3Text.setVisibility(View.INVISIBLE);
                                score++;
                                playDogru();
                            }else playYanlis();
                            break;
                        case "emotion5":
                            if (view.getId() == R.id.dialogue4Text){
                                dialogue2Picture.setImageDrawable(getDrawable(R.drawable.indir));
                                dialogue4Text.setVisibility(View.INVISIBLE);
                                score++;
                                playDogru();
                            }else playYanlis();
                            break;
                        case "emotion6":
                            if (view.getId() == R.id.dialogue2Text){
                                dialogue2Picture.setImageDrawable(getDrawable(R.drawable.indir));
                                dialogue2Text.setVisibility(View.INVISIBLE);
                                score++;
                                playDogru();
                            }else playYanlis();
                            break;
                        case "emotion7":
                            if (view.getId() == R.id.dialogue1Text){
                                dialogue2Picture.setImageDrawable(getDrawable(R.drawable.indir));
                                dialogue1Text.setVisibility(View.INVISIBLE);
                                score++;
                                playDogru();
                            }else playYanlis();
                            break;
                        case "emotion8":
                            if (view.getId() == R.id.dialogue3Text){
                                dialogue2Picture.setImageDrawable(getDrawable(R.drawable.indir));
                                dialogue3Text.setVisibility(View.INVISIBLE);
                                score++;
                                playDogru();
                            }else playYanlis();
                            break;
                    }isTestCompleted();
            }
            return true;
        }
    };
    View.OnDragListener dragListener3 = new View.OnDragListener() {
        @Override
        public boolean onDrag(View v, DragEvent event) {

            final int dragEvent = event.getAction();
            switch (dragEvent){
                case DragEvent.ACTION_DROP:
                    if (dialogue3Played == false){
                        Animation animation = AnimationUtils.loadAnimation(ListeningActivity.this,R.anim.blink_anim);
                        audio3Button.startAnimation(animation);
                        Toast.makeText(ListeningActivity.this,"You should listen the audio first", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                    final View view = (View) event.getLocalState();
                    switch (emotionName){
                        case "happiness":
                            if (view.getId() == R.id.dialogue5Text){
                                dialogue3Picture.setImageDrawable(getDrawable(R.drawable.indir));
                                dialogue5Text.setVisibility(View.INVISIBLE);
                                score++;
                                playDogru();
                            }else playYanlis();
                            break;
                        case "emotion1":
                            if (view.getId() == R.id.dialogue3Text){
                                dialogue3Picture.setImageDrawable(getDrawable(R.drawable.indir));
                                dialogue3Text.setVisibility(View.INVISIBLE);
                                score++;
                                playDogru();
                            }else playYanlis();
                            break;
                        case "emotion2":
                            if (view.getId() == R.id.dialogue5Text){
                                dialogue3Picture.setImageDrawable(getDrawable(R.drawable.indir));
                                dialogue5Text.setVisibility(View.INVISIBLE);
                                score++;
                                playDogru();
                            }else playYanlis();
                            break;
                        case "emotion3":
                            if (view.getId() == R.id.dialogue4Text){
                                dialogue3Picture.setImageDrawable(getDrawable(R.drawable.indir));
                                dialogue4Text.setVisibility(View.INVISIBLE);
                                score++;
                                playDogru();
                            }else playYanlis();
                            break;
                        case "emotion4":
                            if (view.getId() == R.id.dialogue1Text){
                                dialogue3Picture.setImageDrawable(getDrawable(R.drawable.indir));
                                dialogue1Text.setVisibility(View.INVISIBLE);
                                score++;
                                playDogru();
                            }else playYanlis();
                            break;
                        case "emotion5":
                            if (view.getId() == R.id.dialogue2Text){
                                dialogue3Picture.setImageDrawable(getDrawable(R.drawable.indir));
                                dialogue2Text.setVisibility(View.INVISIBLE);
                                score++;
                                playDogru();
                            }else playYanlis();
                            break;
                        case "emotion6":
                            if (view.getId() == R.id.dialogue5Text){
                                dialogue3Picture.setImageDrawable(getDrawable(R.drawable.indir));
                                dialogue5Text.setVisibility(View.INVISIBLE);
                                score++;
                                playDogru();
                            }else playYanlis();
                            break;
                        case "emotion7":
                            if (view.getId() == R.id.dialogue3Text){
                                dialogue3Picture.setImageDrawable(getDrawable(R.drawable.indir));
                                dialogue3Text.setVisibility(View.INVISIBLE);
                                score++;
                                playDogru();
                            }else playYanlis();
                            break;
                        case "emotion8":
                            if (view.getId() == R.id.dialogue4Text){
                                dialogue3Picture.setImageDrawable(getDrawable(R.drawable.indir));
                                dialogue4Text.setVisibility(View.INVISIBLE);
                                score++;
                                playDogru();
                            }else playYanlis();
                            break;
                    }isTestCompleted();
            }
            return true;
        }
    };
    View.OnDragListener dragListener4 = new View.OnDragListener() {
        @Override
        public boolean onDrag(View v, DragEvent event) {

            final int dragEvent = event.getAction();
            switch (dragEvent){
                case DragEvent.ACTION_DROP:
                    if (dialogue4Played == false){
                        Animation animation = AnimationUtils.loadAnimation(ListeningActivity.this,R.anim.blink_anim);
                        audio4Button.startAnimation(animation);
                        Toast.makeText(ListeningActivity.this,"You should listen the audio first", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                    final View view = (View) event.getLocalState();
                    switch (emotionName){
                        case "happiness":
                            if (view.getId() == R.id.dialogue3Text){
                                dialogue4Picture.setImageDrawable(getDrawable(R.drawable.indir));
                                dialogue3Text.setVisibility(View.INVISIBLE);
                                score++;
                                playDogru();
                            }else playYanlis();

                            break;
                        case "emotion1":
                            if (view.getId() == R.id.dialogue1Text){
                                dialogue4Picture.setImageDrawable(getDrawable(R.drawable.indir));
                                dialogue1Text.setVisibility(View.INVISIBLE);
                                score++;
                                playDogru();
                            }else playYanlis();
                            break;
                        case "emotion2":
                            if (view.getId() == R.id.dialogue1Text){
                                dialogue4Picture.setImageDrawable(getDrawable(R.drawable.indir));
                                dialogue1Text.setVisibility(View.INVISIBLE);
                                score++;
                                playDogru();
                            }else playYanlis();
                            break;
                        case "emotion3":
                            if (view.getId() == R.id.dialogue3Text){
                                dialogue4Picture.setImageDrawable(getDrawable(R.drawable.indir));
                                dialogue3Text.setVisibility(View.INVISIBLE);
                                score++;
                                playDogru();
                            }else playYanlis();
                            break;
                        case "emotion4":
                            if (view.getId() == R.id.dialogue2Text){
                                dialogue4Picture.setImageDrawable(getDrawable(R.drawable.indir));
                                dialogue2Text.setVisibility(View.INVISIBLE);
                                score++;
                                playDogru();
                            }else playYanlis();
                            break;
                        case "emotion5":
                            if (view.getId() == R.id.dialogue5Text){
                                dialogue4Picture.setImageDrawable(getDrawable(R.drawable.indir));
                                dialogue5Text.setVisibility(View.INVISIBLE);
                                score++;
                                playDogru();
                            }else playYanlis();
                            break;
                        case "emotion6":
                            if (view.getId() == R.id.dialogue3Text){
                                dialogue4Picture.setImageDrawable(getDrawable(R.drawable.indir));
                                dialogue3Text.setVisibility(View.INVISIBLE);
                                score++;
                                playDogru();
                            }else playYanlis();
                            break;
                        case "emotion7":
                            if (view.getId() == R.id.dialogue2Text){
                                dialogue4Picture.setImageDrawable(getDrawable(R.drawable.indir));
                                dialogue2Text.setVisibility(View.INVISIBLE);
                                score++;
                                playDogru();
                            }else playYanlis();
                            break;
                        case "emotion8":
                            if (view.getId() == R.id.dialogue1Text){
                                dialogue4Picture.setImageDrawable(getDrawable(R.drawable.indir));
                                dialogue1Text.setVisibility(View.INVISIBLE);
                                score++;
                                playDogru();
                            }else playYanlis();
                            break;
                    }isTestCompleted();
            }
            return true;
        }
    };
    View.OnDragListener dragListener5 = new View.OnDragListener() {
        @Override
        public boolean onDrag(View v, DragEvent event) {

            final int dragEvent = event.getAction();
            switch (dragEvent){
                case DragEvent.ACTION_DROP:
                    if (dialogue5Played == false){
                        Animation animation = AnimationUtils.loadAnimation(ListeningActivity.this,R.anim.blink_anim);
                        audio5Button.startAnimation(animation);
                        Toast.makeText(ListeningActivity.this,"You should listen the audio first", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                    final View view = (View) event.getLocalState();
                    switch (emotionName){
                        case "happiness":
                            if (view.getId() == R.id.dialogue2Text){
                                dialogue5Picture.setImageDrawable(getDrawable(R.drawable.indir));
                                dialogue2Text.setVisibility(View.INVISIBLE);
                                score++;
                                playDogru();
                            }else playYanlis();
                            break;
                        case "emotion1":
                            if (view.getId() == R.id.dialogue4Text){
                                dialogue5Picture.setImageDrawable(getDrawable(R.drawable.indir));
                                dialogue4Text.setVisibility(View.INVISIBLE);
                                score++;
                                playDogru();
                            }else playYanlis();
                            break;
                        case "emotion2":
                            if (view.getId() == R.id.dialogue4Text){
                                dialogue5Picture.setImageDrawable(getDrawable(R.drawable.indir));
                                dialogue4Text.setVisibility(View.INVISIBLE);
                                score++;
                                playDogru();
                            }else playYanlis();
                            break;
                        case "emotion3":
                            if (view.getId() == R.id.dialogue2Text){
                                dialogue5Picture.setImageDrawable(getDrawable(R.drawable.indir));
                                dialogue2Text.setVisibility(View.INVISIBLE);
                                score++;
                                playDogru();
                            }else playYanlis();
                            break;
                        case "emotion4":
                            if (view.getId() == R.id.dialogue4Text){
                                dialogue5Picture.setImageDrawable(getDrawable(R.drawable.indir));
                                dialogue4Text.setVisibility(View.INVISIBLE);
                                score++;
                                playDogru();
                            }else playYanlis();
                            break;
                        case "emotion5":
                            if (view.getId() == R.id.dialogue3Text){
                                dialogue5Picture.setImageDrawable(getDrawable(R.drawable.indir));
                                dialogue3Text.setVisibility(View.INVISIBLE);
                                score++;
                                playDogru();
                            }else playYanlis();
                            break;
                        case "emotion6":
                            if (view.getId() == R.id.dialogue1Text){
                                dialogue5Picture.setImageDrawable(getDrawable(R.drawable.indir));
                                dialogue1Text.setVisibility(View.INVISIBLE);
                                score++;
                                playDogru();
                            }else playYanlis();
                            break;
                        case "emotion7":
                            if (view.getId() == R.id.dialogue5Text){
                                dialogue5Picture.setImageDrawable(getDrawable(R.drawable.indir));
                                dialogue5Text.setVisibility(View.INVISIBLE);
                                score++;
                                playDogru();
                            }else playYanlis();
                            break;
                        case "emotion8":
                            if (view.getId() == R.id.dialogue4Text){
                                dialogue5Picture.setImageDrawable(getDrawable(R.drawable.indir));
                                dialogue4Text.setVisibility(View.INVISIBLE);
                                score++;
                                playDogru();
                            }else playYanlis();
                            break;
                    }isTestCompleted();
            }
            return true;
        }
    };

}