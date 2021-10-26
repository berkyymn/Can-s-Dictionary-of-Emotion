package com.emotions.emotionsdeneme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    ImageView happinessPicture, emotion1Picture,emotion2Picture,emotion3Picture,emotion4Picture,
            emotion5Picture,emotion6Picture,emotion7Picture,emotion8Picture;
    TextView emotionName1,emotionName2,emotionName3,emotionName4,emotionName5,emotionName6,
            emotionName7,emotionName8,emotionName9;

    MediaPlayer girisSes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = this.getSharedPreferences("com.example.emotionsdeneme", Context.MODE_PRIVATE);
        happinessPicture = findViewById(R.id.happinessPicture);
        emotion1Picture = findViewById(R.id.emotion1Picture);
        emotion2Picture = findViewById(R.id.emotion2Picture);
        emotion3Picture = findViewById(R.id.emotion3Picture);
        emotion4Picture = findViewById(R.id.emotion4Picture);
        emotion5Picture = findViewById(R.id.emotion5Picture);
        emotion6Picture = findViewById(R.id.emotion6Picture);
        emotion7Picture = findViewById(R.id.emotion7Picture);
        emotion8Picture = findViewById(R.id.emotion8Picture);
        emotionName1 = findViewById(R.id.emotionName1);
        emotionName2 = findViewById(R.id.emotionName2);
        emotionName3 = findViewById(R.id.emotionName3);
        emotionName4 = findViewById(R.id.emotionName4);
        emotionName5 = findViewById(R.id.emotionName5);
        emotionName6 = findViewById(R.id.emotionName6);
        emotionName7 = findViewById(R.id.emotionName7);
        emotionName8 = findViewById(R.id.emotionName8);
        emotionName9 = findViewById(R.id.emotionName9);

        girisSes = MediaPlayer.create(this,R.raw.giris);



        Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/PatrickHand-Regular.ttf");
        emotionName1.setTypeface(tf);
        emotionName2.setTypeface(tf);
        emotionName3.setTypeface(tf);
        emotionName4.setTypeface(tf);
        emotionName5.setTypeface(tf);
        emotionName6.setTypeface(tf);
        emotionName7.setTypeface(tf);
        emotionName8.setTypeface(tf);
        emotionName9.setTypeface(tf);

        happinessPicture.setImageDrawable(getDrawable(R.drawable.happy));

        unlockPictures();

    }

    public void unlockPictures(){
        if (sharedPreferences.getBoolean("happinessCompleted",false)){
            emotion1Picture.setImageDrawable(getDrawable(R.drawable.sad));
        }
        if (sharedPreferences.getBoolean("emotion1Completed",false)){
            emotion2Picture.setImageDrawable(getDrawable(R.drawable.fear));
        }
        if (sharedPreferences.getBoolean("emotion2Completed",false)){
            emotion2Picture.setImageDrawable(getDrawable(R.drawable.disgust));
        }
        if (sharedPreferences.getBoolean("emotion3Completed",false)){
            emotion4Picture.setImageDrawable(getDrawable(R.drawable.anger));
        }
        if (sharedPreferences.getBoolean("emotion4Completed",false)){
            emotion5Picture.setImageDrawable(getDrawable(R.drawable.hate));
        }
        if (sharedPreferences.getBoolean("emotion5Completed",false)){
            emotion6Picture.setImageDrawable(getDrawable(R.drawable.shy));
        }
        if (sharedPreferences.getBoolean("emotion6Completed",false)){
            emotion7Picture.setImageDrawable(getDrawable(R.drawable.wonder));
        }
        if (sharedPreferences.getBoolean("emotion7Completed",false)){
            emotion8Picture.setImageDrawable(getDrawable(R.drawable.yildiz));
        }
        emotion1Picture.setImageDrawable(getDrawable(R.drawable.sad));
        emotion2Picture.setImageDrawable(getDrawable(R.drawable.fear));
        emotion3Picture.setImageDrawable(getDrawable(R.drawable.disgust));
        emotion4Picture.setImageDrawable(getDrawable(R.drawable.anger));
        emotion5Picture.setImageDrawable(getDrawable(R.drawable.hate));
        emotion6Picture.setImageDrawable(getDrawable(R.drawable.shy));
        emotion7Picture.setImageDrawable(getDrawable(R.drawable.wonder));
        emotion8Picture.setImageDrawable(getDrawable(R.drawable.yildiz));

    }

    public boolean isPreviousSectionComplete(String selectedSection) {
        boolean result = false;
        switch (selectedSection) {

            case "emotion1":
                result = sharedPreferences.getBoolean("happinessCompleted", false);
                break;
            case "emotion2":
                result = sharedPreferences.getBoolean("emotion1Completed", false);
                break;
            case "emotion3":
                result = sharedPreferences.getBoolean("emotion2Completed", false);
                break;
            case "emotion4":
                result = sharedPreferences.getBoolean("emotion3Completed", false);
                break;
            case "emotion5":
                result = sharedPreferences.getBoolean("emotion4Completed", false);
                break;
            case "emotion6":
                result = sharedPreferences.getBoolean("emotion5Completed", false);
                break;
            case "emotion7":
                result = sharedPreferences.getBoolean("emotion6Completed", false);
                break;
            case "emotion8":
                result = sharedPreferences.getBoolean("emotion7Completed", false);
                break;
        }

        return result;
    }

    public void unlockImages(){

    }

    public void happinessSelected(View view) {

        Intent intent = new Intent(MainActivity.this, DictionaryActivity_2.class);
        intent.putExtra("emotionNameIntent", "happiness");
        startActivity(intent);
    }

    public void emotion1Selected(View view) {
        if (isPreviousSectionComplete("emotion1")) {
            Intent intent = new Intent(MainActivity.this, DictionaryActivity_2.class);
            intent.putExtra("emotionNameIntent", "emotion1");
            startActivity(intent);
        } else {
            //Toast.makeText(MainActivity.this, "You should complete Happiness first", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this, DictionaryActivity_2.class);
            intent.putExtra("emotionNameIntent", "emotion1");
            startActivity(intent);
        }

    }

    public void emotion2Selected(View view) {
        if (isPreviousSectionComplete("emotion2")) {
            Intent intent = new Intent(MainActivity.this, DictionaryActivity_2.class);
            intent.putExtra("emotionNameIntent", "emotion2");
            startActivity(intent);
            girisSes.start();
        } else {
            //Toast.makeText(MainActivity.this, "You should complete Sadness first", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this, DictionaryActivity_2.class);
            intent.putExtra("emotionNameIntent", "emotion2");
            startActivity(intent);
            girisSes.start();
        }

    }

    public void emotion3Selected(View view) {
        if (isPreviousSectionComplete("emotion3")) {
            Intent intent = new Intent(MainActivity.this, DictionaryActivity_2.class);
            intent.putExtra("emotionNameIntent", "emotion3");
            startActivity(intent);
            girisSes.start();
        } else {
            //Toast.makeText(MainActivity.this, "You should complete Fear first", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this, DictionaryActivity_2.class);
            intent.putExtra("emotionNameIntent", "emotion3");
            startActivity(intent);
            girisSes.start();
        }

    }

    public void emotion4Selected(View view) {
        if (isPreviousSectionComplete("emotion4")) {
            Intent intent = new Intent(MainActivity.this, DictionaryActivity_2.class);
            intent.putExtra("emotionNameIntent", "emotion4");
            startActivity(intent);
            girisSes.start();
        } else {
            //Toast.makeText(MainActivity.this, "You should complete Disgust first", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this, DictionaryActivity_2.class);
            intent.putExtra("emotionNameIntent", "emotion4");
            startActivity(intent);
            girisSes.start();
        }
    }

    public void emotion5Selected(View view) {
        if (isPreviousSectionComplete("emotion5")) {
            Intent intent = new Intent(MainActivity.this, DictionaryActivity_2.class);
            intent.putExtra("emotionNameIntent", "emotion5");
            startActivity(intent);
            girisSes.start();
        } else {
            //Toast.makeText(MainActivity.this, "You should complete Anger first", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this, DictionaryActivity_2.class);
            intent.putExtra("emotionNameIntent", "emotion5");
            startActivity(intent);
            girisSes.start();
        }

    }

    public void emotion6Selected(View view) {
        if (isPreviousSectionComplete("emotion6")) {
            Intent intent = new Intent(MainActivity.this, DictionaryActivity_2.class);
            intent.putExtra("emotionNameIntent", "emotion6");
            startActivity(intent);
            girisSes.start();
        } else {
            //Toast.makeText(MainActivity.this, "You should complete Hate first", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this, DictionaryActivity_2.class);
            intent.putExtra("emotionNameIntent", "emotion6");
            startActivity(intent);
            girisSes.start();
        }

    }

    public void emotion7Selected(View view) {
        if (isPreviousSectionComplete("emotion7")) {
            Intent intent = new Intent(MainActivity.this, DictionaryActivity_2.class);
            intent.putExtra("emotionNameIntent", "emotion7");
            startActivity(intent);
            girisSes.start();
        } else {
            //Toast.makeText(MainActivity.this, "You should complete Shame first", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this, DictionaryActivity_2.class);
            intent.putExtra("emotionNameIntent", "emotion7");
            startActivity(intent);
            girisSes.start();
        }

    }

    public void emotion8Selected(View view) {

        if (isPreviousSectionComplete("emotion7")) {
            Intent intent = new Intent(MainActivity.this,BonusActivity.class);
            startActivity(intent);
            girisSes.start();
        } else {
            Intent intent = new Intent(MainActivity.this,BonusActivity.class);
            startActivity(intent);
            girisSes.start();
        }
    }
}