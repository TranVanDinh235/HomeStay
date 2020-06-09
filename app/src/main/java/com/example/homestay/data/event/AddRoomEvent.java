package com.example.homestay.data.event;

public class AddRoomEvent {
    private int bedroom;
    private int bathroom;
    private int guest;

    public AddRoomEvent(int bedroom, int bathroom, int guest) {
        this.bedroom = bedroom;
        this.bathroom = bathroom;
        this.guest = guest;
    }

    public int getBedroom() {
        return bedroom;
    }

    public void setBedroom(int bedroom) {
        this.bedroom = bedroom;
    }

    public int getBathroom() {
        return bathroom;
    }

    public void setBathroom(int bathroom) {
        this.bathroom = bathroom;
    }

    public int getGuest() {
        return guest;
    }

    public void setGuest(int guest) {
        this.guest = guest;
    }
}
