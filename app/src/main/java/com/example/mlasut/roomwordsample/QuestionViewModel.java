package com.example.mlasut.roomwordsample;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

/**
 * Created by mlasut on 20-4-2018.
 */

public class QuestionViewModel extends AndroidViewModel {
    private QuestionRepository mRepository;
    private LiveData<List<Question>> mAllQuestions;
    private LiveData<List<Question>> mQuerryResult;
    private LiveData<Question> mQuestion;

    public QuestionViewModel(Application application) {
        super(application);
        mRepository = new QuestionRepository(application);
        mAllQuestions = mRepository.getAllQuestions();
    }

    LiveData<List<Question>> getAllQuestions() {
        return mAllQuestions;
    }

    public void insert(Question question) {
        mRepository.insert(question);
    }

    public void update(Question question) {
        mRepository.update(question);
    }
}
