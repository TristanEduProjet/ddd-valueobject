package fr.esgi.ddd.rh.model;

import fr.esgi.ddd.rh.commun.errors.InvalidStateException;
import fr.esgi.ddd.rh.model.EntretienEntity.EntretienState;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assumptions.assumeThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;

public class EntretienEntityTest {
    private /*final static*/ TimeRange tr;
    private /*final static*/ Candidat candidat;
    private /*final static*/ ConsultantRecruteur cr;

    @BeforeEach
    void init_vars() {
        tr = new TimeRange(LocalDateTime.now(), Duration.ofMinutes(1L));
        candidat = new Candidat("test", new Profil(new Experience(0, Technology.C)));
        cr = new ConsultantRecruteur("test", new Profil(new Experience(2, Technology.C)));
    }

    @Test
    void should_not_accept_null_params() {
        Stream.<ThrowingCallable>of(
                ()-> new EntretienEntity(null, null, null),
                ()-> new EntretienEntity(tr, null, null),
                ()-> new EntretienEntity(null, cr, null),
                ()-> new EntretienEntity(null, null, candidat)
        ).forEach(fn -> assertThatNullPointerException().isThrownBy(fn));
    }

    @Test
    void should_confirm_when_waiting() {
        final EntretienEntity entretien = new EntretienEntity(tr, cr, candidat);
        assumeThat(entretien.getState()).isEqualTo(EntretienState.WAITING);
        assertThat(catchThrowable(entretien::confirm)).isNull();
        assertThat(entretien.getState()).isEqualTo(EntretienState.CONFIRMED);
    }

    @Test
    void should_not_confirm_when_already_confirmed() {
        final EntretienEntity entretien = new EntretienEntity(tr, cr, candidat);
        entretien.setState(EntretienState.CONFIRMED);
        //assumeThat(entretien.getState()).isEqualTo(EntretienState.CONFIRMED);
        assertThatThrownBy(entretien::confirm)
                .isInstanceOf(InvalidStateException.class)
                .hasMessageStartingWith("The actual state is ");
    }

    @Test
    void should_not_confirm_when_canceled() {
        final EntretienEntity entretien = new EntretienEntity(tr, cr, candidat);
        entretien.setState(EntretienState.CANCELED);
        //assumeThat(entretien.getState()).isEqualTo(EntretienState.CONFIRMED);
        assertThatThrownBy(entretien::confirm)
                .isInstanceOf(InvalidStateException.class)
                .hasMessageStartingWith("The actual state is ");
    }

    @Test
    void should_cancel_when_waiting() {
        final EntretienEntity entretien = new EntretienEntity(tr, cr, candidat);
        assumeThat(entretien.getState()).isEqualTo(EntretienState.WAITING);
        assertThat(catchThrowable(() -> entretien.cancel("This is a test"))).isNull();
        assertThat(entretien.getState()).isEqualTo(EntretienState.CANCELED);
    }

    @Test
    void should_cancel_when_confirmed() {
        final EntretienEntity entretien = new EntretienEntity(tr, cr, candidat);
        entretien.setState(EntretienState.CONFIRMED);
        //assumeThat(entretien.getState()).isEqualTo(EntretienState.CONFIRMED);
        assertThat(catchThrowable(() -> entretien.cancel("This is a test"))).isNull();
        assertThat(entretien.getState()).isEqualTo(EntretienState.CANCELED);
    }

    @Test
    void should_not_cancel_when_already_canceled() {
        final EntretienEntity entretien = new EntretienEntity(tr, cr, candidat);
        entretien.setState(EntretienState.CANCELED);
        //assumeThat(entretien.getState()).isEqualTo(EntretienState.CANCELED);
        assertThatThrownBy(() -> entretien.cancel("This is a test"))
                .isInstanceOf(InvalidStateException.class)
                .hasMessage("Already canceled");
    }
}
