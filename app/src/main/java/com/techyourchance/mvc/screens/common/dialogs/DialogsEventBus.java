package com.techyourchance.mvc.screens.common.dialogs;

import com.techyourchance.mvc.common.BaseObservable;

public class DialogsEventBus extends BaseObservable<DialogsEventBus.Listener> {

    public void postEvent(Object event) {
        for (Listener listener : getListeners()) {
            listener.onDialogEvent(event);
        }
    }

    public interface Listener {
        void onDialogEvent(Object event);
    }

}
