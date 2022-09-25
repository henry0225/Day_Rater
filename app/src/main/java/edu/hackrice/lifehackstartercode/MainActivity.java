package edu.hackrice.lifehackstartercode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button buttonGoToMainActivity;
    Button btnView;
    Button buttonDelete;
    TextView textViewGreeting;
    TextView streakText;
    TextView weekAverageText;
    TextView totalEntriesText;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        streakText = findViewById(R.id.streakText);
        weekAverageText = findViewById(R.id.weekAverageText);
        totalEntriesText = findViewById(R.id.totalEntriesText);
        dbHelper = new DatabaseHelper(this);

        double total = 0;
        int totalEntries = 0;
        Cursor data = dbHelper.getData();
        while(data.moveToNext()){
            totalEntries++;
            total += data.getFloat(2);
        }
        weekAverageText.setText(String.valueOf(total));
        totalEntriesText.setText(String.valueOf(totalEntries));

        buttonGoToMainActivity = findViewById(R.id.buttonGoToMainActivity);
        View.OnClickListener buttonGoToWelcomeClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, EditDataActivity.class);
                i.putExtra("message", "Write about your day!");
                startActivity(i);
            }
        };
        buttonGoToMainActivity.setOnClickListener(buttonGoToWelcomeClickListener);

        btnView = findViewById(R.id.btnView);
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListDataActivity.class);
                startActivity(intent);
            }
        });

        buttonDelete = findViewById(R.id.buttonDelete);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.deleteAll();
                toastMessage("History successfully deleted!");
            }
        });
    }
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}
