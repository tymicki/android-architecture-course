package com.techyourchance.mvc.screens.questionslist;

import android.view.View;

import com.techyourchance.mvc.questions.Question;

public interface QuestionsListItemViewMvc {
    View getRootView();

    void registerListener(Listener listener);

    void unregisterListener(Listener listener);

    void bindQuestion(Question question);

    interface Listener {
        void onQuestionClicked(Question question);
    }
}
