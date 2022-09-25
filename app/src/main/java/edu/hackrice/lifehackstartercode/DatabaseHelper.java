package edu.hackrice.lifehackstartercode;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    private static final String TABLE_NAME = "journal_table";
    private static final String COL1 = "ID";
    private static final String COL2 = "DATE";
    private static final String COL3 = "RATING";
    private static final String COL4 = "ENTRY";

    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COL2 +" TEXT,"+COL3+" REAL,"+COL4+" TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String item1, double item2, String item3) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, item1);
        contentValues.put(COL3, item2);
        contentValues.put(COL4, item3);

        Log.d(TAG, "addData: Adding " + item1 + " to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Returns all the data from database
     * @return
     */
    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        System.out.println(data);
        return data;
    }

    /**
     * Returns only the ID that matches the name passed in
     * @param name
     * @return
     */
    public Cursor getItemID(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL1 + " FROM " + TABLE_NAME +
                " WHERE " + COL2 + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    /**
     * Updates the date field
     * @param newDate
     * @param id
     * @param oldDate
     */
    public void updateDate(String newDate, int id, String oldDate){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE"+TABLE_NAME+" SET "+ COL2+" = "+newDate+"' WHERE "+COL1+" = '"+id+"'"+" AND "+COL2 + " = '"+oldDate +"'";
        Log.d(TAG, "updateDate: query: "+query);
        Log.d(TAG,"updateDate: Setting date to "+newDate);
        db.execSQL(query);
    }
    /**
     * Updates the rating field
     * @param newRating
     * @param id
     * @param oldRating
     */
    public void updateRating(double newRating, int id, double oldRating){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE"+TABLE_NAME+" SET "+ COL3+" = "+String.valueOf(newRating)+"' WHERE "+COL1+" = '"+id+"'"+" AND "+COL3 + " = '"+String.valueOf(oldRating) +"'";
        Log.d(TAG, "updateRating: query: "+query);
        Log.d(TAG,"updateRating: Setting date to "+String.valueOf(newRating));
        db.execSQL(query);
    }
    /**
     * Updates the entry field
     * @param newEntry
     * @param id
     * @param oldEntry
     */
    public void updateEntry(String newEntry, int id, String oldEntry){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE"+TABLE_NAME+" SET "+ COL4+" = "+newEntry+"' WHERE "+COL1+" = '"+id+"'"+" AND "+COL4 + " = '"+oldEntry +"'";
        Log.d(TAG, "updateEntry: query: "+query);
        Log.d(TAG,"updateEntry: Setting date to "+newEntry);
        db.execSQL(query);
    }


    /**
     * Delete from database
     * @param id
     * @param date
     */
    public void deleteDate(int id, String date){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + COL1 + " = '" + id + "'" +
                " AND " + COL2 + " = '" + date + "'";
        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting " + date + " from database.");
        db.execSQL(query);
    }

    public void deleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        String clearDBQuery = "DELETE FROM "+TABLE_NAME;
        db.execSQL(clearDBQuery);
    }



}
