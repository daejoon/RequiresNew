package com.ddoong2.requiresnew.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserLogTryCatchService {
    private final UserLogService userLogService;

    public void save(Long userId, String name, String nickname, Integer age) {
        try {
            userLogService.save(userId, name, nickname, age);
        } catch (RuntimeException e) {
            log.error("{}", e.getMessage());
        }
    }
}
