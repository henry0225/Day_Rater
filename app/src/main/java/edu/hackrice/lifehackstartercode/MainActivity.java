package edu.hackrice.lifehackstartercode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button buttonGoToMain;
    TextView textViewDayRater;
    TextView textViewWelcomeBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonGoToMain = findViewById(R.id.buttonGoToMainActivity);
        textViewDayRater = findViewById(R.id.textDayRater);
        textViewWelcomeBack = findViewById(R.id.textWelcomeBack);

        View.OnClickListener buttonGoToMainClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, WelcomeActivity.class);
                i.putExtra("message", "Welcome to HackRice 12 First Timers Track!");
                startActivity(i);
            }
        };
        buttonGoToMain.setOnClickListener(buttonGoToMainClickListener);



    }
}