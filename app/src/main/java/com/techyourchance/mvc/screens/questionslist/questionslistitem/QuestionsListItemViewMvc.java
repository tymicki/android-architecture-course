package com.techyourchance.mvc.screens.questionslist.questionslistitem;

import com.techyourchance.mvc.questions.Question;
import com.techyourchance.mvc.screens.common.views.ObservableViewMvc;

public interface QuestionsListItemViewMvc extends ObservableViewMvc<QuestionsListItemViewMvc.Listener> {

    void bindQuestion(Question question);

    interface Listener {
        void onQuestionClicked(Question question);
    }
}
