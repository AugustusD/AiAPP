package com.duanshl.aiapp.data.model;

import org.json.JSONArray;

public class RecgRes {
    public String log_id;
    public JSONArray[] result;

    public String getLog_id() {
        return log_id;
    }

    public void setLog_id(String log_id) {
        this.log_id = log_id;
    }

    public JSONArray[] getResult() {
        return result;
    }

    public void setResult(JSONArray[] result) {
        this.result = result;
    }
}
