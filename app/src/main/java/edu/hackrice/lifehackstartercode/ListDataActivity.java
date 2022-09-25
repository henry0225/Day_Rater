package edu.hackrice.lifehackstartercode;


import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.ListAdapter;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.database.Cursor;
import android.widget.Toast;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class ListDataActivity extends AppCompatActivity {

    private static final String TAG = "ListDataActivity";

    DatabaseHelper mDatabaseHelper;

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);
        mListView = findViewById(R.id.listView);
        mDatabaseHelper = new DatabaseHelper(this);

        populateListView();

    }
    private void populateListView() {
        //Log.d(TAG, "populateListView: Displaying data in the ListView.");

        //get the data and append to a list
        Cursor data = mDatabaseHelper.getData();
        ArrayList<String> listData = new ArrayList<>();
        List<HashMap<String, String>> listItems = new ArrayList<>();
        while(data.moveToNext()){
            //get the value from the database in column 1
            //then add it to the ArrayList
            int score = (int)data.getFloat(2);

            listData.add(data.getString(1)+" " + score +" " + data.getString(3));
            HashMap<String, String> resultsMap = new HashMap<>();
            String dateAndScore = data.getString(1) + "       " + score + "/100";
            resultsMap.put("First Line", dateAndScore);
            resultsMap.put("Second Line", data.getString(3));
            listItems.add(resultsMap);
        }
        Collections.reverse(listData);
        //create the list adapter and set the adapter
        SimpleAdapter adapter = new SimpleAdapter(this, listItems, R.layout.mytextview,new String[]{"First Line", "Second Line"},
                new int[]{R.id.text1, R.id.text2});
        mListView.setAdapter(adapter);

        //set an onItemClickListener to the ListView
    }
}
