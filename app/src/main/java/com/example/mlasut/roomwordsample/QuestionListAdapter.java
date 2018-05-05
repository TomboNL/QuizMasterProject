package com.example.mlasut.roomwordsample;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class QuestionListAdapter extends RecyclerView.Adapter<QuestionListAdapter.QuestionViewHolder> {
    private final MainActivity mMainActivityPtr;
    private final RecyclerView mRecyclerView;

    class QuestionViewHolder extends RecyclerView.ViewHolder {
        private final TextView questionItemView;

        private QuestionViewHolder(View itemView) {
            super(itemView);
            questionItemView = itemView.findViewById(R.id.textView);
        }
    }

    private final LayoutInflater mInflater;

    public List<Question> getQuestions() {
        return mQuestions;
    }

    private List<Question> mQuestions; // Cached copy of questions

    QuestionListAdapter(MainActivity activity_ptr, RecyclerView recyclerView) {
        mInflater = LayoutInflater.from(activity_ptr);
        mMainActivityPtr = activity_ptr;
        mRecyclerView = recyclerView;
    }

    @Override
    public QuestionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                int pos = QuestionListAdapter.this.mRecyclerView.getChildAdapterPosition(v);

                mMainActivityPtr.openQuestionDetail(pos);
            }
        });
        return new QuestionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(QuestionViewHolder holder, int position) {
        if (mQuestions != null) {
            Question current = mQuestions.get(position);
            holder.questionItemView.setText(current.getQuestion());
        } else {
            // Covers the case of data not being ready yet.
            holder.questionItemView.setText("No Question");
        }
    }

    void setQuestions(List<Question> questions){
        mQuestions = questions;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mQuestions has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mQuestions != null)
            return mQuestions.size();
        else return 0;
    }



}