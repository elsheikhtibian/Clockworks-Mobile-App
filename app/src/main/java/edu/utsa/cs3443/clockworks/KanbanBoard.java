package edu.utsa.cs3443.clockworks;

import java.io.Serializable;
import java.util.LinkedList;

public class KanbanBoard implements Serializable {
    private LinkedList<String> taskQueue;
    private String currentTask;
    private final int MAX_SIZE = 9;

    public KanbanBoard()
    {
        this.taskQueue = new LinkedList<>();
        this.currentTask = null;
    }

    public void setCurrentTask(String currentTask)
    {
        this.currentTask = currentTask;
    }

    public String getCurrentTask()
    {
        return currentTask;
    }

    public void setTaskQueue(LinkedList<String> taskQueue)
    {
        this.taskQueue = taskQueue;
    }

    public LinkedList<String> getTaskQueue()
    {
        return taskQueue;
    }

    public void addTask(String task) {
        if (!isQueueFull()) {
            taskQueue.add(task);
            if (currentTask == null) {
                currentTask = taskQueue.poll();
            }
        }
    }

    public void finishCurrentTask() {
        if (!taskQueue.isEmpty()) {
            currentTask = taskQueue.poll();
        } else {
            currentTask = null;
        }
    }

    public void moveToCurrentTask(String task) {
            if (currentTask != null) {
                taskQueue.addFirst(currentTask);
            }
            taskQueue.remove(task);
            currentTask = task;
    }

    public boolean isQueueFull() {
        return taskQueue.size() >= MAX_SIZE;
    }
}
