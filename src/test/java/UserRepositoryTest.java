package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ContextConfiguration(classes = Main.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Sql("/h2-mode-oracle.sql")
    void findByNameLikeModeOracle() {
        persistUserWithName("Foo Bar");

        final var foundUsers = userRepository.findByNameLike("Foo");
        assertThat(foundUsers).hasSize(1);
    }

    @Test
    @Sql("/h2-mode-regular.sql")
    void findByNameLikeModeRegular() {
        persistUserWithName("Foo Bar");

        final var foundUsers = userRepository.findByNameLike("Foo");
        assertThat(foundUsers).hasSize(1);
    }

    private void persistUserWithName(final String name) {
        userRepository.deleteAll();
        final var user = new User();
        user.setName(name);
        user.setId(0L);
        userRepository.save(user);
    }
}
