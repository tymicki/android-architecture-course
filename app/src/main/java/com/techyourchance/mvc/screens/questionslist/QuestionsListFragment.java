package com.techyourchance.mvc.screens.questionslist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.techyourchance.mvc.screens.common.controllers.BackPressedListener;
import com.techyourchance.mvc.screens.common.controllers.BaseFragment;

public class QuestionsListFragment extends BaseFragment implements BackPressedListener {

    private QuestionsListController mQuestionsListController;

    public static void startClearTop(Context context) {
        Intent intent = new Intent(context, QuestionsListActivity.class);
        intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        QuestionsListViewMvc viewMvc = getCompositionRoot().getViewMvcFactory().getQuestionsListViewMvc(null);

        mQuestionsListController = getCompositionRoot().getQuestionsListController();
        mQuestionsListController.bindView(viewMvc);

        return viewMvc.getRootView();
    }

    @Override
    public void onStart() {
        super.onStart();
        mQuestionsListController.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mQuestionsListController.onStop();
    }

    @Override
    public boolean onBackPressed() {
        return mQuestionsListController.onBackPressed();
    }
}
