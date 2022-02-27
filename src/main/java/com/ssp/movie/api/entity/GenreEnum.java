package com.ssp.movie.api.entity;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum GenreEnum {
    ACTION("Action"),
    ADVENTRUE("Adventure"),
    ANIMATION("Animation"),
    BIOGRAPHY("Biography"),
    COMEDY("Comedy"),
    CRIME("Crime"),
    DOCUMENTRY("Documentary"),
    DRAMA("Drama"),
    FAMILY("Family"),
    FANTASY("Fantasy"),
    HISTORY("History"),
    HORROR("Horror"),
    MUSIC("Music"),
    MUSICAL("Musical"),
    MYSTERY("Mystery"),
    NEWS("News"),
    ROMANCE("Romance"),
    SCIFI("Sci-Fi"),
    SHORT("Short"),
    SPORT("Sport"),
    THRILLER("Thriller"),
    WAR("War"),
    WESTERN("Western");


    private String genreLookupName;

    GenreEnum(String genre) {
        this.genreLookupName = genre;
    }

    private static final Map<String, GenreEnum>
            GENRE_MAP = Stream.of(values()).collect(Collectors.toMap(GenreEnum::toString, Function.identity()));

    public String getName() {
        return this.genreLookupName;
    }

    public static boolean isValidGenre(String genre) {
        return (GENRE_MAP.get(genre.toUpperCase()) != null) ? true : false;
    }

}
