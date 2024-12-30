package com.ddoong2.requiresnew.service;

import com.ddoong2.requiresnew.model.UserLog;
import com.ddoong2.requiresnew.repository.UserLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserLogService {
    private final UserLogRepository userLogRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void save(Long userId, String name, String nickname, Integer age) {
        try {
            UserLog userLog = UserLog.builder()
                                     .userId(userId)
                                     .name(name)
                                     .nickname(nickname)
                                     .age(null)
                                     .build();
            userLogRepository.save(userLog);
        } catch (RuntimeException e) {
            log.error("{}", e.getMessage());
        }
    }

}
