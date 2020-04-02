package com.techyourchance.mvc.screens.common.dialogs.promptdialog;

import com.techyourchance.mvc.screens.common.views.ObservableViewMvc;

public interface PromptViewMvc extends ObservableViewMvc<PromptViewMvc.Listener> {

    void setTitle(String title);

    void setMessage(String message);

    void setPositiveButtonCaption(String caption);

    void setNegativeButtonCaption(String caption);

    interface Listener {
        void onPositiveButtonClicked();

        void onNegativeButtonClicked();
    }
}

