package com.model;

public enum Genre {
    DRAMA("драма"),
    CRIME("криминал"),
    FANTASY("фэнтези"),
    DETECTIVE("детектив"),
    MELODRAMA("мелодрама"),
    BIOGRAPHY("биография"),
    COMEDY("комедия"),
    FANTASTIC("фантастика"),
    ACTION("боевик"),
    THRILLER("триллер"),
    ADVENTURES("приключения"),
    ANIME("аниме"),
    CARTOON("мультфильм"),
    FAMILY("семейный"),
    WESTERN("вестерн");

    private final String value;

    Genre(String value) {
        this.value = value;
    }

    public static Genre setGenre(String genre) {
        for (Genre value : Genre.values()) {
            if (genre.equalsIgnoreCase(value.name())) {
                return value;
            }
        }
        return Genre.valueOf("Unknown");
    }
}
