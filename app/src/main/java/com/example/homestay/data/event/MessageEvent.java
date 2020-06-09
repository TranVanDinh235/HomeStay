package com.example.homestay.data.event;

public class MessageEvent {
    private boolean isHasNew;

    public MessageEvent(boolean isHasNew) {
        this.isHasNew = isHasNew;
    }

    public boolean isHasNew() {
        return isHasNew;
    }

    public void setHasNew(boolean hasNew) {
        isHasNew = hasNew;
    }
}
