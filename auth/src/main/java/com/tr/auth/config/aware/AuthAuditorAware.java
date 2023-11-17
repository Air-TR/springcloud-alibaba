package com.tr.auth.config.aware;

import com.tr.auth.common.kit.JwtKit;
import com.tr.auth.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.Optional;

/**
 * @Author: TR
 */
@Component
@Slf4j
public class AuthAuditorAware implements AuditorAware<Integer> {

    @Resource
    private UserRepository userRepository;

    @Override
    public Optional<Integer> getCurrentAuditor() {
        try {
            Integer userId = userRepository.findIdByUsername(JwtKit.getUsername());
            if (Objects.nonNull(userId)){
                return Optional.of(userId);
            }
        } catch (Exception e) {
            log.warn(e.getMessage());
        }
        return Optional.empty();
    }

}
