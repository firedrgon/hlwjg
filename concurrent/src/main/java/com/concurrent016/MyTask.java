package com.concurrent016;

/**
 * Created by Yuanp on 2018/4/21.
 */
public class MyTask extends Thread {
    private int taskId;
    private String taskName;

    public MyTask(int taskId, String taskName) {
        this.taskId = taskId;
        this.taskName = taskName;
    }

    @Override
    public void run() {
        try {
            System.out.println("run taskId = " + this.taskId);
            Thread.sleep(5 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public String toString() {
        return "MyTask{" +
                "taskId=" + taskId +
                ", taskName='" + taskName + '\'' +
                '}';
    }
}




