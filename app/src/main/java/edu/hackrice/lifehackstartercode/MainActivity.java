package edu.hackrice.lifehackstartercode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
/*
    Button buttonChangeGreeting;
    Button buttonGoToWelcome;
    TextView textViewGreeting;
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
        buttonChangeGreeting = findViewById(R.id.buttonChangeGreeting);
        buttonGoToWelcome = findViewById(R.id.buttonGoToWelcome);
        textViewGreeting = findViewById(R.id.textHello);

        View.OnClickListener buttonChangeGreetingClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViewGreeting.setText("Home Page");
            }
        };
        buttonChangeGreeting.setOnClickListener(buttonChangeGreetingClickListener);

        View.OnClickListener buttonGoToWelcomeClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, EditDataActivity.class);
                i.putExtra("message", "Rate your day!");
                startActivity(i);
            }
        };
        buttonGoToWelcome.setOnClickListener(buttonGoToWelcomeClickListener);

 */
    }
}
