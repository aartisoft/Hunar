package com.info.hunar.binding_interface;

import com.info.hunar.model_pojo.quiz_test_model.QuizTestModelDataQuiz;

/**
 * Created by Raghvendra Sahu on 12-Feb-20.
 */
public interface TestQuizClickListener {
    void radioClicked_one(QuizTestModelDataQuiz dataQuiz);
    void radioClicked_two(QuizTestModelDataQuiz dataQuiz);
    void radioClicked_three(QuizTestModelDataQuiz dataQuiz);
    void radioClicked_four(QuizTestModelDataQuiz dataQuiz);
}
