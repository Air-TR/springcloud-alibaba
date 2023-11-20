package com.tr.auth.config.aware;

import com.tr.auth.common.kit.JwtKit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

/**
 * @Author: TR
 */
@Slf4j
@Component
@EnableJpaAuditing
public class AuthAuditorAware implements AuditorAware<Integer> {

    /**
     * 不要在 AuditorAware 里面使用 JpaRepository
     *  会报 WARN 136804 --- [nio-8999-exec-3] c.tr.auth.config.aware.AuthAuditorAware : java.lang.StackOverflowError
     *  会重复执行很多遍 Hibernate: update ... 和 Hibernate: select ... 如：
     *    Hibernate: update user set create_by=?, create_time=?, update_by=?, update_time=?, address=?, age=?, email=?, idcard=?, nickname=?, password=?, phone=?, realname=?, username=? where id=?
     *    Hibernate: select id from user where username = ?
     *    Hibernate: update user set create_by=?, create_time=?, update_by=?, update_time=?, address=?, age=?, email=?, idcard=?, nickname=?, password=?, phone=?, realname=?, username=? where id=?
     *    Hibernate: select id from user where username = ?
     *    Hibernate: update ...
     *    Hibernate: select ...
     */
//    @Resource
//    private UserRepository userRepository;

    @Override
    public Optional<Integer> getCurrentAuditor() {
        try {
            Integer userId = JwtKit.getUserId();
            if (Objects.nonNull(userId)){
                return Optional.of(userId);
            }
        } catch (Exception e) {
            log.warn(e.getMessage());
        }
        return Optional.empty();
    }

}
