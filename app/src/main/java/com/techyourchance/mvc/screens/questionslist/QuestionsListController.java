package com.techyourchance.mvc.screens.questionslist;

import com.techyourchance.mvc.questions.FetchLastActiveQuestionsUseCase;
import com.techyourchance.mvc.questions.Question;
import com.techyourchance.mvc.screens.common.dialogs.DialogsEventBus;
import com.techyourchance.mvc.screens.common.dialogs.DialogsManager;
import com.techyourchance.mvc.screens.common.dialogs.promptdialog.PromptDialogEvent;
import com.techyourchance.mvc.screens.common.screensnavigator.ScreensNavigator;

import java.io.Serializable;
import java.util.List;

public class QuestionsListController implements
        QuestionsListViewMvc.Listener,
        FetchLastActiveQuestionsUseCase.Listener,
        DialogsEventBus.Listener {

    private final FetchLastActiveQuestionsUseCase mFetchLastActiveQuestionsUseCase;
    private final ScreensNavigator mScreensNavigator;
    private final DialogsManager mDialogsManager;
    private final DialogsEventBus mDialogsEventBus;
    private QuestionsListViewMvc mViewMvc;
    private ScreenState mScreenState = ScreenState.IDLE;

    public QuestionsListController(FetchLastActiveQuestionsUseCase fetchLastActiveQuestionsUseCase,
                                   ScreensNavigator screensNavigator,
                                   DialogsManager dialogsManager,
                                   DialogsEventBus dialogsEventBus) {
        mFetchLastActiveQuestionsUseCase = fetchLastActiveQuestionsUseCase;
        mScreensNavigator = screensNavigator;
        mDialogsManager = dialogsManager;
        mDialogsEventBus = dialogsEventBus;
    }

    void bindView(QuestionsListViewMvc viewMvc) {
        mViewMvc = viewMvc;
    }

    SavedState getSavedState() {
        return new SavedState(mScreenState);
    }

    void restoreSavedState(SavedState savedState) {
        mScreenState = savedState.mScreenState;
    }

    void onStart() {
        mViewMvc.registerListener(this);
        mFetchLastActiveQuestionsUseCase.registerListener(this);
        mDialogsEventBus.registerListener(this);

        if (mScreenState != ScreenState.NETWORK_ERROR) {
            fetchQuestionsAndNotify();
        }
    }

    void onStop() {
        mViewMvc.unregisterListener(this);
        mFetchLastActiveQuestionsUseCase.unregisterListener(this);
        mDialogsEventBus.unregisterListener(this);
    }

    private void fetchQuestionsAndNotify() {
        mScreenState = ScreenState.FETCHING_QUESTIONS;
        mViewMvc.showProgressIndication();
        mFetchLastActiveQuestionsUseCase.fetchLastActiveQuestionsAndNotify();
    }

    @Override
    public void onQuestionClicked(Question question) {
        mScreensNavigator.toQuestionDetails(question.getId());
    }

    @Override
    public void onLastActiveQuestionsFetched(List<Question> questions) {
        mScreenState = ScreenState.QUESTIONS_LIST_SHOWN;
        mViewMvc.hideProgressIndication();
        mViewMvc.bindQuestions(questions);
    }

    @Override
    public void onLastActiveQuestionsFetchFailed() {
        mScreenState = ScreenState.NETWORK_ERROR;
        mViewMvc.hideProgressIndication();
        mDialogsManager.showUseCaseErrorDialog(null);
    }

    @Override
    public void onDialogEvent(Object event) {
        if (event instanceof PromptDialogEvent) {
            switch (((PromptDialogEvent) event).getClickedButton()) {
                case POSITIVE:
                    fetchQuestionsAndNotify();
                    break;
                case NEGATIVE:
                    mScreenState = ScreenState.IDLE;
                    break;
            }
        }
    }

    private enum ScreenState {
        IDLE, FETCHING_QUESTIONS, QUESTIONS_LIST_SHOWN, NETWORK_ERROR
    }

    static class SavedState implements Serializable {
        private final ScreenState mScreenState;

        SavedState(ScreenState screenState) {
            mScreenState = screenState;
        }
    }


}
