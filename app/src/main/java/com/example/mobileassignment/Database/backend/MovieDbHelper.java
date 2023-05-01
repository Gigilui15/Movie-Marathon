package com.example.mobileassignment.Database.backend;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.mobileassignment.API.MovieResults;

public class MovieDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "movies.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "movies";
    private static final String TMDB_ID = "id";
    private static final String MOVIE_TITLE = "title";
    private static final String MOVE_OVERVIEW = "overview";
    private static final String BACKDROP_PATH = "backdrop_path";
    private static final String ORIGINAL_LANGUAGE = "original_language";
    private static final String POSTER_PATH = "poster_path";
    private static final String MOVIE_POPULARITY = "popularity";
    private static final String RELEASE_DATE = "release_date";
    private static final String VOTE_AVERAGE = "vote_average";

    public MovieDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + TMDB_ID + " INTEGER PRIMARY KEY, " +
                MOVIE_TITLE + " REAL, " +
                MOVE_OVERVIEW + " TEXT, " +
                BACKDROP_PATH + " TEXT, " +
                ORIGINAL_LANGUAGE + " TEXT, " +
                POSTER_PATH + " TEXT, " +
                MOVIE_POPULARITY + " REAL, " +
                RELEASE_DATE + " TEXT, " +
                VOTE_AVERAGE + " REAL);";
        db.execSQL(query);
    }


    public long addMovie(MovieResults.ResultsBean movie) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("id", Integer.toString(movie.getId()));
        values.put("title", movie.getTitle());
        values.put("overview", movie.getOverview());
        values.put("backdrop_path", movie.getBackdrop_path());
        values.put("original_language", movie.getOriginal_language());
        values.put("poster_path", movie.getPoster_path());
        values.put("vote_average", Double.toString(movie.getVote_average()));
        values.put("release_date", movie.getRelease_date());
        values.put("popularity", Double.toString(movie.getPopularity()));

        long id = db.insert("movies", null, values);
        db.close();
        return id;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public MovieResults.ResultsBean getMovie(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query
                (TABLE_NAME, null, TMDB_ID + "=?", new String[] {String.valueOf(id)}, null, null, null);

        MovieResults.ResultsBean movie = null;

        if (cursor != null && cursor.moveToFirst()) {
            movie = new MovieResults.ResultsBean(
                    cursor.getString(cursor.getColumnIndexOrThrow(BACKDROP_PATH)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(TMDB_ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(MOVIE_TITLE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(ORIGINAL_LANGUAGE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(MOVE_OVERVIEW)),
                    cursor.getString(cursor.getColumnIndexOrThrow(POSTER_PATH)),
                    cursor.getDouble(cursor.getColumnIndexOrThrow(MOVIE_POPULARITY)),
                    cursor.getString(cursor.getColumnIndexOrThrow(RELEASE_DATE)),
                    cursor.getDouble(cursor.getColumnIndexOrThrow(VOTE_AVERAGE)));
            cursor.close();
        }

        db.close();

        return movie;
    }


    public String getTableName() {
        return TABLE_NAME;
    }

    public String getTmdbId() {
        return TMDB_ID;
    }

    public String getMovieTitle() {
        return MOVIE_TITLE;
    }

    public String getMoveOverview() {
        return MOVE_OVERVIEW;
    }

    public String getBackdropPath() {
        return BACKDROP_PATH;
    }

    public String getOriginalLanguage() {
        return ORIGINAL_LANGUAGE;
    }

    public String getPosterPath() {
        return POSTER_PATH;
    }

    public String getMoviePopularity() {
        return MOVIE_POPULARITY;
    }

    public String getReleaseDate() {
        return RELEASE_DATE;
    }

    public String getVoteAverage() {
        return VOTE_AVERAGE;
    }
}
