package com.techyourchance.mvc.screens.common.dialogs.promptdialog;

public class PromptDialogEvent {

    private final Button mClickedButton;

    PromptDialogEvent(Button clickedButton) {
        mClickedButton = clickedButton;
    }

    public Button getClickedButton() {
        return mClickedButton;
    }

    public enum Button {
        POSITIVE, NEGATIVE
    }
}
