package com.techyourchance.mvc.screens.questionslist;

import com.techyourchance.mvc.questions.Question;
import com.techyourchance.mvc.screens.common.views.ObservableViewMvc;

import java.util.List;

public interface QuestionsListViewMvc extends ObservableViewMvc<QuestionsListViewMvc.Listener> {

    void bindQuestions(List<Question> questions);

    void showProgressIndication();

    void hideProgressIndication();

    interface Listener {
        void onQuestionClicked(Question question);
    }

}
