package fr.esgi.ddd.valueobject;

import fr.esgi.ddd.Technology;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;

public class ProfilTest {
    private static Experience exp1, exp2;
    @BeforeAll
    static void prepareVars() {
        /*this.*/exp1 = new Experience(1, Technology.JAVA);
        /*this.*/exp2 = new Experience(2, Technology.C);
    }

    @Test
    void should_accept_empty_list() {
        assertThat(catchThrowable(() -> new Profil(Collections.emptySet()))).isNull();
    }

    @Test
    void should_accept_list() {
        assertThat(catchThrowable(() -> new Profil(new HashSet<>(Arrays.asList(exp1, exp2))))).isNull();
    }

    @Test
    void should_not_accept_null_in_list() {
        assertThatNullPointerException().isThrownBy(() -> new Profil(new Experience[]{exp1, null, exp2}));
    }

    @Test
    void should_accept_empty_array() {
        assertThat(catchThrowable(() -> new Profil(new Experience[]{}))).isNull();
    }

    @Test
    void should_accept_varg() {
        assertThat(catchThrowable(() -> new Profil(exp1, exp2))).isNull();
    }

    @Test
    void should_not_accept_null_in_array() {
        assertThatNullPointerException().isThrownBy(() -> new Profil(new HashSet<>(Arrays.asList(exp1, null, exp2))));
    }
}
