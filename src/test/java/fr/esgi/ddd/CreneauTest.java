package fr.esgi.ddd;

import org.junit.jupiter.api.Test;

import javax.validation.ValidationException;
import java.time.Duration;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

public class CreneauTest {
    @Test
    public void should_create_with_valide_dates() {
        final LocalDateTime start = LocalDateTime.of(2019, 4, 18, 12, 0, 0);
        final LocalDateTime end = LocalDateTime.of(2019, 4, 18, 12, 30);
        assertThat(new Creneau(start, end)).isNotNull();
    }

    @Test
    public void should_not_accept_same_date() {
        final LocalDateTime moment = LocalDateTime.of(2019, 4, 18, 12, 0, 0);
        assertThatThrownBy(() -> new Creneau(moment, moment))
                .isInstanceOf(ValidationException.class)
                .hasMessage("Start must be before the end");
    }

    @Test
    public void should_not_accept_end_before_start() {
        final LocalDateTime start = LocalDateTime.of(2019, 4, 18, 12, 0, 0);
        final LocalDateTime end = LocalDateTime.of(2019, 4, 18, 9, 30);
        assertThatThrownBy(() -> new Creneau(start, end))
                .isInstanceOf(ValidationException.class)
                .hasMessage("Start must be before the end");
    }

    @Test
    public void should_create_with_valide_duration() {
        assertThat(new Creneau(LocalDateTime.of(2019, 4, 18, 12, 0, 0), Duration.ofHours(1)))
                .isNotNull();
    }

    @Test
    public void should_create_not_valide_negative_duration() {
        assertThatThrownBy(() -> new Creneau(LocalDateTime.of(2019, 4, 18, 12, 0, 0), Duration.ofHours(-1)))
                .isInstanceOf(ValidationException.class)
                .hasMessage("Start must be before the end");
    }
}
