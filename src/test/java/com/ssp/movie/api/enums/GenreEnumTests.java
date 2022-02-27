package com.ssp.movie.api.enums;

import com.ssp.movie.api.entity.GenreEnum;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GenreEnumTests {

    @Test
    public void shouldRecogniseValidGenreType() {
        assertTrue(GenreEnum.isValidGenre("Action"));
    }

    @Test
    public void shouldNotRecogniseAnInvalidGenreType() {
        assertFalse(GenreEnum.isValidGenre("NoGenre"));
    }

}
