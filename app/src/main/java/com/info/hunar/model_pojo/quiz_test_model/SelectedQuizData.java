package com.info.hunar.model_pojo.quiz_test_model;

/**
 * Created by Raghvendra Sahu on 12-Feb-20.
 */
public class SelectedQuizData {
    String id; String option;

    public SelectedQuizData(String id, String option) {
        this.id = id;
        this.option = option;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }
}
