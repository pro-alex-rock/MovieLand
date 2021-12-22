package com.model;

public enum SortingField {
    ID("id"),
    RATING("rating"),
    PRICE("price");

    private String value;

    SortingField(String value) {
        this.value = value;
    }

    /*public String getValue(SortingField sortingField) {
        for (SortingField sort : values()) {
            String valueString = sort.value;
            if (sortingField.value.equalsIgnoreCase(valueString)) {
                return valueString;
            }
        }
        return ID.value;
    }*/

    public String getValue() {
        return this.value;
    }
}
