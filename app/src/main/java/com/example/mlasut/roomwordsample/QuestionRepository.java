package com.example.mlasut.roomwordsample;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.mlasut.roomwordsample.dao.QuestionDao;
import com.example.mlasut.roomwordsample.data.QuestionRoomDatabase;

import java.util.List;

/**
 * Created by mlasut on 20-4-2018.
 */

public class QuestionRepository {
    private static final int ACTION_INSERT = 1;
    private static final int ACTION_UPDATE = 2;

    private QuestionDao mQuestionDao;
    private LiveData<List<Question>> mAllQuestions;
    private LiveData<List<Question>> mQueryQuestion;

    QuestionRepository(Application application) {
        QuestionRoomDatabase db = QuestionRoomDatabase.getDatabase(application);
        mQuestionDao = db.questionDao();
        mAllQuestions = mQuestionDao.getAllQuestions();
    }

    LiveData<List<Question>> getAllQuestions() {
        return mAllQuestions;
    }

    public void insert (Question question) {
        new insertAsyncTask(mQuestionDao, ACTION_INSERT).execute(question);
    }

    public void update (Question question) {
        new insertAsyncTask(mQuestionDao, ACTION_UPDATE).execute(question);
    }


    private static class insertAsyncTask extends AsyncTask<Question, Void, Void> {
        private QuestionDao mAsyncTaskDao;
        private int mAction;

        insertAsyncTask(QuestionDao dao, int action) {
            mAsyncTaskDao = dao;
            mAction = action;
        }

        @Override
        protected Void doInBackground(final Question... params) {
            switch (mAction){
                case ACTION_INSERT: mAsyncTaskDao.insert(params[0]); break;
                case ACTION_UPDATE: mAsyncTaskDao.update(params[0]); break;
            }

            return null;
        }
    }
}
