package fr.esgi.ddd.rh.model;

import org.junit.jupiter.api.Test;

import javax.validation.ValidationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.catchThrowable;

public class ExperienceTest {
    @Test
    void should_create_when_params_correct_yearpositive() {
        assertThat(catchThrowable(() -> new Experience(2, Technology.JAVA))).isNull();
    }

    @Test
    void should_create_when_params_correct_noyear() {
        assertThat(catchThrowable(() -> new Experience(0, Technology.JAVA))).isNull();
    }

    @Test
    void should_not_create_when_year_negative() {
        assertThatThrownBy(() -> new Experience(-1, Technology.CPP)).isInstanceOf(ValidationException.class);
    }

    @Test
    void should_not_create_when_techno_null() {
        assertThatNullPointerException().isThrownBy(() -> new Experience(1, null));
    }
}
