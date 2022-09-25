package edu.hackrice.lifehackstartercode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.time.LocalDate;
public class EditDataActivity extends AppCompatActivity {

    TextView textViewWelcome;
    DatabaseHelper mDatabaseHelper;
    Button btnAdd, btnViewData;
    EditText editText;
    SeekBar ratingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);

        textViewWelcome = findViewById(R.id.textViewWelcome);

        Intent i = getIntent();
        String message = i.getStringExtra("message");
        textViewWelcome.setText(message);
        editText = findViewById(R.id.editText);
        ratingBar = findViewById(R.id.ratingBar);
        btnAdd = findViewById(R.id.btnAdd);
        btnViewData = findViewById(R.id.btnView);
        mDatabaseHelper = new DatabaseHelper(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String entry = editText.getText().toString();
                double rating = ratingBar.getProgress();
                String date = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    date = String.valueOf(LocalDate.now());
                }
                if (editText.length() != 0) {
                    AddData(date, rating, entry);
                    editText.setText("");
                    ratingBar.setProgress(0);
                } else {
                    toastMessage("You must put something in the text field!");
                }
                Intent intent = new Intent(EditDataActivity.this, ListDataActivity.class);
                startActivity(intent);
            }
        });



    }

    public void AddData(String col2, double col3, String col4) {
        boolean insertData = mDatabaseHelper.addData(col2, col3, col4);

        if (insertData) {
            toastMessage("Data Successfully Inserted!");
        } else {
            toastMessage("Something went wrong");
        }
    }

    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

}
