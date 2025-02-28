package com.jordan.albano.verticalslice.domain;

public enum QuestionType {
    FREE_TEXT("Texto libre"),
    MULTIPLE_CHOICE("Opción múltiple"),
    NUMERICAL("Numérica"),
    DATE("Fecha");

    private final String description;

    QuestionType(String descripcion) {
        this.description = descripcion;
    }

    public String getDescription() {
        return description;
    }
}