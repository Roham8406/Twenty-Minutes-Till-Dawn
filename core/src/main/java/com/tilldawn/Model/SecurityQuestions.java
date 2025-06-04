package com.tilldawn.Model;

import com.tilldawn.Main;

public enum SecurityQuestions {
    Q1(Main.getLanguage().Q1),
    Q2(Main.getLanguage().Q2),
    Q3(Main.getLanguage().Q3),
    Q4(Main.getLanguage().Q4),
    Q5(Main.getLanguage().Q5),
    Q6(Main.getLanguage().Q6),
    Q7(Main.getLanguage().Q7);

    private String question;

    SecurityQuestions(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }
}
