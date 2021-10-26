package com.emotions.emotionsdeneme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class BonusActivity extends AppCompatActivity {
    ImageButton nextButton;
    TextView questionText,stageText,insText_b;
    ImageView picture1,picture2,picture3,pictureResult;
    int stage = 1;
    MediaPlayer dogruAudio,yanlisAudio;
    int green = Color.parseColor("#5ECF0E");
    int red = Color.parseColor("#EF004C");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bonus);
        questionText = findViewById(R.id.questionText);
        picture1 = findViewById(R.id.picture1);
        picture2 = findViewById(R.id.picture2);
        picture3 = findViewById(R.id.picture3);
        pictureResult = findViewById(R.id.pictureResult);
        stageText = findViewById(R.id.stageText);
        nextButton = findViewById(R.id.nextButton);
        insText_b= findViewById(R.id.ins_text_b);

        Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/PatrickHand-Regular.ttf");
        questionText.setTypeface(tf);
        stageText.setTypeface(tf);
        insText_b.setTypeface(tf);


        dogruAudio = MediaPlayer.create(this,R.raw.dogru_audio);
        yanlisAudio = MediaPlayer.create(this,R.raw.yanlis_audio);

        stageText.setText("1/8");

        questionText.setText("It’s my birthday today! I am very happy!");
        picture1.setImageDrawable(getDrawable(R.drawable.sinirli));
        picture2.setImageDrawable(getDrawable(R.drawable.mutluu));
        picture3.setImageDrawable(getDrawable(R.drawable.tiksinti));



    }
    // yaparız
    public void goToNext(View view){
        stage++;
        if (stage > 8){
            Intent intent = new Intent(BonusActivity.this,MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish();

        }else{
            pictureResult.setImageDrawable(getDrawable(R.drawable.kafa_bos1));
            stageText.setText(stage+"/8");
            nextButton.setBackgroundColor(red);

            switch (stage){
                case 2:
                    questionText.setText("My dog is lost. I am sad.");
                    picture1.setImageDrawable(getDrawable(R.drawable.mutluu));
                    picture2.setImageDrawable(getDrawable(R.drawable.saskin));
                    picture3.setImageDrawable(getDrawable(R.drawable.uzgun));
                    break;
                case 3:
                    questionText.setText("My room is dark. I’m scared.");
                    picture1.setImageDrawable(getDrawable(R.drawable.korkuu));
                    picture2.setImageDrawable(getDrawable(R.drawable.tiksinti));
                    picture3.setImageDrawable(getDrawable(R.drawable.mutluu));
                    break;
                case 4:
                    questionText.setText("There’s a fly in the soup. It’s disgusting!");
                    picture1.setImageDrawable(getDrawable(R.drawable.utanma));
                    picture2.setImageDrawable(getDrawable(R.drawable.saskin));
                    picture3.setImageDrawable(getDrawable(R.drawable.tiksinti));
                    break;
                case 5:
                    questionText.setText("My friends don’t play with me. I’m angry!");
                    picture1.setImageDrawable(getDrawable(R.drawable.saskin));
                    picture2.setImageDrawable(getDrawable(R.drawable.sinirli));
                    picture3.setImageDrawable(getDrawable(R.drawable.korkuu));
                    break;
                case 6:
                    questionText.setText("My friend always breaks my toy. I hate him!");
                    picture1.setImageDrawable(getDrawable(R.drawable.nefret));
                    picture2.setImageDrawable(getDrawable(R.drawable.tiksinti));
                    picture3.setImageDrawable(getDrawable(R.drawable.mutluu));
                    break;
                case 7:
                    questionText.setText("I’m ashamed to sing a song.");
                    picture1.setImageDrawable(getDrawable(R.drawable.saskin));
                    picture2.setImageDrawable(getDrawable(R.drawable.utanma));
                    picture3.setImageDrawable(getDrawable(R.drawable.sinirli));
                    break;
                case 8:
                    questionText.setText("Wow! It’s a Spiderman T-shirt!");
                    picture1.setImageDrawable(getDrawable(R.drawable.saskin));
                    picture2.setImageDrawable(getDrawable(R.drawable.nefret));
                    picture3.setImageDrawable(getDrawable(R.drawable.uzgun));
                    break;

            }
        }
    }


    public void picture1Selected(View view){


        switch (stage){
            case 1:
                playYanlis();
                break;
            case 2:
                playYanlis();
                break;
            case 3:
                playDogru();
                pictureResult.setImageDrawable(getDrawable(R.drawable.korku2_2));
                nextButton.setBackgroundColor(green);
                break;
            case 4:
                playYanlis();
                break;
            case 5:
                playYanlis();
                break;
            case 6:
                playDogru();
                pictureResult.setImageDrawable(getDrawable(R.drawable.nefret1));
                nextButton.setBackgroundColor(green);
                break;
            case 7:
                playYanlis();
                break;
            case 8:
                playDogru();
                pictureResult.setImageDrawable(getDrawable(R.drawable.saskin1));
                nextButton.setBackgroundColor(green);
                break;

        }
    }
    public void picture2Selected(View view){
        switch (stage){
            case 1:
                playDogru();
                pictureResult.setImageDrawable(getDrawable(R.drawable.mutlu1));
                nextButton.setBackgroundColor(green);
                break;
            case 2:
                playYanlis();
                break;
            case 3:
                playYanlis();
                break;
            case 4:
                playYanlis();
                break;
            case 5:
                playDogru();
                pictureResult.setImageDrawable(getDrawable(R.drawable.sinirli1));
                nextButton.setBackgroundColor(green);
                break;
            case 6:
                playYanlis();
                break;
            case 7:
                playDogru();
                pictureResult.setImageDrawable(getDrawable(R.drawable.utanma1));
                nextButton.setBackgroundColor(green);
                break;
            case 8:
                playYanlis();
                break;
        }
    }
    public void picture3Selected(View view){
        switch (stage){
            case 1:

                break;
            case 2:
                playDogru();
                pictureResult.setImageDrawable(getDrawable(R.drawable.uzgun1));
                nextButton.setBackgroundColor(green);
                break;
            case 3:
                playYanlis();
                break;
            case 4:
                playDogru();
                pictureResult.setImageDrawable(getDrawable(R.drawable.tiksinti1));
                nextButton.setBackgroundColor(green);
                break;
            case 5:
                playYanlis();
                break;
            case 6:
                playYanlis();
                break;
            case 7:
                playYanlis();
                break;
            case 8:
                playYanlis();
                break;
        }
    }

    public void playDogru(){
        dogruAudio.start();
    }

    public void playYanlis(){
        yanlisAudio.start();
    }
}