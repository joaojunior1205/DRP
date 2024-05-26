package com.example.drp.domain.customizedFields;

public enum FieldType {
    NUMBER(0),
    STRING(1),
    DATE(2),
    CURRENCY(3),
    BOOLEAN(4),
    LIST_OPTIONS(5),
    RADIO(6),
    URL(7),
    FRACTIONAL(8),
    CNAE(9),
    CHECKBOX(10);

    private int type;

    FieldType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
