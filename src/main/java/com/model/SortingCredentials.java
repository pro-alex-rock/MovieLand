package com.model;

import lombok.Data;

@Data
public class SortingCredentials {
    private SortDirection sortDirection = SortDirection.ASC;
    private SortingField sortingField = SortingField.ID;

    public SortingCredentials() {
    }

    public SortingCredentials(SortDirection sortDirection, SortingField sortingField) {
        this.sortDirection = sortDirection;
        this.sortingField = sortingField;
    }

    public SortDirection getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(SortDirection sortDirection) {
        this.sortDirection = sortDirection;
    }

    public SortingField getSortingField() {
        return sortingField;
    }

    public void setSortingField(SortingField sortingField) {
        this.sortingField = sortingField;
    }
}
