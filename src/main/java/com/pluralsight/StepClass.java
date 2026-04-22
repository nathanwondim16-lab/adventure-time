package com.pluralsight;

public class StepClass {
    private int id;
    private String storyText;
    private String option1Text;
    private int option1NextStepId;
    private String option2Text;
    private int option2NextStepId;

    public StepClass(int id, String storyText, String option1Text, int option1NextStepId, String option2Text, int option2NextStepId) {
        this.id = id;
        this.storyText = storyText;
        this.option1Text = option1Text;
        this.option1NextStepId = option1NextStepId;
        this.option2Text = option2Text;
        this.option2NextStepId = option2NextStepId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStoryText() {
        return storyText;
    }

    public void setStoryText(String storyText) {
        this.storyText = storyText;
    }

    public String getOption1Text() {
        return option1Text;
    }

    public void setOption1Text(String option1Text) {
        this.option1Text = option1Text;
    }

    public int getOption1NextStepId() {
        return option1NextStepId;
    }

    public void setOption1NextStepId(int option1NextStepId) {
        this.option1NextStepId = option1NextStepId;
    }

    public String getOption2Text() {
        return option2Text;
    }

    public void setOption2Text(String option2Text) {
        this.option2Text = option2Text;
    }

    public int getOption2NextStepId() {
        return option2NextStepId;
    }

    public void setOption2NextStepId(int option2NextStepId) {
        this.option2NextStepId = option2NextStepId;
    }

    @Override
    public String toString() {
        return getId() + " " + getStoryText() + " " + getOption1Text() + " " + getOption1NextStepId() + " " + getOption2Text() + " " + getOption2NextStepId();
    }
}
