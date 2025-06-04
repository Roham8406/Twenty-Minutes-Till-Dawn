package com.tilldawn.Model;

import com.tilldawn.Main;

public enum SecurityQuestions {
    Q1, Q2,  Q3, Q4, Q5, Q6, Q7;
    public String getQuestion() {
        switch (this) {
            case Q1: return Main.getLanguage().Q1;
            case Q2: return Main.getLanguage().Q2;
            case Q3: return Main.getLanguage().Q3;
            case Q4: return Main.getLanguage().Q4;
            case Q5: return Main.getLanguage().Q5;
            case Q6: return Main.getLanguage().Q6;
            case Q7: return Main.getLanguage().Q7;
        }
        return "Random question";
    }
}
