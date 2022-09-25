package edu.hackrice.lifehackstartercode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

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
        Calendar yesterday = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date();

        double weekTotal = 0;
        int totalEntries = 0;
        Cursor data = dbHelper.getData();
        int streak = 0;
        boolean continueStreak = true;
        String yestStr;
        String todayStr = formatter.format(today);

        while(data.moveToNext()){
            if(continueStreak){
                try {
                    yesterday.setTime(formatter.parse(data.getString(1)));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                yesterday.add(Calendar.DAY_OF_MONTH, -1);
                yestStr = formatter.format(yesterday.getTime());
                if(!data.getString(1).equals(todayStr)){
                    streak = 0;
                    continueStreak = false;
                }
                else if(data.getString(1).equals(yestStr)){
                    streak++;
                }
            }
            long daysBetween = 8;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                LocalDate to = LocalDate.parse(todayStr);
                LocalDate from = LocalDate.parse(data.getString(1));
                daysBetween = ChronoUnit.DAYS.between(from, to);
            }
            if(daysBetween<=7){
                weekTotal += data.getFloat(2);
            }
            totalEntries++;

        }
        weekAverageText.setText(String.valueOf(Math.round(weekTotal/totalEntries)));
        totalEntriesText.setText(String.valueOf(totalEntries));
        streakText.setText(String.valueOf(streak));

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
                finish();
                startActivity(getIntent());
            }
        });
    }
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}
