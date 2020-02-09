package com.info.hunar.model_pojo.quiz_test_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Raghvendra Sahu on 09-Feb-20.
 */
public class QuizTestModelData {
    @SerializedName("quiz")
    @Expose
    private List<QuizTestModelDataQuiz> quiz = null;

    public List<QuizTestModelDataQuiz> getQuiz() {
        return quiz;
    }

    public void setQuiz(List<QuizTestModelDataQuiz> quiz) {
        this.quiz = quiz;
    }
}
