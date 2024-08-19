package edu.utsa.cs3443.clockworks;

public class Reminder {
    private String text;
    private String time;
    private boolean isCircleFilled;

    public Reminder(String text, String time) {
        this.text = text;
        this.time = time;
        this.isCircleFilled = false;
    }

    public String getText() {
        return text;
    }

    public String getTime() {
        return time;
    }

    public boolean isCircleFilled() {
        return isCircleFilled;
    }

    public void toggleCircleFilled() {
        this.isCircleFilled = !this.isCircleFilled;
    }
}

