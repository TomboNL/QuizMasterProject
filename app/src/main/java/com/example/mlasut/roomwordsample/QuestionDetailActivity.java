package com.example.mlasut.roomwordsample;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class QuestionDetailActivity extends AppCompatActivity {
    private static final String TAG_QUESTION_DETAIL = "QuestionDetailActivity";

    private  Integer mId;
    private QuestionViewModel mQuestionViewModel;

    EditText textQuestion = null;
    EditText textAnswer = null;
    EditText textPoints = null;
    EditText textDifficulty = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_question_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            updateQuestion();
            }
        });

        textQuestion = (EditText) findViewById(R.id.edit_question_detail);
        textAnswer = (EditText) findViewById(R.id.edit_answer_detail);
        textPoints = (EditText) findViewById(R.id.edit_points_detail);
        textDifficulty = (EditText) findViewById(R.id.edit_difficulty_detail);

        Intent intent = getIntent();
        mId = intent.getIntExtra(MainActivity.EXTRA_REPLY_ID, 0);
        Log.v(TAG_QUESTION_DETAIL, "Loading Question with ID: " + mId.toString());

        mQuestionViewModel = ViewModelProviders.of(this).get(QuestionViewModel.class);

        mQuestionViewModel.getAllQuestions().observe(this, new Observer<List<Question>>() {
            @Override
            public void onChanged(@Nullable final List<Question> questions) {
                // Update the cached copy of the questions in the adapter.
                textQuestion.setText(questions.get(mId-1).getQuestion());
                textAnswer.setText(questions.get(mId-1).getAnswer());
                textPoints.setText(String.valueOf(questions.get(mId-1).getPoints()));
                textDifficulty.setText(String.valueOf(questions.get(mId-1).getDifficulty()));
            }
        });
    }

    private void updateQuestion(){
        Intent replyIntent = new Intent();
        replyIntent.putExtra(MainActivity.EXTRA_REPLY_QUESTION, textQuestion.getText().toString());
        replyIntent.putExtra(MainActivity.EXTRA_REPLY_ANSWER, textAnswer.getText().toString());
        replyIntent.putExtra(MainActivity.EXTRA_REPLY_ID, mId);
        setResult(RESULT_OK, replyIntent);
        finish();
    }


}
