package com.example.homestay.data.event;

public class AddGuestEvent {
    private int adult;
    private int child;

    public AddGuestEvent(int adult, int child) {
        this.adult = adult;
        this.child = child;
    }

    public int getAdult() {
        return adult;
    }

    public void setAdult(int adult) {
        this.adult = adult;
    }

    public int getChild() {
        return child;
    }

    public void setChild(int child) {
        this.child = child;
    }
}
