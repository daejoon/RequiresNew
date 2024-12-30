package com.ddoong2.requiresnew.service;

import com.ddoong2.requiresnew.model.User;
import com.ddoong2.requiresnew.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserLogService userLogService;
    private final UserLogTryCatchService userLogTryCatchService;


    @Transactional
    public Long save(String name, String nickname, int age) {
        User user = User.builder()
                        .name(name)
                        .nickname(nickname)
                        .age(age)
                        .build();

        userRepository.save(user);
        try {
            userLogService.save(user.getId(), user.getName(), user.getNickname(), user.getAge());
        } catch (Exception e) {
            log.error("{}", e.getMessage());
        }

        return user.getId();
    }

    @Transactional
    public Long save2(String name, String nickname, int age) {
        User user = User.builder()
                        .name(name)
                        .nickname(nickname)
                        .age(age)
                        .build();

        userRepository.save(user);
        userLogTryCatchService.save(user.getId(), user.getName(), user.getNickname(), user.getAge());

        return user.getId();
    }

}
