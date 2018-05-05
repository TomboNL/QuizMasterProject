package com.example.mlasut.roomwordsample.data;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.mlasut.roomwordsample.Question;
import com.example.mlasut.roomwordsample.dao.QuestionDao;

/**
 * Created by mlasut on 19-4-2018.
 */

@Database(entities = {Question.class}, version = 1)
public abstract class QuestionRoomDatabase extends RoomDatabase {
    public abstract QuestionDao questionDao();

    private static QuestionRoomDatabase INSTANCE;

    public static QuestionRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (QuestionRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            QuestionRoomDatabase.class, "question_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
