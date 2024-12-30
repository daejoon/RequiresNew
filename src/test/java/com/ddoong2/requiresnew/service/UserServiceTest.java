package com.ddoong2.requiresnew.service;

import com.ddoong2.requiresnew.model.User;
import com.ddoong2.requiresnew.model.UserLog;
import com.ddoong2.requiresnew.repository.UserLogRepository;
import com.ddoong2.requiresnew.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@ActiveProfiles("test")
@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserLogRepository userLogRepository;


    @Transactional
    @Test
    @DisplayName("사용자는 저장되는데 로그는 저장되지 않는다")
    void _사용자는_저장되는데_로그는_저장되지_않는다() {

        // given
        User user = User.builder()
                        .name("test_user")
                        .nickname("test_nickname")
                        .age(38)
                        .build();

        // when
        Long userId = userService.save(user.getName(), user.getNickname(), user.getAge());
        Optional<User> findUser = userRepository.findById(userId);
        Iterable<UserLog> findLogList = userLogRepository.findAll();

        // then
        Assertions.assertThat(findUser).isPresent();
        Assertions.assertThat(findLogList).isEmpty();
    }

    @Test
    @DisplayName("try catch를 하지 않아도 예외가 전파되지 않는다")
    void _try_catch를_하지_않아도_예외가_전파되지_않는다() {

        // given
        User user = User.builder()
                        .name("test_user")
                        .nickname("test_nickname")
                        .age(38)
                        .build();

        // when
        Long userId = userService.save2(user.getName(), user.getNickname(), user.getAge());
        Optional<User> findUser = userRepository.findById(userId);
        Iterable<UserLog> findLogList = userLogRepository.findAll();

        // then
        Assertions.assertThat(findUser).isPresent();
        Assertions.assertThat(findLogList).isEmpty();
    }

}