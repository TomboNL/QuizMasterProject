package com.example.mlasut.roomwordsample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewQuestionActivity extends AppCompatActivity {
    private EditText mEditQuestionView;
    private EditText mEditAnswerView;
    private EditText mEditPointsView;
    private EditText mEditDifficultyView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_question);
        mEditQuestionView = findViewById(R.id.edit_question);
        mEditAnswerView = findViewById(R.id.edit_answer);
        mEditPointsView = findViewById(R.id.edit_points);
        mEditDifficultyView = findViewById(R.id.edit_difficulty);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditQuestionView.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    replyIntent.putExtra(MainActivity.EXTRA_REPLY_QUESTION, mEditQuestionView.getText().toString());
                    replyIntent.putExtra(MainActivity.EXTRA_REPLY_ANSWER, mEditAnswerView.getText().toString());
                    replyIntent.putExtra(MainActivity.EXTRA_REPLY_POINTS, mEditPointsView.getText().toString());
                    replyIntent.putExtra(MainActivity.EXTRA_REPLY_DIFFICULTY, mEditDifficultyView.getText().toString());
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}