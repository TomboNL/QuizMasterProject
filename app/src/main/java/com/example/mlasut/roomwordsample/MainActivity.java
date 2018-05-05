package com.example.mlasut.roomwordsample;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int NEW_QUESTION_ACTIVITY_REQUEST_CODE = 1;
    public static final int DETAILED_QUESTION_ACTIVITY_REQUEST_CODE = 2;

    public static final String EXTRA_REPLY_QUESTION = "com.example.quizmaster.REPLY_QUESTION";
    public static final String EXTRA_REPLY_ANSWER = "com.example.quizmaster.REPLY_ANSWER";
    public static final String EXTRA_REPLY_ID = "com.example.quizmaster.REPLY_ID";
    public static final String EXTRA_REPLY_POINTS = "com.example.quizmaster.REPLY_POINTS";
    public static final String EXTRA_REPLY_DIFFICULTY = "com.example.quizmaster.REPLY_DIFFICULTY";

    private QuestionViewModel mQuestionViewModel;

    private  QuestionListAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewQuestionActivity.class);
                startActivityForResult(intent, NEW_QUESTION_ACTIVITY_REQUEST_CODE);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        mAdapter = new QuestionListAdapter(MainActivity.this, recyclerView);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mQuestionViewModel = ViewModelProviders.of(this).get(QuestionViewModel.class);

        mQuestionViewModel.getAllQuestions().observe(this, new Observer<List<Question>>() {
            @Override
            public void onChanged(@Nullable final List<Question> questions) {
                // Update the cached copy of the questions in the adapter.
                mAdapter.setQuestions(questions);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case NEW_QUESTION_ACTIVITY_REQUEST_CODE: {
                if (resultCode == RESULT_OK){
                    Question question = new Question(data.getStringExtra(EXTRA_REPLY_QUESTION),
                            data.getStringExtra(EXTRA_REPLY_ANSWER),
                            Integer.parseInt(data.getStringExtra(EXTRA_REPLY_POINTS)),
                            Integer.parseInt(data.getStringExtra(EXTRA_REPLY_DIFFICULTY)));
                    mQuestionViewModel.insert(question);
                }
                else {
                    Toast.makeText(
                            getApplicationContext(),
                            R.string.empty_not_saved,
                            Toast.LENGTH_LONG).show();
                }
            } break;
            case DETAILED_QUESTION_ACTIVITY_REQUEST_CODE: {
                if (resultCode == RESULT_OK){
                    Question question = new Question(data.getStringExtra(EXTRA_REPLY_QUESTION),
                            data.getStringExtra(EXTRA_REPLY_ANSWER), 0, 0);
                    question.setId(data.getIntExtra(EXTRA_REPLY_ID, 0));
                    mQuestionViewModel.update(question);
                }
                else {
                    Toast.makeText(
                            getApplicationContext(),
                            R.string.empty_not_saved,
                            Toast.LENGTH_LONG).show();
                }
            } break;

        }
    }

    public void openQuestionDetail(int pos) {
        Intent intent = new Intent(this, QuestionDetailActivity.class);
        intent.putExtra(EXTRA_REPLY_ID, mAdapter.getQuestions().get(pos).getId());
        startActivityForResult(intent, DETAILED_QUESTION_ACTIVITY_REQUEST_CODE);
    }
}
