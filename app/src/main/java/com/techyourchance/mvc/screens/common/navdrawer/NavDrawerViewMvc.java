package com.techyourchance.mvc.screens.common.navdrawer;

import android.widget.FrameLayout;

import com.techyourchance.mvc.screens.common.views.ObservableViewMvc;

public interface NavDrawerViewMvc extends ObservableViewMvc<NavDrawerViewMvc.Listener> {

    FrameLayout getFragmentFrame();

    boolean isDrawerOpen();

    void openDrawer();

    void closeDrawer();

    interface Listener {

        void onQuestionsListClicked();
    }

}
