package com.example.delta;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.search.SearchBar;
import java.util.ArrayList;
import java.util.Locale;

public class VoiceInput {

    private static final int Permit_for_audio = 1;
    private SpeechRecognizer speechRecognizer;
    private Intent speechRecognizerIntent;
    private MaterialButton speakButton;
    private SearchBar search;
    private Context context; // Context of the calling Activity

    // Constructor
    public VoiceInput(Context context, MaterialButton speakButton, SearchBar search) {
        this.context = context;
        this.speakButton = speakButton;
        this.search = search;
        initializeSpeechRecognizer();
    }

    private void initializeSpeechRecognizer() {



        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(context);
        speechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle params) {
                Toast.makeText(context, "Ready for speech", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onBeginningOfSpeech() {}

            @Override
            public void onRmsChanged(float rmsdB) {}

            @Override
            public void onBufferReceived(byte[] buffer) {}

            @Override
            public void onEndOfSpeech() {}

            @Override
            public void onError(int error) {
                Toast.makeText(context, "Error: " + error, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResults(Bundle results) {
                ArrayList<String> data = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                if (data != null && !data.isEmpty()) {
                    search.setText(data.get(0));
                }
            }

            @Override
            public void onPartialResults(Bundle partialResults) {}

            @Override
            public void onEvent(int eventType, Bundle params) {}
        });

        speakButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                Log.i("clicked", "I am clicked");

                if (ContextCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.RECORD_AUDIO}, Permit_for_audio);
                } else {
                    speechRecognizer.startListening(speechRecognizerIntent);
                }
            }
        });
    }

    public void onRequestPermissionsResult(int rqC, String[] permits, int[] gRes) {
        if (rqC == Permit_for_audio) {
            if (gRes.length > 0 && gRes[0] == PackageManager.PERMISSION_GRANTED) {
                speechRecognizer.startListening(speechRecognizerIntent);
            } else {
                Toast.makeText(context, "Permission was not granted", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void destroy() {
        if (speechRecognizer != null) {
            speechRecognizer.destroy();
        }
    }
}
