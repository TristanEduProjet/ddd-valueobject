package fr.esgi.ddd.entity;

import fr.esgi.ddd.InvalidStateException;
import fr.esgi.ddd.entity.Entretien.EntretienState;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assumptions.assumeThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;

public class EntretienTest {
    @Test
    void should_not_accept_null_params() {
        Stream.<ThrowingCallable>of(
                ()-> new Entretien(null, null, null),
                ()-> new Entretien("TODO", null, null),
                ()-> new Entretien(null, "TODO", null),
                ()-> new Entretien(null, null, "TODO")
        ).forEach(fn -> assertThatNullPointerException().isThrownBy(fn));
    }

    @Test
    void should_confirm_when_waiting() {
        final Entretien entretien = new Entretien("TODO", "TODO", "TODO");
        assumeThat(entretien.getState()).isEqualTo(EntretienState.WAITING);
        assertThat(catchThrowable(entretien::confirm)).isNull();
        assertThat(entretien.getState()).isEqualTo(EntretienState.CONFIRMED);
    }

    @Test
    void should_not_confirm_when_already_confirmed() {
        final Entretien entretien = new Entretien("TODO", "TODO", "TODO");
        entretien.setState(EntretienState.CONFIRMED);
        //assumeThat(entretien.getState()).isEqualTo(EntretienState.CONFIRMED);
        assertThatThrownBy(entretien::confirm)
                .isInstanceOf(InvalidStateException.class)
                .hasMessageStartingWith("The actual state is ");
    }

    @Test
    void should_not_confirm_when_canceled() {
        final Entretien entretien = new Entretien("TODO", "TODO", "TODO");
        entretien.setState(EntretienState.CANCELED);
        //assumeThat(entretien.getState()).isEqualTo(EntretienState.CONFIRMED);
        assertThatThrownBy(entretien::confirm)
                .isInstanceOf(InvalidStateException.class)
                .hasMessageStartingWith("The actual state is ");
    }

    @Test
    void should_cancel_when_waiting() {
        final Entretien entretien = new Entretien("TODO", "TODO", "TODO");
        assumeThat(entretien.getState()).isEqualTo(EntretienState.WAITING);
        assertThat(catchThrowable(() -> entretien.cancel("This is a test"))).isNull();
        assertThat(entretien.getState()).isEqualTo(EntretienState.CANCELED);
    }

    @Test
    void should_cancel_when_confirmed() {
        final Entretien entretien = new Entretien("TODO", "TODO", "TODO");
        entretien.setState(EntretienState.CONFIRMED);
        //assumeThat(entretien.getState()).isEqualTo(EntretienState.CONFIRMED);
        assertThat(catchThrowable(() -> entretien.cancel("This is a test"))).isNull();
        assertThat(entretien.getState()).isEqualTo(EntretienState.CANCELED);
    }

    @Test
    void should_not_cancel_when_already_canceled() {
        final Entretien entretien = new Entretien("TODO", "TODO", "TODO");
        entretien.setState(EntretienState.CANCELED);
        //assumeThat(entretien.getState()).isEqualTo(EntretienState.CANCELED);
        assertThatThrownBy(() -> entretien.cancel("This is a test"))
                .isInstanceOf(InvalidStateException.class)
                .hasMessage("Already canceled");
    }
}
