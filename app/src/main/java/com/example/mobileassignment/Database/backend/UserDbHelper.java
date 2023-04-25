package com.example.mobileassignment.Database.backend;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;

import com.example.mobileassignment.Database.User;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class UserDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "users.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "users";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_LIST = "fave";

    public UserDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_NAME + " TEXT," +
                COLUMN_USERNAME + " TEXT," +
                COLUMN_PASSWORD + " TEXT," +
                COLUMN_LIST + " BLOB);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Serialize the ArrayList as a BLOB data
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out = null;
        byte[] faveBytes = null;

        try {
            out = new ObjectOutputStream(bos);
            out.writeObject(user.getMarathon());
            faveBytes = bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, user.getFullName());
        values.put(COLUMN_USERNAME, user.getUsername());
        values.put(COLUMN_PASSWORD, user.getPassword());

        String marathonString = TextUtils.join(",", user.getMarathon());
        values.put(COLUMN_LIST, marathonString);
        long id = db.insert(TABLE_NAME, null, values);
        db.close();
        return id;
    }

    public User getUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                COLUMN_NAME,
                COLUMN_USERNAME,
                COLUMN_PASSWORD,
                COLUMN_LIST
        };

        String selection = COLUMN_USERNAME + " = ? AND " + COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = {username, password};

        Cursor cursor = db.query(
                TABLE_NAME,       // The table to query
                projection,       // The array of columns to return (pass null to get all)
                selection,        // The columns for the WHERE clause
                selectionArgs,    // The values for the WHERE clause
                null,             // don't group the rows
                null,             // don't filter by row groups
                null              // don't sort the order
        );

        User user = null;

        if (cursor.moveToFirst()) {
            String fullName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME));
            String retrievedUsername = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USERNAME));
            String retrievedPassword = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PASSWORD));
            String marathonString = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LIST));

            ArrayList<Integer> marathon = new ArrayList<>();
            if (!marathonString.isEmpty()) {
                String[] marathonArray = marathonString.split(",");
                for (String time : marathonArray) {
                    marathon.add(Integer.parseInt(time));
                }
            }

            user = new User(fullName, retrievedUsername, retrievedPassword, marathon);
        }

        cursor.close();
        db.close();

        return user;
    }



    public void updateUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();

        // Serialize the ArrayList as a BLOB data
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out = null;
        byte[] faveBytes = null;

        try {
            out = new ObjectOutputStream(bos);
            out.writeObject(user.getMarathon());
            faveBytes = bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, user.getFullName());
        values.put(COLUMN_USERNAME, user.getUsername());
        values.put(COLUMN_PASSWORD, user.getPassword());

        String marathonString = TextUtils.join(",", user.getMarathon());
        values.put(COLUMN_LIST, marathonString);

        String selection = COLUMN_USERNAME + " = ? AND " + COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = {user.getUsername(), user.getPassword()};

        db.update(TABLE_NAME, values, selection, selectionArgs);

        db.close();
    }



    public static String getTableName() {
        return TABLE_NAME;
    }

    public static String getColumnName() {
        return COLUMN_NAME;
    }

    public static String getColumnUsername() {
        return COLUMN_USERNAME;
    }

    public static String getColumnPassword() {
        return COLUMN_PASSWORD;
    }

    public static String getColumnList() {
        return COLUMN_LIST;
    }
}
