package com.example.homestay.data.event;

import java.util.Calendar;
import java.util.List;

public class SelectedDateEvent {
    private List<Calendar> calendarList;

    public SelectedDateEvent(List<Calendar> calendarList) {
        this.calendarList = calendarList;
    }

    public List<Calendar> getCalendarList() {
        return calendarList;
    }

    public void setCalendarList(List<Calendar> calendarList) {
        this.calendarList = calendarList;
    }
}
