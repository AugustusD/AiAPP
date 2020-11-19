package com.duanshl.aiapp.data.model;

import java.util.ArrayList;

public class RecgRes {
    private String log_id;
    private ArrayList<Res> result;

    public RecgRes(String log_id, ArrayList<Res> result) {
        super();
        this.log_id = log_id;
        this.result = result;
    }

    public RecgRes() {
        super();
    }

    public String getLog_id() {
        return log_id;
    }

    public void setLog_id(String log_id) {
        this.log_id = log_id;
    }

    public ArrayList<Res> getResult() {
        return result;
    }

    public void setResult(ArrayList<Res> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "RecgRes{" +
                "log_id='" + log_id + '\'' +
                ", result=" + result +
                '}';
    }
}
