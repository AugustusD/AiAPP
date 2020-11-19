package com.duanshl.aiapp.data.model;

public class Res {
    public String score;
    public String name;

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Res(String score, String name) {
        super();
        this.score = score;
        this.name = name;
    }
    public Res() {
        super();
    }
    @Override
    public String toString() {
        return "Res [score=" + score + ", name=" + name + "]";
    }
}
