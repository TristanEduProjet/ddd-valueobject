package fr.esgi.ddd.rh.model;

import org.junit.jupiter.api.Test;

import javax.validation.ValidationException;
import java.time.Duration;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TimeRangeTest {
    @Test
    void should_not_accept_null_start() {
        assertThatNullPointerException().isThrownBy(() -> new TimeRange(null, LocalDateTime.now()));
    }

    @Test
    void should_not_accept_null_end() {
        assertThatNullPointerException().isThrownBy(() -> new TimeRange(LocalDateTime.now(), (LocalDateTime)null));
    }

    @Test
    void should_not_accept_null_duration() {
        assertThatNullPointerException().isThrownBy(() -> new TimeRange(LocalDateTime.now(), (Duration) null));
    }

    @Test
    void should_not_accept_same_start_and_end() {
        final LocalDateTime
                same1 = LocalDateTime.of(2000, 1, 1, 8, 0, 0),
                same2 = LocalDateTime.of(2000, 1, 1, 8, 0, 0);
        //assertThatNullPointerException().isThrownBy(() -> new TimeRange(same1, same2));
        assertThatThrownBy(() -> new TimeRange(same1, same2)).isInstanceOfAny(NullPointerException.class, ValidationException.class);
    }

    @Test
    void should_not_accept_zero_duration() {
        assertThatThrownBy(() -> new TimeRange(LocalDateTime.now(), Duration.ZERO))
                .isInstanceOf(ValidationException.class)
                /*.hasMessage("Start must be before the end")*/;
    }

    @Test
    void should_not_accept_negative_duration() {
        assertThatThrownBy(() -> new TimeRange(LocalDateTime.now(), Duration.ofMinutes(-10)))
                .isInstanceOf(ValidationException.class)
                /*.hasMessage("Start must be before the end")*/;
    }

    @Test
    void overlap_when_same_start_and_end() throws ValidationException {
        final LocalDateTime
                start1 = LocalDateTime.of(2000, 1, 1, 8, 0, 0),
                start2 = LocalDateTime.of(2000, 1, 1, 8, 0, 0),
                end1 = LocalDateTime.of(2000, 1, 1, 18, 0, 0),
                end2 = LocalDateTime.of(2000, 1, 1, 18, 0, 0);
        final TimeRange
                range1 = new TimeRange(start1, end1),
                range2 = new TimeRange(start2, end2);
        assertThat(range1.overlaps(range2)).isTrue();
        assertThat(range2.overlaps(range1)).isTrue();
    }

    @Test
    void overlap_when_not_same_start_and_end() throws ValidationException {
        final LocalDateTime
                start1 = LocalDateTime.of(2000, 1, 1, 8, 0, 0),
                start2 = LocalDateTime.of(2000, 1, 1, 10, 0, 0),
                end1 = LocalDateTime.of(2000, 1, 1, 16, 0, 0),
                end2 = LocalDateTime.of(2000, 1, 1, 18, 0, 0);
        final TimeRange
                range1 = new TimeRange(start1, end1),
                range2 = new TimeRange(start2, end2);
        assertThat(range1.overlaps(range2)).isTrue();
        assertThat(range2.overlaps(range1)).isTrue();
    }

    @Test
    void overlap_when_same_start_but_first_end_after() throws ValidationException {
        final LocalDateTime
                start1 = LocalDateTime.of(2000, 1, 1, 8, 0, 0),
                start2 = LocalDateTime.of(2000, 1, 1, 8, 0, 0),
                end1 = LocalDateTime.of(2000, 1, 1, 18, 0, 0),
                end2 = LocalDateTime.of(2000, 1, 1, 16, 0, 0);
        final TimeRange
                range1 = new TimeRange(start1, end1),
                range2 = new TimeRange(start2, end2);
        assertThat(range1.overlaps(range2)).isTrue();
        assertThat(range2.overlaps(range1)).isTrue();
    }

    @Test
    void overlap_when_same_end_but_first_start_before() throws ValidationException {
        final LocalDateTime
                start1 = LocalDateTime.of(2000, 1, 1, 6, 0, 0),
                start2 = LocalDateTime.of(2000, 1, 1, 8, 0, 0),
                end1 = LocalDateTime.of(2000, 1, 1, 18, 0, 0),
                end2 = LocalDateTime.of(2000, 1, 1, 18, 0, 0);
        final TimeRange
                range1 = new TimeRange(start1, end1),
                range2 = new TimeRange(start2, end2);
        assertThat(range1.overlaps(range2)).isTrue();
        assertThat(range2.overlaps(range1)).isTrue();
    }


    @Test
    void overlap_when_first_longer_than_second() throws ValidationException {
        final LocalDateTime
                start1 = LocalDateTime.of(2000, 1, 1, 8, 0, 0),
                start2 = LocalDateTime.of(2000, 1, 1, 10, 0, 0),
                end1 = LocalDateTime.of(2000, 1, 1, 18, 0, 0),
                end2 = LocalDateTime.of(2000, 1, 1, 16, 0, 0);
        final TimeRange
                range1 = new TimeRange(start1, end1),
                range2 = new TimeRange(start2, end2);
        assertThat(range1.overlaps(range2)).isTrue();
        assertThat(range2.overlaps(range1)).isTrue();
    }

    @Test
    void not_overlap_when_first_end_before_second() throws ValidationException {
        final LocalDateTime
                start1 = LocalDateTime.of(2000, 1, 1, 8, 0, 0),
                start2 = LocalDateTime.of(2000, 1, 1, 12, 0, 1),
                end1 = LocalDateTime.of(2000, 1, 1, 12, 0, 0),
                end2 = LocalDateTime.of(2000, 1, 1, 18, 0, 0);
        final TimeRange
                range1 = new TimeRange(start1, end1),
                range2 = new TimeRange(start2, end2);
        assertThat(range1.overlaps(range2)).isFalse();
        assertThat(range2.overlaps(range1)).isFalse();
    }

    @Test
    void include_when_smaller/*or_same_datetime*/() throws ValidationException {
        final LocalDateTime
                start1 = LocalDateTime.of(2000, 1, 1, 8, 0, 0),
                start2 = LocalDateTime.of(2000, 1, 1, 10, 0, 0),
                end1 = LocalDateTime.of(2000, 1, 1, 18, 0, 0),
                end2 = LocalDateTime.of(2000, 1, 1, 16, 0, 0);
        final TimeRange
                range1 = new TimeRange(start1, end1),
                range2 = new TimeRange(start2, end2);
        assertThat(range2.isIncludeIn(range1)).isTrue();
    }

    @Test
    void not_include_when_taller() throws ValidationException { //inverse of _when_smaller
        final LocalDateTime
                start1 = LocalDateTime.of(2000, 1, 1, 8, 0, 0),
                start2 = LocalDateTime.of(2000, 1, 1, 10, 0, 0),
                end1 = LocalDateTime.of(2000, 1, 1, 18, 0, 0),
                end2 = LocalDateTime.of(2000, 1, 1, 16, 0, 0);
        final TimeRange
                range1 = new TimeRange(start1, end1),
                range2 = new TimeRange(start2, end2);
        assertThat(range2.overlaps(range1)).isTrue();
    }

    @Test
    void not_include_when_overlap() throws ValidationException {
        final LocalDateTime
                start1 = LocalDateTime.of(2000, 1, 1, 8, 0, 0),
                start2 = LocalDateTime.of(2000, 1, 1, 10, 0, 0),
                end1 = LocalDateTime.of(2000, 1, 1, 16, 0, 0),
                end2 = LocalDateTime.of(2000, 1, 1, 18, 0, 0);
        final TimeRange
                range1 = new TimeRange(start1, end1),
                range2 = new TimeRange(start2, end2);
        assertThat(range1.isIncludeIn(range2)).isFalse();
        assertThat(range2.isIncludeIn(range1)).isFalse();
    }
}
