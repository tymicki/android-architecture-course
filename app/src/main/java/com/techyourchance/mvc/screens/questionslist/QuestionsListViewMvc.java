package com.techyourchance.mvc.screens.questionslist;

import com.techyourchance.mvc.questions.Question;
import com.techyourchance.mvc.screens.common.ViewMvc;

import java.util.List;

interface QuestionsListViewMvc extends ViewMvc {

    interface Listener {
        void onQuestionClicked(Question question);
    }

    void registerLister(Listener listener);

    void unregisterListener(Listener listener);

    void bindQuestions(List<Question> questions);

}
