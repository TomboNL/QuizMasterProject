package com.example.mlasut.roomwordsample;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by mlasut on 19-4-2018.
 */

@Entity(tableName = "question_table")
public class Question {

    @PrimaryKey (autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private int mId;

    @ColumnInfo(name = "question")
    private String mQuestion;

    @ColumnInfo(name = "answer")
    private String mAnswer;

    @ColumnInfo(name = "points")
    private int mPoints;

    @ColumnInfo(name = "difficulty")
    private int mDifficulty;

    public Question(String question, String answer, int points, int difficulty) {
        this.mQuestion = question;
        this.mAnswer = answer;
        this.mPoints = points;
        this.mDifficulty = difficulty;
    }

    public void setId(@NonNull int mId) {
        this.mId = mId;
    }

    public String getQuestion(){
        return this.mQuestion;
    }
    public String getAnswer(){
        return this.mAnswer;
    }
    public int getPoints(){
        return this.mPoints;
    }
    public int getDifficulty(){
        return this.mDifficulty;
    }
    public int getId() { return this.mId; }


}