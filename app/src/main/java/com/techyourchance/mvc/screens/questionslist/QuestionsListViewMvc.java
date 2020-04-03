package com.techyourchance.mvc.screens.questionslist;

import com.techyourchance.mvc.questions.Question;
import com.techyourchance.mvc.screens.common.ObservableViewMvc;

import java.util.List;

interface QuestionsListViewMvc extends ObservableViewMvc<QuestionsListViewMvc.Listener> {

    interface Listener {
        void onQuestionClicked(Question question);
    }

    void bindQuestions(List<Question> questions);

}
