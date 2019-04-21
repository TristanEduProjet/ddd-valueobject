package fr.esgi.ddd.rh.model;

import org.junit.jupiter.api.Test;

import javax.validation.ValidationException;

import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AdresseTest {
    @Test
    void should_accept_valid_adress() {
        new Adresse(1, "ou", "12345", "ca");
    }

    @Test
    void should_fail_with_negative_number() {
        assertThatThrownBy(() -> new Adresse(-1, "ou", "12345", "ca")).isInstanceOf(ValidationException.class);
    }

    @Test
    void should_fail_with_number_zero() {
        assertThatThrownBy(() -> new Adresse(0, "ou", "12345", "ca")).isInstanceOf(ValidationException.class);
    }

    @Test
    void should_fail_with_null_street() {
        assertThatNullPointerException().isThrownBy(() -> new Adresse(1, null, "12345", "ca"));
    }

    @Test
    void should_fail_with_empty_street() {
        assertThatThrownBy(() -> new Adresse(1, "", "12345", "ca")).isInstanceOf(ValidationException.class);
    }

    @Test
    void should_fail_with_null_cp() {
        assertThatNullPointerException().isThrownBy(() -> new Adresse(1, "ou", null, "ca"));
    }

    @Test
    void should_fail_with_empty_cp() {
        assertThatThrownBy(() -> new Adresse(1, "ou", "", "ca")).isInstanceOf(ValidationException.class);
    }

    @Test
    void should_fail_with_null_city() {
        assertThatNullPointerException().isThrownBy(() -> new Adresse(1, "ou", "12345", null));
    }

    @Test
    void should_fail_with_empty_city() {
        assertThatThrownBy(() -> new Adresse(1, "ou", "12345", "")).isInstanceOf(ValidationException.class);
    }
}
