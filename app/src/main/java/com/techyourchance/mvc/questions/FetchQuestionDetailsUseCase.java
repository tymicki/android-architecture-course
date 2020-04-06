package com.techyourchance.mvc.questions;

import com.techyourchance.mvc.networking.QuestionDetailsResponseSchema;
import com.techyourchance.mvc.networking.QuestionSchema;
import com.techyourchance.mvc.networking.StackoverflowApi;
import com.techyourchance.mvc.screens.common.BaseObservableViewMvc;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FetchQuestionDetailsUseCase extends BaseObservableViewMvc<FetchQuestionDetailsUseCase.Listener> {

    private final StackoverflowApi mStackOverflowApi;

    public FetchQuestionDetailsUseCase(StackoverflowApi stackOverflowApi) {
        mStackOverflowApi = stackOverflowApi;
    }

    public void fetchQuestionDetailsAndNotify(String questionId) {
        mStackOverflowApi.fetchQuestionDetails(questionId)
                .enqueue(new Callback<QuestionDetailsResponseSchema>() {
                    @Override
                    public void onResponse(Call<QuestionDetailsResponseSchema> call, Response<QuestionDetailsResponseSchema> response) {
                        if (response.isSuccessful()) {
                            notifySuccess(response.body().getQuestion());
                        }
                    }

                    @Override
                    public void onFailure(Call<QuestionDetailsResponseSchema> call, Throwable t) {
                        notifyFailure();
                    }
                });

    }

    private void notifyFailure() {
        for (Listener listener : getListeners()) {
            listener.onQuestionDetailsFetchedFailed();
        }
    }

    private void notifySuccess(QuestionSchema questionSchema) {
        for (Listener listener : getListeners()) {
            listener.onQuestionDetailsFetched(new QuestionDetails(
                    questionSchema.getId(),
                    questionSchema.getTitle(),
                    questionSchema.getBody()
            ));
        }
    }

    public interface Listener {

        void onQuestionDetailsFetched(QuestionDetails questionDetails);

        void onQuestionDetailsFetchedFailed();
    }
}
