package com.ddoong2.requiresnew.repository;

import com.ddoong2.requiresnew.model.User;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Profile;

import java.util.Optional;

@Profile( "test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EntityManager em;


    @Test
    @DisplayName("사용자 추가")
    void _사용자_추가() {

        // given
        User user = User.builder()
                         .name("test_user")
                         .nickname("test_nickname")
                         .age(10)
                         .build();
        em.persist(user);
        em.flush();
        em.clear();

        // when
        Optional<User> findUser = userRepository.findById(user.getId());


        // then
        Assertions.assertThat(findUser).isPresent();
    }

}