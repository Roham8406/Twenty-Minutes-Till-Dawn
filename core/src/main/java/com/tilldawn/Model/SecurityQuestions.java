package com.tilldawn.Model;

public enum SecurityQuestions {
    Q1("Your favorite movie?"),
    Q2("Your first partner's name?"),
    Q3("Your email address?"),
    Q4("Your favorite food?"),
    Q5("Your favorite character?"),
    Q6("Your favorite color?"),
    Q7("Random randy orton");

    private String question;
    SecurityQuestions(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }
}
