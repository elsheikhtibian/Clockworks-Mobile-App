package edu.utsa.cs3443.clockworks;

public class TimerModel {

    private int seconds;
    private int originalTime;
    private boolean isRunning;

    public TimerModel(int seconds) {
        this.seconds = seconds;
        this.originalTime = seconds;
        this.isRunning = false;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public int getOriginalTime() {
        return originalTime;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public void reset() {
        this.seconds = this.originalTime;
        this.isRunning = false;
    }

    public String formatTime() {
        int min = seconds / 60;
        int secs = seconds % 60;
        return String.format("%02d:%02d", min, secs);
    }
}
