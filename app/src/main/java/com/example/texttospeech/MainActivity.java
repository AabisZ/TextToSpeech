package com.example.texttospeech;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import java.util.Locale;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener{

    private TextToSpeech tts;
    private Button buttonSpeak;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tts=new TextToSpeech(this,this);
        buttonSpeak=(Button) findViewById(R.id.button1);
        editText=(EditText) findViewById(R.id.editText1);

        buttonSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOut();

            }
        });

    }

    public void onDestroy(){
        if(tts!=null){
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
    public void onInit(int status){
        if(status==TextToSpeech.SUCCESS){
            int result=tts.setLanguage(Locale.US);
            if (result==TextToSpeech.LANG_MISSING_DATA || result==TextToSpeech.LANG_NOT_SUPPORTED){
                Log.e("TTS ","Lang is not supported");

            }
            else{
                buttonSpeak.setEnabled(true);
                speakOut();

            }
        }
        else{
            Log.e("TTS","Init Failed");

        }
    }
    public void speakOut(){
        String text=editText.getText().toString();
        tts.speak(text,TextToSpeech.QUEUE_FLUSH,null);

    }
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.act, menu);
//        return true;
//    }
}