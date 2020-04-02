package com.techyourchance.mvc.screens.questiondetails;

import com.techyourchance.mvc.questions.QuestionDetails;
import com.techyourchance.mvc.screens.common.views.ObservableViewMvc;

public interface QuestionDetailsViewMvc extends ObservableViewMvc<QuestionDetailsViewMvc.Listener> {

    void bindQuestion(QuestionDetails question);

    void showProgressIndication();

    void hideProgressIndication();

    interface Listener {
        void onNavigateUpClicked();
    }
}
