package com.example.mlasut.roomwordsample.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.mlasut.roomwordsample.Question;

import java.util.List;

/**
 * Created by mlasut on 19-4-2018.
 */

@Dao
public interface QuestionDao {

    @Insert
    void insert(Question question);

    @Update
    void update(Question question);

    @Query("DELETE FROM question_table")
    void deleteAll();

    @Query("SELECT * from question_table ORDER BY id ASC")
    LiveData<List<Question>> getAllQuestions();
}
