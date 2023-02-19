package com.softarex.QuestionsPortal.entity;

public enum AnswerType {
    SINGLE_LINE_TEXT("Single line text"),
    MULTILINE_TEXT("Multiline text"),
    RADIO_BUTTON("Radio button"),
    CHECKBOX("CheckBox"),
    COMBOBOX("Combobox"),
    DATE("Date");

    private final String displayValue;

    private AnswerType(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
